create table MODULE
(
    ID    bigint       not null auto_increment,
    HOURS integer      not null,
    NAME  varchar(255) not null,
    PRICE double precision,
    primary key (ID)
);

create table SUBJECT
(
    ID   bigint not null auto_increment,
    NAME varchar(255),
    primary key (ID)
);

create table MODULE_SUBJECT
(
    MODULE_ID  bigint not null,
    SUBJECT_ID bigint not null
);

alter table MODULE_SUBJECT
    add constraint MODULE_SUBJECT_SUBJECT_ID_SUBJECT_ID foreign key (SUBJECT_ID) references SUBJECT (ID);
alter table MODULE_SUBJECT
    add constraint MODULE_SUBJECT_MODULE_ID_MODULE_ID foreign key (MODULE_ID) references MODULE (ID);

INSERT INTO MODULE (ID, HOURS, NAME, PRICE)
VALUES (1, 40, 'BASIC_JAVA', 400.00),
       (2, 35, 'JAVA', 700.00),
       (3, 89, 'DEVOPS', 500.50),
       (4, 41, 'FRONTEND', 645.25);

INSERT INTO SUBJECT (ID, NAME)
VALUES (1, 'BACKEND'),
       (2, 'FRONTEND'),
       (3, 'QA');

INSERT INTO MODULE_SUBJECT (MODULE_ID, SUBJECT_ID)
VALUES (1, 3),
       (2, 1),
       (1, 2),
       (4, 2),
       (3, 2),
       (4, 1);

