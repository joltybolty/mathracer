import java.awt.event.*;
import javax.swing.*;

public class Keys implements KeyListener
{
    private int currKey;
    private boolean start, submit, delete, gameStarted, rPressed;
    private boolean left, right;
    
    public Keys(JFrame f)
    {
        f.addKeyListener(this);
    }
    
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
            left = false;
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
            right = false;
        if (e.getKeyCode() == KeyEvent.VK_R)
            rPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_S)
            gameStarted = false;
    }
    
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_R)
            rPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
            left = true;
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
            right = true;
        if (e.getKeyCode() == KeyEvent.VK_S)
            gameStarted = true;
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            submit = true;
        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
            delete = true;
        else if (e.getKeyCode() >= KeyEvent.VK_0 && e.getKeyCode() <= KeyEvent.VK_9 || e.getKeyCode() == KeyEvent.VK_MINUS)
        {
            start = true;
            currKey = e.getKeyCode();
        }
    }
    
    public boolean getGameStarted()
    {
        return gameStarted;
    }
    
    public void keyTyped(KeyEvent e)
    {
        
    }
    
    public boolean getR()
    {
        return rPressed;
    }
    
    public int getKey()
    {
        return currKey;
    }
    public boolean submitted()
    {
        return submit;
    }
    public void setSubmit()
    {
        submit = false;
    }
    public boolean getStart()
    {
        return start;
    }
    public void setStart()
    {
        start = false;
    }
    public boolean deleted()
    {
        return delete;
    }
    public void setDelete()
    {
        delete = false;
    }
    
    public boolean getLeft()
    {
        return left;
    }
    public boolean getRight()
    {
        return right;
    }
}