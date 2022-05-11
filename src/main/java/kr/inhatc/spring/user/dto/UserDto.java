package kr.inhatc.spring.user.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import kr.inhatc.spring.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private Long id;
	private String Email;
	private String PW;
	private String Nick;
	private String Self;
	private String Role;
	
	
	public UserDto(final User entity) {
		this.id = entity.getId();
		this.Email = entity.getEmail();
		this.PW = entity.getPW();
		this.Nick = entity.getNick();
		this.Self = entity.getSelf();
		this.Role = entity.getRole();
	}
	
	public User toEntity() {
		return User.builder()
				.id(id)
				.Email(Email)
				.PW(PW)
				.Role(Role)
				.Self(Self)
				.Nick(Nick)
				.build();
	}
}
