package kr.inhatc.spring.video_board.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.inhatc.spring.video_board.constant.AttachmentType;
import kr.inhatc.spring.video_board.entity.Attachment;

public interface AttachmentService {
	List<Attachment> saveAttachments(Map<AttachmentType, List<MultipartFile>> multipartFileListMap) throws IOException;
	
	Map<AttachmentType, List<Attachment>> findAttachments();
}
