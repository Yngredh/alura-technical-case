CREATE TABLE avaliation (
    id VARCHAR(36) NOT NULL,
    apprentice_id VARCHAR(36) NOT NULL,
    course_id VARCHAR(36) NOT NULL,
    value INT,
    message VARCHAR(255),
    avaliation_date DATE NOT NULL,
    FOREIGN KEY (apprentice_id) REFERENCES `user`(id),
    FOREIGN KEY (course_id) REFERENCES course (id)
);