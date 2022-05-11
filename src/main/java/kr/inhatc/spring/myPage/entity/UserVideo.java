package kr.inhatc.spring.myPage.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class UserVideo {
	@Id
	private int UVId;
	@Column
	private String UVTitle;
	@Column
	private LocalDateTime UVTime;
	@Column
	private Date UVUpload;

}
