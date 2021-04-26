import javax.swing.*;
import java.awt.*;

public class Ghost implements Hero {
    private Image ghost_right;
    private Image ghost_left;
    private Image ghost_up;
    private Image ghost_down;

    public static int WIDTH;
    public static int HEIGHT;

    private Game game;

    private int direction;

    private int x;
    private int y;

    private int MOVE_X;
    private int MOVE_Y;

    public Ghost(Game game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;

        ImageIcon ghost_right = new ImageIcon(this.getClass().getResource("Images/ghost-green-right.png"));
        ImageIcon ghost_left = new ImageIcon(this.getClass().getResource("Images/ghost-green-left.png"));
        ImageIcon ghost_up = new ImageIcon(this.getClass().getResource("Images/ghost-green-up.png"));
        ImageIcon ghost_down = new ImageIcon(this.getClass().getResource("Images/ghost-green-down.png"));
        this.ghost_right = ghost_right.getImage();
        this.ghost_left = ghost_left.getImage();
        this.ghost_up = ghost_up.getImage();
        this.ghost_down = ghost_down.getImage();
        MOVE_X = ghost_right.getIconWidth();
        MOVE_Y = ghost_right.getIconHeight();
        WIDTH = ghost_down.getIconWidth();
        HEIGHT = ghost_down.getIconHeight();
    }

    @Override
    public void move(int direction) {

    }

    @Override
    public void move() {

    }

    @Override
    public void kill(int x, int y) {

    }

    @Override
    public void paint(Graphics g) {
        if (direction == Game.DOWN) {
            g.drawImage(ghost_down, x, y, null);
        } else if (direction == Game.UP) {
            g.drawImage(ghost_up, x, y, null);
        } else if (direction == Game.LEFT) {
            g.drawImage(ghost_left, x, y, null);
        } else if (direction == Game.RIGHT) {
            g.drawImage(ghost_right, x, y, null);
        }
    }
}
