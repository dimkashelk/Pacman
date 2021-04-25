import javax.swing.*;
import java.awt.*;

public class Apple {
    private Game game;
    public int x;
    public int y;

    private Image wall;

    public Apple(Game game, int x, int y, int apple_type) {
        this.game = game;
        this.x = x;
        this.y = y;
        ImageIcon wall = new ImageIcon(this.getClass().getResource("Images/apple-red.png"));
        if (apple_type == 1) {
            wall = new ImageIcon(this.getClass().getResource("Images/apple-blue.png"));
        } else if (apple_type == 2) {
            wall = new ImageIcon(this.getClass().getResource("Images/apple-green.png"));
        } else if (apple_type == 3) {
            wall = new ImageIcon(this.getClass().getResource("Images/apple-orange.png"));
        }
        this.wall = wall.getImage();
    }

    public void paint(Graphics g) {
        g.drawImage(wall, x, y, null);
    }
}