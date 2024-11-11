create database puntoVenta;
use puntoVenta;
create table categoria(
	nombre varchar(50),
    descripcion text,
    idcategoria int not null primary key auto_increment
);

create table producto(
	nombre varchar(50),
    codigo varchar(13),
    idcategoria int not null,
    foreign key fkcategoria(idcategoria)
    references categoria(idcategoria),
    cantidadPorUnidad int,
    precioUnitario decimal(19,4),
    unidadesEnAlamcen int,
    unidadesEnOrden int,
    nivelDeReorden int,
    descontinuado tinyint,
    idproducto int not null primary key auto_increment 
);

create table empleado(
	apellido varchar(50) not null,
    nombre varchar(50) not null,
    titulo varchar(30),
    fechaNacimiento datetime,
    Contratacion datetime,
    direccion varchar(15),
    ciudad varchar(50),
    codigoPostal varchar(10),
    telefono varchar(10),
    extencion varchar(3),
    idempleado int not null auto_increment primary key
    );

create table venta(
	idempleado int not null,
    foreign key fkempleado(idempleado)
    references empleado(idempleado),
    fecha datetime,
    total decimal(19,4),
    idorden int not null primary key auto_increment
);

create table detallesVenta(
	precioUnitario decimal(19,4),
    cantidad int,
    idproducto int not null,
    foreign key fkproducto(idproducto)
    references producto(idproducto),
    idorden int not null,
    foreign key idorden(idorden)
    references orden(idorden)
);