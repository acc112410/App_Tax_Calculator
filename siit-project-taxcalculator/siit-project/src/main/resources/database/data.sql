INSERT INTO login (id, user_email, user_password, enabled, first_name, second_name, type_of_bussines)
values ('1',
        'admin@yahoo.com',
         'a',
         true,
         'a',
         'a',
         'PFA');
INSERT INTO authorities (user_email, authority)
values('admin@yahoo.com',
        'ROLE_ADMIN');
