# Usar a imagem oficial do OpenJDK 23
FROM openjdk:23-jdk-slim

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR da aplicação para o diretório de trabalho do container
COPY target/vendinha-backend.jar /app/vendinha-backend.jar

# Expor a porta 8080 para acessar a aplicação
EXPOSE 8080

# Comando para executar a aplicação quando o container iniciar
ENTRYPOINT ["java", "-jar", "vendinha-backend.jar"]