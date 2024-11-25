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
### PLAY_CARD structure
```json
{
  "id": string,
  "pos": number
}
```
- id: id of battleCard being played
- pos: position to play the card

## Clientbound (Events)
Events are received by the client to tell the client what has happened so it can react.