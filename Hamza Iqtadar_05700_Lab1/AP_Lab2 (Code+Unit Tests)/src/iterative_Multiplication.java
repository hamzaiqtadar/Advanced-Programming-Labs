import java.util.Random;
import java.util.Scanner;

public class iterative_Multiplication {

   public static void main(String[] args) {
       Scanner s = new Scanner(System.in);
       System.out.println("Enter order n :");
       int N = s.nextInt();
       int[][] a = new int[N][N];
       int[][] b = new int[N][N];
       for (int i = 0; i < a.length; i++) {
           for (int j = 0; j < a[0].length; j++) {
        	   Random r = new Random();
           	int random = r.nextInt(100) + 1;
       		a[i][j] = random;
           }
       }
       System.out.println("Matrix A is:");
       for (int i = 0; i < a.length; i++) {
           for (int j = 0; j < a[0].length; j++) {
        	   System.out.print(a[i][j] + " ");
           }
           System.out.println();
       }
       for (int i = 0; i < b.length; i++) {
           for (int j = 0; j < b[0].length; j++) {
        	   Random r = new Random();
           	int random = r.nextInt(100) + 1;
       		b[i][j] = random;
           }
       }
       System.out.println("Matrix B is:");
       for (int i = 0; i < b.length; i++) {
           for (int j = 0; j < b[0].length; j++) {
        	   System.out.print(b[i][j] + " ");
           }
           System.out.println();
       }
       int[][] c = multiply(a, b);
       System.out.println("Product of A and B is");
       for (int i = 0; i < c.length; i++) {
           for (int j = 0; j < c[0].length; j++) {
               System.out.print(c[i][j] + " ");
           }
           System.out.println();
       }
   }
   public static int[][] multiply(int[][] a, int[][] b) {
       int rowsInA = a.length;
       int columnsInA = a[0].length;
       int columnsInB = b[0].length;
       int[][] c = new int[rowsInA][columnsInB];
       for (int i = 0; i < rowsInA; i++) {
           for (int j = 0; j < columnsInB; j++) {
               for (int k = 0; k < columnsInA; k++) {
                   c[i][j] = c[i][j] + a[i][k] * b[k][j];
               }
           }
       }
       return c;
   }
}
//Acknowledgments: http://www.javawithus.com/programs/matrix-