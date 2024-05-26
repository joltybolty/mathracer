import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class GameObject
{
    private int objX, objY, objWidth, objHeight;
    private BufferedImage objImage;
    private Hitbox objHB;
    
    public GameObject(int x, int y, int w, int h, File f)
    {
        objX = x;
        objY = y;
        objWidth = w;
        objHeight = h;
        objHB = new Hitbox(x, y, w, h);
        try
        {
            objImage = ImageIO.read(f);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void drawObject(Graphics2D g2)
    {
        g2.drawImage(objImage, objX, objY, objWidth, objHeight, null);
    }
    
    public void moveObject(int velocity)
    {
        objY += velocity;
        objHB.updateHitbox(objX, objY, objWidth, objHeight);
    }
    
    public Hitbox getObjHB()
    {
        return objHB;
    }
    
    public int getY()
    {
        return objY;
    }
    public int getX()
    {
        return objX;
    }
    public int getWidth()
    {
        return objWidth;
    }
    public int getHeight()
    {
        return objHeight;
    }
}