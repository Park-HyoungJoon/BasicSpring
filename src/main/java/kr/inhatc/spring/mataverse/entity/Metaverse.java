package kr.inhatc.spring.mataverse.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Metaverse {

	@Id  //PK Annotation
	@GeneratedValue
	@Column(name = "M_Id")
	private int M_Id;
	
	@Column(name = "UId")
	private int UId;
	
	@Column(name = "StartDate")
	private Date StartDate;
	
	@Column (name = "EndDate")
	private Date EndDate;
	
	@Column(name = "Title")
	private String Title;
	

}
