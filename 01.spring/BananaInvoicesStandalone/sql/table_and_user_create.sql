CREATE SCHEMA `invoices` ;

USE mysql;

CREATE USER 'invoices_user'@'%' IDENTIFIED BY 'inv123';

GRANT ALL PRIVILEGES ON invoices.* TO 'invoices_user'@'%';
FLUSH PRIVILEGES;
