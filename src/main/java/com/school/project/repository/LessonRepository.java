package com.school.project.repository;

import com.school.project.model.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> getAllByGroupId(Long id);

    List<Lesson> getAllByTeacherId(Long id);

    List<Lesson> getAllBySubjectsId(Long id);

}
