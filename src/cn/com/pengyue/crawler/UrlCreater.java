package cn.com.pengyue.crawler;
//URL构造器
public interface UrlCreater {
	String createUrl(String url,String keyword,Object... objects );
}
