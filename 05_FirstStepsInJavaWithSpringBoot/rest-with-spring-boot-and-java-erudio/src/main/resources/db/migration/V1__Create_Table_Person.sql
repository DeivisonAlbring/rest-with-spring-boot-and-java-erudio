CREATE TABLE person (
  id bigint NOT NULL AUTO_INCREMENT,
  first_name varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  last_name varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  address varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL, 
  gender varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,  
  PRIMARY KEY (id)
);