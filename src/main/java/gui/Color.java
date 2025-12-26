package gui;

public record Color(int r, int g, int b) {
    public static Color ofHex(String hex) {
        String h = hex.startsWith("#") ? hex.substring(1) : hex;
        int rgb = Integer.parseInt(h, 16);
        return new Color((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
    }
}
