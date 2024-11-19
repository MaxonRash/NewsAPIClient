**SpringBoot** API client for getting news for the specified word from [NewsAPI.org](https://newsapi.org/) Working as service for my [NewsTelegrambot](https://github.com/MaxonRash/NewsTelegramBot)
News are requested and sent daily at **12am** and **8pm (GMT+3)**. Messaging with main app via **Apache Kafka**. All interactions are via commands in chat with bot.
*******
**Technologies** used in these apps:
- **Java 17-18**
- **SpringBoot 2-3**
- **Maven, Spring Data, Spring Security, Spring Scheduler, Zookeeper, Apache Kafka** for messaging between main app and service, **CI:** Github Actions
- **Postgresql, MongoDB** as databases
- **Flyway** - db migration
- **Junit, Mockito** for tests
- **Docker, Docker-compose** for deployment
*****
To launch it on your server you need: **Docker, docker-compose**. Use `docker-compose up --build -d` to run. This will launch **this app** and local **MongoDB** server with all configurations needed. Script for MongoDB user is `mongo-init.js` in root directory.