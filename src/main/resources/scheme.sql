CREATE TABLE IF NOT EXISTS cats (
    cat_id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    birthday DATE,
    breed VARCHAR(50),
    color VARCHAR(50),
    owner VARCHAR(100) references owners (owner_id),
    friends JSON
);

CREATE TABLE IF NOT EXISTS owners (
    owner_id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    birthday DATE,
    password VARCHAR(200),
    roles VARCHAR(30),
    cats JSON
);

CREATE TABLE IF NOT EXISTS cat_friends (
    cat_id BIGINT REFERENCES cats(cat_id),
    friend_id BIGINT REFERENCES cats(cat_id),
    PRIMARY KEY (cat_id, friend_id)
);