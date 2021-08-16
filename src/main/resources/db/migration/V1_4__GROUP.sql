create table `GROUP`
(
    ID         bigint   not null auto_increment,
    START_DATE datetime not null,
    MODULE_ID  bigint   not null,
    primary key (ID)
);

create table GROUP_USER
(
    USER_ID  bigint,
    GROUP_ID bigint not null
);

alter table GROUP_USER
    add constraint GROUP_USER_USER_ID_USER_ID foreign key (USER_ID) references USER (ID);
alter table GROUP_USER
    add constraint GROUP_USER_GROUP_ID_GROUP_ID foreign key (GROUP_ID) references `GROUP` (ID);
alter table `GROUP`
    add constraint GROUP_MODULE_ID_MODULE_ID foreign key (MODULE_ID) references MODULE (ID);

INSERT INTO `GROUP` (ID, START_DATE, MODULE_ID)
VALUES (1, '2019-01-10', 2),
       (2, '2018-09-7', 1),
       (3, '2018-05-3', 3),
       (4, '2018-01-9', 2);
INSERT INTO GROUP_USER (USER_ID, GROUP_ID)
VALUES (1, 2),
       (2, 4),
       (3, 1),
       (4, 2);