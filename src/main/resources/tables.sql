ALTER TABLE `db_sql`.`news_piece`
    CHANGE COLUMN `des` `des` TEXT NULL DEFAULT NULL ;
ALTER TABLE `db_sql`.`news_piece`
    CHANGE COLUMN `body` `body` TEXT NULL DEFAULT NULL ;
ALTER TABLE `db_sql`.`news_piece`
    CHANGE COLUMN `img` `img` TEXT NULL DEFAULT NULL ;
ALTER TABLE `db_sql`.`news_piece`
    CHANGE COLUMN `title` `title` TEXT NULL DEFAULT NULL ;

ALTER TABLE `db_sql`.`news`
    CHANGE COLUMN `des` `des` TEXT NULL DEFAULT NULL ;
ALTER TABLE `db_sql`.`news`
    CHANGE COLUMN `img` `img` TEXT NULL DEFAULT NULL ;
ALTER TABLE `db_sql`.`news`
    CHANGE COLUMN `title` `title` TEXT NULL DEFAULT NULL ;


# ALTER TABLE clients
#     DROP FOREIGN KEY FK3g6e51o2ovgxvty5k330c8coy;

ALTER TABLE news_piece
    ADD CONSTRAINT FK_newspiece_newsid
        FOREIGN KEY (fk_news_entity_id)
            REFERENCES news(id)
            ON DELETE CASCADE;