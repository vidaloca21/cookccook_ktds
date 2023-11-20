package com.cookccook.app.remember.dao;

import java.util.Date;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.stereotype.Repository;

import com.cookccook.app.remember.PersistentLogin;

@Repository
public class PersistentLoginDAOImpl extends SqlSessionDaoSupport implements PersistentLoginDAO {
    
	@Autowired
    @Override
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }
	
	@Override
	public void createNewToken(PersistentRememberMeToken token) {
	    PersistentLogin persistentLogin = new PersistentLogin();
	    persistentLogin.setSeries(token.getSeries());
	    persistentLogin.setMemberId(token.getUsername());
	    persistentLogin.setToken(token.getTokenValue());
	    persistentLogin.setLastUsed(token.getDate());
	    getSqlSession().insert("createNewToken", persistentLogin);
	}
	
	//  @Override
	//  public PersistentRememberMeToken getTokenForSeries(String seriesId) {
	//      PersistentLogin persistentLogin = getSqlSession().selectOne("getTokenForSeries", seriesId);
	//      return new PersistentRememberMeToken(persistentLogin.getMemberId(), persistentLogin.getSeries(), persistentLogin.getToken(), persistentLogin.getLastUsed());
	//  }
  

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
	    PersistentLogin persistentLogin = getSqlSession().selectOne("getTokenForSeries", seriesId);
        
        if (persistentLogin == null) {
            // persistentLogin이 null인 경우의 처리
            logger.error("No persistent login found for series id: " + seriesId);
            return null;
        } else {
            return new PersistentRememberMeToken(persistentLogin.getMemberId(), persistentLogin.getSeries(), persistentLogin.getToken(), persistentLogin.getLastUsed());
        }
	}

	@Override
	public void removeUserTokens(String username) {
	    getSqlSession().delete("removeUserTokens", username);
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
	    PersistentLogin persistentLogin = new PersistentLogin();
	    persistentLogin.setSeries(series);
	    persistentLogin.setToken(tokenValue);
	    persistentLogin.setLastUsed(lastUsed);
	    getSqlSession().update("updateToken", persistentLogin);
	}
}