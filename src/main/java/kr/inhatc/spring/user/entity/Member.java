package kr.inhatc.spring.user.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Member{
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 30, nullable = true)
	private String username;
	@Column(nullable = true)
	private String password;
	@Column(length = 100, nullable = true)
	private String email;
	private String role;
}
