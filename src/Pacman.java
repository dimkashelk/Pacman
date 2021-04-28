import javax.swing.*;
import javax.xml.crypto.dsig.spec.XPathFilterParameterSpec;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class Pacman implements Hero {
    private Image pacman_right;
    private Image pacman_left;
    private Image pacman_up;
    private Image pacman_down;

    public static int WIDTH;
    public static int HEIGHT;

    private Game game;

    private int direction;

    public int x;
    public int y;

    private int MOVE_X = 5;
    private int MOVE_Y = 5;

    private Vector<Vector<Vector<Vector<Integer>>>> path;
    private Vector<Vector<Integer>> mas;

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
        kill(x, y);
        findPath();
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
        if (x < 0 ||
                x > 40 * (game.getSize().height - 1) ||
                y < 0 ||
                y > 40 * (game.getSize().width - 1) ||
                game.getCell(y / 40, x / 40) == 0) {
            if (direction == Game.DOWN) {
                y -= MOVE_Y;
            } else if (direction == Game.UP) {
                y += MOVE_Y;
            } else if (direction == Game.LEFT) {
                x += MOVE_X;
            } else if (direction == Game.RIGHT) {
                x -= MOVE_X;
            }
            return;
        }
        findPath();
        game.moveGhosts();
        kill(x, y);
    }

    @Override
    public void kill(int x, int y) {
        game.killApple(x, y);
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
        findPath(y / 40, x / 40);
        for (int i = 0; i < mas.size(); i++) {
            for (int j = 0; j < mas.get(0).size(); j++) {
                System.out.print(mas.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public void findPath(int x, int y) {
        ArrayList<Point> queue = new ArrayList<>();
        queue.add(new Point(x, y));
        mas.get(x).set(y, 1);
        while (queue.size() > 0) {
            Point cur = queue.remove(queue.size() - 1);
            x = cur.x;
            y = cur.y;
            if (mas.get(x + 1).get(y) == 0 || mas.get(x).get(y) + 1 < mas.get(x + 1).get(y)) {
                queue.add(new Point(x + 1, y));
                mas.get(x + 1).set(y, mas.get(x).get(y) + 1);
            }
            if (mas.get(x - 1).get(y) == 0 || mas.get(x).get(y) + 1 < mas.get(x - 1).get(y)) {
                queue.add(new Point(x - 1, y));
                mas.get(x - 1).set(y, mas.get(x).get(y) + 1);
            }
            if (mas.get(x).get(y + 1) == 0 || mas.get(x).get(y) + 1 < mas.get(x).get(y + 1)) {
                queue.add(new Point(x, y + 1));
                mas.get(x).set(y + 1, mas.get(x).get(y) + 1);
            }
            if (mas.get(x).get(y - 1) == 0 || mas.get(x).get(y) + 1 < mas.get(x).get(y - 1)) {
                queue.add(new Point(x, y - 1));
                mas.get(x).set(y - 1, mas.get(x).get(y) + 1);
            }
        }
    }

    public Vector<Vector<Integer>> getMas() {
        return mas;
    }
}