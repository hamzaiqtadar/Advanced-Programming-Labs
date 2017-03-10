import java.io.*;
import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Extension extends Thread
{
	 public static String getExtension(String filename) 
	 {
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
}
