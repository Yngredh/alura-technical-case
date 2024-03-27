CREATE TABLE course (
    id VARCHAR(36) NOT NULL,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(20) NOT NULL,
    instructor_id VARCHAR(36) NOT NULL,
    description VARCHAR(255) NOT NULL,
    inactivation_date DATE NULL,
    creation_date DATE NOT NULL,
    is_available BOOLEAN NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (instructor_id) REFERENCES user(id)
);