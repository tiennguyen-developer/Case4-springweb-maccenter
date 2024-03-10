-- you must insert after create entity

INSERT INTO `preprojectdb`.`category` (`name`)
VALUES ('Mac');
INSERT INTO `preprojectdb`.`category` (`name`)
VALUES ('Ipad');
INSERT INTO `preprojectdb`.`category` (`name`)
VALUES ('Iphone');
INSERT INTO `preprojectdb`.`category` (`name`)
VALUES ('Watch');


INSERT INTO `preprojectdb`.`roles` (`name`)
VALUES ('ROLE_ADMIN');
INSERT INTO `preprojectdb`.`roles` (`name`)
VALUES ('ROLE_USER');



INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin MacBook 1', 'mb1.jpg', 'MacBook mẫu 1', '30111000', '12000', '1');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin MacBook 2', 'mb2.jpg', 'MacBook mẫu 2', '30222000', '12000', '1');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin MacBook 3', 'mb3.jpg', 'MacBook mẫu 3', '30333000', '12000', '1');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin MacBook 4', 'mb4.jpg', 'MacBook mẫu 4', '30444000', '12000', '1');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin MacBook 5', 'mb1.jpg', 'MacBook mẫu 5', '30555000', '12000', '1');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin MacBook 6', 'mb2.jpg', 'MacBook mẫu 6', '30666000', '12000', '1');

INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin iPad 1', 'ipad1.jpg', 'iPad mẫu 1', '9111000', '680', '2');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin iPad 2', 'ipad2.jpg', 'iPad mẫu 2', '9222000', '680', '2');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin iPad 3', 'ipad3.jpg', 'iPad mẫu 3', '9333000', '680', '2');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin iPad 4', 'ipad4.jpg', 'iPad mẫu 4', '9444000', '680', '2');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin iPad 5', 'ipad1.jpg', 'iPad mẫu 5', '9555000', '680', '2');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin iPad 6', 'ipad2.jpg', 'iPad mẫu 6', '9666000', '680', '2');

INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin iPhone 1', 'iphone1.jpg', 'iPhone mẫu 1', '19111000', '180', '3');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin iPhone 2', 'iphone2.jpg', 'iPhone mẫu 2', '19222000', '180', '3');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin iPhone 3', 'iphone3.jpg', 'iPhone mẫu 3', '19333000', '180', '3');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin iPhone 4', 'iphone4.jpg', 'iPhone mẫu 4', '19444000', '180', '3');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin iPhone 5', 'iphone1.jpg', 'iPhone mẫu 5', '19555000', '180', '3');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin iPhone 6', 'iphone2.jpg', 'iPhone mẫu 6', '19666000', '180', '3');

INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin Watch 1', 'w1.jpg', 'Watch mẫu 1', '8111000', '38', '4');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin Watch 2', 'w2.jpg', 'Watch mẫu 2', '8222000', '38', '4');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin Watch 3', 'w3.jpg', 'Watch mẫu 3', '8333000', '38', '4');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin Watch 4', 'w4.jpg', 'Watch mẫu 4', '8444000', '38', '4');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin Watch 5', 'w1.jpg', 'Watch mẫu 5', '8555000', '38', '4');
INSERT INTO `preprojectdb`.`product` (`description`, `image_name`, `name`, `price`, `weight`, `category_id`)
VALUES ('Thông tin Watch 6', 'w2.jpg', 'Watch mẫu 6', '8666000', '38', '4');

INSERT INTO users(id, email, first_name, last_name, `password`)
VALUES ('adm@gmail.com', 'admin', 'admin', 'adm1234');


INSERT INTO `preprojectdb`.`users` (`id`, `email`, `first_name`, `last_name`, `password`, `ID`)
VALUES ('thaychinh@gmail.com', 'thay', 'chinh', 'chinh123', `1`);


INSERT INTO `preprojectdb`.`users` (`email`, `first_name`, `last_name`, `password`)
VALUES ('thaychinh@gmail.com', 'thay', 'chinh', 'chinh123');
INSERT INTO `preprojectdb`.`users_role` (`user_id`, `role_id`)
VALUES ('11', '1');
INSERT INTO `preprojectdb`.`users_role` (`user_id`, `role_id`)
VALUES ('11', '2');

INSERT INTO `preprojectdb`.`users` (`email`, `first_name`, `last_name`, `password`)
VALUES ('adm1@gmail.com', 'adm', 'admin', 'adm123');
INSERT INTO `preprojectdb`.`users_role` (`users_id`, `role_id`)
VALUES ('2', '1');
INSERT INTO `preprojectdb`.`users_role` (`users_id`, `role_id`)
VALUES ('2', '2');

INSERT INTO `preprojectdb`.`users` (`email`, `first_name`, `last_name`, `password`)
VALUES ('khach@gmail.com', 'khac', 'khach hàng', 'khach123');
INSERT INTO `preprojectdb`.`users_role` (`users_id`, `role_id`)
VALUES ('3', '2');
