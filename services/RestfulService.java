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
public class RestfulService {

	Connection con = null;

	@GET
	@Path("/details")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDetails getDetails(){
		UserDetails details = new UserDetails();
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
				String	USERID = rs.getString("USERID");
					//PASSWORD FIRST_NAME LAST_NAME PERS_IDENTIFICATION_NO ADDRESS_LINE1 ADDRESS_LINE2 CITY      STATE     COUNTRY PINCODE EMAIL_ID     MOBILE_NO LST_UPDT_TS
					
					details.setUserID(USERID);

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
