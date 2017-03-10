import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
 
public class SocketServer {
	public static void main(String[] args)
	{
		ServerSocket listener = null;
		try{
			ArrayList<Records> list = new ArrayList<Records>();
			listener = new ServerSocket(9900);
			System.out.println("Waiting for clients...");
			while(true)
			{
				Socket socket = listener.accept();
				ObjectOutputStream  OutClient = new ObjectOutputStream (socket.getOutputStream());
				ObjectInputStream inClient = new ObjectInputStream(socket.getInputStream());
				Records newRec;
				newRec = (Records)inClient.readObject();
				if(newRec.op == 1){
					list.add(newRec);
					System.out.println("Record Received \n User: " +newRec.uname+ "\n Notes: " + newRec.notes);
				}
				else
				{
					for(int i = 0; i < list.size();i++)
					{
						if(list.get(i).uname.equals(newRec.uname))
						{
							OutClient.writeObject(list.get(i));
						}
						else {
							System.out.println("User Not Found!");
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}

