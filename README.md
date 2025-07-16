# CSPB Ticketing API

This API service manages the CRUD operations of the ticketing system of CSPB. The server allows viewing, updating, closing (not deleting), and creating requests.

For creating requests, include the requestor, type of request, description, comments, and assigned user to handle the request.

## Building and running

```bash
SPRING_PROFILES_ACTIVE=dev ./gradlew bootRun
```

Use the `dev` profile to run against an embedded Mongo database. Switch to the
`prod` profile to connect to the Mongo instance defined by
`SPRING_DATA_MONGODB_URI`.

The service listens on port `8080` by default.
