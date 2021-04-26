import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
        this.mas = game.getRoads();
        int wall = mas.size() * mas.get(0).size() + 1;
        for (int i = 0; i < mas.size(); i++) {
            for (int j = 0; j < mas.get(0).size(); j++) {
                if (mas.get(i).get(j) == 0) {
                    mas.get(i).set(j, wall);
                } else {
                    mas.get(i).set(j, 0);
                }
            }
        }
        for (int i = 0; i < mas.size(); i++) {
            for (int j = 0; j < mas.get(0).size(); j++) {
                System.out.print(mas.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    private void findPath(int x, int y) {
        ArrayList<Point> queue = new ArrayList<Point>();
        queue.add(new Point(x, y));
//        mas[x][y] = 1;
//        while (queue.size() > 0) {
//            Point cur = queue.remove(queue.size() - 1);
//            x = cur.x;
//            y = cur.y;
//            if (x < width - 1 && mas[x + 1][y] == 0) {
//                queue.add(new Point(x + 1, y));
//                mas[x + 1][y] = 1;
//            }
//            if (x > 0 && mas[x - 1][y] == 0) {
//                queue.add(new Point(x - 1, y));
//                mas[x - 1][y] = 1;
//            }
//            if (y < height - 1 && mas[x][y + 1] == 0) {
//                queue.add(new Point(x, y + 1));
//                mas[x][y + 1] = 1;
//            }
//            if (y > 0 && mas[x][y - 1] == 0) {
//                queue.add(new Point(x, y - 1));
//                mas[x][y - 1] = 1;
//            }
//        }
    }
}
