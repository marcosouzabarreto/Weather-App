CREATE DATABASE weather_app;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users (
    id UUID NOT NULL UNIQUE DEFAULT uuid_generate_v4(),
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS previous_locations_weather (
    id UUID NOT NULL UNIQUE DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL,
    humidity FLOAT8 NOT NULL,
    temperature FLOAT8 NOT NULL,
    latitude FLOAT8 NOT NULL,  
    longitude FLOAT8 NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);