package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Game;
import gui.GUI;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Object model;
    private TestController controller;

    private static class TestController extends Controller<Object> {
        public TestController(Object model) {
            super(model);
        }

        @Override
        public void command(Game game, GUI.operation operation, long time) throws IOException {
        }
    }

    @BeforeEach
    void setUp() {
        model = new Object();
        controller = new TestController(model);
    }

    @Test
    void testGetModel() {
        assertEquals(model, controller.getModel());
        assertSame(model, controller.getModel());
    }

    @Test
    void testModelNotNull() {
        assertNotNull(controller.getModel());
    }

    @Test
    void testConstructorAssignment() {
        Object newModel = new Object();
        TestController newController = new TestController(newModel);
        assertSame(newModel, newController.getModel());
    }
}