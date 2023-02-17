

DROP TABLE IF EXISTS user;
CREATE TABLE user (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        adresse_email VARCHAR(255) UNIQUE NOT NULL,
                        mot_de_passe VARCHAR(255) NOT NULL,
                        username VARCHAR(255) NOT NULL
);
DROP TABLE IF EXISTS compte;
CREATE TABLE compte (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        id_user INT NOT NULL,
                        solde FLOAT NOT NULL,
                        FOREIGN KEY (id_user) REFERENCES user(id)

);

DROP TABLE IF EXISTS friend;
CREATE TABLE friend (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        id_user INT NOT NULL,
                        adresse_email_friend VARCHAR(255) UNIQUE NOT NULL,
                        id_user_friend INT NOT NULL,
                        FOREIGN KEY (id_user) REFERENCES user(id)

);

DROP TABLE IF EXISTS transaction;
CREATE TABLE transaction (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        id_user INT NOT NULL,
                        id_user_reception INT NOT NULL,
                        montant FLOAT NOT NULL,
                        date_time DATETIME NOT NULL,
                        FOREIGN KEY (id_user) REFERENCES user(id),
                        FOREIGN KEY (id_user_reception) REFERENCES friend(id)
);