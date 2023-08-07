DROP TABLE if EXISTS `Article`;
DROP TABLE if EXISTS `User`;

DROP TABLE if EXISTS `File`;
DROP TABLE if EXISTS `Terms`;

CREATE TABLE `User`(
                       `uid`		VARCHAR(20) PRIMARY KEY,
                       `password`    VARCHAR(255),
                       `name`		VARCHAR(20),
                       `nick`		VARCHAR(20) UNIQUE,
                       `email`		VARCHAR(50) UNIQUE,
                       `hp`			CHAR(13) UNIQUE,
                       `role`		VARCHAR(20) DEFAULT 'USER',
                       `zip`		CHAR(5),
                       `addr1`		VARCHAR(255),
                       `addr2`		VARCHAR(255),
                       `reg_ip`		VARCHAR(100),
                       `reg_date`	DATETIME,
                       `leave_date`	DATETIME
);
CREATE TABLE `Article` (
                           `no`			INT AUTO_INCREMENT PRIMARY KEY,
                           `title`		VARCHAR(255),
                           `content`	TEXT NOT null,
                           `hit`			INT DEFAULT 0,
                           `writer`		VARCHAR(20) NOT null,
                           `regip`		VARCHAR(100) NOT null,
                           `rdate`		DATETIME NOT NULL,
                           FOREIGN KEY(`writer`) REFERENCES `user`(`uid`)
);
CREATE TABLE `File` (
                        `fno`			INT AUTO_INCREMENT PRIMARY key,
                        `ano`			INT NOT null,
                        `origName`	VARCHAR(255) NOT null,
                        `newName`	VARCHAR(255) NOT null,
                        `download`	INT DEFAULT 0,
                        `rdate`		DATETIME NOT NULL,
                        FOREIGN KEY(`ano`) REFERENCES `Article`(`no`)
);
CREATE TABLE `Terms` (
                         `terms`		TEXT NOT null,
                         `privacy`	TEXT NOT null
);