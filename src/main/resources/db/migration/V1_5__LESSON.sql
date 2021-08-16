create table LESSON
(
    ID          bigint       not null auto_increment,
    LESSON_DATE datetime     not null,
    THEMA       varchar(255) not null,
    GROUP_ID    bigint       not null,
    SUBJECT_ID  bigint       not null,
    TEACHER_ID  bigint       not null,
    primary key (ID)
);
create table ATTENDANCE
(
    ID         bigint not null auto_increment,
    IS_PRESENT bit,
    LESSON_ID  bigint not null,
    USER_ID    bigint not null,
    primary key (ID)
);
alter table LESSON
    add constraint LESSON_GROUP_ID_GROUP_ID foreign key (GROUP_ID) references `GROUP` (ID);
alter table LESSON
    add constraint LESSON_SUBJECT_ID_SUBJECT_ID foreign key (SUBJECT_ID) references SUBJECT (ID);
alter table LESSON
    add constraint LESSON_TEACHER_ID_USER_ID foreign key (TEACHER_ID) references USER (ID);

alter table ATTENDANCE
    add constraint ATTENDANCE_LESSON_ID_LESSON_ID foreign key (LESSON_ID) references LESSON (ID);
alter table ATTENDANCE
    add constraint ATENDANCE_USER_ID_USER_ID foreign key (USER_ID) references USER (ID);



INSERT INTO LESSON (ID, LESSON_DATE, THEMA, GROUP_ID, SUBJECT_ID, TEACHER_ID)
VALUES (1, '2018-03-14', 'STRING_API', 1, 1, 2),
       (2, '2018-02-14', 'COLLECTIONS', 2, 1, 2),
       (3, '2018-10-02', 'JUNIT', 2, 3, 2),
       (4, '2018-08-09', 'SPRING_BOOT', 1, 2, 2);

INSERT INTO ATTENDANCE (ID, IS_PRESENT, LESSON_ID, USER_ID)
VALUES (1, 0, 4, 1),
       (2, 1, 4, 2),
       (4, 0, 1, 3),
       (3, 0, 2, 4);