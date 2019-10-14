INSERT INTO usert (id, username, password) VALUES (0, 'irfan', '$2a$10$iZUTUmy77BYXJr6kghvYeuyadC8WNf5dBR/AifvZJMpIiZBXS0NXe');
INSERT INTO usert (id, username, password) VALUES (1, 'user1', '$2a$10$iZUTUmy77BYXJr6kghvYeuyadC8WNf5dBR/AifvZJMpIiZBXS0NXe');

INSERT INTO rolet (id, description, name) VALUES (1, 'Admin role', 'ADMIN');
INSERT INTO rolet (id, description, name) VALUES (2, 'User role', 'USER');

INSERT INTO user_roles (user_id, role_id) VALUES (0, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);