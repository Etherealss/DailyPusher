use daily_pusher;

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


drop table project;

create table if not exists project
(
    id                           bigint       not null
    primary key,
    project_name       varchar(255) not null,
    project_department varchar(255) not null,
    project_leader     varchar(255) not null,
    phone     varchar(255) not null,
    start_date         datetime         not null,
    end_date           datetime         not null,
    status             int          not null,
    task_count         int          not null,
    solve_task_count   int          not null,
    demand_count       int          not null,
    solve_demand_count int          not null,
    bug_count          int          not null,
    solve_bug_count    int          not null,
    creator     bigint      not null,
    create_time datetime    not null,
    modify_time datetime    not null,
    constraint project_name
    unique (project_name)
    )
    collate = utf8mb4_unicode_ci;