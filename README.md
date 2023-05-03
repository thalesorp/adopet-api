# AdoPet

Project developed for the 6th back-end challenge given by [Alura](https://www.alura.com.br/challenges/back-end-6).

**AdoPet** is a web application that connects would-be pet guardians with NGOs working to find a new home for animals for adoption.

This repository contains the source code of the REST API developed in Java with the Spring Boot 3 framework, which provides services to the AdoPet application. For the front end part of the project, please refer to the [AdoPet front end repository](https://github.com/sucodelarangela/adopet) by [Angela Caldas](https://github.com/sucodelarangela).


## 💡 Features

The AdoPet API provides the following endpoints for interacting with the platform:

- Guardians CRUD:

    - `POST /guardians`: Creates a new guardian. Request body example:

        ```JSON
        {
            "name": "Fulano",
            "email": "fulano@email.com",
            "password": "abcd1234",
            "passwordConfirmation": "abcd1234"
        }
        ```

        - `201 Created`: The guardian was created sucessfully. The response body will contain a JSON with the newly created guardian data, and the response header will contain a location indicating the URL of the newly created resource. Response body example:

            ```JSON
            {
                "id": 1,
                "name": "Fulano",
                "email": "fulano@email.com",
                "phone": null,
                "about": null,
                "role": "GUARDIAN",
                "address": {
                    "street": null,
                    "complement": null,
                    "city": null,
                    "state": null,
                    "postalCode": null,
                    "country": null
                }
            }
            ```

        - `400 Bad Request`: If the password and password confirmation fields do not match.

    - `GET /guardians`: Returns a list of all guardians.

        - `200 OK`: Returns a pageable object containing a list of guardians data.

        - `204 No Content`: If there are no guardians in the database.

    - `GET /guardians/{id}`: Returns a specific guardian by an identifier.

        - `200 OK`: Returns a JSON containing the requested guardian data.

        - `404 Not Found`: If the guardian with the specified identifier does not exist.

    - `PUT /guardians`: Updates an existing guardian.

        - `200 OK` : Returns a JSON containing the updated guardian data.

        - `404 Not Found`: If the guardian with the specified identifier does not exist.

    - `DELETE /guardians/{id}`: Deletes an existing guardian.

        - `200 OK`: The guardian was successfully deleted.

        - `404 Not Found`: If the guardian with the specified identifier does not exist.

- Shelters CRUD:

    - `POST /shelters`: Creates a new shelter. Request body example:

        ```
        {
            "name": "Pet Rescue",
            "email": "petrescue@email.com",
            "password": "1234abcd",
            "passwordConfirmation": "1234abcd"
        }
        ```

        - `201 Created`: The shelter was created sucessfully. The response body will contain a JSON with the newly created shelter data, and the response header will contain a location indicating the URL of the newly created resource. Response body example:

            ```
            {
                "id": 2,
                "name": "Pet Rescue",
                "email": "petrescue@email.com",
                "phone": null,
                "about": null,
                "role": "SHELTER",
                "address": {
                    "street": null,
                    "complement": null,
                    "city": null,
                    "state": null,
                    "postalCode": null,
                    "country": null
                }
            }
            ```

        - `400 Bad Request`: If the password and password confirmation fields do not match.

    - `GET /shelters`: Returns a list of all shelters.

        - `200 OK`: Returns a pageable object containing a list of shelters data.

        - `204 No Content`: If there are no shelters in the database.

    - `GET /shelters/{id}`: Returns a specific shelter by an identifier.

        - `200 OK`: Returns a JSON containing the requested shelter data.

        - `404 Not Found`: If the shelter with the specified identifier does not exist.

    - `PUT /shelters`: Updates an existing shelter.

        - `200 OK` : Returns a JSON containing the updated shelter data.

        - `404 Not Found`: If the shelter with the specified identifier does not exist.

    - `DELETE /shelters/{id}`: Deletes an existing shelter.

        - `200 OK`: The shelter was successfully deleted.

        - `404 Not Found`: If the shelter with the specified identifier does not exist.


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
