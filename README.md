# Flight-Scanner

This is Java REST project to provide platform to search available flights. This is implemented by using spring-boot framework and H2 in-memory database.

## Prerequisites
- Java 1.8 or greater.
- Maven 3.0 or greater.

## Package Structure
- com.flightscanner: Spring boot application
- com.flightscanner.controllers: REST controllers
- com.flightscanner.dao: Database repositories
- com.flightscanner.entities: Database entities
- com.flightscanner.handlers: Data handlers (collector, writer and remover)
- com.flightscanner.model: Data models

## How to run
mvn clean package
./run.sh

## Features
1) This is RESTfull project.
2) This project supports two flight providers data (please refer following description section).
3) It runs background function to collect data indepenedent of user request, so responding user search query is just local in-memory database search.

## Future Works
- [ ] Add more rest APIs for extra features like user account and its related APIs.
- [ ] Support authentication and authorization between endpoints.
- [ ] Add Presentation layer.
- [ ] Add test cases.
- [ ] Add support for more providers.


## Description

Flight-scanner is a interface between User and flight data providers. It supports search over available flights for which data is collected from flight's provider.

### Flight providers
Current flight-scanner collects data from two providers.

#### Cheap:
- URL: https://obscure-caverns-79008.herokuapp.com/cheap
- Data Format: 
    {
        String id # Identification field for record
        String departure # source airport for flight
        String arrival # destination airport for flight
        Long departureTime # departure time from departure (source) airport in epoch
        Long arrivalTime # arrival time at arrival (destination) airport in epoch
    }
- Example:
{"id":1934425520793669632,"departure":"Veinticinco de Mayo","arrival":"Puan","departureTime":1548506349097,"arrivalTime":1548508149213},{"id":419793331512658944,"departure":"Armstrong","arrival":"Catriel","departureTime":1548503092857,"arrivalTime":1548505056685}]

#### Business:
- URL: https://obscure-caverns-79008.herokuapp.com/business
- Data Format: 
    {
        String uuid # Identification field for record
        String flight # source and destination airports for flight in the format of "source -> destination"
        String departure # departure time from departure (source) airport in the format "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" or "yyyy-MM-dd'T'HH:mm:ss'Z'"
        String arrival # arrival time at arrival (destination) airport in the format "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" or "yyyy-MM-dd'T'HH:mm:ss'Z'"
    }
- Example:
{{"uuid":"dde5e425-7aff-4247-a225-15831af55031","flight":"Canuelas -> Adelia Maria","departure":"2019-01-27T08:55:39.435Z","arrival":"2019-01-27T10:40:24.061Z"},{"uuid":"1b9c0974-9940-4e27-9ac7-4d48db3923e3","flight":"Galvez -> Huanchillas","departure":"2019-01-27T09:16:09.867Z","arrival":"2019-01-27T11:09:47.933Z"}]

### REST API
Flight-scanner provides search API with various filtering options. Response is always sorted on the basis of departure time.

#### Endpoint

GET /api/v1/flight/search

#### Query Parameters
1) source: filter output on source airport, if not given then search for all source airports
2) destination: filter output on destination airport, if not given then search for all destination airports
3) startTime: filter output only to return flights which have departure time after startTime (in epoch)
4) endTime: filter output only to return flights which have departure time before endTime (in epoch)
5) pageSize: number of flights to return in response

#### Examples
1) Show all flights: /api/v1/flight/search
2) Show first 10 earliest flights: /api/v1/flight/search?pageSize=10
3) Show 20 earliest flights from Galvez airport: /api/v1/flight/search?source=Galvez&pageSize=20
4) Show flights to Huanchillas airport: /api/v1/flight/search?destination=Huanchillas
5) Show 5 earliest flights from Galvez to Huanchillas: /api/v1/flight/search?source=Galvez&destination=Huanchillas&pageSize=5
6) Show 20 earliest flights from Galvez after January 26, 2019 12:39:09.097 PM GMT: /api/v1/flight/search?source=Galvez&startTime=1548506349097&pageSize=20
7) Show 20 earliest flights from Galvez in between January 26, 2019 12:39:09.097 PM GMT and January 26, 2019 1:15:50.097 PM GMT: /api/v1/flight/search?source=Galvez&startTime=1548506349097&endTime=1548508550097&pageSize=20
8) Show all flights from Galvez to Huanchillas in between January 26, 2019 12:39:09.097 PM GMT and January 26, 2019 1:15:50.097 PM GMT: /api/v1/flight/search?source=Galvez&destination=Huanchillas&startTime=1548506349097&endTime=1548508550097

#### Response
List of all flights matched with given criteria sorted by departure time.

- Format:
{
    String id # Identification field for record with prefix "collectorId#"
    String source # source airport for flight
    String destination # destination airport for flight
    Long departure # departure time from source airport in epoch
    Long arrival # arrival time at destination airport in epoch
}

- Example:
[{"id":"cheap#4194709095131265024","source":"canada de gomez","destination":"venado tuerto","departure":1548583773983,"arrival":1548592588154},{"id":"business#1e3a23c4-0c7f-42a1-b4d7-bc5dfd56dafe","source":"mar de ajo","destination":"retiro","departure":1548583784835,"arrival":1548588150177},{"id":"cheap#2012445936577901568","source":"oran","destination":"minacar","departure":1548583868354,"arrival":1548585455192}]

