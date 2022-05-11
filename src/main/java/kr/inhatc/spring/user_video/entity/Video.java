package kr.inhatc.spring.user_video.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Video {
	@Id
	private int Video_NUM;
	@Column(nullable = false)
	private String Video_name;
	@Column(name="Video_time")
	private LocalDateTime Video_time;
	@Column
	private String Video_Genre;
	@Temporal(TemporalType.TIMESTAMP)
	private Date Video_Upload;

}
