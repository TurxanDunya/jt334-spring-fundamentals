package com.texnoera.service;

import com.texnoera.client.CardClient;
import com.texnoera.client.CardFeignClient;
import com.texnoera.dao.UserCacheRepository;
import com.texnoera.dao.UserRepository;
import com.texnoera.dao.entity.User;
import com.texnoera.error.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static com.texnoera.constants.TestConstants.AGE;
import static com.texnoera.constants.TestConstants.NAME;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserCacheRepository userCacheRepository;

    @Mock
    private CardClient cardClient;

    @Mock
    private CardFeignClient cardFeignClient;

    @InjectMocks
    private UserService userService;

    @Test
    void getByName_IfCacheExist_ReturnFromCache() {
        String given = NAME;

        User user = new User();
        user.setAge(AGE);
        Mockito.when(userCacheRepository.read(given)).thenReturn(user);

        User actual = userService.getByName(given);

        User expected = new User();
        expected.setAge(29);
        Assertions.assertEquals(expected, actual);
        verify(userCacheRepository, times(1)).read(given);
    }

    @Test
    void getByName_IfCacheNotExist_ReturnFromRepository() {
        String given = NAME;

        Mockito.when(userCacheRepository.read(given))
                .thenReturn(null);

        User user = new User();
        user.setAge(AGE);
        Mockito.when(userRepository.findByName(given))
                .thenReturn(List.of(user));
        Mockito.doNothing().when(userCacheRepository).save(user);

        User actual = userService.getByName(given);

        User expected = new User();
        expected.setAge(29);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getByName_IfDataNotFoundInRepo_ThrowException() {
        String given = NAME;

        Mockito.when(userCacheRepository.read(given))
                .thenReturn(null);

        Mockito.when(userRepository.findByName(given))
                .thenReturn(Collections.emptyList());

        Assertions.assertThrows(UserNotFoundException.class,
                () -> userService.getByName(given));
        verify(userCacheRepository, never()).save(any());
    }

}
