import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Iterator; 
 
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {
   private static SessionFactory factory; 
   public static void main(String[] args) throws IOException {
      try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      ManageEmployee ME = new ManageEmployee();
      
      final File folder = new File("C:\\Users\\Hamza\\Desktop");
      listFilesForFolder(folder);
      //listEmployees();
   }
   public static void listFilesForFolder(final File folder) throws IOException 
   {
	    for (final File fileEntry : folder.listFiles()) 
	    {
	        if (fileEntry.isDirectory()) 
	        {
	            listFilesForFolder(fileEntry);
	        } else 
	        {
	        	//System.out.println(fileEntry.getName());
	            //addEmployee(fileEntry.getName(),fileEntry.getAbsolutePath(),fileEntry.getParent(),fileEntry.getPath());
	            listEmployees(fileEntry.getName(),fileEntry.getAbsolutePath(),fileEntry.getParent(),fileEntry.getPath());
	        }
	    }
	}
   private static String getFileChecksum(MessageDigest digest, File file) throws IOException
   {
       //Get file input stream for reading the file content
       FileInputStream fis = new FileInputStream(file);

       //Create byte array to read data in chunks
       byte[] byteArray = new byte[1024];
       int bytesCount = 0;

       //Read file data and update in message digest
       while ((bytesCount = fis.read(byteArray)) != -1) {
           digest.update(byteArray, 0, bytesCount);
       };

       //close the stream; We don't need it now.
       fis.close();

       //Get the hash's bytes
       byte[] bytes = digest.digest();

       //This bytes[] has bytes in decimal format;
       //Convert it to hexadecimal format
       StringBuilder sb = new StringBuilder();
       for(int i=0; i< bytes.length ;i++)
       {
           sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
       }

       //return complete hash
       return sb.toString();
   }
   /* Method to CREATE an employee in the database */
   public static Integer addEmployee(String fname, String path, String folder, String filepath)
   {
      Session session = factory.openSession();
      Transaction tx = null;
      Integer employeeID = null;
      try
      {
    	  File filetohash=new File(filepath);
    	  MessageDigest sha1Digest = MessageDigest.getInstance("SHA-1");
    	String filehash = getFileChecksum(sha1Digest,filetohash);
         tx = session.beginTransaction();
         Employee employee = new Employee(fname, path, folder,filehash);
         employeeID = (Integer) session.save(employee); 
         tx.commit();
      }
      catch (IOException e) 
      {
		// TODO Auto-generated catch block
		e.printStackTrace();
      } 
      catch (NoSuchAlgorithmException e) 
      {
		// TODO Auto-generated catch block
		e.printStackTrace();
      }
      finally 
      {
         session.close(); 
      }
      return employeeID;
   }
   /* Method to  READ all the employees */
   public static void listEmployees(String fname, String path, String folder, String filepath){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
    	  File filetohash=new File(filepath);
    	  MessageDigest sha1Digest = MessageDigest.getInstance("SHA-1");
    	String filehash = getFileChecksum(sha1Digest,filetohash);
         tx = session.beginTransaction();
         
//         String sql = "SELECT * FROM EMPLOYEE WHERE f = :employee_id";
//         SQLQuery query = session.createSQLQuery(sql);
//         query.addEntity(User.class);
//         query.setParameter("user_id", 3);
//         List<User> results = query.list();
         
         List employees = session.createQuery("FROM Employee").list(); 
         for (Iterator iterator = employees.iterator(); iterator.hasNext();){
            Employee employee = (Employee) iterator.next();
            String newhash=employee.getFilehash();
            String name=employee.getFilename();
            if (/*(!newhash.equals(filehash))  && */ (filepath.equals(name))) 
	        {
            	if (!newhash.equals(filehash))
            	{
            	//listFilesForFolder(fileEntry);
            		//System.out.println(filepath);
            		//System.out.println(name);
            	
	            System.out.println("Changes have been made in the File: " + employee.getFilename()); 
	            //System.out.print("  Path Name: " + employee.getFilepath()); 
	            //System.out.println("  Folder: " + employee.getFoldername()); 
	            //System.out.println("  Hash: " + employee.getFilehash());
            	}
	        }
//            else if ((newhash.equals(filehash)) && (fname.equals(name))) 
//            {
//            	System.out.println("Changes not made in any File");
//            }
            //System.out.println("FROM");

         }
         tx.commit();
      }
      catch (HibernateException e) 
      {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      finally 
      {
         session.close(); 
      }
   }
}