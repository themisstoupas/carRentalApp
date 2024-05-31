-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Εξυπηρετητής: 127.0.0.1
-- Χρόνος δημιουργίας: 29 Φεβ 2024 στις 21:07:09
-- Έκδοση διακομιστή: 10.4.32-MariaDB
-- Έκδοση PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Βάση δεδομένων: `car_rental`
--

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `cars`
--

CREATE TABLE `cars` (
  `car_id` int(11) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `model` varchar(50) NOT NULL,
  `daily_cost` decimal(10,2) NOT NULL,
  `cubic_capacity` int(11) NOT NULL,
  `seats` int(11) NOT NULL,
  `available_for_rent` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `cars`
--

INSERT INTO `cars` (`car_id`, `category_id`, `model`, `daily_cost`, `cubic_capacity`, `seats`, `available_for_rent`) VALUES
(4, 5, 'aygo', 50.00, 1100, 4, NULL),
(6, 7, 'ford', 300.00, 2000, 4, NULL),
(8, 9, 'toyota prius', 5.00, 1500, 4, NULL),
(9, 10, 'BMW X5', 450.00, 3000, 7, NULL),
(10, 11, 'yaris', 80.00, 1200, 5, NULL),
(11, 12, 'ford fiesta', 120.00, 1242, 5, NULL);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `car_categories`
--

CREATE TABLE `car_categories` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `car_categories`
--

INSERT INTO `car_categories` (`category_id`, `category_name`) VALUES
(1, 'SMALL'),
(2, 'SMALL'),
(3, 'SMALL'),
(4, 'SUV'),
(5, 'SMALL'),
(6, 'SMALL'),
(7, 'SMALL'),
(8, 'BIG'),
(9, 'ECONOMY'),
(10, 'SUV'),
(11, 'ECONOMY'),
(12, 'SMALL'),
(13, 'ECONOMY');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `customers`
--

CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `home_address` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `customers`
--

INSERT INTO `customers` (`customer_id`, `name`, `surname`, `gender`, `home_address`, `email`, `phone`) VALUES
(1, 'asa', 'asdas', 'asdas', 'asdas', 'asdasd', 'asdasd'),
(4, 'wqdqw', 'qwdwqdqw', 'Male', 'qwdqwdqw', 'qwdqwdqwdqwdq', 'qwdqwdqwdqw'),
(5, 'themis', 'them', 'Male', 'kalamarias 2', 'themis@themis.com', '6936936934'),
(6, 'sakis', 'rouvas', 'Female', 'ekalis 2', 'sakis@sakis.com', '6909090906'),
(8, 'xristos', 'getsios', 'Other', 'vamvaka', 'xristos@getsios43.com', '2314256897'),
(9, 'themis', 'oops', 'Male', 'ooops21', 'ooo@ooo.com', '231055585558'),
(12, 'themis', 'stoupas', 'Male', 'trilofos', 'themis@themis.com', '25252525');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `rentals`
--

CREATE TABLE `rentals` (
  `rental_id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `car_id` int(11) DEFAULT NULL,
  `days` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `rentals`
--

INSERT INTO `rentals` (`rental_id`, `customer_id`, `car_id`, `days`) VALUES
(3, 5, 4, 4),
(6, 5, 11, 4),
(7, 5, 8, 6);

--
-- Ευρετήρια για άχρηστους πίνακες
--

--
-- Ευρετήρια για πίνακα `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`car_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Ευρετήρια για πίνακα `car_categories`
--
ALTER TABLE `car_categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Ευρετήρια για πίνακα `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customer_id`);

--
-- Ευρετήρια για πίνακα `rentals`
--
ALTER TABLE `rentals`
  ADD PRIMARY KEY (`rental_id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `car_id` (`car_id`);

--
-- AUTO_INCREMENT για άχρηστους πίνακες
--

--
-- AUTO_INCREMENT για πίνακα `cars`
--
ALTER TABLE `cars`
  MODIFY `car_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT για πίνακα `car_categories`
--
ALTER TABLE `car_categories`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT για πίνακα `customers`
--
ALTER TABLE `customers`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT για πίνακα `rentals`
--
ALTER TABLE `rentals`
  MODIFY `rental_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Περιορισμοί για άχρηστους πίνακες
--

--
-- Περιορισμοί για πίνακα `cars`
--
ALTER TABLE `cars`
  ADD CONSTRAINT `cars_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `car_categories` (`category_id`);

--
-- Περιορισμοί για πίνακα `rentals`
--
ALTER TABLE `rentals`
  ADD CONSTRAINT `rentals_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  ADD CONSTRAINT `rentals_ibfk_2` FOREIGN KEY (`car_id`) REFERENCES `cars` (`car_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
