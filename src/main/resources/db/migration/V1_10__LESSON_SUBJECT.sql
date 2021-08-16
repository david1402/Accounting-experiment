alter table LESSON
    drop FOREIGN KEY LESSON_SUBJECT_ID_SUBJECT_ID;
alter table LESSON
    drop COLUMN SUBJECT_ID;


create table LESSON_SUBJECT
(
    LESSON_ID  bigint not null,
    SUBJECT_ID bigint not null
);

alter table LESSON_SUBJECT
    add constraint LESSON__SUBJECT_SUBJECT_ID_SUBJECT_ID foreign key (SUBJECT_ID) references SUBJECT (ID);
alter table LESSON_SUBJECT
    add constraint LESSON_SUBJECT_LESSON_ID_LESSON_ID foreign key (LESSON_ID) references LESSON (ID);

INSERT INTO LESSON_SUBJECT (LESSON_ID, SUBJECT_ID)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 2),
       (3, 3),
       (4, 3),
       (1, 2),
       (2, 2);
