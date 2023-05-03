CREATE TABLE pets (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL,
    size VARCHAR(20),
    temperament VARCHAR(255),
    adopted BIT NOT NULL DEFAULT 0,
    imageURL VARCHAR(255),
    shelter_id INTEGER NOT NULL,
    FOREIGN KEY (shelter_id) REFERENCES users(id)
);
