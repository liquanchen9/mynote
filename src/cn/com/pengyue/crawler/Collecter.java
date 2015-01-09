package cn.com.pengyue.crawler;

//采集器
public interface Collecter {
	//初始化
	void init();
	
	//URL--> HOST 可以决定你的采集间隔
	int getWaitTime(String url);
	
	//是否使用代理
	boolean  getIsNeedProxy(String url);
	
	//采集网页
	String collectHtml(String url);
}
