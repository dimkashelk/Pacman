import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Game extends JPanel implements Runnable {
    public static final int TIME_DELTA = 10;

    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    public static final int START_GAME = 1;
    public static final int STOP_GAME = 0;
    public static final int END_GAME = -1;
    public static final int EAT_GHOSTS = 2;
    private int steps_eat = 0;
    private static final int MAX_STEPS_EAT = 16;

    private int IS_GAMING = 0;

    private Window wnd;

    private Pacman pacman;
    private Vector<Ghost> ghosts;
    private Vector<Wall> walls;
    private Vector<Apple> apples;
    private Vector<Vector<Integer>> roads;

    private int wall_type;
    private int apple_type;

    private Image end_game = null;
    private Image win_game = null;

    private int score = 0;

    private Font small;
    private Font win;
    private FontMetrics metrics_small;
    private FontMetrics metrics_win;

    public Game(Window wnd) {
        super(true);

        this.wnd = wnd;

        small = new Font("Helvetica", Font.BOLD, 22);
        win = new Font("Impact MT", Font.BOLD, 30);
        metrics_small = this.getFontMetrics(small);
        metrics_win = this.getFontMetrics(win);

        end_game = new ImageIcon(this.getClass().getResource("Images/endgame.jpg")).getImage();
        win_game = new ImageIcon(this.getClass().getResource("Images/wingame.jpg")).getImage();

        walls = new Vector<>();
        apples = new Vector<>();

        roads = new Vector<>();
        for (int i = 0; i < (int) wnd.getHeight() / 40; i++) {
            Vector<Integer> dop = new Vector<>();
            for (int j = 0; j < (int) wnd.getWidth() / 40; j++) {
                dop.add(0);
            }
            roads.add(dop);
        }

        initArea();

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
        ghosts = new Vector<>();
        int ghost_type = 0;
        for (int i = roads.size() - 1; i >= 0; i--) {
            Ghost ghost = null;
            boolean fl = false;
            for (int j = roads.get(0).size() - 1; j >= 0; j--) {
                if (roads.get(i).get(j) == 1) {
                    ghost = new Ghost(this, 40 * j, 40 * i, ghost_type);
                    ghost_type += 1;
                    fl = true;
                    break;
                }
            }
            if (fl) {
                ghosts.add(ghost);
                if (ghosts.size() == 4) {
                    break;
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            Apple apple = apples.get(new Random().nextInt(apples.size()));
            apple.special = 1;
        }

        setVisible(true);
        requestFocus();
        KeyboardListener keyboardListener = new KeyboardListener(wnd);
        addKeyListener(keyboardListener);
    }

    private void initArea() {
        initAreaColumns(0, roads.get(0).size(), 0, roads.size());
        for (int i = 0; i < roads.size(); i++) {
            roads.get(i).set(0, 0);
            roads.get(i).set(roads.get(0).size() - 1, 0);
        }
        for (int i = 0; i < roads.get(0).size(); i++) {
            roads.get(0).set(i, 0);
            roads.get(roads.size() - 1).set(i, 0);
        }
        wall_type = Math.abs((new Random()).nextInt()) % 4;
        apple_type = Math.abs((new Random()).nextInt()) % 4;
        for (int i = 0; i < roads.size(); i++) {
            for (int j = 0; j < roads.get(0).size(); j++) {
                if (roads.get(i).get(j) == 0) {
                    walls.add(new Wall(this, 40 * j, 40 * i, wall_type));
                } else {
                    apples.add(new Apple(this, 40 * j, 40 * i, apple_type));
                }
            }
        }
    }

    private void initAreaColumns(int left, int right, int up, int down) {
        if (left + 3 >= right) {
            return;
        }
        int column = getRandomNumber(left, right);
        for (int i = up; i < down; i++) {
            if (roads.get(i).get(column - 1) != 1 && roads.get(i).get(column + 1) != 1) {
                roads.get(i).set(column, 1);
            }
        }
        initAreaRows(left, column, up, down);
        initAreaRows(column - 1, right, up, down);
    }

    private void initAreaRows(int left, int right, int up, int down) {
        if (up + 3 >= down) {
            return;
        }
        int row = getRandomNumber(up, down);
        for (int i = left; i < right; i++) {
            if (roads.get(row - 1).get(i) != 1 && roads.get(row + 1).get(i) != 1) {
                roads.get(row).set(i, 1);
            }
        }
        initAreaColumns(left, right, up, row);
        initAreaColumns(left, right, row - 1, down);
    }

    private int getRandomNumber(int a, int b) {
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
        if (apples.size() == 0) {
            g.drawImage(win_game, 0, 0, getWidth(), getHeight(), null);
            g.setColor(Color.WHITE);
            g.setFont(win);
            g.drawString("Your score: " + score, (getWidth() - metrics_small.stringWidth("Score: " + score)) / 2 - metrics_small.stringWidth("Score: " + score) / 2, getHeight() / 11 * 10);
        } else if (ghosts.size() == 0) {
            score += 100 * apples.size();
            g.drawImage(win_game, 0, 0, getWidth(), getHeight(), null);
            apples = new Vector<>();
            g.setColor(Color.WHITE);
            g.setFont(win);
            g.drawString("Your score: " + score, (getWidth() - metrics_small.stringWidth("Score: " + score)) / 2 - metrics_small.stringWidth("Score: " + score) / 2, getHeight() / 11 * 10);
        }
        else if (IS_GAMING == Game.END_GAME) {
            g.drawImage(end_game, 0, 0, getWidth(), getHeight(), null);
        } else {
            for (Wall wall : walls) {
                wall.paint(g);
            }
            for (Apple apple : apples) {
                apple.paint(g);
            }

            pacman.paint(g);
            for (Ghost ghost : ghosts) {
                ghost.paint(g);
            }
            g.setColor(Color.BLACK);
            g.setFont(small);
            g.drawString("Score: " + score, getWidth() - metrics_small.stringWidth("Score: " + score) - 15, metrics_small.getHeight());
            g.drawString("Lifes: " + pacman.lifes,  15, metrics_small.getHeight());
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
        if (IS_GAMING == Game.EAT_GHOSTS) {
            steps_eat += 1;
            if (steps_eat == Game.MAX_STEPS_EAT) {
                setMode(Game.START_GAME);
                steps_eat = 0;
            }
        }
        pacman.move(direction);
    }


    public void setMode(int status) {
        this.IS_GAMING = status;
        pacman.setMode(status);
        repaint();
    }

    public int getCell(int x, int y) {
        return roads.get(x).get(y);
    }

    public Dimension getSize() {
        return new Dimension(roads.size(), roads.get(0).size());
    }

    public void killApple(int x, int y) {
        for (int i = 0; i < apples.size(); i++) {
            if (x == apples.get(i).x && y == apples.get(i).y) {
                if (apples.get(i).special == 1) {
                    setMode(Game.EAT_GHOSTS);
                }
                apples.remove(i);
                score += 100;
                break;
            }
        }
    }

    public Vector<Vector<Integer>> getRoads() {
        return roads;
    }

    public void moveGhosts() {
        for (Ghost ghost : ghosts) {
            ghost.move();
        }
    }

    public boolean getGhost(int x, int y) {
        int count = 0;
        for (Ghost ghost : ghosts) {
            if (ghost.x == x && ghost.y == y) {
                count += 1;
            }
        }
        return count >= 2;
    }

    public Vector<Vector<Integer>> getMas() {
        return pacman.getMas();
    }

    public Vector<Ghost> getGhosts() {
        return ghosts;
    }

    public void saveGame() {
        saveMap();
        saveHeroes();
        saveApples();
        wnd.loadMenu();
    }

    private void saveMap() {
        try (FileWriter writer = new FileWriter("./Saves/map.txt", false)) {
            for (Vector<Integer> road : roads) {
                for (int j = 0; j < roads.get(0).size(); j++) {
                    writer.write(road.get(j) + " ");
                }
                writer.write("\n");
            }
            writer.write(wall_type + "\n");
            writer.write(score + "\n");
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void saveHeroes() {
        try (FileWriter writer = new FileWriter("./Saves/heroes.txt", false)) {
            writer.write(pacman.x + " " + pacman.y + " " + pacman.direction + "\n");
            for (Ghost ghost : ghosts) {
                writer.write(ghost.x + " " + ghost.y + " " + ghost.direction + " " + ghost.ghost_type + "\n");
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void saveApples() {
        try (FileWriter writer = new FileWriter("./Saves/apples.txt", false)) {
            for (Apple apple : apples) {
                writer.write(apple.x + " " + apple.y + " " + apple.apple_type + " " + apple.special + "\n");
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void continueGame() {
        loadMap();
        loadHeroes();
        loadApples();
        repaint();
    }

    private void loadMap() {
        try (FileReader reader = new FileReader("./Saves/map.txt")) {
            roads = new Vector<>();

            boolean wall_type_fl = true, score_fl = false;

            Scanner sc = new Scanner(reader);
            while (sc.hasNext()) {
                Scanner dop = new Scanner(sc.nextLine());
                Vector<Integer> d = new Vector<>();
                while (dop.hasNextInt()) {
                    d.add(dop.nextInt());
                }
                if (d.size() > 1) {
                    roads.add(d);
                } else {
                    if (wall_type_fl) {
                        wall_type = d.get(0);
                        wall_type_fl = false;
                        score_fl = true;
                    } else if (score_fl) {
                        score_fl = false;
                        score = d.get(0);
                    }
                }
            }

            walls = new Vector<>();
            for (int i = 0; i < roads.size(); i++) {
                for (int j = 0; j < roads.get(0).size(); j++) {
                    if (roads.get(i).get(j) == 0) {
                        walls.add(new Wall(this, 40 * j, 40 * i, wall_type));
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void loadHeroes() {
        try (FileReader reader = new FileReader("./Saves/heroes.txt")) {
            Scanner sc = new Scanner(reader);
            Scanner dop = new Scanner(sc.nextLine());

            pacman = new Pacman(dop.nextInt(), dop.nextInt(), this, dop.nextInt());

            ghosts = new Vector<>();
            while (sc.hasNext()) {
                dop = new Scanner(sc.nextLine());
                Ghost ghost = new Ghost(this, dop.nextInt(), dop.nextInt(), dop.nextInt(), dop.nextInt());
                ghosts.add(ghost);
            }
            pacman.checkCollisions();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void loadApples() {
        try (FileReader reader = new FileReader("./Saves/apples.txt")) {
            Scanner sc = new Scanner(reader);

            apples = new Vector<>();
            while (sc.hasNext()) {
                Scanner dop = new Scanner(sc.nextLine());
                apples.add(new Apple(this, dop.nextInt(), dop.nextInt(), dop.nextInt(), dop.nextInt()));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void newGame() {
        score = 0;

        walls = new Vector<>();
        apples = new Vector<>();

        roads = new Vector<>();
        for (int i = 0; i < (int) wnd.getHeight() / 40; i++) {
            Vector<Integer> dop = new Vector<>();
            for (int j = 0; j < (int) wnd.getWidth() / 40; j++) {
                dop.add(0);
            }
            roads.add(dop);
        }

        initArea();

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
        ghosts = new Vector<>();
        int ghost_type = 0;
        for (int i = roads.size() - 1; i >= 0; i--) {
            Ghost ghost = null;
            boolean fl = false;
            for (int j = roads.get(0).size() - 1; j >= 0; j--) {
                if (roads.get(i).get(j) == 1) {
                    ghost = new Ghost(this, 40 * j, 40 * i, ghost_type);
                    ghost_type += 1;
                    fl = true;
                    break;
                }
            }
            if (fl) {
                ghosts.add(ghost);
                if (ghosts.size() == 4) {
                    break;
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            Apple apple = apples.get(new Random().nextInt(apples.size()));
            apple.special = 1;
        }
        setMode(STOP_GAME);
    }

    public int getMode() {
        return IS_GAMING;
    }

    public void killGhost(int x, int y) {
        for (int i = 0; i < ghosts.size(); i++) {
            if (x == ghosts.get(i).x && y == ghosts.get(i).y) {
                ghosts.remove(i);
                score += 500;
                break;
            }
        }
    }

    public void beginPosPacman() {
        for (int i = 0; i < roads.size(); i++) {
            for (int j = 0; j < roads.get(0).size(); j++) {
                if (roads.get(i).get(j) == 1) {
                    pacman.x = 40 * j;
                    pacman.y = 40 * i;
                    return;
                }
            }
        }
    }
}