import java.io.*;
import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Searching extends Thread
{
	crawler c = new crawler();
	Extension E = new Extension();
	public boolean finder(File cnt, ConcurrentHashMap index) throws IOException {

        File[] names = cnt.listFiles();                // storing all the files in the file array
        File cnt1;
        int flag = 0;
        if (names == null) {                            // if directory is empty return from it
            return false;
        }
        for (int i = 0; i < names.length; i++) {
            cnt1 = names[i];
//            if (cnt1.isDirectory()) {
//                finder(cnt1, ConcurrentHashMap index);                    // going into inner directory
//            }
            if (cnt1.isFile()) {
                // First store file name and splits on spaces and extension
                List<String> lc;
                String s = cnt1.getName();
                String g = cnt1.getAbsolutePath();
                String dot[] = s.split("\\.(?=[^\\.]+$)");
                lc = (List<String>) index.get(dot[dot.length-1]);
                if (lc != null) {
                    lc.add(g);
                } else {
                    List<String> kc = new ArrayList<>();
                    kc.add(g);
                    index.put(dot[dot.length - 1], kc);
                }
                String f[];
                if (dot.length>1)
                 f= dot[0].split("\\s+");
                else
                    f=s.split("\\s+");;
                for (int k = 0; k < f.length; k++) {
                    lc = (List<String>) index.get(f[k]);
                    if (lc != null) {
                        lc.add(g);
                    } else {
                        List<String> kc = new ArrayList<>();
                        kc.add(g);
                        index.put(f[k], kc);
                    }
                }
                lc = (List<String>) index.get(s);
                if (lc != null) {
                    lc.add(g);
                } else {
                    List<String> kc = new ArrayList<>();
                    kc.add(g);
                    index.put(s, kc);
                }
                if (E.getExtension(s).equalsIgnoreCase("txt")) 
                {
                    FileReader fr = new FileReader(g);
                    BufferedReader tr = new BufferedReader(fr);
                    String sCurrentLine;
                    while ((sCurrentLine = tr.readLine()) != null) 
                    {
                        String p[] = sCurrentLine.split("\\s+");
                        for (int k = 0; k < p.length; k++) 
                        {
                            lc = (List<String>) index.get(p[k]);
                            if (lc != null) 
                            {
                                lc.add(g);
                            } else 
                            {
                                List<String> kc = new ArrayList<>();
                                kc.add(g);
                                index.put(p[k], kc);
                            }
                        }
                    }
                    flag = 1;
                } 
                else 
                {
                    flag = 1;
                }

            }
        }
        if (flag == 1)
            return true;
        else
            return false;
    }
}
