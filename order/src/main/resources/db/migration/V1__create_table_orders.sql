CREATE TABLE orders (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  date_time datetime NOT NULL,
  order_status varchar(100) NOT NULL,
  PRIMARY KEY (id)
)