import javax.swing.*;
import java.awt.*;

public class Apple {
    private Game game;
    public int x;
    public int y;

    private Image apple;
    private Image apple_special;

    public int apple_type;
    public int special = 0;

    public Apple(Game game, int x, int y, int apple_type) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.apple_type = apple_type;

        ImageIcon apple = new ImageIcon(this.getClass().getResource("Images/apple-red.png"));
        ImageIcon apple_special = new ImageIcon(this.getClass().getResource("Images/apple-red-special.png"));
        if (apple_type == 1) {
            apple = new ImageIcon(this.getClass().getResource("Images/apple-blue.png"));
            apple_special = new ImageIcon(this.getClass().getResource("Images/apple-blue-special.png"));
        } else if (apple_type == 2) {
            apple = new ImageIcon(this.getClass().getResource("Images/apple-green.png"));
            apple_special = new ImageIcon(this.getClass().getResource("Images/apple-green-special.png"));
        } else if (apple_type == 3) {
            apple = new ImageIcon(this.getClass().getResource("Images/apple-orange.png"));
            apple_special = new ImageIcon(this.getClass().getResource("Images/apple-orange-special.png"));
        }
        this.apple = apple.getImage();
        this.apple_special = apple_special.getImage();
    }

    public Apple(Game game, int x, int y, int apple_type, int special) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.apple_type = apple_type;

        ImageIcon apple = new ImageIcon(this.getClass().getResource("Images/apple-red.png"));
        ImageIcon apple_special = new ImageIcon(this.getClass().getResource("Images/apple-red-special.png"));
        if (apple_type == 1) {
            apple = new ImageIcon(this.getClass().getResource("Images/apple-blue.png"));
            apple_special = new ImageIcon(this.getClass().getResource("Images/apple-blue-special.png"));
        } else if (apple_type == 2) {
            apple = new ImageIcon(this.getClass().getResource("Images/apple-green.png"));
            apple_special = new ImageIcon(this.getClass().getResource("Images/apple-green-special.png"));
        } else if (apple_type == 3) {
            apple = new ImageIcon(this.getClass().getResource("Images/apple-orange.png"));
            apple_special = new ImageIcon(this.getClass().getResource("Images/apple-orange-special.png"));
        }
        this.apple = apple.getImage();
        this.apple_special = apple_special.getImage();
        this.special = special;
    }

    public void paint(Graphics g) {
        if (special == 1) {
            g.drawImage(apple_special, x, y, null);
        } else {
            g.drawImage(apple, x, y, null);
        }
    }
}