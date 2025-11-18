package com.texnoera.mapper;

import com.texnoera.dao.entity.User;
import com.texnoera.dto.UserDto;
import com.texnoera.dto.enums.UserStatus;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-17T20:43:03+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.4 (Amazon.com Inc.)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setAge( user.getAge() );
        userDto.setStatus( user.getStatus() );
        userDto.setCreatedAt( user.getCreatedAt() );

        mapFullName( userDto, user );

        return userDto;
    }

    @Override
    public User toUserAdd(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        if ( userDto.getStatus() != null ) {
            user.setStatus( userDto.getStatus() );
        }
        else {
            user.setStatus( UserStatus.ACTIVE );
        }
        user.setId( userDto.getId() );
        user.setAge( userDto.getAge() );
        user.setCreatedAt( userDto.getCreatedAt() );

        mapNameAndSurname( user, userDto );

        return user;
    }

    @Override
    public void updateUser(User user, UserDto userDto) {
        if ( userDto == null ) {
            return;
        }

        if ( userDto.getId() != null ) {
            user.setId( userDto.getId() );
        }
        if ( userDto.getAge() != null ) {
            user.setAge( userDto.getAge() );
        }
        if ( userDto.getStatus() != null ) {
            user.setStatus( userDto.getStatus() );
        }
        if ( userDto.getCreatedAt() != null ) {
            user.setCreatedAt( userDto.getCreatedAt() );
        }

        mapNameAndSurname( user, userDto );
    }
}
