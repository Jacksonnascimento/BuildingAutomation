create database automaçãoPredial;
 
 
create table sala ( id int AUTO_INCREMENT,
 numeroSala char (25) not null , horaLigar datetime not null,
 horaDesligar datetime not null , primary key (id));