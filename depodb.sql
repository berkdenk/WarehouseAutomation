-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 30, 2021 at 05:00 PM
-- Server version: 8.0.18
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `depodb`
--

-- --------------------------------------------------------

--
-- Table structure for table `kullanicilar`
--

CREATE TABLE `kullanicilar` (
  `k_id` int(11) NOT NULL,
  `k_ad` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `k_sifre` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `k_yetki` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kullanicilar`
--

INSERT INTO `kullanicilar` (`k_id`, `k_ad`, `k_sifre`, `k_yetki`) VALUES
(1, 'berktug2', 'berktug1234', 1),
(3, 'denk', 'denk123', 0),
(4, 'denk22', 'denk1', 0),
(5, 'denkberktug', 'berktug123', 1),
(6, 'deneme', '2123', 0),
(7, 'deneme2', 'dene', 1);

-- --------------------------------------------------------

--
-- Table structure for table `urunler`
--

CREATE TABLE `urunler` (
  `u_id` int(11) NOT NULL,
  `u_barkod` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `u_ad` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `u_fiyat` int(11) NOT NULL,
  `u_stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `urunler`
--

INSERT INTO `urunler` (`u_id`, `u_barkod`, `u_ad`, `u_fiyat`, `u_stok`) VALUES
(1, '152369', 'Yemek', 44, 169),
(2, '15862', 'Çikolata', 1, 300),
(3, '1525', 'oval', 12, 150),
(4, '12', 'elma2', 1, 19),
(5, '15', 'as', 1, 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kullanicilar`
--
ALTER TABLE `kullanicilar`
  ADD PRIMARY KEY (`k_id`);

--
-- Indexes for table `urunler`
--
ALTER TABLE `urunler`
  ADD PRIMARY KEY (`u_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kullanicilar`
--
ALTER TABLE `kullanicilar`
  MODIFY `k_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `urunler`
--
ALTER TABLE `urunler`
  MODIFY `u_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
