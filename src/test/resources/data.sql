DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
  id INT PRIMARY KEY,
  employee_name VARCHAR(250) NOT NULL,
  manager_id INT,
  FOREIGN KEY(manager_id) REFERENCES employee(id)
);

INSERT INTO employee (id, employee_name, manager_id) VALUES
    (150, 'CEO1', null),
    (220, 'Martin', 150),
    (667, 'CEO2', null),
    (333, 'Peter', 667);

