package com.school.project.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto implements Serializable {

    @ApiModelProperty(hidden = true)
    private Long id;

    @NotEmpty
    @Length(min = 2, max = 20)
    private String name;
}
