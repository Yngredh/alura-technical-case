CREATE TABLE user (
    id VARCHAR(36) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    creation_date DATE NOT NULL,
    PRIMARY KEY (id)
);
