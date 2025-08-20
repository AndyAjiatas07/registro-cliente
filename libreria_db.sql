drop database if exists libreria_2021496_db;
create database libreria_2021496_db;
use libreria_2021496_db;
 
create table Clientes(
	codigoCliente integer auto_increment,
    nombre varchar (64),
    apellido varchar (64),
    telefono varchar (16),
    correo varchar (128),
    genero enum ('masculino','femenino','no'),
    edad integer,
    constraint pk_cliente primary key (codigoCliente)
);
 
 
insert into Clientes (nombre, apellido, telefono, correo, genero, edad)
values
('Carlos', 'Ramírez', '5551-2345', 'carlos.ramirez@example.com', 'masculino', 28),
('Ana', 'González', '5551-6789', 'ana.gonzalez@example.com', 'femenino', 34),
('Luis', 'Mendoza', '5552-9876', 'luis.mendoza@example.com', 'masculino', 41);
 
select * from Clientes;