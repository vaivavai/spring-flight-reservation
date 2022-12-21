# Spring Boot Flight Reservation

## Description

This is an Spring Boot application that is used as REST API to create flights, flight reservations using airports, users, flight models.

## Getting Started

### Dependencies

* PostgreSQL
* Maven 3.0+
* Java 17

### Configuring database locally
* Create a postgres database named **_postgres_**. Default port is **_5432_** on localhost
* Open src/main/resources/application.properties file and change the spring datasource username and password as per your PostgreSQL installation.
* Prefill data in tables by running initial_data.sql script in src/main/resources using your preferred DB client.

### Installing
* To prepare maven dependancies
```
mvn clean install
```

### Executing program

* To run the application
```
mvn spring-boot:run
```

## Authors
Vaiva Vaitkeviciute

 [My LinkedIn](https://www.linkedin.com/in/vaiva-v-628461237/)

## Explore Rest APIs

The app defines following CRUD APIs.

### Airports

| Method | Url                          | Decription                                                     | Sample Valid Request Body | Example Response    |
|--------|------------------------------|----------------------------------------------------------------|---------------------------|---------------------|
| GET    | api/v1/airports              | Get all airports                                               |                           |                     |
| GET    | api/v1/airports/{airportId}  | Get airports by ID                                             |                           | [JSON](#getairport) |
| POST   | api/v1/airports              | Add new airport (If airport name does not already exist in DB) | [JSON](#airportcreate)    |                     |
| PUT    | api/v1/airports/{airportId}  | Update airport                                                 | [JSON](#airportupdate)    |                     |
| DELETE | api/v1/airports/{airportId}  | Delete airport                                                 |                           |                     |

### Flights

| Method         | Url                                    | Description                                           | Sample Valid Request Body | Example Response   |
|----------------|----------------------------------------|-------------------------------------------------------|--------------------------|--------------------|
| GET            | api/v1/flights                         | Get all flights                                       |                          |                    |
| _Request param_  | originAirportId                        | Can filter by origin airport ID                       |                          |                    |
| _Request param_ | destinationAirportId                   | Can filter by destination airport ID                  |                          |                    |
| _Request param_ | originAirportId & destinationAirportId | Can filter by both origin and destination airport IDs |                          |                    |
| GET            | api/v1/flights/{flightId}              | Get flight by ID                                      |                          | [JSON](#getflight) |
| POST           | api/v1/flights                         | Add new flight                                        | [JSON](#flightcreate)    |                    |
| PUT            | api/v1/flights/{flightId}              | Update flight                                         | [JSON](#flightupdate)    |                    |
| DELETE         | api/v1/flights/{flightId}              | Delete flight                                         |                          |                    |

### Reservations

| Method        | Url                                  | Description                                                                                | Sample Valid Request Body  | Example Response        |
|---------------|--------------------------------------|--------------------------------------------------------------------------------------------|----------------------------|-------------------------|
| GET           | api/v1/reservations                  | Get all flight reservations                                                                |                            |                         |
| _Request param_ | flightId                             | Can filter by flight ID                                                                    |                            |                         |
| _Request param_ | userId                               | Can filter by user ID                                                                      |                            |                         |
| _Request param_ | flightId & userId                    | Can filter by both flight and user IDs                                                     |                            | |
| GET           | api/v1/reservations/{reservationId}  | Get reservation by ID                                                                      |                            |   [JSON](#getreservation)                       |
| POST          | api/v1/reservations                  | Add new reservation  (if reservation with the same flight and user does not already exist) | [JSON](#reservationcreate) |                         |
| PUT           | api/v1/reservations/{reservationId}  | Update reservation                                                                         | [JSON](#reservationupdate) |                         |
| DELETE        | api/v1/reservations/{reservationId}  | Delete reservation                                                                         |                            |                         |

### App Users

| Method | Url                     | Description    | Sample Valid Request Body | Example Response |
| ------ |-------------------------|----------------|---------------------------|------------------|
| GET    | api/v1/users            | Get all users  |                           |                  |
| GET    | api/v1/users/{userid}   | Get user by ID |                           | [JSON](#getuser) |
| POST   | api/v1/users            | Add new user   | [JSON](#usercreate)       |                  |
| PUT    | api/v1/users/{userid}   | Update user | [JSON](#userupdate)       |                  |
| DELETE | api/v1/users/{userid}   | Delete user    |                           |                  |

Test them using postman or any other rest client.

## Sample Valid JSON Request Body's


##### <a id="airportcreate">Create Airport -> /api/v1/airports</a>
```json
{
  "name": "ATH",
  "country": "Grece",
  "city": "Athens"
}
```

##### <a id="airportupdate">Update Airport -> /api/v1/airports/{airportId}</a>
```json
{
  "name": "ATH",
  "country": "Greece",
  "city": "Athens"
}
```


##### <a id="flightcreate">Create Flight -> /api/v1/flights</a>
```json
{
  "originAirportId": "492045d1-2683-4d0a-a60b-0ae1332ce3e8",
  "destinationAirportId": "5c9c9e83-8667-4d6e-9f6b-8f3db7210686",
  "departureDate": "2023-10-15T12:30:00",
  "price": 220.5
}
```


##### <a id="flightupdate">Update Flight -> /api/v1/flights/{flightId}</a>
```json
{
  "originAirportId": "492045d1-2683-4d0a-a60b-0ae1332ce3e8",
  "destinationAirportId": "5c9c9e83-8667-4d6e-9f6b-8f3db7210686",
  "departureDate": "2023-10-15T12:30:00",
  "price": 200.0
}
```

##### <a id="reservationcreate">Create Reservation -> /api/v1/reservations</a>
```json
{
  "flightId": "6ae7d30f-a7ba-4e36-87f7-2689a2b72f1e",
    "userId": "7a1bfe69-a08c-4f35-affe-ccb45de1123a"

  }
```

##### <a id="reservationupdate">Update Reservation -> /api/v1/reservations/{reservationId}</a>
```json
{
  "flightId": "04bef472-bedc-492e-a2f9-765156cf7a9f",
  "userId": "7a1bfe69-a08c-4f35-affe-ccb45de1123a"
}
```

##### <a id="usercreate">Create User -> /api/v1/users</a>
```json
{
  "name": "Tom",
  "password": "password",
  "email": "tom@gmail.com",
  "appUserRole": "USER"
}
```
##### <a id="userupdate">Update User -> /api/v1/users/{userId}</a>
```json
{
  "name": "Tom",
  "password": "updatedPassword",
  "email": "tom@gmail.com",
  "appUserRole": "USER"
}
```



## Sample JSON Response Body's


##### <a id="getairport">Get Airport by ID -> /api/v1/airports/3ba74066-2171-4458-a917-f5eca679c6b5</a>
```json
{
  "id": "3ba74066-2171-4458-a917-f5eca679c6b5",
  "name": "VNO",
  "country": "Lithuania",
  "city": "Vilnius"
}
```

##### <a id="getflight">Get Flight by ID -> /api/v1/flights/4f6dfe69-a08c-4f35-affe-ccb45de1672c</a>
```json
{
  "id": "4f6dfe69-a08c-4f35-affe-ccb45de1672c",
  "originAirport":{
    "id": "3ba74066-2171-4458-a917-f5eca679c6b5",
    "name": "VNO",
    "country": "Lithuania",
    "city": "Vilnius"
  },
  "destinationAirport":{
    "id": "492045d1-2683-4d0a-a60b-0ae1332ce3e8",
    "name": "KNO",
    "country": "Lithuania",
    "city": "Kaunas"
  },
  "departureDate": "2023-03-15T10:30:00",
  "price": 250.5
}
```

##### <a id="getreservation">Get Reservation by ID -> /api/v1/reservations/111bfe69-a08c-4f35-affe-ccb45de1123a</a>
```json
{
  "id": "111bfe69-a08c-4f35-affe-ccb45de1123a",
  "flight":{
    "id": "4f6dfe69-a08c-4f35-affe-ccb45de1672c",
    "originAirport":{
      "id": "3ba74066-2171-4458-a917-f5eca679c6b5",
      "name": "VNO",
      "country": "Lithuania",
      "city": "Vilnius"
    },
    "destinationAirport":{
      "id": "492045d1-2683-4d0a-a60b-0ae1332ce3e8",
      "name": "KNO",
      "country": "Lithuania",
      "city": "Kaunas"
    },
    "departureDate": "2023-03-15T10:30:00",
    "price": 250.5
  },
  "user":{
    "id": "7a1bfe69-a08c-4f35-affe-ccb45de1123a",
    "name": "Ann",
    "password": "ann34645",
    "email": "ann@gmail.com",
    "appUserRole": "USER"
  }
}
```

##### <a id="getuser">Get User by ID -> /api/v1/users/7a1bfe69-a08c-4f35-affe-ccb45de1123a</a>
```json
{
  "id": "7a1bfe69-a08c-4f35-affe-ccb45de1123a",
  "name": "Ann",
  "password": "ann34645",
  "email": "ann@gmail.com",
  "appUserRole": "USER"
}
```


## Version History

* 1.1
    * Adding Airport class instead of just city name as origin/destination
* 1.0
    * Initial Release