CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    phone CHAR(11),
    about VARCHAR(200),
    role VARCHAR(10) NOT NULL,

    street VARCHAR(100),
    complement VARCHAR(100),
    city VARCHAR(100),
    state CHAR(2),
    postal_code CHAR(8),
    country VARCHAR(100)
);
