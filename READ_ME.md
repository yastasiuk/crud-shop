## Server
### Docker
0. Get docker
```
https://docs.docker.com/docker-for-windows/install/
```
1. Build docker file
```
docker build -t crud-shop:0.1 -f ./server/Dockerfile ./server/
```
2. Start container
```
docker-compose up -d
```
3. Check 'http://localhost:8080/greeting'

### Local
1. Start via `./server/gradlew` or via vscode scripts


## Server
1. ```npm install```
2. ```npm start```
3. Go to ```http://localhost:4200/```