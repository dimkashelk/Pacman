import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            wnd.setMode(Game.START_GAME);
        }
    }
}