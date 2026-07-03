-- V2: Datos iniciales de prueba

INSERT INTO categorias (nombre, tipo, descripcion) VALUES
('Floral', 'Mujer', 'Fragancias florales y delicadas'),
('Amaderado', 'Hombre', 'Notas amaderadas y robustas'),
('Oriental', 'Unisex', 'Fragancias exóticas y especiadas');

INSERT INTO perfumes (nombre, marca, precio, stock, id_categoria, tamano, genero, descripcion, fragancia) VALUES
('Chanel N°5', 'Chanel', 89990, 50, 1, '100ml', 'Mujer', 'Icónico perfume floral', 'Rosa, Jazmín, Vainilla'),
('Bleu de Chanel', 'Chanel', 79990, 30, 2, '100ml', 'Hombre', 'Fresco y amaderado', 'Cedro, Madera de Sándalo'),
('Black Opium', 'Yves Saint Laurent', 74990, 40, 3, '90ml', 'Mujer', 'Oscuro y seductor', 'Café, Vainilla, Flor Blanca');
