package com.Nunes.aqua_and_ignis;

import com.Nunes.aqua_and_ignis.model.Game;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ApplicationTest {

    @Test
    void main() throws IOException {
        try (MockedConstruction<Game> mocked = mockConstruction(Game.class)) {
            Application.main(new String[]{});

            Game game = mocked.constructed().get(0);
            verify(game, times(1)).run();
        }
    }

    @Test
    void constructor() {
        Application application = new Application();
        assertNotNull(application);
    }
}