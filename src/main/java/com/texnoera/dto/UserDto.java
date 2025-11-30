package com.texnoera.dto;

import com.texnoera.dto.enums.UserStatus;
import com.texnoera.validations.ValidPassword;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDto implements Serializable {

    private Long id;

    @Schema(defaultValue = "Turkhan")
    @NotEmpty(message = "fullName can not be empty")
    //@Pattern(regexp = "^(?![????])[A-Za-z«Á÷ˆ‹¸??????]+(?: [A-Za-z«Á÷ˆ‹¸??????]+)*$")
    private String fullName;

    @ValidPassword
    private String password;

    @Positive(message = "age can not be less or equal to 0")
    @NotNull(message = "age can not be null")
    private Integer age;
    private UserStatus status;

    private String cardNumber;
    private LocalDateTime createdAt;
    private BookDto book;

}
