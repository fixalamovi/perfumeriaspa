-- V1: Esquema inicial de la Perfumería SPA

CREATE TABLE IF NOT EXISTS categorias (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    tipo TEXT NOT NULL,
    descripcion TEXT
);

CREATE TABLE IF NOT EXISTS perfumes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    marca TEXT NOT NULL,
    precio REAL NOT NULL,
    stock INTEGER NOT NULL DEFAULT 0,
    id_categoria INTEGER,
    tamano TEXT,
    genero TEXT,
    descripcion TEXT,
    fragancia TEXT,
    FOREIGN KEY (id_categoria) REFERENCES categorias(id)
);

CREATE TABLE IF NOT EXISTS clientes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    apellido TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    telefono TEXT,
    direccion TEXT
);

CREATE TABLE IF NOT EXISTS pedidos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    cliente_id INTEGER NOT NULL,
    fecha TEXT NOT NULL,
    total REAL NOT NULL,
    estado TEXT NOT NULL DEFAULT 'PENDIENTE',
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

CREATE TABLE IF NOT EXISTS pedido_perfumes (
    pedido_id INTEGER NOT NULL,
    perfume_id INTEGER NOT NULL,
    PRIMARY KEY (pedido_id, perfume_id),
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    FOREIGN KEY (perfume_id) REFERENCES perfumes(id)
);

CREATE TABLE IF NOT EXISTS resenas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    perfume_id INTEGER NOT NULL,
    cliente_id INTEGER NOT NULL,
    puntuacion INTEGER NOT NULL CHECK(puntuacion >= 1 AND puntuacion <= 5),
    comentario TEXT,
    fecha TEXT NOT NULL,
    FOREIGN KEY (perfume_id) REFERENCES perfumes(id),
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);
