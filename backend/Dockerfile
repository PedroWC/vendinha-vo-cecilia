# Usar uma imagem oficial do OpenJDK como base
FROM openjdk:17-jdk-alpine

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR da aplicação para o diretório de trabalho do container
COPY target/vendinha-backend.jar /app/vendinha-backend.jar

# Expor a porta 8080 para acessar a aplicação
EXPOSE 8080

# Comando para executar a aplicação quando o container iniciar
ENTRYPOINT ["java", "-jar", "vendinha-backend.jar"]