-- Dùng để loại bỏ foreign key khi muốn xóa dữ liệu
-- muốn xóa hay update pải dùng hàm này set về 0 ms xóa hay update dc
-- 0,1
# SET FOREIGN_KEY_CHECKS=1;

-- DAILYPOSTS
insert into daily_posts (img, title, des, poster_name, client_entity_id)
values ('https://www.seatemperatu.re/site/images/illustration/vietnam_770.jpg', 'halong', 'dep qua', 'name', 1);
insert into daily_posts (img, title, des, poster_name, client_entity_id)
values ('https://media.timeout.com/images/105241469/image.jpg', 'halong', 'dep qua', 'name', 2);

-- LIKES
insert into daily_post_entity_likes (daily_post_entity_id, likes)
values (1, '1');
insert into daily_post_entity_likes (daily_post_entity_id, likes)
values (2, '2');

-- FAVORITES
insert into daily_post_entity_favorites (daily_post_entity_id, favorites)
values (1, '1');
insert into daily_post_entity_favorites (daily_post_entity_id, favorites)
values (2, '2');
insert into daily_post_entity_favorites (daily_post_entity_id, favorites)
values (3, '4');

# CLIENTS
insert into clients (username, email, address, password, sdt, gender, role)
values ('test', 'test@email.com', 'test', '12345678', '0123456789', 'nam', 'user');
insert into clients (username, email, address, password, sdt, gender, role)
values ('test1', 'test1@email.com', 'test1', '123456378', '01223456789', 'nu', 'user');





