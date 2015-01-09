package cn.com.pengyue.crawler;

public class CollecterWrapper implements Collecter {
	private Collecter real;

	public CollecterWrapper(Collecter real) {
		this.real = real;
	}

	public boolean getIsNeedProxy(String url) {
		return real.getIsNeedProxy(url);
	}

	public int getWaitTime(String url) {
		return real.getWaitTime(url);
	}

	public void init() {
		real.init();
	}

	public String collectHtml(String url) {
		return real.collectHtml(url);
	}
	
}
