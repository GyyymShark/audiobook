CREATE TABLE `myceleb`.`book` (
                                  `book_id` BIGINT NOT NULL,
                                  `title` VARCHAR(45) NOT NULL,
                                  `author` VARCHAR(45) NULL,
                                  `contents` TEXT NULL,
                                  `views` BIGINT NOT NULL,
                                  PRIMARY KEY (`book_id`));
