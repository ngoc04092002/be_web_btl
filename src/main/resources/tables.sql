create table daily_posts
(
    daily_post_id bigint(20) unsigned not null auto_increment,
    img           text,
    title         text,
    des           text,
    poster_name   nvarchar(255)                default '',
    created_at    TIMESTAMP           NOT NULL default now(),
    updated_at    TIMESTAMP           NOT NULL default now(),
    primary key (daily_post_id)
);

create table daily_post_entity_likes
(
    daily_post_entity_id bigint(20) unsigned not null auto_increment,
    likes                varchar(255)
);

create table daily_post_entity_favorites
(
    daily_post_entity_id bigint(20) unsigned not null auto_increment,
    favorites                varchar(255)
);


