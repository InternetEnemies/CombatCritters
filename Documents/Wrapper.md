# Wrapper Spec
This file is a general specification for the C# and Typescript wrappers, 
this may not reflect exact method signatures. For proper documentation look 
at the wrapper specific repos.
```mermaid
classDiagram
    Client o-- UserHandler
    Client *-- User
    Client o-- CardHandler
    Client o-- Rest
    
    User o-- UserCards
    User o-- Friends
    User o-- Decks
    
    
    class Client {
        + User user
        + UserHandler users
        + CardHandler cards
        + Rest Rest
        
        + void login()
        + void register()
    }
    
    class UserHandler {
        + User[] getUsers()
    }
    
    class User {
        + UserCards cards
        + Friends friends
        + Decks decks
        + Profile profile
    }
    
    class Deck{
        + addCard(Card card, int position)
        + removeCard(int postition)
    }
    
    class Decks{
        + Deck[] getDecks()
        + Deck getDeck(deckid)
    }
    
    class Card
    
    class Profile {
        
    }
```

*NOTE: the public class members are referring to JS getters and C# properties not actual public
class members*