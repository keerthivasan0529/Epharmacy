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



---------------------------------------------------------------------Sample Data---------------------------------------------
-- expires in 8 months → should show stored discount
--INSERT INTO medicine VALUES (null, 'TestMed A', 'ABC', 'Ayurvedic', '2025-01-01', '2027-05-01', 100.00, 5, 50);

-- expires in 5 months → should show 20%
--INSERT INTO medicine VALUES (null, 'TestMed B', 'XYZ', 'Diabetes', '2025-01-01', '2027-02-01', 200.00, 8, 50);

-- Insert Sample Customers

-- INSERT INTO customer (name, email, password, dob, contact_number, gender, plan, plan_expiry_date, health_coins)
-- VALUES
--     ('Abhishek', 'abhishek@example.com', 'hashedPass1', '1990-05-12', '9876543210', 'Male', 'Regular', NULL, 50),
--     ('Keerthi', 'keerthi@example.com', 'hashedPass2', '1992-03-22', '9123456789', 'Female', 'Prime', '2026-03-22', 120),
--     ('Arun', 'arun@example.com', 'hashedPass3', '1995-07-15', '9988776655', 'Male', 'Regular', NULL, 0),
--     ('Deepti', 'deepti@example.com', 'hashedPass4', '1993-11-30', '9876501234', 'Female', 'Prime', '2026-11-30', 200),
--     ('Mahak', 'mahak@example.com', 'hashedPass5', '1988-01-10', '9765432109', 'Female', 'Regular', NULL, 10);

-- Insert Sample Addresses

-- INSERT INTO address (customer_id, address_name, address_line1, address_line2, area, city, state, pincode)
-- VALUES
--     (1, 'Home', '12 MG Road', 'Near Mall', 'Indiranagar', 'Bangalore', 'Karnataka', '560038'),
--     (2, 'Work', 'Tech Park', 'Phase 2', 'Whitefield', 'Bangalore', 'Karnataka', '560066'),
--     (3, 'Home', '23 Gandhi Street', 'Near Temple', 'Koramangala', 'Bangalore', 'Karnataka', '560034'),
--     (4, 'Home', '45 Residency Rd', 'Opp. Metro', 'MG Road', 'Bangalore', 'Karnataka', '560025'),
--     (5, 'Work', 'IT Hub', 'Tower 3', 'Electronic City', 'Bangalore', 'Karnataka', '560100');

-- Insert Sample Medicines

-- INSERT INTO medicine (medicine_name, manufacturer, category, manufacturing_date, expiry_date, price, discount_percent, stock_quantity)
-- VALUES
--     ('Paracetamol', 'Cipla', 'Covid Essentials', '2025-01-01', '2026-01-01', 50.00, 10, 200),
--     ('Metformin', 'Sun Pharma', 'Diabetes', '2024-12-01', '2026-06-01', 120.00, 15, 150),
--     ('Ashwagandha', 'Himalaya', 'Ayurvedic', '2025-02-15', '2026-12-15', 300.00, 20, 100),
--     ('Homeopathy Drops', 'Dr. Batra', 'Homeopathy', '2025-03-01', '2026-09-01', 80.00, 5, 250),
--     ('Vitamin C Tablets', 'Zydus', 'Covid Essentials', '2025-01-20', '2026-07-20', 150.00, 10, 180);

-- Insert Sample Cart

-- INSERT INTO cart (customer_id, medicine_id, quantity)
-- VALUES
--     (1, 1, 2),
--     (2, 2, 3),
--     (3, 3, 1),
--     (4, 4, 2),
--     (5, 5, 1);

-- Insert Sample Orders

-- INSERT INTO orders (customer_id, order_date, order_status, delivery_status, delivery_address_id, total_value, discount_applied, final_amount)
-- VALUES
--     (1, NOW(), 'PROCESSING', 'AWAITING_CONFIRMATION', 1, 200.00, 20.00, 180.00),
--     (2, NOW(), 'CONFIRMED', 'IN_TRANSIT', 2, 360.00, 36.00, 324.00),
--     (3, NOW(), 'OUT_FOR_DELIVERY', 'OUT_FOR_DELIVERY', 3, 300.00, 60.00, 240.00),
--     (4, NOW(), 'DELIVERED', 'DELIVERED', 4, 160.00, 16.00, 144.00),
--     (5, NOW(), 'CANCELLED', 'CANCELLED', 5, 100.00, 0.00, 0.00);

-- Insert Sample Order_Items

-- INSERT INTO order_items (order_id, medicine_id, quantity, price)
-- VALUES
--     (1, 1, 2, 100.00),
--     (2, 2, 3, 360.00),
--     (3, 3, 1, 300.00),
--     (4, 4, 2, 160.00),
--     (5, 5, 1, 100.00);

-- Insert Sample Payments

-- INSERT INTO payment (order_id, customer_id, card_id, amount, payment_status)
-- VALUES
--     (1, 1, 'CARD001', 180.00, 'SUCCESS'),
--     (2, 2, 'CARD002', 324.00, 'SUCCESS'),
--     (3, 3, 'CARD003', 240.00, 'SUCCESS'),
--     (4, 4, 'CARD004', 144.00, 'SUCCESS'),
--     (5, 5, 'CARD005', 0.00, 'FAILED');

-- Insert Sample Cards

-- INSERT INTO cards (card_id, customer_id, name_on_card, card_type, cvv, expiry_date)
-- VALUES
--     ('CARD001', 1, 'Abhishek', 'DEBIT', '123', '2027-05-01'),
--     ('CARD002', 2, 'Keerthi', 'CREDIT', '456', '2028-03-01'),
--     ('CARD003', 3, 'Arun', 'DEBIT', '789', '2026-12-01')