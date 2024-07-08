# Usar uma base de imagem com JDK
FROM openjdk:17

# Copiar o arquivo jar do projeto para o container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Executar a aplicação
ENTRYPOINT ["java","-jar","/app.jar"]
