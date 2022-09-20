create database db_examen;

use db_examen;

create table Estudiantes(
id_estudiante bigint auto_increment primary key,
carnet nchar(40),
nombres varchar(60),
apellidos varchar(60),
direccion varchar(100),
telefono int,
genero bit,
email varchar(70),
fecha_nacimiento date
);

select * from estudiantes;