# Simple REST APIs in Spring Boot


## Getting Started

Clone the repo and open it in Spring Tool Suite, then start the application locally.

The app is available at `localhost:8080`. 

You may wish to utilize Postman to call 

* the upsert endpoint `localhost:8080/pool/v1/update` with the JSON request body 

```
{
 "poolId": 1,
 "poolValues": [1, 7, 7, 4, 8]
}
```

* the query endpoint `localhost:8080/pool/v1/query` with the body 

```
{
 "poolId": 1,
 "percentile": 60
}
```
The in-memory H2 database dialect is employed to simplify database connections, hence illustrate other points more important.

The database can be accessed in the browser via `http://localhost:8080/h2-console`. Just click `Connect` on the window and you'll taken to the UI.


## Notes on architecture and performance

### Project structure

* The API endpoints contain `v1` to cater for versioning.

* The package `spring.oxygen.pool` is designed as a module. Similar modules can be added as packages at the same level as its.

* Children in the pool package follow the structure of a standard web project, with the controller, service, domain and repository tiers.


### Upsert performance

For the pool upsert API, one can combine the update and insert into a single query to reduce the number of round trips to database to one, but I couldn't do this with H2 due to time constraints. In addition, this approach would make it difficult to know whether the upsert action is append or insert and return the result in the API response.


Therefore, I chose to have at most two database connections during one API call. 

I also employed Java-native `System.arraycopy()`, which is efficient for positioning one array after another.

### Query performance

For the pool query endpoint, Array was used as it's best suited for sorting. 

## Tests
Several preliminary unit and integration tests were implemented.
