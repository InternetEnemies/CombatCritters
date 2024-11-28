# CritterProtocol V1
*A basic messaging protocol for Combat Critters battles

The payloads are generally split between 2 types, commands and events.

*Payload Structure:*
```
<Resource>
<Json Payload>
```
Where resource is the type of json payload that is being sent, the structures of the payloads and their purpose are defined below.

## Serverbound (Commands)
Commands are sent to the server from the client to control game actions.
### PLAY_CARD_COMMAND
```json
{
  "id": integer,
  "pos": integer
}
```
- id: id of battleCard being played
- pos: position to play the card

### END_TURN_COMMAND
```json
{}
```
empty body object
### SACRIFICE_COMMAND
```json
{
  "pos": integer
}
```

## Clientbound (Events & State)
Events are received by the client to tell the client what has happened so it can react.

### PLAYER_TURN_EVENT
```json
{
  "is_turn": boolean
}
```
### HEALTH_EVENT
```json
{
  "health": integer,
  "is_player": boolean
}
```
### ENERGY_EVENT
```json
{
  "energy": integer,
  "is_player": boolean
}
```

### HAND_EVENT
```json
{
  "cards": [Card]
}
```
list of CardPayloads

### DRAW_PILE_EVENT
```json
{
  "size": integer
}
```
### BOARD_STATE_EVENT
```json
{
  "slots": [CardState],
  "type" : string
}
```
type is one of: `player`,`player_buffer`,`opponent`,`opponent_buffer`

(this will need to be changed to a unified state event at some point)