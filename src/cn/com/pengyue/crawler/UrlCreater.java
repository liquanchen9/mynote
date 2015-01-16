package cn.com.pengyue.crawler;

import cn.com.pengyue.vo.PageInfo;
import cn.com.pengyue.vo.ws.Rule;

//URL构造器
public interface UrlCreater {
	String createUrl(Rule rule, PageInfo  pageinfo );
	
	void init(String type,ResourceGetter resourceGetter);
}
