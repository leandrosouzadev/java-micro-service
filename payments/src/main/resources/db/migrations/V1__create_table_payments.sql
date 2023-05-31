CREATE TABLE payments(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    amount decimal(19,2) NOT NULL,
    name VARCHAR(100) DEFAULT NULL,
    number VARCHAR(19) DEFAULT NULL,
    expiration VARCHAR(7) NOT NULL,
    code VARCHAR(3) NOT NULL,
    payment_status VARCHAR(50) NOT NULL,
    order_id bigint(20) NOT NULL,
    PRIMARY KEY (id)
);