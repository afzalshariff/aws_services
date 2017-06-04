package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aws.services.user.bean.UserDetails;

@Resource
@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public class RestfulService {
	
	private 	UserDetails details;
	Connection con = null;

	@GET
	public UserDetails getDetails(){

		try{
			Class.forName("com.mysql.jdbc.Driver"); 
		} catch (Exception e) {
			System.out.println("Error While connecting to Driver");
			e.printStackTrace();
		}
		try {
			String url ="jdbc:mysql://myrdsinstance.c4vfq4x8xx4n.us-west-2.rds.amazonaws.com:3306/myrdsinstance";
			String username = "myrdsinstance";
			String password = "myrdsinstance";
			con = DriverManager.getConnection(url,username,password);

		} catch(SQLException e){
			System.out.println("Connection Failed! Please Check console");
			e.printStackTrace();
		}


		if(con != null) {
			try{
				Statement stm = con.createStatement();
				String sql = "select * from USER_PROFILE";
				ResultSet rs = stm.executeQuery(sql);
				while(rs.next()){
					details = new UserDetails();
					details.setUserID(rs.getString("USERID"));

				}
				con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}

		}
		return details;
	}
}
