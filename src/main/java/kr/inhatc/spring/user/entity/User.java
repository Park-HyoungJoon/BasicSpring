package kr.inhatc.spring.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
public class User {
	
	@Id
	@GeneratedValue
	@Column(name = "UId")
	private Long id;
	
	@Column
	private String Email;
	
	@Column
	private String PW;
	
	@Column
	private String Nick;
	
	@Column
	private String Self;
	
	@Column
	private String Role;
}
