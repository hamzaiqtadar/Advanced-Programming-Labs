import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Indexer extends Extension implements  Runnable {

	Extension getExt=new Extension();
    List<String> paths;
    ConcurrentHashMap<String, List<String>> index;
    ConcurrentHashMap<String,ConcurrentHashMap<String,String>>fileinfo;
    int start, stop;
    public Indexer(List<String> paths, int start, int stop, ConcurrentHashMap<String, List<String>> in,ConcurrentHashMap<String,ConcurrentHashMap<String,String>>fi) 
    {
        this.index=in;
        this.paths=paths;
        this.start=start;
        this.stop=stop;
        this.fileinfo=fi;
    }
    @Override
    public void run() 
    {
        try 
        {
            IndexFiles();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    public void  IndexFiles() throws IOException 
    {
        File file1;
        for(int i=start;i<stop;i++){
            file1 = new File(paths.get(i));
            if (file1.isFile()) 
            {
            	BasicFileAttributes attr = Files.readAttributes(file1.toPath(), BasicFileAttributes.class);
            	String creationTime=attr.creationTime().toString();
                String modificationTime=attr.creationTime().toString();
                String accessTime=attr.lastAccessTime().toString();
                String fileSize=attr.size()+"";
                ConcurrentHashMap<String,String> info= new ConcurrentHashMap<>();
                info.put("Creation Time", creationTime);
                info.put("Last Modified", modificationTime);
                info.put("Last Accessed", accessTime);
                info.put("File Size", fileSize);
                String writePermission;
                if(file1.canWrite())
                {
                	writePermission="Write Permission Available";
                }
                else 
                {
                	writePermission="Write Permission not Available";
                }
                info.put("Write Permission", writePermission);
                String readPermission;
                if(file1.canRead())
                {
                	readPermission="Write Permission Available";
                }
                else 
                {
                	readPermission="Write Permission not Available";
                }
                info.put("Read Permission", readPermission);
                FileOwnerAttributeView ownerAttributeView = Files.getFileAttributeView(file1.toPath(), FileOwnerAttributeView.class);
                UserPrincipal owner = ownerAttributeView.getOwner();
                String ownername=owner.getName();
                info.put("File Owner", ownername);               
                List<String> lc;
                String s = file1.getName();
            	info.put("File Name", s);
                String g = file1.getAbsolutePath();
                fileinfo.put(g, info);
            	info.put("File Path", g);
                String dot[] = s.split("\\.(?=[^\\.]+$)");
                lc = index.get(dot[dot.length - 1]);
                if (lc != null) {
                    lc.add(g);
                } else {
                    List<String> kc = Collections.synchronizedList(new ArrayList<String>());
                    kc.add(g);
                    index.put(dot[dot.length - 1], kc);
                }
                String f[];
                if (dot.length>1)
                    f= dot[0].split("\\s+");
                else
                    f=s.split("\\s+");;
                for (int k = 0; k < f.length; k++) 
                {
                    lc = index.get(f[k]);
                    if (lc != null) 
                    {
                        lc.add(g);
                    } else {
                        List<String> kc = Collections.synchronizedList(new ArrayList<String>());
                        kc.add(g);
                        index.put(f[k], kc);
                    }
                }

                lc = index.get(s);
                if (lc != null) 
                {
                    lc.add(g);
                } else 
                {
                    List<String> kc = Collections.synchronizedList(new ArrayList<String>());
                    kc.add(g);
                    index.put(s, kc);
                }
                if (Extension.getExtension(s).equalsIgnoreCase("txt")) 
                {
                    FileReader fr = new FileReader(g);
                    BufferedReader tr = new BufferedReader(fr);
                    String sCurrentLine;
                    while ((sCurrentLine = tr.readLine()) != null) 
                    {
                        String p[] = sCurrentLine.split("\\s+");
                        for (int k = 0; k < p.length; k++) 
                        {
                            lc = index.get(p[k]);
                            if (lc != null) 
                            {
                                lc.add(g);
                            } else 
                            {
                                List<String> kc = Collections.synchronizedList(new ArrayList<String>());
                                kc.add(g);
                                index.put(p[k], kc);
                            }
                        }
                    }
                }
            }
        }
    }
}
