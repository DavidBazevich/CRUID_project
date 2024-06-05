create database application;
use application;
create table user(
	id int primary key auto_increment,
    name char(45),
    surname char(45),
    login char(45) unique,
    password char(65)
);
create table notes(
	id int primary key auto_increment,
    note_name char(45),
    note_data mediumtext,
    login_user char(45),
    foreign key (login_user) references user(login) on delete cascade
);