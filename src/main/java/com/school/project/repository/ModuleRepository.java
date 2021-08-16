package com.school.project.repository;

import com.school.project.model.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    Optional<Module> getModuleByName(String name);

    Optional<List<Module>> getModuleBySubjectsId(Long id);
}
