package cn.com.pengyue.crawler.spi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import ognl.Ognl;
import ognl.OgnlException;

import cn.com.pengyue.crawler.ResourceGetter;
import cn.com.pengyue.crawler.UrlCreater;
import cn.com.pengyue.vo.PageInfo;
import cn.com.pengyue.vo.ws.Rule;

public class StringFormatUrlCreater implements UrlCreater {

	private String searchUrl;
	private String searchKeywordPropName;
	
	@Override
	public String createUrl(Rule rule, PageInfo  pageinfo) {
		String keyword = rule.getEnRuleInclude();
		try {
			keyword = (String) Ognl.getValue(searchKeywordPropName, rule);
		} catch (OgnlException e1) {}
		try {
			keyword = URLEncoder.encode(keyword, "utf-8");
		} catch (UnsupportedEncodingException e) {}
		return String.format(searchUrl, keyword,pageinfo.getPageNo(),pageinfo.getPageSize(),pageinfo.getFirstNo());
	}

	@Override
	public void init(String type, ResourceGetter resourceGetter) {
		Properties props = new Properties();
		try {
			props.load( 
				resourceGetter.getResourceAsStream(
						StringFormatUrlCreater.class.getName().replaceAll("\\.", "/")+".properties")
				);
		} catch (Exception e) {
			throw new RuntimeException("没配置类型的.searchUrl！");
		}
		searchUrl =  props.getProperty(type+".searchUrl");
		searchKeywordPropName = props.getProperty(type+".searchKeywordPropName");
		if(searchKeywordPropName==null || searchKeywordPropName.length()==0){
			searchKeywordPropName = "enRuleInclude";
		}
	}
}
