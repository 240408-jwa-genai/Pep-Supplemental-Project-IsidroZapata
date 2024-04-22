
-- needed for referential integrity enforcement
PRAGMA foreign_keys = 1;

create table users(
	id integer primary KEY autoincrement,
	username varchar(20) unique,
	password varchar(20)
);

create table planets(
	id serial primary key,
	name varchar(20),
	ownerId int references users(id)
);

create table moons(
	id serial primary key,
	name varchar(20),
	myPlanetId int references planets(id)
);
DROP TABLE users;
INSERT INTO users (username, password) VALUES ('test user', 'test password');

--DO NOT USE! INSERT INTO planets (name,ownerId) VALUES(1,'test planet', 1);

--DO NOT USE! INSERT INTO planets (name,ownerId) VALUES(1,'test planet2', 2);
INSERT INTO planets(id,name,ownerId) VALUES(1,'test planet 1',1);
INSERT INTO planets (id,name,ownerId) VALUES(3,'test planet3',3); -- NOT Ran yet
SELECT name FROM planets WHERE name = 'test planet';
SELECT id FROM planets WHERE id = 1;

UPDATE planets SET id = 2 WHERE name = 'test planet2';
DELETE FROM planets WHERE id = 3;