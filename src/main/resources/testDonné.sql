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