package kr.inhatc.spring.user.dto;

import kr.inhatc.spring.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {
	private Long id;
	private String title;
	private String contents;
	private int hitCnt;
	private String uploadDate;
	private String creator;
	
	
	public UserDto(final User entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.contents = entity.getContents();
		this.hitCnt = entity.getHitCnt();
		this.uploadDate = entity.getUploadDate();
		this.creator = entity.getCreator();
	}
	
	public User toEntity() {
		return User.builder()
				.title(title)
				.contents(contents)
				.hitCnt(0)
				.uploadDate(uploadDate)
				.creator(creator)
				.build();
	}
}
