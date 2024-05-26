import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class Game extends JPanel implements Runnable
{
    private int width, height, difficulty, correct, score, highscore;
    private boolean generated, gameStarted, gameLost, gameRestarted;
    private String problem;
    private Thread thread;
    private Screen screen;
    private Keys keyListener;
    private Character player;
    private ArrayList<String> letters;
    private Mathers math;
    
    public Game(int w, int h, JFrame f)
    {
        width = w;
        height = h;
        difficulty = 1;
        highscore = 0;
        score = 0;
        problem = "";
        player = new Character(width / 2, height / 4, 90, 147, new File("car0.png"));
        screen = new Screen(width, height);
        letters =  new ArrayList<String>();
        math = new Mathers();
        keyListener = new Keys(f);
        thread = new Thread(this);
        thread.start();
    }
    
    public void run()
    {
        while(true)
        {
            update();
            try
            {
                thread.sleep(17);
            }
            catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }
            repaint();
        }
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        if (gameStarted && !gameLost)
        {
            String answer = "";
            screen.drawBackground(g2);
            player.drawPlayer(g2);
            screen.drawCars(g2);
            screen.displayProblem(problem, g2);
            for (int i = 0; i < letters.size(); i++)
                answer += letters.get(i);
            screen.displayAnswer(answer, g2);
        }
        else if (!gameLost)
            screen.drawStartScreen(g2);
        else
            screen.drawLoseScreen(g2, highscore);
    }
    
    private void update()
    {
        if (keyListener.getGameStarted())
            gameStarted = true;
        if (gameStarted && !gameLost)
        {
            if (player.getPlayerSpeed() > 0)
                gameLost = true;
            for (int i = 0; i < screen.getCars().size(); i++)
            {
                if (player.getPlayerHB().touching(screen.getCars().get(i).getObjHB()))
                    gameLost = true;
            }
            screen.generateBackground();
            screen.moveBackground(-player.getPlayerSpeed());
            screen.generateCars();
            screen.moveCars(-player.getPlayerSpeed() - 3);
            player.movePlayer(keyListener.getLeft(), keyListener.getRight());
            if (keyListener.getStart())
            {
                char currLetter = (char)keyListener.getKey();
                if (letters.size() < 10)
                    letters.add(currLetter + "");
                else
                    letters.set(letters.size() - 1, currLetter + "");
                keyListener.setStart();
            }
            if (!generated)
            {
                problem = math.generateProblem(difficulty);
                generated = true;
            }
            if (keyListener.deleted() && letters.size() > 0)
            {
                letters.remove(letters.size() - 1);
                keyListener.setDelete();
            }
            if (keyListener.submitted())
            {
                keyListener.setSubmit();
                String answer = "";
                for (int i = 0; i < letters.size(); i++)
                {
                    answer += letters.get(i);
                }
                if (math.checkAnswer(answer))
                {
                    generated = false;
                    correct++;
                    score++;
                    player.speedUp();
                }
                else
                {
                    correct = 0;
                    if (highscore < score)
                        highscore = score;
                    score = 0;
                    if (difficulty > 1)
                        difficulty--;
                    player.slowDown();
                }
                letters.clear();
                if (correct == 1 + difficulty)
                {
                    difficulty++;
                    correct = 0;
                }
            }
        }
        else if (gameLost)
        {
            highscore = score;
            if (keyListener.getR())
                gameRestarted = true;
        }
        if (gameRestarted)
        {
            gameLost = false;
            gameStarted = false;
            gameRestarted = false;
            generated = false;
            difficulty = 0;
            correct = 0;
            highscore = 0;
            score = 0;
            letters.clear();
            player.restartCharacter();
            screen.restartScene();
        }
    }
}