create table user
(
    id_user      int auto_increment
        primary key,
    email        varchar(255) not null,
    mot_de_passe varchar(255) not null,
    username     varchar(255) not null,
    constraint adresse_email
        unique (email)
);

create table compte
(
    id_compte int auto_increment
        primary key,
    id_user   int   not null,
    solde     float not null,
    constraint compte_ibfk_1
        foreign key (id_user) references user (id_user)
);

create index id_user
    on compte (id_user);

create table friend
(
    id_friend      int auto_increment
        primary key,
    id_user        int not null,
    id_user_friend int null,
    constraint FKboxgsy789akxcdj6vbssujyna
        foreign key (id_user_friend) references user (id_user),
    constraint friend_ibfk_1
        foreign key (id_user) references user (id_user)
);

create index id_user
    on friend (id_user);

create table transaction
(
    id_transaction    int auto_increment
        primary key,
    id_user           int             not null,
    id_user_reception int             not null,
    date_time         datetime        not null,
    frais             int             null,
    description       varchar(255)    null,
    amount            float default 0 not null,
    constraint FKipnt7wb0eyeegoo1pyqyapamq
        foreign key (id_user_reception) references user (id_user),
    constraint transaction_ibfk_1
        foreign key (id_user) references user (id_user),
    constraint transaction_ibfk_2
        foreign key (id_user_reception) references friend (id_user_friend)
);

create index id_user
    on transaction (id_user);

-- Insérer des données dans la table user
INSERT INTO user (email, mot_de_passe, username) VALUES
                                                     ('jdupont@email.com', 'motdepasse1', 'jdupont'),
                                                     ('pmartin@email.com', 'motdepasse2', 'pmartin'),
                                                     ('mtremblay@email.com', 'motdepasse3', 'mtremblay'),
                                                     ('dsmith@email.com', 'motdepasse4', 'dsmith'),
                                                     ('jdoe@email.com', 'motdepasse5', 'jdoe');

-- Insérer des données dans la table compte
INSERT INTO compte (id_user, solde) VALUES
                                        (1, 1000.00),
                                        (2, 2000.00),
                                        (3, 3000.00),
                                        (4, 1500.00),
                                        (5, 2500.00);

-- Insérer des données dans la table friend
INSERT INTO friend (id_user, id_user_friend) VALUES
                                                 (1, 2),
                                                 (1, 3),
                                                 (2, 3),
                                                 (3, 4),
                                                 (4, 5);

-- Insérer des données dans la table transaction
INSERT INTO transaction (id_user, id_user_reception, date_time, frais, description, amount) VALUES
(1, 2, '2023-05-01 10:00:00', 2, 'Paiement de facture', 50.00),
(1, 3, '2023-05-01 10:05:00', 2, 'Transfert dargent', 200.00),
(2, 3, '2023-05-01 10:10:00', 3, 'Achat en ligne', 100.00),
(3, 4, '2023-05-01 10:15:00', 1, 'Paiement de loyer', 500.00),
(4, 5, '2023-05-01 10:20:00', 2, 'Transfert dargent', 150.00);