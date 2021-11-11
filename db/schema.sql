create database todo;

create table if not exists item (
    id serial primary key,
    description varchar,
    created timestamp default current_timestamp,
    done boolean
);

create table if not exists users (
    id serial primary key,
    name varchar,
    email varchar not null unique,
    password varchar not null
);

alter table item add column user_id int references users(id) on delete cascade;

create table if not exists category (
    id serial primary key,
    name varchar
);

insert into category(name) values
                                  ('Важное'),
                                  ('Срочное'),
                                  ('Дом'),
                                  ('Работа'),
                                  ('Хобби'),
                                  ('Досуг');