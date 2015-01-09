package cn.com.pengyue.crawler.updater.spi;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import org.jsoup.Jsoup;

import cn.com.pengyue.crawler.updater.Updater;

public class UpdaterImpl implements Updater {
	private String selectorProviderSrcUrl = "https://raw.githubusercontent.com/liquanchen9/overseas_infomation_crawler/master/"+UpdaterImpl.class.getName().replaceAll("\\.", "/")+".properties";
	private Properties selectorProvider = new Properties();
	
	private String host ;
	private String url;
	
	public UpdaterImpl() {
		try {
			selectorProvider.load(new StringReader(Jsoup.connect(selectorProviderSrcUrl).ignoreContentType(true).execute().body()));
		} catch (IOException e) {//忽略 
		}
		System.out.println(selectorProvider);
		host = selectorProvider.getProperty("host","https://github.com");
		url = host + selectorProvider.getProperty("url","/liquanchen9/overseas_infomation_crawler/releases");
	}

	@Override
	public void update(String version) {
		try {
//			Document doc =  Jsoup.connect(url).get();
//			String lastVersionUrl =  host +  doc.select(".release-timeline .release-meta .tag-references .css-truncate").first().attr("href");
			String lastVersionUrl =  host + "/liquanchen9/overseas_infomation_crawler/tree/1.0.0";
			String lastVersion = lastVersionUrl.substring(lastVersionUrl.lastIndexOf("/")+1);
			System.out.println(lastVersion);
			if(lastVersion.compareTo(version)>0){
				//下载
				
			}
		} catch (Exception e) {/*更新失败就算了*/}
	}
	
	public static void main(String[] args) {
		new UpdaterImpl().update("0.0.9");
	}
}
