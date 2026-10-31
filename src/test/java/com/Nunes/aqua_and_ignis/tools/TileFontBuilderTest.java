package com.Nunes.aqua_and_ignis.tools;

import com.Nunes.aqua_and_ignis.gui.Tile;
import org.junit.jupiter.api.Test;

import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class TileFontBuilderTest {

    @Test
    void everyTileGlyphCanBeDisplayed() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (InputStream sprites = getClass().getResourceAsStream("/sprites/sprites.txt")) {
            TileFontBuilder.build(sprites, out);
        }

        Font font = Font.createFont(Font.TRUETYPE_FONT, new ByteArrayInputStream(out.toByteArray()));

        for (Tile tile : Tile.values())
            assertTrue(font.canDisplay(tile.glyph()), tile.name());
    }

    @Test
    void missingSpriteIsRejected() {
        InputStream sprites = new ByteArrayInputStream("[FLOOR]\n".getBytes(StandardCharsets.UTF_8));

        assertThrows(IllegalArgumentException.class,
                () -> TileFontBuilder.build(sprites, new ByteArrayOutputStream()));
    }

    @Test
    void badRowIsRejected() {
        String text = "[FLOOR]\n" + "................\n".repeat(15) + "..............\n";
        InputStream sprites = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));

        assertThrows(IllegalArgumentException.class,
                () -> TileFontBuilder.build(sprites, new ByteArrayOutputStream()));
    }
}
