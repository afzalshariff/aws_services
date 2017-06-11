package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SqlConnection {
	private DBParameters dbParameters;
	private Connection con = null;
	String sql="";
	ResultSet rs= null; 
	
	public void execute() {
		
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
		} catch (Exception e) {
			System.out.println("Error While connecting to Driver");
			e.printStackTrace();
		}

		try {
			
			dbParameters = new DBParameters();
			dbParameters.setUrl("jdbc:mysql://myrdsinstance.c4vfq4x8xx4n.us-west-2.rds.amazonaws.com:3306/myrdsinstance");
			dbParameters.setUsername("myrdsinstance");
			dbParameters.setPassword("myrdsinstance");
			String url = dbParameters.getUrl();
			String username = dbParameters.getUsername();
			String password = dbParameters.getPassword();
			con = DriverManager.getConnection(url,username,password);
		}
		catch (Exception e) {
			System.out.println("Unable to fetch the parameters to establish DataBase Connection");
			e.printStackTrace();

		}
	}

	public ResultSet getResultSet() {
		if(con != null) {
			try{
				Statement stm = con.createStatement();
				dbParameters.setSqlStatement("select * from USER_PROFILE");
				sql = dbParameters.getSqlStatement();
				rs = stm.executeQuery(sql);
				//con.close();
			} 

			catch(SQLException e){
				System.out.println("Connection Failed! Please Check console");
				e.printStackTrace();
			}


		}
		return rs;
	}
}