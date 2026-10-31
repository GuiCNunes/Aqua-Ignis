package com.Nunes.aqua_and_ignis.view;

public final class Scale {
    public static int SCALE = 2;

    public static void setScale (int x){ SCALE = x;}

    public static int fontSize() {
        return switch (SCALE) {
            case 1 -> 12;
            case 3 -> 24;
            default -> 16;
        };
    }

    private Scale() {}
}
