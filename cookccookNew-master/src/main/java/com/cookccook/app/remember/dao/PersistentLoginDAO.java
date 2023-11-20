package com.cookccook.app.remember.dao;

import java.util.Date;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

public interface PersistentLoginDAO extends PersistentTokenRepository {
	
	public void createNewToken(PersistentRememberMeToken token);
    
	public PersistentRememberMeToken getTokenForSeries(String seriesId);
    
	public void removeUserTokens(String username);
    
	public void updateToken(String series, String tokenValue, Date lastUsed);
}
