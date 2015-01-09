package cn.com.pengyue.crawler;

import java.io.FileInputStream;
import java.util.Comparator;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import org.apache.commons.lang.ClassUtils;

//爬虫
public class Crawler {

	private static final String DEFAULT_STRATEGIES_PATH = "Crawler.properties";

	private static final Properties defaultStrategies;

	private Collecter collecter;//采集器

	private Invoketioner invoketioner;//规划者

	private DataProvider dataProvider;//数据处理器
	
	private Parser parsers;//解析器
	
	private Queue<String> urlQueue;//请求队列  
	
	private String version = "1.0.0";
	
	private Crawler() {
		initStrategies();//初始化
		
		//更新机制
		update();
	}

	protected void update() {
		//是否更新
		
	}

	protected void initStrategies() {
		initDataProvider();
		initInvoketioner();
		initCollecter();
		initParser();
		initQueue();
	}
	
	//开始采集
	public void doCrawl(){
		//数据 提取
		 dataProvider.readConfig();
		
		
	}

	private void initQueue() {
		urlQueue = new PriorityBlockingQueue<String>(10, //TODO
				(Comparator<String>)getDefaultStrategy(Comparator.class.getName()));
	}

	private void initParser() {
		this.parsers = getDefaultStrategy(Parser.class.getName());
	}

	private void initDataProvider() {
		this.dataProvider = getDefaultStrategy(DataProvider.class.getName());
	}

	private void initCollecter() {
		this.collecter = getDefaultStrategy(Collecter.class.getName());
	}

	private void initInvoketioner() {
		this.invoketioner = getDefaultStrategy(Invoketioner.class.getName());
	}

	protected <T> T getDefaultStrategy(String strategyInterface) {
		try {
			Class<T> cls = ClassUtils.getClass(defaultStrategies
					.getProperty(strategyInterface));
			return cls.newInstance();
		} catch (Exception ex) {
			throw new IllegalStateException("Could not Instance '"
					+ strategyInterface + "' Bean : " + ex.getMessage());
		}
	}

	static {
		Properties strategies = new Properties();
		//load defaults 加载默认配置
		try {
			strategies.load(Crawler.class
					.getResourceAsStream(DEFAULT_STRATEGIES_PATH));
		} catch (Exception ex) {
			throw new IllegalStateException("Could not load '"
					+ DEFAULT_STRATEGIES_PATH + "': " + ex.getMessage());
		}
		//load file 加载配置文件
		try {
			strategies.load(new FileInputStream(DEFAULT_STRATEGIES_PATH));
		} catch (Exception ex) { /*ignore 忽略错误 */}
		defaultStrategies = strategies;
	}
}
