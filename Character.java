import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Character
{
    private int playerX, defaultX, playerY, vX, vY, difference, playerWidth, playerHeight;
    private BufferedImage img;
    private Hitbox playerHB;
    
    public Character(int x, int y, int w, int h, File f)
    {
        vY = -3;
        vX = 0;
        playerX = x;
        defaultX = playerX;
        difference = 5;
        playerY = y;
        playerWidth = w;
        playerHeight = h;
        playerHB = new Hitbox(x, y, w, h);
        try
        {
            img = ImageIO.read(f);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void movePlayer(boolean leftPressed, boolean rightPressed)
    {
        playerX += vX;
        
        if (leftPressed)
            vX = -difference;
        else if (rightPressed)
            vX = difference;
        else
            vX = 0;
        
        playerHB.updateHitbox(playerX, playerY, playerWidth, playerHeight);
    }
    
    public void drawPlayer(Graphics2D g2)
    {
        g2.drawImage(img, playerX, playerY, playerWidth, playerHeight, null);
    }
    
    public void speedUp()
    {
        vY -= 3;
        difference++;
    }
    public void slowDown()
    {
        vY++;
    }

    public int getPlayerSpeed()
    {
        return vY;
    }
    
    public Hitbox getPlayerHB()
    {
        return playerHB;
    }
    
    public void restartCharacter()
    {
        vX = 0;
        vY = -3;
        playerX = defaultX;
    }
}