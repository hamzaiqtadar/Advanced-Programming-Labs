import java.io.*;
import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class crawler extends Thread{

    

//    public static String getExtension(String filename) {
//        if (filename == null) {
//            return null;
//        }
//        int extensionPos = filename.lastIndexOf('.');
//        int lastUnixPos = filename.lastIndexOf('/');
//        int lastWindowsPos = filename.lastIndexOf('\\');
//        int lastSeparator = Math.max(lastUnixPos, lastWindowsPos);
//
//        int index = lastSeparator > extensionPos ? -1 : extensionPos;
//        if (index == -1) {
//            return "";
//        } else {
//            return filename.substring(index + 1);
//        }
//    }
//    public boolean load(String dr) throws IOException {
//        File fr2 = new File(dr);
//
//        if (fr2.exists() & fr2.isDirectory()) {                // checking if the file folder exists then search
//            System.out.println("Indexing... Please wait");
//            index = new HashMap<>();
//            // calling finder to search for name passed in argument and the directory
//            if (finder(fr2)) {
//                System.out.println("Indexing Complete. Enter your key word");
//                return true;
//            }
//
//        } else
//            System.out.println("Directory path is corrupt");
//        return false;
//    }

//    public boolean finder(File cnt) throws IOException {
//
//        File[] names = cnt.listFiles();                // storing all the files in the file array
//        File cnt1;
//        int flag = 0;
//        if (names == null) {                            // if directory is empty return from it
//            return false;
//        }
//        for (int i = 0; i < names.length; i++) {
//            cnt1 = names[i];
//            if (cnt1.isDirectory()) {
//                finder(cnt1);                    // going into inner directory
//            }
//            if (cnt1.isFile()) {
//                // First store file name and splits on spaces and extension
//                List<String> lc;
//                String s = cnt1.getName();
//                String g = cnt1.getAbsolutePath();
//                String dot[] = s.split("\\.(?=[^\\.]+$)");
//                lc = index.get(dot[dot.length - 1]);
//                if (lc != null) {
//                    lc.add(g);
//                } else {
//                    List<String> kc = new ArrayList<>();
//                    kc.add(g);
//                    index.put(dot[dot.length - 1], kc);
//                }
//                String f[];
//                if (dot.length>1)
//                 f= dot[0].split("\\s+");
//                else
//                    f=s.split("\\s+");;
//                for (int k = 0; k < f.length; k++) {
//                    lc = index.get(f[k]);
//                    if (lc != null) {
//                        lc.add(g);
//                    } else {
//                        List<String> kc = new ArrayList<>();
//                        kc.add(g);
//                        index.put(f[k], kc);
//                    }
//                }
//                lc = index.get(s);
//                if (lc != null) {
//                    lc.add(g);
//                } else {
//                    List<String> kc = new ArrayList<>();
//                    kc.add(g);
//                    index.put(s, kc);
//                }
//                if (getExtension(s).equalsIgnoreCase("txt")) 
//                {
//                    FileReader fr = new FileReader(g);
//                    BufferedReader tr = new BufferedReader(fr);
//                    String sCurrentLine;
//                    while ((sCurrentLine = tr.readLine()) != null) 
//                    {
//                        String p[] = sCurrentLine.split("\\s+");
//                        for (int k = 0; k < p.length; k++) 
//                        {
//                            lc = index.get(p[k]);
//                            if (lc != null) 
//                            {
//                                lc.add(g);
//                            } else 
//                            {
//                                List<String> kc = new ArrayList<>();
//                                kc.add(g);
//                                index.put(p[k], kc);
//                            }
//                        }
//                    }
//                    flag = 1;
//                } 
//                else 
//                {
//                    flag = 1;
//                }
//
//            }
//        }
//        if (flag == 1)
//            return true;
//        else
//            return false;
//    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        crawler c = new crawler();
        Indexing I = new Indexing();
        Searching S = new Searching();
        File fr = null;
        try 
        {
            fr=I.load("D://NUST//6th Semester//Advanced Programming//Labs//Lab 4//Test");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConcurrentHashMap<String, List<String>> index = null;
        while (true) {
            //System.out.print("Enter the word to search: ");
            try {
				
				if (S.finder(fr,index)) {
				    System.out.println("Indexing Complete. Enter your key word");
				    String q = s.nextLine();
				    List<String> lv = index.get(q);
				    if (lv != null) {
		                for (int o = 0; o < lv.size(); o++)
		                    System.out.println(lv.get(o));
		            }
				    //return true;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
        }
    }

	public HashMap index;

}
