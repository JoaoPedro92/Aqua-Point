-- -------------------------------------------------------
-- ZONES
-- -------------------------------------------------------
insert into zone (id, zone_name) values (1, 'Zona Lisboa');
insert into zone (id, zone_name) values (2, 'Zona Porto');
insert into zone (id, zone_name) values (3, 'Zona Coimbra');
insert into zone (id, zone_name) values (4, 'Zona Faro');
insert into zone (id, zone_name) values (5, 'Zona Braga');

-- -------------------------------------------------------
-- LOCALS
-- -------------------------------------------------------
insert into local (id, local_name, zone_id) values (1, 'Moscavide', 1);
insert into local (id, local_name, zone_id) values (2, 'Parque das Nações', 1);
insert into local (id, local_name, zone_id) values (3, 'Sacavém', 1);
insert into local (id, local_name, zone_id) values (4, 'Belém', 1);

-- -------------------------------------------------------
-- TYPES
-- -------------------------------------------------------
insert into type (id, type_name) values (1, 'Humano');
insert into type (id, type_name) values (2, 'Animais');
insert into type (id, type_name) values (3, 'Ambos');

-- -------------------------------------------------------
-- USERS
-- -------------------------------------------------------
insert into users (id, name, email, password, joined)
values (1, 'João Silva', 'joao@aqua.com',
'$2a$10$canYSC5o7BHK/eIljUeOfueegcAsXKoZU6TA3XTRyt6wAAaSbUAay', '2025-09-03');

insert into users (id, name, email, password, joined)
values (2, 'Maria Costa', 'maria@aqua.com',
'$2a$10$canYSC5o7BHK/eIljUeOfueegcAsXKoZU6TA3XTRyt6wAAaSbUAay', '2025-09-21');

insert into users (id, name, email, password, joined)
values (3, 'Ricardo Gomes', 'ricardo@aqua.com',
'$2a$10$canYSC5o7BHK/eIljUeOfueegcAsXKoZU6TA3XTRyt6wAAaSbUAay', '2025-10-10');

insert into users (id, name, email, password, joined)
values (4, 'Ana Pereira', 'ana@aqua.com',
'$2a$10$canYSC5o7BHK/eIljUeOfueegcAsXKoZU6TA3XTRyt6wAAaSbUAay', '2025-11-05');

insert into users (id, name, email, password, joined)
values (5, 'Sofia Rocha', 'sofia@aqua.com',
'$2a$10$canYSC5o7BHK/eIljUeOfueegcAsXKoZU6TA3XTRyt6wAAaSbUAay', '2025-12-01');

-- -------------------------------------------------------
-- STATES
-- -------------------------------------------------------
insert into states (id, state_name) values (1, 'Bom estado');
insert into states (id, state_name) values (2, 'Necessita manutenção');

-- -------------------------------------------------------
-- RATING
-- -------------------------------------------------------
insert into rating (id, rating) values (1, 1);
insert into rating (id, rating) values (2, 2);
insert into rating (id, rating) values (3, 3);
insert into rating (id, rating) values (4, 4);
insert into rating (id, rating) values (5, 5);

-- -------------------------------------------------------
-- COMMENTS
-- -------------------------------------------------------
insert into comment (id, comment) values (1, 'Funcionava perfeitamente, água fresca.');
insert into comment (id, comment) values (2, 'Pressão da água muito fraca.');
insert into comment (id, comment) values (3, 'Excelente para encher garrafas.');
insert into comment (id, comment) values (4, 'Estava desligado.');
insert into comment (id, comment) values (5, 'Local limpo e acessível.');
insert into comment (id, comment) values (6, 'Parece precisar de manutenção.');
insert into comment (id, comment) values (7, 'Bom para animais, bem posicionado.');
insert into comment (id, comment) values (8, 'Muito usado, devia ser melhorado.');
insert into comment (id, comment) values (9, 'Água com bom sabor.');
insert into comment (id, comment) values (10, 'Fluxo irregular, mas funciona.');

-- -------------------------------------------------------
-- AQUA_POINTS
-- -------------------------------------------------------
insert into aqua_points (id, point_name, point_type, local_id, latitude, longitude)
values (1, 'Bebedouro do IADE', 3, 1, 38.780500, -9.102231);

insert into aqua_points (id, point_name, point_type, local_id, latitude, longitude)
values (2, 'Bebedouro Moscavide', 1, 2, 38.780605, -9.100509);

insert into aqua_points (id, point_name, point_type, local_id, latitude, longitude)
values (3, 'Bebedouro 25 Abril', 2, 1, 38.779082, -9.102526);

insert into aqua_points (id, point_name, point_type, local_id, latitude, longitude)
values (4, 'Bebedouro das Velas', 1, 1, 38.780801, -9.098031);

insert into aqua_points (id, point_name, point_type, local_id, latitude, longitude)
values (5, 'Bebedouro de Sacavém', 3, 3, 38.783080, -9.102465);

insert into aqua_points (id, point_name, point_type, local_id, latitude, longitude)
values (6, 'Bebedouro do Nilo', 2, 1, 38.782060, -9.099311);

insert into aqua_points (id, point_name, point_type, local_id, latitude, longitude)
values (7, 'Bebedouro da Rainha', 1, 2, 38.782782, -9.091919);

-- -------------------------------------------------------
-- POINTS_STATE
-- -------------------------------------------------------
insert into points_state (id, point_id, state_id) values (1, 1, 1);
insert into points_state (id, point_id, state_id) values (2, 2, 2);
insert into points_state (id, point_id, state_id) values (3, 3, 1);
insert into points_state (id, point_id, state_id) values (4, 4, 2);
insert into points_state (id, point_id, state_id) values (5, 5, 1);
insert into points_state (id, point_id, state_id) values (6, 6, 1);
insert into points_state (id, point_id, state_id) values (7, 7, 2);

-- -------------------------------------------------------
-- FAVORITES (com IDs obrigatórios)
-- -------------------------------------------------------
insert into favorites (id, user_id, point_id, date)
values (1, 1, 1, '2025-07-01');

insert into favorites (id, user_id, point_id, date)
values (2, 2, 3, '2025-07-02');

insert into favorites (id, user_id, point_id, date)
values (3, 3, 2, '2025-07-10');

insert into favorites (id, user_id, point_id, date)
values (4, 4, 5, '2025-08-05');

insert into favorites (id, user_id, point_id, date)
values (5, 5, 6, '2025-08-12');

insert into favorites (id, user_id, point_id, date)
values (6, 1, 7, '2025-09-01');

-- -------------------------------------------------------
-- INTERACTIONS (reviews)
-- -------------------------------------------------------
insert into interaction (id, user_id, rating_id, comment_id, point_id, date)
values (1, 1, 5, 1, 1, '2025-09-05');

insert into interaction (id, user_id, rating_id, comment_id, point_id, date)
values (2, 2, 2, 2, 2, '2025-09-18');

insert into interaction (id, user_id, rating_id, comment_id, point_id, date)
values (3, 3, 4, 3, 3, '2025-10-02');

insert into interaction (id, user_id, rating_id, comment_id, point_id, date)
values (4, 4, 1, 4, 4, '2025-10-21');

insert into interaction (id, user_id, rating_id, comment_id, point_id, date)
values (5, 5, 5, 5, 5, '2025-11-03');

insert into interaction (id, user_id, rating_id, comment_id, point_id, date)
values (6, 1, 3, 6, 6, '2025-11-17');

insert into interaction (id, user_id, rating_id, comment_id, point_id, date)
values (7, 2, 4, 7, 6, '2025-11-29');

insert into interaction (id, user_id, rating_id, comment_id, point_id, date)
values (8, 3, 2, 8, 7, '2025-12-04');

insert into interaction (id, user_id, rating_id, comment_id, point_id, date)
values (9, 4, 5, 9, 1, '2025-12-15');

insert into interaction (id, user_id, rating_id, comment_id, point_id, date)
values (10, 5, 3, 10, 3, '2025-12-28');

commit;