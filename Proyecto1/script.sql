-- Borrar tablas si existen
DROP TABLE IF EXISTS Vehiculos;
DROP TABLE IF EXISTS Departamentos;

-- Crear tabla Departamentos
CREATE TABLE Departamentos (
    id_dept INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    ubicacion VARCHAR(100)
);

-- Crear tabla Vehiculos
CREATE TABLE Vehiculos (
    id_vehiculo INT PRIMARY KEY AUTO_INCREMENT,
    matricula VARCHAR(10) NOT NULL UNIQUE,
    modelo VARCHAR(50),
    id_dept INT,
    CONSTRAINT fk_departamento
        FOREIGN KEY (id_dept)
        REFERENCES Departamentos(id_dept)
        ON DELETE SET NULL
);

-- Insertar datos
INSERT INTO Departamentos (nombre, ubicacion)
VALUES ('Ventas', 'Planta 1');

INSERT INTO Departamentos (nombre, ubicacion)
VALUES ('Logística', 'Almacén Central');