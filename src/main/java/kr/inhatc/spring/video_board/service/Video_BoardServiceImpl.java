package kr.inhatc.spring.video_board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import kr.inhatc.spring.user.service.UserService;
import kr.inhatc.spring.video_board.dto.Video_BoardDto;
import kr.inhatc.spring.video_board.entity.QVideo_Board;
import kr.inhatc.spring.video_board.entity.Video_Board;
import kr.inhatc.spring.video_board.repository.Video_BoardRepository;
import kr.inhatc.spring.video_board.util.PageRequestDto;
import kr.inhatc.spring.video_board.util.PageResultDto;
import kr.inhatc.spring.video_board.util.UploadFile;

@Service
//@Transactional(readOnly = true)
public class Video_BoardServiceImpl implements Video_BoardService {

	@Autowired
	Video_BoardRepository video_BoardRepository;
	
	@Autowired
	UploadFile uploadFile;

	@Autowired
	UserService userService;
	/**
	 * 게시글 조회
	 */
//	@Override
//	public List<Video_BoardDto> videoList() {
//		List<Video_Board> list = video_BoardRepository.findAllByOrderByIdDesc();
//		return list.stream().map(Video_BoardDto::new).collect(Collectors.toList());
//	}

//	@Transactional
//	@Override
//	public Page<Video_BoardDto> videoList(Pageable pageable) {
//		
//		Page<Video_Board> page = video_BoardRepository.findAll(pageable);
//		Page<Video_BoardDto> pageDto = page.map(Video_BoardDto::new);
//		return pageDto;
//		
//		return video_BoardRepository.findAll(pageable).map(Video_BoardDto::new);	// 코드 최적화(Entity -> DTO 변환)
//	}

	@Override
	public Page<Video_BoardDto> search(String keyword, Pageable pageable) {
		Page<Video_Board> list = video_BoardRepository.findByTitleContaining(keyword, pageable);
		Page<Video_BoardDto> listDto = list.map(Video_BoardDto::new);
		return listDto;
	}

	private BooleanBuilder getSearch(PageRequestDto requestDto) {

		String type = requestDto.getType();

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		QVideo_Board qVideo_Board = QVideo_Board.video_Board;

		String keyword = requestDto.getKeyword();

		BooleanExpression expression = qVideo_Board.id.gt(0L); // id > 0 인 조건만 생성함!

		booleanBuilder.and(expression);

		if (type == null || type.trim().length() == 0) { // 검색 조건이 없는 경우
			return booleanBuilder;
		}

		// OR 연산에 의거해서 검색 조건을 작성하기
		BooleanBuilder conditionBuilder = new BooleanBuilder();

		if (type.contains("t")) {
			conditionBuilder.or(qVideo_Board.title.contains(keyword));
		}
		if (type.contains("c")) {
			conditionBuilder.or(qVideo_Board.contents.contains(keyword));
		}
		if (type.contains("o")) {
			conditionBuilder.or(qVideo_Board.creator.contains(keyword));
		}

		// 모든 조건 통합
		booleanBuilder.and(conditionBuilder);

		return booleanBuilder;

	}
 
	@Override
	public PageResultDto<Video_BoardDto, Video_Board> getList(PageRequestDto requestDto) {

		// 역순으로 페이지 정보 가져오기 (내림차순으로 페이지 출력하기)
		Pageable pageable = requestDto.getPageable(Sort.by("id").descending());
		
		// 검색 조건 처리
		BooleanBuilder booleanBuilder = getSearch(requestDto);

		// QuestDSL 사용 - 페이지 정보를 통한 페이지 결과 가져오기
		Page<Video_Board> result = video_BoardRepository.findAll(booleanBuilder, pageable);

		// 엔티티를 DTO로 변환해주는 기능
		Function<Video_Board, Video_BoardDto> fn = (entity -> entityToDto(entity));

		return new PageResultDto<>(result, fn);
	}
	
	@Override
	public PageResultDto<Video_BoardDto, Video_Board> getList2(PageRequestDto requestDto) {

		// 역순으로 페이지 정보 가져오기 (내림차순으로 페이지 출력하기)
		Pageable pageable = requestDto.getPageable(Sort.by("id").descending());
		
		// 검색 조건 처리
		BooleanBuilder booleanBuilder = getSearch(requestDto);
	 	
		// QuestDSL 사용 - 페이지 정보를 통한 페이지 결과 가져오기
		Page<Video_Board> result = video_BoardRepository.find(booleanBuilder, pageable);

		// 엔티티를 DTO로 변환해주는 기능
		Function<Video_Board, Video_BoardDto> fn = (entity -> entityToDto(entity));

		return new PageResultDto<>(result, fn);
	}

	/**
	 * 게시글 저장
	 */
	@Override
	public void saveVideo(Video_BoardDto video, MultipartFile file) throws Exception  {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String path = auth.getName();
		String nick = userService.findUserNick(path);
		video.setCreator(nick);
		
		if(file.isEmpty()) {
			String img = "";
			video.setFilepath(img);
		} else {
			uploadFile.fildUpload(video, file);
		}
		video_BoardRepository.save(video.toEntity());
	}
	
	

	/**
	 * 게시글 상세조회
	 */
	@Override
	@Transactional
	public Video_BoardDto videoDetail(Long id) {
		Optional<Video_Board> optional = video_BoardRepository.findById(id);
		if (optional.isPresent()) {
			Video_Board user = optional.get();
			user.increaseHits();
			return new Video_BoardDto(user);
		} else {
			throw new NullPointerException();
		}
	}

	/**
	 * 게시글 삭제
	 */
	@Override
	public void videoDelete(Long id) {
		video_BoardRepository.deleteById(id);
	}
	@Override
	@Transactional
	public List<Video_BoardDto> searchVideo(int id) {
		List<Video_BoardDto> searchLecture = video_BoardRepository.searchVideo(id);
		return searchLecture;
	}
	



}
