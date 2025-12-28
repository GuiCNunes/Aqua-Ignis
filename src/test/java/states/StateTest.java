package states;

import controller.Controller;
import gui.GUI;
import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.View;

import java.io.IOException;

import static org.mockito.Mockito.*;

class StateTest {
    private Game game;
    private GUI gui;
    private View<Object> view;
    private Controller<Object> controller;
    private Object model;
    private State<Object> state;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        game = mock(Game.class);
        gui = mock(GUI.class);
        view = mock(View.class);
        controller = mock(Controller.class);
        model = new Object();

        state = new State<>(model) {
            @Override
            public View<Object> getView() {
                return view;
            }

            @Override
            public Controller<Object> getController() {
                return controller;
            }
        };
    }

    @Test
    void commandFlow() throws IOException {
        long time = 500L;
        GUI.operation op = GUI.operation.up;

        when(gui.getOperation()).thenReturn(op);

        state.command(game, gui, time);

        verify(gui, times(1)).getOperation();
        verify(controller, times(1)).command(game, op, time);
        verify(view, times(1)).draw(gui);
    }
}