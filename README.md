# Combat Critters 2.0

## Link to Repositories

- Desktop UI [repo](https://github.com/InternetEnemies/combatcritters-maui)
- Desktop Wrapper [repo](https://github.com/InternetEnemies/combatcritters-sharp)

- Web UI [repo](https://github.com/InternetEnemies/combatcritters-react)
- Web Wrapper [repo](https://github.com/InternetEnemies/combatcritters-ts)

## Slides and Presentation

- Our [Proposal_Presentation](https://docs.google.com/presentation/d/1ejMu4u_MBDTiwN8okK5Jnudc20xtLdkQM-5kvj9lD6E/edit#slide=id.gc6f980f91_0_42)

## Summary and Vision

The purpose of Combat Critters is to let our audience engage with a simple, fun, and exciting deck management card game that allows them to collect and battle with their favourite cards.

### What is Combat Critters 2.0?

With the massive success of the first Combat Critters we have decided to bring our audience the new and improved Combat Critters 2.0. Combat Critters 2.0 is a deck management card game that allows players to build and manage their own custom deck of cards. After creating a deck that perfectly suits their needs, they can take it to battle against other online opponents. Our battles provide players with the same thrilling battle experience as playing the game with physical cards at a table. As players hone their skills in battle they can progressively move up our battle ranking system, and even become the best in the world. What’s more, players looking for higher stakes in their battles are able to wager their cards in battle against others.
In addition to wagering cards, Combat Critters 2.0 offers many other other ways in which players can collect cards. The one we are most excited for is our flea market. The flea market will enable players to browse through trade offers put up by others, as well as put up for trades of their own. These trades can consist of any combination of currency and cards.
The other main way of obtaining cards is through our digital vendors. These vendors offer daily trades that provide our users with more card collecting options. To make these vendors more engaging, you are able to level them up by trading with them. As they level up they will begin to offer new and exciting trades.
Combat Critters 2.0 will also feature an in depth profile system. With this system players will be able to add friends, feature their favourite decks on their profile for their friends to see, view and copy their friends decks, and even challenge their friends to a friendly battle.

### Who is Combat Critters 2.0 for?

Combat Critters 2.0 is designed primarily for individuals with an interest in trading cards and competitive gaming. It will allow players of all age groups to collect their favourite cards and personalize their own card decks. We expect our primary player demographic to be those of all ages with an interest in card games. Combat Critter’s simple and exciting design makes it suitable for everyone.

### Why is Combat Critters 2.0 valuable?

The platform is valuable to people who enjoy playing card games but may not always have time to sit down and play at a table with friends. We also want to provide an easy way to learn, yet an exciting experience to a wide range of people. This project allows anyone to play the game on the go whenever they have free time, rather than having to carry around physical cards. In addition, our vendors, flea market, and wagering system gives players a variety of ways to collect cards, granting them freedom in player expression with how they manage their deck.
Non-functional requirements
One non-functional requirement we will meet at the end of our project is the ability to handle one-hundred battles being fought simultaneously.

### How will Combat Critters 2.0 be successful?

We will consider Combat Critters 2.0 a success if one year after release we have one-hundred thousand daily users.

Combat Critters is a new and fun game for all!

## Initial Architecture

Link to [intial_architecture](https://docs.google.com/presentation/d/1ejMu4u_MBDTiwN8okK5Jnudc20xtLdkQM-5kvj9lD6E/edit#slide=id.g3011f795577_9_0).

MAUI and React both provide good UI frameworks and can interface with a web api provided by spring. Since we are continuing a previous project and reusing some of the codebase, Java is required and spring boot seems like the commonly used library that will suit our requirements. Again since the codebase already exists, continuing with SQL makes sense and Postgres is free and open source. Using EC2(and S3/AWS overall) for hosting works well to meet our needs and it allows us to leverage other AWS services should the need arise.

## Work Division

We will try to separate dev tasks equally in correspondence to everyone's schedule and ability. However, if one of us is busy with something else, the rest of us will try our best to cover his task. We will divide our tasks on the Github project task board, making sure everyone has only one task at a time. We will at minimum host one in-person meeting every week on Wednesday to make sure everyone is on the same page. We will communicate using discord in case there are any issues

# Combat Critters 1.0

## Milestones

### Iteration 0

- Our [Vision Statement.](https://code.cs.umanitoba.ca/comp3350-winter2024/internetenemies-a02-10/-/blob/dev/Documents/VISION.md?ref_type=heads)
- Our [Features.](https://code.cs.umanitoba.ca/comp3350-winter2024/internetenemies-a02-10/-/issues/?sort=label_priority&state=all&label_name%5B%5D=T%20-%20Feature&first_page_size=20#)
- Our [User Stories.](https://code.cs.umanitoba.ca/comp3350-winter2024/internetenemies-a02-10/-/issues/?sort=label_priority&state=all&label_name%5B%5D=T%20-%20Story&first_page_size=20#)

### Iteration 1

- Our [Architecture](https://code.cs.umanitoba.ca/comp3350-winter2024/internetenemies-a02-10/-/blob/dev/Documents/ARCHITECTURE.md?ref_type=heads#architecture-iteration-1)
- Rules for [Building a Deck](https://code.cs.umanitoba.ca/comp3350-winter2024/internetenemies-a02-10/-/blob/dev/Documents/Deck%20Construction%20Rules.md#deck-requirements)

### Iteration 2

- Our updated [Architecture](https://code.cs.umanitoba.ca/comp3350-winter2024/internetenemies-a02-10/-/blob/dev/Documents/ARCHITECTURE.md?ref_type=heads#architecture-iteration-2) for iteration 2.
- _NOTE: this app is meant to run landscape, please do not rotate to portrait mode!_

### Iteration 3

- Our final [Architecture](https://code.cs.umanitoba.ca/comp3350-winter2024/internetenemies-a02-10/-/blob/dev/Documents/ARCHITECTURE.md?ref_type=heads#architecture-iteration-3) for the project.
- Our [Retrospective](https://code.cs.umanitoba.ca/comp3350-winter2024/internetenemies-a02-10/-/blob/dev/Documents/RETROSPECTIVE.md?ref_type=heads#retrospectivemd-document) write up.
- Our [Coding Standards](https://code.cs.umanitoba.ca/comp3350-winter2024/internetenemies-a02-10/-/blob/dev/Documents/coding%20standard.md?ref_type=heads#coding-standards)

#### Purpose

The purpose of our project was to make a fun, strategy based card game to play on the go, hence our vision statements success criteria: "This will be a success if players find it easier and more efficient than playing card games in person". This takes the idea of playing a card game like `Yu-Gi-Oh` or `Pokemon` and puts our own twist on it with the style of gameplay against the computer controlled battle opponents, and our card theme. We wanted to make something new and fresh because those two games have been around for a long time and we feel like they needed some more competition. Although we do not have physical cards to sell in stores like those two games, we think we have captured the essence of them fairly well with our own spin. This game is meant to be enjoyed by everyone that has an interest in strategy style cards games and we hope that this is the case!

#### Running Combat Critters

Clone the repository to your android studio and then run the app on your emulator or a physical device (i.e. the google pixel tablet). Then you are good to go, you are on the home screen of the game and are free to play!
