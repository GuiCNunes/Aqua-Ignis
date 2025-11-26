import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


public class Space {
    private int width;
    private int height;

    private Player ignis;
    private Player aqua;

    public Space(int width, int height) {
        this.width = width;
        this.height = height;

        ignis = new Ignis(11,10);
        aqua = new Aqua(10,10);
    }
    public void draw(TextGraphics graphics) {
        aqua.draw(graphics);
        ignis.draw(graphics);
    }

    public void processKey(KeyStroke key) {
        // ignis setas
        if (key.getKeyType() == KeyType.ArrowUp) movePlayer(ignis, ignis.moveUp());
        if (key.getKeyType() == KeyType.ArrowDown) movePlayer(ignis, ignis.moveDown());
        if (key.getKeyType() == KeyType.ArrowLeft) movePlayer(ignis, ignis.moveLeft());
        if (key.getKeyType() == KeyType.ArrowRight) movePlayer(ignis, ignis.moveRight());

        // aqua wasd
        if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'w' || key.getCharacter() == 'W'))
            movePlayer(aqua, aqua.moveUp());
        if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 's' || key.getCharacter() == 'S'))
            movePlayer(aqua, aqua.moveDown());
        if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'a' || key.getCharacter() == 'A'))
            movePlayer(aqua, aqua.moveLeft());
        if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'd' || key.getCharacter() == 'D'))
            movePlayer(aqua, aqua.moveRight());
    }

    private void movePlayer(Player player, Position newPos) {
        if (canMove(newPos)) {
            player.setPosition(newPos);
        }
    }

    private boolean canMove(Position p) {
        return p.getX() >= 0 && p.getX() < width &&
                p.getY() >= 0 && p.getY() < height;
    }
}
