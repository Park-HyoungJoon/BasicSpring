package kr.inhatc.spring.mataverse.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import kr.inhatc.spring.mataverse.entity.Metaverse;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetaverseDTO {
	private int MId;
	private int UId;
	private String Title;
	private Date StartDate;
	private Date EndDate;
	

	

}
