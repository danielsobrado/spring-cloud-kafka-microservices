CREATE DATABASE jvmcc_test_db;
USE jvmcc;  
CREATE USER 'jvmcc'@'%' IDENTIFIED BY 'jvmcc';
GRANT ALL PRIVILEGES ON *.* TO 'jvmcc'@'%';
FLUSH privileges;

CREATE TABLE IF NOT EXISTS review (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id VARCHAR(255) NOT NULL,
    average_review_score FLOAT NOT NULL,
    number_reviews INT NOT NULL
)  ENGINE=INNODB;

INSERT INTO review VALUES (1, 'M20324', 0, 0);
INSERT INTO review VALUES (2, 'AC7836', 0, 0);
INSERT INTO review VALUES (3, 'C77154', 0, 0);
INSERT INTO review VALUES (4, 'BB5476', 0, 0);
INSERT INTO review VALUES (5, 'B42000', 0, 0);