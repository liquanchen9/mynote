package cn.com.pengyue.crawler;
//规划者
public interface Invoketioner {
	
	//加载信息
	void init();
	
	//是否有下一个
	boolean hasNext();
	
	//发布任务给采集器
	void pushCollectTask();
	
	//处理采集器结果
	void invokeTaskResult();
}
