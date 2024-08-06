INSERT INTO users (id, username, email, password) VALUES
('1', 'Admin', 'admin@gmail.com', 'admin'), --100000
('2', 'Guest', 'guest@gmail.com', 'guest'),--100001
('3', 'User', 'user@yandex.ru', 'password'), --100002
('4','1', '1', '1');

INSERT INTO user_roles (role, user_id) VALUES
('USER', 1),
('ADMIN', 1),
('USER', 2),
('USER', 3),
('USER', 4),
('ADMIN', 4);

SELECT * from users