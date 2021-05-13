import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel implements Runnable {

    private Window wnd;
    private Image background;

    public Menu(Window wnd) {
        super(true);

        this.wnd = wnd;

        background = new ImageIcon(this.getClass().getResource("Images/menu.png")).getImage();
        setLayout(null);
        setSize(wnd.getWidth(), wnd.getHeight());

        JButton new_game = new JButton();
        Image icon_image = new ImageIcon(this.getClass().getResource("Images/new-game-button.png")).getImage().getScaledInstance((int) (wnd.getWidth() * 0.302), (int) (wnd.getHeight() * 0.083), Image.SCALE_DEFAULT);
        new_game.setText(null);
        new_game.setIcon(new ImageIcon(icon_image));
        new_game.setBounds((int) (wnd.getWidth() * 0.365), (int) (wnd.getHeight() * 0.602), icon_image.getWidth(null), icon_image.getHeight(null));
        new_game.setLocation((int) (wnd.getWidth() * 0.365), (int) (wnd.getHeight() * 0.602));
        new_game.setBorder(BorderFactory.createEmptyBorder());
        new_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wnd.newGame();
            }
        });
        add(new_game);
    }

    @Override
    public void run() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(background, 0, 0, wnd.getWidth(), wnd.getHeight(), null);
    }
}
