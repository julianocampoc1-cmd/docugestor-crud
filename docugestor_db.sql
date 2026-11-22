-- 1. Crear base de datos del Sistema de Gestión Documental
DROP DATABASE IF EXISTS docugestor_db;
CREATE DATABASE docugestor_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE docugestor_db;

-- 2. Tabla ROL (Entidad padre)
CREATE TABLE rol (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL UNIQUE,
    descripcion VARCHAR(255),
    estado BOOLEAN NOT NULL DEFAULT TRUE
);

-- 3. Tabla USUARIO (Entidad hija con llave foránea hacia rol)
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    documento VARCHAR(20) NOT NULL UNIQUE,
    nombre_completo VARCHAR(120) NOT NULL,
    correo VARCHAR(120) NOT NULL UNIQUE,
    clave VARCHAR(100) NOT NULL,
    id_rol INT NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_usuario_rol
        FOREIGN KEY (id_rol)
        REFERENCES rol(id_rol)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

-- 4. Datos iniciales de prueba
INSERT INTO rol (nombre, descripcion, estado) VALUES
('Administrador', 'Control total del sistema, usuarios y parametrización documental', TRUE),
('Gestor Documental', 'Radicación, clasificación, carga y actualización de expedientes', TRUE),
('Auditor', 'Consulta y verificación de trazabilidad documental sin edición', TRUE);

INSERT INTO usuario (documento, nombre_completo, correo, clave, id_rol, estado) VALUES
('1006123456', 'Julián David Ocampo', 'julian.ocampo@docugestor.com', 'Admin2026*', 1, TRUE),
('1144987654', 'Laura Sofía Martínez', 'laura.martinez@docugestor.com', 'Gestor2026*', 2, TRUE),
('1107456789', 'Carlos Andrés Gómez', 'carlos.gomez@docugestor.com', 'Auditor2026*', 3, TRUE);

-- 5. Consulta de verificación con INNER JOIN
SELECT 
    u.id_usuario,
    u.documento,
    u.nombre_completo,
    u.correo,
    r.nombre AS rol,
    u.estado
FROM usuario u
INNER JOIN rol r ON u.id_rol = r.id_rol;