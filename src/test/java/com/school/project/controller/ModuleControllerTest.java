package com.school.project.controller;

import com.school.project.model.entities.Module;
import com.school.project.model.entities.Subject;
import com.school.project.service.ModuleService;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ModuleController.class)
@Import(ModelMapper.class)
public class ModuleControllerTest {

    @Autowired
    private MockMvc mvc;


    @MockBean
    private ModuleService moduleService;

    private static String NEW_MODULE_JSON_STRING = "{\"name\":\"JAVA-3\",\"hours\":5,\"subjects\":[{\"id\":1,\"name\":\"BACKEND\"}],\"price\":950.0}";

    private static String NEW_MODULE_WITH_ID_JSON_STRING = "{\"id\":1,\"name\":\"JAVA-3\",\"hours\":5,\"subjects\":[{\"id\":1,\"name\":\"BACKEND\"}],\"price\":950.0}";

    private static String MODULE_LIST_JSON_STRING = "[{\"id\":1,\"name\":\"JAVA-3\",\"hours\":5,\"subjects\":[{\"id\":1,\"name\":\"BACKEND\"}],\"price\":950.0}," +
            "{\"id\":2,\"name\":\"DEVOPS\",\"hours\":40,\"subjects\":[{\"id\":1,\"name\":\"BACKEND\"},{\"id\":2,\"name\":\"QA\"}],\"price\":850.0}]";

    @Test
    public void testCreateModule() throws Exception {
        when(moduleService.createModule(any(Module.class))).thenReturn(getTestModule());
        mvc.perform(post("/modules")
                .content(NEW_MODULE_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
        verify(moduleService).createModule(new Module("JAVA-3", new Integer(5), getSubjectListForTest(), 950.0));
    }

    @Test

    public void testUpdateModule() throws Exception {
        mvc.perform(put("/modules/" + 1L)
                .content(NEW_MODULE_WITH_ID_JSON_STRING)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print());
        verify(moduleService, times(1)).updateModule(getTestModule(), 1L);
    }

    @Test
    public void testGetModuleById() throws Exception {
        when(moduleService.getModuleById(1L)).thenReturn(getTestModule());
        mvc.perform(get("/modules/" + 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(NEW_MODULE_WITH_ID_JSON_STRING));
    }

    @Test
    public void testGetModuleByName() throws Exception {
        when(moduleService.getModuleByName("JAVA-3")).thenReturn(getTestModule());
        mvc.perform(get("/modules/names/" + "JAVA-3")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(NEW_MODULE_WITH_ID_JSON_STRING));
    }

    @Test
    public void testGetModuleBySubjectId() throws Exception {
        when(moduleService.getModuleBySubject(1L)).thenReturn(getTestListModules());
        mvc.perform(get("/modules/subject/" + 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(MODULE_LIST_JSON_STRING));
    }

    private List<Module> getTestListModules() {
        Module module = getTestModule();

        Module module1 = new Module("DEVOPS", new Integer(40), getSubjectListForTestGetModuleBySybject(), 850.0);
        module1.setId(2L);
        List<Module> modules = new ArrayList<>();
        Collections.addAll(modules, module, module1);
        return modules;

    }

    private Module getTestModule() {
        Module module = new Module("JAVA-3", new Integer(5), getSubjectListForTest(), 950.0);
        module.setId(1L);
        return module;
    }

    private List<Subject> getSubjectListForTest() {
        Subject subject = getSubjectForTest(1l, "BACKEND");
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        return subjects;
    }

    private List<Subject> getSubjectListForTestGetModuleBySybject() {
        Subject subject = getSubjectForTest(1L, "BACKEND");
        Subject subject1 = getSubjectForTest(2L, "QA");
        List<Subject> subjects = new ArrayList<>();
        Collections.addAll(subjects, subject, subject1);
        return subjects;
    }

    private Subject getSubjectForTest(Long id, String name) {
        Subject subject = new Subject(name);
        subject.setId(id);
        return subject;
    }
}
