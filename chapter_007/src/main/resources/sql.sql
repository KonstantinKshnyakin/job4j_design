create table country
(
    id           serial primary key,
    country_name varchar(100) unique
);

create table member
(
    id         serial primary key,
    first_name varchar(100),
    last_name  varchar(100),
    rating     integer,
    country_id integer references country (id)
);

truncate member, country
