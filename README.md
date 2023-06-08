# spring-cache-simple-example

curl localhost:8080/book

curl localhost:8080/book/isbn1

curl --location --request POST "localhost:8080/book" --header "Content-Type: application/json" --data-raw "{\"isbn\": \"isbn4\", \"author\": \"author4\", \"title\": \"title4\"}"
curl --location --request PUT "localhost:8080/book" --header "Content-Type: application/json" --data-raw "{\"isbn\": \"isbn2\", \"author\": \"author22\", \"title\": \"title22\"}"
curl --location --request DELETE "localhost:8080/book/isbn3"