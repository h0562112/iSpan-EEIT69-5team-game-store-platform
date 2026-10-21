-- 由 JPA Entity (com.ispan.eeit69.model) 反推還原的資料庫結構
-- 欄位名稱沿用 Java 屬性名 (PhysicalNamingStrategyStandardImpl，不轉 snake_case)
-- 只含結構，不含原本的資料

CREATE DATABASE IF NOT EXISTS `5team` DEFAULT CHARACTER SET utf8mb4;
USE `5team`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `users_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB;

CREATE TABLE `avatar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `picture` longtext,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB;

CREATE TABLE `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employeeId` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `startDay` date DEFAULT NULL,
  `picture` longtext,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `itemCategory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `itemId` varchar(255) DEFAULT NULL,
  `mp4` varchar(255) DEFAULT NULL,
  `itemStock` int DEFAULT NULL,
  `itemName` varchar(255) DEFAULT NULL,
  `itemPrice` int DEFAULT NULL,
  `itempicture` longtext,
  `itempicture1` longtext,
  `itempicture2` longtext,
  `itemDescription` varchar(255) DEFAULT NULL,
  `createTime` datetime(6) DEFAULT NULL,
  `lastTime` datetime(6) DEFAULT NULL,
  `discount` varchar(255) DEFAULT NULL,
  `categoryId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`categoryId`) REFERENCES `itemCategory` (`id`)
) ENGINE=InnoDB;

CREATE TABLE `cart` (
  `cartId` int NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `picture` longtext,
  `userId` int DEFAULT NULL,
  `itemId` int DEFAULT NULL,
  PRIMARY KEY (`cartId`),
  FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  FOREIGN KEY (`itemId`) REFERENCES `items` (`id`)
) ENGINE=InnoDB;

CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `reviewText` varchar(255) DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `creationTime` datetime(6) DEFAULT NULL,
  `itemsId` int DEFAULT NULL,
  `userId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`itemsId`) REFERENCES `items` (`id`),
  FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB;

CREATE TABLE `friends` (
  `friendsID` int NOT NULL AUTO_INCREMENT,
  `userID1` int DEFAULT NULL,
  `userID2` int DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`friendsID`)
) ENGINE=InnoDB;

CREATE TABLE `friendsMessages` (
  `messageID` int NOT NULL AUTO_INCREMENT,
  `senderUserid` int NOT NULL,
  `receiverUserid` int NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `timeStamp` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`messageID`)
) ENGINE=InnoDB;

CREATE TABLE `player_Games_library` (
  `playerGameID` int NOT NULL AUTO_INCREMENT,
  `playerID` int DEFAULT NULL,
  `gameID` int DEFAULT NULL,
  PRIMARY KEY (`playerGameID`)
) ENGINE=InnoDB;


CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int DEFAULT NULL,
  `subtotal` int DEFAULT NULL,
  `discountAmount` int DEFAULT NULL,
  `totalAmount` int DEFAULT NULL,
  `couponCode` varchar(255) DEFAULT NULL,
  `createTime` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `order_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orderId` int DEFAULT NULL,
  `itemId` int DEFAULT NULL,
  `itemName` varchar(255) DEFAULT NULL,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB;

CREATE TABLE `coupon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `discountType` varchar(10) DEFAULT NULL,
  `discountValue` int DEFAULT NULL,
  `minSpend` int DEFAULT NULL,
  `expireTime` datetime(6) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `createTime` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`code`)
) ENGINE=InnoDB;

CREATE TABLE `user_coupon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int DEFAULT NULL,
  `couponId` int DEFAULT NULL,
  `used` bit(1) DEFAULT NULL,
  `usedTime` datetime(6) DEFAULT NULL,
  `receivedTime` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`userId`, `couponId`),
  FOREIGN KEY (`couponId`) REFERENCES `coupon` (`id`)
) ENGINE=InnoDB;

CREATE TABLE `news` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` longtext,
  `createTime` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;
