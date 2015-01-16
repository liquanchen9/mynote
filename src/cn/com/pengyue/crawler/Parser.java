package cn.com.pengyue.crawler;

import java.util.List;

import cn.com.pengyue.vo.PageInfo;
import cn.com.pengyue.vo.ws.Page;
import cn.com.pengyue.vo.ws.Rule;

//解析器
public interface Parser { 
	//URL--> HOST+parameter 决定如何处理日期格式
	List<Page> parse(String html,Rule rule,PageInfo pageInfo);

	
	void init(String type,ResourceGetter resourceGetter);


	String parseContent(String htmlCode);
}
