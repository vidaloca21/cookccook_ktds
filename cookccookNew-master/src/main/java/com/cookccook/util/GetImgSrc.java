package com.cookccook.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.cookccook.app.beans.FileHandler;
import com.cookccook.app.member.vo.MemberVO;

@Component
public class GetImgSrc {

	@Autowired
	private FileHandler fileHandler;

	public GetImgSrc(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}
	
    public ResponseEntity<byte[]> getImageFileSrc(String fileUUID, String filename) {
        File file = fileHandler.getStoredFile(fileUUID);
        if (file == null) {
            throw new IllegalArgumentException("파일이 존재하지 않습니다.");
        }
        try {
            byte[] imageBytes = Files.readAllBytes(file.toPath());
            HttpHeaders header = new HttpHeaders();
	        header.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=" + filename);
            return new ResponseEntity<>(imageBytes, header, HttpStatus.OK);
        } catch (IOException e) {
            throw new IllegalArgumentException("이미지를 불러올 수 없습니다.");
        }
        
        
    }
	    
}
