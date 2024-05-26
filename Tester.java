import javax.swing.*;
import java.awt.*;

public class Tester
{
    public static void main(String[] args)
    {
        int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        JFrame window = new JFrame();
        Game game = new Game(width, height, window);
        window.add(game);
        window.setSize(width, height);
        window.setTitle("Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}