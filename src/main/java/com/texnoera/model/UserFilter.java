package com.texnoera.model;

import com.texnoera.dto.enums.UserStatus;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFilter {

    @Hidden
    private Long id;
    private String name;
    private String surname;
    private UserStatus status;

    @Min(value = 1, message = "Invalid age")
    @Max(value = 150, message = "Invalid age")
    private Integer age;
    private LocalDate from;
    private LocalDate to;

    @Size(min = 1, max = 5)
    private List<Object> objects;

    @Email
    private String email;
}
