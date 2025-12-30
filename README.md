# Aqua & Ignis

## Game Description
The Aqua & Ignis is a dual player escape room, where the goal for each player, Aqua and Ignis to find each other and leave the room,
They will have to make sure they are safe from monsters and to find each other in the room labyrinth while being under low vision habilities.
There will be levels in wich monsters in larger quantities and diferent maps acording to difficulty.

This project was developed by Carlos Costa (up202405390@up.pt), Guilherme Nunes (up202404656@up.pt) and João Barros (up202405577@up.pt).

## Implemented Features

-**Two players** - Both players are implemented.

-**Buttons** - Functional and interactive buttons.

-**Players Control** - Each player has the habilitie to move using the keyboard control.

-**Collisions detection** - Collisions between different objects are verified.(Ex.: both players, monsters, walls)

-**Low-vision habilities** - Each player only has the abilities to a certain radius around its position.

-**Monsters** - Monsters are implemented.

-**Connected Menus** - The user can browse throw different menus. (EX: Main Menu, Play and Help)

-**Monsters Movement** - Monsters moving using a time-based logic.

-**Path View** - The path of each player is remembered.

-**Random maze algorithm** 

-**Clock** - Clock that counts the seconds of in game time.

-**Stars** - 3 starts that the players can catch.

-**Key** - Key that players catch to open a door to therefore win the game.

-**Scale**- Feature that makes possible the for the players to choose between 3 different sizes for the window.


## Planned Features
All of them were implemented


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

-**Consequences**-
As a consequence, the codebase is more organised and modular, it is easier to add new levels, monsters or mechanics without breaking the rest of the project, and bugs are easier to trace to either logic, rendering or input.
On the other hand, MVC introduces more classes and requires some discipline to keep the layers properly separated, which can feel like extra overhead for a small game, but we consider that the gain in clarity, maintainability and extensibility clearly outweighs this cost.

## Game loop

-**Problem in context**
While creating our game we quickly realized we needed continuous updates while the game was running: a timer that keeps counting the time spent in-game and the constant update of gameplay (reading input, updating the current state, and drawing the new frame).
Because of that, from the very beginning we needed a mechanism that repeatedly updates the game over time.

-**The Pattern**
We used the **Game Loop pattern**, a behavioral pattern where the program runs a loop that repeatedly processes input, updates the game logic, and renders the frame. 
This ensures the game keeps running in a predictable cycle until it is told to stop.

-**The Implementation**-
We implemented the loop in the Game class, inside the run() method.
While the current state is not null, the loop keeps calling state.command(this, gui, start), which handles the input and updates/draws the current state. To keep the loop stable, we aim for around 30 FPS by using frameTime = 1000 / 30 and sleeping the remaining time of the frame whenever possible.

-**Consequences**
This pattern makes the game flow consistent and easy to manage, since all updates happen continuously at a controlled rhythm. It also makes it simpler to keep features like timers and animations in sync with the game execution. However, frame timing is not perfectly precise because Thread.sleep() depends on the operating system scheduler, so small variations can happen. Also, if the work inside a frame becomes too heavy, the game may fail to keep 30 FPS, which can make updates feel less smooth.

## State Pattern

-**Problem in context**
As the game grew, we needed to support multiple screens and behaviors such as the Main Menu, Help screen, the actual Gameplay, and the Win/Lose screens. Each of these modes reacts differently to input and renders different content. If we handled everything inside a single class using many if/else statements, the code would quickly become hard to read, extend, and maintain.

-**The Pattern**
We used the State pattern, a behavioral pattern where an object changes its behavior depending on its internal state. Instead of using conditionals, the current state is represented by a dedicated object, and switching behavior is done by replacing that state object.

-**The Implementation**-
We implemented this pattern through the abstract State class and multiple concrete states: MenuState, HelpState, GameState, WinState, and LoseState. The Game class holds a reference to the current State and, during the game loop, delegates each frame to state.command(this, gui, start). Each state provides its own Controller and View, so input handling and rendering automatically match the active state. When a transition is required (for example starting the game from the menu, opening the help screen, or finishing the game), the controller triggers it by calling game.setState(new ...), replacing the current state object.

-**Consequences**
This makes the codebase more modular and easier to extend, since adding a new screen/state usually only requires creating a new State class with its own view and controller, without modifying existing states. It also reduces complex conditionals and keeps state-specific logic isolated. The main downside is the increased number of classes and the need to keep transitions well organized, otherwise it can become harder to track how the game flows between states.

## GUI

-**Problem in context**
With the goal of simplifying our rendering and input code, we wanted to avoid spreading Lanterna-specific classes and logic across the entire project.
Without an abstraction, every view/controller would need to know how to read keys using Lanterna and how to draw rectangles/text using Lanterna’s API, which would increase coupling and make future changes harder.

-**The Pattern**
We used the Facade pattern, which provides a simplified interface to a more complex subsystem.
In our case, the GUI interface exposes a small set of high-level methods (input operations and drawing primitives) while hiding all Lanterna details behind a single component.

-**Implementation**
We implemented the facade through the GUI interface and its concrete class LanternaGUI. The interface defines the operations the rest of the application needs (such as getOperation(), fillRect(), drawText(), etc.), while LanternaGUI contains all the Lanterna-specific setup (screen creation, font configuration, terminal window configuration) and the mapping between raw KeyStroke events and our own operation enum. We also introduced our own Color type so that the views only depend on our code and not on Lanterna’s TextColor.

-**Consequences**-
This approach greatly reduces coupling between the game logic/views and the Lanterna library, making the codebase easier to read and maintain. It also centralizes input handling and rendering details in one place, which simplifies debugging and makes it easier to change the GUI backend in the future (for example replacing Lanterna with another library). The main downside is that the facade must be updated whenever we need new drawing features not present in the current interface.


In the Game class we also used the Game Loop pattern, which repeatedly reads the input, updates the state and redraws the game.
Additionally, we apply a simple delegation pattern in the view layer, where generic views for players and monsters delegate the concrete rendering to specific views for each entity type.


## Construction of interface

-**Problem in context**
While developing the game we noticed that every screen (menu, help, gameplay, win/lose) followed the same rendering workflow: clear the screen, draw the elements for that screen, and then refresh the display. Without a common structure, we would have to duplicate this sequence in every view class, increasing repetition and making it easier to introduce inconsistencies (for example forgetting to clear or refresh).

-**The Pattern**
We used the Template Method pattern, a behavioral pattern where an abstract class defines the skeleton of an algorithm and leaves some steps to be implemented by subclasses. This allows all concrete views to share the same rendering pipeline while still customizing what is actually drawn.

-**Implementation**
We implemented the pattern in the abstract class View<T>. The method draw(GUI gui) defines the fixed sequence of steps: it clears the screen, calls the abstract method drawElements(gui), and then refreshes the screen. Each concrete view (such as MenuView, HelpView, MazeView, WinView, and LoseView) overrides drawElements(gui) to draw the specific content for that state, while the overall rendering flow remains consistent across the project.

-**Consequences**-
This approach reduces code duplication and guarantees that all screens are rendered using the same reliable process, improving maintainability and readability. It also makes it easier to add new screens, since new views only need to implement drawElements(gui) and automatically inherit the correct rendering workflow. The main drawback is reduced flexibility: if a specific screen needs a different rendering pipeline (for example multiple refreshes per frame or special transitions), the template may need to be extended or adjusted, otherwise subclasses are forced to follow the same structure.

## Monsters Movement

-**Problem in context**
During development we needed different behaviors for entities that are conceptually similar but act differently. In particular, different monster types should move according to their own rules (for example, ghosts can ignore walls while slimes cannot). If we implemented all movement logic inside a single method with many conditionals, the code would become harder to extend whenever new monster types were added.

-**The Pattern**
We used the Strategy pattern, a behavioral pattern that defines a family of algorithms, encapsulates each one, and makes them interchangeable. This allows the behavior to vary independently from the code that uses it.

-**Implementation**
We implemented different movement strategies through dedicated controllers for each monster type. The MonsterController iterates through all monsters and delegates the movement to the appropriate strategy controller: GhostController handles ghost movement and SlimeController handles slime movement. This selection is currently done using a simple dispatcher with instanceof, but the movement algorithm itself is encapsulated inside each specific controller (moveMonster(...)). As a result, each monster type follows its own movement rules without forcing a single monolithic movement method.

-**Consequences**-
This design makes it easier to add new monster types with new behaviors: we can create a new controller/strategy for that monster and integrate it without rewriting existing movement logic. It also improves readability because each behavior is isolated in its own class. The downside is the increase in the number of classes and the extra indirection introduced by delegation. Additionally, using instanceof for dispatching is simple but can become less scalable as the number of monster types grows, requiring refactoring to a more flexible registration-based approach.

## UML
![image alt](https://github.com/FEUP-LDTS-2025/project-t01g09/blob/main/src/main/resources/UML/img.png?raw=true)

## Known-code smells
We believe we fix all the errors. No major code smells identified.

## Testing
![image_alt](https://github.com/FEUP-LDTS-2025/project-t01g09/blob/main/src/main/resources/Images/Screenshot%20from%202025-12-30%2022-43-51.png)
![image_alt](https://github.com/FEUP-LDTS-2025/project-t01g09/blob/main/src/main/resources/Images/Screenshot%20from%202025-12-30%2022-44-03.png)

## Screenshot of coverage report
![image_alt](https://github.com/FEUP-LDTS-2025/project-t01g09/blob/main/src/main/resources/Images/Screenshot%20from%202025-12-30%2022-45-31.png)

## Link to mutation testing report

## Self-evaluation
During this project we believe that the work was not divided fully equallt, with:
-Guilherme Nunes leading the project with 42%;
-Carlos Costa with 33%;
-João Barros with 25%;


