mvn clean package spring-boot:repackage
cp target/*.jar target/app.jar

docker-compose up
