SET GLOBAL event_scheduler = ON;
SET GLOBAL time_zone = "+00:00";
SELECT CURRENT_TIMESTAMP;
DROP EVENT IF EXISTS `clean_black_list`;
CREATE EVENT clean_black_list
    ON SCHEDULE EVERY 30 MINUTE
    STARTS CURRENT_TIMESTAMP
    DO
    DELETE
    FROM pf_jwt_black_list
    WHERE expired < CURRENT_TIMESTAMP;
