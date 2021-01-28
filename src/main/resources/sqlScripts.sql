drop database pf_petfood;
create database pf_petfood;
use pf_petfood;


INSERT INTO pf_roles (name, status) VALUES
('ANONYMOUS', 'ACTIVE'),
('USER', 'ACTIVE'),
('ADMIN', 'ACTIVE');

INSERT INTO pf_animal_categories (name) VALUES
('СОБАКА'),
('КОТ'),
('РЫБКА'),
('ГРЫЗУН'),
('РЕПТИЛИЯ'),
('ПТИЦА');

INSERT INTO pf_food_types(type) VALUES
('Сухой'),
('Жидкий'),
('Смешанный');

INSERT INTO pf_dog_sizes(name, short_name, min_weight_kg, max_weight_kg) VALUES
('Карликовая','XS','0','2'),
('Мелкая','S','2','10'),
('Средняя','M','10','32'),
('Крупная','XL','32','99');
