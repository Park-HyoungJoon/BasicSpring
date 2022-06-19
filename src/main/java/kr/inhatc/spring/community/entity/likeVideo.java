package kr.inhatc.spring.community.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import kr.inhatc.spring.myPage.entity.UserVideo;
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
public class likeVideo {
	@Id
	int UId;
	@Column
	int UVId;
	@Column
	int likeV;
	@Column
	int subscribe;
}
