-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 16-Out-2025 às 20:46
-- Versão do servidor: 10.4.32-MariaDB
-- versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `aqua_point`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `aqua_points`
--

CREATE TABLE `aqua_points` (
  `id` int(11) NOT NULL,
  `point_name` text NOT NULL,
  `point_type` int(11) NOT NULL,
  `local_id` int(11) NOT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `comment` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `favorites`
--

CREATE TABLE `favorites` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `point_id` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `interaction`
--

CREATE TABLE `interaction` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `rating_id` int(11) NOT NULL,
  `comment_id` int(11) NOT NULL,
  `point_id` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `local`
--

CREATE TABLE `local` (
  `id` int(11) NOT NULL,
  `local_name` text NOT NULL,
  `zone_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `points_state`
--

CREATE TABLE `points_state` (
  `id` int(11) NOT NULL,
  `point_id` int(11) NOT NULL,
  `state_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `rating`
--

CREATE TABLE `rating` (
  `id` int(11) NOT NULL,
  `rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `states`
--

CREATE TABLE `states` (
  `id` int(11) NOT NULL,
  `state_name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `type`
--

CREATE TABLE `type` (
  `id` int(11) NOT NULL,
  `type_name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `email` text NOT NULL,
  `password` text NOT NULL,
  `joined` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `zone`
--

CREATE TABLE `zone` (
  `id` int(11) NOT NULL,
  `zone_name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `aqua_points`
--
ALTER TABLE `aqua_points`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `favorites`
--
ALTER TABLE `favorites`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `interaction`
--
ALTER TABLE `interaction`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `local`
--
ALTER TABLE `local`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `points_state`
--
ALTER TABLE `points_state`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `rating`
--
ALTER TABLE `rating`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `states`
--
ALTER TABLE `states`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `zone`
--
ALTER TABLE `zone`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `aqua_points`
--
ALTER TABLE `aqua_points`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `favorites`
--
ALTER TABLE `favorites`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `interaction`
--
ALTER TABLE `interaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `local`
--
ALTER TABLE `local`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `points_state`
--
ALTER TABLE `points_state`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `rating`
--
ALTER TABLE `rating`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `states`
--
ALTER TABLE `states`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `type`
--
ALTER TABLE `type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `zone`
--
ALTER TABLE `zone`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

--
-- Relações entre tabelas (FOREIGN KEYS)
--

-- Relação: AQUA_POINTS -> TYPE e LOCAL
ALTER TABLE `aqua_points`
  ADD CONSTRAINT `fk_aqua_points_type`
    FOREIGN KEY (`point_type`) REFERENCES `type`(`id`) 
    ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_aqua_points_local`
    FOREIGN KEY (`local_id`) REFERENCES `local`(`id`) 
    ON DELETE CASCADE ON UPDATE CASCADE;

-- Relação: LOCAL -> ZONE
ALTER TABLE `local`
  ADD CONSTRAINT `fk_local_zone`
    FOREIGN KEY (`zone_id`) REFERENCES `zone`(`id`) 
    ON DELETE CASCADE ON UPDATE CASCADE;

-- Relação: POINTS_STATE -> AQUA_POINTS e STATES
ALTER TABLE `points_state`
  ADD CONSTRAINT `fk_points_state_point`
    FOREIGN KEY (`point_id`) REFERENCES `aqua_points`(`id`) 
    ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_points_state_state`
    FOREIGN KEY (`state_id`) REFERENCES `states`(`id`) 
    ON DELETE CASCADE ON UPDATE CASCADE;

-- Relação: INTERACTION -> USERS, AQUA_POINTS, RATING e COMMENT
ALTER TABLE `interaction`
  ADD CONSTRAINT `fk_interaction_user`
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) 
    ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_interaction_rating`
    FOREIGN KEY (`rating_id`) REFERENCES `rating`(`id`) 
    ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_interaction_comment`
    FOREIGN KEY (`comment_id`) REFERENCES `comment`(`id`) 
    ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_interaction_point`
    FOREIGN KEY (`point_id`) REFERENCES `aqua_points`(`id`) 
    ON DELETE CASCADE ON UPDATE CASCADE;

-- Relação: FAVORITES -> USERS e AQUA_POINTS
ALTER TABLE `favorites`
  ADD CONSTRAINT `fk_favorites_user`
    FOREIGN KEY (`user_id`) REFERENCES `users`(`id`) 
    ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_favorites_point`
    FOREIGN KEY (`point_id`) REFERENCES `aqua_points`(`id`) 
    ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
