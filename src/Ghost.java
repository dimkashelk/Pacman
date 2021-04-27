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

    private int x;
    private int y;

    private int MOVE_X;
    private int MOVE_Y;

    private Vector<Vector<Integer>> mas;

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

        findPath();
    }

    @Override
    public void move(int direction) {

    }

    @Override
    public void move() {
        if (mas.get(y / 40).get(x / 40 - 1) == mas.get(y / 40).get(x / 40) - 1) {
            x -= MOVE_X;
        } else if (mas.get(y / 40).get(x / 40 + 1) == mas.get(y / 40).get(x / 40) - 1) {
            x += MOVE_X;
        } else if (mas.get(y / 40 - 1).get(x / 40) == mas.get(y / 40).get(x / 40) - 1) {
            y -= MOVE_Y;
        } else if (mas.get(y / 40 + 1).get(x / 40) == mas.get(y / 40).get(x / 40) - 1) {
            y += MOVE_Y;
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

    private void findPath() {
        mas = new Vector<>();
        Vector<Vector<Integer>> dop = game.getRoads();
        for (int i = 0; i < dop.size(); i++) {
            Vector<Integer> dop2 = new Vector<>();
            for (int j = 0; j < dop.get(0).size(); j++) {
                if (dop.get(i).get(j) == 0) {
                    dop2.add(-1);
                } else {
                    dop2.add(0);
                }
            }
            mas.add(dop2);
        }
        findPath(game.getPacmanCoords().y / 40, game.getPacmanCoords().x / 40, 1);
        for (int i = 0; i < mas.size(); i++) {
            for (int j = 0; j < mas.get(0).size(); j++) {
                System.out.print(mas.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    private void findPath(int y, int x, int d) {
        mas.get(y).set(x, d);
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j < x + 1; j++) {
                if (x < mas.get(0).size() - 1 && mas.get(y).get(x + 1) == 0) {
                    findPath(y, x + 1, d + 1);
                }
                if (y > 0 && mas.get(y - 1).get(x) == 0) {
                    findPath(y - 1, x, d + 1);
                }
                if (y < mas.size() && mas.get(y + 1).get(x) == 0) {
                    findPath(y + 1, x, d + 1);
                }
                if (x > 0 && mas.get(y).get(x - 1) == 0) {
                    findPath(y, x - 1, d + 1);
                }
            }
        }
    }
}