package cn.com.pengyue.crawler;

public interface DataProvider {
	//结果存储
	void saveData(Object data) ;
	//配置读取
	Object readConfig();
}
