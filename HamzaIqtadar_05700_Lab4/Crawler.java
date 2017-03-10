import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Crawler {

	public static void Walk(String st, String s2) throws IOException
	{
		File ff = new File(st);
		File[] list = ff.listFiles();	
		if (list == null) 
		{
			System.out.println("There are no files in the given folder.");
			return;
		}	
		else
		{
			HashMap hm = new HashMap();
			
			for (int a=0;a<list.length;a++) 
			{				
				File f=list[a];	
				if (f.isDirectory()) 
				{
					Walk(f.getAbsolutePath(),s2);
				}
				else if (getExtension(f.getName()).equalsIgnoreCase("txt") )
				{
					System.out.println(f.getAbsolutePath());
						String address=f.getAbsolutePath();
						String content;
						BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));
						try
						{
							while ((content = br.readLine()) != null) 
							{
								content=br.readLine();
								String[] fileword=content.split("\\s+");
								for(int i=0;i<fileword.length;i++)
								{
									hm.put(address, fileword[i]);
								}
							}
						}
						catch(Exception e)
						{
							System.out.println("Error : "+e);
						}
					}
					else
					{
						hm.put(f.getAbsolutePath(), "Filetype not supported");
					}
				
				
			}
			System.out.println("The given word occured in: "+hm.get(s2));
		}

	}
	public static String getExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int extensionPos = filename.lastIndexOf('.');
        int lastUnixPos = filename.lastIndexOf('/');
        int lastWindowsPos = filename.lastIndexOf('\\');
        int lastSeparator = Math.max(lastUnixPos, lastWindowsPos);

        int index = lastSeparator > extensionPos ? -1 : extensionPos;
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }
	public static void main(String args[]) throws IOException
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the folder path:");
		String folder=s.nextLine();
		System.out.println("Enter the word to be searched:");
		String word=s.nextLine();
		Walk(folder,word);
	}
}
