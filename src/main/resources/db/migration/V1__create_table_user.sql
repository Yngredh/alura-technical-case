CREATE TABLE `user` (
  `name` VARCHAR(255) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  `creation_date` DATE NOT NULL,
  PRIMARY KEY (`email`,`username`)
);