package kr.inhatc.spring.user_video.dto;

import java.time.LocalDateTime;
import java.util.Date;


import kr.inhatc.spring.user_video.entity.user;
import kr.inhatc.spring.user_video.entity.User_Video;
import kr.inhatc.spring.user_video.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User_VideoDTO {
	private int uvPNum;
	private user user;
	private Video Video_NUM;
	private int PlayTime;
	private int Overlap;
	private Date PlayOn;
	private Date PlayOff;
  
	public User_VideoDTO(final User_Video entity) {
		this.uvPNum = entity.getUvPNum();
		this.user = entity.getUser();
		this.Video_NUM = entity.getVideo_NUM();
		this.PlayTime = entity.getPlayTime();
		this.Overlap = entity.getOverlap();
		this.PlayOn = entity.getPlayOn();
		this.PlayOff = entity.getPlayOff();
	}
	public static User_Video toEntity(final User_VideoDTO dto) {
		return User_Video.builder()
				.uvPNum(dto.getUvPNum())
				.user(dto.getUser())
				.Video_NUM(dto.getVideo_NUM())
				.PlayTime(dto.getPlayTime())
				.Overlap(dto.getOverlap())
				.PlayOn(dto.getPlayOn())
				.PlayOff(dto.getPlayOff())
				.build();
	}
}

