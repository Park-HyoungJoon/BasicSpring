package kr.inhatc.spring.video_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.inhatc.spring.video_board.entity.Video_Img;

@Repository
public interface Video_ImgRepository extends JpaRepository<Video_Img, Long> {

}
