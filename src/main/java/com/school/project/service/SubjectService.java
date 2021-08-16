package com.school.project.service;

import com.school.project.model.entities.Subject;

import java.util.List;

public interface SubjectService {

    Subject create(Subject subject);

    void update(Subject subject, Long id);

    Subject getSubjectById(Long id);

    List<Subject> getAllSubjects();

}
