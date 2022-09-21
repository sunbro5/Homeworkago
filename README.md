mvn spring-boot:run

curl -XGET 'localhost:8080/rides?offset=0&limit=20'

curl -XGET 'localhost:8080/rides/3a026c93-d643-4c87-9aa9-e686eab93a20'

curl -XPOST -H "Content-type: application/json" -d '{"rideId": "3a026c93-d643-4c87-9aa9-e686eab93a20","text" : "ahoooj"}' 'localhost:8080/rides/comment'