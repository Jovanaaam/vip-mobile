-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 29, 2020 at 11:18 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fpis`
--

-- --------------------------------------------------------

--
-- Table structure for table `valuta`
--

DROP TABLE IF EXISTS `valuta`;
CREATE TABLE IF NOT EXISTS `valuta` (
  `idvalute` int(11) NOT NULL AUTO_INCREMENT,
  `nazivvalute` varchar(200) NOT NULL,
  PRIMARY KEY (`idvalute`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `valuta`
--

INSERT INTO `valuta` (`idvalute`, `nazivvalute`) VALUES
(1, 'RSD'),
(2, 'EUR');

-- --------------------------------------------------------

--
-- Table structure for table `vrstakredita`
--

DROP TABLE IF EXISTS `vrstakredita`;
CREATE TABLE IF NOT EXISTS `vrstakredita` (
  `idvrstekredita` int(11) NOT NULL,
  `nazivvrstekredita` varchar(200) NOT NULL,
  `idvalute` int(11) NOT NULL,
  PRIMARY KEY (`idvrstekredita`),
  KEY `idvalute` (`idvalute`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vrstakredita`
--

INSERT INTO `vrstakredita` (`idvrstekredita`, `nazivvrstekredita`, `idvalute`) VALUES
(1, 'Komercijalni', 1),
(5, 'Potrosacki', 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
