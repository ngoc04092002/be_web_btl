-- Dùng để loại bỏ foreign key khi muốn xóa dữ liệu
-- muốn xóa hay update pải dùng hàm này set về 0 ms xóa hay update dc
-- 0,1
# SET FOREIGN_KEY_CHECKS=1;

-- NEWS
insert into news (img, title, des, poster_name, client_entity_id)
values ('https://www.seatemperatu.re/site/images/illustration/vietnam_770.jpg', 'halong', 'dep qua', 'name', 1);
insert into news (img, title, des, poster_name, client_entity_id)
values ('https://media.timeout.com/images/105241469/image.jpg', 'halong', 'dep qua', 'name', 2);

# CLIENTS
insert into clients (username, email, address, password, sdt, gender, role)
values ('test', 'test@email.com', 'test', '12345678', '0123456789', 'nam', 'user');
insert into clients (username, email, address, password, sdt, gender, role)
values ('test1', 'test1@email.com', 'test1', '123456378', '01223456789', 'nu', 'user');


-- FOREIGN KEY
ALTER TABLE news_entity_likes
    ADD CONSTRAINT fk_news_entity_likes_news_entity_id FOREIGN KEY (news_entity_id)
        REFERENCES news (id)
        ON DELETE CASCADE;


