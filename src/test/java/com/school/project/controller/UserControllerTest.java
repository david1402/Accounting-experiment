package com.school.project.controller;


import com.school.project.model.entities.User;
import com.school.project.service.UserService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * Unit test with dependency mocked.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@Import(ModelMapper.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    static Date date;

    @BeforeClass
    public static void beforeTest() throws ParseException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        date = format.parse("2019-05-04T00:00:00");
    }

    @Test
    public void testCreateNewUser() throws Exception {
        when(userService.create(any(User.class))).thenReturn(getSampleUserToCreate());
        mvc.perform(post("/users")
                .content(NEW_USER_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        //dates are a bit tricky, lets omit them for now
        verify(userService).create(new User("sergey", "lukichev", date, "sergey@example.com", "+49333300"));
    }

    @Test
    public void testUpdateUser() throws Exception {

        mvc.perform(put("/users/" + 1)
                .content(NEW_USER_FOR_UPDATE_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        verify(userService, Mockito.times(1)).update(getSampleUserToUpdate(), 1L);
    }

    @Test
    public void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(getSampleUser());

        mvc.perform(get("/users/" + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())//good for simple debugging
                .andExpect(jsonPath("$.firstName").value("sergey"))
                .andExpect(jsonPath("$.lastName").value("lukichev"));

        //check for all fields returned by the call
    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(getSampleUserList());

        mvc.perform(get("/users")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].firstName").value("sergey"))
                .andExpect(jsonPath("$.[0].lastName").value("lukichev"))
                .andExpect(jsonPath("$.[0].email").value("sergey@example.com"))
                .andExpect(jsonPath("$.[0].phoneNumber").value("+49333300"))
                .andExpect(jsonPath("$.[1].firstName").value("ivan"))
                .andExpect(jsonPath("$.[1].lastName").value("pupkin"))
                .andExpect(jsonPath("$.[1].email").value("ivan@example.com"))
                .andExpect(jsonPath("$.[1].phoneNumber").value("+380501413552"));
        //DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        //Date date = format.parse("2019-05-04T00:00:00");
        //dates are a bit tricky, lets omit them for now
        verify(userService, Mockito.times(1)).getAllUsers();
    }

    private User getSampleUser() {
        return new User("sergey", "lukichev", date, "sergey@example.com", "+49333300");
    }

    private User getSampleUserToUpdate() {
        User user = new User("Ivan", "Ivanov", date, "ivan_@mail.ru", "17612345678");
        user.setId(1L);
        return user;
    }

    private User getSampleUserToCreate() throws ParseException {
        return new User("sergey", "lukichev",date, "sergey@example.com", "+49333300");
    }

    private static String NEW_USER_FOR_UPDATE_JSON_STRING = "{\"id\":1,\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\",\"birthDate\":\"2019-05-04T00:00:00.000+0000\",\"email\":\"ivan_@mail.ru\",\"phoneNumber\":\"17612345678\"}";

    private List<User> getSampleUserList() {
        List<User> users = Arrays.asList(new User("sergey", "lukichev", new Date(), "sergey@example.com", "+49333300"),
                new User("ivan", "pupkin", new Date(), "ivan@example.com", "+380501413552"));
        return users;
    }

    private static String NEW_USER_JSON_STRING = "{\"firstName\":\"sergey\",\"lastName\":\"lukichev\",\"birthDate\":1556928000000,\"email\":\"sergey@example.com\",\"phoneNumber\":\"+49333300\"}";

}
