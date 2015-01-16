package cn.com.pengyue.crawler.spi;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.Map.Entry;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import ognl.OgnlException;
import cn.com.pengyue.crawler.Collecter;
import cn.com.pengyue.crawler.Invoketioner;
import cn.com.pengyue.crawler.UrlCreater;
import cn.com.pengyue.crawler.UrlCreaterAware;
import cn.com.pengyue.vo.PageInfo;
import cn.com.pengyue.vo.ws.Page;
import cn.com.pengyue.vo.ws.Rule;

public class InvoketionerSpi implements Invoketioner ,UrlCreaterAware{
	
	private Map<Rule, PageInfo> needInvokedInfo = new Hashtable<Rule, PageInfo>();
	private List<Rule> rules = new Vector<Rule>();
	private UrlCreater urlCreater;
	
	private boolean isInvokeRule = true;
	private boolean isInvokeTimer = false;
	private Rule currtRule;
	private PageInfo currtPageInfo;
	private ThreadPoolExecutor thredPool;
	private int startThreadNum = 0;
	private List<Integer> invokedResults = new Vector<Integer>();
	private Timer timer = new Timer(true);
	private ThreadPoolExecutor collectPageThredPool;
	
	public void init(List<Rule> rules, PageInfo pageinfo,Map<Object, Object> config) {
		pageinfo.setPageNo(1);
		this.rules.addAll(rules);
		for (Rule rule : rules) {
			try {
				needInvokedInfo.put(rule, (PageInfo) pageinfo.clone());
			} catch (CloneNotSupportedException e) {}
		}
		
		startThreadNum = Integer.valueOf((String)config.get(Invoketioner.class.getName()+".startThreadNum"));
		int startNum = rules.size();
		if(startNum > startThreadNum){//大于配置的，使用小的，节省资源
			startNum = startThreadNum;
		}
		this.thredPool =  new ThreadPoolExecutor(1, startThreadNum = startNum,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
	}
	
	@Override
	public boolean hasNext() {
		boolean result = (isInvokeRule = rules.size() > 0) || needInvokedInfo.size()>0;
		return result;
	}
	
	@Override
	public void pushCollectTask(Collecter collecter) {
		if(isInvokeRule){
			currtRule = rules.remove(0);
			currtPageInfo = needInvokedInfo.get(currtRule);
			execute(collecter);
			return;
		}
		if( !isInvokeTimer){
			//发布完 全部焦点了！！
			TimerTask task = this.new PushCollectTask(collecter);
			try {
				timer.schedule(task, 1000);
			} catch (IllegalStateException e) {
				//java.lang.IllegalStateException: Timer already cancelled. 忽
			}
			isInvokeTimer = true;
		}
	}
	
	public void pushCollectPageTask(Collecter collecter,List<Page> pages) {
		int size = pages.size();
		getCollectPageThredPool(size);
		 //直接发布任务出去
		for (Page page : pages) {
			if(collectPageThredPool.getActiveCount()>startThreadNum){
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {}
			}
			Runnable task = this.new PushCollectPageTask(collecter,page);
			collectPageThredPool.execute(task);
		}
	}
	
	private synchronized ThreadPoolExecutor getCollectPageThredPool(int startNum) {
		if(collectPageThredPool!=null)return collectPageThredPool;
		if(startNum > startThreadNum){//大于配置的，使用小的，节省资源
			startNum = startThreadNum;
		}
		return collectPageThredPool = new ThreadPoolExecutor(1, startNum,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
	}
	
	public void execute(Collecter collecter){
		currtRule.setRule(currtRule.getRuleEnName());
		String url = urlCreater.createUrl( currtRule, currtPageInfo);
		if(thredPool.getTaskCount()>10){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {}
		}
		thredPool.execute(InvoketionerSpi.this.new CollecterTask(collecter,url,currtPageInfo,currtRule));
	}
	
	@Override
	public void invokeTaskResult() {
		// TODO Auto-generated method stub
	}
	
	private class CollecterTask implements Runnable{
		private Collecter collecter;
		private String url;
		private PageInfo pageInfo;
		private Rule rule;

		public CollecterTask(Collecter collecter, String url,
				PageInfo pageInfo, Rule rule) {
			super();
			this.collecter = collecter;
			this.url = url;
			this.pageInfo = pageInfo;
			this.rule = rule;
		}


		public void run() {
			try {
				List<Page> results = collecter.collectHtml(url,rule,pageInfo);
				pushCollectPageTask(collecter, results);
				invokedResults.add(1);
			} catch (Throwable e) {
				e.printStackTrace();
				invokedResults.add(0);
			} finally{
				//System.out.println("collecter执行完了");
			}
		}
	}
	
	private class PushCollectPageTask implements Runnable{
		private Collecter collecter;
		private Page page;
		
		public PushCollectPageTask(Collecter collecter, Page page) {
			this.collecter = collecter;
			this.page = page;
		}

		@Override
		public void run() {
			collecter.collectHtml(page.getUrl(),page);
		}
	}
	
	private class PushCollectTask extends TimerTask{
		private Collecter collecter;

		public PushCollectTask(Collecter collecter) {
			this.collecter = collecter;
		}
		
		@Override
		public void run() {
			if(isContinue()){
				long waitTime = 0;
				long noFinalCount = 0;
				if(collectPageThredPool==null){//如果没有进行页面抓取就等5秒
					waitTime = 5000;
				}else if(( noFinalCount = collectPageThredPool.getTaskCount()- collectPageThredPool.getCompletedTaskCount())>0){//如果没有进行页面抓取线程多就有几个等几秒
					waitTime = 1000 * noFinalCount;
				}
				timer.schedule(new PushCollectTask(collecter), waitTime);
			}
		}

		public boolean isContinue() {
			//发布完 全部焦点了！！
			boolean findInvokedPageInfo = false;
			for (Entry<Rule, PageInfo> entry : needInvokedInfo.entrySet()) {
				if(currtPageInfo.getTotalSize()>0){//查找处理的总数量的焦点信息
					currtPageInfo = entry.getValue();
					currtRule = entry.getKey();
					findInvokedPageInfo = true;
					break;
				}
			}
			if(invokedResults.size()==needInvokedInfo.size() && !findInvokedPageInfo){//处理都有失败了,
				needInvokedInfo.clear();//都失败了就不用再处理下面的分页信息了
				return false;
			}
			if(!findInvokedPageInfo){//没有找到处理过总数量的信息 那只能等待了。。
				//等待完继续找
//				Thread.sleep(1000);
//				pushCollectTask(collecter);
				return true;
			}
			//如果 现在这个页码是最大的 那么这个焦点的全部分页就都结束了
			if(currtPageInfo.getPageNo()+1 > currtPageInfo.getMaxPageNo()){
				needInvokedInfo.remove(currtRule);//移除这个焦点
				//cancel();
				return true;
			}
			//下一页
			currtPageInfo.setPageNo(currtPageInfo.getPageNo()+1);
			execute(collecter);
			return true;
		}
	}

	@Override
	public void setUrlCreater(UrlCreater urlCreater) {
		this.urlCreater = urlCreater;
	}
	
	public static void main(String[] args) throws OgnlException {
		
		System.out.println("#doc = @org.jsoup.Jsoup@parse(#root),#eles = #doc.select('.upi_item'),#eleSize = #eles.size(),#results = new java.util.ArrayList(#eleSize),#forEles = :[ #this < #eleSize ? (#ele=#eles.get(#this),#page= new cn.com.pengyue.vo.ws.Page(),#dateEle=#ele.select('.p'),(#dateEle.size()>0?(#dateEle=#dateEle.get(0),#dateStr = #dateEle.text(),#dateNum = @Integer@valueOf(#dateStr.replaceAll('\\s+[a-zA-Z]+\\s+ago$','')),#dateNum = 0 - #dateNum,#dateStr = #dateNum>1 ? #dateStr.replaceAll('s\\s+ago$','') : #dateStr.replaceAll('\\s+ago$',''),#dateStr = #dateStr.replaceAll('^\\d+\\s+',''),#dateField = (#dateStr=='second'? 13: #dateStr=='minute'? 12: #dateStr=='hour'? 10: #dateStr=='day'? 5: #dateStr=='week'? 3: #dateStr=='month'? 2: #dateStr=='year'? 1: #dateStr=='decade'? (#dateNum = #dateNum*10 ,1): 5 ),#date = @java.util.Calendar@getInstance(),#date.add(#dateField,#dateNum),#page.infoPushDate = #date.getTime()):(#page.infoPushDate=new java.util.Date())),#page.title=#ele.select('.h1').get(0).text(),#page.contentDesc=#ele.select('.desc').get(0).text(),#page.url=#ele.select('a').attr('href'),#page.rule=#rule.rule,#page.ruleId=#rule.id,#page.domainName='合众社',#page.domain='www.upi.com',#results.add(#page),#forEles(#this+1)) : 1 ],#forEles(0)#pageInfo.pageSize=25,#totalEles = #doc.select('#pn_arw td'),#pageInfo.totalSize= #totalEles.size()>2 ?( @Integer@valueOf(#doc.select('#pn_arw td').get(2).text().replaceAll('^.*of\\s+',''))):25,@System@out.print(results.size()),#results [ognl.ParseException: Encountered  at line 1, column 1242".substring(1242));
	}

}
