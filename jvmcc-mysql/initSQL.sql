CREATE DATABASE IF NOT EXISTS jvmcc;
USE jvmcc;  
CREATE USER IF NOT EXISTS 'jvmcc'@'%' IDENTIFIED BY 'jvmcc';
GRANT ALL PRIVILEGES ON *.* TO 'jvmcc'@'%';
FLUSH privileges;

CREATE TABLE IF NOT EXISTS jvmcc.review (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id VARCHAR(255) NOT NULL,
    review_score SMALLINT NOT NULL,
    comment VARCHAR(255) NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_update_user VARCHAR(255) DEFAULT 'Not Authenticated'
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS jvmcc.`review_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB;

INSERT INTO review (product_id, review_score, comment) VALUES ('M20324', 1, "test comment 1-1");
INSERT INTO review_seq (next_val) VALUES (100);
INSERT INTO review (product_id, review_score, comment) VALUES ('M20324', 2, "test comment 1-2");
INSERT INTO review (product_id, review_score, comment) VALUES ('M20324', 3, "test comment 1-3");
INSERT INTO review (product_id, review_score, comment) VALUES ('M20324', 4, "test comment 1-4");
INSERT INTO review (product_id, review_score, comment) VALUES ('M20324', 5, "test comment 1-5");
INSERT INTO review (product_id, review_score, comment) VALUES ('AC7836', 2, "test comment 2-1");
INSERT INTO review (product_id, review_score, comment) VALUES ('AC7836', 2, "test comment 2-2");
INSERT INTO review (product_id, review_score, comment) VALUES ('AC7836', 2, "test comment 2-3");
INSERT INTO review (product_id, review_score, comment) VALUES ('C77154', 3, "test comment 3-1");
INSERT INTO review (product_id, review_score, comment) VALUES ('BB5476', 4, "test comment 4-1");
INSERT INTO review (product_id, review_score, comment) VALUES ('BB5476', 4, "test comment 4-2");
INSERT INTO review (product_id, review_score, comment) VALUES ('BB5476', 4, "test comment 4-3");
INSERT INTO review (product_id, review_score, comment) VALUES ('B42000', 5, "test comment 5-1");
INSERT INTO review (product_id, review_score, comment) VALUES ('B42000', 5, "test comment 5-2");
INSERT INTO review (product_id, review_score, comment) VALUES ('B42000', 5, "test comment 5-3");
INSERT INTO review (product_id, review_score, comment) VALUES ('B42000', 5, "test comment 5-4");
