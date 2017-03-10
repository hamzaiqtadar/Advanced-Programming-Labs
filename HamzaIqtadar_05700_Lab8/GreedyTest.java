import static org.junit.Assert.*;

import org.junit.Test;

public class GreedyTest 
{
	@Test
	public void Greedy() 
	{
		Greedy test=new Greedy();
		int[] coins={25,10,5,1};
		int value=99;
		int number=0;
		int ans=9;
		assertEquals(test.greedyChange(value, coins, number),ans);
		assertEquals(test.dynamicChange(coins, value),ans);
	}

}
