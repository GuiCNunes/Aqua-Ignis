package com.Nunes.aqua_and_ignis.view.mazeView;

import com.Nunes.aqua_and_ignis.gui.Color;
import com.Nunes.aqua_and_ignis.model.Position;
import com.Nunes.aqua_and_ignis.view.Theme.Colors;

public class Lighting {

    private static final double EDGE_BRIGHTNESS = 0.5;
    private static final double MAX_TINT = 0.2;

    private final int radius;

    public Lighting(int radius) {
        this.radius = radius;
    }

    public double brightness(int x, int y, Position aqua, Position ignis) {
        return EDGE_BRIGHTNESS + (1 - EDGE_BRIGHTNESS) * closeness(x, y, aqua, ignis);
    }

    public Color light(Color color, int x, int y, Position aqua, Position ignis) {
        Color tint = distance(x, y, aqua) <= distance(x, y, ignis) ? Colors.AQUA : Colors.IGNIS;
        double amount = MAX_TINT * closeness(x, y, aqua, ignis);
        return color.mixed(tint, amount).scaled(brightness(x, y, aqua, ignis));
    }

    private double closeness(int x, int y, Position aqua, Position ignis) {
        double nearest = Math.min(distance(x, y, aqua), distance(x, y, ignis));
        return Math.max(0, 1 - nearest / radius);
    }

    private double distance(int x, int y, Position player) {
        return Math.hypot(x - player.getX(), y - player.getY());
    }
}
