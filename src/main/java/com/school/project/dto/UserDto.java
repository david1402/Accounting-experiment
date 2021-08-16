package com.school.project.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    @ApiModelProperty(hidden = true)
    private Long id;

    @NotEmpty
    @Length(min = 2, max = 20)
    private String firstName;

    @NotEmpty
    @Length(min = 2, max = 20)
    private String lastName;

    @NotNull
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date birthDate;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String phoneNumber;

}
