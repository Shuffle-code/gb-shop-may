insert into authority (permission)
values ('product.create'),
       ('product.read'),
       ('product.update'),
       ('product.delete');

insert into account_role(name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into role_authority(ROLE_ID, AUTHORITY_ID)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 2);



select * from authority;

select * from role_authority;

select * from account_role;

select * from user_role;


select * from account_user where username = 'admin';

select * from manufacturer;

select * from product_image;