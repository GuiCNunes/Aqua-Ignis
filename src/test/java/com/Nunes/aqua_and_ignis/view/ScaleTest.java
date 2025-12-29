package com.Nunes.aqua_and_ignis.view;
import static org.junit.jupiter.api.Assertions.*;

import com.Nunes.aqua_and_ignis.view.Scale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
class ScaleTest {
    @BeforeEach
    void ResetScale() {
        Scale.SCALE = 1;
    }

    @Test
    void DefaultScaleTest() {
        assertEquals(1,  Scale.SCALE);;
    }
    @Test
    void SetScaleTest_2() {
        Scale.SCALE = 2;
        assertEquals(2,  Scale.SCALE);
    }

    @Test
    void SetScaleTest_3() {
        Scale.SCALE = 3;
        assertEquals(3,  Scale.SCALE);
    }

    @Test
    void SetScaleTest_4() {
        Scale.setScale(4);
        assertEquals(4,  Scale.SCALE);
    }

    @Test
    void SetScaleTest_0() {
        Scale.SCALE = 0;
        assertEquals(0,  Scale.SCALE);
    }

    @Test
    void SetScaleTest_Negative() {
        Scale.SCALE = -1;
        assertEquals(-1,  Scale.SCALE);
    }
}
