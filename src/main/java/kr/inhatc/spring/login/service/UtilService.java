package kr.inhatc.spring.login.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class UtilService {
	
	public String GetRandomNum(int end) {
        String num="";
        Random random=new Random();
        for(int i=0;i<end;i++){
            num+=Integer.toString(random.nextInt(10));
        }
        return num;
    }
}
