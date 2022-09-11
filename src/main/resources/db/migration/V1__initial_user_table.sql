CREATE TABLE IF NOT EXISTS `users`
(
    `user_id` varchar(255) NOT NULL,
    `name`    varchar(255) NOT NULL,
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;