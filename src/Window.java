import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private Image background;

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

        JPanel menu = new JPanel();
        menu.setLayout(new BorderLayout());

        JButton new_game = new JButton();
        Image icon_image = new ImageIcon(this.getClass().getResource("Images/new-game-button.png")).getImage().getScaledInstance((int) (getWidth() * 0.302), (int) (getHeight() * 0.083), Image.SCALE_DEFAULT);
        new_game.setIcon(new ImageIcon(icon_image));
        new_game.setLocation((int) (getWidth() * 0.365), (int) (getHeight() * 0.602));
        new_game.setBorder(BorderFactory.createEmptyBorder());
        menu.add(new_game);

//        Game pacman = new Game(this);
//        pacman.setSize(getWidth(), getHeight());
//        pacman.setLocation(0, 0);
//        add(pacman);
//
//        KeyboardListener keyboardListener = new KeyboardListener(pacman);
//        addKeyListener(keyboardListener);
//
//        Thread pacmanThread = new Thread(pacman);
//        pacmanThread.start();

        add(menu, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        Window wnd = new Window();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}