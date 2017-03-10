
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

	public class Main 
	{
		/**
		 * This is the main class of the project.It runs the main method which asks the user to input an option.The user can search whether a word occurs in document specified, or the user can search for the file paths in which a word occurs.Similarly the user can search for a word based on the file's path and attributes(in this case size)...
		 *
		 * @param ...
		 * @return .....
		 */
	    List<String> list;
	    ConcurrentHashMap<String, List<String>> in;
	    static ConcurrentHashMap<String,ConcurrentHashMap<String,String>>fileinfo;

	    public Main() 
	    {
	        list = Collections.synchronizedList(new ArrayList<String>());
	        in= new ConcurrentHashMap<>();
	        fileinfo=new ConcurrentHashMap<>();
	    }
	    public void crawl()
	    {
	        File f = new File("D://NUST//6th Semester//Advanced Programming//Labs//Lab 4//Test");
	        File file1;

	        if (f.isDirectory()) 
	        {
	            File[] files = f.listFiles(); 
	            if (files == null) 
	            {
	                return;
	            }
	            List<Thread> threads = new ArrayList<>();
	            for (int i = 0; i < files.length; i++) 
	            {
	                //String p=files[i].getAbsolutePath();
	                
	                if (files[i].isDirectory()) 
	                {
	                   Thread t = new Thread(new Crawler(list,files[i].getAbsolutePath()));
	                    t.start();
	                    threads.add(t);
	                }
	                else 
	                {
	                	String path=files[i].getAbsolutePath();
	                    list.add(path);
	                }
	            }
	            for (Thread thread : threads)
	            {
	            	try 
	            	{
	                    thread.join();
	                } 
	            	catch (InterruptedException e) 
	            	{
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	    public void index()
	    {
	        int x= list.size()/3;
	        List<Thread> threads = new ArrayList<>();
	        Thread t1 = new Thread(new Indexer(list,0,x,in,fileinfo));
	        t1.start();
	        threads.add(t1);
	        Thread t2 = new Thread(new Indexer(list,x,x*2,in,fileinfo));
	        t2.start();
	        threads.add(t2);
	        Thread t3 = new Thread(new Indexer(list,x*2,x*3,in,fileinfo));
	        t3.start();
	        threads.add(t3);
	        for (Thread thread : threads)
	        {
	            try 
	        	{
	                thread.join();
	            } 
	        	catch (InterruptedException e) 
	        	{
	                e.printStackTrace();
	            }
	        }
	    }
	    public static void main(String[] args) 
	    {
	        Scanner s = new Scanner(System.in);
	        Main obj= new Main();
	        obj.crawl();
	        obj.index();
            System.out.println("Enter 1 to search using file name\nEnter 2 to search by content\nEnter 3 to search by path\nEnter 4 to search by Attributes.");
	        int option=s.nextInt();
            while (true) 
	        {
	            switch(option)
	            {
	            	case 1:
	            	{
	            		String str = s.nextLine();
	            		System.out.println("Enter the file name in which to search: ");
	            		String str2 = s.nextLine();
	    	            List<String> lstr = obj.in.get(str);
	            		if (lstr != null) 
	            		{
	            			for (int i = 0; i < lstr.size(); i++)
	            			{
	            				//if(lstr.get(i).equals())
	            				String filepath=lstr.get(i);
	            				ConcurrentHashMap<String,String> temp=new ConcurrentHashMap<>();
	            				temp=fileinfo.get(filepath);
	            				String name=temp.get("File Name");
	            				if(str2.equals(name))
	            				{
	            					System.out.println("The word occurs in this file.");
	            				}
	            			}
	            		}
	            		System.out.println("Enter the word to search: ");
	            	}
	            	break;
	            	case 2:
	            	{
	            		String str = s.nextLine();
	    	            List<String> lstr = obj.in.get(str);
	            		if (lstr != null) 
	            		{
	            			for (int i = 0; i < lstr.size(); i++)
	            			{
	            				System.out.println(lstr.get(i));
	            			}
	            		}
	            		System.out.print("Enter the word to search: ");
	            	}
	            	break;
	            	case 3:
	            	{
	            		String str = s.nextLine();
	            		System.out.println("Enter the file path in which to search: ");
	            		String str2 = s.nextLine();
	    	            List<String> lstr = obj.in.get(str);
	            		if (lstr != null) 
	            		{
	            			for (int i = 0; i < lstr.size(); i++)
	            			{
	            				String filepath=lstr.get(i);
	            				ConcurrentHashMap<String,String> temp=new ConcurrentHashMap<>();
	            				temp=fileinfo.get(filepath);
	            				String path=temp.get("File Path");
	            				if(str2.equals(path))
	            				{
	            					System.out.println("The word occurs in the path specified.");
	            				}
	            			}
	            		}
	            		System.out.println("Enter the word to search: ");
	            	}
	            	break;
	            	case 4:
	            	{
	            		String str = s.nextLine();
	            		System.out.println("Enter the size of file in which to search: ");
	            		String str2 = s.nextLine();
	    	            List<String> lstr = obj.in.get(str);
	            		if (lstr != null) 
	            		{
	            			for (int i = 0; i < lstr.size(); i++)
	            			{
	            				String filepath=lstr.get(i);
	            				ConcurrentHashMap<String,String> temp=new ConcurrentHashMap<>();
	            				temp=fileinfo.get(filepath);
	            				String size=temp.get("File Size");
	            				if(str2.equals(size))
	            				{
	            					System.out.println("The word occurs in a file of the size specified.");
	            				}
	            			}
	            		}
	            		System.out.println("Enter the word to search: ");
	            	}
	            	break;
	            	default:
	            		System.out.print("Incorrect Option.Correct options are 1-4.");
	            }
	        }
	    }
	}