import static org.junit.Assert.*;

import org.junit.Test;

public class matrix_MultiplierTest {

	@Test
	public void iterative_Multiplication() {
		iterative_Multiplication test=new iterative_Multiplication();
		int A[][]={{1,2},{3,4}};
		int B[][]={{2,0},{1,2}};
		int C[][]={{4,4},{10,8}};
		assertArrayEquals(test.multiply(A,B),C);
//		fail("Not yet implemented");
	}
}
