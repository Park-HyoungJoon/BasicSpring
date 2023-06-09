package kr.inhatc.spring.video_board.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.inhatc.spring.video_board.dto.Video_BoardDto;

@Component
public class UploadFile {
	
	@Value("${resource}")
	private String path;
	
	public String makeDir() {
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator +cal.get(Calendar.YEAR) + "";
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		if(!new File(path+datePath, datePath).exists()) {
			new File(path, yearPath).mkdir();
			new File(path, monthPath).mkdir();
			new File(path, datePath).mkdir();
		}
		return datePath;
	}
	
	public void fildUpload(Video_BoardDto dto,MultipartFile file) throws IOException {
		
		UUID uuid = UUID.randomUUID();
		
		String fileName = File.separator + uuid + "_" + file.getOriginalFilename();
		
		String dir = makeDir();
		
		File uploadFile = new File(path + dir, fileName);
		
		uploadFile.createNewFile();
		
		FileCopyUtils.copy(file.getBytes(), uploadFile);
		
		dto.setFilename(fileName);
		dto.setFilepath(File.separator + "upload" + dir + fileName);
		
	}
}
