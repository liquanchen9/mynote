package cn.com.pengyue.crawler.spi;

import java.util.List;
import java.util.Properties;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import cn.com.pengyue.crawler.Parser;
import cn.com.pengyue.crawler.ResourceGetter;
import cn.com.pengyue.vo.PageInfo;
import cn.com.pengyue.vo.ws.Page;
import cn.com.pengyue.vo.ws.Rule;

public class OgnlParser implements Parser {
	
	private String parseOgnl;
	private String parseContentOgnl;
	@Override
	public List<Page> parse(String html,Rule rule,PageInfo pageInfo) {
		OgnlContext context = new OgnlContext();
		context.put("pageInfo",pageInfo);
		context.put("rule",rule);
		try {
			return (List<Page>) Ognl.getValue(parseOgnl, context, html);
		} catch (OgnlException e) {
			e.printStackTrace();
			throw new RuntimeException("配置OGNL 执行出问题，请联系开发者！！",e);
		}
	}
	
	@Override
	public String parseContent(String htmlCode) {
		try {
			return (String) Ognl.getValue(parseContentOgnl,htmlCode);
		} catch (OgnlException e) {
			e.printStackTrace();
			throw new RuntimeException("配置OGNL 执行出问题，请联系开发者！！",e);
		}
	}

	@Override
	public void init(String type, ResourceGetter resourceGetter) {
		Properties props = new Properties();
		try {
			props.load( 
				resourceGetter.getResourceAsStream(
						OgnlParser.class.getName().replaceAll("\\.", "/")+".properties")
				);
		} catch (Exception e) {
			throw new RuntimeException("没配置类型的ognl！");
		}
		parseOgnl =  props.getProperty(type+".parse");
		parseContentOgnl =  props.getProperty(type+".parseContent");
		
		if(parseContentOgnl==null ){
			parseContentOgnl = "#root";//默认就是页面代码
		}
	}
}
