package com.Nunes.aqua_and_ignis.tools;

import com.Nunes.aqua_and_ignis.gui.Tile;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TileFontBuilder {
    private static final int SPRITE_SIZE = 16;
    private static final int PIXEL = 100;
    private static final int EM = SPRITE_SIZE * PIXEL;
    private static final int ASCENT = EM + 1;
    private static final String FONT_NAME = "AquaIgnisTiles";

    public static void main(String[] args) throws IOException {
        Path output = Path.of(args[1]);
        Files.createDirectories(output.getParent());
        try (InputStream sprites = Files.newInputStream(Path.of(args[0]));
             OutputStream font = Files.newOutputStream(output)) {
            build(sprites, font);
        }
    }

    public static void build(InputStream spriteFile, OutputStream out) throws IOException {
        Map<Tile, List<String>> sprites = readSprites(spriteFile);

        List<List<int[]>> glyphs = new ArrayList<>();
        glyphs.add(List.of());
        for (Tile tile : Tile.values()) glyphs.add(rectangles(sprites.get(tile)));

        out.write(assemble(tables(glyphs)));
    }

    private static Map<Tile, List<String>> readSprites(InputStream in) throws IOException {
        Map<Tile, List<String>> sprites = new EnumMap<>(Tile.class);
        List<String> rows = null;
        for (String line : new String(in.readAllBytes(), StandardCharsets.UTF_8).split("\\R")) {
            line = line.trim();
            if (line.isEmpty()) continue;
            if (line.startsWith("[")) {
                rows = new ArrayList<>();
                sprites.put(Tile.valueOf(line.substring(1, line.length() - 1)), rows);
            } else {
                rows.add(line);
            }
        }
        for (Tile tile : Tile.values()) checkSprite(tile, sprites.get(tile));
        return sprites;
    }

    private static void checkSprite(Tile tile, List<String> rows) {
        if (rows == null) throw new IllegalArgumentException("Missing sprite for " + tile);
        if (rows.size() != SPRITE_SIZE) throw new IllegalArgumentException(tile + " needs 16 rows");
        for (String row : rows)
            if (!row.matches("[#.]{16}")) throw new IllegalArgumentException(tile + " has a bad row: " + row);
    }

    private static List<int[]> rectangles(List<String> rows) {
        List<int[]> rectangles = new ArrayList<>();
        for (int row = 0; row < SPRITE_SIZE; row++) {
            int y0 = (SPRITE_SIZE - 1 - row) * PIXEL;
            int col = 0;
            while (col < SPRITE_SIZE) {
                if (rows.get(row).charAt(col) != '#') {
                    col++;
                    continue;
                }
                int start = col;
                while (col < SPRITE_SIZE && rows.get(row).charAt(col) == '#') col++;
                rectangles.add(new int[]{start * PIXEL, y0, col * PIXEL, y0 + PIXEL});
            }
        }
        return rectangles;
    }

    private static Map<String, byte[]> tables(List<List<int[]>> glyphs) throws IOException {
        List<byte[]> glyphData = new ArrayList<>();
        for (List<int[]> rectangles : glyphs) glyphData.add(glyph(rectangles));

        Map<String, byte[]> tables = new TreeMap<>();
        tables.put("OS/2", os2());
        tables.put("cmap", cmap());
        tables.put("glyf", glyf(glyphData));
        tables.put("head", head());
        tables.put("hhea", hhea(glyphs.size()));
        tables.put("hmtx", hmtx(glyphs));
        tables.put("loca", loca(glyphData));
        tables.put("maxp", maxp(glyphs));
        tables.put("name", name());
        tables.put("post", post());
        return tables;
    }

    private static byte[] glyph(List<int[]> rectangles) throws IOException {
        if (rectangles.isEmpty()) return new byte[0];

        List<int[]> points = new ArrayList<>();
        for (int[] r : rectangles) {
            points.add(new int[]{r[0], r[1]});
            points.add(new int[]{r[0], r[3]});
            points.add(new int[]{r[2], r[3]});
            points.add(new int[]{r[2], r[1]});
        }

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        data.writeShort(rectangles.size());
        data.writeShort(minX(rectangles));
        data.writeShort(0);
        data.writeShort(maxX(rectangles));
        data.writeShort(EM);
        for (int i = 0; i < rectangles.size(); i++) data.writeShort(i * 4 + 3);
        data.writeShort(0);
        for (int i = 0; i < points.size(); i++) data.writeByte(1);
        int previous = 0;
        for (int[] point : points) {
            data.writeShort(point[0] - previous);
            previous = point[0];
        }
        previous = 0;
        for (int[] point : points) {
            data.writeShort(point[1] - previous);
            previous = point[1];
        }
        return padded(bytes.toByteArray());
    }

    private static int minX(List<int[]> rectangles) {
        return rectangles.stream().mapToInt(r -> r[0]).min().orElse(0);
    }

    private static int maxX(List<int[]> rectangles) {
        return rectangles.stream().mapToInt(r -> r[2]).max().orElse(0);
    }

    private static byte[] glyf(List<byte[]> glyphData) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        for (byte[] glyph : glyphData) bytes.write(glyph);
        return bytes.toByteArray();
    }

    private static byte[] loca(List<byte[]> glyphData) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        int offset = 0;
        for (byte[] glyph : glyphData) {
            data.writeInt(offset);
            offset += glyph.length;
        }
        data.writeInt(offset);
        return bytes.toByteArray();
    }

    private static byte[] head() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        data.writeInt(0x00010000);
        data.writeInt(0x00010000);
        data.writeInt(0);
        data.writeInt(0x5F0F3CF5);
        data.writeShort(1);
        data.writeShort(EM);
        data.writeLong(0);
        data.writeLong(0);
        data.writeShort(0);
        data.writeShort(0);
        data.writeShort(EM);
        data.writeShort(EM);
        data.writeShort(0);
        data.writeShort(8);
        data.writeShort(2);
        data.writeShort(1);
        data.writeShort(0);
        return bytes.toByteArray();
    }

    private static byte[] hhea(int glyphCount) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        data.writeInt(0x00010000);
        data.writeShort(ASCENT);
        data.writeShort(0);
        data.writeShort(0);
        data.writeShort(EM);
        data.writeShort(0);
        data.writeShort(0);
        data.writeShort(EM);
        data.writeShort(1);
        data.writeShort(0);
        data.writeShort(0);
        for (int i = 0; i < 4; i++) data.writeShort(0);
        data.writeShort(0);
        data.writeShort(glyphCount);
        return bytes.toByteArray();
    }

    private static byte[] hmtx(List<List<int[]>> glyphs) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        for (List<int[]> rectangles : glyphs) {
            data.writeShort(EM);
            data.writeShort(minX(rectangles));
        }
        return bytes.toByteArray();
    }

    private static byte[] maxp(List<List<int[]>> glyphs) throws IOException {
        int contours = glyphs.stream().mapToInt(List::size).max().orElse(0);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        data.writeInt(0x00010000);
        data.writeShort(glyphs.size());
        data.writeShort(contours * 4);
        data.writeShort(contours);
        data.writeShort(0);
        data.writeShort(0);
        data.writeShort(2);
        for (int i = 0; i < 8; i++) data.writeShort(0);
        return bytes.toByteArray();
    }

    private static byte[] cmap() throws IOException {
        int first = Tile.values()[0].glyph();
        int last = first + Tile.values().length - 1;

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        data.writeShort(0);
        data.writeShort(1);
        data.writeShort(3);
        data.writeShort(1);
        data.writeInt(12);

        data.writeShort(4);
        data.writeShort(32);
        data.writeShort(0);
        data.writeShort(4);
        data.writeShort(4);
        data.writeShort(1);
        data.writeShort(0);
        data.writeShort(last);
        data.writeShort(0xFFFF);
        data.writeShort(0);
        data.writeShort(first);
        data.writeShort(0xFFFF);
        data.writeShort(1 - first);
        data.writeShort(1);
        data.writeShort(0);
        data.writeShort(0);
        return bytes.toByteArray();
    }

    private static byte[] name() throws IOException {
        String[] strings = {FONT_NAME, "Regular", FONT_NAME, FONT_NAME};
        int[] ids = {1, 2, 4, 6};

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        data.writeShort(0);
        data.writeShort(strings.length);
        data.writeShort(6 + 12 * strings.length);
        int offset = 0;
        for (int i = 0; i < strings.length; i++) {
            int length = strings[i].length() * 2;
            data.writeShort(3);
            data.writeShort(1);
            data.writeShort(0x409);
            data.writeShort(ids[i]);
            data.writeShort(length);
            data.writeShort(offset);
            offset += length;
        }
        for (String string : strings) data.write(string.getBytes(StandardCharsets.UTF_16BE));
        return bytes.toByteArray();
    }

    private static byte[] os2() throws IOException {
        int first = Tile.values()[0].glyph();
        int last = first + Tile.values().length - 1;

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        data.writeShort(4);
        data.writeShort(EM);
        data.writeShort(400);
        data.writeShort(5);
        data.writeShort(0);
        for (int i = 0; i < 10; i++) data.writeShort(0);
        data.writeShort(0);
        data.write(new byte[10]);
        data.writeInt(0);
        data.writeInt(1 << 28);
        data.writeInt(0);
        data.writeInt(0);
        data.write("NONE".getBytes(StandardCharsets.US_ASCII));
        data.writeShort(0x40);
        data.writeShort(first);
        data.writeShort(last);
        data.writeShort(ASCENT);
        data.writeShort(0);
        data.writeShort(0);
        data.writeShort(ASCENT);
        data.writeShort(0);
        data.writeInt(1);
        data.writeInt(0);
        data.writeShort(0);
        data.writeShort(0);
        data.writeShort(0);
        data.writeShort(0);
        data.writeShort(0);
        return bytes.toByteArray();
    }

    private static byte[] post() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        data.writeInt(0x00030000);
        data.writeInt(0);
        data.writeShort(-100);
        data.writeShort(50);
        data.writeInt(1);
        for (int i = 0; i < 4; i++) data.writeInt(0);
        return bytes.toByteArray();
    }

    private static byte[] assemble(Map<String, byte[]> tables) throws IOException {
        int count = tables.size();
        int entrySelector = 31 - Integer.numberOfLeadingZeros(count);
        int searchRange = 16 * (1 << entrySelector);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        data.writeInt(0x00010000);
        data.writeShort(count);
        data.writeShort(searchRange);
        data.writeShort(entrySelector);
        data.writeShort(count * 16 - searchRange);

        int offset = 12 + 16 * count;
        int headOffset = 0;
        for (Map.Entry<String, byte[]> table : tables.entrySet()) {
            byte[] content = padded(table.getValue());
            if (table.getKey().equals("head")) headOffset = offset;
            data.write(table.getKey().getBytes(StandardCharsets.US_ASCII));
            data.writeInt(checksum(content));
            data.writeInt(offset);
            data.writeInt(table.getValue().length);
            offset += content.length;
        }
        for (byte[] table : tables.values()) data.write(padded(table));

        byte[] font = bytes.toByteArray();
        ByteBuffer.wrap(font).putInt(headOffset + 8, 0xB1B0AFBA - checksum(font));
        return font;
    }

    private static byte[] padded(byte[] bytes) {
        return Arrays.copyOf(bytes, (bytes.length + 3) & ~3);
    }

    private static int checksum(byte[] padded) {
        ByteBuffer buffer = ByteBuffer.wrap(padded);
        int sum = 0;
        while (buffer.remaining() >= 4) sum += buffer.getInt();
        return sum;
    }
}
