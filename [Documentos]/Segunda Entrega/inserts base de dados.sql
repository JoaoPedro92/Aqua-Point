INSERT INTO zone (zone_name) VALUES
('Lisboa'),
('Porto'),
('Coimbra'),
('Faro'),
('Braga');

INSERT INTO local (local_name, zone_id) VALUES
('Parque das Nações', 1),
('Baixa do Porto', 2),
('Universidade de Coimbra', 3),
('Praia de Faro', 4),
('Centro Histórico de Braga', 5),
('Belém', 1),
('Boavista', 2);

INSERT INTO type (type_name) VALUES
('Fonte Pública'),
('Bebedouro'),
('Rio'),
('Lago'),
('Poço');

INSERT INTO users (name, email, password, joined) VALUES
('João Silva', 'joao.silva@example.com', '1234', '2025-06-01'),
('Maria Santos', 'maria.santos@example.com', 'abcd', '2025-07-15'),
('Carlos Rocha', 'carlos.rocha@example.com', 'pass', '2025-08-10'),
('Ana Gomes', 'ana.gomes@example.com', 'senha', '2025-09-20'),
('Rita Costa', 'rita.costa@example.com', 'root', '2025-10-05');

INSERT INTO states (state_name) VALUES
('Bom estado'),
('Necessita manutenção'),
('Inativo');

INSERT INTO rating (rating) VALUES
(5), (4), (3), (2), (1);

INSERT INTO comment (comment) VALUES
('Água limpa e fresca.'),
('Precisava de manutenção.'),
('Muito bom!'),
('Cheiro estranho.'),
('Perfeito para encher garrafas.');

INSERT INTO aqua_points (point_name, point_type, local_id, latitude, longitude) VALUES
('Fonte da Praça', 1, 1, 38.78049309176, -9.0943),
('Bebedouro do Parque', 2, 1, 38.78105763775865, -9.10341),
('Fonte Ribeira', 1, 2, 38.7797779937, -9.10358927178053),
('Lago da Universidade', 4, 3, 38.7805390919, -9.1013791314),
('Poço da Praia', 5, 4, 37.0170, -7.9350),
('Fonte do Centro', 1, 5, 38.779970, -9.101486),
('Fonte de Belém', 1, 6, 38.779857, -9.100698),
('Fonte da Boavista', 1, 7, 38.781133, -9.104759);

INSERT INTO points_state (point_id, state_id) VALUES
(1, 1),
(2, 2),
(3, 1),
(4, 3),
(5, 1),
(6, 2),
(7, 1),
(8, 1);

INSERT INTO favorites (user_id, point_id, date) VALUES
(1, 1, '2025-07-01'),
(2, 3, '2025-07-02'),
(3, 2, '2025-07-10'),
(4, 5, '2025-08-05'),
(5, 6, '2025-08-12'),
(1, 7, '2025-09-01'),
(2, 8, '2025-09-15');

INSERT INTO interaction (user_id, rating_id, comment_id, point_id, date) VALUES
(1, 1, 1, 1, '2025-07-01'),
(2, 2, 2, 3, '2025-07-05'),
(3, 3, 3, 2, '2025-07-15'),
(4, 4, 4, 5, '2025-08-10'),
(5, 5, 5, 6, '2025-08-18'),
(1, 2, 3, 7, '2025-09-01'),
(2, 1, 1, 8, '2025-09-10');
