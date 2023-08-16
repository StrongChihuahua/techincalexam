CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    middle_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    birth_date DATE NOT NULL,
    gender ENUM('MALE', 'FEMALE', 'OTHER') NOT NULL,
    marital_status ENUM('SINGLE', 'MARRIED', 'DIVORCED', 'WIDOWED') NOT NULL,
    position VARCHAR(20) NOT NULL,
    date_hired DATE NOT NULL
);

CREATE TABLE contacts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    value VARCHAR(255) NOT NULL,
    is_primary BOOLEAN NOT NULL,
    employee_id BIGINT,
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

CREATE TABLE address (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    address_1 VARCHAR(255) NOT NULL,
    address_2 VARCHAR(255) NOT NULL,
    is_primary BOOLEAN NOT NULL,
    employee_id BIGINT,
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

INSERT INTO employees (first_name, middle_name, last_name, birth_date, gender, marital_status, position, date_hired)
VALUES ('John', 'Michael', 'Doe', '1990-05-15', 'MALE', 'SINGLE', 'Manager', '2021-01-10');

INSERT INTO employees (first_name, middle_name, last_name, birth_date, gender, marital_status, position, date_hired)
VALUES ('Jane', 'Marie', 'Smith', '1988-08-20', 'FEMALE', 'MARRIED', 'Developer', '2020-03-05');

INSERT INTO employees (first_name, middle_name, last_name, birth_date, gender, marital_status, position, date_hired)
VALUES ('Robert', 'William', 'Johnson', '1995-02-10', 'MALE', 'SINGLE', 'Analyst', '2019-09-15');

INSERT INTO contacts (value, is_primary, employee_id)
VALUES ('john.doe@example.com', true, 1);
INSERT INTO contacts (value, is_primary, employee_id)
VALUES ('1234567890', false, 1);

INSERT INTO contacts (value, is_primary, employee_id)
VALUES ('jane.smith@example.com', true, 2);
INSERT INTO contacts (value, is_primary, employee_id)
VALUES ('9876543210', false, 2);

INSERT INTO contacts (value, is_primary, employee_id)
VALUES ('robert.johnson@example.com', true, 3);
INSERT INTO contacts (value, is_primary, employee_id)
VALUES ('5552223333', false, 3);

INSERT INTO address (address_1, address_2, is_primary, employee_id)
VALUES ('123 Main Street', 'Apt 4B', true, 1);
INSERT INTO address (address_1, address_2, is_primary, employee_id)
VALUES ('456 Elm Avenue', 'Unit 10', false, 1);

INSERT INTO address (address_1, address_2, is_primary, employee_id)
VALUES ('789 Oak Road', 'Suite 201', true, 2);
INSERT INTO address (address_1, address_2, is_primary, employee_id)
VALUES ('123 Pine Lane', 'Unit 5A', false, 2);

INSERT INTO address (address_1, address_2, is_primary, employee_id)
VALUES ('555 Cedar Street', 'Apt 7C', true, 3);
INSERT INTO address (address_1, address_2, is_primary, employee_id)
VALUES ('777 Maple Avenue', 'Unit 3B', false, 3);
