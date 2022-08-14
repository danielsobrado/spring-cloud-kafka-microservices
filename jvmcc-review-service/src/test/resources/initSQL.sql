CREATE TABLE IF NOT EXISTS review (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id VARCHAR(255) NOT NULL,
    review_score SMALLINT NOT NULL,
    comment VARCHAR(255) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)  ENGINE=INNODB;

INSERT INTO review (id, product_id, review_score, comment) VALUES (1, 'M20324', 1, "test comment 1-1");
INSERT INTO review (id, product_id, review_score, comment) VALUES (2, 'M20324', 2, "test comment 1-2");
INSERT INTO review (id, product_id, review_score, comment) VALUES (3, 'M20324', 3, "test comment 1-3");
INSERT INTO review (id, product_id, review_score, comment) VALUES (4, 'M20324', 4, "test comment 1-4");
INSERT INTO review (id, product_id, review_score, comment) VALUES (5, 'M20324', 5, "test comment 1-5");
INSERT INTO review (id, product_id, review_score, comment) VALUES (6, 'AC7836', 2, "test comment 2-1");
INSERT INTO review (id, product_id, review_score, comment) VALUES (7, 'AC7836', 2, "test comment 2-2");
INSERT INTO review (id, product_id, review_score, comment) VALUES (8, 'AC7836', 2, "test comment 2-3");
INSERT INTO review (id, product_id, review_score, comment) VALUES (9, 'C77154', 3, "test comment 3-1");
INSERT INTO review (id, product_id, review_score, comment) VALUES (10, 'BB5476', 4, "test comment 4-1");
INSERT INTO review (id, product_id, review_score, comment) VALUES (11, 'BB5476', 4, "test comment 4-2");
INSERT INTO review (id, product_id, review_score, comment) VALUES (12, 'BB5476', 4, "test comment 4-3");
INSERT INTO review (id, product_id, review_score, comment) VALUES (13, 'B42000', 5, "test comment 5-1");
INSERT INTO review (id, product_id, review_score, comment) VALUES (14, 'B42000', 5, "test comment 5-2");
INSERT INTO review (id, product_id, review_score, comment) VALUES (15, 'B42000', 5, "test comment 5-3");
INSERT INTO review (id, product_id, review_score, comment) VALUES (16, 'B42000', 5, "test comment 5-4");