package com.school.project.service;

import com.school.project.model.entities.Attendance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendanceService {
    Attendance createAttendance(Attendance attendance);

    void update (Attendance attendance, Long id);

    List<Attendance> filterByLesson (Long id);

    List<Attendance> filterByUser (Long id);

    List<Attendance> filterByGroup (Long id);

    Attendance getAttendanceById (long id);
}
