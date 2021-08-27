SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS departments (
id int PRIMARY KEY auto_increment,
name VARCHAR,
description VARCHAR,
numberofemployees INTEGER,
);

CREATE TABLE IF NOT EXISTS users (
id int PRIMARY KEY auto_increment,
position VARCHAR,
userdepartment VARCHAR,
);

CREATE TABLE IF NO EXISTS news (
id int PRIMARY KEY auto_increment,
post VARCHAR,
);