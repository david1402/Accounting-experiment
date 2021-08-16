package com.school.project.model.entities;

import com.school.project.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "LESSON")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lesson extends AbstractEntity {

    @Column(name = "THEMA", nullable = false)
    private String thema;

    @OneToMany(targetEntity = Subject.class)
    @JoinTable(name = "LESSON_SUBJECT", joinColumns = {@JoinColumn(name = "LESSON_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SUBJECT_ID")})
    private List<Subject> subjects;

    @OneToOne
    @JoinColumn(name = "GROUP_ID", nullable = false)
    private Group group;

    @OneToOne
    @JoinColumn(name = "TEACHER_ID", nullable = false)
    private User teacher;

}
