# Aqua & Ignis

## Game Description
The Aqua & Ignis is a dual player escape room, where the goal for each player, Aqua and Ignis to find each other and leave the room,
They will have to make sure they are safe from monsters and to find each other in the room labirint while being under low vision habilities.
There will be levels in wich monsters in larger quantities and diferent maps acording to difficulti.

This project was developed by Carlos Costa (up202405390@up.pt), Guilherme Nunes (up202404656@up.pt) and João Barros (up202405577@up.pt).

## Implemented Features

-**two players** - both players are implemented.

-**Buttons** - Functional and interactive buttons.

-**Players Control** - Each player has the habilitie to move using the keyboard control.

-**Collisions detection**- collisions between different objects are verified.(Ex.: both players, monstersm walls)

-**Low-vision habilities**- each player only has the habilitie to a certaint space arround its position.

-**Monsters** - monsters are implemented

## Planned Features

-**Monsters** - monsters movement.

-**Connected Menus** - the user can browse throw different menus. (EX: Main Menu, Play and Levels)

-**Implement the maps** - implement the labirints we create in png.

-**Textures for players, monsters and ambient** - having texture for everything

-**Form of atack** - each player will have the habilitie to atack monsters.


## Design

-**Problem in Context.**
When we started the project, we were faced with the problem of deciding which patterns to use for the game and which ones would best fulfil our needs, especially considering that we wanted to support different game states in the future and use a graphical user interface (GUI).

-**The Partern**-
The architectural pattern used in the project was Model–View–Controller (MVC).
We also used the Game Loop pattern to run the main game cycle and a simple delegation pattern in the view layer,
where generic views delegate the concrete rendering to specific entity views.

-**The Implementation**-
During the development of the game we realised that, if we mixed game logic, drawing on the screen and input handling in the same classes, the code would quickly become hard to understand and maintain, so we decided to use the Model–View–Controller (MVC) architectural pattern.
In our project, the Model represents the game state and rules (players, monsters, map, etc.),
the View is responsible for displaying this state to the user (rendering the map, players and monsters),
and the Controller connects player input to the Model by reading keyboard actions and button clicks and invoking the appropriate methods on the Model.
In the Game class we also used the Game Loop pattern, which repeatedly reads the input, updates the state and redraws the game.
Additionally, we apply a simple delegation pattern in the view layer, where generic views for players and monsters delegate the concrete rendering to specific views for each entity type.

-**Consequences**-
As a consequence, the codebase is more organised and modular, it is easier to add new levels, monsters or mechanics without breaking the rest of the project, and bugs are easier to trace to either logic, rendering or input.
On the other hand, MVC introduces more classes and requires some discipline to keep the layers properly separated, which can feel like extra overhead for a small game, but we consider that the gain in clarity, maintainability and extensibility clearly outweighs this cost.
Using gameloops also makes it easier to organize the code. HOwever it also means that every iteration must keep the game state consistent.
Finally, the use of delegation in the view layer makes it simpler to extend and customise the visual representation of each entity, at the cost of adding an extra level of indirection between the game logic and the concrete rendering code.

## Mockups

![image alt](https://github.com/FEUP-LDTS-2025/project-t01g09/blob/main/src/main/resources/Mockups/MOCKUPMENU.webp?raw=true)
![image alt](https://github.com/FEUP-LDTS-2025/project-t01g09/blob/main/src/main/resources/Mockups/helpmockup.webp?raw=true)
![image alt](https://github.com/FEUP-LDTS-2025/project-t01g09/blob/main/src/main/resources/Mockups/mockupmapa.webp?raw=true)
![image alt](https://github.com/FEUP-LDTS-2025/project-t01g09/blob/main/src/main/resources/Mockups/mochupwithvision.webp?raw=true)
