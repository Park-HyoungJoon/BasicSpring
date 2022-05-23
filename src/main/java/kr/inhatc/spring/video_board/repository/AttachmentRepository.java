package kr.inhatc.spring.video_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.video_board.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

}
