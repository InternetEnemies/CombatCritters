# Architecture 

The first iteration follows a Three-Tier Architecture with the following packages:

## com.internetEnemies.combatCritters.presentation

This package stores classes related to the presentation/UI layer of the application.

**




## com.internetEnemies.combatCritters.Logic

This packages stores classes related to any complex logic the application uses.

### **PackOpener**
- `randomByRarity(CardSlot slot)`
    * Takes a CardSlot object as input and returns a random Card.Rarity enum based on the weights given by the CardSlot.

- `findCardsOfRarity(Card.Rarity rarity, Pack pack)`
    * Finds and returns a list of cards that match `rarity` from the given Pack object.

- `pullCards(Pack)`
    * Returns a list of cards generated from the given Pack object.

### **PackOpener**
- `randomByRarity(CardSlot slot)`
    * Takes a CardSlot object as input and returns a random Card.Rarity enum based on the weights given by the CardSlot.

- `findCardsOfRarity(Card.Rarity rarity, Pack pack)`
    * Finds and returns a list of cards that match `rarity` from the given Pack object.

- `pullCards(Pack)`
    * Returns a list of cards generated from the given Pack object.

    

## Why is Combat Critters valuable?

The platform is valuable to people who enjoy playing card games but may not always have time to sit down and play at a table with friends. We also want to provide an easy way to learn, yet an exciting experience to a wide range of people. This project allows anyone to play the game on the go whenever they have free time, rather than having to carry around physical cards. In addition, our marketplace and trading system gives players a variety of ways to collect cards, granting them freedom in player expression with how they manage their deck.


