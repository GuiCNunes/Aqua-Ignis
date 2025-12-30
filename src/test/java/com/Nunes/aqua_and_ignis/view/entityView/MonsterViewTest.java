package com.Nunes.aqua_and_ignis.view.entityView;

import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.model.entity.Monster;
import com.Nunes.aqua_and_ignis.model.entity.Slime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

class MonsterViewTest {
    private MonsterView monsterView;
    private SlimeView slimeView;
    private GhostView ghostView;
    private GUI gui;

    @BeforeEach
    void setUp() throws Exception {
        monsterView = new MonsterView();
        slimeView = mock(SlimeView.class);
        ghostView = mock(GhostView.class);
        gui = mock(GUI.class);

        Field slimeField = MonsterView.class.getDeclaredField("slimeView");
        slimeField.setAccessible(true);
        slimeField.set(monsterView, slimeView);

        Field ghostField = MonsterView.class.getDeclaredField("ghostView");
        ghostField.setAccessible(true);
        ghostField.set(monsterView, ghostView);
    }

    @Test
    void drawSlime() {
        Slime slime = mock(Slime.class);

        monsterView.draw(slime, gui);

        verify(slimeView, times(1)).draw(slime, gui);
        verify(ghostView, never()).draw(any(), any());
    }

    @Test
    void drawGhost() {
        Monster ghost = mock(Monster.class);

        monsterView.draw(ghost, gui);

        verify(ghostView, times(1)).draw(ghost, gui);
        verify(slimeView, never()).draw(any(), any());
    }
}