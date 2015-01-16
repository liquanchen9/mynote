package cn.com.pengyue.crawler;

public abstract class CollecterWrapper extends Collecter {
	private Collecter real;

	public CollecterWrapper(Collecter real) {
		this.real = real;
	}
}
