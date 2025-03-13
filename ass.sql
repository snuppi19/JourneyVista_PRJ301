Create database travelApp;
Use travelApp;

--Khach hang muong tham gia-> phai dky tai khoan --user
CREATE TABLE users (
  id INT IDENTITY(1,1) PRIMARY KEY,
 fullname VARCHAR(100) DEFAULT '',
 phone_number VARCHAR(10) NOT NULL,
 address VARCHAR(200) DEFAULT '',
 password VARCHAR(100) NOT NULL, 
 created_at DATETIME,
 updated_at DATETIME,
 is_active TINYINT DEFAULT 1,
 dob DATE,
 facebook_acount_id INT DEFAULT 0,
 google_acount_id INT DEFAULT 0
);

ALTER TABLE users ADD role_id INT;
ALTER TABLE users
ADD gender BIT DEFAULT 1;
ALTER TABLE users
ADD email VARCHAR(100);
UPDATE users
SET email = 'john@example.com'
WHERE id = 1;

UPDATE users
SET email = 'jane@example.com'
WHERE id = 2;

UPDATE users
SET email = 'admin@example.com'
WHERE id = 3;

UPDATE users
SET gender = 1
WHERE id = 3;

create table roles(
  id int primary key,
  name varchar(20) not null
);
alter table users add foreign key (role_id) references roles(id);
select* from  users

Create table tokens(
    id INT IDENTITY(1,1) PRIMARY KEY,
	token varchar(255) unique not null,
	token_type varchar(50) not null,
	expiration_date datetime,
	revoked tinyint not null,
	expired tinyint not null,
	user_id int,
	--foreign key
	FOREIGN KEY (user_id) references users(id)
)

--dang nhap fb ,gg 
create table social_accounts(
 id INT IDENTITY(1,1) PRIMARY KEY,
 provider varchar(20) not null ,
 provider_id varchar(50) not null,
email varchar(150) not null,
name varchar(100) not null,
user_id int
 --foreign key
	FOREIGN KEY (user_id) references users(id)
 );
 --danh muc phuong tien di chuyen may bay oto xe khach
 create table transports(
    id INT IDENTITY(1,1) PRIMARY KEY,
	name varchar(100) not null default''
 )
--phuong tien di chuyen
 create table vehicles(
    id INT IDENTITY(1,1) PRIMARY KEY, 
	name varchar(350),
	price float not null check(price>=0),
	thumbnail varchar(300) default'',
	--thumnail dan toi hinh anh
	description varchar(200) default'',
    created_at datetime,
    updated_at datetime,
	 transports_id int,
	 foreign key(transports_id) references transports(id)
	);

--đặt phương tiện di chuyển -orders
create table orders(
   id INT IDENTITY(1,1) PRIMARY KEY, 
   user_id int,
   foreign key(user_id) references users(id),
   fullname varchar(100)default'',
   email varchar(100) default'',
   phone_number varchar(20) not null,
   address varchar(200) not null,
   note varchar(100) default'',
   order_date datetime default current_timestamp,
   status varchar(20),
   total_money float check(total_money>=0)
);
alter table orders add vehicles_id varchar(100);
alter table orders add address_end  varchar(100);
alter table orders add order_end date;
ALTER TABLE orders
ALTER COLUMN vehicles_id INT;

alter table orders add foreign key (vehicles_id) references vehicles(id);

--khi xoa 1 chuyen di-> xoa mem -> them truong active
alter table orders add active tinyint;
--trang thai chuyen di chi cho nhan 4 gia tri 
ALTER TABLE orders
ADD CONSTRAINT chk_status CHECK (status IN ('wait', 'process', 'success', 'cancelled'));




create table orders_detail (
   id INT IDENTITY(1,1) PRIMARY KEY,
   orders_id int,
   foreign key (orders_id) references orders(id),
   vehicles_id int,
   foreign key (vehicles_id) references vehicles(id),
   price float check(price>=0),
   number_of_vehicles int check(number_of_vehicles> 0),
   total_money float check(total_money >=0),
   type varchar(20) default ''
);
ALTER TABLE orders_detail
ADD number_people INT CHECK (number_people > 0);
 create table accounts(
   id INT IDENTITY(1,1) PRIMARY KEY,
	email varchar(100),
	password varchar(100),
	user_id int,
	foreign key (user_id) references users(id)
 )
 ALTER TABLE accounts ADD role_id INT;


 -- Insert roles
-- Insert roles
INSERT INTO roles (id, name) VALUES 
(1, 'Admin'),
(2, 'User');

-- Insert users
INSERT INTO users (fullname, phone_number, address, password, created_at, updated_at, is_active, dob, role_id) VALUES
('John Doe', '1234567890', '123 Main St, City', 'hashed_password_1', GETDATE(), GETDATE(), 1, '1990-01-15', 2),
('Jane Smith', '2345678901', '456 Elm St, Town', 'hashed_password_2', GETDATE(), GETDATE(), 1, '1985-07-22', 2),
('Admin User', '9876543210', '789 Oak St, Village', 'admin_password', GETDATE(), GETDATE(), 1, '1980-03-10', 1);

-- Insert transports
INSERT INTO transports (name) VALUES
('Plane'),
('Bus');

-- Insert vehicles
INSERT INTO vehicles (name, price, thumbnail, description, created_at, updated_at, transports_id) VALUES
('Boeing 737', 5000.00, 'boeing737.jpg', 'Commercial airliner', GETDATE(), GETDATE(), 1),
('Airbus A320', 4800.00, 'airbusa320.jpg', 'Short to medium-range commercial passenger jet', GETDATE(), GETDATE(), 1),
('Greyhound Bus', 100.00, 'greyhound.jpg', 'Long-distance bus', GETDATE(), GETDATE(), 2),
('City Bus', 30.00, 'citybus.jpg', 'Local transit bus', GETDATE(), GETDATE(), 2);

-- Insert orders
INSERT INTO orders (user_id, fullname, email, phone_number, address, note, order_date, status, total_money, vehicles_id, address_end, order_end, active) VALUES
(1, 'John Doe', 'john@example.com', '1234567890', '123 Main St, City', 'Business trip', GETDATE(), 'process', 5000.00, 1, 'Tokyo, Japan', DATEADD(day, 7, GETDATE()), 1),
(2, 'Jane Smith', 'jane@example.com', '2345678901', '456 Elm St, Town', 'Family vacation', GETDATE(), 'wait', 200.00, 3, 'Los Angeles, USA', DATEADD(day, 5, GETDATE()), 1);

-- Insert orders_detail
INSERT INTO orders_detail (orders_id, vehicles_id, price, number_of_vehicles, total_money, type) VALUES
(1, 1, 5000.00, 1, 5000.00, 'One-way'),
(2, 3, 100.00, 2, 200.00, 'Round-trip');

-- Insert accounts
INSERT INTO accounts (email, password, user_id, role_id) VALUES
('john@example.com', 'hashed_password_1', 1, 2),
('jane@example.com', 'hashed_password_2', 2, 2),
('admin@example.com', 'admin_password', 3, 1);

-- Insert social_accounts
INSERT INTO social_accounts (provider, provider_id, email, name, user_id) VALUES
('Facebook', 'fb_123456', 'john@example.com', 'John Doe', 1),
('Google', 'g_789012', 'jane@example.com', 'Jane Smith', 2);

-- Insert tokens (example refresh tokens)
INSERT INTO tokens (token, token_type, expiration_date, revoked, expired, user_id) VALUES
('refresh_token_123', 'REFRESH', DATEADD(day, 30, GETDATE()), 0, 0, 1),
('refresh_token_456', 'REFRESH', DATEADD(day, 30, GETDATE()), 0, 0, 2);
select  * from users
select  * from accounts
select  * from orders 
select  * from orders_detail
select  * from transports
select  * from vehicles
delete from users where id=26
delete from accounts where id=2

INSERT INTO vehicles (name, price, thumbnail, description, created_at, updated_at, transports_id)
VALUES 
('VietNam Airline', 7333, 'vnaire.png', '', GETDATE(), GETDATE(), 1),
('Vietjet', 2300, 'vietjet.png', '', GETDATE(), GETDATE(), 1),
('BamBoo Airway', 4555, 'bamboo.png', '', GETDATE(), GETDATE(), 1),
('Etihah', 8333, 'etihah.png', '', GETDATE(), GETDATE(), 1);
update vehicles set name ='Etihah' where id=8

delete  orders where id=27
delete  orders_detail where id=16
select * from orders WHERE user_id=23 and address= 'Da-Nang' and address_end='Hanoi' and total_money=5800.0
select * from orders_detail WHERE orders_id=17

delete users where id=24

select * from orders_detail

CREATE TABLE Ucskh (
    id INT IDENTITY(1,1) PRIMARY KEY, -- Tự động tăng ID bắt đầu từ 1
    user_id INT not null,                      -- Kiểu INT, cho phép NULL
    admin_id INT NOT NULL,            -- Kiểu INT, không cho phép NULL
    description VARCHAR(300)          -- Kiểu VARCHAR, tối đa 300 ký tự
);
drop table Acskh
CREATE TABLE Acskh (
    id INT IDENTITY(1,1) PRIMARY KEY,
	U_id int not null,-- Tự động tăng ID bắt đầu từ 1
    admin_id INT not null,                      -- Kiểu INT, cho phép NULL
    user_id INT NOT NULL,            -- Kiểu INT, không cho phép NULL
    description VARCHAR(300)          -- Kiểu VARCHAR, tối đa 300 ký tự
);
ALTER TABLE Acskh
ADD CONSTRAINT FK_Acskh_Ucskh
FOREIGN KEY (U_id) REFERENCES Ucskh(id);

select *from Ucskh
select *from Acskh
delete from  Ucskh where id=12
delete from  Acskh where id=8



