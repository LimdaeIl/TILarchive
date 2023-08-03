GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost';
CREATE USER 'dave'@'localhost' IDENTIFIED BY 'funcoding';
GRANT ALL PRIVILEGES ON *.* TO 'dave'@'localhost';
flush privileges ;

CREATE DATABASE blog_db;
show databases ;

