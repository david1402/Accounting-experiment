package com.school.project.service.impl;

import com.school.project.exception.GroupNotFoundException;
import com.school.project.model.entities.Group;
import com.school.project.model.entities.User;
import com.school.project.repository.GroupRepository;
import com.school.project.service.GroupService;
import com.school.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserService userService;

    @Override
    public Group createUpdate(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group getById(Long id) {
        Optional<Group> byId = groupRepository.findById(id);
        if (!byId.isPresent()) {
            throw new GroupNotFoundException("Group with id " + id + " was not found");
        }
        return byId.get();
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group addUser(Long groupId, Long userId) {
        User userById = userService.getUserById(userId);
        Group group = getById(groupId);
        List<User> users = group.getUserList();
        users.add(userById);
        return groupRepository.save(group);
    }

}
