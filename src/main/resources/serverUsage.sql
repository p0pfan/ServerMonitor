CREATE TABLE `serverdata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serverIP` varchar(20) NOT NULL,
  `cpuUsage` double DEFAULT NULL,
  `memUsage` double DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8
