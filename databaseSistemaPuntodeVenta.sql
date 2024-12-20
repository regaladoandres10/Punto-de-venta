CREATE DATABASE puntoVenta;
USE puntoVenta;

-- CREATE USER 'Admin'@'localhost' identified BY '1234';

GRANT SELECT, UPDATE, DELETE, INSERT ON puntoVenta.* TO 'Admin'@'localhost';


CREATE TABLE empleado (
	idempleado INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    apellido VARCHAR(50) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    titulo VARCHAR(30),
    fechaNacimiento DATETIME,
    contratacion DATETIME,
    direccion VARCHAR(100),
    ciudad VARCHAR(50),
    codigoPostal VARCHAR(10),
    telefono VARCHAR(10),
    extension VARCHAR(3)
);


CREATE TABLE login
(
	id INT NOT NULL PRIMARY KEY auto_increment,
    user VARCHAR(100) NOT NULL unique,
    password VARCHAR(100) NOT NULL,
    idempleado INT NOT NULL,
    FOREIGN KEY (idempleado) REFERENCES empleado(idempleado) ON DELETE CASCADE
);

CREATE TABLE categoria (
	idcategoria INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50),
    descripcion TEXT
);

CREATE TABLE producto (
	idproducto INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50),
    codigo VARCHAR(13) UNIQUE,
    idcategoria INT NOT NULL,
    FOREIGN KEY (idcategoria) REFERENCES categoria(idcategoria) ON DELETE CASCADE,
    cantidadPorUnidad INT,
    precioUnitario DECIMAL(19,4),
    unidadesEnAlmacen INT,
    unidadesEnOrden INT,
    nivelDeReorden INT
);

CREATE TABLE clientes(
	idCliente INT NOT NULL primary key auto_increment,
    nombreCompañia VARCHAR(50) NOT NULL,
    nombreContacto VARCHAR(40),
    tituloContacto VARCHAR(40),
    direccion VARCHAR(60),
    ciudad VARCHAR(30) NOT NULL,
    codigoPostal VARCHAR(10) NOT NULL,
    pais VARCHAR(30),
    telefono VARCHAR(10)
);

CREATE TABLE venta (
	idorden INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idempleado INT NOT NULL,
    FOREIGN KEY (idempleado) REFERENCES empleado(idempleado) ON DELETE CASCADE,
    idCliente INT NOT NULL,
    FOREIGN KEY (idCliente) references clientes(idCliente) ON DELETE CASCADE,
    fecha DATETIME,
    total DECIMAL(19,4)
);


-- Ahora puedes crear la tabla 'detallesVenta' sin problemas

CREATE TABLE detallesVenta (
    precioUnitario DECIMAL(19,4),
    cantidad INT,
    codigo VARCHAR(13) NOT NULL,
    FOREIGN KEY (codigo) REFERENCES producto(codigo) ON DELETE CASCADE,
    idorden INT NOT NULL,
    FOREIGN KEY (idorden) REFERENCES venta(idorden) ON DELETE CASCADE
);

CREATE TABLE auditoria(
	
    idAuditoria INT NOT NULL PRIMARY KEY auto_increment,
    idEmpleado INT,
    fecha datetime,
    cambio ENUM('INSERT', 'UPDATE', 'DELETE'),
    fechaCambio datetime not null default current_timestamp
    
);


-- Crear trigger INSERT de venas
DELIMITER //
CREATE TRIGGER venta_insert
AFTER INSERT ON venta
FOR EACH ROW
BEGIN
	INSERT INTO auditoria (idEmpleado, fecha, cambio) VALUES(NEW.idempleado, NEW.fecha, 'INSERT');
END//
DELIMITER ;

-- Auditoria update venta
DELIMITER //
CREATE TRIGGER venta_UPDATE
AFTER UPDATE ON venta
FOR EACH ROW
BEGIN
	INSERT INTO auditoria (idEmpleado, fecha, cambio) VALUES(NEW.idempleado, OLD.fecha, 'UPDATE');
END//
DELIMITER ;
-- Auditoria delete venta
DELIMITER //
CREATE TRIGGER venta_DELETE
AFTER UPDATE ON venta
FOR EACH ROW
BEGIN
	INSERT INTO auditoria (idEmpleado, fecha, cambio) VALUES(NEW.idempleado, OLD.fecha, 'DELETE');
END//
DELIMITER ;






-- Utilizando un procedimiento almacenado (Store procedure)
-- Crear
DELIMITER //
CREATE procedure guardarClientes
(IN nombreCompañia VARCHAR(50), IN nombreContacto VARCHAR(40), IN tituloContacto VARCHAR(40), IN direccion VARCHAR(60), IN ciudad VARCHAR(30), IN codigoPostal VARCHAR(10), IN pais VARCHAR(30), IN telefono VARCHAR(10))
BEGIN
	INSERT INTO clientes(
    nombreCompañia,
    nombreContacto,
    tituloContacto,
    direccion,
    ciudad,
    codigoPostal,
    pais,
    telefono
    )
    VALUES(
    nombreCompañia,
    nombreContacto,
    tituloContacto,
    direccion,
    ciudad,
    codigoPostal,
    pais,
    telefono
    );
END//

DELIMITER ;
-- Mandamos llamar el store procedure
CALL guardarClientes("Costco", "Marieal Martinez Herrera", "Gerente", "Av. Miguel Martinez 198", "Queretaro", "98987", "Mexico", "442321232");

DELIMITER //
-- Creamos el store procedure editar o actualizar
CREATE PROCEDURE actualizarClientes
(IN id INT, IN nombreCompañia VARCHAR(50), IN nombreContacto VARCHAR(40), IN tituloContacto VARCHAR(40), IN direccion VARCHAR(60), IN ciudad VARCHAR(30), IN codigoPostal VARCHAR(10), IN pais VARCHAR(30), IN telefono VARCHAR(10))
begin
	UPDATE clientes
    SET 
    nombreCompañia = nombreCompañia, 
    nombreContacto = nombreContacto, 
    tituloContacto = tituloContacto, 
    direccion = direccion, 
    ciudad = ciudad, 
    codigoPostal = codigoPostal, 
    pais = pais, 
    telefono = telefono
    WHERE idCliente = id;
end//

DELIMITER ;
-- Mandamos llamar el store procedure
CALL actualizarClientes(1, "Soriana", "Andres Perez", "RH", "Dalia 1213A", "Uriangato", "65656", "Mexico", "4421212321");

-- Creando store procedure para eliminar cliente
DELIMITER //
CREATE PROCEDURE eliminarCliente
(IN id int)
begin
	DELETE FROM clientes WHERE idCliente = id;
end//

-- Mandamos llamar el store procedure eliminar
DELIMITER ;
CALL eliminarCliente(4);



-- Vistas
-- Reporte por mes
CREATE VIEW reporteMes AS
SELECT ven.idOrden, ven.fecha, cli.nombreCompañia AS nombreCompania, concat(emp.nombre," ",emp.apellido) AS empleado, ven.total, count(dv.codigo) AS detalles
FROM (((venta ven JOIN clientes cli ON ven.idCliente = cli.IdCliente) JOIN empleado emp ON ven.idempleado = emp.idempleado) JOIN detallesventa dv ON ven.idorden = dv.idorden) GROUP BY ven.idorden;

-- Reporte ventas por producto
CREATE VIEW ventasPorProducto AS
SELECT concat(emp.nombre," ",emp.apellido) AS Nombre, ven.idorden, prod.nombre AS producto, dv.cantidad, ven.fecha
FROM(((venta ven JOIN  empleado emp ON ven.idempleado = emp.idempleado) JOIN detallesventa dv ON ven.idorden = dv.idorden)JOIN producto prod ON dv.codigo = prod.codigo);

-- Reporte ventas por empleado
create view VentasEmpleado as
SELECT v.idOrden, v.fecha, concat(e.nombre," ",e.apellido) AS empleado, v.total
FROM venta v  JOIN empleado e ON v.idempleado = e.idempleado ;



-- Funcion cantidad de productos
DELIMITER //

CREATE FUNCTION calcularCantidadProductos(folioVenta INT) 
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE totalProductos INT;
    
    SELECT SUM(cantidad) INTO totalProductos
    FROM detallesventa
    WHERE idorden = folioVenta;
    
    RETURN COALESCE(totalProductos, 0);
END //

DELIMITER ;




INSERT INTO empleado (apellido, nombre, titulo, fechaNacimiento, contratacion, direccion, ciudad, codigoPostal, telefono, extension)
VALUES ('', 'Admin', 'Administrador', '1990-04-20', '2023-01-15', 'Calle Principal A', 'Ciudad D', '02345', '5351234567', '342');

INSERT INTO empleado (
    apellido, nombre, titulo, fechaNacimiento, contratacion, direccion, ciudad, codigoPostal, telefono, extension
) VALUES 
('Gómez', 'Juan', 'Gerente', '1980-05-12 00:00:00', '2022-01-15 09:00:00', 'Calle Principal 123', 'Ciudad A', '12345', '5551234567', '101');

INSERT INTO empleado (
    apellido, nombre, titulo, fechaNacimiento, contratacion, direccion, ciudad, codigoPostal, telefono, extension
) VALUES 
('Pérez', 'María', 'Analista', '1992-07-25 00:00:00', '2023-03-10 08:30:00', 'Av. Las Flores 456', 'Ciudad B', '67890', '5559876543', '102');

INSERT INTO empleado (
    apellido, nombre, titulo, fechaNacimiento, contratacion, direccion, ciudad, codigoPostal, telefono, extension
) VALUES 
('López', 'Carlos', 'Desarrollador', '1988-11-02 00:00:00', '2024-02-01 10:00:00', 'Calle Nueva 789', 'Ciudad C', '54321', '5552468135', '103');

INSERT INTO categoria (nombre, descripcion) VALUES 
('Bebidas', 'Productos líquidos como refrescos, jugos y agua embotellada'),
('Snacks', 'Aperitivos como galletas, papas fritas y dulces'),
('Cereales y Granos', 'Cereales para el desayuno, arroz, pasta y otros granos'),
('Enlatados y Conservas', 'Productos enlatados como atún, frijoles y verduras en conserva'),
('Lácteos', 'Productos lácteos como leche, yogurt y queso'),
('Limpieza', 'Productos para la limpieza del hogar como detergentes, desinfectantes y jabones'),
('Básicos de Cocina', 'Aceites, especias, azúcar, sal y otros productos de cocina básicos'),
('Salsas y Condimentos', 'Salsas, aderezos, especias y otros condimentos'),
('Panadería y Tortillería', 'Productos de panadería y tortillas'),
('Café y Té', 'Café, té y otros productos relacionados'),
('Carnes y Embutidos', 'Productos cárnicos como pollo, res, jamón y embutidos'),
('Frutas y Verduras', 'Frutas y verduras frescas'),
('Higiene Personal', 'Productos de higiene personal como shampoo, jabón, pasta dental'),
('Bebidas Alcohólicas', 'Bebidas con contenido alcohólico como cervezas y vinos');

 
 INSERT INTO producto (nombre, codigo, idcategoria, cantidadPorUnidad, precioUnitario, unidadesEnAlmacen, unidadesEnOrden, nivelDeReorden)
VALUES
('Coca-Cola', '1234567890123', 1, 24, 0.99, 100, 50, 10),
('Pepsi', '2345678901234', 1, 24, 0.95, 90, 45, 10),
('Galletas Oreo', '3456789012345', 2, 12, 2.99, 80, 40, 5),
('Cereal Kellogg''s', '4567890123456', 3, 6, 3.49, 70, 35, 5),
('Atún Dolores', '5678901234567', 4, 48, 1.99, 60, 30, 10),
('Leche Lala', '6789012345678', 5, 12, 1.50, 100, 50, 15),
('Yogurt Danone', '7890123456789', 5, 6, 0.80, 120, 60, 10),
('Jabón Zote', '8901234567890', 6, 12, 1.20, 50, 25, 5),
('Detergente Ariel', '9012345678901', 6, 10, 4.50, 80, 40, 8),
('Arroz La Merced', '0123456789012', 7, 10, 0.90, 150, 75, 15),
('Aceite Capullo', '1234567890133', 8, 1, 3.00, 60, 20, 10),
('Mayonesa McCormick', '2345678901244', 9, 6, 2.50, 40, 15, 5),
('Salsa Valentina', '3456789012355', 10, 12, 1.30, 90, 45, 10),
('Café Nescafé', '4567890123466', 11, 1, 5.50, 70, 25, 10),
('Tortillas Maseca', '5678901234577', 7, 20, 1.00, 100, 40, 15),
('Azúcar Zulka', '6789012345688', 7, 10, 1.10, 120, 50, 15),
('Sal La Fina', '7890123456799', 7, 12, 0.70, 80, 30, 10),
('Harina Gamesa', '8901234567800', 7, 5, 1.20, 90, 35, 8),
('Jugo Jumex', '9012345678912', 1, 24, 0.90, 100, 40, 12),
('Refresco Sprite', '0123456789023', 1, 24, 0.99, 80, 30, 10),
('Pan Bimbo', '1234567890144', 2, 10, 1.50, 100, 50, 10),
('Papel Higiénico Pétalo', '2345678901255', 6, 4, 3.99, 200, 80, 15),
('Cerveza Corona', '3456789012366', 1, 24, 0.70, 150, 50, 20),
('Shampoo Pantene', '4567890123477', 6, 6, 4.30, 60, 20, 5),
('Cepillo Colgate', '5678901234588', 6, 1, 1.00, 100, 30, 10),
('Pasta Dental Colgate', '6789012345699', 6, 1, 1.50, 90, 25, 8),
('Cloro Cloralex', '7890123456801', 6, 10, 2.20, 80, 20, 10),
('Desinfectante Pinol', '8901234567812', 6, 10, 3.00, 70, 15, 10),
('Chocolates Hershey''s', '9012345678923', 2, 12, 1.99, 80, 20, 10),
('Helado Holanda', '0123456789034', 5, 6, 3.50, 50, 15, 5),
('Mermelada La Costeña', '1234567890155', 9, 1, 2.75, 70, 20, 8),
('Refresco Fanta', '2345678901266', 1, 24, 0.95, 60, 20, 10),
('Galletas Marías', '3456789012377', 2, 12, 1.20, 110, 40, 10),
('Lechuga Romana', '4567890123488', 12, 1, 0.80, 40, 10, 5),
('Tomate Bola', '5678901234599', 12, 1, 1.10, 100, 50, 20),
('Zanahoria', '6789012345700', 12, 1, 0.90, 80, 25, 10),
('Papa Blanca', '7890123456811', 12, 1, 1.00, 90, 30, 15),
('Cebolla Blanca', '8901234567822', 12, 1, 0.85, 100, 40, 20),
('Manzana Roja', '9012345678933', 12, 1, 1.50, 120, 60, 15),
('Naranja Valencia', '0123456789045', 12, 1, 1.00, 110, 50, 15),
('Cilantro', '1234567890166', 12, 1, 0.50, 60, 20, 5),
('Perejil', '2345678901277', 12, 1, 0.40, 50, 15, 5),
('Plátano', '3456789012388', 12, 1, 0.60, 100, 30, 10),
('Aguacate Hass', '4567890123499', 12, 1, 2.50, 60, 15, 5),
('Limón', '5678901234600', 12, 1, 0.70, 80, 20, 10),
('Piña', '6789012345711', 12, 1, 1.75, 50, 10, 5),
('Sandía', '7890123456822', 12, 1, 2.00, 40, 10, 5),
('Pollo Entero', '8901234567833', 13, 1, 5.00, 30, 5, 3),
('Pierna de Pollo', '9012345678944', 13, 1, 3.00, 25, 5, 2),
('Carne Molida', '0123456789056', 13, 1, 6.00, 20, 5, 2),
('Chuleta de Cerdo', '1234567890177', 13, 1, 4.50, 15, 3, 1),
('Jamón Virginia', '2345678901288', 13, 1, 3.80, 50, 10, 5),
('Salchicha San Rafael', '3456789012399', 13, 1, 2.80, 60, 12, 8);

INSERT INTO clientes (nombreCompañia, nombreContacto, tituloContacto, direccion, ciudad, codigoPostal, pais, telefono)
VALUES
('Supermercado El Buen Ahorro', 'Carlos Martínez', 'Gerente de Tienda', 'Av. Las Palmas 123', 'Ciudad de México', '01234', 'México', '5512345678'),
('Mercado Familiar', 'Ana López', 'Encargada de Ventas', 'Calle Central 456', 'Guadalajara', '44123', 'México', '3312345678'),
('Abarrotes La Esquina', 'Pedro Ramírez', 'Supervisor', 'Colonia Centro 789', 'Monterrey', '64056', 'México', '8112345678'),
('Supermercado Frescura', 'Laura García', 'Administradora', 'Blvd. Reforma 101', 'Puebla', '72089', 'México', '2212345678'),
('MegaSuper', 'Diego Ortega', 'Subgerente', 'Calle Independencia 202', 'Tijuana', '22045', 'México', '6641234567'),
('Hipermercado La Económica', 'María Hernández', 'Gerente General', 'Av. Principal 303', 'León', '37067', 'México', '4771234567'),
('Supermercado Mi Tiendita', 'Andrea Torres', 'Jefa de Sucursal', 'Plaza Comercial 404', 'Querétaro', '76090', 'México', '4421234567'),
('Abarrotes y Más', 'Luis Fernández', 'Jefe de Compras', 'Zona Norte 505', 'Mérida', '97045', 'México', '9991234567'),
('Supermercado El Surtidor', 'Sofía Pérez', 'Asesora de Ventas', 'Zona Sur 606', 'Cancún', '77523', 'México', '9981234567'),
('Tienda Todo Fresco', 'Juan Sánchez', 'Coordinador de Inventario', 'Carretera Nacional 707', 'Ciudad de México', '01567', 'México', '5551234567');

select * from clientes;

DELIMITER //

CREATE TRIGGER validarProducto
BEFORE INSERT ON producto
FOR EACH ROW
BEGIN
    -- Validación a: El precio no debe ser negativo
    IF NEW.precioUnitario < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El precio de un producto no puede ser negativo.';
    END IF;

    -- Validación b: El nombre no debe ser vacío o solo espacios en blanco
    IF TRIM(NEW.nombre) = '' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El nombre de un producto no puede estar vacío o contener solo espacios en blanco.';
    END IF;

    -- Validación c: Código de barras debe ser NULL o tener entre 8 y 20 caracteres
    IF NEW.codigo IS NOT NULL AND (CHAR_LENGTH(NEW.codigo) < 8 OR CHAR_LENGTH(NEW.codigo) > 20) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El código de barras debe tener entre 8 y 20 caracteres o ser NULL.';
    END IF;
END//

DELIMITER ;

insert into producto values(101,'ok','1234565675',3,1,1,100,1,4);
insert into login values(null,'Admin',sha('1234'),1);

delimiter //

create procedure spVentasAleatorias(ventas int)
begin
    declare j int default 0;
    declare i int default 0;
    declare Idproducto  varchar(20);
    declare Fecha_venta date;
    declare Cantidad int;
    declare Total decimal(10, 2) default 0;
    declare Fecha_random date;
    declare limite int;
    declare Precio decimal(10, 2);
    declare idEmpleado int default null;
    declare Id_venta int;
    declare idCliente int default null;

    while j < ventas do
        set Total = 0; -- Reiniciar total de la venta
        set Limite = greatest(1, floor(1 + (rand() * 5))); -- Número aleatorio de productos por venta
        set Fecha_random = date_add(curdate(), interval floor(rand() * 365) day);

        -- Seleccionar empleado y cliente aleatorios
		set idEmpleado = floor(rand()*(select count(idempleado) from empleado)+1);
        set idCliente = floor(rand()*(select count(idcliente) from clientes)+1);

        -- Mensajes de depuración
        -- select idEmpleado as 'Empleado seleccionado', idCliente as 'Cliente seleccionado';

        -- Crear la venta
        insert into venta (idempleado, idcliente, fecha, total)
        values (idEmpleado, idCliente, Fecha_random, Total);

        -- Obtener el ID de la venta recién insertada
        set Id_venta = LAST_INSERT_ID();

        -- Insertar detalles de la venta
        set i = 0;
        REPEAT
            -- Seleccionar un producto aleatorio
            select codigo into Idproducto from producto order by rand() limit 1;

            -- Cantidad aleatoria entre 1 y 60
            set Cantidad = floor(1 + (rand() * 60));

            -- Obtener precio del producto
            select precioUnitario into Precio from producto where codigo = Idproducto;

            -- Calcular el total acumulado de la venta
            set Total = Total + (Cantidad * Precio);

            -- Insertar en detallesVenta
            insert into detallesVenta (precioUnitario,cantidad,codigo,idorden)
            values (Precio,Cantidad,Idproducto,Id_venta);

            set i = i + 1;
        UNTIL i >= Limite
        END REPEAT;

        -- Actualizar el total en la tabla `venta`
        update venta set total = Total where idorden = Id_venta;

        -- Incrementar el contador de ventas
        set j = j + 1;
    end while;
end //

delimiter ;


