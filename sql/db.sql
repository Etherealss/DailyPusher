create table content
(
    id                           bigint       not null
        primary key,
    content_name                 varchar(128) not null,
    project_id                   bigint       null,
    briefing                     varchar(255) not null,
    contain_weather              tinyint(1)   not null,
    contain_motto                tinyint(1)   not null,
    enterprise_we_chat_hook_keys json         not null,
    creator                      bigint       not null,
    create_time                  datetime     not null,
    modify_time                  datetime     not null
)
    collate = utf8mb4_unicode_ci;

create table content_schedule
(
    id             bigint   not null,
    content_id     bigint   not null,
    schedule_type  tinyint  not null,
    schedule_param json     null,
    creator        bigint   not null,
    create_time    datetime not null,
    modify_time    datetime not null,
    constraint content_schedule_pk_content_id
        unique (content_id)
);

create table user
(
    id          bigint      not null
        primary key,
    username    varchar(32) not null,
    password    varchar(64) not null,
    creator     bigint      not null,
    create_time datetime    not null,
    modify_time datetime    not null
);

