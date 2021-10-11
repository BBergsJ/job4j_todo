create database todo;

create table if not exists item (
    id serial primary key,
    description varchar,
    created timestamp default current_timestamp,
    done boolean
);