CREATE TABLE IF NOT EXISTS review (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id VARCHAR(255) NOT NULL,
    review_score SMALLINT NOT NULL,
    comment VARCHAR(255) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)  ENGINE=INNODB;

INSERT INTO review (id, product_id, review_score, comment) VALUES (1, 'M20324', 1, "test comment 1");
INSERT INTO review (id, product_id, review_score, comment) VALUES (2, 'AC7836', 2, "test comment 2");
INSERT INTO review (id, product_id, review_score, comment) VALUES (3, 'C77154', 3, "test comment 3");
INSERT INTO review (id, product_id, review_score, comment) VALUES (4, 'BB5476', 4, "test comment 4");
INSERT INTO review (id, product_id, review_score, comment) VALUES (5, 'B42000', 5, "test comment 5");