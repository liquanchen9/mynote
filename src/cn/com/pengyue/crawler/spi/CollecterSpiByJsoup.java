package cn.com.pengyue.crawler.spi;

import java.io.IOException;
import java.util.Properties;

import org.jsoup.Jsoup;

import cn.com.pengyue.crawler.Collecter;

public class CollecterSpiByJsoup extends Collecter {

	private int catchNum;

	@Override
	protected String collectHtmlCode(String url) {
		try {
			Properties prop = System.getProperties();
			// 设置http访问要使用的代理服务器的地址
			prop.setProperty("http.proxyHost", "127.0.0.1" /*"42.96.174.201"*/);
			// 设置http访问要使用的代理服务器的端口
			prop.setProperty("http.proxyPort", "8087" /*"14347"*/);
			// 设置不需要通过代理服务器访问的主机，可以使用*通配符，多个地址用|分隔
			prop.setProperty("http.nonProxyHosts", "localhost");

			// 设置安全访问使用的代理服务器地址与端口
			// 它没有https.nonProxyHosts属性，它按照http.nonProxyHosts 中设置的规则访问
			prop.setProperty("https.proxyHost",  "127.0.0.1" /*"42.96.174.201"*/);
			prop.setProperty("https.proxyPort", "8087" /*"14347"*/);
			System.out.println("connect to: "+url);
			return Jsoup.connect(url).timeout(100000).get().html();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			Properties prop = System.getProperties();
			// 设置http访问要使用的代理服务器的地址
			prop.remove("http.proxyHost");//, "127.0.0.1" /*"42.96.174.201"*/);
			// 设置http访问要使用的代理服务器的端口
			prop.remove("http.proxyPort");//, "8087" /*"14347"*/);
			// 设置不需要通过代理服务器访问的主机，可以使用*通配符，多个地址用|分隔
			prop.remove("http.nonProxyHosts");//, "localhost");

			// 设置安全访问使用的代理服务器地址与端口
			// 它没有https.nonProxyHosts属性，它按照http.nonProxyHosts 中设置的规则访问
			prop.remove("https.proxyHost");//,  "127.0.0.1" /*"42.96.174.201"*/);
			prop.remove("https.proxyPort");//, "8087" /*"14347"*/);
			
		}
		//return null;
	}
	
	@Override
	protected boolean getIsNeedProxy(String url) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected int getWaitTime(String url) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void init(String type) {
		// 这种采集计划数量
		//catchNum = dataProvider.readCatchNum(type);
	}
}
