create table user
(
    id_user      int auto_increment
        primary key,
    email        varchar(255) not null,
    mot_de_passe varchar(255) not null,
    username     varchar(255) not null,
    constraint adresse_email
        unique (email)
)
    engine = InnoDB;

create table compte
(
    id_compte int auto_increment
        primary key,
    id_user   int   not null,
    solde     float not null,
    constraint compte_ibfk_1
        foreign key (id_user) references user (id_user)
)
    engine = InnoDB;

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
)
    engine = InnoDB;

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
)
    engine = InnoDB;

create index id_user
    on transaction (id_user);

