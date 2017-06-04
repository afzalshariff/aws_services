package com.aws.services.user.service;

import com.aws.services.user.bean.UserDetails;

/**
 * @author Afzal-Shariff
 * This interface further calls USerDetailsServiceImpl
 */
public interface UserDetailsService {
	
	public UserDetails getDetails(String userID);

}
