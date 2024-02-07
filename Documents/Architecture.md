# Architecture 

The first iteration follows a Three-Tier Architecture with the following packages:

## com.internetEnemies.combatCritters.objects

This package stores classes for any DSO's being passed between layers

### Card

Class containing the required attributes for a card.

### CritterCard

Child class of card. Adds required attributes needed for a Critter Card.

### ItemCard

Child class of card. Adds required attributes needed for an Item Card .

### DeckDetails

Class that passes information regarding decks that are stored within the database.

### Pack

Class that passed information regarding packs such as the list of cards available, and pull chances.

### CardSlot

Helper class for `Pack`. Stores the probabilites for pulling cards of a rarity.

### DeckValidity

Passes the validity of a deck between layers.


## com.internetEnemies.combatCritters.presentation

This package stores classes related to the presentation/UI layer of the application.

### CardAdapterHelper

Class for inflating the shared views of CardWithQuantityAdaper and CardWithoutQuantity.

### CardWithQuantityAdapter

Creates the view of all the cards in the inventory section of the Deck Builder screen..

### CardWithoutQuantityAdapter 

Creates the view for all the cards that are available in the Deck Builder

## DeckBuilderActivity

Builds the main screen with the Deck Builder and inventory. 

## PackOpeningActivity

Can choose three packs to open from this screen. The packs are passed to CardsOpenedActivity through an intent.

## com.internetEnemies.combatCritters.Logic

This package stores classes related to any complex logic the application uses.


### **PackOpener**
Handles logic regarding the opening of packs.

### **CardCatalog**
Fetches information from the Card Inventory stored in the database.

### **PackCatalog**
Fetches information regarding packs in the databse.

### **DeckBuilder**
Handles contents of individual Decks stored in the data layer.  

### **DeckManager**
Handles information regarding the decks stored in the data layer.

Stores an instance of `IDeckInventory` used for storing multiple decks.

## com.internetEnemies.combatCritters.data

###ICardInventory

The interface for accessing the card inventory in the database. Allows us to add, remove, and get all the cards currently in the inventory.

###ICardSearch

The interface for quering cards within the database (ie. List of cards owned and their quantities).

###IRegistry

The interface for accessing the registry of Packs and Cards in the game. 

###IDeck

The interface for accessing the card contents of a deck.

###IDeckInventory

The interface for accessing intentification details of a a deck in the database.



