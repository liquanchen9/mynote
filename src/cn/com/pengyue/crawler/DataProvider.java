package cn.com.pengyue.crawler;

import java.util.List;
import java.util.Map;

import cn.com.pengyue.vo.ws.Page;
import cn.com.pengyue.vo.ws.Rule;

public interface DataProvider {
	//结果存储
	List<Page> savePageAndHtmlCode(List<Page> pages, String htmlCode);
	//配置读取
	List<Rule> readRules();
	//读取计划采集数量
	int readCatchNum(String type);
	
	//初始化
	void init(Map<Object,Object> config);
	//结果存储
	void updatePageAndHtmlCode(Page page, String htmlCode);
}
