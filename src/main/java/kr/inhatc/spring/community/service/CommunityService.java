package kr.inhatc.spring.community.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import kr.inhatc.spring.community.repository.likeVideoRepository;
import kr.inhatc.spring.myPage.dto.UserVideoDTO;
import kr.inhatc.spring.myPage.entity.QUserVideo;
import kr.inhatc.spring.myPage.entity.UserVideo;
import kr.inhatc.spring.myPage.repository.UserVideoRepository;
import kr.inhatc.spring.video_board.util.PageRequestDto;
import kr.inhatc.spring.video_board.util.PageResultDto;

@Service
public class CommunityService {

	@Autowired
	private UserVideoRepository uvRepository;
	
	@Autowired
	private likeVideoRepository lvRepository;
	
	public Page<UserVideoDTO> search(String keyword, Pageable pageable) {
		Page<UserVideo> list = uvRepository.findByUVTitleContaining(keyword,pageable);
		Page<UserVideoDTO> listDto = list.map(UserVideoDTO::new);	
		return listDto;
	}
	private BooleanBuilder getSearch(PageRequestDto requestDto) {
		
		String type = requestDto.getType();
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		
		QUserVideo qVideo_Board = QUserVideo.userVideo;
		
		String keyword = requestDto.getKeyword();
		
		BooleanExpression expression = qVideo_Board.UVId.gt(0L);		// id > 0 인 조건만 생성함!
		
		booleanBuilder.and(expression);
		
		if(type == null || type.trim().length() == 0) {		// 검색 조건이 없는 경우
			return booleanBuilder;
		}
		
		//OR 연산에 의거해서 검색 조건을 작성하기
		BooleanBuilder conditionBuilder = new BooleanBuilder();
		
		if(type.contains("t")) {
			conditionBuilder.or(qVideo_Board.UVTitle.contains(keyword));
		}
		if(type.contains("c")) {
			conditionBuilder.or(qVideo_Board.UVContents.contains(keyword));
		}
		
		//모든 조건 통합
		booleanBuilder.and(conditionBuilder);
		
		return booleanBuilder;
		
	}
	
	public PageResultDto<UserVideoDTO,UserVideo> getList(PageRequestDto requestDto) {
		
		//역순으로 페이지 정보 가져오기 (내림차순으로 페이지 출력하기)
		Pageable pageable = requestDto.getPageable(Sort.by("UVId").descending());
		
		// 검색 조건 처리
		BooleanBuilder booleanBuilder = getSearch(requestDto);
		
		// QuestDSL 사용 - 페이지 정보를 통한 페이지 결과 가져오기
		Page<UserVideo> result = uvRepository.findAll(booleanBuilder,pageable);
		// 엔티티를 DTO로 변환해주는 기능
		Function<UserVideo, UserVideoDTO> fn = (entity -> entityToDto(entity));
		
		//result는 UserVideo의 모든 정보
		return new PageResultDto<>(result, fn);
	}

	private UserVideoDTO entityToDto(UserVideo entity) {
		UserVideoDTO dto = UserVideoDTO.builder()
				.UId(entity.getUId())
				.UVId(entity.getUVId())
				.UVTime(entity.getUVTime())
				.UVUpload(entity.getUVUpload())
				.UVContents(entity.getUVContents())
				.UVHitCnt(entity.getUVHitCnt())
				.UVTitle(entity.getUVTitle())
				.build();
		
		return dto;
	}
	@Async
	public int findUser(int id) {
		try {
		int optional = lvRepository.findUser(id);
		int catchId = optional;
		System.out.println("catchcatch!! :: "+ catchId);
		return catchId;
		
		}catch (Exception e) {
			return 0;
		}
	}
	@Async
	public void addfav(int id, int uVId) {
		lvRepository.addlikeVideo(id, uVId);
	}

}