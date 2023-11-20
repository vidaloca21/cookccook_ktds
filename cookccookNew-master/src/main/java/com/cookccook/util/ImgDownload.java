package com.cookccook.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cookccook.app.article.vo.RecipeVO;
import com.cookccook.app.beans.FileHandler;
import com.cookccook.app.beans.FileHandler.StoredFile;
import com.cookccook.util.dao.AttachDAO;
import com.cookccook.util.vo.AttachVO;

@Component
public class ImgDownload {
	
	@Autowired
	private FileHandler fileHandler;
	@Autowired
	private AttachDAO attachDAO;
	
	private String imgFilePath = "C:/recipeImages/";              // 저장할 파일명 (경로 포함)
	private String imgFormat = "jpg";
	
	@Transactional
	public int createNewAttachImg() {
		List<RecipeVO> recipeList = attachDAO.getAllImageUrl();
		int insertCount = 0;
		for (RecipeVO recipe: recipeList) {
			String imgUrl = recipe.getAttImgSmall();
			String fileName = imgUrl.substring( imgUrl.lastIndexOf("/")+1 );
			File imgFile = getImageFromUrl(imgUrl, imgFilePath+fileName, imgFormat);
			if (imgFile != null) {
				AttachVO attachVO = createNewAttachVO(imgFile);
				attachVO.setFileName(fileName);
				attachVO.setOriginalId(recipe.getArticleId());
				insertCount += attachDAO.createNewAttach(attachVO);
			}
		}
		
		return insertCount;
	}
	
	@Transactional
	public int createNewPostAttachImg() {
		List<RecipeVO> recipeList = attachDAO.getAllPostImageUrl();
		int insertCount = 0;
		for (RecipeVO recipe: recipeList) {
			String imgUrl = recipe.getAttImgSmall();
			String fileName = imgUrl.substring( imgUrl.lastIndexOf("/")+1 );
			File imgFile = getImageFromUrl(imgUrl, imgFilePath+fileName, imgFormat);
			if (imgFile != null) {
				AttachVO attachVO = createNewAttachVO(imgFile);
				attachVO.setFileName(fileName);
				attachVO.setOriginalId(recipe.getArticleId());
				insertCount += attachDAO.createNewAttach(attachVO);
			}
		}
		
		return insertCount;
	}
	
	@Transactional
	public int updateRecipeTitleImg() {
		List<AttachVO> attachList = attachDAO.getAllAttachList();
		RecipeVO recipeVO = new RecipeVO();
		int updateCount = 0;
		for (AttachVO attachVO: attachList) {
			recipeVO.setArticleId(attachVO.getOriginalId());
			recipeVO.setTitleImgPath(attachVO.getOriginFileName());
			updateCount += attachDAO.addFilePathToRecipeVO(recipeVO);
		}
		return updateCount;
	}
	
	public File getImageFromUrl(String imgUrl, String imgFilePath, String imgFormat) {
		try
		{
			// Image 가져오기
			URI imgUri = new URI(imgUrl);
			BufferedImage image = ImageIO.read(imgUri.toURL());
			// Image 저장할 파일
			File imgFile = new File(imgFilePath);
			// Image 저장
			ImageIO.write(image, imgFormat, imgFile);
			return imgFile;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public AttachVO createNewAttachVO(File file) {
		AttachVO attachVO = new AttachVO();
		if(file == null) {
			return null;
		}
		else {
			FileHandler.StoredFile storedFile = fileHandler.storeFile(file);
			attachVO.setFileName(storedFile.getFileName());
			attachVO.setOriginFileName(storedFile.getRealFileName());
			attachVO.setFilepath(storedFile.getRealFilePath());
			return attachVO;
		}
	}

}
