import java.awt.geom.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Screen
{
    private int width, height, bgY;
    private ArrayList<GameObject> bgs;
    private ArrayList<GameObject> cars;
    
    public Screen(int w, int h)
    {
        width = w;
        height = h;
        bgs = new ArrayList<GameObject>();
        cars = new ArrayList<GameObject>();
    }
    
    public void displayProblem(String problem, Graphics2D g2)
    {
        Rectangle2D.Double r = new Rectangle2D.Double(400, 50, 600, 100);
        g2.setColor(new Color(211, 211, 211));
        g2.fill(r);
        g2.setFont(new Font("BOLD", 50, 50));
        g2.setColor(Color.BLACK);
        g2.drawString(problem + " = ", 450, 125);
    }
    public void displayAnswer(String answer, Graphics2D g2)
    {
        g2.setFont(new Font("BOLD", 50, 50));
        g2.setColor(Color.BLACK);
        g2.drawString(answer, 800, 125);
    }
    
    public ArrayList<GameObject> getCars()
    {
        return cars;
    }
    public void generateCars()
    {
        if (cars.size() == 0 || cars.get(cars.size() - 1).getY() + cars.get(cars.size() - 1).getHeight() > height)
        {
            for (int i = 0; i < 5; i++)
            {
                cars.add(new GameObject((int)(Math.random() * (width - 300)) + 100, (int)(Math.random() * height) - height, 92, 150, new File("car" + ((int)(Math.random() * 9) + 1) + ".png")));
            }
        }
    }
    public void moveCars(int velocity)
    {
        for (int i = 0; i < cars.size(); i++)
        {
            cars.get(i).moveObject(velocity);
        }
    }
    public void drawCars(Graphics2D g2)
    {
        for (int i = 0; i < cars.size(); i++)
        {
            cars.get(i).drawObject(g2);
        }
    }
    
    public void generateBackground()
    {
        if (bgs.size() == 0)
        {
            bgs.add(new GameObject(0, 0, width, height, new File("background.png")));
        }
        else if (bgs.get(bgs.size() - 1).getY() + bgs.get(bgs.size() - 1).getHeight() > height)
        {
            bgs.add(new GameObject(0, -height + 20, width, height, new File("background.png")));
        }
    }
    public void moveBackground(int velocity)
    {
        for (int i = 0; i < bgs.size(); i++)
        {
            bgs.get(i).moveObject(velocity);
        }
    }
    public void drawBackground(Graphics2D g2)
    {
        for (int i = 0; i < bgs.size(); i++)
        {
            bgs.get(i).drawObject(g2);
        }
    }
    public void drawStartScreen(Graphics2D g2)
    {
        BufferedImage img = null;
        try
        {
            img = ImageIO.read(new File("startscreen.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        g2.drawImage(img, 0, 0, width, 1008, null);
    }
    public void drawLoseScreen(Graphics2D g2, int highscore)
    {
        BufferedImage img = null;
        try
        {
            img = ImageIO.read(new File("losescreen.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        g2.drawImage(img, 0, 0, width, 1008, null);
        g2.setFont(new Font("BOLD", 100, 100));
        g2.drawString("Highscore: " + highscore, 400, 900);
    }
    
    public void restartScene()
    {
        bgs.clear();
        cars.clear();
    }
}