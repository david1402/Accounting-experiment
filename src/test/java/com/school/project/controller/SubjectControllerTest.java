package com.school.project.controller;

import com.school.project.model.entities.Subject;
import com.school.project.service.SubjectService;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SubjectController.class)
@Import(ModelMapper.class)

public class SubjectControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SubjectService subjectService;

    private static String NEW_SUBJECT_JSON_STRING = "{\"name\":\"QA\"}";

    private static String NEW_SUBJECT_JSON_STRING_WITH_ID = "{\"id\":1,\"name\":\"QA\"}";

    @Test
    public void testCreateSubject() throws Exception {
        when(subjectService.create(any(Subject.class))).thenReturn(getSampleSubject());
        mvc.perform(post("/subjects")
                .content(NEW_SUBJECT_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(subjectService).create(new Subject("QA"));
    }

    @Test
    public void testGetSubjectByID() throws Exception {
        when(subjectService.getSubjectById(1L)).thenReturn(getSampleSubject());
        mvc.perform(get("/subject/" + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(NEW_SUBJECT_JSON_STRING_WITH_ID));
    }

    @Test
    public void testGetAllSubjects() throws Exception {
        when(subjectService.getAllSubjects()).thenReturn(getSampleSubjectList());
        mvc.perform(get("/subjects")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value("QA"))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[1].name").value("Frontend"));
    }

    @Test
    public void testUpdateSubject() throws Exception {

        mvc.perform(put("/subjects/" + 1L)
                .content(NEW_SUBJECT_JSON_STRING_WITH_ID)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print());
        verify(subjectService, Mockito.times(1)).update(getSampleSubjectToUpdate(), 1L);
    }

    public Subject getSampleSubject() {
        Subject subject = new Subject("QA");
        subject.setId(1L);
        return subject;
    }

    private List<Subject> getSampleSubjectList() {
        Subject sb = new Subject("QA");
        sb.setId(1L);
        Subject sb1 = new Subject("Frontend");
        sb1.setId(2L);
        List<Subject> subjects = Arrays.asList(sb, sb1);
        return subjects;
    }

    private Subject getSampleSubjectToUpdate() {
        return new Subject("QA");
    }

}
