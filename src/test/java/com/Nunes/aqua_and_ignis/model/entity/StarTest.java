package com.Nunes.aqua_and_ignis.model.entity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.Nunes.aqua_and_ignis.model.entity.Star;
import org.junit.jupiter.api.Test;

class StarTest {

    @Test
    void TestCreatStar(){
        Star star = new Star(15, 15);
        assertNotNull(star);
        assertEquals(15, star.getPosition().getX());
        assertEquals(15, star.getPosition().getY());
    }
}
