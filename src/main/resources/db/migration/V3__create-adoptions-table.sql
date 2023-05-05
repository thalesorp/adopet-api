CREATE TABLE adoptions (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    pet_id INT NOT NULL REFERENCES pets(id),
    guardian_id INT NOT NULL REFERENCES users(id),
    adoption_date DATE NOT NULL
);
