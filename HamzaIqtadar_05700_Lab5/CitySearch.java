import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

public class CitySearch 
{
	static Connection connection = null;
	static PreparedStatement prep_statement = null;
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
//		try {
//			upload();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the city name:");
		//s.nextLine();
		String city=s.nextLine();
		//System.out.println("now");
		latSearch(city);
//		System.out.println("Enter the latitude:");
//		double latitude=s.nextDouble();
//		System.out.println("Enter the longitude:");
//		double longitude=s.nextDouble();
//		System.out.println("The nearby cities are:");
//		citySearch(latitude,longitude);
	}
//	static void upload() throws ClassNotFoundException, SQLException, IOException
//	{
//			      //Register JDBC driver
//		        Class.forName("com.mysql.jdbc.Driver");
//
//				connection = DriverManager.getConnection("jdbc:mysql://localhost/aplab5","root","seecs@123");
//
//		            //Execute a query
//				String sql = "Insert into cities(country,region,city,latitude,longitude) values(?,?,?,?,?)";
//				prep_statement = connection.prepareStatement(sql);
//				FileOutputStream fileOut;
//				String line;
//				String csvSplitBy=",";
//				try 
//				{
//					FileReader fileIn = new FileReader("C:\\Users\\Hamza\\Desktop\\GeoLiteCity-Location.csv");
//					BufferedReader out = new BufferedReader(fileIn);
//					out.readLine();
//					out.readLine();
//					
//					while((line=out.readLine())!=null)
//					{	
//						String[] city=line.split(csvSplitBy);
//						prep_statement.setString(1,city[1]);
//			            prep_statement.setString(2,city[2]); 
//			            prep_statement.setString(3,city[3]);
//			            //prep_statement.setInt(4,Integer.parseInt(city[4]));  
//			            prep_statement.setDouble(4,Double.parseDouble(city[5]));
//			            prep_statement.setDouble(5,Double.parseDouble(city[6]));
//			            //prep_statement.setInt(6,Integer.parseInt(city[7]));
//			            //prep_statement.setInt(7,Integer.parseInt(city[8]));
//			            int rows = prep_statement.executeUpdate();
//					}
//					
//				} 
//				catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	}
	
	
//	static void citySearch(double lat,double lon) throws ClassNotFoundException, SQLException
//	{
//		try 
//		{
//			Class.forName("com.mysql.jdbc.Driver");
//			connection = DriverManager.getConnection("jdbc:mysql://localhost/aplab5","root","seecs@123");
//
//	        String sql = "SELECT id, firstname, lastname, age FROM Employees";
//	        ResultSet rs = prep_statement.executeQuery(sql);
//	        int i=0;
//	//Extract data from result set
//	        while (rs.next()) {
//	            //Retrieve by column name & Display values
//	            System.out.println(i+"-" + rs.getString("city"));
//	            i++;
//	        }
//	       
//	//Clean-up environment
//	        rs.close();
//	        prep_statement.close();
//	        connection.close();
//		} catch (ClassNotFoundException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		//return null;
//	}
	static void latSearch(String c) throws SQLException
	{
		//System.out.println("now");
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/aplab5","root","seecs@123");
	       // String sql = ;
	        PreparedStatement prep_statement = connection.prepareStatement("SELECT * FROM cities WHERE city=?;");
	        prep_statement.setString(1,c);
	        ResultSet rs = prep_statement.executeQuery();
	//Extract data from result set
        	//System.out.println("here");

	        while (rs.next()) {
	            //Retrieve by column name & Display values
	        	double lat=rs.getDouble("latitude");
	        	double lon=rs.getDouble("longitude");
	            System.out.println("Latitude:" + lat);
	            System.out.println("Longitude:" + lon);
	        }
	//Clean-up environment
	        rs.close();
	        prep_statement.close();
	        connection.close();
		} catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
