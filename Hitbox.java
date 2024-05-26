import java.awt.*;
import java.awt.geom.*;

public class Hitbox
{
    private int left, right, top, bottom;
    public Hitbox(int x, int y, int width, int height)
    {
        left = x + 2 ;
        right = x + width - 2;
        top = y + 2;
        bottom = y + height - 2;
    }
    
    public void drawHitbox(Graphics2D g2)
    {
        Rectangle2D.Double r = new Rectangle2D.Double(left, top, right - left, bottom - top);
        g2.setColor(Color.WHITE);
        g2.fill(r);
    }
    
    public boolean touching(Hitbox enemy)
    {
        if (left <= enemy.getRight() && right >= enemy.getLeft() && top <= enemy.getBottom() && bottom >= enemy.getTop())
            return true;
        return false;
    }
    
    public void updateHitbox(int x, int y, int width, int height)
    {
        left = x + 2 ;
        right = x + width - 2;
        top = y + 2;
        bottom = y + height - 2;
    }
    
    public int getTop()
    {
        return top;
    }
    public int getBottom()
    {
        return bottom;
    }
    public int getLeft()
    {
        return left;
    }
    public int getRight()
    {
        return right;
    }
}