package com.Nunes.aqua_and_ignis.controller;

import com.Nunes.aqua_and_ignis.controller.entity.MonsterController;
import com.Nunes.aqua_and_ignis.controller.entity.PlayerController;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.Game;
import com.Nunes.aqua_and_ignis.model.Maze;
import com.Nunes.aqua_and_ignis.model.MazeGenerator;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.model.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.Nunes.aqua_and_ignis.states.LoseState;
import com.Nunes.aqua_and_ignis.states.MenuState;
import com.Nunes.aqua_and_ignis.states.WinState;
import org.mockito.MockedConstruction;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class MazeControllerTest {
    private Maze maze;
    private Game game;
    private Ignis ignis;
    private Aqua aqua;
    private MazeController controller;

    @BeforeEach
    void setUp() {
        maze = mock(Maze.class);
        game = mock(Game.class);
        ignis = mock(Ignis.class);
        aqua = mock(Aqua.class);

        when(maze.getWidth()).thenReturn(20);
        when(maze.getHeight()).thenReturn(20);
        when(maze.getExit()).thenReturn(new Position(10, 10));

        when(maze.getIgnis()).thenReturn(ignis);
        when(maze.getAqua()).thenReturn(aqua);
        when(maze.getMonsters()).thenReturn(new ArrayList<>());
        when(maze.getStars()).thenReturn(new ArrayList<>());

        controller = new MazeController(maze);
    }

    @Test
    void testCheckWin() throws IOException {
        Position exit = new Position(10, 10);
        Position other = new Position(0, 0);
        when(maze.getExit()).thenReturn(exit);

        when(maze.isExitOpen()).thenReturn(false);
        when(ignis.getPosition()).thenReturn(exit);
        when(aqua.getPosition()).thenReturn(exit);
        controller.command(game, GUI.operation.none, 0);
        verify(game, never()).setState(any(WinState.class));

        when(maze.isExitOpen()).thenReturn(true);
        when(ignis.getPosition()).thenReturn(exit);
        when(aqua.getPosition()).thenReturn(other);
        controller.command(game, GUI.operation.none, 0);
        verify(game, never()).setState(any(WinState.class));

        when(ignis.getPosition()).thenReturn(other);
        when(aqua.getPosition()).thenReturn(exit);
        controller.command(game, GUI.operation.none, 0);
        verify(game, never()).setState(any(WinState.class));

        when(ignis.getPosition()).thenReturn(exit);
        when(aqua.getPosition()).thenReturn(exit);
        controller.command(game, GUI.operation.none, 0);
        verify(game).setState(any(WinState.class));
    }

    @Test
    void testCheckLoss() throws IOException {
        Position p1 = new Position(1, 1);
        Position p2 = new Position(2, 2);
        Monster m = mock(Monster.class);
        when(maze.getMonsters()).thenReturn(List.of(m));

        when(m.getPosition()).thenReturn(p1);
        when(ignis.getPosition()).thenReturn(p1);
        when(aqua.getPosition()).thenReturn(p2);
        controller.command(game, GUI.operation.none, 0);
        verify(game).setState(any(LoseState.class));

        reset(game);
        when(m.getPosition()).thenReturn(p2);
        when(ignis.getPosition()).thenReturn(p1);
        when(aqua.getPosition()).thenReturn(p2);
        controller.command(game, GUI.operation.none, 0);
        verify(game).setState(any(LoseState.class));
    }

    @Test
    void testSpawnMonsterProbability() throws IOException {
        when(maze.getMonsters()).thenReturn(new ArrayList<>());
        for (int i = 0; i < 100; i++) {
            controller.command(game, GUI.operation.none, 10000 + (i * 10001));
        }
        verify(maze, atLeastOnce()).addMonster(any(Slime.class));
        verify(maze, atLeastOnce()).addMonster(any(Ghost.class));
    }

    @Test
    void testSpawnMonsterTimerAndCapacity() throws IOException {
        controller.command(game, GUI.operation.none, 5000);
        verify(maze, never()).addMonster(any());

        List<Monster> fullList = new ArrayList<>();
        Position p = new Position(0,0);
        for (int i = 0; i < 15; i++) {
            Monster m = mock(Monster.class);
            when(m.getPosition()).thenReturn(p);
            fullList.add(m);
        }
        when(maze.getMonsters()).thenReturn(fullList);
        controller.command(game, GUI.operation.none, 10000);
        verify(maze, never()).addMonster(any());
    }

    @Test
    void testHandleCollisions() throws IOException {
        Position position = new Position(5, 5);
        Star star = mock(Star.class);
        Key key = mock(Key.class);
        List<Star> stars = new ArrayList<>(List.of(star));

        when(ignis.getPosition()).thenReturn(position);
        when(aqua.getPosition()).thenReturn(new Position(0,0));
        when(star.getPosition()).thenReturn(position);
        when(key.getPosition()).thenReturn(position);
        when(maze.getStars()).thenReturn(stars);
        when(maze.getKey()).thenReturn(key);

        controller.command(game, GUI.operation.none, 0);

        verify(maze).incrementStars();
        verify(maze).setExitOpen(true);
        verify(maze).setKey(null);
    }

    @Test
    void commandQuit() throws IOException {
        controller.command(game, GUI.operation.quit, 0);
        verify(game).setState(any(MenuState.class));
    }

    @Test
    void testController() throws Exception {
        PlayerController playerController = mock(PlayerController.class);
        MonsterController monsterController = mock(MonsterController.class);

        Field playerField = MazeController.class.getDeclaredField("playerController");
        playerField.setAccessible(true);
        playerField.set(controller, playerController);

        Field monsterField = MazeController.class.getDeclaredField("monsterController");
        monsterField.setAccessible(true);
        monsterField.set(controller, monsterController);

        controller.command(game, GUI.operation.up, 0);

        verify(playerController).command(game, GUI.operation.up, 0);
        verify(monsterController).command(game, GUI.operation.up, 0);
    }

    @Test
    void testCheckItemsAqua() throws IOException {
        Position position = new Position(1, 1);
        when(aqua.getPosition()).thenReturn(position);
        when(ignis.getPosition()).thenReturn(new Position(0, 0));

        Star star = mock(Star.class);
        when(star.getPosition()).thenReturn(position);
        List<Star> stars = new ArrayList<>();
        stars.add(star);
        when(maze.getStars()).thenReturn(stars);

        controller.command(game, GUI.operation.none, 0);

        verify(maze).incrementStars();
        assertTrue(stars.isEmpty());
    }

    @Test
    void testKeyNullBranch() throws IOException {
        when(maze.getKey()).thenReturn(null);
        controller.command(game, GUI.operation.none, 0);
        verify(maze, never()).setExitOpen(anyBoolean());
    }

    @Test
    void testSpawnTimer() throws IOException {
        controller.command(game, GUI.operation.none, 9999);
        verify(maze, never()).addMonster(any());

        controller.command(game, GUI.operation.none, 10000);
        verify(maze, times(1)).addMonster(any());
    }

    @Test
    void testConstructorGeneration() throws Exception {
        Maze mazeMock = mock(Maze.class);
        try (MockedConstruction<MazeGenerator> mocked = mockConstruction(MazeGenerator.class)) {
            new MazeController(mazeMock);
            verify(mocked.constructed().get(0)).generate(mazeMock);
        }
    }

    @Test
    void testNoStarCollision() throws IOException {
        Position pIgnis = new Position(1, 1);
        Position pStar = new Position(2, 2);
        when(ignis.getPosition()).thenReturn(pIgnis);
        when(aqua.getPosition()).thenReturn(new Position(0, 0));

        Star star = mock(Star.class);
        when(star.getPosition()).thenReturn(pStar);
        when(maze.getStars()).thenReturn(new ArrayList<>(List.of(star)));

        controller.command(game, GUI.operation.none, 0);
        verify(maze, never()).incrementStars();
    }

    @Test
    void testKeyCollision() throws IOException {
        Position pIgnis = new Position(1, 1);
        Position pKey = new Position(2, 2);
        Key key = mock(Key.class);

        when(ignis.getPosition()).thenReturn(pIgnis);
        when(aqua.getPosition()).thenReturn(pIgnis);
        when(key.getPosition()).thenReturn(pKey);
        when(maze.getKey()).thenReturn(key);

        controller.command(game, GUI.operation.none, 0);
        verify(maze, never()).setExitOpen(true);
    }

    @Test
    void testSpawnTimerAdditionNegation() throws IOException {
        controller.command(game, GUI.operation.none, 10000);
        verify(maze, times(1)).addMonster(any());

        controller.command(game, GUI.operation.none, 11000);
        verify(maze, times(1)).addMonster(any());
    }

    @Test
    void testSpawnMonsterRandomNegation() throws IOException {
        when(maze.getMonsters()).thenReturn(new ArrayList<>());

        try (MockedConstruction<Random> mockedRandom = mockConstruction(Random.class, (mock, context) -> {
            when(mock.nextBoolean()).thenReturn(true);
        })) {
            controller.command(game, GUI.operation.none, 10000);
            verify(maze).addMonster(any(Slime.class));
            verify(maze, never()).addMonster(any(Ghost.class));
        }

        try (MockedConstruction<Random> mockedRandom = mockConstruction(Random.class, (mock, context) -> {
            when(mock.nextBoolean()).thenReturn(false);
        })) {
            controller.command(game, GUI.operation.none, 20001);
            verify(maze).addMonster(any(Ghost.class));
        }
    }
}