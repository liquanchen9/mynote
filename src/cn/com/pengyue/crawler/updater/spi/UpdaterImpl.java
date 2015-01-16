package cn.com.pengyue.crawler.updater.spi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

import ognl.Ognl;
import ognl.OgnlException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.com.pengyue.crawler.Crawler;
import cn.com.pengyue.crawler.updater.Updater;

public class UpdaterImpl implements Updater {
	private Properties selectorProvider = new Properties();
	private String selectorProviderSrcUrl;

	private String host;
	private String url;
	private String downloadUrl;
	private String downloadFileName;

	public UpdaterImpl() {
		try {
			selectorProvider.load(UpdaterImpl.class
					.getResourceAsStream("UpdaterImpl.properties"));
		} catch (IOException e) {// 忽略
		}
	}

	@Override
	public boolean update(String version) {
		InputStream is = null;
		OutputStream os = null;
		try {
			String lastVersion = getLastVersion();
			if (lastVersion == null) {
				lastVersion = retryGetVersion();
			}
			if (lastVersion.compareTo(version) > 0) {
				// 下载
				// https://github.com/liquanchen9/overseas_infomation_crawler/releases/download/1.0.0/aopalliance.jar
				downloadFileName = selectorProvider
						.getProperty("downloadFileName");
				downloadUrl = String.format(selectorProvider
						.getProperty("downloadUrl"), lastVersion,
						downloadFileName);
				int timeout= Integer.valueOf( selectorProvider.getProperty("downloadTimeout"));
				byte[] updateData = Jsoup.connect(downloadUrl).timeout(timeout)
						.ignoreContentType(true).execute().bodyAsBytes();

				save2UpdateDir(updateData);
				
				//更新爬虫版本
				File versionFile = new File(Crawler.DEFAULT_STRATEGIES_PATH);
				if(!versionFile.exists()){
					versionFile.createNewFile();
				}
				Properties versionProp = new Properties();
				versionProp.load(is = new FileInputStream(versionFile));
				versionProp.put("version", lastVersion);
				versionProp.store(os = new FileOutputStream(versionFile), "自动更新到"+lastVersion);
				return true;
			}
		} catch (Exception e) {/* 更新失败就算了 */
			e.printStackTrace();
		} finally {
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {}
			}
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {}
			}
		}
		return false;
	}
	

	private void save2UpdateDir(byte[] updateData) throws IOException {
		File updateDir = new File(selectorProvider.getProperty("updateDir"));
		if (!updateDir.exists()) {
			updateDir.mkdirs();
		}
		File updateFile = new File(updateDir, downloadFileName);
		if (!updateFile.exists()) {
			updateFile.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(updateFile, false);
		try {
			fos.write(updateData);
		} finally {
			fos.close();
		}
	}

	public String getLastVersion() {
		host = selectorProvider.getProperty("host", "https://github.com");
		url = host
				+ selectorProvider.getProperty("url",
						"/liquanchen9/overseas_infomation_crawler/releases");
		try {
			Document doc = Jsoup.connect(url).get();
			String lastVersionUrl = host
					+ value("lastVersion",doc);
			String lastVersion = lastVersionUrl.substring(lastVersionUrl
					.lastIndexOf("/") + 1);
			return lastVersion;
		} catch (Exception e) {/* 失败就算了 */
		}
		return null;
	}

	public String retryGetVersion() {
		try {
			selectorProviderSrcUrl = String.format(selectorProvider
					.getProperty("selectorProviderSrcUrl"), UpdaterImpl.class
					.getName().replaceAll("\\.", "/"));
			selectorProvider.load(new StringReader(Jsoup.connect(
					selectorProviderSrcUrl).ignoreContentType(true).execute()
					.body()));
		} catch (IOException e) {// 忽略
		}
		return getLastVersion();
	}

	public String value(String key, Document doc)
			throws OgnlException {
		return (String) Ognl.getValue(selectorProvider.getProperty(key), doc);
	}
	
	private ClassLoader classLoader;
	
	
	@Override
	public ClassLoader getUpdateClassLoader() throws MalformedURLException {
		if(classLoader==null){
			classLoader = new URLClassLoader(new URL[]{new URL(String.format("jar:file://%s/%s!/",
					selectorProvider.getProperty("updateDir"),downloadFileName))});
		}
		return classLoader;
	}

}
