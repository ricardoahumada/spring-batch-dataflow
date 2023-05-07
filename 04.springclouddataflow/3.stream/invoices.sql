-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 25, 2023 at 02:27 PM
-- Server version: 5.7.11
-- PHP Version: 5.6.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `invoices`
--
CREATE DATABASE IF NOT EXISTS `invoices` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `invoices`;

-- --------------------------------------------------------

--
-- Table structure for table `propietario`
--

DROP TABLE IF EXISTS `propietario`;
CREATE TABLE `propietario` (
  `pid` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `seccion` int(11) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `propietario`
--

INSERT INTO `propietario` (`pid`, `email`, `nombre`, `seccion`, `telefono`) VALUES
(1, 'mkasbye0@fema.gov', 'Morie Kasbye', 8, '255-374-9234'),
(2, 'mlouthe1@webmd.com', 'Mandy Louthe', 9, '891-841-1605'),
(3, 'jwithull2@gnu.org', 'Jane Withull', 3, '973-842-1254'),
(4, 'dstrognell3@about.com', 'Dewey Strognell', 9, '700-643-1673'),
(5, 'rtoal4@usda.gov', 'Ramonda Toal', 3, '671-588-4993'),
(6, 'efrancino5@examiner.com', 'Eberto Francino', 1, '641-437-7808'),
(7, 'dgoodson6@yolasite.com', 'Dorisa Goodson', 3, '725-296-6585'),
(8, 'jjuniper7@msn.com', 'Jana Juniper', 3, '191-523-3769'),
(9, 'ybaud8@cocolog-nifty.com', 'Yanaton Baud', 7, '966-964-2797'),
(10, 'fallicock9@drupal.org', 'Francyne Allicock', 2, '755-424-7722'),
(11, 'fchevaliera@cornell.edu', 'Fletch Chevalier', 6, '663-849-3628'),
(12, 'rmittenb@discuz.net', 'Rosalyn Mitten', 10, '883-559-6864'),
(13, 'dwalduckc@cbc.ca', 'Dulciana Walduck', 3, '505-433-8664'),
(14, 'gpearsond@dagondesign.com', 'Garland Pearson', 3, '562-899-3443'),
(15, 'rtathere@businessweek.com', 'Rodney Tather', 6, '792-371-8524'),
(16, 'bscottsf@4shared.com', 'Bond Scotts', 7, '907-651-8105'),
(17, 'lprosserg@hud.gov', 'Lawton Prosser', 4, '412-101-9024'),
(18, 'hhobgenh@myspace.com', 'Hanson Hobgen', 1, '709-533-1816'),
(19, 'mtonni@tiny.cc', 'Myrilla Tonn', 4, '699-656-4555'),
(20, 'amacailinej@pen.io', 'Angelico MacAiline', 9, '655-150-1970'),
(21, 'dmcguinleyk@msu.edu', 'Dame McGuinley', 4, '739-121-0093'),
(22, 'fjoannetl@surveymonkey.com', 'Frayda Joannet', 10, '792-483-4082'),
(23, 'ksperlingm@toplist.cz', 'Kareem Sperling', 4, '819-519-7733'),
(24, 'sgerbn@squarespace.com', 'Sal Gerb', 8, '716-479-5949'),
(25, 'cjestecoo@gnu.org', 'Cleveland Jesteco', 5, '506-886-6856'),
(26, 'landryszczakp@t-online.de', 'Lorena Andryszczak', 3, '423-595-5050'),
(27, 'oelsayq@addthis.com', 'Ondrea Elsay', 10, '284-595-0442'),
(28, 'rswabyr@nasa.gov', 'Rozina Swaby', 4, '418-275-5133'),
(29, 'ghammers@va.gov', 'Giulia Hammer', 7, '928-590-3129'),
(30, 'siddienst@sogou.com', 'Shaun Iddiens', 4, '352-969-0111'),
(31, 'msandeveru@dailymail.co.uk', 'Morena Sandever', 6, '736-685-4309'),
(32, 'jrendlev@microsoft.com', 'Jens Rendle', 10, '477-656-5665'),
(33, 'slearyw@businessinsider.com', 'Selene Leary', 7, '689-120-9572'),
(34, 'tyurkinx@timesonline.co.uk', 'Tarra Yurkin', 6, '827-190-9713'),
(35, 'planfranchiy@skyrock.com', 'Peadar Lanfranchi', 2, '812-591-6238'),
(36, 'mflemingz@blinklist.com', 'Marmaduke Fleming', 3, '104-336-8508'),
(37, 'psneath10@aboutads.info', 'Phyllida Sneath', 8, '843-403-7231'),
(38, 'cgrabert11@theatlantic.com', 'Cortie Grabert', 8, '808-684-2161'),
(39, 'dbolles12@disqus.com', 'Deva Bolles', 4, '131-981-4584'),
(40, 'alinturn13@desdev.cn', 'Abey Linturn', 3, '340-927-0037'),
(41, 'bdrewett14@latimes.com', 'Bernette Drewett', 2, '224-182-1677'),
(42, 'kroper15@cloudflare.com', 'Ketti Roper', 10, '805-100-5906'),
(43, 'omerriton16@flavors.me', 'Owen Merriton', 10, '539-203-7252'),
(44, 'rdericot17@discuz.net', 'Rubetta Dericot', 4, '934-376-1756'),
(45, 'ksucre18@hp.com', 'Kesley Sucre', 5, '110-167-9556'),
(46, 'lgirkins19@pen.io', 'Lisabeth Girkins', 10, '458-264-5880'),
(47, 'ko1a@goo.ne.jp', 'Kurt O\' Molan', 9, '815-303-5565'),
(48, 'amccallister1b@sogou.com', 'Amabelle McCallister', 5, '749-961-2426'),
(49, 'bpiscopiello1c@soup.io', 'Braden Piscopiello', 3, '584-992-3455'),
(50, 'cbaynom1d@ovh.net', 'Chicky Baynom', 9, '997-338-7867'),
(51, 'hkillbey1e@go.com', 'Hilly Killbey', 7, '831-214-7499'),
(52, 'mwhorlton1f@opera.com', 'Micheil Whorlton', 6, '122-668-7779'),
(53, 'mrought1g@comcast.net', 'Moe Rought', 2, '117-349-2793'),
(54, 'mjearum1h@trellian.com', 'Myrlene Jearum', 9, '935-427-4667'),
(55, 'vmcarthur1i@altervista.org', 'Vikki McArthur', 6, '116-234-4205'),
(56, 'delby1j@diigo.com', 'Daron Elby', 8, '561-239-9723'),
(57, 'cgebbie1k@tamu.edu', 'Clarita Gebbie', 10, '853-918-5916'),
(58, 'ahupka1l@wikimedia.org', 'Aleta Hupka', 3, '861-107-7929'),
(59, 'ecrawley1m@prlog.org', 'Eduard Crawley', 6, '287-551-2176'),
(60, 'hpennycock1n@blogtalkradio.com', 'Harper Pennycock', 2, '223-495-2000'),
(61, 'wmarkham1o@rediff.com', 'Wilt Markham', 6, '935-183-0185'),
(62, 'gtow1p@amazon.com', 'Granny Tow', 1, '181-542-2730'),
(63, 'cwalford1q@google.co.uk', 'Clint Walford', 3, '366-416-7838'),
(64, 'dconroy1r@blogger.com', 'Dani Conroy', 3, '976-129-9493'),
(65, 'ejosebury1s@cbc.ca', 'Elisha Josebury', 8, '165-209-9259'),
(66, 'rlawleff1t@yellowbook.com', 'Roldan Lawleff', 1, '303-297-1042'),
(67, 'bferebee1u@mozilla.com', 'Berny Ferebee', 9, '614-954-8267'),
(68, 'sjills1v@cyberchimps.com', 'Stefanie Jills', 3, '320-170-5405'),
(69, 'mferres1w@accuweather.com', 'Maritsa Ferres', 1, '253-484-7877'),
(70, 'bdrage1x@cbsnews.com', 'Brooks Drage', 4, '310-955-1234'),
(71, 'kborless1y@themeforest.net', 'Kile Borless', 10, '254-882-9034'),
(72, 'gmayston1z@si.edu', 'Gusty Mayston', 1, '268-383-2977'),
(73, 'jseamans20@google.ru', 'Jordon Seamans', 4, '368-872-3850'),
(74, 'escoggans21@abc.net.au', 'Eddie Scoggans', 3, '658-439-9320'),
(75, 'hbarnwell22@360.cn', 'Heddie Barnwell', 9, '662-176-8706'),
(76, 'citshak23@tinyurl.com', 'Clarie Itshak', 3, '299-102-6677'),
(77, 'oduligall24@fema.gov', 'Oralia Duligall', 8, '577-236-8652'),
(78, 'jgraalman25@accuweather.com', 'Jacinda Graalman', 2, '223-457-2495'),
(79, 'fmaro26@dailymotion.com', 'Frederik Maro', 10, '618-409-7223'),
(80, 'cdawid27@wordpress.com', 'Charlie Dawid', 8, '232-517-9465'),
(81, 'gmcgeady28@ehow.com', 'Galina McGeady', 8, '403-646-0412'),
(82, 'bbachman29@dailymail.co.uk', 'Betti Bachman', 10, '750-490-5486'),
(83, 'fdriscoll2a@usgs.gov', 'Faustina Driscoll', 10, '801-828-2860'),
(84, 'dhenlon2b@weebly.com', 'Derward Henlon', 2, '715-715-8586'),
(85, 'clinsay2c@nydailynews.com', 'Claybourne Linsay', 8, '264-335-7985'),
(86, 'jtrebble2d@drupal.org', 'Jacquette Trebble', 7, '675-754-9398'),
(87, 'ktoward2e@google.cn', 'Karilynn Toward', 6, '754-644-2252'),
(88, 'tmegroff2f@nbcnews.com', 'Thor Megroff', 1, '890-187-4877'),
(89, 'ttomalin2g@alexa.com', 'Trev Tomalin', 7, '860-303-4639'),
(90, 'cmartignoni2h@miitbeian.gov.cn', 'Camella Martignoni', 3, '992-563-9517'),
(91, 'aroscher2i@webnode.com', 'Adele Roscher', 5, '840-778-7378'),
(92, 'eumfrey2j@springer.com', 'Eyde Umfrey', 5, '934-247-2763'),
(93, 'ikenway2k@mashable.com', 'Ikey Kenway', 2, '154-726-5391'),
(94, 'lmacloughlin2l@purevolume.com', 'Link MacLoughlin', 4, '612-598-2999'),
(95, 'dsowersby2m@noaa.gov', 'Dosi Sowersby', 10, '112-915-2552'),
(96, 'dbeech2n@ask.com', 'Donielle Beech', 8, '354-438-6529'),
(97, 'dgrishinov2o@e-recht24.de', 'Danette Grishinov', 8, '360-232-5008'),
(98, 'wdautry2p@whitehouse.gov', 'Winnie Dautry', 9, '176-933-8835'),
(99, 'mtomanek2q@ebay.com', 'Mic Tomanek', 1, '884-834-0614'),
(100, 'mmuccino2r@time.com', 'Marietta Muccino', 7, '737-209-1271'),
(113, 'N@Y.com', 'Nedro', 4, '666222333');

-- --------------------------------------------------------

--
-- Table structure for table `recibo`
--

DROP TABLE IF EXISTS `recibo`;
CREATE TABLE `recibo` (
  `id` bigint(20) NOT NULL,
  `base_imponible` double NOT NULL,
  `cantidad` int(11) NOT NULL,
  `direccion_contacto` varchar(255) DEFAULT NULL,
  `direccion_envio` varchar(255) DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  `fecha_emision` date DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `impuestos` float NOT NULL,
  `nombre_contacto` varchar(255) DEFAULT NULL,
  `nombre_producto` varchar(255) DEFAULT NULL,
  `precio_unitario` float NOT NULL,
  `total` double NOT NULL,
  `valido` bit(1) NOT NULL,
  `propietario` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `propietario`
--
ALTER TABLE `propietario`
  ADD PRIMARY KEY (`pid`);

--
-- Indexes for table `recibo`
--
ALTER TABLE `recibo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkj4tqbe8rrp9ojfojjiwmhd4w` (`propietario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `propietario`
--
ALTER TABLE `propietario`
  MODIFY `pid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=114;
--
-- AUTO_INCREMENT for table `recibo`
--
ALTER TABLE `recibo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `recibo`
--
ALTER TABLE `recibo`
  ADD CONSTRAINT `FKkj4tqbe8rrp9ojfojjiwmhd4w` FOREIGN KEY (`propietario`) REFERENCES `propietario` (`pid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


USE `mysql`;
GRANT ALL PRIVILEGES ON invoices.* TO 'invoices_user'@'%' IDENTIFIED BY 'inv123' WITH GRANT OPTION