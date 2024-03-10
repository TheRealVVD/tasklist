insert into users (name, username, password)
values ('John Doe', 'johndoe@gmail.com', '$2a$10$EIe7kgFRFMQq5wlKEhmkueeniKXZB4nS8w8Ku8LcQGn7G2mpzZLlO'),
       ('Mike Smith', 'mikesmith@yahoo.com', '$2a$10$s3VD6KC/zJfHPdfuiLBt6eCQyGF15Ng1NSKz8rvxEiIm7fv5HseIm');

insert into tasks (title, description, status, expiration_date)
values ('Buy cheese', null, 'TODO', '2023-09-09 12:00:00'),
       ('Do homework', 'Math', 'IN_PROGRESS', '2023-01-01 13:00:00'),
       ('Clean rooms', null, 'DONE', null),
       ('Call bro', 'Ask about gym', 'TODO', '2023-02-02 11:00:00');

insert into users_tasks (task_id, user_id)
values (1, 2),
       (2, 2),
       (3, 2),
       (4, 1);

insert into users_roles (user_id, role)
values (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');