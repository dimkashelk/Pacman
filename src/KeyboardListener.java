import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    Game pacman;

    public KeyboardListener(Game pacman) {
        this.pacman = pacman;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pacman.move(Game.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pacman.move(Game.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            pacman.move(Game.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pacman.move(Game.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            pacman.setMode(Game.START_GAME);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            pacman.setMode(Game.START_GAME);
        }
    }
}