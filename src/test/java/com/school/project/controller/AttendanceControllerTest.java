package com.school.project.controller;

import com.school.project.model.entities.*;
import com.school.project.service.AttendanceService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AttendanceController.class)
@Import(ModelMapper.class)
public class AttendanceControllerTest {

    @Autowired
    private MockMvc mvc;

    static Date date;

    @MockBean
    private AttendanceService attendanceService;

    private static String NEW_ATTENDACE_JSON_STRING = "{\"lesson\":{\"id\":4,\"thema\":\"Angular\"," +
            "\"subjects\":[{\"name\":\"QA\"},{\"name\":\"FRONTEND\"}]," +
            "\"group\":{\"startDate\":1556928000000,\"module\":{\"name\":\"Basic_Java\",\"hours\":70,\"subjects\":[{\"name\":\"QA\"},{\"name\":\"FRONTEND\"}],\"price\":300.0}," +
            "\"userList\":[{\"firstName\":\"Sergey\",\"lastName\":\"Petrov\",\"birthDate\":\"2019-05-04\",\"email\":\"petrov@email.com\",\"phoneNumber\":\"12345678\"}," +
            "{\"firstName\":\"Friedrich\",\"lastName\":\"Mauer\",\"birthDate\":\"2019-05-04\",\"email\":\"mauer@email.com\",\"phoneNumber\":\"12345678\"}]}," +
            "\"teacher\":{\"firstName\":\"Niko\",\"lastName\":\"Teacher\",\"birthDate\":\"2019-05-04\",\"email\":\"teacher@email.com\",\"phoneNumber\":\"12345678\"}}," +
            "\"user\":{\"firstName\":\"Sergey\",\"lastName\":\"Petrov\",\"birthDate\":\"2019-05-04\",\"email\":\"petrov@email.com\",\"phoneNumber\":\"12345678\"}," +
            "\"isPresent\":true}";
    private static String NEW_ATTENDACE_JSON_STRING_WITH_ID = "{\"id\":1,\"lesson\":{\"id\":4,\"thema\":\"Angular\"," +
            "\"subjects\":[{\"name\":\"QA\"},{\"name\":\"FRONTEND\"}]," +
            "\"group\":{\"startDate\":1556928000000,\"module\":{\"name\":\"Basic_Java\",\"hours\":70,\"subjects\":[{\"name\":\"QA\"},{\"name\":\"FRONTEND\"}],\"price\":300.0}," +
            "\"userList\":[{\"firstName\":\"Sergey\",\"lastName\":\"Petrov\",\"birthDate\":\"2019-05-04\",\"email\":\"petrov@email.com\",\"phoneNumber\":\"12345678\"}," +
            "{\"firstName\":\"Friedrich\",\"lastName\":\"Mauer\",\"birthDate\":\"2019-05-04\",\"email\":\"mauer@email.com\",\"phoneNumber\":\"12345678\"}]}," +
            "\"teacher\":{\"firstName\":\"Niko\",\"lastName\":\"Teacher\",\"birthDate\":\"2019-05-04\",\"email\":\"teacher@email.com\",\"phoneNumber\":\"12345678\"}}," +
            "\"user\":{\"firstName\":\"Sergey\",\"lastName\":\"Petrov\",\"birthDate\":\"2019-05-04\",\"email\":\"petrov@email.com\",\"phoneNumber\":\"12345678\"}," +
            "\"isPresent\":true}";

    private static String ATTENDANCE_LIST_JSON_STRING = "[{\"id\":1,\"lesson\":{\"id\":12,\"thema\":\"Angular\"," +
            "\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}],\"createdDate\":1556928000000," +
            "\"group\":{\"id\":3,\"startDate\":1556928000000,\"module\":{\"id\":1,\"name\":\"Basic_Java\",\"hours\":70,\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}],\"price\":300.0}," +
            "\"userList\":[{\"id\":1,\"firstName\":\"Sergey\",\"lastName\":\"Petrov\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"petrov@email.com\",\"phoneNumber\":\"12345678\"}," +
            "{\"id\":2,\"firstName\":\"Friedrich\",\"lastName\":\"Mauer\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"mauer@email.com\",\"phoneNumber\":\"12345678\"}]}," +
            "\"teacher\":{\"id\":3,\"firstName\":\"Niko\",\"lastName\":\"Teacher\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"teacher@email.com\",\"phoneNumber\":\"12345678\"}}," +
            "\"user\":{\"id\":1,\"firstName\":\"Sergey\",\"lastName\":\"Petrov\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"petrov@email.com\",\"phoneNumber\":\"12345678\"},\"isPresent\":true}," +
            "{\"id\":2,\"lesson\":{\"id\":12,\"thema\":\"Angular\"," +
            "\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}],\"createdDate\":1556928000000," +
            "\"group\":{\"id\":3,\"startDate\":1556928000000,\"module\":{\"id\":1,\"name\":\"Basic_Java\",\"hours\":70,\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}],\"price\":300.0}," +
            "\"userList\":[{\"id\":1,\"firstName\":\"Sergey\",\"lastName\":\"Petrov\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"petrov@email.com\",\"phoneNumber\":\"12345678\"}," +
            "{\"id\":2,\"firstName\":\"Friedrich\",\"lastName\":\"Mauer\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"mauer@email.com\",\"phoneNumber\":\"12345678\"}]}," +
            "\"teacher\":{\"id\":3,\"firstName\":\"Niko\",\"lastName\":\"Teacher\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"teacher@email.com\",\"phoneNumber\":\"12345678\"}}," +
            "\"user\":{\"id\":2,\"firstName\":\"Friedrich\",\"lastName\":\"Mauer\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"mauer@email.com\",\"phoneNumber\":\"12345678\"},\"isPresent\":false}]";

    private static String ATTENDANCE_LIST_FOR_FILTER_BY_USER_JSON_STRING = "[{\"id\":1,\"lesson\":{\"id\":12,\"thema\":\"Angular\"," +
            "\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}],\"createdDate\":1556928000000," +
            "\"group\":{\"id\":3,\"startDate\":1556928000000,\"module\":{\"id\":1,\"name\":\"Basic_Java\",\"hours\":70,\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}],\"price\":300.0}," +
            "\"userList\":[{\"id\":1,\"firstName\":\"Sergey\",\"lastName\":\"Petrov\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"petrov@email.com\",\"phoneNumber\":\"12345678\"}," +
            "{\"id\":2,\"firstName\":\"Friedrich\",\"lastName\":\"Mauer\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"mauer@email.com\",\"phoneNumber\":\"12345678\"}]}," +
            "\"teacher\":{\"id\":3,\"firstName\":\"Niko\",\"lastName\":\"Teacher\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"teacher@email.com\",\"phoneNumber\":\"12345678\"}}," +
            "\"user\":{\"id\":2,\"firstName\":\"Friedrich\",\"lastName\":\"Mauer\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"mauer@email.com\",\"phoneNumber\":\"12345678\"},\"isPresent\":true}," +
            "{\"id\":2,\"lesson\":{\"id\":12,\"thema\":\"Angular\"," +
            "\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}],\"createdDate\":1556928000000," +
            "\"group\":{\"id\":3,\"startDate\":1556928000000,\"module\":{\"id\":1,\"name\":\"Basic_Java\",\"hours\":70,\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}],\"price\":300.0}," +
            "\"userList\":[{\"id\":1,\"firstName\":\"Sergey\",\"lastName\":\"Petrov\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"petrov@email.com\",\"phoneNumber\":\"12345678\"}," +
            "{\"id\":2,\"firstName\":\"Friedrich\",\"lastName\":\"Mauer\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"mauer@email.com\",\"phoneNumber\":\"12345678\"}]}," +
            "\"teacher\":{\"id\":3,\"firstName\":\"Niko\",\"lastName\":\"Teacher\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"teacher@email.com\",\"phoneNumber\":\"12345678\"}}," +
            "\"user\":{\"id\":2,\"firstName\":\"Friedrich\",\"lastName\":\"Mauer\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"mauer@email.com\",\"phoneNumber\":\"12345678\"},\"isPresent\":false}]";

    @BeforeClass
    public static void beforeTest() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Test
    public void testCreateAttendance() throws Exception {
        when(attendanceService.createAttendance(any(Attendance.class))).thenReturn(getTestAttendance());
        mvc.perform(post("/attendances")
                .content(NEW_ATTENDACE_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
        verify(attendanceService).createAttendance(new Attendance(getTestLesson(), getTestUser("Sergey", "Petrov", 1L), true));
    }

    @Test
    public void testUpdate() throws Exception {
        mvc.perform(put("/attendances/" + 1L)
                .content(NEW_ATTENDACE_JSON_STRING_WITH_ID)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print());
        verify(attendanceService, times(1)).update(getTestAttendance(), 1L);
    }

    @Test
    public void testFilterByGroup() throws Exception {
        when(attendanceService.filterByGroup(3L)).thenReturn(getListAttendances());
        mvc.perform(get("/attendances/group/" + 3L)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(ATTENDANCE_LIST_JSON_STRING));
    }

    @Test
    public void testFilterByLesson() throws Exception {
        when(attendanceService.filterByLesson(12L)).thenReturn(getListAttendances());
        mvc.perform(get("/attendances/lesson/" + 12L)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(ATTENDANCE_LIST_JSON_STRING));
    }

    @Test
    public void testFilterByUser() throws Exception {
        when(attendanceService.filterByUser(3L)).thenReturn(getListAttendancesForFilterByUser());
        mvc.perform(get("/attendances/user/" + 3L)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(ATTENDANCE_LIST_FOR_FILTER_BY_USER_JSON_STRING));
    }

    private Attendance getTestAttendance() throws Exception {
        Attendance attendance = new Attendance(getTestLesson(), getTestUser("Sergey", "Petrov", 1L), true);
        attendance.setId(1L);
        return attendance;
    }

    private List<Attendance> getListAttendances() throws Exception {
        Attendance attendance = new Attendance(getTestLesson(), getTestUser("Sergey", "Petrov", 1L), true);
        Attendance attendance1 = new Attendance(getTestLesson(), getTestUser("Friedrich", "Mauer", 2L), false);
        attendance.setId(1L);
        attendance1.setId(2L);
        List<Attendance> attendances = new ArrayList<>();
        Collections.addAll(attendances, attendance, attendance1);
        return attendances;
    }

    private List<Attendance> getListAttendancesForFilterByUser() throws Exception {
        Attendance attendance = new Attendance(getTestLesson(), getTestUser("Friedrich", "Mauer", 2L), true);
        Attendance attendance1 = new Attendance(getTestLesson(), getTestUser("Friedrich", "Mauer", 2L), false);
        attendance.setId(1L);
        attendance1.setId(2L);
        List<Attendance> attendances = new ArrayList<>();
        Collections.addAll(attendances, attendance, attendance1);
        return attendances;
    }

    private User getTestUser(String firstName, String lastName, Long id) {
        User us = new User(firstName, lastName, date, lastName.toLowerCase() + "@email.com", "12345678");
        us.setId(id);
        return us;
    }

    private Lesson getTestLesson() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");

        Lesson lesson = new Lesson(
                "Angular",
                getListSubjects(),
                getGroupForTest(),
                getTeacherForTest());
        lesson.setId(12L);
        lesson.setCreatedDate(date);
        lesson.setUpdatedDate(date);
        return lesson;
    }

    private User getTeacherForTest() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");
        User teacher = new User("Niko", "Teacher", date, "teacher@email.com", "12345678");
        teacher.setId(3L);
        return teacher;
    }

    private Group getGroupForTest() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");
        Group group = new Group(getModuleForTest(), date, getListUsers());
        group.setId(3L);
        return group;
    }

    private List<User> getListUsers() throws ParseException {
        List<User> listUsers = new ArrayList<>();
        Collections.addAll(listUsers, getTestUser("Sergey", "Petrov", 1L),
                getTestUser("Friedrich", "Mauer", 2L));
        return listUsers;
    }

    private Module getModuleForTest() {
        Module module = new Module("Basic_Java", 70, getListSubjects(), 300.00);
        module.setId(1L);
        return module;
    }

    private List<Subject> getListSubjects() {
        List<Subject> subjects = new ArrayList<>();
        Subject sb1 = new Subject("QA");
        sb1.setId(3L);
        Subject sb2 = new Subject("FRONTEND");
        sb2.setId(2L);
        Collections.addAll(subjects, sb1, sb2);
        return subjects;
    }
}
