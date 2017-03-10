import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

public class Interpreter 
{
	HashMap<String,Object> storage=new HashMap();
	public static void main(String[] args)
	{
		Interpreter obj=new Interpreter();
		try 
		{
			obj.readFile();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(obj.storage.toString());
	}
	public void readFile() throws IOException 
	{
		 FileReader fileReader = new FileReader(new File("D:/test.txt"));

		 BufferedReader br = new BufferedReader(fileReader);
//		 handleCharacters(br);
		 String line = null;
		 // if no more lines the readLine() returns null
		 while ((line = br.readLine()) != null) 
		 {
		      // reading lines until the end of the file
			 interpret(line);
		 }
	}
	public void interpret(String line)
	{
		String[] slashed=line.split(" +");
		if(slashed[0].equals("var"))
		{
			if(isNumeric(slashed[1]))
			{
				System.out.println("There is a synatx error in the line: "+line);
				return;
			}
			String[] str=line.split("var")[1].trim().split("=");
			if(str.length==1)
			{
				System.out.println("There is a synatx error in the line: "+line);
				return;
			}
			storage.put(str[0].trim(),Integer.parseInt(str[1].trim()));
//			for(int i=0;i<slashed.length;i++)
//			{
//
//			}
		}
		else if(slashed[0].equals("print"))
		{
//			if(isNumeric(slashed[1]))
//			{
//				System.out.println("There is a synatx error in the line: "+line);
//				return;
//			}
			String[] str=line.split("print");
			if(str.length==1)
			{
				System.out.println("There is a synatx error in the line: "+line);
				return;
			}
			String str2=str[1].trim();
			if(str2.length()==1)
			{
				if(storage.get(str2)==null)
				{
					System.out.println("There is an undeclared variable in the line: "+line);
					return;
				}
				else
				{
					System.out.println(str2+" = "+storage.get(str2));
				}
			}
			else
			{
				translate(str2);
			}
		}
	}
	public void translate(String str2)
	{
		Stack<String> ops = new Stack<String>();
        Stack<Integer> vals = new Stack<Integer>();
        str2=str2.replace("+"," + ");
        str2=str2.replace("-"," - ");
        str2=str2.replace("*"," * ");
        str2=str2.replace("/"," / ");
        String str3[] =str2.split(" +");
        for(int i = 0; i < str3.length; i++) {
                        String s = str3[i];
            if (s.equals("(")) 
            {
            	
            }
            else if (s.equals("+") || s.equals("*") || s.equals("-") || s.equals("/")) 
            {
                ops.push(s);
            } 
            else if (s.equals(")")) 
            {
                getComp(ops, vals);
            } else 
            {
            	if(isNumeric(s))
            	{
            		vals.push(Integer.parseInt(s));
            	}
            	else
            	{
            		if(storage.get(s)==null)
            		{
            			System.out.println("There is an error in the code");
    					return;
            		}
            		else
            		{
            			vals.push((Integer) storage.get(s));
            		}
            	}	
            }
        }
        getComp(ops, vals);
        System.out.println(vals.pop());
	}
	 private static void getComp(Stack<String> ops, Stack<Integer> vals) 
	 {
	        String op = ops.pop();
	        if (op.equals("+")) 
	        {
	            vals.push(vals.pop() + vals.pop());
	        } 
	        else if (op.equals("*")) 
	        {
	            vals.push(vals.pop() * vals.pop());
	        }
	        if (op.equals("-")) 
	        {
	            vals.push(vals.pop() + vals.pop());
	        } 
	        else if (op.equals("/")) 
	        {
	            vals.push(vals.pop() * vals.pop());
	        }
	    }
	public static boolean isNumeric(String s) 
	{
	    return s.matches("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)");
	}
//	private static void handleCharacters(BufferedReader reader) throws IOException 
//	{
//        int r;
//        while ((r = reader.read()) != -1) {
//            char ch = (char) r;
//            System.out.println("Do something with " + ch);
//        }
//	}
}
