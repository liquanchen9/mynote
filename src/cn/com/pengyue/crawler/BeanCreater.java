package cn.com.pengyue.crawler;

public interface BeanCreater {
	public <T> T createBean(String className);
}
