package kr.inhatc.spring.video_board.dto;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.inhatc.spring.video_board.constant.AttachmentType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Board_FormDto {
	private Long id;
	private String title;
	private String contents;
	private int hitCnt;
	private String uploadDate;
	private String creator;
	private String type;
	//나중에 수정할거야!
	private String url1;
	private String url2;
	private String url3;
	private String url4;
	private String url5;
	private List<MultipartFile> imgageFiles;
	private List<MultipartFile> generalFiles;
	
	@Builder
	public Board_FormDto(Long id, String title, String contents, int hitCnt, String uploadDate, String creator,
			String type, String url1, String url2, String url3, String url4, String url5,
			List<MultipartFile> imgageFiles, List<MultipartFile> generalFiles) {
		super();
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.hitCnt = hitCnt;
		this.uploadDate = uploadDate;
		this.creator = creator;
		this.type = type;
		this.url1 = url1;
		this.url2 = url2;
		this.url3 = url3;
		this.url4 = url4;
		this.url5 = url5;
		this.imgageFiles = imgageFiles;
		this.generalFiles = generalFiles;
	}
	
	
//	public Video_BoardDto createVideo_Board() {
//		Map<AttachmentType, List<MultipartFile>> attachments = getAttachmentTypeListMap();
//		return Video_BoardDto.builder()
//		
//	}

	private Map<AttachmentType, List<MultipartFile>> getAttachmentTypeListMap() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
