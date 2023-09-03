--liquibase formatted sql

--changeset wolroys:1
create table if not exists Users(
    id bigserial primary key,
    username varchar(50) not null,
    email varchar(100) not null,
    password varchar(100) not null,
    unique (username),
    unique (email),
    created_at timestamp,
    modified_at timestamp
);

--changeset wolroys:2
create table if not exists Notes(
    id bigserial primary key,
    user_id int references Users(id) on delete cascade,
    title varchar(255) not null,
    content text,
    created_at timestamp,
    modified_at timestamp
);

--changeset wolroys:3
create table if not exists Tasks(
    id bigserial primary key,
    user_id int references Users(id) on delete cascade,
    title varchar(255) not null,
    description text,
    due_date date,
    created_at timestamp,
    modified_at timestamp
);