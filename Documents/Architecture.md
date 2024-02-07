# Architecture 

The first iteration follows a Three-Tier Architecture with the following packages:

## com.internetEnemies.combatCritters.presentation

This package stores classes related to the presentation/UI layer of the application.

**




## com.internetEnemies.combatCritters.Logic

This package stores classes related to any complex logic the application uses.

### **PackOpener**
Handles logic regarding the opening of packs.
- `randomByRarity(CardSlot slot)`
    * Takes a `CardSlot` object as input and returns a random `Card.Rarity` enum based on the weights given by the CardSlot.

- `findCardsOfRarity(Card.Rarity rarity, Pack pack)`
    * Finds and returns a `List<Card>` of cards that match `rarity` from the given Pack object.

- `pullCards(Pack)`
    * Returns a List<Card> generated from the given Pack object.

### **CardCatalog**
Fetches information from the Card Inventory.
- `getOwned()`
    * Returns the cards that are currently in the player's inventory as a `Map<Card, Integer>` where the Integer value is the amount owned.

- `getAll()`
    * Returns a list of all cards in the database as a `Map<Card, Integer>` where the Integer value is the amount owned.

### **PackCatalog**
Fetches regarding packs in the databse.
- `getPack(int id)`
    * Returns a pack object with the corresponding id.
    
- `getListOfPacks()`
    * Returns a list of all the packs in the database.

###DeckBuilder
Handles access to Decks stored in the data layer.
- `addCard(Card insert)`
    * Inserts the given Card object into the deck.

- `addCard(int slot Card insert)`
    * Inserts the given Card object into index `slot` in the deck.

- `removeCard(int slot)`
    * Removes a card stored at index `slot` in the deck.

- `getCard()`
    * Returns a `List<Card>` of all the cards in the deck.

- `getTotalNumOfCards()`
    * Returns the number of total cards in the deck.

- `getNumOfCard(Card card)`
    * Returns the total number of occurences of `card` in the deck.




    

## Why is Combat Critters valuable?

The platform is valuable to people who enjoy playing card games but may not always have time to sit down and play at a table with friends. We also want to provide an easy way to learn, yet an exciting experience to a wide range of people. This project allows anyone to play the game on the go whenever they have free time, rather than having to carry around physical cards. In addition, our marketplace and trading system gives players a variety of ways to collect cards, granting them freedom in player expression with how they manage their deck.


