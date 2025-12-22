package com.texnoera.scheduler;

import com.texnoera.dao.entity.User;
import com.texnoera.dto.UserDto;
import com.texnoera.dto.enums.UserStatus;
import com.texnoera.mapper.UserMapper;
import com.texnoera.model.UserFilter;
import com.texnoera.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserStatusScheduler {

    private final UserService userService;

    @Scheduled(cron = "0 0 10 1 1 1")
    public void updateUserStatusMoreThanFiveYears() {
        log.info("Updating user status more than 5 years STARTED");
        List<UserDto> users = userService.getUsers(UserFilter.builder()
                .status(UserStatus.ACTIVE)
                .to(LocalDate.now().minusYears(5))
                .build());

        List<User> userEntities = users.stream()
                .map(UserMapper.INSTANCE::toUserAdd)
                .toList();

        userEntities.forEach(userEntity ->
                userEntity.setStatus(UserStatus.INACTIVE));

        userService.updateAll(userEntities);
        log.info("Updating user status more than 5 years FINISHED. " +
                "Updated: {} rows", userEntities.size());
    }

}
