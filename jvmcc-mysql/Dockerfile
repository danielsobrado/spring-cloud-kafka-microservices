FROM mysql/mysql-server:8.0

COPY initSQL.sql /docker-entrypoint-initdb.d/

EXPOSE 3306

# docker build -t jvmcc-mysql .
# docker run -p 3306:3306 --name mysql-server -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=demo -e MYSQL_PASSWORD=demo -d mysql/mysql-server:8.0
# docker exec -it mysql-server mysql -uroot -p