INSERT INTO pf_roles (id, name, status) VALUES
(1,'ANONYMOUS', 'ACTIVE'),
(2, 'USER', 'ACTIVE'),
(3,'ADMIN', 'ACTIVE');

INSERT INTO pf_animal_categories (id, name) VALUES
(1, 'СОБАКА'),
(2, 'КОТ'),
(3, 'РЫБКА'),
(4, 'ГРЫЗУН'),
(5, 'РЕПТИЛИЯ'),
(6, 'ПТИЦА');

-- подкатегории рыбок, грызунов, рептилий, птиц
INSERT INTO pf_animal_sub_category() VALUES
(1, "Всеїдні рибки"),
(2, "Дискуси"),
(3, "Донні рибки"),
(4, "Живородні рибки"),
(5, "Золоті рибки"),
(6, "Лабіринтові рибки"),
(7, "Рослидноїдні рибки"),
(8, "Цихліди"),
(9, "Білки та бурундуки"),
(10, "Кролики"),
(11, "Ящірки"),
(12, "Морські свинки"),
(13, "Миші та піщанки"),
(14, "Хом'яки"),
(15, "Шиншили та білки дегу"),
(16, "Черепахи"),
(17, "Ящірки"),
(18, "Хвилясті папуги"),
(19, "Канарки"),
(20, "Великі папуги"),
(21, "Комахоїдні птахи"),
(22, "Екзотичні птахи");

-- тип корма для собак и котов
INSERT INTO pf_food_types(id, type, code) VALUES
(1, 'Сухой', 1),
(2, 'Жидкий', 2);

-- тип корма для рыб
INSERT INTO pf_food_sub_types(id, sub_type) VALUES
(1,"Таблетки"),
(2,"Чіпси"),
(3,"Трава"),
(4,"Гранули"),
(5,"Палички"),
(6,"Основний"),
(7,"Додатковий"),
(8,"Сіно");

INSERT INTO pf_dog_sizes(id, name, short_name, min_weight_kg, max_weight_kg) VALUES
(1, 'Карликовая','XS','0','2'),
(2, 'Мелкая','S','2','10'),
(3, 'Средняя','M','10','32'),
(4, 'Крупная','XL','32','99');

INSERT INTO pf_daily_food_amount (id, pet_category, adult_pet_size, animal_age_type, food_type, food_amount) VALUES
(1, 'собака', 'XS', 'BABY', 'сухой', 55),
(2, 'собака', 'XS', 'ADULT', 'сухой', 46),
(3, 'собака', 'XS', 'OLD', 'сухой', 70),
(4, 'собака', 'S', 'BABY', 'сухой', 112),
(5, 'собака', 'S', 'ADULT', 'сухой', 93),
(6, 'собака', 'S', 'OLD', 'сухой', 119),
(7, 'собака', 'M', 'BABY', 'сухой', 255),
(8, 'собака', 'M', 'ADULT', 'сухой', 235),
(9, 'собака', 'M', 'OLD', 'сухой', 260),
(10, 'собака', 'XL', 'BABY', 'сухой', 310),
(11, 'собака', 'XL', 'ADULT', 'сухой', 290),
(12, 'собака', 'XL', 'OLD', 'сухой', 330),
(13, 'собака', 'XS', 'BABY', 'жидкий', 45),
(14, 'собака', 'XS', 'ADULT', 'жидкий', 36),
(15, 'собака', 'XS', 'OLD', 'жидкий', 50),
(16, 'собака', 'S', 'BABY', 'жидкий', 92),
(17, 'собака', 'S', 'ADULT', 'жидкий', 73),
(18, 'собака', 'S', 'OLD', 'жидкий', 99),
(19, 'собака', 'M', 'BABY', 'жидкий', 235),
(20, 'собака', 'M', 'ADULT', 'жидкий', 216),
(21, 'собака', 'M', 'OLD', 'жидкий', 240),
(22, 'собака', 'XL', 'BABY', 'жидкий', 280),
(23, 'собака', 'XL', 'ADULT', 'жидкий', 270),
(24, 'собака', 'XL', 'OLD', 'жидкий', 290);

INSERT INTO pf_daily_food_amount (id, pet_category, animal_age_type, food_type, food_amount) VALUES
(25, 'кот', 'BABY', 'сухой', 35),
(26, 'кот', 'ADULT', 'сухой', 27),
(27, 'кот', 'OLD', 'сухой', 28),
(28, 'кот', 'BABY', 'жидкий', 18),
(29, 'кот', 'ADULT', 'жидкий', 15),
(30, 'кот', 'OLD', 'жидкий', 42);

INSERT INTO pf_vendors (id, title) value
(1, 'vendor title 1'),
(2, 'vendor title 2');

INSERT INTO pf_users (id, email, enabled, first_name, last_name, new_user_flag, password, status, role_id)
VALUES(1, 'pf_admin@mail.com', true, 'Admin', 'Administrator', false, '$2a$10$d/Z9GVTGHWVgOb1eNuAs1eudeYGrftM6a9agLMmbJ7S1qj7w7U7VC', 'ACTIVE', 3);
-- password Admin123!