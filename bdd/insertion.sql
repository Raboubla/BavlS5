-- Insertion des types de produits
INSERT INTO typeProduit (idTypeProduit, nonTypeProduit) VALUES
(1, 'Pains Traditionnels'),
(2, 'Viennoiseries'),
(3, 'Pâtisseries Classiques'),
(4, 'Biscuits et Petits Gâteaux'),
(5, 'Produits Salés'),
(6, 'Spécialités Régionales');

-- Insertion des unités de mesure
INSERT INTO Unite (idUnite, Unite) VALUES
(1, 'kg'),
(2, 'g'),
(3, 'l'),
(4, 'ml'),
(5, 'unité'),
(6, 'pièce');

-- Insertion des produits
INSERT INTO Produit (idProduit, nomProduit, prixRevient, prixVente, image, idTypeProduit) VALUES
(1, 'Baguette Tradition', 0.40, 1.20, 'https://images.unsplash.com/photo-1597079910443-60c43fc4f729', 1),
(2, 'Pain de Campagne', 1.20, 3.50, 'https://images.unsplash.com/photo-1589367920969-ab8e050bbb04', 1),
(3, 'Croissant au Beurre', 0.50, 1.50, 'https://images.unsplash.com/photo-1555507036-ab1f4038808a', 2),
(4, 'Pain au Chocolat', 0.60, 1.60, 'https://images.unsplash.com/photo-1626094309830-abbb0c99da4a', 2),
(5, 'Éclair au Chocolat', 1.50, 3.80, 'https://images.unsplash.com/photo-1634118520179-0c78b72df69a', 3);

-- Insertion des stocks
INSERT INTO Stock (idStock, quantiteStock, dateFaranyMaj, idProduit) VALUES
(1, 100, '2024-03-15', 1),
(2, 50, '2024-03-15', 2),
(3, 80, '2024-03-15', 3),
(4, 75, '2024-03-15', 4),
(5, 40, '2024-03-15', 5);

-- Insertion des clients
INSERT INTO Client (idClient, nomClient, email, password, telephone, adresse) VALUES
(1, 'Marie Dupont', 'marie@email.com', 'hashedpassword1', '0123456789', '123 Rue de Paris'),
(2, 'Jean Martin', 'jean@email.com', 'hashedpassword2', '0234567890', '456 Avenue des Fleurs'),
(3, 'Sophie Bernard', 'sophie@email.com', 'hashedpassword3', '0345678901', '789 Boulevard Central');

-- Insertion des commandes
INSERT INTO Commande (idCommande, dateCommande, status, idClient) VALUES
(1, '2024-03-15', 'completed', 1),
(2, '2024-03-15', 'processing', 2),
(3, '2024-03-15', 'pending', 3);

-- Insertion des produits commandés
INSERT INTO commandeProduit (idCommandeProduit, quantite, idCommande, idProduit) VALUES
(1, 2, 1, 1),  -- 2 baguettes
(2, 1, 1, 3),  -- 1 croissant
(3, 3, 2, 4),  -- 3 pains au chocolat
(4, 1, 2, 5),  -- 1 éclair
(5, 2, 3, 2);  -- 2 pains de campagne

-- Insertion des paiements
INSERT INTO Paiement (idPaiement, montant, datePaiement, methode, idCommande) VALUES
(1, 3.90, '2024-03-15', 'CB', 1),
(2, 8.60, '2024-03-15', 'CB', 2),
(3, 7.00, '2024-03-15', 'En attente', 3);

-- Insertion des évaluations
INSERT INTO Evaluation (idEvaluation, satisfaction, commentaire, dateEvaluation, idCommande) VALUES
(1, '5', 'Excellent, comme toujours !', '2024-03-14', 1),
(2, '4', 'Très bon service, produits frais', '2024-03-14', 2),
(3, '5', 'Les meilleurs croissants de la ville', '2024-03-13', 1);

-- Insertion des ingrédients
INSERT INTO Ingredient (idIngredient, nomIngredient, idUnite) VALUES
(1, 'Farine T55', 1),
(2, 'Levure', 2),
(3, 'Sel', 2),
(4, 'Eau', 3),
(5, 'Beurre', 1),
(6, 'Chocolat', 1);

-- Insertion des formules (recettes)
INSERT INTO Formule (idFormule, quantite, idProduit, idIngredient) VALUES
(1, 0.250, 1, 1),  -- 250g farine pour baguette
(2, 5, 1, 2),      -- 5g levure pour baguette
(3, 5, 1, 3),      -- 5g sel pour baguette
(4, 0.150, 1, 4),  -- 150ml eau pour baguette
(5, 0.250, 3, 1),  -- 250g farine pour croissant
(6, 0.125, 3, 5);  -- 125g beurre pour croissant

-- Insertion des administrateurs
INSERT INTO Admin (idAdmin, nomAdmin, email, password) VALUES
(1, 'Admin Principal', 'admin@boulangerie.com', 'hashedpassword123');

-- Insertion des propriétaires
INSERT INTO Proprietaire (idProprietaire, nomProprietaire, email, password) VALUES
(1, 'Pierre Durand', 'pierre@boulangerie.com', 'hashedpassword456');