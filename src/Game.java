import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class Game extends JPanel implements Runnable {
    public static final int TIME_DELTA = 10;

    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    public static final int START_GAME = 1;
    public static final int STOP_GAME = 0;

    private int IS_GAMING = 0;

    private Window wnd;

    private Pacman pacman;
    private Vector<Wall> walls;

    public Game(Window wnd) {
        super(true);

        this.wnd = wnd;

        pacman = new Pacman(50, 50, this);

        int wall_type = (new Random()).nextInt() % 4;
        walls = new Vector<>();
        walls.add(new Wall(this, 50, 50, wall_type));

        KeyboardListener keyboardListener = new KeyboardListener(this);
        addKeyListener(keyboardListener);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);

        pacman.paint(g);

        for (Wall wall: walls) {
            wall.paint(g);
        }
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(TIME_DELTA);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move(int direction) {
        pacman.move(direction);
    }


    public void setMode(int status) {
        this.IS_GAMING = status;
        repaint();
    }
}