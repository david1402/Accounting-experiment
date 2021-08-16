package com.school.project.service;

import com.school.project.model.entities.Group;

import java.util.List;

public interface GroupService {

    Group createUpdate(Group group);

    Group getById(Long id);

    List<Group> getAll();

    Group addUser(Long groupId, Long userId);

}
