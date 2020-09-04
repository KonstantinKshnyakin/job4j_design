create database tracker;

CREATE TABLE role (
role_id serial PRIMARY KEY,
name varchar(2000)
);

CREATE TABLE rules (
rules_id serial PRIMARY KEY,
txt varchar(2000)
);

CREATE TABLE role_rules (
role_id int REFERENCES role (role_id),
rules_id int REFERENCES rules (rules_id),
CONSTRAINT role_rules_pkey PRIMARY KEY (role_id, rules_id)
);

CREATE TABLE users (
id serial PRIMARY KEY,
name varchar(2000),
role_id int REFERENCES role (role_id)
);

CREATE TABLE state (
state_id serial PRIMARY KEY,
txt varchar(2000)
);

CREATE TABLE category (
category_id serial PRIMARY KEY,
txt varchar(2000)
);

CREATE TABLE items (
id serial PRIMARY KEY,
txt varchar(2000),
state_id int REFERENCES state (state_id),
category_id int REFERENCES category (category_id),
user_id int REFERENCES users (id)
);

CREATE TABLE comments (
comment_id serial PRIMARY KEY,
txt varchar(2000),
item_id int REFERENCES items (id)
);

CREATE TABLE attaches (
attach_id serial PRIMARY KEY,
data bytea,
item_id int REFERENCES items (id)
);

insert into rules (txt) values ('read');
insert into rules (txt) values ('create');
insert into rules (txt) values ('update');
insert into rules (txt) values ('delete');

insert into role (name) values ('user');
insert into role (name) values ('admin');

insert into role_rules (role_id, rules_id) values (1, 1);
insert into role_rules (role_id, rules_id) values (2, 1);
insert into role_rules (role_id, rules_id) values (2, 2);
insert into role_rules (role_id, rules_id) values (2, 3);
insert into role_rules (role_id, rules_id) values (2, 4);

insert into users (name, role_id) values ('Sasha', 1);
insert into users (name, role_id) values ('Kostya', 1);
insert into users (name, role_id) values ('Dima', 2);

insert into state (txt) values ('Готова');
insert into state (txt) values ('В процессе');

insert into category (txt) values ('Новая');
insert into category (txt) values ('Отложена');

insert into items (txt, state_id, category_id, user_id) values ('Переделать', 2, 1, 1);
insert into items (txt, state_id, category_id, user_id) values ('Позвонить', 1, 2, 2);

insert into comments (txt, item_id) values ('Срочно', 1);
insert into comments (txt, item_id) values ('в офис', 2);

insert into attaches (data, item_id) values ('dsfaet546gsyhew', 1);
insert into attaches (data, item_id) values ('8sdf46a5y4het6h5', 2);






















