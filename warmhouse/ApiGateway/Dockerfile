# Используем официально поддерживаемый образ Eclipse Temurin с JRE 17
FROM eclipse-temurin:17-jre-alpine

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем JAR-файл (собираемый Maven'ом)
COPY target/api-gateway-1.0.0.jar app.jar

# Открываем порт 8082
EXPOSE 8082

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]