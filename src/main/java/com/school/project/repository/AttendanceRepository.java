package com.school.project.repository;

import com.school.project.model.entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<List<Attendance>> getAttendancesByLesson_GroupId(Long id);

    Optional<List<Attendance>> getAttendancesByUserId(Long id);

    Optional<List<Attendance>> getAttendancesByLessonId(Long id);
}
