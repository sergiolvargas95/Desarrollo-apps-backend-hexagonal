-- =============================================
-- Script de creación del schema
-- Gestión de Usuarios y Cursos — PostgreSQL
-- =============================================

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS users (
    id          VARCHAR(36)  NOT NULL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        VARCHAR(20)  NOT NULL CHECK (role IN ('ADMIN', 'MEMBER', 'REVIEWER')),
    status      VARCHAR(20)  NOT NULL DEFAULT 'PENDING'
                    CHECK (status IN ('ACTIVE', 'INACTIVE', 'PENDING', 'BLOCKED')),
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Usuario administrador inicial (password: Admin1234!)
INSERT INTO users (id, name, email, password, role, status)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    'Administrador',
    'admin@example.com',
    '$2a$12$placeholderHashReplaceWithRealBCryptHash',
    'ADMIN',
    'ACTIVE'
) ON CONFLICT (id) DO NOTHING;

-- =============================================
-- Tabla de Cursos
-- =============================================

CREATE TABLE IF NOT EXISTS cursos (
    id          VARCHAR(36)  NOT NULL PRIMARY KEY,
    nombre      VARCHAR(150) NOT NULL,
    codigo      VARCHAR(50)  NOT NULL UNIQUE,
    descripcion TEXT         NOT NULL,
    institucion VARCHAR(150) NOT NULL,
    ubicacion   VARCHAR(255) NOT NULL,
    modalidad   VARCHAR(20)  NOT NULL
                    CHECK (modalidad IN ('VIRTUAL', 'PRESENCIAL', 'SEMIPRESENCIAL')),
    nivel       VARCHAR(20)  NOT NULL
                    CHECK (nivel IN ('BASICO', 'INTERMEDIO', 'AVANZADO')),
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP NOT NULL DEFAULT NOW()
);
