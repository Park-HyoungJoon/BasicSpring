package kr.inhatc.spring.mataverse.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Metaverse {

	@Id  //PK Annotation
//	PK 자동 증가하게 해준다.(Auto increment)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int M_Id;
	@Column
	private Date StartDate;
	@Column 
	private Date EndDate;
	@Column
	private String Title;
	

}
