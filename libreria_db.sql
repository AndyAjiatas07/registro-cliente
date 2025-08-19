drop database if exists libreria_db;
CREATE DATABASE IF NOT EXISTS libreria_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE libreria_db;

CREATE TABLE Autor (
    codigoAutor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    nacionalidad VARCHAR(50),
    biografia TEXT
);

CREATE TABLE Categoria (
    codigoCategoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);

CREATE TABLE Libro (
    codigoLibro INT AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    titulo VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    fecha_publicacion DATE,
    codigoCategoria INT,
    FOREIGN KEY (codigoCategoria) REFERENCES Categoria(codigoCategoria) ON DELETE SET NULL
);

CREATE TABLE Cliente (
    codigoCliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, -- IMPORTANTE: Siempre guarda contraseñas hasheadas!
    direccion VARCHAR(255),
    fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Venta (
    codigoVenta INT AUTO_INCREMENT PRIMARY KEY,
    codigoCliente INT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10, 2) NOT NULL,
    estado VARCHAR(20) DEFAULT 'PROCESANDO', -- Ej: PROCESANDO, ENVIADO, ENTREGADO
    FOREIGN KEY (codigoCliente) REFERENCES Cliente(codigoCliente)
);


CREATE TABLE LibroAutor (
    codigoLibro INT NOT NULL,
    codigoAutor INT NOT NULL,
    PRIMARY KEY (codigoLibro, codigoAutor),
    FOREIGN KEY (codigoLibro) REFERENCES Libro(codigoLibro) ON DELETE CASCADE,
    FOREIGN KEY (codigoAutor) REFERENCES Autor(codigoAutor) ON DELETE CASCADE
);

CREATE TABLE DetalleVenta (
    codigoDetalleVenta INT AUTO_INCREMENT PRIMARY KEY,
    codigoVenta INT NOT NULL,
    codigoLibro INT NOT NULL,
    cantidad INT NOT NULL,
    precioUnitario DECIMAL(10, 2) NOT NULL, -- Precio al momento de la compra
    FOREIGN KEY (codigoVenta) REFERENCES Venta(codigoVenta) ON DELETE CASCADE,
    FOREIGN KEY (codigoLibro) REFERENCES Libro(codigoLibro)
);

-- Insertar Categorías
INSERT INTO Categoria (nombre, descripcion) VALUES
('Ciencia Ficción', 'Viajes en el tiempo, futuros distópicos y tecnología avanzada.'),
('Fantasía', 'Magia, mundos épicos y criaturas mitológicas.'),
('Programación', 'Guías y manuales para desarrolladores de software.');

-- Insertar Autores
INSERT INTO Autor (nombre, nacionalidad, biografia) VALUES
('Isaac Asimov', 'Estadounidense', 'Escritor y profesor de bioquímica, un prolífico autor de obras de ciencia ficción.'),
('J.R.R. Tolkien', 'Británico', 'Escritor, poeta, filólogo y profesor universitario, conocido por El Hobbit y El Señor de los Anillos.'),
('Robert C. Martin', 'Estadounidense', 'Ingeniero de software y autor, conocido como "Uncle Bob".');

-- Insertar Libros
INSERT INTO Libro (isbn, titulo, precio, stock, codigoCategoria, fecha_publicacion) VALUES
('978-0553803716', 'Fundación', 15.99, 50, 1, '1951-06-01'),
('978-0618640157', 'La Comunidad del Anillo', 18.50, 35, 2, '1954-07-29'),
('978-0132350884', 'Clean Code: A Handbook of Agile Software Craftsmanship', 35.75, 100, 3, '2008-08-01');

-- Relacionar Libros con Autores
INSERT INTO LibroAutor (codigoLibro, codigoAutor) VALUES
(1, 1), -- Fundación -> Isaac Asimov
(2, 2), -- La Comunidad del Anillo -> J.R.R. Tolkien
(3, 3); -- Clean Code -> Robert C. Martin

-- Insertar un Cliente de prueba
INSERT INTO Cliente (nombre, apellido, email, password) VALUES
('Ana', 'García', 'ana.garcia@email.com', 'hash_de_la_contraseña'); -- Recuerda usar un algoritmo como BCrypt para el hash
INSERT INTO Cliente (nombre, apellido, email, password) VALUES
('alvaro', 'Calderon', 'a.calderon@email.com', 'admin');

-- Crear una Venta de prueba
INSERT INTO Venta (codigoCliente, total, estado) VALUES
(1, 51.49, 'ENVIADO');

-- Detalle de la Venta
INSERT INTO DetalleVenta (codigoVenta, codigoLibro, cantidad, precioUnitario) VALUES
(1, 1, 1, 15.99), -- 1 copia de Fundación
(1, 3, 1, 35.50); -- 1 copia de Clean Code (el precio puede variar ligeramente)