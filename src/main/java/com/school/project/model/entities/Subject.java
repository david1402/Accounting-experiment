package com.school.project.model.entities;

import com.school.project.model.AbstractBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SUBJECT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject extends AbstractBaseEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

}
