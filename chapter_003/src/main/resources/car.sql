create database car;

CREATE TABLE car_bodys (
id serial PRIMARY KEY,
name varchar(200)
);

CREATE TABLE engines (
id serial PRIMARY KEY,
name varchar(200)
);

CREATE TABLE transmissions (
id serial PRIMARY KEY,
name varchar(200)
);

CREATE TABLE cars (
id serial PRIMARY KEY,
name varchar(200),
car_body_id int REFERENCES car_bodys(id),
engine_id int REFERENCES engines(id),
transmission_id int REFERENCES transmissions(id)
);

INSERT INTO car_bodys(name) values ('Седан');
INSERT INTO car_bodys(name) values ('Хечбек');
INSERT INTO car_bodys(name) values ('Универсал');

INSERT INTO engines(name) values ('1.6L');
INSERT INTO engines(name) values ('3.0L');
INSERT INTO engines(name) values ('5.7L');

INSERT INTO transmissions(name) values ('ZF');
INSERT INTO transmissions(name) values ('GMC');
INSERT INTO transmissions(name) values ('JATCO');

INSERT INTO cars(name, car_body_id, engine_id, transmission_id) values ('Focus', 1, 1, 3);
INSERT INTO cars(name, car_body_id, engine_id, transmission_id) values ('Mustang', 2, 3, 2);


select c.name, cb.name as car_body, e.name as engine, t.name as transmission from cars as c
left join car_bodys as cb on c.car_body_id = cb.id
left join engines as e on c.engine_id = e.id
left join transmissions as t on c.transmission_id = t.id;

select cb.name from cars as c
right join car_bodys as cb on c.car_body_id = cb.id where c.name is null;

select e.name from cars as c
right join engines as e on c.engine_id = e.id where c.name is null;

select t.name from cars as c
right join transmissions as t on c.transmission_id = t.id where c.name is null;

