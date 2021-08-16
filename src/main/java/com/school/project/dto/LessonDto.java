package com.school.project.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto implements Serializable {

    @ApiModelProperty(hidden = true)
    private Long id;

    @NotEmpty
    @Length(min = 2, max = 20)
    private String thema;

    @NotEmpty
    private List<SubjectDto> subjects;

    @NotNull
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Long createdDate;

    @NotNull
    private GroupDto group;

    @NotNull
    private UserDto teacher;
}
