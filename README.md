# GitHub Repositories API

## Description

This is a simple Quarkus-based API that fetches non-fork GitHub repositories for a given user. It also returns information about the branches and their last commit SHA.

## How to Run

1. Clone the repository.
2. Run the application:

#### Running the Application in Development Mode

To start the application in development mode (with live reload enabled), use the following command:

```shell script
./mvnw quarkus:dev
```

#### Running the Application in Production Mode

To run the application in production mode, first package the application:

```shell script
./mvnw package
```

This will create a quarkus-run.jar in the target/quarkus-app/ directory.

Run the application using:

```shell script
java -jar target/quarkus-app/quarkus-run.jar
```

## API Endpoints

### GET /github/user/{username}/repos

Fetches the list of non-fork repositories for a given GitHub user. For each repository, the name, owner login, branches, and the last commit SHA for each branch are returned.

#### Example Response:

```json
[
  {
    "repositoryName": "repo-name",
    "ownerLogin": "owner-login",
    "branches": [
      {
        "name": "main",
        "commitSha": "commit-sha"
      }
    ]
  }
]
```

#### Error Response:

```json
{
  "status": 404,
  "message": "User not found"
}
```
