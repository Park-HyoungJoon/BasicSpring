package kr.inhatc.spring.video_board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video_Board {
	
	@Id
	@GeneratedValue
	@Column(name = "video_id")
	private Long id;
	
	@Column(name = "video_title")
	private String title;
	
	@Column(name = "video_contents", columnDefinition = "TEXT")
	private String contents;
	
	@Column(name = "video_hitCnt")
	private int hitCnt;
	
	@Column(name = "video_uploadDate")
	private String uploadDate;
	
	@Column(name = "video_creator")
	private String creator;
	 

	
	
	/**
	 * 조회수 증가
	 */
	public void increaseHits() {
		this.hitCnt++;
	}
	
	
}
