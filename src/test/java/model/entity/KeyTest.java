package model.entity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class KeyTest {

    @Test
    void TestCreateKey() {
        Key key = new Key(16, 21);
        assertNotNull(key);
        assertEquals(16, key.getPosition().getX());
        assertEquals(21, key.getPosition().getY());
    }
}
