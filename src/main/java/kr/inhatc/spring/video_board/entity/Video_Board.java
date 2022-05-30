package kr.inhatc.spring.video_board.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
	 
	@Column(name = "video_type")
	private String type;
	
	//샘플! 나중에 수정할거야!
	@Column(name = "video_url1", columnDefinition = "TEXT")
	private String url1;
	@Column(name = "video_url2", columnDefinition = "TEXT")
	private String url2;
	@Column(name = "video_url3", columnDefinition = "TEXT")
	private String url3;
	@Column(name = "video_url4", columnDefinition = "TEXT")
	private String url4;
	@Column(name = "video_url5", columnDefinition = "TEXT")
	private String url5;
	
	
	
	
	/**
	 * 조회수 증가
	 */
	public void increaseHits() {
		this.hitCnt++;
	}
	
	
}
