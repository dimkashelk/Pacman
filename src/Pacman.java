import javax.swing.*;
import java.awt.*;

public class Pacman implements Hero {
    private Image pacman_right;
    private Image pacman_left;
    private Image pacman_up;
    private Image pacman_down;

    public static int WIDTH;
    public static int HEIGHT;

    private Game game;

    private int direction;

    private int x;
    private int y;

    private int MOVE_X = 5;
    private int MOVE_Y = 5;

    public Pacman(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
        ImageIcon pacman_right = new ImageIcon(this.getClass().getResource("Images/pacman_right.png"));
        ImageIcon pacman_left = new ImageIcon(this.getClass().getResource("Images/pacman_left.png"));
        ImageIcon pacman_up = new ImageIcon(this.getClass().getResource("Images/pacman_up.png"));
        ImageIcon pacman_down = new ImageIcon(this.getClass().getResource("Images/pacman_down.png"));
        this.pacman_right = pacman_right.getImage();
        this.pacman_left = pacman_left.getImage();
        this.pacman_up = pacman_up.getImage();
        this.pacman_down = pacman_down.getImage();
        MOVE_X = pacman_right.getIconWidth();
        MOVE_Y = pacman_right.getIconHeight();
        WIDTH = pacman_down.getIconWidth();
        HEIGHT = pacman_down.getIconHeight();
    }

    @Override
    public void move(int direction) {
        this.direction = direction;
        move();
    }

    @Override
    public void move() {
        if (direction == Game.DOWN) {
            y += MOVE_Y;
        } else if (direction == Game.UP) {
            y -= MOVE_Y;
        } else if (direction == Game.LEFT) {
            x -= MOVE_X;
        } else if (direction == Game.RIGHT) {
            x += MOVE_X;
        }
    }

    @Override
    public void kill() {

    }

    @Override
    public void paint(Graphics g) {
        if (direction == Game.DOWN) {
            g.drawImage(pacman_down, x, y, null);
        } else if (direction == Game.UP) {
            g.drawImage(pacman_up, x, y, null);
        } else if (direction == Game.LEFT) {
            g.drawImage(pacman_left, x, y, null);
        } else if (direction == Game.RIGHT) {
            g.drawImage(pacman_right, x, y, null);
        }
    }
}