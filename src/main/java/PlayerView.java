import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;

public class PlayerView {
    public void draw(Player player, TextGraphics g, char character, String color) {
        g.setForegroundColor(TextColor.Factory.fromString(color));
        g.putString(new TerminalPosition(player.getPosition().getX(), player.getPosition().getY()), String.valueOf(character));
    }
}
