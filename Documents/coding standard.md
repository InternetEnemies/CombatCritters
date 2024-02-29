# Coding Standards

## Variable Declaration

### declare at the top

```csharp
public class Database {
    private static Database INSTANCE;
...
}
```

### meaningful name (the name should reveal its purpose)
    
```csharp
private final ICardInventory cardInventory;
```

## Method Declaration

### Always have a documentation at the top (or in the interface)

```csharp
    /**
     * open a pack and send its contents to the players inventory
     *
     * @param pack Pack to open from
     * @return the list of cards that will be added
     */
    List<Card> openPack(Pack pack);
```

### if the method only use one statement, do it in one line (increase line cover)

```csharp
public Map<Card,Integer> getOwned() {return cardSearch.getOwned();}
```

### otherwise, in this format

```csharp
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_grid, container, false);
        gridView = view.findViewById(R.id.cardGridView);
        return view;
    }
```

## Class Declaration

### Always have a documentation at the top

```csharp
/**
 * DeckBuilder.java
 * COMP 3350 A02
 * @Project      combat critters
 * @created     01-February-2024
 *
 * @PURPOSE:     As a deck modifying tool, functions includes addCard and removeCard, etc,
 *               need to initialize with a IDeck,
 *               therefore must be created by the DeckManager.getBuilder()
 */
 
 ...
```

### if it is an interface, put an I as the prefix

```csharp
ICardCatalog.java
CardVatalog.java
```