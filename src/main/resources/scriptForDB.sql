-- Морковь - шт, Мясо- кг, Капуста - шт, Банан - шт, Яблоко - шт, Зерно - кг, Вода - л
INSERT INTO FOOD(amound, measure, name, type_of_product) VALUES (10, 'pcs',  'Морковь', 'VEGETABLE');
INSERT INTO FOOD(amound, measure, name, type_of_product) VALUES (54, 'kg',  'Мясо', 'MEAT');
INSERT INTO FOOD(amound, measure, name, type_of_product) VALUES (32, 'pcs',  'Капуста', 'VEGETABLE');
INSERT INTO FOOD(amound, measure, name, type_of_product) VALUES (186, 'pcs',  'Банан', 'FRUIT');
INSERT INTO FOOD(amound, measure, name, type_of_product) VALUES (0, 'pcs',  'Яблоко', 'FRUIT');
INSERT INTO FOOD(amound, measure, name, type_of_product) VALUES (24000, 'kg',  'Зерно', 'VEGETABLE');
INSERT INTO FOOD(amound, measure, name, type_of_product) VALUES (1000, 'l',  'Вода', 'LIQUID');

-- Обезьяна, Заяц, Орёл, Тигр, Лошадь
INSERT INTO ANIMAL(name, predator, species)  VALUES ('Обезьяна', false, 'MAMMAL');
INSERT INTO ANIMAL(name, predator, species)  VALUES ('Заяц', false, 'MAMMAL');
INSERT INTO ANIMAL(name, predator, species)  VALUES ('Орёл', true, 'BIRD');
INSERT INTO ANIMAL(name, predator, species)  VALUES ('Тигр', true, 'MAMMAL');
INSERT INTO ANIMAL(name, predator, species)  VALUES ('Лошадь', false, 'MAMMAL');

-- Обезьяна (банан, яблоко), Заяц (морковь, капуста), Орёл (мясо, зерно, яблоко),
-- Тигр (мясо), Лошадь (зерно, яблоко, морковь)
INSERT INTO DIET(ANIMAL_ID, FOOD_ID, AMOUNT) VALUES (1, 4, 2);
INSERT INTO DIET(ANIMAL_ID, FOOD_ID, AMOUNT) VALUES (1, 5, 6);
INSERT INTO DIET(ANIMAL_ID, FOOD_ID, AMOUNT) VALUES (1, 7, 4);

INSERT INTO DIET(ANIMAL_ID, FOOD_ID, AMOUNT) VALUES (2, 1, 1.5);
INSERT INTO DIET(ANIMAL_ID, FOOD_ID, AMOUNT) VALUES (2, 3, 2.6);
INSERT INTO DIET(ANIMAL_ID, FOOD_ID, AMOUNT) VALUES (2, 7, 2);

INSERT INTO DIET(ANIMAL_ID, FOOD_ID, AMOUNT) VALUES (3, 2, 1);
INSERT INTO DIET(ANIMAL_ID, FOOD_ID, AMOUNT) VALUES (3, 6, 2);
INSERT INTO DIET(ANIMAL_ID, FOOD_ID, AMOUNT) VALUES (3, 5, 3.2);
INSERT INTO DIET(ANIMAL_ID, FOOD_ID, AMOUNT) VALUES (3, 7, 1);

INSERT INTO DIET(ANIMAL_ID, FOOD_ID, AMOUNT) VALUES (4, 2, 7.3);
INSERT INTO DIET(ANIMAL_ID, FOOD_ID, AMOUNT) VALUES (4, 7, 6.7);

INSERT INTO DIET(ANIMAL_ID, FOOD_ID, AMOUNT) VALUES (5, 6, 11);
INSERT INTO DIET(ANIMAL_ID, FOOD_ID, AMOUNT) VALUES (5, 5, 34);
INSERT INTO DIET(ANIMAL_ID, FOOD_ID, AMOUNT) VALUES (5, 1, 25.5);
INSERT INTO DIET(ANIMAL_ID, FOOD_ID, AMOUNT) VALUES (5, 7, 1);


