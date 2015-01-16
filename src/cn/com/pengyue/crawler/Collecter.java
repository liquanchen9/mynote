package cn.com.pengyue.crawler;

import java.util.List;

import cn.com.pengyue.vo.PageInfo;
import cn.com.pengyue.vo.ws.Page;
import cn.com.pengyue.vo.ws.Rule;

//采集器
public abstract class Collecter implements ParserAware,DataProviderAware {
	
	protected Parser parser;
	protected DataProvider dataProvider;
	
	//初始化
	protected abstract void init(String type);
	
	//URL--> HOST 可以决定你的采集间隔
	protected abstract int getWaitTime(String url);
	
	//是否使用代理
	protected abstract boolean getIsNeedProxy(String url);
	
	//采集网页源代码
	protected abstract String collectHtmlCode(String url);
	
	//采集网页
	public final List<Page> collectHtml(String url, Rule rule, PageInfo pageInfo){
		String htmlCode = collectHtmlCode(url);
		List<Page> pages = parser.parse(htmlCode,rule,pageInfo);
		pages = dataProvider.savePageAndHtmlCode(pages,htmlCode);
		return pages;
	}
	
	//采集正文
	public final void collectHtml(String url, Page page) {
		String htmlCode = collectHtmlCode(url);
		String content = parser.parseContent(htmlCode);
		dataProvider.updatePageAndHtmlCode(page,content);
	}
	
	@Override
	public void setParser(Parser parser) {
		this.parser = parser;
	}

	public void setDataProvider(DataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}
}
