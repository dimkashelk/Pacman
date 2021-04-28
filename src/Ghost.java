import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Ghost implements Hero {
    private Image ghost_right;
    private Image ghost_left;
    private Image ghost_up;
    private Image ghost_down;

    public static int WIDTH;
    public static int HEIGHT;

    private Game game;

    private int direction;

    public int x;
    public int y;

    private int MOVE_X;
    private int MOVE_Y;

    private Vector<Vector<Integer>> mas;

    public Ghost(Game game, int x, int y, int ghost_type) {
        this.game = game;
        this.x = x;
        this.y = y;

        ImageIcon ghost_right = new ImageIcon(this.getClass().getResource("Images/ghost-green-right.png"));
        ImageIcon ghost_left = new ImageIcon(this.getClass().getResource("Images/ghost-green-left.png"));
        ImageIcon ghost_up = new ImageIcon(this.getClass().getResource("Images/ghost-green-up.png"));
        ImageIcon ghost_down = new ImageIcon(this.getClass().getResource("Images/ghost-green-down.png"));
        if (ghost_type == 1) {
            ghost_right = new ImageIcon(this.getClass().getResource("Images/ghost-blue-right.png"));
            ghost_left = new ImageIcon(this.getClass().getResource("Images/ghost-blue-left.png"));
            ghost_up = new ImageIcon(this.getClass().getResource("Images/ghost-blue-up.png"));
            ghost_down = new ImageIcon(this.getClass().getResource("Images/ghost-blue-down.png"));
        } else if (ghost_type == 2) {
            ghost_right = new ImageIcon(this.getClass().getResource("Images/ghost-red-right.png"));
            ghost_left = new ImageIcon(this.getClass().getResource("Images/ghost-red-left.png"));
            ghost_up = new ImageIcon(this.getClass().getResource("Images/ghost-red-up.png"));
            ghost_down = new ImageIcon(this.getClass().getResource("Images/ghost-red-down.png"));
        } else if (ghost_type == 3) {
            ghost_right = new ImageIcon(this.getClass().getResource("Images/ghost-orange-right.png"));
            ghost_left = new ImageIcon(this.getClass().getResource("Images/ghost-orange-left.png"));
            ghost_up = new ImageIcon(this.getClass().getResource("Images/ghost-orange-up.png"));
            ghost_down = new ImageIcon(this.getClass().getResource("Images/ghost-orange-down.png"));
        }
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
        mas = game.getMas();
        int step_x = 0, step_y = 0;
        if (mas.get(y / 40).get(x / 40 - 1) == mas.get(y / 40).get(x / 40) - 1) {
            x -= MOVE_X;
            step_x = -MOVE_X;
            direction = Game.LEFT;
        } else if (mas.get(y / 40).get(x / 40 + 1) == mas.get(y / 40).get(x / 40) - 1) {
            x += MOVE_X;
            step_x = MOVE_X;
            direction = Game.RIGHT;
        } else if (mas.get(y / 40 - 1).get(x / 40) == mas.get(y / 40).get(x / 40) - 1) {
            y -= MOVE_Y;
            step_y = -MOVE_Y;
            direction = Game.UP;
        } else if (mas.get(y / 40 + 1).get(x / 40) == mas.get(y / 40).get(x / 40) - 1) {
            y += MOVE_Y;
            step_y = MOVE_Y;
            direction = Game.DOWN;
        }
        if (game.getGhost(x, y)) {
            x -= step_x;
            y -= step_y;
        }
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
