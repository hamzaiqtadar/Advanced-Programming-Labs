
public class Employee {
   private int id;
   private String filename; 
   private String filepath;   
   private String foldername;
   private String filehash;

   public Employee() {}
   public Employee(String fname, String pname, String folname,String fhash ) {
      this.filename = fname;
      this.filename = pname;
      this.foldername = folname;
      this.filehash = fhash;
   }
   public int getId() {
      return id;
   }
   public void setId( int id ) {
      this.id = id;
   }
   public String getFileName() {
      return filename;
   }
   public void setFileName( String f_name ) {
      this.filename = f_name;
   }
   public String getPath() {
      return filepath;
   }
   public void setPath( String p_name ) {
      this.filepath = p_name;
   }
   public String getFolder() {
      return foldername;
   }
   public void setFolder( String fol_name ) {
      this.foldername = fol_name;
   }
   public String getHash() {
	      return filehash;
	   }
   public void setHash(String f_hash ) {
	      this.filehash = f_hash;
	   }
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
public String getFilepath() {
	return filepath;
}
public void setFilepath(String filepath) {
	this.filepath = filepath;
}
public String getFoldername() {
	return foldername;
}
public void setFoldername(String foldername) {
	this.foldername = foldername;
}
public String getFilehash() {
	return filehash;
}
public void setFilehash(String filehash) {
	this.filehash = filehash;
}
}
