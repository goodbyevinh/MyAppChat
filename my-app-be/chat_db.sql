CREATE TABLE IF NOT EXISTS role (
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS users(
	id int GENERATED ALWAYS AS IDENTITY,
    email varchar(255) unique,
    password varchar(255),
    fullname varchar(100),
    phone varchar(100),
    address varchar(100),
    role_id INT,
    date date default now(),
    primary key(id),
    constraint fk_role foreign key(role_id) references role(id)
);

CREATE TABLE IF NOT EXISTS category (
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS brand (
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS product (
    id INT GENERATED ALWAYS AS IDENTITY,
    name varchar(255),
    main_image varchar(100),
	amount_of_sold int default 0,
    price int,
    category_id int,
    brand_id int,
    PRIMARY KEY (id),
    constraint fk_category foreign key(category_id) references category(id),
    constraint fk_brand foreign key(brand_id) references brand(id)
);
CREATE TABLE IF NOT EXISTS image_product (
    id INT GENERATED ALWAYS AS IDENTITY,
    name varchar(255),
    product_id int,
    PRIMARY KEY (id),
    constraint fk_product foreign key(product_id) references product(id)
);
CREATE TABLE IF NOT EXISTS size (
    id INT GENERATED ALWAYS AS IDENTITY,
    name varchar(10),
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS product_size (
	product_id int,
    size_id int,
    amount int DEFAULT 0,
    primary key(product_id,size_id),
    constraint fk_product foreign key(product_id) references product(id),
    constraint fk_size foreign key(size_id) references size(id)
);

CREATE TABLE IF NOT EXISTS bookmark_product(
	user_id int,
    product_id int,
    primary key(user_id, product_id),
    constraint fk_user foreign key(user_id) references users(id),
    constraint fk_product foreign key(product_id) references product(id)
);

CREATE TABLE IF NOT EXISTS coupon (
    id INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(50) unique not null,
    rate float not null,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS status (
	id int GENERATED ALWAYS AS IDENTITY,
    name varchar(50),
    primary key(id)
);
CREATE TABLE IF NOT EXISTS p_order(
	id INT GENERATED ALWAYS AS IDENTITY,
    fee_ship int,
    coupon float,
    temp_total int,
    total float,
    order_token varchar(255),
    user_id int,
    delivery_address varchar(255),
    status_id int,
    date date default now(),
    primary key(id),
    constraint fk_status foreign key(status_id) references status(id),
    constraint fk_user foreign key(user_id) references users(id)
);
CREATE TABLE IF NOT EXISTS product_order (
	order_id int,
    product_id int,
    size varchar(10),
    amount int,
    price int,
    primary key(order_id,product_id, size),
    constraint fk_order foreign key(order_id) references p_order(id),
    constraint fk_product foreign key(product_id) references product(id)
);

INSERT INTO role( name, description ) VALUES ('ROLE_ADMIN', 'Admin');
INSERT INTO role( name, description ) VALUES ('ROLE_USER', 'Customer');


INSERT INTO status(name) VALUES ('Chưa xác nhận');
INSERT INTO status(name) VALUES ('Xác nhận');
INSERT INTO status(name) VALUES ('Hoàn thành');
INSERT INTO status(name) VALUES ('Đã hủy');

INSERT INTO user( email, password, fullname, role_id ) VALUES ('admin@gmail.com', '$2a$10$IhrjhZcZiATnxdnUVZlxC.1sF5bflxtiFCxpaeCvYtMnykred/WR6', 'ADMIN', 1);
INSERT INTO user( email, password, fullname, role_id )
VALUES ('daidung@gmail.com', '$2a$10$IhrjhZcZiATnxdnUVZlxC.1sF5bflxtiFCxpaeCvYtMnykred/WR6', 'Dai Dung', 2);

INSERT INTO brand(name) VALUES ('Adidas');
INSERT INTO brand(name) VALUES ('Nike');
INSERT INTO brand(name) VALUES ('Jordan');
INSERT INTO brand(name) VALUES ('Puma');
INSERT INTO brand(name) VALUES ('Converse');
INSERT INTO brand(name) VALUES ('Vans');

INSERT INTO product(name, price, brand_id, amount_of_sold ) VALUES ('
Adidas FLB_Runner Shoes - Black (CQ1970)', 699000, 1, 3);
INSERT INTO product(name, price, brand_id ) VALUES ('
Adidas Superstar Slipon Core Black (S81337/BZ0112)', 1550000, 1);
INSERT INTO product(name, price, brand_id ) VALUES ('
Adidas Continental 80 Shoes - Crystal White (EF2101)', 1290000, 1);
INSERT INTO product(name, price, brand_id, amount_of_sold ) VALUES ('
Adidas Stan Smith Green (M20324)', 1550000, 1, 5);
INSERT INTO product(name, price, brand_id ) VALUES ('
Adidas Alphabounce Beyond Core Black W (CG5581)', 999000, 1);
INSERT INTO product(name, price, brand_id, amount_of_sold ) VALUES ('
Adidas Superstar Gold Label (C77124/EG4958)', 1850000, 1, 9);
INSERT INTO product(name, price, brand_id ) VALUES ('
Adidas Superstar Gold Label (FV3284)', 1650000, 1);
INSERT INTO product(name, price, brand_id ) VALUES ('
Adidas Ultraboost 4.0 Shoes - Triple Black (EH1420)', 2990000, 1);
INSERT INTO product(name, price, brand_id , amount_of_sold) VALUES ('
Adidas Ultraboost Reflective Shoes - Coreblack (EG8105)', 2490000, 1, 14);
INSERT INTO product(name, price, brand_id , amount_of_sold) VALUES ('
Nike Court Legacy White Black Canvas (CW6539-101)', 1450000, 2, 7);
INSERT INTO product(name, price, brand_id ) VALUES ('
Nike Court Legacy White Desert Ochre (DA5380-102)', 1450000, 2);
INSERT INTO product(name, price, brand_id ) VALUES ('
Nike Zoom Gravity Metallic Silver (BQ3202-001 / BQ3203-002)', 999000, 2);
INSERT INTO product(name, price, brand_id ) VALUES ('
Nike Court Legacy White Desert Ochre Next Nature (DH3162-100)', 1850000, 2);
INSERT INTO product(name, price, brand_id, amount_of_sold ) VALUES ('
Nike Blazer Low 77 Vintage (DA6364-101)', 2450000, 2, 1);
INSERT INTO product(name, price, brand_id ) VALUES ('
Jordan 1 Low Vintage Grey (553558-053)', 5150000, 3);
INSERT INTO product(name, price, brand_id ) VALUES ('
Jordan 1 Low Shadow Toe - Smoke V4 (553558-052)', 5650000, 3);
INSERT INTO product(name, price, brand_id ) VALUES ('
Jordan 1 Retro High OG Bordeaux (575441-611)', 3990000, 3);
INSERT INTO product(name, price, brand_id ) VALUES ('
Jordan 1 Low Bred Toe (553558-612)', 3990000, 3);
INSERT INTO product(name, price, brand_id ) VALUES ('
Jordan 1 Low White Wolf Grey (DC0774-105)', 7050000, 3);
INSERT INTO product(name, price, brand_id ) VALUES ('
Puma Smash Vulc Mule - Black (309680-02)', 999000, 4);
INSERT INTO product(name, price, brand_id , amount_of_sold) VALUES ('
Puma Smash Vulc Mule - White (309680-01)', 999000, 4, 2);
INSERT INTO product(name, price, brand_id ) VALUES ('
Puma Bari Mule Black (371318-01)', 999000, 4);


INSERT INTO image_product(name, product_id ) VALUES ('
adidas-fub-runner1.webp' ,1);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-fub-runner2.webp' ,1);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-fub-runner3.webp' ,1);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-fub-runner4.webp' ,1);


INSERT INTO image_product(name, product_id ) VALUES ('
adidas-superstar-slipon1.webp' ,2);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-superstar-slipon2.webp' ,2);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-superstar-slipon3.webp' ,2);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-superstar-slipon4.webp' ,2);

INSERT INTO image_product(name, product_id ) VALUES ('
adidas-continental-shoes1.webp' ,3);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-continental-shoes2.webp' ,3);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-continental-shoes3.webp' ,3);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-continental-shoes4.webp' ,3);

INSERT INTO image_product(name, product_id ) VALUES ('
adidas-stan-smith-green1.webp' ,4);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-stan-smith-green2.webp' ,4);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-stan-smith-green3.webp' ,4);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-stan-smith-green4.webp' ,4);


INSERT INTO image_product(name, product_id ) VALUES ('
adidas-alphabounce-beyond1.webp' ,5);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-alphabounce-beyond2.webp' ,5);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-alphabounce-beyond3.webp' ,5);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-alphabounce-beyond4.webp' ,5);

INSERT INTO image_product(name, product_id ) VALUES ('
adidas-superstar-gold-label1.webp' ,6);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-superstar-gold-label2.webp' ,6);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-superstar-gold-label3.webp' ,6);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-superstar-gold-label4.webp' ,6);

INSERT INTO image_product(name, product_id ) VALUES ('
adidas-superstar-gold-labelFV3284-1.webp' ,7);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-superstar-gold-labelFV3284-2.webp' ,7);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-superstar-gold-labelFV3284-3.webp' ,7);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-superstar-gold-labelFV3284-4.webp' ,7);

INSERT INTO image_product(name, product_id ) VALUES ('
adidas-ultraboost-shoes-1.webp' ,8);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-ultraboost-shoes-2.webp' ,8);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-ultraboost-shoes-3.webp' ,8);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-ultraboost-shoes-4.webp' ,8);

INSERT INTO image_product(name, product_id ) VALUES ('
adidas-ultraboost-reflective-shoes1.webp' ,9);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-ultraboost-reflective-shoes2.webp' ,9);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-ultraboost-reflective-shoes3.webp' ,9);
INSERT INTO image_product(name, product_id ) VALUES ('
adidas-ultraboost-reflective-shoes4.webp' ,9);

INSERT INTO image_product(name, product_id ) VALUES ('
nike-court-legacy-white-black-canvas1.webp' ,10);
INSERT INTO image_product(name, product_id ) VALUES ('
nike-court-legacy-white-black-canvas2.webp' ,10);
INSERT INTO image_product(name, product_id ) VALUES ('
nike-court-legacy-white-black-canvas3.webp' ,10);
INSERT INTO image_product(name, product_id ) VALUES ('
nike-court-legacy-white-black-canvas4.webp' ,10);

INSERT INTO image_product(name, product_id ) VALUES ('
nike-court-legacy-white-desert-ochre1.webp' ,11);
INSERT INTO image_product(name, product_id ) VALUES ('
nike-court-legacy-white-desert-ochre2.webp' ,11);
INSERT INTO image_product(name, product_id ) VALUES ('
nike-court-legacy-white-desert-ochre3.webp' ,11);
INSERT INTO image_product(name, product_id ) VALUES ('
nike-court-legacy-white-desert-ochre4.webp' ,11);

INSERT INTO image_product(name, product_id ) VALUES ('
nike-zoom-gravity-metallic-silver1.webp' ,12);
INSERT INTO image_product(name, product_id ) VALUES ('
nike-zoom-gravity-metallic-silver2.webp' ,12);
INSERT INTO image_product(name, product_id ) VALUES ('
nike-zoom-gravity-metallic-silver3.webp' ,12);
INSERT INTO image_product(name, product_id ) VALUES ('
nike-zoom-gravity-metallic-silver4.webp' ,12);

INSERT INTO image_product(name, product_id ) VALUES ('
nike-court-legacy-white-desert-ochre11.webp' ,13);
INSERT INTO image_product(name, product_id ) VALUES ('
nike-court-legacy-white-desert-ochre12.webp' ,13);
INSERT INTO image_product(name, product_id ) VALUES ('
nike-court-legacy-white-desert-ochre13.webp' ,13);
INSERT INTO image_product(name, product_id ) VALUES ('
nike-court-legacy-white-desert-ochre14.webp' ,13);

INSERT INTO image_product(name, product_id ) VALUES ('
nike-blazer-low77-vintage1.webp' ,14);
INSERT INTO image_product(name, product_id ) VALUES ('
nike-blazer-low77-vintage2.webp' ,14);
INSERT INTO image_product(name, product_id ) VALUES ('
nike-blazer-low77-vintage3.webp' ,14);
INSERT INTO image_product(name, product_id ) VALUES ('
nike-blazer-low77-vintage4.webp' ,14);

INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-low-vintage-grey1.webp' ,15);
INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-low-vintage-grey2.webp' ,15);
INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-low-vintage-grey3.webp' ,15);
INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-low-vintage-grey4.webp' ,15);

INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-low-shadow-toe1.webp' ,16);
INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-low-shadow-toe2.webp' ,16);
INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-low-shadow-toe3.webp' ,16);
INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-low-shadow-toe4.webp' ,16);

INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-retro-high-og1.webp' ,17);
INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-retro-high-og2.webp' ,17);
INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-retro-high-og3.webp' ,17);
INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-retro-high-og4.webp' ,17);

INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-low-bred-toe1.webp' ,18);
INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-low-bred-toe2.webp' ,18);
INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-low-bred-toe3.webp' ,18);
INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-low-bred-toe4.webp' ,18);

INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-low-white-wolf-grey1.webp' ,19);
INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-low-white-wolf-grey2.webp' ,19);
INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-low-white-wolf-grey3.webp' ,19);
INSERT INTO image_product(name, product_id ) VALUES ('
jordan1-low-white-wolf-grey4.webp' ,19);

INSERT INTO image_product(name, product_id ) VALUES ('
puma-smash-vulc-mule-black1.webp' ,20);
INSERT INTO image_product(name, product_id ) VALUES ('
puma-smash-vulc-mule-black2.webp' ,20);
INSERT INTO image_product(name, product_id ) VALUES ('
puma-smash-vulc-mule-black3.webp' ,20);
INSERT INTO image_product(name, product_id ) VALUES ('
puma-smash-vulc-mule-black4.webp' ,20);

INSERT INTO image_product(name, product_id ) VALUES ('
puma-smash-vulc-mule-white1.webp' ,21);
INSERT INTO image_product(name, product_id ) VALUES ('
puma-smash-vulc-mule-white2.webp' ,21);
INSERT INTO image_product(name, product_id ) VALUES ('
puma-smash-vulc-mule-white3.webp' ,21);
INSERT INTO image_product(name, product_id ) VALUES ('
puma-smash-vulc-mule-white4.webp' ,21);

INSERT INTO image_product(name, product_id ) VALUES ('
puma-bari-mule-black1.webp' ,22);
INSERT INTO image_product(name, product_id ) VALUES ('
puma-bari-mule-black2.webp' ,22);
INSERT INTO image_product(name, product_id ) VALUES ('
puma-bari-mule-black3.webp' ,22);
INSERT INTO image_product(name, product_id ) VALUES ('
puma-bari-mule-black4.webp' ,22);


INSERT INTO size (name) VALUES ('35');
INSERT INTO size (name) VALUES ('35.5');
INSERT INTO size (name) VALUES ('36');
INSERT INTO size (name) VALUES ('36.5');
INSERT INTO size (name) VALUES ('37');
INSERT INTO size (name) VALUES ('37.5');
INSERT INTO size (name) VALUES ('38');
INSERT INTO size (name) VALUES ('38.5');
INSERT INTO size (name) VALUES ('39');
INSERT INTO size (name) VALUES ('39.5');
INSERT INTO size (name) VALUES ('40');
INSERT INTO size (name) VALUES ('40.5');
INSERT INTO size (name) VALUES ('41');
INSERT INTO size (name) VALUES ('41.5');
INSERT INTO size (name) VALUES ('42');
INSERT INTO size (name) VALUES ('42.5');
INSERT INTO size (name) VALUES ('43');
INSERT INTO size (name) VALUES ('43.5');
INSERT INTO size (name) VALUES ('44');
INSERT INTO size (name) VALUES ('44.5');



INSERT INTO category (name) VALUES ('Giày chạy bộ');
INSERT INTO category (name) VALUES ('Giày đi bộ');
INSERT INTO category (name) VALUES ('Giày tennis');
INSERT INTO category (name) VALUES ('Giày đá bóng');
INSERT INTO category (name) VALUES ('Giày địa hình');



UPDATE product SET category_id = '1' WHERE (id = '1');
UPDATE product SET category_id = '2' WHERE (id = '2');
UPDATE product SET category_id = '3' WHERE (id = '3');
UPDATE product SET category_id = '4' WHERE (id = '4');
UPDATE product SET category_id = '1' WHERE (id = '5');
UPDATE product SET category_id = '2' WHERE (id = '6');
UPDATE product SET category_id = '3' WHERE (id = '7');
UPDATE product SET category_id = '4' WHERE (id = '8');
UPDATE product SET category_id = '1' WHERE (id = '9');
UPDATE product SET category_id = '2' WHERE (id = '10');
UPDATE product SET category_id = '3' WHERE (id = '11');
UPDATE product SET category_id = '4' WHERE (id = '12');
UPDATE product SET category_id = '1' WHERE (id = '13');
UPDATE product SET category_id = '2' WHERE (id = '14');
UPDATE product SET category_id = '3' WHERE (id = '15');
UPDATE product SET category_id = '4' WHERE (id = '16');
UPDATE product SET category_id = '1' WHERE (id = '17');
UPDATE product SET category_id = '2' WHERE (id = '18');
UPDATE product SET category_id = '3' WHERE (id = '19');
UPDATE product SET category_id = '4' WHERE (id = '20');
UPDATE product SET category_id = '1' WHERE (id = '21');
UPDATE product SET category_id = '2' WHERE (id = '22');



INSERT INTO product_size (product_id, size_id, amount) VALUES ('12', '3', 2);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('13', '2', 2);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('14', '2', 2);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('14', '3', 2);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('15', '1', 2);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('16', '7', 2);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('17', '6', 2);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('18', '7', 2);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('19', '2', 2);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('20', '5', 2);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('21', '10', 2);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('22', '1', 2);

INSERT INTO product_size (product_id, size_id, amount) VALUES ('1', '1', 2);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('1', '2', 2);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('1', '3', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('1', '4', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('1', '5', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('1', '6', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('1', '7', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('1', '8', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('1', '9', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('2', '1', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('2', '2', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('2', '3', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('2', '4', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('2', '5', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('2', '6', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('2', '7', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('2', '8', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('3', '1', 1);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('3', '2', 1);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('3', '3', 1);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('3', '4', 1);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('3', '5', 1);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('3', '6', 1);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('3', '7', 1);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('4', '1', 1);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('4', '2', 1);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('4', '3', 1);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('4', '4', 1);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('4', '5', 1);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('4', '6', 1);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('5', '1', 1);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('5', '2', 1);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('5', '3', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('5', '4', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('5', '5', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('5', '6', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('5', '7', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('5', '8', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('6', '1', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('6', '2', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('6', '3', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('6', '4', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('6', '5', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('6', '6', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('6', '7', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('7', '1', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('7', '2', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('7', '3', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('7', '4', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('7', '5', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('7', '6', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('7', '7', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('7', '8', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('7', '9', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('8', '1', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('8', '2', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('8', '3', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('8', '4', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('8', '5', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('8', '6', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('8', '7', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('8', '8', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('9', '1', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('9', '2', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('9', '3', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('9', '4', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('9', '5', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('9', '6', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('9', '7', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('9', '8', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('9', '9', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('10', '1', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('10', '2', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('10', '3', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('10', '4', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('10', '5', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('10', '6', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('10', '7', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('10', '8', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('10', '9', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('10', '10',3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('11', '1', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('11', '2', 3);
INSERT INTO product_size (product_id, size_id, amount) VALUES ('11', '3', 3);

UPDATE product SET main_image = 'adidas-alphabounce-beyond1.webp' WHERE (id = '5');
UPDATE product SET main_image = 'adidas-continental-shoes4.webp' WHERE (id = '3');
UPDATE product SET main_image = 'adidas-fub-runner2.webp' WHERE (id = '1');
UPDATE product SET main_image = 'adidas-stan-smith-green1.webp' WHERE (id = '4');
UPDATE product SET main_image = 'adidas-superstar-gold-label1.webp' WHERE (id = '6');
UPDATE product SET main_image = 'adidas-superstar-gold-labelFV3284-1.webp' WHERE (id = '7');
UPDATE product SET main_image = 'adidas-superstar-slipon1.webp' WHERE (id = '2');
UPDATE product SET main_image = 'adidas-ultraboost-reflective-shoes1.webp' WHERE (id = '9');
UPDATE product SET main_image = 'adidas-ultraboost-shoes-1.webp' WHERE (id = '8');
UPDATE product SET main_image = 'nike-court-legacy-white-desert-ochre13.webp' WHERE (id = '11');
UPDATE product SET main_image = 'nike-blazer-low77-vintage1.webp' WHERE (id = '14');
UPDATE product SET main_image = 'nike-court-legacy-white-desert-ochre1.webp' WHERE (id = '13');
UPDATE product SET main_image = 'nike-court-legacy-white-black-canvas1.webp' WHERE (id = '10');
UPDATE product SET main_image = 'nike-zoom-gravity-metallic-silver3.webp' WHERE (id = '12');
UPDATE product SET main_image = 'jordan1-low-vintage-grey1.webp' WHERE (id = '15');
UPDATE product SET main_image = 'jordan1-low-shadow-toe1.webp' WHERE (id = '16');
UPDATE product SET main_image = 'jordan1-low-white-wolf-grey3.webp' WHERE (id = '19');
UPDATE product SET main_image = 'jordan1-retro-high-og1.webp' WHERE (id = '17');
UPDATE product SET main_image = 'jordan1-low-bred-toe1.webp' WHERE (id = '18');
UPDATE product SET main_image = 'puma-bari-mule-black1.webp' WHERE (id = '20');
UPDATE product SET main_image = 'puma-smash-vulc-mule-black1.webp' WHERE (id = '21');
UPDATE product SET main_image = 'puma-smash-vulc-mule-white1.webp' WHERE (id = '22');



INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '3', 'hcm');

INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '2', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '2', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '2', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '2', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '2', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '2', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '2', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '2', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '2', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '2', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '2', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '2', 'hcm');
INSERT INTO p_order (fee_ship, coupon, temp_total, total, user_id, status_id, delivery_address) VALUES ('40000', '40000', '1000000', '15000000', '1', '2', 'hcm');

INSERT INTO coupon (name, rate) VALUES ('ASVAS1', '5');
INSERT INTO coupon (name, rate) VALUES ('ASVAS2', '5');
INSERT INTO coupon (name, rate) VALUES ('ASVAS3', '5');
INSERT INTO coupon (name, rate) VALUES ('ASVAS4', '5');
INSERT INTO coupon (name, rate) VALUES ('ASVAS5', '5');
INSERT INTO coupon (name, rate) VALUES ('ASV2AS3', '5');
INSERT INTO coupon (name, rate) VALUES ('AV2AS3', '5');
INSERT INTO coupon (name, rate) VALUES ('AS2AS4', '5');
INSERT INTO coupon (name, rate) VALUES ('AS3AS5', '5');
INSERT INTO coupon (name, rate) VALUES ('ASVAS6', '5');
INSERT INTO coupon (name, rate) VALUES ('SV3AS8', '5');
INSERT INTO coupon (name, rate) VALUES ('ASVAS8', '5');
INSERT INTO coupon (name, rate) VALUES ('AV3AS4', '5');
INSERT INTO coupon (name, rate) VALUES ('ASV3Sd', '5');
INSERT INTO coupon (name, rate) VALUES ('AS4AS12', '5');
INSERT INTO coupon (name, rate) VALUES ('ASVASa', '5');




INSERT INTO bookmark_product (user_id, product_id) VALUES ('1', '1');
INSERT INTO bookmark_product (user_id, product_id) VALUES ('1', '2');
INSERT INTO bookmark_product (user_id, product_id) VALUES ('1', '3');

INSERT INTO role (name, description) VALUES ('ROLE_ANONYMOUS', 'anonymous');

INSERT INTO coupon (name, rate) VALUES ('GIAMGIA10%', '10');
INSERT INTO coupon (name, rate) VALUES ('GIAMGIA20%', '20');
INSERT INTO coupon (name, rate) VALUES ('GIAMGIA30%', '30');
INSERT INTO coupon (name, rate) VALUES ('GIAMGIA40%', '40');
INSERT INTO coupon (name, rate) VALUES ('GIAMGIA50%', '50');

========================================


CREATE TABLE IF NOT EXISTS account(
	id int GENERATED ALWAYS AS IDENTITY,
    email varchar(255) unique,
    password varchar(255),
    fullname varchar(100),
    role_id INT default 2,
    date date default now(),
    is_oauth2 boolean,
    primary key(id),
    constraint fk_role foreign key(role_id) references role(id)
);
CREATE TABLE IF NOT EXISTS account_account(
	account_me_id int,
	account_other_id int,
	primary key (account_me_id, account_other_id),
	constraint fk_accountMe foreign key (account_me_id) references account(id),
	constraint fk_accountOther foreign key (account_other_id) references account(id)
);
CREATE TABLE IF NOT EXISTS c_group(
	id int GENERATED ALWAYS AS IDENTITY,
	name varchar(255),
	account_id int,
	primary key(id),
	constraint fk_account foreign key(account_id) references account(id)
);

CREATE TABLE IF NOT EXISTS group_account(
	account_id int,
	group_id int,
	primary key (account_id, group_id),
	constraint fk_account foreign key (account_id) references account(id),
	constraint fk_group foreign key (group_id) references c_group(id)
); 

CREATE TABLE IF NOT EXISTS invite_friend(
	account_me_id int,
	account_friend_id int,
	primary key (account_me_id, account_friend_id),
	constraint fk_accountMe foreign key (account_me_id) references account(id),
	constraint fk_accountFriend foreign key (account_friend_id) references account(id)
);

select a2
from account a1
inner join account_account aa on a1.id == aa.account_me_id 
inner join account a2 on aa.account_other_id == a2.id  
where a1.id = 8


select a2
from account a1
inner join invite_friend inf on a1.id = inf.account_me_id 
inner join account a2 on inf.account_friend_id = a2.id 
where a1.id = 8





INSERT INTO account( email, password, fullname, role_id ) VALUES ('admin@gmail.com', '$2a$10$IhrjhZcZiATnxdnUVZlxC.1sF5bflxtiFCxpaeCvYtMnykred/WR6', 'ADMIN', 1);


