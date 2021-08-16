package com.school.project.service.impl;

import com.school.project.exception.ModuleNotFoundeException;
import com.school.project.model.entities.Module;
import com.school.project.repository.ModuleRepository;
import com.school.project.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    @Override
    public Module createModule(Module module) {
        return moduleRepository.save(module);
    }

    @Override
    public void updateModule(Module moduleToUpdate, Long id) {
        moduleToUpdate.setId(id);
        moduleRepository.saveAndFlush(moduleToUpdate);
    }

    @Override
    public Module getModuleById(Long id) {
        Optional<Module> byId = moduleRepository.findById(id);
        validateResponseIsPresent(byId, "id :");
        return byId.get();
    }

    @Override
    public Module getModuleByName(String name) {
        Optional<Module> moduleByName = moduleRepository.getModuleByName(name);
        validateResponseIsPresent(moduleByName, " name :");
        return moduleByName.get();
    }

    @Override
    public List<Module> getModuleBySubject(Long id) {
        Optional<List<Module>> moduleBySubjects = moduleRepository.getModuleBySubjectsId(id);
        validateResponseIsPresent(moduleBySubjects,"Subject id :");
        return moduleBySubjects.get();
    }

    public void validateResponseIsPresent(Optional<?> module, String field) {
        if (!module.isPresent())
            throw new ModuleNotFoundeException("Module with " + field + " is not found");
    }
}
