package cn.com.pengyue.crawler.spi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import cn.com.pengyue.crawler.DataProvider;
import cn.com.pengyue.vo.ws.Page;
import cn.com.pengyue.vo.ws.Rule;
import cn.com.pengyue.vo.ws.WebService;
import cn.com.pengyue.vo.ws.WebService_Service;

public class WebServiceClientDataProvider implements DataProvider {

	private WebService webService;
	private String wsdl;
	private String namespace;
	private String qname;
	private String saveDir;

	@Override
	public int readCatchNum(String type) {
		return 99999999;// 不限制
	}

	@Override
	public List<Rule> readRules() {
		return webService.getRules();
	}

	@Override
	public void init(Map<Object, Object> config) {
		wsdl = (String) config.get(DataProvider.class.getName() + ".wsdl");
		namespace = (String) config.get(DataProvider.class.getName()
				+ ".namespace");
		qname = (String) config.get(DataProvider.class.getName() + ".qname");
		saveDir = (String) config
				.get(DataProvider.class.getName() + ".saveDir");
		webService = (WebService) Proxy.newProxyInstance(WebService.class.getClassLoader(), new Class[]{WebService.class}, 
				new IfNullOrOOMErrorRetrayWebService().setWebService());
	}
	
//	public static void main(String[] args) {
//		WebServiceClientDataProvider  service =	new WebServiceClientDataProvider();
//		service.namespace="http://www.pengyue.com.cn/";
//		service.wsdl="http://localhost:8080/overseas_infomation/webservices?wsdl";
//		service.qname="WebService";
//		service.setWebService();
//		
//		Page page = new Page();
//		page.setAddDate(new Date());
//		page.setInfoPushDate(new Date());
//		service.webService.savePage(page );
//	}
	

	private String nowDateStr = new SimpleDateFormat("yyyyMMdd")
			.format(new Date());

	private static String getMD5(String content) {
		String md5 = "";
		try {
			MessageDigest md5MessageDigest;
			md5MessageDigest = MessageDigest.getInstance("MD5");
			if (content == null || content.trim().equals("")) {
				return null;
			}
			md5MessageDigest.update(content.getBytes());
			byte digest[] = md5MessageDigest.digest();
			StringBuilder builder = new StringBuilder();
			for (byte b : digest) {
				builder.append(String.format("%02X", b));
			}
			md5 = builder.toString();
		} catch (NoSuchAlgorithmException e) {}
		return md5;
	}

	@Override
	public List<Page> savePageAndHtmlCode(List<Page> pages, String htmlCode) {
		if(!pages.isEmpty()){
			Page page = pages.get(0);
			String pageDomain = page.getDomain();
			int ruleId = page.getRuleId();
			File saveFile = new File(saveDir, nowDateStr + "/" + pageDomain+"/"+System.nanoTime()+ "/"+ruleId+".html");
			saveFile.getParentFile().mkdirs();
			save2File(htmlCode, saveFile);
		}
		List<Page> results = new ArrayList<Page>();
		for (Page page : pages) {
			try {
				page.setStatus(1);
				page.setAddDate(new Date());
//				page.setSaveContentPath(saveFile.getAbsolutePath());
				page = webService.savePage(page);
				results.add(page);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return results;
	}
	private void save2File(String data,File file){
		file.getParentFile().mkdirs();
		Writer writer = null;
		try {
			writer = new OutputStreamWriter(new FileOutputStream(file),"utf-8");
			writer.write(data);
		} catch (UnsupportedEncodingException e) {
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if(writer!=null)try {
					writer.close();
				} catch (IOException e) {}
		}
	}

	@Override
	public void updatePageAndHtmlCode(Page page, String htmlCode) {
		String md5Name = getMD5(page.getUrl()+"&_"+page.getRuleId()+"&_"+page.getTitle());
		File saveFile = new File(saveDir, nowDateStr + "/" + page.getDomainName() +"/"+md5Name +".html");
		save2File(htmlCode, saveFile);
		page.setSaveContentPath(saveFile.getAbsolutePath());
		webService.updatePage(page);
	}
	
	private class IfNullOrOOMErrorRetrayWebService implements InvocationHandler {
		private WebService webService;

		public void setWebService(WebService webService) {
			this.webService = webService;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			if(webService!=null){
				try {
					return method.invoke(webService, args); 
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} catch (OutOfMemoryError oom){
					System.out.println("这个webService内存不够用了！接下来重新创建一个！");
				}
			}
			setWebService();
			if(webService==null){
				throw new NullPointerException("无法连接服务器未返回值，请检查相关配置和服务器情况！！");
			}
			return method.invoke(webService, args); 
		}
		
		private IfNullOrOOMErrorRetrayWebService setWebService() {
			try {
				webService = new WebService_Service(new URL(wsdl), new QName(
						namespace, qname)).getWebServicePort();
			} catch (Exception e) {
				webService = null;
			}
			return this;
		}
	}
}

