-- INSERT INTO author (id, name, birthday) VALUES (1, 'William Faulkner', '1897-09-25');
-- INSERT INTO author (id, name, birthday) VALUES (2, 'Liz Fenton', '1977-10-15');
-- INSERT INTO author (id, name, birthday) VALUES (3, 'Lisa Steinke', '1977-04-23');

-- INSERT INTO book (id, isbn, title, published_year, price, genre) VALUES (1, '978-0075536574', 'ABSALOM, ABSALOM!', 1936, 24.77, 'Historical Fiction');
-- INSERT INTO book (id, isbn, title, published_year, price, genre) VALUES (2, '978-1542005098', 'How to Save a Life', 2020, 7.25, 'Novel');

-- INSERT INTO bookauthor(id, book_id, author_id) VALUES (1, 1, 1);
-- INSERT INTO bookauthor(id, book_id, author_id) VALUES (2, 2, 2);
-- INSERT INTO bookauthor(id, book_id, author_id) VALUES (3, 2, 3);

INSERT INTO users(id, username, password, access_type) VALUES(1, 'admin', 'admin', 2);
INSERT INTO users(id, username, password, access_type) VALUES(2, 'user', 'user', 1);
