package kr.inhatc.spring.video_board.dto;

import java.net.URLEncoder;

import org.modelmapper.ModelMapper;

import kr.inhatc.spring.video_board.entity.Video_Img;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Video_ImgDto {
	private String uuid;
	private String imgName;
	private String path;
	
	public String getImageURL() {
		try {
			return URLEncoder.encode(path+"/"+uuid+"_"+imgName,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getThumbnailURL() {
		try {
			return URLEncoder.encode(path+"/s_"+uuid+"_"+imgName,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
