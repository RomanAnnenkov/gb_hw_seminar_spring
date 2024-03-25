INSERT INTO users (username, password, email, role)
VALUES ('user1', 'pass', 'u1@example.com', 'developer'),
('user2', 'pass', 'u2@example.com', 'tester'),
('user3', 'pass', 'u3@example.com', 'developer'),
('user4', 'pass', 'u4@example.com', 'tester');

INSERT INTO projects (name, description, created_date)
VALUES
('online-shop', 'web service', '2023-05-20'),
('weather-info', 'android application', '2023-10-16'),
('library', 'web service', '2023-08-10');

INSERT INTO users_project_relation (user_id, project_id)
VALUES
('1', '1'),
('2', '1'),
('1', '3'),
('2', '3'),
('3', '3'),
('4', '3'),
('3', '2'),
('4', '2');