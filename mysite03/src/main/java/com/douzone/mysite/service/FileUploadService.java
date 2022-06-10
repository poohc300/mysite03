package com.douzone.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	private static String SAVE_PATH = "/mysite-uploads";
	private static String URL_BASE = "/assets/gallery";
	
	public String restore(MultipartFile multipartFile) {
		String url = null;
		try {
			if(multipartFile.isEmpty()) {
				return url;
			}
			
			File uploadDirectory = new File(SAVE_PATH);
			if(!uploadDirectory.exists()) {
				uploadDirectory.mkdirs();
			}
			
			String originFileName = multipartFile.getOriginalFilename();
			String extName = originFileName.substring(originFileName.lastIndexOf('.')+1);
			String saveFilename = generateSaveFilename(extName);
			Long fileSize = multipartFile.getSize();
			
			System.out.println("################" + originFileName);
			System.out.println("################" + saveFilename);
			System.out.println("################" + fileSize);
			
			byte[] data = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH +"/"+  saveFilename);	
			os.write(data);
			os.close();
			
			url = StringUtils.cleanPath(URL_BASE + "/" + saveFilename);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return url;
	}
	
	private String generateSaveFilename(String extName) {
		String filename = "";
		Calendar calendar = Calendar.getInstance();
		
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}
	
	
}
