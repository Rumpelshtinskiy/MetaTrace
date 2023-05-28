# MetaTrace
## Description
MetaTrace is a websocket server, that generates a unique arbitrary BigInteger value for each request in JSON format. 
The server does not allow more than 1 connection to be established from one IP address. 

## Requirements
* Java 17
* Docker
* Docker-compose
* Postman (optional)

## Launching the application
## The first way (temporary not recommended on Windows)
The easiest way to start is to use docker-compose from the root directory of the project:
```shell
docker-compose up -d
```
The application will be available at `localhost:3000`

## The second way (recommended)
Go to the `launch/MetaTraceDB` directory of the project and run the following commands:
```shell
docker-compose up -d
```
Then go back to the `launch` directory of the project and run the following commands:
```shell
java -jar MetaTrace.jar
```
The application will be available at `localhost:3000`

## Usage
For testing, you can use `Postman` or any other similar tool.

Launch the `Postman` pick `File->New...` and chose a 'WebSocket' connection. 
In `Enter request URL` field enter `ws://localhost:3000/`. Then click on `Connect` button.
Type a number of **size** BigInteger you want to get as Message. Send a request by clicking on the `Send` button.

## Notes
The **size** of the BigInteger must be in the range from 1 to 2691, this is due to the limitation of the database.

