package com.school.project.controller;

import com.school.project.dto.GroupDto;
import com.school.project.model.entities.Group;
import com.school.project.service.GroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class GroupController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private GroupService groupService;

    @PostMapping("/group")
    public ResponseEntity<GroupDto> createGroup(@RequestBody @Valid GroupDto groupDto) {
        return ResponseEntity.ok()
                .body(convertGroupToGroupDto(groupService.createUpdate(convertGroupDtoToGroup(groupDto))));
    }

    @PutMapping("/group/{groupId}/{userId}")
    public void addUserToGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        groupService.addUser(groupId, userId);
        ResponseEntity.ok().build();
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(convertGroupToGroupDto(groupService.getById(id)));
    }

    @GetMapping("/groups")
    public List<GroupDto> getAllGroups() {
        return groupService.getAll().stream()
                .map(s -> convertGroupToGroupDto(s))
                .collect(Collectors.toList());
    }

    private Group convertGroupDtoToGroup(GroupDto groupDto) {
        return modelMapper.map(groupDto, Group.class);
    }

    private GroupDto convertGroupToGroupDto(Group group) {
        return modelMapper.map(group, GroupDto.class);
    }

}
