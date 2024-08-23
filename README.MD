# Сервис отправки текстовых сообщений

Настройка способа отправки текстовых сообщение происходит в классе PostServiceConfiguration
В данной реализации используется класс-заглушка EmailPostServiceEmulator

Для создания кастомных способов отправки необходимо унаследоваться от PostService

Описание API интерфейса доступно по адресу http://localhost:8080/api/v1/swagger-ui/index.html

## Быстрый запуск приложения
<code>
chmod +x ./start.sh
./start.sh
</code>

## Запуск приложения, подробнее

<code>
# Компиляция проекта
mvn clean package spring-boot:repackage

# Переименоание артефакта
cp target/*.jar target/app.jar

# Запуск необходимых docker- контейнеров
docker-compose up
</code>
