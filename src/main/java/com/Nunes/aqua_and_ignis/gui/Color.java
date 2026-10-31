package com.Nunes.aqua_and_ignis.gui;

public record Color(int r, int g, int b) {
    public static Color ofHex(String hex) {
        String h = hex.startsWith("#") ? hex.substring(1) : hex;
        int rgb = Integer.parseInt(h, 16);
        return new Color((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
    }

    public Color scaled(double factor) {
        return new Color(scale(r, factor), scale(g, factor), scale(b, factor));
    }

    private static int scale(int channel, double factor) {
        return (int) Math.max(0, Math.min(255, Math.round(channel * factor)));
    }
}
