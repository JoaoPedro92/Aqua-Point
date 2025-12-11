-- -------------------------------------------------------
-- Base de Dados AQUAPOINT
-- -------------------------------------------------------

create database if not exists aquapoint;
use aquapoint;

-- -------------------------------------------------------
-- TABELA ZONE
-- -------------------------------------------------------
create table zone (
    id int not null auto_increment,
    zone_name text not null,
    primary key (id)
);

-- -------------------------------------------------------
-- TABELA LOCAL
-- -------------------------------------------------------
create table local (
    id int not null auto_increment,
    local_name text not null,        
    zone_id int not null,            
    primary key (id)
);

-- -------------------------------------------------------
-- TABELA TYPE
-- -------------------------------------------------------
create table type (
    id int not null auto_increment,
    type_name text not null,         
    primary key (id)
);

-- -------------------------------------------------------
-- TABELA USERS
-- -------------------------------------------------------
create table users (
    id int not null auto_increment,
    name text not null,
    email text not null,
    password text not null,
    joined date not null,
    primary key (id)
);

-- -------------------------------------------------------
-- TABELA STATES
-- -------------------------------------------------------
create table states (
    id int not null auto_increment,
    state_name text not null,
    primary key (id)
);

-- -------------------------------------------------------
-- TABELA RATING
-- -------------------------------------------------------=
create table rating (
    id int not null auto_increment,
    rating int not null,             
    primary key (id)
);

-- -------------------------------------------------------
-- TABELA COMMENT
-- -------------------------------------------------------
create table comment (
    id int not null auto_increment,
    comment text not null,
    primary key (id)
);

-- -------------------------------------------------------
-- TABELA AQUA_POINTS
-- -------------------------------------------------------
create table aqua_points (
    id int not null auto_increment,
    point_name text not null,
    point_type int not null,         
    local_id int not null,           
    latitude double not null,
    longitude double not null,
    primary key (id)
);

-- -------------------------------------------------------
-- TABELA POINTS_STATE
-- -------------------------------------------------------
create table points_state (
    id int not null auto_increment,
    point_id int not null,           
    state_id int not null,           
    primary key (id)
);

-- -------------------------------------------------------
-- TABELA FAVORITES
-- -------------------------------------------------------
create table favorites (
    id int not null auto_increment,
    user_id int not null,            
    point_id int not null,           
    date date not null,
    primary key (id)
);

-- -------------------------------------------------------
-- TABELA INTERACTION (reviews)
-- -------------------------------------------------------
create table interaction (
    id int not null auto_increment,
    user_id int not null,            
    rating_id int not null,          
    comment_id int not null,         
    point_id int not null,           
    date date not null,
    primary key (id)
);

-- -------------------------------------------------------
-- FOREIGN KEYS
-- -------------------------------------------------------

-- LOCAL -> ZONE
alter table local
add constraint fk_local_zone
foreign key (zone_id) references zone(id)
on delete cascade on update cascade;

-- AQUA_POINTS -> TYPE e LOCAL
alter table aqua_points
add constraint fk_aqua_points_type
foreign key (point_type) references type(id)
on delete cascade on update cascade;

alter table aqua_points
add constraint fk_aqua_points_local
foreign key (local_id) references local(id)
on delete cascade on update cascade;

-- POINTS_STATE -> AQUA_POINTS e STATES
alter table points_state
add constraint fk_points_state_point
foreign key (point_id) references aqua_points(id)
on delete cascade on update cascade;

alter table points_state
add constraint fk_points_state_state
foreign key (state_id) references states(id)
on delete cascade on update cascade;

-- FAVORITES -> USERS e AQUA_POINTS
alter table favorites
add constraint fk_favorites_user
foreign key (user_id) references users(id)
on delete cascade on update cascade;

alter table favorites
add constraint fk_favorites_point
foreign key (point_id) references aqua_points(id)
on delete cascade on update cascade;

-- INTERACTION -> USERS, RATING, COMMENT, AQUA_POINTS
alter table interaction
add constraint fk_interaction_user
foreign key (user_id) references users(id)
on delete cascade on update cascade;

alter table interaction
add constraint fk_interaction_rating
foreign key (rating_id) references rating(id)
on delete cascade on update cascade;

alter table interaction
add constraint fk_interaction_comment
foreign key (comment_id) references comment(id)
on delete cascade on update cascade;

alter table interaction
add constraint fk_interaction_point
foreign key (point_id) references aqua_points(id)
on delete cascade on update cascade;
