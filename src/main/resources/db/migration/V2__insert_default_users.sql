CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(255) NOT NULL
);

INSERT INTO users (username, password, roles) VALUES ('user', '$2a$10$UZrciWoiDUmLsF7T.yiB1..1Cccw4DbOvHVdUzV9VNaFbCT.xch7G', 'ROLE_USER');
INSERT INTO users (username, password, roles) VALUES ('admin', '$2a$10$hRzY51GhiZwQhugsOmAZWeGQMIKfTPX9NFs2ohOe8.PIkB4YmadB.', 'ROLE_ADMIN');