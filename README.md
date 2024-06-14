# alura-challenge-backend-7
Challenge de backend da escola de programação da alura

# Tecnologias
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

---------------
## Sobre a base de dados:

### Como subir a Base de dados utilizando arquivo docker disponibilizado no projeto:
  - Acessar através do terminal o projeto e abrir o diretório docker
  - Executar o comando `docker-compose up`;
  - Observar se o container subiu corretamente;

### Como criar a base de dados:
- Executar a sequencia de comandos abaixo para criar a base de dados que vamos utilizar para testar localmente:
  - `sudo docker rename docker-db-1 jornada-milhas`
  - `sudo docker exec -it jornada-milhas bash`
  - `mysql -p`
  - `show databases;`;
  - `create database jornada_milhas;`;
------
# Colaboradores da encrenca
- Euller filho do vento
- Fernando 7 janta