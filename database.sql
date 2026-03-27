-- Create Database
CREATE DATABASE epharmacy;
USE epharmacy;

-- Customer Table
CREATE TABLE customer (
  customer_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  dob DATE NOT NULL,
  contact_number VARCHAR(10) NOT NULL,
  gender VARCHAR(10),
  plan VARCHAR(20) DEFAULT 'Regular',
  plan_expiry_date DATE,
  health_coins INT DEFAULT 0
);

-- Address Table
CREATE TABLE address (
  address_id INT AUTO_INCREMENT PRIMARY KEY,
  customer_id INT NOT NULL,
  address_name VARCHAR(50),
  address_line1 VARCHAR(255),
  address_line2 VARCHAR(255),
  area VARCHAR(100),
  city VARCHAR(100),
  state VARCHAR(100),
  pincode VARCHAR(6),
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- Medicine Table
CREATE TABLE medicine (
  medicine_id INT AUTO_INCREMENT PRIMARY KEY,
  medicine_name VARCHAR(100) NOT NULL,
  manufacturer VARCHAR(100),
  category VARCHAR(50),
  manufacturing_date DATE,
  expiry_date DATE,
  price DECIMAL(10,2),
  discount_percent INT,
  stock_quantity INT DEFAULT 0
);

-- Cart Table
CREATE TABLE cart (
  cart_id INT AUTO_INCREMENT PRIMARY KEY,
  customer_id INT NOT NULL,
  medicine_id INT NOT NULL,
  quantity INT NOT NULL,
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
  FOREIGN KEY (medicine_id) REFERENCES medicine(medicine_id)
);

-- Orders Table
CREATE TABLE orders (
  order_id INT AUTO_INCREMENT PRIMARY KEY,
  customer_id INT NOT NULL,
  order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  order_status VARCHAR(20) DEFAULT 'PROCESSING',
  delivery_status VARCHAR(30) DEFAULT 'AWAITING_CONFIRMATION',
  delivery_address_id INT,
  total_value DECIMAL(10,2),
  discount_applied DECIMAL(10,2),
  final_amount DECIMAL(10,2),
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
  FOREIGN KEY (delivery_address_id) REFERENCES address(address_id)
);

-- Order_Items Table
CREATE TABLE order_items (
  order_item_id INT AUTO_INCREMENT PRIMARY KEY,
  order_id INT NOT NULL,
  medicine_id INT NOT NULL,
  quantity INT NOT NULL,
  price DECIMAL(10,2),
  FOREIGN KEY (order_id) REFERENCES orders(order_id),
  FOREIGN KEY (medicine_id) REFERENCES medicine(medicine_id)
);

-- Payment Table
CREATE TABLE payment (
  payment_id INT AUTO_INCREMENT PRIMARY KEY,
  order_id INT NOT NULL,
  customer_id INT NOT NULL,
  card_id VARCHAR(50),
  amount DECIMAL(10,2),
  payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  payment_status VARCHAR(20) DEFAULT 'SUCCESS',
  FOREIGN KEY (order_id) REFERENCES orders(order_id),
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- Cards Table
CREATE TABLE cards (
  card_id VARCHAR(50) PRIMARY KEY,
  customer_id INT NOT NULL,
  name_on_card VARCHAR(100),
  card_type ENUM('DEBIT','CREDIT'),
  cvv VARCHAR(4),
  expiry_date DATE,
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- Password History Table
CREATE TABLE password_history (
  history_id INT AUTO_INCREMENT PRIMARY KEY,
  customer_id INT NOT NULL,
  password VARCHAR(255) NOT NULL,
  changed_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);