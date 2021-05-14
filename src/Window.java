import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Window extends JFrame {

    private Image background;

    private Menu menu = null;
    private Thread pacmanThread = null;
    private Game pacman = null;
    private KeyboardListener keyBoardListener = null;

    public Window() throws HeadlessException {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen_size = toolkit.getScreenSize();
        Dimension window_size = new Dimension(
                ((screen_size.width / 2) / 40) * 40 - 24,
                ((screen_size.height / 2) / 40) * 40 - 2
        );
        Point window_location = new Point(
                (screen_size.width - window_size.width) / 2,
                (screen_size.height - window_size.height) / 2
        );
        setTitle("Pacman");
        setLocation(window_location);
        setSize(window_size);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        background = new ImageIcon(this.getClass().getResource("Images/menu.png")).getImage();

        menu = new Menu(this);
        menu.setLocation(0, 0);
        add(menu);

        pacman = new Game(this);
        pacman.setSize(getWidth(), getHeight());
        pacman.setLocation(0, 0);

        keyBoardListener = new KeyboardListener(this);
        addKeyListener(keyBoardListener);

        pacmanThread = new Thread(pacman);
        pacmanThread.start();

        setVisible(true);
    }

    public static void main(String[] args) {
        Window wnd = new Window();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    public void newGame() {
        remove(menu);
        add(pacman);
        pacman.requestFocus();
        pacman.requestFocusInWindow();
        revalidate();
    }

    public void move(int direction) {
        pacman.move(direction);
    }

    public void setMode(int status) {
        pacman.setMode(status);
    }

    public void continueGame() {
        newGame();
    }

    public void saveGame() throws IOException {
        checkDirectory();
        pacman.saveGame();
    }

    private void checkDirectory() {
        File pathSaves = new File("./Saves");
        if (!pathSaves.exists()) {
            pathSaves.mkdir();
        }
    }
}