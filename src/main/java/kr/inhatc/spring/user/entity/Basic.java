package kr.inhatc.spring.user.entity;

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
public class Basic {
	
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;
	
	@Column(name = "user_title")
	private String title;
	
	@Column(name = "user_contents", columnDefinition = "TEXT")
	private String contents;
	
	@Column(name = "user_hitCnt")
	private int hitCnt;
	
	@Column(name = "user_uploadDate")
	private String uploadDate;
	
	@Column(name = "user_creator")
	private String creator;
	

	
	
	/**
	 * 조회수 증가
	 */
	public void increaseHits() {
		this.hitCnt++;
	}
	
	
}
