package com.cookccook.app.article.web;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class ApiController {

    @GetMapping("/apitest")
    public Map<String, Object> callApiWithXml() {
		try {
			String apiKey = "46ccd8d3eb004109886b";
			String serviceName = "COOKRCP01";
			String dataType = "xml";
			String startToEndIdx = "/0/100";
			String apiUrl = "http://openapi.foodsafetykorea.go.kr/api/" + apiKey + "/" + serviceName + "/" 
																		+ dataType + "/" + startToEndIdx;
            URI uri = new URI(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) uri.toURL().openConnection();
			urlConnection.connect();
			
			BufferedInputStream bis = new BufferedInputStream(urlConnection.getInputStream());
	        BufferedReader br = new BufferedReader(new InputStreamReader(bis));
	        StringBuffer st = new StringBuffer();
	        String line;
	        while ((line = br.readLine()) != null) {
	            st.append(line);
	        }
	        JSONObject xmlJSONObj = XML.toJSONObject(st.toString());
	        Map<String, Object> recipeMap = new HashMap<>();
	        recipeMap = new ObjectMapper().readValue(xmlJSONObj.toString(), Map.class);
	        recipeMap.values().remove("");
	        return recipeMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
}