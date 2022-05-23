package kr.inhatc.spring.video_board.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.multipart.MultipartFile;

import kr.inhatc.spring.user.entity.Basic;
import kr.inhatc.spring.video_board.constant.AttachmentType;
import kr.inhatc.spring.video_board.entity.Video_Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video_BoardDto {
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
	private Map<AttachmentType, List<MultipartFile>> attachmentFiles = new ConcurrentHashMap<>();
	
	public Video_BoardDto(Video_Board entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.contents = entity.getContents();
		this.hitCnt = entity.getHitCnt();
		this.uploadDate = entity.getUploadDate();
		this.creator = entity.getCreator();
		this.type = entity.getType();
		//나중에 수정할거야!
		this.url1 = entity.getUrl1();
		this.url2 = entity.getUrl2();
		this.url3 = entity.getUrl3();
		this.url4 = entity.getUrl4();
		this.url5 = entity.getUrl5();
		
	}
	
	//createBoard()
	public Video_Board toEntity() {
		return Video_Board.builder()
				.title(title)
				.contents(contents)
				.hitCnt(0)
				.uploadDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
				.creator("admin")
				.type(type)
				.url1(url1)
				.url2(url2)
				.url3(url3)
				.url4(url4)
				.url5(url5)
				.build();
	}
}
