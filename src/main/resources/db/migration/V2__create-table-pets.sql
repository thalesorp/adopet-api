CREATE TABLE pets (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    size VARCHAR(20) NOT NULL,
    temperament VARCHAR(255) NOT NULL,
    adopted BIT NOT NULL DEFAULT 0,
    imageURL VARCHAR(255),
    shelter_id INT NOT NULL,
    FOREIGN KEY (shelter_id) REFERENCES users(id)
);
