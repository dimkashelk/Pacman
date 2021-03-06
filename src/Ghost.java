import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Ghost implements Hero {
    private final Image ghost_right;
    private final Image ghost_right_run;
    private final Image ghost_left;
    private final Image ghost_left_run;
    private final Image ghost_up;
    private final Image ghost_up_run;
    private final Image ghost_down;
    private final Image ghost_down_run;

    public static int WIDTH;
    public static int HEIGHT;

    private final Game game;

    public int direction;
    public int ghost_type;

    public int x;
    public int y;

    private final int MOVE_X;
    private final int MOVE_Y;

    public Ghost(Game game, int x, int y, int ghost_type) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.ghost_type = ghost_type;

        ImageIcon ghost_right = new ImageIcon(this.getClass().getResource("Images/ghost-green-right.png"));
        ImageIcon ghost_right_run = new ImageIcon(this.getClass().getResource("Images/ghost-green-right-run.png"));
        ImageIcon ghost_left = new ImageIcon(this.getClass().getResource("Images/ghost-green-left.png"));
        ImageIcon ghost_left_run = new ImageIcon(this.getClass().getResource("Images/ghost-green-left-run.png"));
        ImageIcon ghost_up = new ImageIcon(this.getClass().getResource("Images/ghost-green-up.png"));
        ImageIcon ghost_up_run = new ImageIcon(this.getClass().getResource("Images/ghost-green-up-run.png"));
        ImageIcon ghost_down = new ImageIcon(this.getClass().getResource("Images/ghost-green-down.png"));
        ImageIcon ghost_down_run = new ImageIcon(this.getClass().getResource("Images/ghost-green-down-run.png"));
        if (ghost_type == 1) {
            ghost_right = new ImageIcon(this.getClass().getResource("Images/ghost-blue-right.png"));
            ghost_right_run = new ImageIcon(this.getClass().getResource("Images/ghost-blue-right-run.png"));
            ghost_left = new ImageIcon(this.getClass().getResource("Images/ghost-blue-left.png"));
            ghost_left_run = new ImageIcon(this.getClass().getResource("Images/ghost-blue-left-run.png"));
            ghost_up = new ImageIcon(this.getClass().getResource("Images/ghost-blue-up.png"));
            ghost_up_run = new ImageIcon(this.getClass().getResource("Images/ghost-blue-up-run.png"));
            ghost_down = new ImageIcon(this.getClass().getResource("Images/ghost-blue-down.png"));
            ghost_down_run = new ImageIcon(this.getClass().getResource("Images/ghost-blue-down-run.png"));
        } else if (ghost_type == 2) {
            ghost_right = new ImageIcon(this.getClass().getResource("Images/ghost-red-right.png"));
            ghost_right_run = new ImageIcon(this.getClass().getResource("Images/ghost-red-right-run.png"));
            ghost_left = new ImageIcon(this.getClass().getResource("Images/ghost-red-left.png"));
            ghost_left_run = new ImageIcon(this.getClass().getResource("Images/ghost-red-left-run.png"));
            ghost_up = new ImageIcon(this.getClass().getResource("Images/ghost-red-up.png"));
            ghost_up_run = new ImageIcon(this.getClass().getResource("Images/ghost-red-up-run.png"));
            ghost_down = new ImageIcon(this.getClass().getResource("Images/ghost-red-down.png"));
            ghost_down_run = new ImageIcon(this.getClass().getResource("Images/ghost-red-down-run.png"));
        } else if (ghost_type == 3) {
            ghost_right = new ImageIcon(this.getClass().getResource("Images/ghost-orange-right.png"));
            ghost_right_run = new ImageIcon(this.getClass().getResource("Images/ghost-orange-right-run.png"));
            ghost_left = new ImageIcon(this.getClass().getResource("Images/ghost-orange-left.png"));
            ghost_left_run = new ImageIcon(this.getClass().getResource("Images/ghost-orange-left-run.png"));
            ghost_up = new ImageIcon(this.getClass().getResource("Images/ghost-orange-up.png"));
            ghost_up_run = new ImageIcon(this.getClass().getResource("Images/ghost-orange-up-run.png"));
            ghost_down = new ImageIcon(this.getClass().getResource("Images/ghost-orange-down.png"));
            ghost_down_run = new ImageIcon(this.getClass().getResource("Images/ghost-orange-down-run.png"));
        }
        this.ghost_right = ghost_right.getImage();
        this.ghost_right_run = ghost_right_run.getImage();
        this.ghost_left = ghost_left.getImage();
        this.ghost_left_run = ghost_left_run.getImage();
        this.ghost_up = ghost_up.getImage();
        this.ghost_up_run = ghost_up_run.getImage();
        this.ghost_down = ghost_down.getImage();
        this.ghost_down_run = ghost_down_run.getImage();
        MOVE_X = ghost_right.getIconWidth();
        MOVE_Y = ghost_right.getIconHeight();
        WIDTH = ghost_down.getIconWidth();
        HEIGHT = ghost_down.getIconHeight();
    }

    public Ghost(Game game, int x, int y, int direction, int ghost_type) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.ghost_type = ghost_type;
        this.direction = direction;

        ImageIcon ghost_right = new ImageIcon(this.getClass().getResource("Images/ghost-green-right.png"));
        ImageIcon ghost_right_run = new ImageIcon(this.getClass().getResource("Images/ghost-green-right-run.png"));
        ImageIcon ghost_left = new ImageIcon(this.getClass().getResource("Images/ghost-green-left.png"));
        ImageIcon ghost_left_run = new ImageIcon(this.getClass().getResource("Images/ghost-green-left-run.png"));
        ImageIcon ghost_up = new ImageIcon(this.getClass().getResource("Images/ghost-green-up.png"));
        ImageIcon ghost_up_run = new ImageIcon(this.getClass().getResource("Images/ghost-green-up-run.png"));
        ImageIcon ghost_down = new ImageIcon(this.getClass().getResource("Images/ghost-green-down.png"));
        ImageIcon ghost_down_run = new ImageIcon(this.getClass().getResource("Images/ghost-green-down-run.png"));
        if (ghost_type == 1) {
            ghost_right = new ImageIcon(this.getClass().getResource("Images/ghost-blue-right.png"));
            ghost_right_run = new ImageIcon(this.getClass().getResource("Images/ghost-blue-right-run.png"));
            ghost_left = new ImageIcon(this.getClass().getResource("Images/ghost-blue-left.png"));
            ghost_left_run = new ImageIcon(this.getClass().getResource("Images/ghost-blue-left-run.png"));
            ghost_up = new ImageIcon(this.getClass().getResource("Images/ghost-blue-up.png"));
            ghost_up_run = new ImageIcon(this.getClass().getResource("Images/ghost-blue-up-run.png"));
            ghost_down = new ImageIcon(this.getClass().getResource("Images/ghost-blue-down.png"));
            ghost_down_run = new ImageIcon(this.getClass().getResource("Images/ghost-blue-down-run.png"));
        } else if (ghost_type == 2) {
            ghost_right = new ImageIcon(this.getClass().getResource("Images/ghost-red-right.png"));
            ghost_right_run = new ImageIcon(this.getClass().getResource("Images/ghost-red-right-run.png"));
            ghost_left = new ImageIcon(this.getClass().getResource("Images/ghost-red-left.png"));
            ghost_left_run = new ImageIcon(this.getClass().getResource("Images/ghost-red-left-run.png"));
            ghost_up = new ImageIcon(this.getClass().getResource("Images/ghost-red-up.png"));
            ghost_up_run = new ImageIcon(this.getClass().getResource("Images/ghost-red-up-run.png"));
            ghost_down = new ImageIcon(this.getClass().getResource("Images/ghost-red-down.png"));
            ghost_down_run = new ImageIcon(this.getClass().getResource("Images/ghost-red-down-run.png"));
        } else if (ghost_type == 3) {
            ghost_right = new ImageIcon(this.getClass().getResource("Images/ghost-orange-right.png"));
            ghost_right_run = new ImageIcon(this.getClass().getResource("Images/ghost-orange-right-run.png"));
            ghost_left = new ImageIcon(this.getClass().getResource("Images/ghost-orange-left.png"));
            ghost_left_run = new ImageIcon(this.getClass().getResource("Images/ghost-orange-left-run.png"));
            ghost_up = new ImageIcon(this.getClass().getResource("Images/ghost-orange-up.png"));
            ghost_up_run = new ImageIcon(this.getClass().getResource("Images/ghost-orange-up-run.png"));
            ghost_down = new ImageIcon(this.getClass().getResource("Images/ghost-orange-down.png"));
            ghost_down_run = new ImageIcon(this.getClass().getResource("Images/ghost-orange-down-run.png"));
        }
        this.ghost_right = ghost_right.getImage();
        this.ghost_right_run = ghost_right_run.getImage();
        this.ghost_left = ghost_left.getImage();
        this.ghost_left_run = ghost_left_run.getImage();
        this.ghost_up = ghost_up.getImage();
        this.ghost_up_run = ghost_up_run.getImage();
        this.ghost_down = ghost_down.getImage();
        this.ghost_down_run = ghost_down_run.getImage();
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
        Vector<Vector<Integer>> mas = game.getMas();
        int step_x = 0, step_y = 0;
        if (game.getMode() == Game.EAT_GHOSTS) {
            if (mas.get(y / 40).get(x / 40 - 1) == mas.get(y / 40).get(x / 40) + 1) {
                x -= MOVE_X;
                step_x = -MOVE_X;
                direction = Game.LEFT;
            } else if (mas.get(y / 40).get(x / 40 + 1) == mas.get(y / 40).get(x / 40) + 1) {
                x += MOVE_X;
                step_x = MOVE_X;
                direction = Game.RIGHT;
            } else if (mas.get(y / 40 - 1).get(x / 40) == mas.get(y / 40).get(x / 40) + 1) {
                y -= MOVE_Y;
                step_y = -MOVE_Y;
                direction = Game.UP;
            } else if (mas.get(y / 40 + 1).get(x / 40) == mas.get(y / 40).get(x / 40) + 1) {
                y += MOVE_Y;
                step_y = MOVE_Y;
                direction = Game.DOWN;
            } else if (mas.get(y / 40).get(x / 40 - 1) == mas.get(y / 40).get(x / 40)) {
                x -= MOVE_X;
                step_x = -MOVE_X;
                direction = Game.LEFT;
            } else if (mas.get(y / 40).get(x / 40 + 1) == mas.get(y / 40).get(x / 40)) {
                x += MOVE_X;
                step_x = MOVE_X;
                direction = Game.RIGHT;
            } else if (mas.get(y / 40 - 1).get(x / 40) == mas.get(y / 40).get(x / 40)) {
                y -= MOVE_Y;
                step_y = -MOVE_Y;
                direction = Game.UP;
            } else if (mas.get(y / 40 + 1).get(x / 40) == mas.get(y / 40).get(x / 40)) {
                y += MOVE_Y;
                step_y = MOVE_Y;
                direction = Game.DOWN;
            }
            if (game.getGhost(x, y)) {
                x -= step_x;
                y -= step_y;
            }
        } else {
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
            } else if (mas.get(y / 40).get(x / 40 - 1) == mas.get(y / 40).get(x / 40)) {
                x -= MOVE_X;
                step_x = -MOVE_X;
                direction = Game.LEFT;
            } else if (mas.get(y / 40).get(x / 40 + 1) == mas.get(y / 40).get(x / 40)) {
                x += MOVE_X;
                step_x = MOVE_X;
                direction = Game.RIGHT;
            } else if (mas.get(y / 40 - 1).get(x / 40) == mas.get(y / 40).get(x / 40)) {
                y -= MOVE_Y;
                step_y = -MOVE_Y;
                direction = Game.UP;
            } else if (mas.get(y / 40 + 1).get(x / 40) == mas.get(y / 40).get(x / 40)) {
                y += MOVE_Y;
                step_y = MOVE_Y;
                direction = Game.DOWN;
            }
            if (game.getGhost(x, y)) {
                x -= step_x;
                y -= step_y;
            }
        }
    }

    @Override
    public void kill(int x, int y) {

    }

    @Override
    public void paint(Graphics g) {
        if (game.getMode() == Game.EAT_GHOSTS) {
            if (direction == Game.DOWN) {
                g.drawImage(ghost_down_run, x, y, null);
            } else if (direction == Game.UP) {
                g.drawImage(ghost_up_run, x, y, null);
            } else if (direction == Game.LEFT) {
                g.drawImage(ghost_left_run, x, y, null);
            } else if (direction == Game.RIGHT) {
                g.drawImage(ghost_right_run, x, y, null);
            }
        } else {
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
}
