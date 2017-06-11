package services;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aws.services.user.bean.UserDetails;

@Resource
@Path("/test")
public class RestfulService extends SqlConnection {
	private RestfulService service;
	private UserDetails userDetails;
	//private DBParameters parameters;
	private ResultSet rs;

	@GET
	@Path("/allUserDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDetails getDetails(){
		userDetails = new UserDetails();
		service =new RestfulService();
		service.execute();
		//parameters = new DBParameters();
		//parameters.setSqlStatement("select * from USER_PROFILE");
		rs = service.getResultSet();
		try {
			while(rs.next()){
				userDetails.setUserID(rs.getString("USERID"));
				userDetails.setFirstName(rs.getString("FIRST_NAME"));
				userDetails.setLastName(rs.getString("LAST_NAME"));
				userDetails.setPersID(rs.getString("PERS_IDENTIFICATION_NO"));
				userDetails.setAddressLine1("ADDRESS_LINE1");
				userDetails.setAddressLine2("ADDRESS_LINE2");
				userDetails.setCity(rs.getString("CITY"));
				userDetails.setState(rs.getString("STATE"));
				userDetails.setCountry(rs.getString("COUNTRY"));
				userDetails.setPincode(rs.getInt("PINCODE"));
				userDetails.setEmailID(rs.getString("EMAIL_ID"));
				userDetails.setMobileNumber(rs.getInt("MOBILE_NO"));
			}
			rs.close();
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}

		return userDetails;

	}
}