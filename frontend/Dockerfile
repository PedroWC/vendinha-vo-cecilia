# Etapa 1: Build da aplicação
FROM node:18-alpine AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos de dependências do projeto
COPY package*.json ./

# Instala as dependências
RUN npm install --legacy-peer-deps

# Copia o código-fonte do projeto para o container
COPY . .

# Passa a variável de ambiente durante o build do React
ARG REACT_APP_API_URL
ENV REACT_APP_API_URL=$REACT_APP_API_URL

# Executa o build da aplicação
RUN npm run build

# Etapa 2: Configuração para servir a aplicação
FROM nginx:alpine

# Remove a configuração padrão do Nginx
RUN rm -rf /usr/share/nginx/html/*

# Copia os arquivos da build do React para a pasta onde o Nginx espera os arquivos
COPY --from=build /app/build /usr/share/nginx/html

# Expõe a porta 80 para acessar a aplicação
EXPOSE 80

# Inicia o Nginx
CMD ["nginx", "-g", "daemon off;"]
