package kr.inhatc.spring.video_board.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import kr.inhatc.spring.user.entity.Basic;
import kr.inhatc.spring.video_board.entity.Video_Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video_BoardDto {
	private Long id;
	private String title;
	private String contents;
	private int hitCnt;
	private String uploadDate;
	private String creator;
	
	
	public Video_BoardDto(final Video_Board entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.contents = entity.getContents();
		this.hitCnt = entity.getHitCnt();
		this.uploadDate = entity.getUploadDate();
		this.creator = entity.getCreator();
	}
	
	public Video_Board toEntity() {
		return Video_Board.builder()
				.title(title)
				.contents(contents)
				.hitCnt(0)
				.uploadDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
				.creator("admin")
				.build();
	}
}
