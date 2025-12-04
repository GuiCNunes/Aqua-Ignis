package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import model.Maze;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class MazeControllerTest {

    @Mock
    private Maze mockMaze;

    private MazeController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new MazeController(mockMaze);
    }

    @Test
    void testProcessKeyCallsMazeProcessKey() {
        KeyStroke key = new KeyStroke(KeyType.ArrowUp);

        controller.processKey(key);

        verify(mockMaze, times(1)).processKey(key);
    }

    @Test
    void testProcessKeyWithMultipleCalls() {
        KeyStroke key1 = new KeyStroke(KeyType.ArrowUp);
        KeyStroke key2 = new KeyStroke(KeyType.ArrowDown);

        controller.processKey(key1);
        controller.processKey(key2);

        verify(mockMaze, times(2)).processKey(any(KeyStroke.class));
    }
}
