import javax.swing.*;
import java.awt.*;

public class Wall {
    private Game game;
    public int x;
    public int y;

    public int wall_type;

    private Image wall;

    public Wall(Game game, int x, int y, int wall_type) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.wall_type = wall_type;

        ImageIcon wall = new ImageIcon(this.getClass().getResource("Images/wall-red.png"));
        if (wall_type == 1) {
            wall = new ImageIcon(this.getClass().getResource("Images/wall-blue.png"));
        } else if (wall_type == 2) {
            wall = new ImageIcon(this.getClass().getResource("Images/wall-green.png"));
        } else if (wall_type == 3) {
            wall = new ImageIcon(this.getClass().getResource("Images/wall-orange.png"));
        }
        this.wall = wall.getImage();
    }

    public void paint(Graphics g) {
        g.drawImage(wall, x, y, null);
    }
}