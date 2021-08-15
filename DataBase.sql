create database administration;

USE administration;

CREATE TABLE usuarios ( -- tabla para manejar a los usuario < : 
	nombre varchar(50) not null comment 'nombre del usuario',
    apellido varchar(60) not null comment 'apellido del usuario',
    nombre_usuario varchar(25) not null comment 'nombre usuario',
    contraseña varchar(50) 
);

truncate table usuarios;
SELECT * FROM usuarios; -- mostrar todo el contenido de la tabla < : 

CREATE TABLE adminSystem ( -- tabla que manejara el CRUD 
	nombre varchar(50) not null comment 'nombre del usuario',
    apellido varchar(60) not null comment 'apellido del usuario',
    telefono varchar(15) not null comment 'telefono del usuario',
    correo varchar(60) not null comment 'correo del usuario',
	nombre_usuario varchar(50) 
);

SELECT * FROM adminSystem;