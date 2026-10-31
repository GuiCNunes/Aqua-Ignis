package com.Nunes.aqua_and_ignis.gui;

import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class TileFontConfiguration extends AWTTerminalFontConfiguration {

    public TileFontConfiguration(int fontSize) {
        super(false, BoldMode.EVERYTHING_BUT_SYMBOLS, loadFonts(fontSize));
    }

    private static Font[] loadFonts(int fontSize) {
        Font tileFont = loadTileFont().deriveFont(Font.PLAIN, (float) fontSize);
        Font textFont = new Font("Monospaced", Font.PLAIN, fontSize);
        return new Font[]{tileFont, textFont};
    }

    private static Font loadTileFont() {
        try (InputStream in = TileFontConfiguration.class.getResourceAsStream("/fonts/AquaIgnisTiles.ttf")) {
            return Font.createFont(Font.TRUETYPE_FONT, in);
        } catch (IOException | FontFormatException e) {
            throw new IllegalStateException("Could not load the tile font", e);
        }
    }
}
