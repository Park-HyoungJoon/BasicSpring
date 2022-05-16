package kr.inhatc.spring.myPage.dto;

import java.util.Collection;
import java.util.Date;

import kr.inhatc.spring.myPage.entity.UserFriend;
import kr.inhatc.spring.myPage.entity.UserLecture;
import kr.inhatc.spring.user.entity.User;
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
public class UserFriendDTO {
	private int UFPId;
	private int OtherUId;
	private int UId;

	public UserFriendDTO(final UserFriend entity) {
		this.UFPId = entity.getUFPId();
		this.OtherUId = entity.getOtherUId();
		this.UId = entity.getUId();
	}
	public static UserFriend toEntity(final UserFriendDTO dto) {
		return UserFriend.builder()
				.UFPId(dto.getUFPId())
				.OtherUId(dto.getOtherUId())
				.UId(dto.getUId())
				.build();
	}
}
