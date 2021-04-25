import java.awt.*;

public interface Hero {

    public void move(int direction);

    public void move();

    public void kill(int x, int y);

    public void paint(Graphics g);
}