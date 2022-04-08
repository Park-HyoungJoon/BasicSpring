package kr.inhatc.spring.user.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
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
	private String uploadDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	
	@Column(name = "user_creator")
	private String creator = "admin";
	

	
	
	/**
	 * 조회수 증가
	 */
	public void increaseHits() {
		this.hitCnt++;
	}
	
	
}
