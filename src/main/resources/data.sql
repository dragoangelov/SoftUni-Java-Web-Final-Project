USE `delivery_service`;

INSERT INTO `products` (name, price, description,category) VALUES ('Margherita',7.99,
                                                                'Tomato sauce, mozzarella, spices','pizza');
INSERT INTO `products` (name, price, description,category) VALUES ('Caprese',8.99,
                                                                'Tomato sauce, mozzarella, baby mozzarella, cherry tomatoes,
                                                                 basil pesto','pizza');
INSERT INTO `products` (name, price, description,category) VALUES ('Quattro Formaggi',10.99,
                                                                'Philadelphia sauce, smoked cheese, blue cheese,
                                                                 cheddar, pesto, mozzarella, cherry tomatoes','pizza');
INSERT INTO `products` (name, price, description,category) VALUES ('Quattro Staggioni',10.99,
                                                                'Tomato sauce, mozzarella, ham, bacon, mushrooms, pickles','pizza');
INSERT INTO `products` (name, price, description,category) VALUES ('Di Montagna',9.99,
                                                                'Tomato sauce, mozzarella, ham, bacon, sausage, mushrooms, spices','pizza');
INSERT INTO `products` (name, price, description,category) VALUES ('Pepperoni',10.99,
                                                                'Tomato sauce, mozzarella, hot Italian sausage','pizza');
INSERT INTO `products` (name, price, description,category) VALUES ('Pizza with prosciutto',11.99,
                                                                'Tomato sauce, mozzarella, baby spinach,
                                                                 cherry tomatoes, prosciutto, parmesan','pizza');
INSERT INTO `products` (name, price, description,category) VALUES ('Capricciosa',9.99,
                                                                'Tomato sauce, cheese, ham, mushrooms, olive oil, oregano','pizza');

INSERT INTO `products` (name, price, description,category) VALUES ('Brownie',4.99,'With ice cream','dessert');
INSERT INTO `products` (name, price, description,category) VALUES ('Cheesecake',6.99,'With blueberries','dessert');
INSERT INTO `products` (name, price, description,category) VALUES ('Cake', 4.99,'Filled with cream','dessert');
INSERT INTO `products` (name, price, description,category) VALUES ('Pancake',4.79,'Filled with vanilla cream','dessert');

INSERT INTO `products` (name, price, description,category) VALUES ('Coca cola',1.99,'0.5L','drink');
INSERT INTO `products` (name, price, description,category) VALUES ('Fanta',1.99,'0.5L','drink');
INSERT INTO `products` (name, price, description,category) VALUES ('Sprite',1.99,'0.5L','drink');
INSERT INTO `products` (name, price, description,category) VALUES ('Orange juice',1.99,'0.330L','drink');
INSERT INTO `products` (name, price, description,category) VALUES ('Water',1.00,'0.5L','drink');
INSERT INTO `products` (name, price, description,category) VALUES ('Beer',4.99,'0.5L','drink');

INSERT INTO `users` (age, email, first_name, gender, last_name, password, phone_number, username) VALUES (23, 'admin@bg.bg', 'Admin', 'MALE', 'Adminov', '123123', 0899444333, 'admin');
INSERT INTO `users` (age, email, first_name, gender, last_name, password, phone_number, username) VALUES (32, 'user@bg.bg', 'User', 'MALE', 'Userov', '123123', 0899444222, 'user');

INSERT INTO `roles` (id, role) VALUES (1, 'ADMIN');
INSERT INTO `roles` (id, role) VALUES (2, 'USER');

INSERT INTO `users_roles` (user_entity_id, roles_id) VALUES (1, 1);
INSERT INTO `users_roles` (user_entity_id, roles_id) VALUES (2, 2);
