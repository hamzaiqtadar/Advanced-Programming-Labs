import java.util.Scanner;

public class Greedy 
{
	public static void main(String[] args)
	{
		Scanner s=new Scanner(System.in);
		int value=0;
		int number=0;
		int coins[] = {25, 10, 5, 1};
		System.out.println("Enter 1 to use Greedy Algorithm.\nEnter 2 to use Dynamic Programming ");
		int x=s.nextInt();
		switch(x)
		{
			case 1:
			{
				System.out.println("Enter the amount to change using coins: ");
				value=s.nextInt();
				System.out.println("Number of coins required to change are: "+greedyChange(value,coins,number));
			}
			break;
			case 2:
			{
				System.out.println("Enter the amount to change using coins: ");
				value=s.nextInt();
				System.out.println("Number of coins required to change are: "+dynamicChange(coins,value));
			}
			break;
			default:
			{
				System.out.println("Incorrect input.");
			}
			break;
		}
	}
	public static int greedyChange(int value,int[] coins,int number)	
	{
		if(value>=coins[0])
		{
			number+=1;
			number=greedyChange(value-coins[0],coins,number);
		}
		else if(value>=coins[1])
		{
			number+=1;
			number=greedyChange(value-coins[1],coins,number);
		}		
		else if(value>=coins[2])
		{
			number+=1;
			number=greedyChange(value-coins[2],coins,number);
		}
		else if(value==0)
		{
			return number;
		}
		else
		{
			number+=value;
		}
		return number;
	}
	public static int dynamicChange(int[] denom, int targetAmount) 
	{
	    int actualAmount;
	    int m = denom.length+1;
	    int n = targetAmount + 1;
	    int infinity = Integer.MAX_VALUE-1;
	    int[][] table = new int[m][n];
	    for (int j = 1; j < n; j++)
	    {
	        table[0][j] = infinity;
	    }
	    for (int j = 1; j < m; j++) 
	    {
	        for (int i = 1; i < n; i++) 
	        {
	            if (i - denom[j-1] >= 0)
	            {
	                actualAmount = table[j][i - denom[j-1]];
	            }
	            else
	            {
	                actualAmount = infinity;
	            }
	            table[j][i] = Math.min(table[j-1][i], 1 + actualAmount);
	        }
	    }
	    return table[m-1][n-1];
	}
}
