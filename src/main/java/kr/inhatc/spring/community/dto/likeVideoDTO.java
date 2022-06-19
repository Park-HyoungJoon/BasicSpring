package kr.inhatc.spring.community.dto;

import java.time.LocalDateTime;
import java.util.Date;

import kr.inhatc.spring.community.entity.likeVideo;
import kr.inhatc.spring.myPage.dto.UserVideoDTO;
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
public class likeVideoDTO {
	int UId;
	int UVId;
	int likeV;
	int subscribe;
	
	public likeVideoDTO(likeVideo entity) {
		this.UId = entity.getUId();
		this.UVId = entity.getUVId();
		this.likeV = entity.getLikeV();
		this.subscribe = entity.getSubscribe();
	}
	
	public static likeVideo toEntity(likeVideoDTO dto) {
		return likeVideo.builder()
				.UId(dto.getUId())
				.UVId(dto.getUVId())
				.likeV(dto.getLikeV())
				.subscribe(dto.getSubscribe())
				.build();
	}

}
