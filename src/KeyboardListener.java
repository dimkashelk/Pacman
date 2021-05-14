import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class KeyboardListener implements KeyListener {

    private Window wnd;

    public KeyboardListener(Window wnd) {
        this.wnd = wnd;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            wnd.move(Game.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            wnd.move(Game.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            wnd.move(Game.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            wnd.move(Game.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            wnd.setMode(Game.START_GAME);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println("YES");
            try {
                wnd.saveGame();
                System.out.println("YEEEEE");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            wnd.setMode(Game.START_GAME);
        }
    }
}