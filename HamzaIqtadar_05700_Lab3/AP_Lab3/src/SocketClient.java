import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
	public static void main(String[] args) 
	{
		try
		{	
			Socket sender = new Socket("localhost",9900);
			Records newRec = Input();
			ObjectOutputStream outServer = new ObjectOutputStream(sender.getOutputStream());
			ObjectInputStream inServer = new ObjectInputStream(sender.getInputStream());
			outServer.writeObject(newRec);
			if(newRec.op == 2)
			{
				while(true)
				{
					Records newRec1 = (Records)inServer.readObject();
					System.out.println("Results: \n User Name: "+newRec1.uname+ "\n Notes : "+ newRec1.notes);
				}
			}
			sender.close();   
		}
		catch(Exception e)
		{
			System.out.println("Exception");
			System.out.println(e.getMessage());
		}
	}
	public static Records Input()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("To enter a new Record,enter 1 \n Tp search a previous record,enter 2");
		int option = Integer.parseInt(sc.nextLine());
		System.out.println("Enter username: ");
		String username = sc.nextLine();
		Records newRec;
		if(option == 1)
		{
			System.out.println("Enter your note:");			
			String notes = sc.nextLine();
			newRec = new Records(username,notes,1);
		}
		else
		{
			newRec = new Records(username,"",2);
		}
		return newRec;
	}
}
