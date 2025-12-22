package com.texnoera.mapper;

import com.texnoera.dao.entity.User;
import com.texnoera.dto.UserDto;
import com.texnoera.dto.enums.UserStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.texnoera.constants.TestConstants.AGE;
import static com.texnoera.constants.TestConstants.NAME;

public class UserMapperTest {

    // given
    // actual
    // expected

    @Test
    void testToUserDto() {
        User given = new User();
        given.setId(1L);
        given.setName(NAME);
        given.setSurname("Testov");
        given.setAge(AGE);
        given.setStatus(UserStatus.ACTIVE);
        given.setCreatedAt(LocalDateTime.of(2025, 5, 5, 12, 0, 0));

        UserDto expected = new UserDto();
        expected.setId(1L);
        expected.setFullName("Jack Testov");
        expected.setPassword(null);
        expected.setAge(AGE);
        expected.setStatus(UserStatus.ACTIVE);
        expected.setCardNumber(null);
        expected.setCreatedAt(LocalDateTime.of(2025, 5, 5, 12, 0, 0));
        expected.setBook(null);

        UserDto actual = UserMapper.INSTANCE.toUserDto(given);
        Assertions.assertEquals(expected, actual);
    }

}
