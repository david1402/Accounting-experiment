package com.school.project.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDto implements Serializable {

    @ApiModelProperty(hidden = true)
    private Long id;

    @NotEmpty
    @Length(min = 2, max = 20)
    private String name;

    @NotNull
    @Max(300)
    private Integer hours;

    @NotEmpty
    private List<SubjectDto> subjects;

    @NotNull
    private Double price;
}
