import java.lang.Math;
import java.util.ArrayList;

public class Mathers
{
    private int answer;
    public Mathers()
    {
        answer = 0;
    }
    
    public String generateProblem(int difficulty)
    {
        String problem = "";
        int numOfNums = (int)(Math.random() * difficulty) + 2;
        int[] nums = new int[numOfNums];
        for (int i = 0; i < nums.length; i++)
        {
            int operation = (int)(Math.random() * 4) + 1;
            nums[i] = ((int)(Math.random() * (10 * difficulty))) + 1;
            if (i < nums.length - 1)
                switch (operation)
                {
                    case 1:
                        problem += nums[i] + " + ";
                        break;
                    case 2:
                        problem += nums[i] + " - ";
                        break;
                    case 3:
                        problem += nums[i] + " * ";
                        break;
                    case 4:
                        problem += nums[i] + " / ";
                        break;
                }
            else
                problem += nums[i];
        }
        String[] probArray = problem.split(" ");
        ArrayList<Integer> numArray = new ArrayList<Integer>();
        ArrayList<String> opArray = new ArrayList<String>();
        for (int x = 0; x < probArray.length; x++)
        {
            if (probArray[x].equals("+") || probArray[x].equals("-") || probArray[x].equals("*") || probArray[x].equals("/"))
                opArray.add(probArray[x]);
            else
                numArray.add(Integer.parseInt(probArray[x]));
        }
        answer = numArray.get(0);
        int a = 1, b = 0;
        while (a < numArray.size() && b < opArray.size())
        {
            switch (opArray.get(b))
            {
                case "+":
                    answer += numArray.get(a);
                    break;
                case "-":
                    answer -= numArray.get(a);
                    break;
                case "*":
                    answer *= numArray.get(a);
                    break;
                case "/":
                    answer /= numArray.get(a);
                    break;
            }
            a++;
            b++;
        }
        return problem;
    }
    
    public boolean checkAnswer(String input)
    {
        if (input.equals(""))
            return false;
        if (Integer.parseInt(input) == answer)
            return true;
        return false;
    }
}