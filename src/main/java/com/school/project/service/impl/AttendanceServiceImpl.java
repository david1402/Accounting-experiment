package com.school.project.service.impl;

import com.school.project.exception.AttendanceNotFoundExсeption;
import com.school.project.model.entities.Attendance;
import com.school.project.repository.AttendanceRepository;
import com.school.project.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Override
    public Attendance createAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public void update(Attendance attendance, Long id) {
        attendance.setId(id);
        attendanceRepository.saveAndFlush(attendance);
    }

    @Override
    public List<Attendance> filterByLesson(Long id) {
        Optional<List<Attendance>> attendancesByLessonId = attendanceRepository.getAttendancesByLessonId(id);
        validateResponseIsPresent(attendancesByLessonId, "filtred by Lesson ");
        return attendancesByLessonId.get();
    }

    @Override
    public List<Attendance> filterByUser(Long id) {
        Optional<List<Attendance>> attendancesByUserId = attendanceRepository.getAttendancesByUserId(id);
        validateResponseIsPresent(attendancesByUserId, " filtred by User");
        return attendancesByUserId.get();
    }

    @Override
    public List<Attendance> filterByGroup(Long id) {
        Optional<List<Attendance>> attendancesByGroupId = attendanceRepository.getAttendancesByLesson_GroupId(id);
        validateResponseIsPresent(attendancesByGroupId, "filtred by Group");
        return attendancesByGroupId.get();
    }

    @Override
    public Attendance getAttendanceById(long id) {
        Optional<Attendance> byId = attendanceRepository.findById(id);
        if (!byId.isPresent()) throw new AttendanceNotFoundExсeption("Atten with id " + id + " is not found");
        return byId.get();
    }

    public void validateResponseIsPresent(Optional<?> attendace, String field) {
        if (!attendace.isPresent())
            throw new AttendanceNotFoundExсeption("Attendance  " + field + " is not found");
    }
}
