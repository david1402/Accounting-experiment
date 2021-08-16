package com.school.project.model.types;

public enum UserAccountType {

    ADMIN(1, "Admin"),
    TEACHER(2, "Teacher"),
    STUDENT(3, "Student");

    UserAccountType(Integer id, String name) {
    }
}
