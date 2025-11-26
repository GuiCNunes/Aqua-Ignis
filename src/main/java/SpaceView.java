import com.googlecode.lanterna.graphics.TextGraphics;

public class SpaceView {
    private PlayerView playerView = new PlayerView();

    public void draw(Space space, TextGraphics g) {
        playerView.draw(space.getAqua(), g, '~', "#00BFFF");
        playerView.draw(space.getIgnis(), g, '^', "#FFA500");
    }
}
