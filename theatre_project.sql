-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 27, 2022 at 02:28 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bioskop_ndut`
--

-- --------------------------------------------------------

--
-- Table structure for table `theatre`
--

CREATE TABLE `theatre` (
  `id_theatre` int(11) NOT NULL,
  `judul` text NOT NULL,
  `sinopsis` text NOT NULL,
  `def_seats` int(11) NOT NULL,
  `ava_seats` int(11) NOT NULL,
  `weekday` int(11) NOT NULL,
  `weekend` int(11) NOT NULL,
  `poster` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `theatre`
--

INSERT INTO `theatre` (`id_theatre`, `judul`, `sinopsis`, `def_seats`, `ava_seats`, `weekday`, `weekend`, `poster`) VALUES
(1, 'Incantation', 'Six years ago, Li Ronan is cursed after breaking a religious taboo; now, she must protect her daughter from the consequences of her actions.', 9, 9, 30000, 50000, 'asset\\incantation.jfif'),
(2, 'Minions: The Rise of Gru', 'In the 1970s, young Gru tries to join a group of supervillains called the Vicious 6 after they oust their leader -- the legendary fighter Wild Knuckles. When the interview turns disastrous, Gru and his Minions go on the run with the Vicious 6 hot on their tails. Luckily, he finds an unlikely source for guidance -- Wild Knuckles himself -- and soon discovers that even bad guys need a little help from their friends.', 9, 9, 35000, 55000, 'asset/minion.jpg'),
(3, 'hahaha', 'Rini beserta bapak, ibu, dan ketiga adik laki-lakinya terpaksa tinggal di rumah neneknya di luar kota setelah rumah mereka dijual untuk pengobatan ibunya. Ibunya sakit selama 3,5 tahun tanpa diketahui penyebabnya, sehingga keluarganya mengalami krisis finansial.\n\nSetelah ibunya meninggal, ayah Rini pergi untuk sebuah pekerjaan. Teror mulai muncul. Sosok hantu perempuan menyerupai ibu mereka menghantui Rini dan adik-adiknya. Rini menemukan fakta bahwa di masa lalu keluarganya terlibat dalam suatu perjanjian hitam yang mengancam keselamatan keluarga mereka. Rini dan adik-adiknya harus membuka misteri masa lalu untuk tetap hidup.\n', 9, 9, 30000, 50000, 'asset\\hahaha.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `theater` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `judul` text NOT NULL,
  `jam` text NOT NULL,
  `total_pendapatan` int(11) NOT NULL,
  `total_penjualan` int(11) NOT NULL,
  `seat_taken` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `theater`, `tanggal`, `judul`, `jam`, `total_pendapatan`, `total_penjualan`, `seat_taken`) VALUES
(7, 1, '2022-07-27', 'Incantation', '15.00', 60000, 2, '000220000'),
(8, 1, '2022-08-03', 'Incantation', '12.30', 30000, 1, '000200000'),
(9, 1, '2022-08-04', 'Incantation', '15.00', 90000, 3, '002020020'),
(10, 2, '2022-08-04', 'Minions: The Rise of Gru', '15.00', 0, 0, '000000000'),
(11, 3, '2022-08-04', 'hahaha', '15.00', 0, 0, '000000000'),
(12, 3, '2022-08-04', 'hahaha', '12.30', 60000, 2, '220000000'),
(13, 1, '2022-08-16', 'Incantation', '15.00', 0, 0, '000000000'),
(14, 2, '2022-08-16', 'Minions: The Rise of Gru', '15.00', 0, 0, '000000000'),
(15, 3, '2022-08-16', 'hahaha', '15.00', 0, 0, '000000000'),
(16, 1, '2022-08-16', 'Incantation', '18.00', 0, 0, '000000000');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `role` text NOT NULL,
  `pass` text NOT NULL,
  `admin_pass` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `role`, `pass`, `admin_pass`) VALUES
(1, 'sasa', 'superadmin', 'hehe', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `theatre`
--
ALTER TABLE `theatre`
  ADD PRIMARY KEY (`id_theatre`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `theatre`
--
ALTER TABLE `theatre`
  MODIFY `id_theatre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
