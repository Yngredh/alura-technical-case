CREATE TABLE course (
    name VARCHAR(255) NOT NULL,
    code VARCHAR(20) NOT NULL,
    instructor_email VARCHAR(255) NOT NULL,
    instructor_username VARCHAR(20) NOT NULL,
    description VARCHAR(255) NOT NULL,
    inactivation_date DATE NULL,
    creation_date DATE NOT NULL,
    is_available BOOLEAN NOT NULL,
    PRIMARY KEY (code),
    FOREIGN KEY (instructor_email, instructor_username) REFERENCES user(email, username)
);