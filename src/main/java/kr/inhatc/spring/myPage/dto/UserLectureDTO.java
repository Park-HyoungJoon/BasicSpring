  package kr.inhatc.spring.myPage.dto;

import java.time.LocalDateTime;
import java.util.Date;

import kr.inhatc.spring.myPage.entity.UserLecture;
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
public class UserLectureDTO {
	private int ULPId;
	private int LPId;
	private int UId;
	private Date ULUpload;

	public UserLectureDTO(final UserLecture entity) {
		this.ULPId = entity.getULPId();
		this.LPId = entity.getLPId();
		this.UId = entity.getUId();
		this.ULUpload = entity.getULUpload();
	}
	public static UserLecture toEntity(final UserLectureDTO dto) {
		return UserLecture.builder()
				.ULPId(dto.getULPId())
				.LPId(dto.getLPId())
				.UId(dto.getUId())
				.ULUpload(dto.getULUpload())
				.build();
	}
}
