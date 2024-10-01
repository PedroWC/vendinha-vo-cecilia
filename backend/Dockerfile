# Etapa 1: Usar uma imagem Maven oficial para compilar o projeto
FROM maven:3.9.2-eclipse-temurin-20 AS build

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo pom.xml, o código-fonte e baixar as dependências
COPY pom.xml ./
COPY src ./src
RUN mvn dependency:go-offline -B

# Executar o comando Maven para compilar e empacotar o projeto
RUN mvn clean package -DskipTests

# Etapa 2: Usar uma imagem oficial do OpenJDK 20 para rodar a aplicação
FROM openjdk:20-jdk

# Definir o diretório de trabalho no novo container
WORKDIR /app

# Copiar o arquivo JAR gerado na etapa anterior
COPY --from=build /app/target/vendinha-0.0.1-SNAPSHOT.jar /app/vendinha.jar

# Expor a porta 8181
EXPOSE 8181

# Comando para executar o JAR
ENTRYPOINT ["java", "-jar", "vendinha.jar"]
