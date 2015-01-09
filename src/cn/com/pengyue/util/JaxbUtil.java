package cn.com.pengyue.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/**
 * Jaxb2工具类
 * 
 * @author zhuc
 * @create 2013-3-29 下午2:40:14
 */
public class JaxbUtil {

	/**
	 * JavaBean转换成xml 默认编码UTF-8
	 * 
	 * @param obj
	 * @param writer
	 * @return
	 */
	public static String convertToXml(Object obj) {
		return convertToXml(obj, "UTF-8");
	}

	/**
	 * JavaBean转换成xml
	 * 
	 * @param obj
	 * @param encoding
	 * @return
	 */
	public static String convertToXml(Object obj, String encoding) {
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// /**
	// * xml转换成JavaBean
	// * @param xml
	// * @param c
	// * @return
	// */
	// @SuppressWarnings("unchecked")
	// public static <T> T converyToJavaBean(String file, Class<T> c) {
	// T t = null;
	// try {
	// JAXBContext context = JAXBContext.newInstance(c);
	// Unmarshaller unmarshaller = context.createUnmarshaller();
	// t = (T) unmarshaller.unmarshal(new StringReader(xml));
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//  
	// return t;
	// }

	/**
	 * docment转换成JavaBean
	 * 
	 * @param xml
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T converyToJavaBean(String file, Class<T> c) {
		try {
			return converyToJavaBean(new FileInputStream(file), c);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * docment转换成JavaBean
	 * 
	 * @param xml
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T converyToJavaBean(InputStream is, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(is);
			t = (T) unmarshaller.unmarshal(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}