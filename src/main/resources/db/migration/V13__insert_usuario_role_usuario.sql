INSERT INTO usuario (nome, email, password) VALUES ('admin', 'admin@email.com', '$2a$12$yjreWuGuDeuMIeziO42a5u/t0eZwPtmBODVxnOZx7IJ/ahZ.N9UNy');
INSERT INTO role (nome) VALUES ('ADMIN');
INSERT INTO usuario_role (usuario_id, role_id) VALUES (3, 2);