package com.school.project.service;

import com.school.project.dto.LessonDto;
import com.school.project.model.entities.Lesson;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LessonService {
    Lesson createLesson(Lesson lesson);

    Lesson getLesson(Long id);

    List<Lesson> getAllLessonsByGroup(Long id);

    List<Lesson> getAllLessonsByTeacher(Long id);

    List<Lesson> getAllLessonsBySubject(Long id);

    void updateLesson(Lesson lesson, Long id);

    List<Lesson> getAllLessons();
}
