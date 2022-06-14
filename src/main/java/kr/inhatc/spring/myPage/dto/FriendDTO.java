package kr.inhatc.spring.myPage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FriendDTO {
	long id;
	String name;
	String price;
}
