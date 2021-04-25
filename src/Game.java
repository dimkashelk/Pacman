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
    private Vector<Vector<Integer>> roads;

    public Game(Window wnd) {
        super(true);

        this.wnd = wnd;

        walls = new Vector<>();

        Vector<Vector<Integer>> roads = new Vector<>();
        for (int i = 0; i < (int) wnd.getHeight() / 40; i++) {
            Vector<Integer> dop = new Vector<>();
            for (int j = 0; j < (int) wnd.getWidth() / 40; j++) {
                dop.add(0);
            }
            roads.add(dop);
        }

        initArea(roads);

        this.roads = roads;

        for (int i = 0; i < roads.size(); i++) {
            boolean fl = false;
            for (int j = 0; j < roads.get(0).size(); j++) {
                if (roads.get(i).get(j) == 1) {
                    pacman = new Pacman(40 * j, 40 * i, this);
                    fl = true;
                    break;
                }
            }
            if (fl) {
                break;
            }
        }

        KeyboardListener keyboardListener = new KeyboardListener(this);
        addKeyListener(keyboardListener);
    }

    private void initArea(Vector<Vector<Integer>> roads) {
        Vector<Integer> columns = new Vector<>();
        initAreaColumns(roads, 0, roads.get(0).size(), columns);
        for (Integer column : columns) {
            if (roads.get(1).get(column - 1) != 1 && roads.get(1).get(column + 1) != 1) {
                for (int i = 1; i < roads.size() - 1; i++) {
                    roads.get(i).set(column, 1);
                }
            }
        }
        Vector<Integer> rows = new Vector<>();
        initAreaRows(roads, 0, roads.size(), rows);
        for (Integer row : rows) {
            if (roads.get(row - 1).get(0) != 1 && roads.get(row + 1).get(0) != 1) {
                for (int i = 0; i < roads.get(0).size(); i++) {
                    roads.get(row).set(i, 1);
                }
            }
        }
        for (int i = 0; i < roads.size(); i++) {
            roads.get(i).set(0, 0);
            roads.get(i).set(roads.get(0).size() - 1, 0);
        }
        int wall_type = (new Random()).nextInt() % 4;
        for (int i = 0; i < roads.size(); i++) {
            for (int j = 0; j < roads.get(0).size(); j++) {
                if (roads.get(i).get(j) == 0) {
                    walls.add(new Wall(this, 40 * j, 40 * i, wall_type));
                }
            }
        }
    }

    private void initAreaColumns(Vector<Vector<Integer>> roads, int start, int end, Vector<Integer> col) {
        if (start + 3 >= end) {
            return;
        }
        int column = getRandomNumberWithoutBorders(start, end);
        col.add(column);
        initAreaColumns(roads, start, column, col);
        initAreaColumns(roads, column, end, col);
    }

    private void initAreaRows(Vector<Vector<Integer>> roads, int start, int end, Vector<Integer> rows) {
        if (start + 3 >= end) {
            return;
        }
        int row = getRandomNumberWithoutBorders(start, end);
        rows.add(row);
        initAreaRows(roads, start, row, rows);
        initAreaRows(roads, row, end, rows);
    }

    private int getRandomNumberWithoutBorders(int a, int b) {
        int dop = a + (int) (Math.random() * (b - a - 2));
        if (dop == a)
            return a + 2;
        return dop;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);

        pacman.paint(g);

        for (Wall wall : walls) {
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

    public int getCell(int x, int y) {
        return roads.get(x).get(y);
    }

    public Dimension getSize() {
        return new Dimension(roads.size(), roads.get(0).size());
    }
}