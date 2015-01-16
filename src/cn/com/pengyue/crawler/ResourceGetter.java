package cn.com.pengyue.crawler;

import java.io.InputStream;

public interface ResourceGetter {
	InputStream getResourceAsStream(String name);
}
