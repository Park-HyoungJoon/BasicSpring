package kr.inhatc.spring.user.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import kr.inhatc.spring.user.dto.CrawDTO;


@Service
public class CrawlingService {
	
	
		String url = "http://www.yes24.com/24/Category/More/001001003?ElemNo=94&ElemSeq=2";
		
		@PostConstruct
		public List<CrawDTO> getCraw() throws IOException{
			Document doc = Jsoup.connect(url).get();
			
			Elements e1 = doc.getElementsByAttributeValue("class","cCont_goodsSet");
			Elements e2 = doc.getElementsByAttributeValue("class","goods_price");
			Elements e3 = doc.getElementsByAttributeValue("class","goods_read");
			Elements e4 = doc.getElementsByAttributeValue("class","goods_name");
			Elements priceEle = e2.select("em");
			Elements rprice = priceEle.attr("class", "yes_b");
			Elements reads = e3.attr("class", "goods_read");
			
			ArrayList<String> arrPrice = new ArrayList<>();
			for (int i = 0; i < rprice.size(); i+=2) {
				Element price1 = rprice.get(i);
				arrPrice.add(price1.text());
			}
			
			List<CrawDTO> craw = new ArrayList<CrawDTO>();
			for (int i = 0; i < e1.size(); i++) {
				
				Element bookEle = e1.get(i);
				Elements imgEle = bookEle.select("img");
				String imgUrl = imgEle.attr("src");
				String title = imgEle.attr("alt");
				Element read1 = reads.get(i);
				
				CrawDTO crawdto = CrawDTO.builder()
						.title(title)
						.imagelink(imgUrl)
						.explanation(read1.text())
						.price(arrPrice.get(i))
						.build();
				
				craw.add(crawdto);
					
		}
			return craw;
		}
		

}

		
		
		
	
		//가격값 받아오기
		// 따로 추출해야함 
		
		
		
	
		
			
			
//			System.out.println("제목 : " + title);
//			System.out.println("이미지 링크 :"+imgUrl);
//			System.out.println("설명 : " +read1.text());
//			System.out.println("가격 :" + arrPrice.get(i) );
//			System.out.println();
			
		
		
		
	
		
		
	


