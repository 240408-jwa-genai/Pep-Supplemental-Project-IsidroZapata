
-- needed for referential integrity enforcement
PRAGMA foreign_keys = 1;

create table users(
	id integer primary KEY autoincrement,
	username varchar(20) unique,
	password varchar(20)
);

create table planets(
	id integer primary KEY,
	name varchar(20) unique,
	ownerId int references users(id) ON DELETE CASCADE
);

create table moons(
	id integer primary key,
	name varchar(20) unique,
	myPlanetId int references planets(id) ON DELETE CASCADE
);
DROP TABLE users;
DROP TABLE planets;
DROP TABLE moons;
INSERT INTO users (username, password) VALUES ('ww', 'ww');
INSERT INTO users (username, password) VALUES ('ee', 'ee');

--DO NOT USE! INSERT INTO planets (name,ownerId) VALUES(1,'test planet', 1);

--DO NOT USE! INSERT INTO planets (name,ownerId) VALUES(1,'test planet2', 2);
INSERT INTO planets(id,name,ownerId) VALUES(1,'test planet 1',1);
INSERT INTO planets (id,name,ownerId) VALUES(3,'test planet3',3); -- NOT Ran yet
INSERT INTO moons(id,name,myPlanetId ) VALUES(3,'testmoon3',3);
SELECT name FROM planets WHERE name = 'test planet';
SELECT id FROM planets WHERE id = 1;

UPDATE planets SET id = 2 WHERE name = 'test planet2';
DELETE FROM planets WHERE id = 3;