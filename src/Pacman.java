import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class Pacman implements Hero {
    private final Image pacman_right;
    private final Image pacman_right_run;
    private final Image pacman_left;
    private final Image pacman_left_run;
    private final Image pacman_up;
    private final Image pacman_up_run;
    private final Image pacman_down;
    private final Image pacman_down_run;

    public static int WIDTH;
    public static int HEIGHT;

    private final Game game;

    public int direction;

    public int x;
    public int y;

    private final int MOVE_X;
    private final int MOVE_Y;

    private Vector<Vector<Integer>> mas;

    private static int mode = Game.STOP_GAME;
    public int lifes = 3;

    public Pacman(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;

        ImageIcon pacman_right = new ImageIcon(this.getClass().getResource("Images/pacman_right.png"));
        ImageIcon pacman_right_run = new ImageIcon(this.getClass().getResource("Images/pacman_right_run.png"));
        ImageIcon pacman_left = new ImageIcon(this.getClass().getResource("Images/pacman_left.png"));
        ImageIcon pacman_left_run = new ImageIcon(this.getClass().getResource("Images/pacman_left_run.png"));
        ImageIcon pacman_up = new ImageIcon(this.getClass().getResource("Images/pacman_up.png"));
        ImageIcon pacman_up_run = new ImageIcon(this.getClass().getResource("Images/pacman_up_run.png"));
        ImageIcon pacman_down = new ImageIcon(this.getClass().getResource("Images/pacman_down.png"));
        ImageIcon pacman_down_run = new ImageIcon(this.getClass().getResource("Images/pacman_down_run.png"));

        this.pacman_right = pacman_right.getImage();
        this.pacman_right_run = pacman_right_run.getImage();
        this.pacman_left = pacman_left.getImage();
        this.pacman_left_run = pacman_left_run.getImage();
        this.pacman_up = pacman_up.getImage();
        this.pacman_up_run = pacman_up_run.getImage();
        this.pacman_down = pacman_down.getImage();
        this.pacman_down_run = pacman_down_run.getImage();

        MOVE_X = pacman_right.getIconWidth();
        MOVE_Y = pacman_right.getIconHeight();

        WIDTH = pacman_down.getIconWidth();
        HEIGHT = pacman_down.getIconHeight();

        kill(x, y);
        findPath();
    }

    public Pacman(int x, int y, Game game, int direction) {
        this.x = x;
        this.y = y;
        this.game = game;

        ImageIcon pacman_right = new ImageIcon(this.getClass().getResource("Images/pacman_right.png"));
        ImageIcon pacman_right_run = new ImageIcon(this.getClass().getResource("Images/pacman_right_run.png"));
        ImageIcon pacman_left = new ImageIcon(this.getClass().getResource("Images/pacman_left.png"));
        ImageIcon pacman_left_run = new ImageIcon(this.getClass().getResource("Images/pacman_left_run.png"));
        ImageIcon pacman_up = new ImageIcon(this.getClass().getResource("Images/pacman_up.png"));
        ImageIcon pacman_up_run = new ImageIcon(this.getClass().getResource("Images/pacman_up_run.png"));
        ImageIcon pacman_down = new ImageIcon(this.getClass().getResource("Images/pacman_down.png"));
        ImageIcon pacman_down_run = new ImageIcon(this.getClass().getResource("Images/pacman_down_run.png"));

        this.pacman_right = pacman_right.getImage();
        this.pacman_right_run = pacman_right_run.getImage();
        this.pacman_left = pacman_left.getImage();
        this.pacman_left_run = pacman_left_run.getImage();
        this.pacman_up = pacman_up.getImage();
        this.pacman_up_run = pacman_up_run.getImage();
        this.pacman_down = pacman_down.getImage();
        this.pacman_down_run = pacman_down_run.getImage();

        MOVE_X = pacman_right.getIconWidth();
        MOVE_Y = pacman_right.getIconHeight();

        WIDTH = pacman_down.getIconWidth();
        HEIGHT = pacman_down.getIconHeight();

        kill(x, y);
        findPath();

        this.direction = direction;
    }

    @Override
    public void move(int direction) {
        this.direction = direction;
        move();
    }

    @Override
    public void move() {
        if (mode == Game.END_GAME || mode == Game.STOP_GAME) {
            return;
        }
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
        checkCollisions();
        game.moveGhosts();
        checkCollisions();
        kill(x, y);
    }

    @Override
    public void kill(int x, int y) {
        game.killApple(x, y);
    }

    @Override
    public void paint(Graphics g) {
        if (mode == Game.EAT_GHOSTS) {
            if (direction == Game.DOWN) {
                g.drawImage(pacman_down_run, x, y, null);
            } else if (direction == Game.UP) {
                g.drawImage(pacman_up_run, x, y, null);
            } else if (direction == Game.LEFT) {
                g.drawImage(pacman_left_run, x, y, null);
            } else if (direction == Game.RIGHT) {
                g.drawImage(pacman_right_run, x, y, null);
            }
        } else {
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

    private void findPath() {
        mas = new Vector<>();
        Vector<Vector<Integer>> dop = game.getRoads();
        for (int i = 0; i < dop.size(); i++) {
            Vector<Integer> dop2 = new Vector<>();
            for (int j = 0; j < dop.get(i).size(); j++) {
                if (dop.get(i).get(j) == 0) {
                    dop2.add(-1);
                } else {
                    dop2.add(0);
                }
            }
            mas.add(dop2);
        }
        findPath(y / 40, x / 40);
    }

    public void findPath(int x, int y) {
        ArrayList<MyPoint> queue = new ArrayList<>();
        queue.add(new MyPoint(x, y));
        mas.get(x).set(y, 1);
        while (queue.size() > 0) {
            MyPoint cur = queue.remove(queue.size() - 1);
            x = cur.x;
            y = cur.y;
            if (mas.get(x + 1).get(y) == 0 || mas.get(x).get(y) + 1 < mas.get(x + 1).get(y)) {
                queue.add(new MyPoint(x + 1, y));
                mas.get(x + 1).set(y, mas.get(x).get(y) + 1);
            }
            if (mas.get(x - 1).get(y) == 0 || mas.get(x).get(y) + 1 < mas.get(x - 1).get(y)) {
                queue.add(new MyPoint(x - 1, y));
                mas.get(x - 1).set(y, mas.get(x).get(y) + 1);
            }
            if (mas.get(x).get(y + 1) == 0 || mas.get(x).get(y) + 1 < mas.get(x).get(y + 1)) {
                queue.add(new MyPoint(x, y + 1));
                mas.get(x).set(y + 1, mas.get(x).get(y) + 1);
            }
            if (mas.get(x).get(y - 1) == 0 || mas.get(x).get(y) + 1 < mas.get(x).get(y - 1)) {
                queue.add(new MyPoint(x, y - 1));
                mas.get(x).set(y - 1, mas.get(x).get(y) + 1);
            }
        }
    }

    public Vector<Vector<Integer>> getMas() {
        return mas;
    }

    public void setMode(int status) {
        mode = status;
    }

    public void checkCollisions() {
        Vector<Ghost> toKill = new Vector<>();
        for (Ghost ghost : game.getGhosts()) {
            if (ghost.x == x && ghost.y == y) {
                if (mode == Game.START_GAME) {
                    lifes -= 1;
                    game.beginPosPacman();
                    if (lifes == 0) {
                        game.setMode(Game.END_GAME);
                    }
                } else if (mode == Game.EAT_GHOSTS) {
                    toKill.add(ghost);
                }
            }
        }
        for (Ghost ghost : toKill) {
            game.killGhost(ghost.x, ghost.y);
        }
    }
}