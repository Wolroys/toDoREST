--liquibase formatted sql

--changeset wolroys:1
INSERT INTO users (username, email, password) VALUES
    ('john_doe', 'john.doe@example.com', 'password123'),
    ('jane_smith', 'jane.smith@example.com', 'securepass'),
    ('alice_jones', 'alice.jones@example.com', '123456'),
    ('mary_jackson', 'mary.jackson@example.com', 'marypass'),
    ('robert_adams', 'robert.adams@example.com', 'secure123');

INSERT INTO notes (title, content, user_id) VALUES
    ('Заметка 1', 'Содержание заметки 1', 1),
    ('Заметка 2', 'Содержание заметки 2', 1),
    ('Важная задача', 'Выполнить до завтра', 2),
    ('Заметка для Alice', 'Секретное сообщение', 3);

INSERT INTO tasks (title, description, user_id) VALUES
    ('Задача 1', 'Описание задачи 1', 1),
    ('Задача 2', 'Описание задачи 2', 1),
    ('Важная задача', 'Выполнить до завтра', 2),
    ('Задача для Alice', 'Секретное задание', 3);