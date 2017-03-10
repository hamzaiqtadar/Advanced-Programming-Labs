import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Crawler implements Runnable
{
    List<String> results;
    String URL;
    public Crawler(List<String> S,String URL) 
    {
        results=S;
        this.URL=URL;
    }
    @Override
    public void run() 
    {
        crawl_it(URL);
    }
    public void crawl_it(String initialDirectory)
    {
        File fr2 = new File(initialDirectory);
        if (fr2.exists() & fr2.isDirectory()) 
        {
            System.out.println("Crawling");
            try 
            {
                if (finder(fr2)) 
                {
                    System.out.println("Indexing Complete. Enter the word to search:");
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        } 
        else
        {
            System.out.println("Directory path is corrupt");
        }
    }
    public boolean finder(File cnt) throws IOException 
    {
        File[] names = cnt.listFiles();
        File file1;
        int flag = 0;
        if (names == null) 
        {
            return false;
        }
        for (int i = 0; i < names.length; i++) 
        {
            file1 = names[i];
            if (file1.isDirectory()) 
            {
                finder(file1);
            }
            if (file1.isFile()) 
            {
                System.out.println(file1.getCanonicalPath());
                String g = file1.getAbsolutePath();
                results.add(g);
            }
        }
        return false;
    }
}
