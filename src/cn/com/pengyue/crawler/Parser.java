package cn.com.pengyue.crawler;

import java.util.Date;

//解析器
public interface Parser {
	//URL--> HOST+parameter 决定如何处理日期格式
	Object parse(String html);
	
	//URL--> HOST+parameter 决定如何处理解析 是解析列表还是解析正文
	Date parseDate(String dateStr);
}
