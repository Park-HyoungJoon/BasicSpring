package kr.inhatc.spring.video_board.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.inhatc.spring.video_board.config.FileStore;
import kr.inhatc.spring.video_board.constant.AttachmentType;
import kr.inhatc.spring.video_board.entity.Attachment;
import kr.inhatc.spring.video_board.repository.AttachmentRepository;

@Service
public class AttachmentServiceImpl implements AttachmentService{
	@Autowired
	private AttachmentRepository attachmentRepository;
	@Autowired
	private FileStore fileStore;
	
	public List<Attachment> saveAttachments(Map<AttachmentType, List<MultipartFile>> multipartFileListMap) throws IOException{
		List<Attachment> imageFiles = fileStore.storeFiles(multipartFileListMap.get(AttachmentType.IMAGE), AttachmentType.IMAGE);
		List<Attachment> generalFiles = fileStore.storeFiles(multipartFileListMap.get(AttachmentType.GENERAL), AttachmentType.GENERAL);
		List<Attachment> result = Stream.of(imageFiles,generalFiles)
				.flatMap(f -> f.stream())
				.collect(Collectors.toList());
		return result;
	}
	
	public Map<AttachmentType, List<Attachment>> findAttachments() {
		List<Attachment> attachments = attachmentRepository.findAll();
		Map<AttachmentType, List<Attachment>> result = attachments.stream()
				.collect(Collectors.groupingBy(Attachment::getAttachmentType));
		return result;
	}
}
