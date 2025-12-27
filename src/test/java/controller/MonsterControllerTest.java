package controller;

import gui.GUI;
import model.Game;
import model.Maze;
import model.Position;
import model.entity.Ghost;
import model.entity.Monster;
import model.entity.Slime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class MonsterControllerTest {
    private Maze maze;
    private Game game;
    private MonsterController controller;

    @BeforeEach
    void setUp() {
        maze = mock(Maze.class);
        game = mock(Game.class);
        controller = new MonsterController(maze);
    }

    @Test
    void testCommandUpdates() throws IOException {
        Slime slime = mock(Slime.class);
        Ghost ghost = mock(Ghost.class);
        Position pos = mock(Position.class);
        Position nextPos = mock(Position.class);

        when(slime.getPosition()).thenReturn(pos);
        when(ghost.getPosition()).thenReturn(pos);
        when(pos.getRandomNeighbour()).thenReturn(nextPos);

        List<Monster> monsters = new ArrayList<>();
        monsters.add(slime);
        monsters.add(ghost);
        when(maze.getMonsters()).thenReturn(monsters);

        controller.command(game, GUI.operation.none, 600);

        verify(pos, times(2)).getRandomNeighbour();
    }

    @Test
    void testCommand() throws IOException {
        Monster monster = mock(Monster.class);
        List<Monster> monsters = List.of(monster);
        when(maze.getMonsters()).thenReturn(monsters);

        controller.command(game, GUI.operation.none, 100);

        verify(monster, never()).getPosition();
    }

    @Test
    void testCommandMultipleUpdates() throws IOException {
        Slime slime = mock(Slime.class);
        Position pos = mock(Position.class);
        when(slime.getPosition()).thenReturn(pos);
        when(pos.getRandomNeighbour()).thenReturn(mock(Position.class));

        when(maze.getMonsters()).thenReturn(List.of(slime));

        controller.command(game, GUI.operation.none, 600);
        verify(pos, times(1)).getRandomNeighbour();

        controller.command(game, GUI.operation.none, 700);
        verify(pos, times(1)).getRandomNeighbour();

        controller.command(game, GUI.operation.none, 1200);
        verify(pos, times(2)).getRandomNeighbour();
    }

}