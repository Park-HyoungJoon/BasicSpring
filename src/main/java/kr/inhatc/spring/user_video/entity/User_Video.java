package kr.inhatc.spring.user_video.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@OneToMany
//@ManyToOne
public class User_Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uvPNum;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id")
	//private int id;
	private user user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Video_NUM")
	//private int Video_NUM;
	private Video Video_NUM;
	private int PlayTime;
	@Column
	private int Overlap;
	@Temporal(TemporalType.TIMESTAMP)
	private Date PlayOn;
	@Temporal(TemporalType.TIMESTAMP)
	private Date PlayOff;
}
