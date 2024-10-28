CREATE TABLE USERS(
       id BIGSERIAL PRIMARY KEY,
       username VARCHAR(255) NOT NULL UNIQUE,
       password VARCHAR(255) NOT NULL,
       banned BOOLEAN DEFAULT false
       );
       
CREATE TABLE FRIENDS(
       userTx INT NOT NULL, 
       userRx INT NOT NULL, 
       PRIMARY KEY (userTx, userRx), 
       FOREIGN KEY (userTx) REFERENCES USERS(id), 
       FOREIGN KEY (userRx) REFERENCES USERS(id)
       );
       
CREATE TABLE DECKS(
       userId INTEGER, 
       id BIGSERIAL PRIMARY KEY,
       name VARCHAR(255) NOT NULL, 
       FOREIGN KEY (userId) REFERENCES USERS(id)
       );
       
CREATE TABLE PROFILES(
       userId INT NOT NULL PRIMARY KEY, 
       deckId INT, 
       FOREIGN KEY (userId) REFERENCES USERS(id), 
       FOREIGN KEY (deckId) REFERENCES DECKS(id)
       );
-- Cards
CREATE TABLE CARDS(
       id BIGSERIAL PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       image VARCHAR(255),
       playCost INTEGER,
       rarity INTEGER,
       type VARCHAR(20),
       damage INTEGER,
       health INTEGER,
       effectId INTEGER
       );
       
CREATE TABLE PACKS(
       id BIGSERIAL PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       image VARCHAR(255) NOT NULL
       );
       
CREATE TABLE PACKCARDS(
       packId INT NOT NULL,
       cardId INT NOT NULL, 
       FOREIGN KEY (packID) REFERENCES PACKS(id), 
       FOREIGN KEY (cardId) REFERENCES CARDS(id)
       );
       
CREATE TABLE CARDSLOT(
       packId INT NOT NULL,
       position INT NOT NULL, 
       common DOUBLE PRECISION NOT NULL, 
       uncommon DOUBLE PRECISION NOT NULL, 
       rare DOUBLE PRECISION NOT NULL, 
       epic DOUBLE PRECISION NOT NULL, 
       legend DOUBLE PRECISION NOT NULL, 
       FOREIGN KEY (packId) REFERENCES PACKS(id)
       );
       
CREATE TABLE DECKCARDS(
       deckId INT NOT NULL,
       cardId INT NOT NULL,
       position INT NOT NULL,
       PRIMARY KEY (deckid,position),
       FOREIGN KEY (deckId) REFERENCES Decks(id),
       FOREIGN KEY (cardId) REFERENCES Cards(id)
       );
       
CREATE TABLE CARDINVENTORY(
       userId INT NOT NULL, 
       cardId INT NOT NULL, 
       amount INT NOT NULL, 
       PRIMARY KEY (cardId,userId), 
       FOREIGN KEY (cardId) REFERENCES Cards(id), 
       FOREIGN KEY (userId) REFERENCES USERS(id)
       );
       
CREATE TABLE PACKINVENTORY(
       userid INT NOT NULL,
       packId INT NOT NULL, 
       amount INT NOT NULL, 
       PRIMARY KEY (packId, userid), 
       FOREIGN KEY (packId) REFERENCES Packs(id),
       FOREIGN KEY (userid) REFERENCES Users(id)
       );
       
CREATE TABLE TRANSACTIONS (
       id BIGSERIAL PRIMARY KEY, 
       type VARCHAR(255) NOT NULL
       );
       
CREATE TABLE TRANSACTIONITEM (
       tid INT NOT NULL,
       type VARCHAR(255) NOT NULL,
       recv BOOLEAN,
       packId INT,
       cardId INT,
       currency INT,
       amount INT, 
       FOREIGN KEY (tid) REFERENCES TRANSACTIONS(id), 
       FOREIGN KEY (packId) REFERENCES PACKS(id), 
       FOREIGN KEY (cardId) REFERENCES CARDS(id)
       );
       
CREATE TABLE MARKETDISCOUNTS (
       tid INT NOT NULL,
       discount DOUBLE PRECISION, 
       FOREIGN KEY (tid) REFERENCES TRANSACTIONS(id)
       );
       
CREATE TABLE PLAYERINFO (
       userid INT NOT NULL, 
       balance INT NOT NULL, 
       PRIMARY KEY (userid),
       FOREIGN KEY (userid) REFERENCES USERS(id)
       );
       
CREATE TABLE BATTLEINFO (
       id BIGSERIAL, 
       NAME VARCHAR(255) NOT NULL, 
       DESCRIPTION VARCHAR(255) NOT NULL, 
       IMAGE VARCHAR(255), 
       REWARDSID INT NOT NULL, 
       PRIMARY KEY(id), 
       FOREIGN KEY (REWARDSID) REFERENCES TRANSACTIONS(id)
       );
       
CREATE TABLE TRADEINFO (
       tid INT NOT NULL, 
       name VARCHAR(255), 
       image VARCHAR(255), 
       FOREIGN KEY (tid) REFERENCES TRANSACTIONS(id)
       );