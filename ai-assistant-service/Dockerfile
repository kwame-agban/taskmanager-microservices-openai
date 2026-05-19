FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/ai-assistant-service-*.jar app.jar
ENV OPENAI_API_KEY=${OPENAI_API_KEY}
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]
