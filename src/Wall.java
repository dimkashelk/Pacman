import javax.swing.*;
import java.awt.*;

public class Wall {
    private Game game;
    private int x;
    private int y;

    private Image wall;

    public Wall(Game game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;
        ImageIcon wall = new ImageIcon(this.getClass().getResource("Images/wall.png"));
        this.wall = wall.getImage();
    }

    public void paint(Graphics g) {
        g.drawImage(wall, x, y, null);
    }
}