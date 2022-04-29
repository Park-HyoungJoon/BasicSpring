package kr.inhatc.spring.user_video.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.user_video.entity.Video;

public interface VideoRepository extends JpaRepository<Video, Integer>{

}
