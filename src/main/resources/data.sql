DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  id INT PRIMARY KEY,
  employee_name VARCHAR(250) NOT NULL,
  manager_id INT,
  FOREIGN KEY(manager_id) REFERENCES employee(id)
);

INSERT INTO employee (id, employee_name, manager_id) VALUES
    (150, 'Jamie', null),
    (100, 'Alan', 150),
    (220, 'Martin', 100),
    (275, 'Alex', 100),
    (400, 'Steve', 150),
    (190, 'David', 400);

