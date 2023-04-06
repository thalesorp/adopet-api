# AdoPet

Project developed for the 6th back-end challenge given by [Alura](https://www.alura.com.br/challenges/back-end-6).

**AdoPet** is a web application that connects would-be pet guardians with NGOs working to find a new home for animals for adoption.

This repository contains the source code of the REST API developed in Java with the Spring Boot 3 framework, which provides services to the AdoPet application. For the front end part of the project, please refer to the [AdoPet front end repository](https://github.com/sucodelarangela/adopet) by [Angela Caldas](https://github.com/sucodelarangela).


## 💡 Features

The AdoPet API provides the following endpoints for interacting with the platform:

- `POST /users`: Creates a new user.

    - `200 OK`: Returns a JSON containing the newly created user data.

    - `400 Bad Request`: If the password and password confirmation fields do not match.

- `GET /users`: Returns a list of all users.

    - `200 OK`: Returns a pageable object containing a list of users data.

    - `204 No Content`: If there are no users in the database.

- `GET /users/{id}`: Returns a specific user by an identifier.

    - `200 OK`: Returns a JSON containing the requested user data.

    - `404 Not Found`: If the user with the specified identifier does not exist.

- `PUT /users`: Updates an existing user.

    - `200 OK` : Returns a JSON containing the updated user data.

    - `404 Not Found`: If the user with the specified identifier does not exist.

- `DELETE /users/{id}`: Deletes an existing user.

    - `200 OK`: If the user was deleted successfully.

    - `404 Not Found`: If the user with the specified identifier does not exist.


## ⚙️ How to Use

To run the project locally, you will need to have JDK 20 installed on your machine. Then, simply clone the repository and run the `./mvnw spring-boot:run` command in the project root. The REST API will be available at `http://localhost:8080`.

To make requests to the API endpoints, it's recommend using [Postman](https://www.postman.com/) or a similar tool.


## 🐛 Contribution

Contributions are welcome! If you would like to contribute to the project, follow these steps:

1. Fork the project.
2. Create a branch with your changes: `git checkout -b my-changes`.
3. Commit your changes: `git commit -m 'My changes'`.
4. Push to your fork: `git push origin my-changes`.
5. Create a pull request.


## License

This project is licensed under the MIT License - see the LICENSE file for details.

---

Developed with 🫰🏽 by @ThalesORP 🦕
