# Combat Critters 2.0

## Link to Repositories

### Frontends
- Desktop (C#) [repo](https://github.com/InternetEnemies/combatcritters-maui)
- Web (TypeScript/React) [repo](https://github.com/InternetEnemies/combatcritters-react)

The desktop application is a management/admin interface for the game which is played on the
React web interface.

### Wrappers
These wrappers provide an interface for the frontends to interact with the api, they will be
delivered via the languages respective package managers (NPM and NuGet).
- Web (TypeScript) Wrapper [repo](https://github.com/InternetEnemies/combatcritters-ts)
- Desktop (C#) Wrapper [repo](https://github.com/InternetEnemies/combatcritters-sharp)

### Test Reports
Available in this [repo](https://github.com/InternetEnemies/TestResults)

## Setup
### Prerequisites
- Java 22
### Running/Building
#### Build
`./gradlew build`
#### Running
##### DB

*Starting:*
`docker compose -p combatcritters up -d postgres`

*Stopping:*
`docker compose -p combatcritters down`

##### Start api
`./gradlew critterSpring:bootRun`

The api will be online on port `8080`, refer to the API documentation below for details.
#### Test
`./gradlew <test suite>`

Test Suites:
- test
- integrationTest

#### Starting backend for production / frontend
`docker compose up`

This will start the backend on port `4000`

## API
- Our [API Documentation](https://api-fiddle.com/editor/vanjackals-organization/critter-spec)
## Slides and Presentation

- Our [Proposal Presentation](https://docs.google.com/presentation/d/1ejMu4u_MBDTiwN8okK5Jnudc20xtLdkQM-5kvj9lD6E/edit#slide=id.gc6f980f91_0_42)

## Summary and Vision
Our vision and project summary can be found [here](./Documents/VISION.md)

## Architecture
Our architecture can be found [here](./Documents/ARCHITECTURE.md)

## Sequence Diagrams
Can be found [here](./Documents/SequenceDiagrams/README.md)

## Testing plan
Can be found [here](./Documents/TESTING_PLAN.md)

## Work Division

We will try to separate dev tasks equally in correspondence to everyone's schedule and ability. However, if one of us is busy with something else, the rest of us will try our best to cover his task. We will divide our tasks on the Github project task board, making sure everyone has only one task at a time. We will at minimum host one in-person meeting every week on Wednesday to make sure everyone is on the same page. We will communicate using discord in case there are any issues

## Meeting notes
Our meeting notes can be found [here](./Documents/Meetings).
