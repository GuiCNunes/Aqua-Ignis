package com.Nunes.aqua_and_ignis.view.menuView;

import com.Nunes.aqua_and_ignis.gui.Color;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class BackgroundTest {
    private GUI gui;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        when(gui.getWidth()).thenReturn(92);
        when(gui.getHeight()).thenReturn(32);
    }

    @Test
    void fireIsOnTheLeftAndWaterOnTheRight() {
        for (int y = 0; y < 32; y++) {
            assertTrue(Background.isFire(0, y, 92));
            assertFalse(Background.isFire(91, y, 92));
        }
    }

    @Test
    void theSplitIsJagged() {
        boolean differs = false;
        for (int y = 1; y < 32; y++) {
            differs |= Background.isFire(45, y, 92) != Background.isFire(45, 0, 92);
        }
        assertTrue(differs);
    }

    @Test
    void everyRowIsFilledEdgeToEdge() {
        Background.draw(gui);

        ArgumentCaptor<Integer> x = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> y = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> w = ArgumentCaptor.forClass(Integer.class);
        verify(gui, atLeast(32)).fillRect(x.capture(), y.capture(), w.capture(), eq(1), any(Color.class));
        int[] covered = new int[32];
        for (int i = 0; i < x.getAllValues().size(); i++) {
            covered[y.getAllValues().get(i)] += w.getAllValues().get(i);
        }
        for (int row : covered) assertEquals(92, row);
    }

    @Test
    void skyIsDarkerAtTheTopThanNearTheHorizon() {
        Background.draw(gui);

        verify(gui).fillRect(eq(0), eq(1), anyInt(), eq(1), eq(Colors.FIRE_SKY_TOP));
        verify(gui, atLeastOnce()).fillRect(anyInt(), eq(17), anyInt(), eq(1), eq(Colors.FIRE_SKY_LOW));
    }

    @Test
    void drawsNothingOnAnEmptyWindow() {
        when(gui.getWidth()).thenReturn(0);
        when(gui.getHeight()).thenReturn(0);

        Background.draw(gui);

        verify(gui, never()).fillRect(anyInt(), anyInt(), anyInt(), anyInt(), any());
    }
}
