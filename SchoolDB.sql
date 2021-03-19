-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 19 Mar 2021 pada 16.54
-- Versi server: 10.4.14-MariaDB
-- Versi PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `SchoolDB`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admins`
--

CREATE TABLE `admins` (
  `id` varchar(20) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(300) NOT NULL,
  `salt` varchar(300) NOT NULL,
  `last_login` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `admins`
--

INSERT INTO `admins` (`id`, `username`, `password`, `salt`, `last_login`) VALUES
('1', 'admin', 'vmk/Sz9kbfvBuvcytXTWRI+uKygZ4N60c8p5J7oTlHs=', '3RJYKD5n6tJLYztHQZwJPL6OjIL2DjK9euWeOLm8', '2021-03-09 02:00:04');

-- --------------------------------------------------------

--
-- Struktur dari tabel `students`
--

CREATE TABLE `students` (
  `id` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `students`
--

INSERT INTO `students` (`id`, `name`, `address`, `created_at`, `updated_at`) VALUES
('F1HTIZHICKKF2RLQHVM8', 'Zavier Ferodova Al Fitroh', 'Surakarta\nIndonesia', '2021-02-17 06:18:02', '2021-03-02 14:40:28');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
