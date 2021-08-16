create table USER
(
    ID           bigint      not null auto_increment,
    FIRST_NAME   varchar(50) not null,
    LAST_NAME    varchar(50) not null,
    BIRTH_DATE   datetime    not null,
    EMAIL        varchar(50) not null,
    PHONE_NUMBER varchar(50) not null,
    CREATED_DATE datetime    not null,
    UPDATED_DATE datetime    not null,
    primary key (ID)
);

create table USER_ACCOUNT
(
    ID                  bigint      not null auto_increment,
    USER_ID             bigint      not null ,
    REGISTRATION_DATE   datetime    not null,
    ACCOUNT_ROLE        varchar(50),
    primary key (ID)
);

alter table USER
    add constraint ID unique (ID);

alter table USER_ACCOUNT
    add constraint ID unique (ID);
alter table  USER_ACCOUNT
    add constraint USER_ACCOUNT_USER_ID_USER_ID
        foreign key (USER_ID) references USER(ID) ON DELETE CASCADE;
