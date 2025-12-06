package controller;
/*
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import model.Maze;
import model.Position;
import model.entity.Ignis;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class MazeControllerTest {

    @Mock
    private Maze mockMaze;

    @Mock
    private Ignis mockIgnis;

    private MazeController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new MazeController(mockMaze);

        when(mockMaze.getIgnis()).thenReturn(mockIgnis);

        when(mockIgnis.moveUp()).thenReturn(new Position(10, 9));
        when(mockIgnis.moveDown()).thenReturn(new Position(10, 11));
    }

    @Test
    void testProcessKeyCallsMazeProcessKey() {
        KeyStroke key = new KeyStroke(KeyType.ArrowUp);

        controller.processKey(key);

        verify(mockMaze, times(1)).movePlayer(mockIgnis, mockIgnis.moveUp());
    }

    @Test
    void testProcessKeyWithMultipleCalls() {
        KeyStroke key1 = new KeyStroke(KeyType.ArrowUp);
        KeyStroke key2 = new KeyStroke(KeyType.ArrowDown);

        controller.processKey(key1);
        controller.processKey(key2);

        verify(mockMaze, times(1)).movePlayer(mockIgnis, mockIgnis.moveUp());
        verify(mockMaze, times(1)).movePlayer(mockIgnis, mockIgnis.moveDown());
    }
}

 */
