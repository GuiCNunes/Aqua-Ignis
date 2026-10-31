package com.Nunes.aqua_and_ignis.view.menuView;

import com.Nunes.aqua_and_ignis.gui.Color;
import com.Nunes.aqua_and_ignis.gui.GUI;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;

public final class Background {
    private static final int BANDS = 6;
    private static final int[] JAGGED = {0, 2, 1, 3, 1, -1, 0, -2};

    public static void draw(GUI gui) {
        int width = gui.getWidth();
        int height = gui.getHeight();
        for (int y = 0; y < height; y++) {
            drawRow(gui, y, width, height);
        }
    }

    public static boolean isFire(int x, int y, int width) {
        return x < width / 2 + JAGGED[y % JAGGED.length];
    }

    private static void drawRow(GUI gui, int y, int width, int height) {
        int start = 0;
        Color run = colorAt(0, y, width, height);
        for (int x = 1; x <= width; x++) {
            Color color = x < width ? colorAt(x, y, width, height) : null;
            if (!run.equals(color)) {
                gui.fillRect(start, y, x - start, 1, run);
                start = x;
                run = color;
            }
        }
    }

    private static Color colorAt(int x, int y, int width, int height) {
        boolean fire = isFire(x, y, width);
        int horizon = horizon(height);
        if (y > horizon) return waterColor(x, y, horizon, height, fire);
        if (y == horizon) return fire ? Colors.FIRE_HILL : Colors.WATER_HILL;
        if (y >= horizon - mountainHeight(x)) return fire ? Colors.FIRE_MOUNTAIN : Colors.WATER_MOUNTAIN;
        if (isStar(x, y)) return Colors.STAR_DIM;
        return skyColor(y, horizon, fire);
    }

    private static int horizon(int height) {
        return height * 5 / 8;
    }

    private static int mountainHeight(int x) {
        return 2 + Math.abs(x % 24 - 12) / 3 + (x / 24) % 2;
    }

    private static boolean isStar(int x, int y) {
        return (x * 17 + y * 31) % 29 == 0;
    }

    private static Color skyColor(int y, int horizon, boolean fire) {
        Color top = fire ? Colors.FIRE_SKY_TOP : Colors.WATER_SKY_TOP;
        Color low = fire ? Colors.FIRE_SKY_LOW : Colors.WATER_SKY_LOW;
        int band = Math.min(BANDS - 1, y * BANDS / (horizon - 2));
        return top.mixed(low, band / (double) (BANDS - 1));
    }

    private static Color waterColor(int x, int y, int horizon, int height, boolean fire) {
        Color base = fire ? Colors.FIRE_WATER : Colors.WATER_WATER;
        Color glint = fire ? Colors.FIRE_WATER_GLINT : Colors.WATER_WATER_GLINT;
        Color deep = fire ? Colors.FIRE_WATER_DEEP : Colors.WATER_WATER_DEEP;
        if ((x / 3 * 5 + y * 3) % 7 == 0) return glint;
        double depth = (y - horizon) / (double) (height - horizon);
        return base.mixed(deep, depth);
    }

    private Background() {}
}
