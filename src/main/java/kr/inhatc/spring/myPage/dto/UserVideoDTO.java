package kr.inhatc.spring.myPage.dto;

import java.time.LocalDateTime;
import java.util.Date;

import kr.inhatc.spring.myPage.entity.UserVideo;
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
public class UserVideoDTO {
	
	private int UVId;
	private String UVTitle;
	private LocalDateTime UVTime;
	private Date UVUpload;
	private String UVContents;
	private int UVHitCnt;
	private int UId;

	public UserVideoDTO(final UserVideo entity) {
		this.UVId = entity.getUVId();
		this.UVTime = entity.getUVTime();
		this.UVTitle = entity.getUVTitle();
		this.UVUpload = entity.getUVUpload();
		this.UId = entity.getUId();
		this.UVContents = entity.getUVContents();
		this.UVHitCnt = entity.getUVHitCnt();
	}
	public static UserVideo toEntity(final UserVideoDTO dto) {
		return UserVideo.builder()
				.UId(dto.getUId())
				.UVId(dto.getUVId())
				.UVTime(dto.getUVTime())
				.UVUpload(dto.getUVUpload())
				.UVContents(dto.getUVContents())
				.UVHitCnt(dto.getUVHitCnt())
				.build();
	}
}
