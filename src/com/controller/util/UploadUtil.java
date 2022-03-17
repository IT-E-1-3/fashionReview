package com.controller.util;
/*
 * 사진 파일 저장 클래스
 * 작성자 : 김보경, 차성호
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

public class UploadUtil {

	String uploadPath;
	private ServletContext app;
	
	// 생성 메서드 
	public static UploadUtil create(ServletContext app) {
		
		UploadUtil uploadUtil = new UploadUtil();
		uploadUtil.setApp(app);
		//업로드 경로 설정(절대경로)
		uploadUtil.setUploadPath(app.getRealPath("/upload"));
		
		return uploadUtil;
	}
	
	private void setApp(ServletContext app) {
		this.app = app;
	}
	private void setUploadPath(String realPath) {
		this.uploadPath = realPath;
	}
	
	// 파일 저장 
	public String saveFiles(Part filePart, String folderPath) {
	
		//저장될 폴더 경로
		String realPath = this.uploadPath + File.separator + folderPath;
		//저장될 파일(경로포함)
		String filePath = realPath + File.separator +filePart.getSubmittedFileName();
		
		try(
			//InputStream객체 생성
			InputStream fis = filePart.getInputStream();
			//OutputStream객체 생성
			OutputStream fos = new FileOutputStream(filePath);)
		{
			//이미지 파일 저장할 byte배열 선언
			byte[] buf = new byte[1024];
			int len = 0;
			
			while((len = fis.read(buf, 0, 1024)) != -1)
				fos.write(buf, 0, len);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
	
	/*/upload 하위 폴더 경로 생성 */
	public String createFileParth() {
		LocalDateTime date = LocalDateTime.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String[] paths = formatter.format(date).split("/");
		
		String result = paths[0] + File.separator + paths[1] + File.separator + paths[2];
		
		createFolders(result);
		
		return result; 
	}
	
	private void createFolders(String paths) {
		
		File folders = new File(uploadPath, paths);
		
		if(!folders.exists())
			folders.mkdirs();
	}
}