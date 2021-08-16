package com.school.project.model.entities;

import com.school.project.model.AbstractBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "MODULE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Module extends AbstractBaseEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "HOURS", nullable = false)
    private Integer hours;

    @ManyToMany(targetEntity = Subject.class)
    @JoinTable(name = "MODULE_SUBJECT", joinColumns = {@JoinColumn(name = "MODULE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SUBJECT_ID")})
    private List<Subject> subjects;

    @Column(name = "PRICE")
    private Double price;

}
