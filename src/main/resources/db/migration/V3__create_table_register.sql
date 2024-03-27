CREATE TABLE register (
    id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    course_id VARCHAR(36) NOT NULL,
    registration_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES `user`(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
);