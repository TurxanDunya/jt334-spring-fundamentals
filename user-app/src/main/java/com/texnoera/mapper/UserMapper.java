package com.texnoera.mapper;

import com.texnoera.dao.entity.Book;
import com.texnoera.dao.entity.User;
import com.texnoera.dto.BookDto;
import com.texnoera.dto.UserDto;
import com.texnoera.dto.enums.UserStatus;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(imports = UserStatus.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //    @Mapping(target = "book", source = "book", qualifiedByName = "mapBooks")
    UserDto toUserDto(User user);

    @Mapping(target = "status", defaultExpression = "java(UserStatus.ACTIVE)",
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    User toUserAdd(UserDto userDto);

    @AfterMapping
    default void mapFullName(@MappingTarget UserDto userDto, User user) {
        userDto.setFullName(user.getName() + " " + user.getSurname());
    }

    @Named("mapBooks")
    default BookDto mapBook(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setName(book.getName());
        bookDto.setPublishedDate(book.getPublishedDate());
        return bookDto;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(@MappingTarget User user, UserDto userDto);

    @AfterMapping
    default void mapNameAndSurname(@MappingTarget User user, UserDto userDto) {
        if (userDto == null) {
            return;
        }

        String[] nameAndSurname = userDto.getFullName().split(" ");
        user.setName(nameAndSurname[0]);
        user.setSurname(nameAndSurname[1]);
    }

}
