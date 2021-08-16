package com.school.project.controller;


import com.school.project.model.entities.*;
import com.school.project.service.LessonService;
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
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LessonController.class)
@Import(ModelMapper.class)
public class LessonControllerTest {

    @Autowired
    private MockMvc mvc;

    static Date date;

    static Long time;

    private static String NEW_LESSON_JSON_STRING2 = "{\"thema\":\"Angular\",\"subjects\":[{\"id\":2},{\"id\":3}],\"createdDate\":1556928000000,\"group\":{\"id\":3},\"teacher\":{\"id\":3}}";

    private static String LESSON_JSON_STRING = "{\"id\":12,\"thema\":\"Angular\",\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}],\"createdDate\":1556928000000,\"group\":{\"id\":3,\"startDate\":1556928000000,\"module\":{\"id\":1,\"name\":\"Basic_Java\",\"hours\":70,\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}],\"price\":300.0},\"userList\":[{\"id\":1,\"firstName\":\"Sergey\",\"lastName\":\"Petrov\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"petrov@email.com\",\"phoneNumber\":\"12345678\"},{\"id\":2,\"firstName\":\"Iurii\",\"lastName\":\"Vasiliev\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"vasiliev@email.com\",\"phoneNumber\":\"12345678\"}]},\"teacher\":{\"id\":3,\"firstName\":\"Niko\",\"lastName\":\"Teacher\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"teacher@email.com\",\"phoneNumber\":\"12345678\"}}";

    private static String LESSON_LIST_JSON_STRING = "[{\"id\":12,\"thema\":\"JS\"," +
            "\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}],\"createdDate\":1556928000000,\"group\":{\"id\":3,\"startDate\":1556928000000," +
            "\"module\":{\"id\":1,\"name\":\"Basic_Java\",\"hours\":70,\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}],\"price\":300.0}," +
            "\"userList\":[{\"id\":1,\"firstName\":\"Sergey\",\"lastName\":\"Petrov\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"petrov@email.com\",\"phoneNumber\":\"12345678\"}," +
            "{\"id\":2,\"firstName\":\"Iurii\",\"lastName\":\"Vasiliev\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"vasiliev@email.com\",\"phoneNumber\":\"12345678\"}]}," +
            "\"teacher\":{\"id\":3,\"firstName\":\"Niko\",\"lastName\":\"Teacher\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"teacher@email.com\",\"phoneNumber\":\"12345678\"}}," +
            "{\"id\":11,\"thema\":\"Angular\",\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}],\"createdDate\":1556928000000,\"group\":{\"id\":3,\"startDate\":1556928000000," +
            "\"module\":{\"id\":1,\"name\":\"Basic_Java\",\"hours\":70,\"subjects\":[{\"id\":3,\"name\":\"QA\"},{\"id\":2,\"name\":\"FRONTEND\"}],\"price\":300.0}," +
            "\"userList\":[{\"id\":1,\"firstName\":\"Sergey\",\"lastName\":\"Petrov\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"petrov@email.com\",\"phoneNumber\":\"12345678\"}," +
            "{\"id\":2,\"firstName\":\"Iurii\",\"lastName\":\"Vasiliev\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"vasiliev@email.com\",\"phoneNumber\":\"12345678\"}]}," +
            "\"teacher\":{\"id\":3,\"firstName\":\"Niko\",\"lastName\":\"Teacher\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"teacher@email.com\",\"phoneNumber\":\"12345678\"}}]";

    @MockBean
    private LessonService lessonService;

    @BeforeClass
    public static void beforeTest() throws ParseException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = format.parse("2019-05-04T00:00:00.000");
        time = date.getTime();
    }

    @Test
    public void testGetLessonById() throws Exception {
        when(lessonService.getLesson(12L)).thenReturn(getTestLesson());

        mvc.perform(get("/lessons/" + 12)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(LESSON_JSON_STRING));
    }

    @Test
    public void testGetAllLessons() throws Exception {
        when(lessonService.getAllLessons()).thenReturn(getListLessonsForTest());
        mvc.perform(get("/lessons").contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().string(LESSON_LIST_JSON_STRING));
    }

    @Test
    public void testGetAllLessonsByGroup() throws Exception {
        when(lessonService.getAllLessonsByGroup(3L)).thenReturn(getListLessonsForTest());
        mvc.perform(get("/lessons/group/" + 3)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(LESSON_LIST_JSON_STRING));
    }

    @Test
    public void testGetAllLessonsByTeacher() throws Exception {
        when(lessonService.getAllLessonsByTeacher(2L)).thenReturn(getListLessonsForTest());
        mvc.perform(get("/lessons/teacher/" + 2)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(LESSON_LIST_JSON_STRING));
    }

    @Test
    public void testGetAllLessonsBySubject() throws Exception {
        when(lessonService.getAllLessonsBySubject(3L)).thenReturn(getListLessonsForTest());
        mvc.perform(get("/lessons/subject/" + 3)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(LESSON_LIST_JSON_STRING));
    }

    @Test
    public void testCreateLesson() throws Exception {
        when(lessonService.createLesson(any(Lesson.class))).thenReturn(getRealTestLesson());
        mvc.perform(post("/lessons")
                .content(NEW_LESSON_JSON_STRING2)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = format.parse("2019-05-04T00:00:00.000");
        verify(lessonService).createLesson(new Lesson("Angular",
                getListSubjectsForCreate(),
                new Group(null, null, null),
                new User(null, null, null, null, null)));
    }

    @Test
    public void testUpdateLesson() throws Exception {
        mvc.perform(put("/lessons/" + 12L)
                .content(LESSON_JSON_STRING)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        verify(lessonService, times(1)).updateLesson(getLessonToUpdate(), 12L);
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


    private Lesson getRealTestLesson() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");
        Lesson lesson = new Lesson(
                "Angular",
                getListSubjectsForCreate(),
                new Group(null, null, null),
                new User(null, null, null, null, null));
        lesson.setCreatedDate(date);
        return lesson;
    }


    private Lesson getLessonToUpdate() throws Exception {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");
        Lesson ls = new Lesson(
                "Angular",
                getListSubjectsForUpdate(),
                getGroupForTest(),
                getTeacherForTest());
        ls.setId(12L);
        return ls;
    }

    private List<Lesson> getListLessonsForTest() throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");
        List<Lesson> listToTest = new ArrayList<>();
        Lesson l1 = new Lesson(
                "JS",
                getListSubjects(),
                getGroupForTest(),
                getTeacherForTest());
        l1.setId(12L);
        l1.setCreatedDate(date);
        Lesson l2 = new Lesson(
                "Angular",
                getListSubjects(),
                getGroupForTest(),
                getTeacherForTest());
        l2.setId(11L);
        l2.setCreatedDate(date);
        Collections.addAll(listToTest, l1, l2);
        return listToTest;
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

    private List<Subject> getListSubjectsForCreate() {
        List<Subject> subjects = new ArrayList<>();
        Subject sb1 = new Subject(null);
        Subject sb2 = new Subject(null);
        Collections.addAll(subjects, sb1, sb2);
        return subjects;
    }

    private List<Subject> getListSubjectsForUpdate() {
        List<Subject> subjects = new ArrayList<>();
        Subject sb1 = new Subject("QA");
        Subject sb2 = new Subject("FRONTEND");
        Collections.addAll(subjects, sb1, sb2);
        return subjects;
    }


    private List<User> getListUsers() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse("2019-05-04");
        User us1 = new User("Sergey", "Petrov", date, "petrov@email.com", "12345678");
        User us2 = new User("Iurii", "Vasiliev", date, "vasiliev@email.com", "12345678");
        List<User> listUsers = new ArrayList<>();
        us1.setId(1L);
        us1.setCreatedDate(date);
        us1.setUpdatedDate(date);
        us2.setId(2L);
        us2.setCreatedDate(date);
        us2.setUpdatedDate(date);
        Collections.addAll(listUsers, us1, us2);
        return listUsers;
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

    private Module getModuleForTest() {
        Module module = new Module("Basic_Java", 70, getListSubjects(), 300.00);
        module.setId(1L);
        return module;
    }
}
