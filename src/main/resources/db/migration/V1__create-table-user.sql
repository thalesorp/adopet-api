CREATE TABLE users (
    id BIGINT NOT NULL auto_increment PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);
