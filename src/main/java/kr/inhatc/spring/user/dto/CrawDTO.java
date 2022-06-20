package kr.inhatc.spring.user.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import kr.inhatc.spring.video_board.entity.Video_Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrawDTO {
	private String title;
	private String imagelink;
	private String explanation;
	private String price;
	

	
	public CrawDTO toEntity() {
		return CrawDTO.builder()
				.title(title)
				.imagelink(imagelink)
				.explanation(explanation)
				.price(price)
				.build();
	}
	
}
