package cn.com.pengyue.crawler;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.ClassUtils;

import cn.com.pengyue.crawler.updater.Updater;
import cn.com.pengyue.vo.PageInfo;
import cn.com.pengyue.vo.ws.Rule;

//爬虫
public class Crawler implements BeanCreater,ResourceGetter{

	public static final String DEFAULT_STRATEGIES_PATH = "Crawler.properties";

	private static final Properties defaultStrategies;

	private Collecter collecter;// 采集器

	private Invoketioner invoketioner;// 规划者

	private UrlCreater urlCreater; // URL构造器

	private DataProvider dataProvider;// 数据处理器

	private Parser parser;// 解析器

	private Updater updater;

	private List<Rule> rules;

	private String type;

	private Crawler() {
	}

	protected void initStrategies() {
		initUpdater();

		// 更新机制
		updater.update(defaultStrategies.getProperty("version", "1.0.0"));
		
		initDataProvider();
		
		initUrlCreater();
		
		initParser();
		
		initCollecter();
		
		initInvoketioner();
		
		//注入
		awre(dataProvider,
				urlCreater,
				parser,
				collecter,
				invoketioner);
	}
	
	private void initUpdater() {
		this.updater = getDefaultStrategy(Updater.class.getName());
	}
	   
	private void initDataProvider() {
		this.dataProvider = getDefaultStrategy(DataProvider.class.getName());
		this.dataProvider.init(defaultStrategies);
	}
	

	private void initUrlCreater() {
		this.urlCreater = getDefaultStrategy(UrlCreater.class.getName());
		this.urlCreater.init(type, this);
	}


	private void initParser() {
		this.parser = getDefaultStrategy(Parser.class.getName());
		this.parser.init(type, this);
	}

	

	private void initCollecter() {
		this.collecter = getDefaultStrategy(Collecter.class.getName());
		collecter.init(type);
	}

	private void initInvoketioner() {
		this.invoketioner = getDefaultStrategy(Invoketioner.class.getName());
	
		//数据 提取
		rules = dataProvider.readRules();
		//分页信息
		PageInfo pageinfo = new PageInfo();
		pageinfo.setPageSize(Integer.valueOf(defaultStrategies.getProperty(type+".pageSize","10")));
		
		//初始规划
		invoketioner.init(rules,pageinfo,defaultStrategies);
	}
	
	
	protected void awre(Object... objects){
		for (Object obj : objects) {
			if(obj instanceof CollecterAware ){
				((CollecterAware)obj).setCollecter(collecter);
			}
			if(obj instanceof DataProviderAware ){
				((DataProviderAware)obj).setDataProvider(dataProvider);
			}
			if(obj instanceof ParserAware ){
				((ParserAware)obj).setParser(parser);
			}
			if(obj instanceof UrlCreaterAware ){
				((UrlCreaterAware)obj).setUrlCreater(urlCreater);
			}
		}
	}
	
	@Override
	public <T> T createBean(String className) {
		T result = null;
		Exception ex = null;
		try {
			Class<T> cls = ClassUtils.getClass(className);
			result = cls.newInstance();
		} catch (Exception e) {
			ex = e;
		}
		if (result == null) {
			try {
				Class<T> cls = ClassUtils.getClass(updater
						.getUpdateClassLoader(), className);
				result = cls.newInstance();
			} catch (Exception e) {
			}// 从更新包加载类
		}
		if (result == null) {
			throw new IllegalStateException("Could not Instance '"
					+ className + "' Bean : " + ex.getMessage());
		}
		return result;
	}

	protected <T> T getDefaultStrategy(String strategyInterface) {
		String className = defaultStrategies.getProperty(strategyInterface);
		return createBean(className);
	}
	
	@Override
	public InputStream getResourceAsStream(String name) {
		InputStream result = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
		if(result==null){
			result = ClassLoader.getSystemClassLoader().getResourceAsStream(name);
		}
		if(result==null){
			try {
				result = updater.getUpdateClassLoader().getResourceAsStream(name);
			} catch (MalformedURLException e) {}
		}
		return result;
	}

	static {
		Properties strategies = new Properties();
		// load defaults 加载默认配置
		try {
			strategies.load(Crawler.class
					.getResourceAsStream(DEFAULT_STRATEGIES_PATH));
		} catch (Exception ex) {
			throw new IllegalStateException("Could not load '"
					+ DEFAULT_STRATEGIES_PATH + "': " + ex.getMessage());
		}
		// load file 加载配置文件
		try {
			strategies.load(new FileInputStream(DEFAULT_STRATEGIES_PATH));
		} catch (Exception ex) { /* ignore 忽略错误 */
		}
		defaultStrategies = strategies;
	}
	
	
	// 开始采集
	public static void doCrawl(String crawlerType) {
		Crawler crawler = new Crawler();
		crawler.type = crawlerType;

		crawler.initStrategies();//初始化
		
		//执行规划		
		while(crawler.invoketioner.hasNext()){
			//发布任务给采集器
			crawler.invoketioner.pushCollectTask(crawler.collecter);
		}
	}
	
	public static void main(String[] args) throws ParseException {
		//Crawler.doCrawl("Aptn");
		//Crawler.doCrawl("Upi");
		//Crawler.doCrawl("Reuters");
		//Crawler.doCrawl("Afp");
		//Crawler.doCrawl("Kyodonews");
		//Crawler.doCrawl("Thetimes");
		//Crawler.doCrawl("Independent");
		//Crawler.doCrawl("Washingtonpost");
		//Crawler.doCrawl("Dailymail");
		Crawler.doCrawl("Thestar");
	}
}
