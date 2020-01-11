#Database Creation
DROP DATABASE spring_market;
CREATE DATABASE spring_market;
USE spring_market;

#Tables Creation
CREATE TABLE User(
user_id INT AUTO_INCREMENT NOT NULL,
email VARCHAR(50) NOT NULL,
password VARCHAR(60) NOT NULL,
name VARCHAR(50),
enabled int,
PRIMARY KEY (user_id)
);

CREATE TABLE Role(
role_id INT AUTO_INCREMENT,
role VARCHAR(50),
PRIMARY KEY (role_id)
);

CREATE TABLE User_Role(
user_user_id INT NOT NULL,
role_role_id INT NOT NULL,
FOREIGN KEY (user_user_id) REFERENCES User (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (role_role_id) REFERENCES Role (role_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Product(
product_id INT AUTO_INCREMENT,
name VARCHAR(100),
price DOUBLE,
availability ENUM('available', 'not_available'),
image_name VARCHAR(50),
PRIMARY KEY (product_id)
);

CREATE TABLE Delivery_Method(
delivery_method_id int AUTO_INCREMENT,
name varchar(50),
price DOUBLE,
estimated_days int,
PRIMARY KEY(delivery_method_id)
);


CREATE TABLE Credit_Card(
credit_card_id INT AUTO_INCREMENT,
holder VARCHAR(50),
number VARCHAR(50),
expiration_date_month INT,
expiration_date_year INT,
cvc VARCHAR(3),
user_id INT,
PRIMARY KEY (credit_card_id),
FOREIGN KEY (user_id) REFERENCES User (user_id) ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE Address(
address_id INT AUTO_INCREMENT,
name varchar(50),
phone varchar(20),
country VARCHAR(50),
city VARCHAR(50),
province VARCHAR(50),
address VARCHAR(50),
zipcode VARCHAR(5),
user_id INT,
PRIMARY KEY (address_id),
FOREIGN KEY (user_id) REFERENCES User (user_id) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE Delivery(
delivery_id INT AUTO_INCREMENT,
delivery_order_date DATETIME,
estimated_delivery_date DATE,
user_id INT,
delivery_method_id INT,
product_total_cost DOUBLE,
delivery_total_cost DOUBLE,
address_id INT,
credit_card_id INT,
PRIMARY KEY (delivery_id),
FOREIGN KEY (user_id) REFERENCES User (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (delivery_method_id) REFERENCES Delivery_Method (delivery_method_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (address_id) REFERENCES Address (address_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (credit_card_id) REFERENCES Credit_Card (credit_card_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE Product_Delivery(
product_product_id INT NOT NULL,
delivery_delivery_id INT NOT NULL,
price DOUBLE NOT NULL,
quantity INT NOT NULL,
FOREIGN KEY (product_product_id) REFERENCES Product (product_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (delivery_delivery_id) REFERENCES Delivery (delivery_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
PRIMARY KEY (product_product_id, delivery_delivery_id)
);


#Data Insertion
INSERT INTO User(email, password, name, enabled) VALUES('johnmulaney@gmail.com', '$2y$12$xNab02x2RpZroD7GcJy5/.K5OMb9iXNvLZsc.hVWd5FM6b1esrgKO', 'John Mulaney', 1);
INSERT INTO User(email, password, name, enabled) VALUES('jimjefferies@gmail.com', '$2y$12$eUYCfwM24g06.19PKQXENOEEDKEIY7BpHe8LtFjIA04yKG9lxdPEG', 'Jim Jefferies', 1);
INSERT INTO User(email, password, name, enabled) VALUES('kate@snl.com', '$2y$12$FQU888OGKCd3UQu3OvHopuYm93V44NCZu8z7UJ898nb87/KZCDDrm', 'Kate McKinnon', 1);
INSERT INTO User(email, password, name, enabled) VALUES('ajeselnik001@gmail.com', '$2y$12$vvPAP6JzYyZddLCa6930AuCa1tzU7crE7iCI1f6VkyDuvYspA83km', 'Anthony Jeselnik', 1);
INSERT INTO User(email, password, name, enabled) VALUES('cliff@wutang.com', '$2y$12$d3CNIuElN0arAnGKmLlJmuT8jrlA8EvUxBCZcX3Xi5HESFgBldhim', 'Clifford Smith', 1);
INSERT INTO User(email, password, name, enabled) VALUES('ss001@gmail.com', '$2y$12$3JhCFZS2uTtM1mPHRnXjROrFHpeTG.O7N07LWuz0Sa3jvpsowhYEK', 'Sarah Silverman', 1);
INSERT INTO User(email, password, name, enabled) VALUES('waller-bridge@gmail.com', '$2y$12$KTTgkFIbMflrXrUHzvee2.w1nWWsuFIPoDmab34mTBxrYRSHVMAGK', 'Phoebe Waller-Bridge', 1);
INSERT INTO User(email, password, name, enabled) VALUES('admin@springmarket.com', '$2y$12$QZKRFpGNOSAD3FzNQefKt.S2FMgO8d3h/GKvivl8R/JERwyf1YxpO', 'Admin', 1);

INSERT INTO Role(role) VALUES('CUSTOMER');
INSERT INTO Role(role) VALUES('ADMIN');

INSERT INTO User_Role(user_user_id, role_role_id) VALUES(1,1);
INSERT INTO User_Role(user_user_id, role_role_id) VALUES(2,1);
INSERT INTO User_Role(user_user_id, role_role_id) VALUES(3,1);
INSERT INTO User_Role(user_user_id, role_role_id) VALUES(4,1);
INSERT INTO User_Role(user_user_id, role_role_id) VALUES(5,1);
INSERT INTO User_Role(user_user_id, role_role_id) VALUES(6,1);
INSERT INTO User_Role(user_user_id, role_role_id) VALUES(7,1);
INSERT INTO User_Role(user_user_id, role_role_id) VALUES(8,2);

INSERT INTO Product(name, price, availability, image_name) VALUES('Kellogg\'s Breakfast Cereal Variety Fun Packs 8.56 oz', 2.98, 'available', 'fun-pak.jpeg');
INSERT INTO Product(name, price, availability, image_name) VALUES('Honey Nut Cheerios Gluten Free Breakfast Cereal, 19.5 oz', 3.64, 'available', 'cheerios.jpeg');
INSERT INTO Product(name, price, availability, image_name) VALUES('Kellogg\'s Apple Jacks Breakfast Cereal Original Family Size 19.4 oz', 3.64, 'available', 'apple-jacks.jpeg');
INSERT INTO Product(name, price, availability, image_name) VALUES('Kellogg\'s Rice Krispies Original Breakfast Cereal 18 oz', 3.64, 'available', 'rice-krispies.jpeg');
INSERT INTO Product(name, price, availability, image_name) VALUES('Kellogg\'s Honey Smacks Family Size Breakfast Cereal 23oz Box', 3.64, 'available', 'smacks.jpeg');
INSERT INTO Product(name, price, availability, image_name) VALUES('Kellogg\'s Krave Breakfast Cereal DOUBLE Chocolate Family Size 16.7 oz', 3.64, 'available', 'krave.jpeg');
INSERT INTO Product(name, price, availability, image_name) VALUES('Cookie Crisp Cereal, 11.25 - Ounce Box', 3.85, 'available', 'cookie-crisp.png');
INSERT INTO Product(name, price, availability, image_name) VALUES('Cocoa Puffs, Chocolate Cereal, 20.9 oz', 3.64, 'available', 'cocoa-puffs.jpeg');
INSERT INTO Product(name, price, availability, image_name) VALUES('Chocolate Lucky Charms, Marshmallow Cereal, 19.5 oz', 3.64, 'available', 'lucky-charms.png');
INSERT INTO Product(name, price, availability, image_name) VALUES('Kellogg\'s Frosted Flakes Breakfast Cereal, Fat-Free, 13.5 oz', 2.98, 'available', 'frosted-fleaks.jpeg');
INSERT INTO Product(name, price, availability, image_name) VALUES('Kellogg\'s Strawberry Krispies Breakfast Cereal Original Family Size 16.5 oz', 3.64, 'available', 'strawberry-krispies.jpeg');
INSERT INTO Product(name, price, availability, image_name) VALUES('Kellogg\'s Coco Pops Breakfast Cereal Original, 14 oz', 3.90, 'available', 'choco-pocs.jpg');
INSERT INTO Product(name, price, availability, image_name) VALUES('Kellogg\'s Froot Loops Breakfast Cereal Family Size 19.4 oz', 3.64, 'available', 'froot-loops.jpeg');
INSERT INTO Product(name, price, availability, image_name) VALUES('Kellogg\'s Corn Pops Breakfast Cereal Original Family Size 19.1 oz', 3.64, 'available', 'corn-pops.jpeg');
INSERT INTO Product(name, price, availability, image_name) VALUES('Cap\'n Crunch Breakfast Cereal, Original, 20 Oz', 2.98, 'available', 'cap-n-crunch.jpeg');
INSERT INTO Product(name, price, availability, image_name) VALUES('Post Fruity Pebbles Gluten Free Breakfast Cereal, 15 Oz', 3.12, 'available', 'fruity-pebbles.jpeg');
INSERT INTO Product(name, price, availability, image_name) VALUES('Post Golden Crisp Wheat Breakfast Cereal, 24 Oz', 3.98, 'available', 'golden-crisp.jpeg');
INSERT INTO Product(name, price, availability, image_name) VALUES('Kellogg\'s Unicorn Cereal Breakfast Cereal Family Size 18.7 oz', 3.64, 'available', 'unicorn.jpeg');
INSERT INTO Product(name, price, availability, image_name) VALUES('Trix, Cereal, Fruit Flavored Corn Puffs, 17 oz', 3.64, 'available', 'trix.jpeg');
INSERT INTO Product(name, price, availability, image_name) VALUES('Teenage Mutant Ninja Turtles Breakfast Cereal, 16 oz', 4.00, 'available', 'turtles-cereals.png');
INSERT INTO Product(name, price, availability, image_name) VALUES('Kellogg\'s Scooby Doo Berry Bones Breakfast Cereal, 18 oz', 3.64, 'available', 'berry-bones.png');
INSERT INTO Product(name, price, availability, image_name) VALUES('Kellogg\'s Homer\'s Cinnamon Donut Breakfast Cereal, 12 oz', 3.64, 'available', 'homer.png');
INSERT INTO Product(name, price, availability, image_name) VALUES('Golden Nuggets Breakfaast Cereal, 12 oz', 3.55, 'available', 'golden-nuggets.png');
INSERT INTO Product(name, price, availability, image_name) VALUES('Croonchy Stars Breakfast Cereal, 20 oz', 3.98, 'available', 'croonchy-stars.png');
INSERT INTO Product(name, price, availability, image_name) VALUES('Monster Cereal, Count Chocula, 10.4 oz', 3.77, 'available', 'count-chocula.png');
INSERT INTO Product(name, price, availability, image_name) VALUES('Pokemon Cereal, 10.4 oz', 3.55, 'available', 'pokemon.png');
INSERT INTO Product(name, price, availability, image_name) VALUES('Pokemon Cereal, Psy-Pops 10.4, oz', 3.55, 'available', 'psy-pops.png');
INSERT INTO Product(name, price, availability, image_name) VALUES('Kellogg\'s Mud & Bugs Breakfast Cereal, 12 oz', 3.64, 'available', 'mud-and-bugs.png');
INSERT INTO Product(name, price, availability, image_name) VALUES('Garfield Fruity Bites, 12 oz', 3.62, 'available', 'garfield-fruity-bites.png');
INSERT INTO Product(name, price, availability, image_name) VALUES('Kellogg\'s Super Mario Breakfast Cereal, 12 oz', 3.64, 'available', 'super-mario.jpeg');

INSERT INTO Credit_Card(holder, number, expiration_date_month, expiration_date_year, cvc, user_id) VALUES('John Mulaney', '4140225196568634', 2, 2021, '988', 1);
INSERT INTO Credit_Card(holder, number, expiration_date_month, expiration_date_year, cvc, user_id) VALUES('Aniya Moore', '4528232123355178', 4, 2022, '449', 1);
INSERT INTO Credit_Card(holder, number, expiration_date_month, expiration_date_year, cvc, user_id) VALUES('Jim Jefferies', '4805446733871630', 4, 2028, '378', 2);
INSERT INTO Credit_Card(holder, number, expiration_date_month, expiration_date_year, cvc, user_id) VALUES('Kate McKinnon', '4258508015902425', 12, 2024, '297', 3);
INSERT INTO Credit_Card(holder, number, expiration_date_month, expiration_date_year, cvc, user_id) VALUES('Anthony Jeselnik', '4775772946088639', 9, 2025, '400', 4);
INSERT INTO Credit_Card(holder, number, expiration_date_month, expiration_date_year, cvc, user_id) VALUES('Paityn Evans', '4932816651243762', 10, 2020, '915', 4);
INSERT INTO Credit_Card(holder, number, expiration_date_month, expiration_date_year, cvc, user_id) VALUES('Clifford Smith', '4936659093698433', 8, 2028, '398', 5);
INSERT INTO Credit_Card(holder, number, expiration_date_month, expiration_date_year, cvc, user_id) VALUES('Robert Fitzgerald', '4921026998246477', 12, 2027, '928', 5);
INSERT INTO Credit_Card(holder, number, expiration_date_month, expiration_date_year, cvc, user_id) VALUES('Gary Grice', '4586906215496482', 4, 2020, '964', 5);
INSERT INTO Credit_Card(holder, number, expiration_date_month, expiration_date_year, cvc, user_id) VALUES('Sarah Silverman', '4881925178890145', 6, 2023, '116', 6);
INSERT INTO Credit_Card(holder, number, expiration_date_month, expiration_date_year, cvc, user_id) VALUES('Phoebe Waller-Bridge', '4343768323145888', 1, 2025, '746', 7);

INSERT INTO Address(name, phone, country, city, province, address, zipcode, user_id) VALUES('John Mulaney', '+1-555-7552-538', 'Spain', 'Bilbao', 'Bizkaia', 'Elcano 23', '48008', 1);
INSERT INTO Address(name, phone, country, city, province, address, zipcode, user_id) VALUES('Aniya Moore', '+1-555-3140-595', 'Spain', 'Toledo', 'Toledo', 'Calle de Orgaz 7', '45004', 1);
INSERT INTO Address(name, phone, country, city, province, address, zipcode, user_id) VALUES('Jim Jefferies', '+1-555-5488-083', 'Spain', 'San Sebastian', 'Gipuzkoa', 'Calle Aldakonea 71', '20012', 2);
INSERT INTO Address(name, phone, country, city, province, address, zipcode, user_id) VALUES('Kate McKinnon', '+1-555-7155-076', 'Spain', 'Vigo', 'Pontevedra', 'Rúa do Ecuador 47', '36203', 3);
INSERT INTO Address(name, phone, country, city, province, address, zipcode, user_id) VALUES('Anthony Jeselnik', '+1-555-7581-999', 'Spain', 'Jaén', 'Jaén', 'Antonio Muñoz Molina 6', '23009', 4);
INSERT INTO Address(name, phone, country, city, province, address, zipcode, user_id) VALUES('Clifford Smith', '+1-555-1838-569', 'Spain', 'Madrid', 'Madrid', 'Bolivia 33', '28016', 5);
INSERT INTO Address(name, phone, country, city, province, address, zipcode, user_id) VALUES('Sarah Silverman', '+1-555-8920-176', 'Spain', 'Villarcayo', 'Burgos', 'Calvo Sotelo 8', '09550', 6);
INSERT INTO Address(name, phone, country, city, province, address, zipcode, user_id) VALUES('Phoebe Waller-Bridge', '+1-555-8920-176', 'Spain', 'Cádiz', 'Cádiz', 'Guadalquivir 15 1A', '11012', 7);

INSERT INTO Delivery_Method(name, price, estimated_days) VALUES('Normal', 3.00, 4);
INSERT INTO Delivery_Method(name, price, estimated_days) VALUES('Premium', 4.50, 2);
INSERT INTO Delivery_Method(name, price, estimated_days) VALUES('Premium Deluxe', 7.50, 1);

INSERT INTO Delivery(delivery_order_date, estimated_delivery_date, user_id, delivery_method_id, address_id, credit_card_id, product_total_cost, delivery_total_cost) VALUES('2019-10-10 14:05:43', '2019-11-01', 1, 1, 1, 1, 27.64, 30.64);
INSERT INTO Delivery(delivery_order_date, estimated_delivery_date, user_id, delivery_method_id, address_id, credit_card_id, product_total_cost, delivery_total_cost) VALUES('2019-09-27 18:01:22', '2019-10-01', 1, 2, 1, 2, 11.28, 15.78);
INSERT INTO Delivery(delivery_order_date, estimated_delivery_date, user_id, delivery_method_id, address_id, credit_card_id, product_total_cost, delivery_total_cost) VALUES('2019-09-20 12:01:05', '2019-09-27', 1, 1, 2, 1, 32.41, 35.41);
INSERT INTO Delivery(delivery_order_date, estimated_delivery_date, user_id, delivery_method_id, address_id, credit_card_id, product_total_cost, delivery_total_cost) VALUES('2019-10-26 10:05:30', '2019-10-28', 2, 1, 3, 3, 3.55, 6.55);
INSERT INTO Delivery(delivery_order_date, estimated_delivery_date, user_id, delivery_method_id, address_id, credit_card_id, product_total_cost, delivery_total_cost) VALUES('2019-10-06 12:35:54', '2019-10-10', 2, 2, 3, 3, 65.46, 69.96);
INSERT INTO Delivery(delivery_order_date, estimated_delivery_date, user_id, delivery_method_id, address_id, credit_card_id, product_total_cost, delivery_total_cost) VALUES('2019-10-04 20:26:41', '2019-10-09', 3, 1, 4, 4, 33.15, 36.15);
INSERT INTO Delivery(delivery_order_date, estimated_delivery_date, user_id, delivery_method_id, address_id, credit_card_id, product_total_cost, delivery_total_cost) VALUES('2019-09-30 11:11:28', '2019-10-05', 4, 1, 5, 6, 25.48, 28.48);
INSERT INTO Delivery(delivery_order_date, estimated_delivery_date, user_id, delivery_method_id, address_id, credit_card_id, product_total_cost, delivery_total_cost) VALUES('2019-10-09 16:20:55', '2019-10-14', 5, 1, 6, 7, 5.96, 8.96);
INSERT INTO Delivery(delivery_order_date, estimated_delivery_date, user_id, delivery_method_id, address_id, credit_card_id, product_total_cost, delivery_total_cost) VALUES('2019-10-05 14:55:20', '2019-10-10', 5, 3, 6, 9, 22.18, 29.68);
INSERT INTO Delivery(delivery_order_date, estimated_delivery_date, user_id, delivery_method_id, address_id, credit_card_id, product_total_cost, delivery_total_cost) VALUES('2019-10-10 17:40:42', '2019-11-25', 6, 1, 7, 10, 32.76, 35.76);
INSERT INTO Delivery(delivery_order_date, estimated_delivery_date, user_id, delivery_method_id, address_id, credit_card_id, product_total_cost, delivery_total_cost) VALUES('2019-10-16 19:11:47', '2019-10-20', 7, 1, 8, 11, 14.29, 17.29);
INSERT INTO Delivery(delivery_order_date, estimated_delivery_date, user_id, delivery_method_id, address_id, credit_card_id, product_total_cost, delivery_total_cost) VALUES('2019-09-30 18:05:41', '2019-10-05', 7, 3, 8, 11, 115.11, 122.61);

INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(4, 1, 3.64, 1);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(11, 1, 3.64, 1);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(8, 1, 3.64, 2);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(16, 1, 3.12, 1);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(12, 1, 3.90, 3);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(22, 2, 3.64, 1);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(20, 2, 4.00, 2);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(15, 3, 2.98, 2);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(7, 3, 3.85, 3);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(1, 3, 2.98, 5);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(26, 4, 3.55, 1);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(10, 5, 2.98, 2);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(18, 5, 3.64, 2);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(21, 5, 3.64, 1);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(3, 5, 3.64, 1);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(22, 5, 3.64, 2);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(5, 5, 3.64, 3);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(12, 5, 3.90, 5);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(29, 5, 3.62, 2);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(6, 6, 3.64, 1);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(10, 6, 2.98, 2);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(20, 6, 4.00, 5);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(23, 6, 3.55, 1);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(28, 7, 3.64, 2);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(9, 7, 3.64, 3);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(14, 7, 3.64, 1);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(5, 7, 3.64, 1);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(10, 8, 2.98, 1);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(15, 8, 2.98, 1);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(10, 9, 2.98, 2);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(4, 9, 3.64, 2);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(1, 9, 2.98, 3);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(9, 10, 3.64, 6);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(22, 11, 3.64, 1);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(27, 11, 3.55, 3);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(1, 12, 2.98, 2);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(5, 12, 3.64, 3);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(8, 12, 3.64, 3);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(11, 12, 3.64, 5);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(12, 12, 3.90, 4);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(20, 12, 4.00, 5);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(23, 12, 3.55, 3);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(24, 12, 3.98, 3);
INSERT INTO Product_Delivery(product_product_id, delivery_delivery_id, price, quantity) VALUES(28, 12, 3.64, 3);