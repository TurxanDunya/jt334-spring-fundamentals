package com.texnoera.service;

import com.texnoera.client.CardClient;
import com.texnoera.client.CardFeignClient;
import com.texnoera.client.model.CardDto;
import com.texnoera.dao.UserCacheRepository;
import com.texnoera.dao.UserRepository;
import com.texnoera.dao.entity.User;
import com.texnoera.dto.UserDto;
import com.texnoera.error.exceptions.UserNotFoundException;
import com.texnoera.mapper.UserMapper;
import com.texnoera.messaging.kafka.KafkaProducer;
import com.texnoera.messaging.rabbit.UserPublisher;
import com.texnoera.messaging.event.UserCreatedEvent;
import com.texnoera.model.UserFilter;
import com.texnoera.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.texnoera.config.properties.KafkaConstants.USER_CREATION_TOPIC;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${application.whitelist.user}")
    public Long whitelistUserId;

    private final UserRepository userRepository;
    private final UserCacheRepository userCacheRepository;
    private final CardClient cardClient;
    private final CardFeignClient cardFeignClient;
    private final UserPublisher userPublisher;
    private final KafkaProducer kafkaProducer;

    public List<UserDto> getUsers(UserFilter filter) {
        log.debug("Getting users with filter: {}", filter);
        List<UserDto> userDtoList = userRepository.findAll(new UserSpecification(filter)).stream()
                .map(UserMapper.INSTANCE::toUserDto)
                .toList();

        for (UserDto userDto : userDtoList) {
            CardDto cardDto = cardFeignClient.getCardById(userDto.getId());
            userDto.setCardNumber(cardDto.getPan());
        }

        return userDtoList;
    }

    public User getByName(String name) {
        User user = userCacheRepository.read(name);
        if (Objects.nonNull(user)) {
            log.debug("User read from cache: {}", user);
            return user;
        }

        User userFromDb = userRepository.findByName(name).stream()
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        userCacheRepository.save(userFromDb);
        log.debug("User read from db: {}", user);
        return userFromDb;
    }

    public User getBySurName(String surName) {
        return userRepository.findByLastName(surName).stream()
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void add(UserDto userDto) {
        var userCreatedEvent = UserCreatedEvent.builder()
                .username(userDto.getFullName())
                .message("User created successfully")
                .email(userDto.getEmail())
                .build();

        userRepository.save(UserMapper.INSTANCE.toUserAdd(userDto));
        //userPublisher.send(userCreatedEvent);
        kafkaProducer.send(USER_CREATION_TOPIC,
                userCreatedEvent,
                KafkaProducer.MessageType.USER_CREATION);
        log.info("Added user: {}", userDto);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void update(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        UserMapper.INSTANCE.updateUser(user, userDto);
        userRepository.save(user);
    }

    public void updateAll(List<User> users) {
        userRepository.saveAll(users);
    }

}
