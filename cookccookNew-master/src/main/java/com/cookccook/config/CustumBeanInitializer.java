package com.cookccook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cookccook.app.beans.FileHandler;
import com.cookccook.app.member.dao.MemberDAO;
import com.cookccook.security.SHA;
import com.cookccook.security.SecuritySHA;
import com.cookccook.security.SecurityUserDetailsService;

@SpringBootConfiguration
public class CustumBeanInitializer {
	
	@Autowired
    private MemberDAO memberDAO;
	
    @Value("${app.multipart.base-dir:C:/uploadFiles}")
    private String baseDir;

    @Value("${app.multipart.obfuscation.enable:false}")
    private boolean enableObfuscation;

    @Value("${app.multipart.obfuscation.hide-ext.enable:false}")
    private boolean enableObfuscationHideExt;

    @Bean
    public SHA sha(){
        return new SHA();
    }
    
    @Bean
    public SecuritySHA securitySHA() {
    	return new SecuritySHA();
    }
    
    @Bean
    public FileHandler fileHandler() {
    	FileHandler fileHandler = new FileHandler();
    	fileHandler.setBaseDir(baseDir);
    	fileHandler.setEnableObfuscation(enableObfuscation);
    	fileHandler.setEnableObfuscationHideExt(enableObfuscationHideExt);
    	return fileHandler;
    }
    
    @Bean
    public UserDetailsService userDetailsService(){return new SecurityUserDetailsService(memberDAO);}

    @Bean
    public PasswordEncoder passwordEncoder() {return new SecuritySHA();}
}
