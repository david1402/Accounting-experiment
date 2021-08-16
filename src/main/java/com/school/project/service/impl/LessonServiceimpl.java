package com.school.project.service.impl;

import com.school.project.exception.LessonNotFoundException;
import com.school.project.model.entities.Lesson;
import com.school.project.repository.LessonRepository;
import com.school.project.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LessonServiceimpl implements LessonService {

    @Autowired
    LessonRepository lessonRepository;

    @Override
    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson getLesson(Long id) {
        Optional<Lesson> byId = lessonRepository.findById(id);
        if (!byId.isPresent()) throw new LessonNotFoundException("Lesson with id : " + id + " is not found");
        return byId.get();
    }

    @Override
    public List<Lesson> getAllLessonsByGroup(Long id) {
        List<Lesson> lessonByGroupId = lessonRepository.getAllByGroupId(id);
        validationResponseIsNotEmpty(lessonByGroupId, "Group", id);
        return lessonByGroupId;
    }

    @Override
    public List<Lesson> getAllLessonsByTeacher(Long id) {
        List<Lesson> lessonByTeacherId = lessonRepository.getAllByTeacherId(id);
        validationResponseIsNotEmpty(lessonByTeacherId, "Teacher", id);
        return lessonByTeacherId;
    }

    @Override
    public List<Lesson> getAllLessonsBySubject(Long id) {
        List<Lesson> lessonBySubjectId = lessonRepository.getAllBySubjectsId(id);
        validationResponseIsNotEmpty(lessonBySubjectId, "Subject", id);
        return lessonBySubjectId;
    }

    @Override
    public void updateLesson(Lesson lessonToUpdate, Long id) {
        lessonToUpdate.setId(id);
        lessonRepository.saveAndFlush(lessonToUpdate);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public void validationResponseIsNotEmpty(List<Lesson> input, String name, Long id) {
        if (input.isEmpty())
            throw new LessonNotFoundException("Lessons with " + name + " id : " + id + " is not found");
    }
}

