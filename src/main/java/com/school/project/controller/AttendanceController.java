package com.school.project.controller;

import com.school.project.dto.AttendanceDto;
import com.school.project.model.entities.Attendance;
import com.school.project.service.AttendanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/attendances")
    public ResponseEntity<AttendanceDto> create(@RequestBody @Valid AttendanceDto attendanceDto) {
        return ResponseEntity.ok()
                .body(convertAttendanceToAttendanceDto(attendanceService
                        .createAttendance(convertAttendanceDtoToAttendance(attendanceDto))));
    }

    @PutMapping("/attendances/{id}")
    public void update(@RequestBody @Valid AttendanceDto attendanceDto, @PathVariable Long id) {
        attendanceService.update(convertAttendanceDtoToAttendance(attendanceDto), id);
        ResponseEntity.ok().build();
    }

    @GetMapping("/attendances/{id}")
    public ResponseEntity<AttendanceDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(convertAttendanceToAttendanceDto(attendanceService.getAttendanceById(id)));
    }

    @GetMapping("/attendances/group/{id}")
    public ResponseEntity<List<AttendanceDto>> filterByGroup(@PathVariable Long id) {
        return ResponseEntity.ok().body(attendanceService.filterByGroup(id).stream()
                .map(s -> convertAttendanceToAttendanceDto(s)).collect(Collectors.toList()));
    }

    @GetMapping("/attendances/lesson/{id}")
    public ResponseEntity<List<AttendanceDto>> filterByLesson(@PathVariable Long id) {
        return ResponseEntity.ok().body(attendanceService.filterByLesson(id).stream()
                .map(s -> convertAttendanceToAttendanceDto(s)).collect(Collectors.toList()));
    }

    @GetMapping("/attendances/user/{id}")
    public ResponseEntity<List<AttendanceDto>> filterByUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(attendanceService.filterByUser(id).stream()
                .map(s -> convertAttendanceToAttendanceDto(s)).collect(Collectors.toList()));
    }

    private Attendance convertAttendanceDtoToAttendance(AttendanceDto attendanceDto) {
        return modelMapper.map(attendanceDto, Attendance.class);
    }

    private AttendanceDto convertAttendanceToAttendanceDto(Attendance attendance) {
        return modelMapper.map(attendance, AttendanceDto.class);
    }
}
