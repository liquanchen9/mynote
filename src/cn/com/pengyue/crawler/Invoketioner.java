package cn.com.pengyue.crawler;

import java.util.List;
import java.util.Map;

import cn.com.pengyue.vo.PageInfo;
import cn.com.pengyue.vo.ws.Rule;

//规划者
public interface Invoketioner {
	
	//加载信息
	void init(List<Rule> rules, PageInfo pageinfo,Map<Object, Object> config);
	
	//是否有下一个
	boolean hasNext();
	
	//发布任务给请求队列中
	void pushCollectTask(Collecter collecter);
	
	//处理采集器结果
	void invokeTaskResult();
}
