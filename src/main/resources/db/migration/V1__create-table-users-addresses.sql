CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone CHAR(11),
    about VARCHAR(255),
    role VARCHAR(10) NOT NULL,

    street VARCHAR(255),
    complement VARCHAR(255),
    city VARCHAR(255),
    state CHAR(255),
    postal_code CHAR(8),
    country VARCHAR(255)
);
