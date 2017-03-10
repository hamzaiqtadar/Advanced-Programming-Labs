import java.io.*;
import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Indexing extends Thread
{
	crawler c = new crawler();
	public File fr2;
	
    public File load(String dr) throws IOException {
        File fr2 = new File(dr);

        if (fr2.exists() & fr2.isDirectory()) {                // checking if the file folder exists then search
            System.out.println("Indexing... Please wait");
            c.index = new HashMap<>();
            // calling finder to search for name passed in argument and the directory
//            return fr2;
//
//        } else
//        {
//            System.out.println("Directory path is corrupt");
//            //return false;
//        }
		
    }
		return fr2;
}

}
