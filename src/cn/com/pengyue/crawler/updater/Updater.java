package cn.com.pengyue.crawler.updater;

import java.net.MalformedURLException;

public interface Updater {  
	boolean update(String version);

	ClassLoader getUpdateClassLoader() throws MalformedURLException;
}
