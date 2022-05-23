package kr.inhatc.spring.video_board.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kr.inhatc.spring.video_board.constant.AttachmentType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Attachment {
	@Id
	@GeneratedValue
	private Long id;
	private String originFilename;
	private String storeFilename;
	@Enumerated(EnumType.STRING)
	private AttachmentType attachmentType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "video_id")
	private Video_Board video_Board;
	
	@Builder
	public Attachment(Long id, String originFilename, String storePath, AttachmentType attachmentType) {
		this.id = id;
		this.originFilename = originFilename;
		this.storeFilename = storePath;
		this.attachmentType = attachmentType;
	}
	
}
