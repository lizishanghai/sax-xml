package ca.ws.sax;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;


/**
 * sax解析xml文档
 * @author li
 *
 */
public class demo2 {
	
	@Test
	public void test() throws ParserConfigurationException, SAXException, IOException{
		//1.创建解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		//2.获得解析器
		SAXParser sp = factory.newSAXParser();
		
		//3.得到读取器
		XMLReader reader = sp.getXMLReader();
		
		//4.设置 内容处理器
		reader.setContentHandler(new TagValueHandler());
		
		//5.读取xml文档
		reader.parse("src/book.xml");
		
	}

}

//获取指定标签的值
class TagValueHandler extends DefaultHandler{

	private String currentTag;	//记住当前解析到的是什么标签
	private int needNumber = 2;	//记住想获取第几个作者标签的值
	private int currentNumber = 0;	//当前解析到的是第几个	
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		
		currentTag=qName;
		if(currentTag.equals("author"))
			currentNumber++;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		currentTag=null;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		
		if("author".equals(currentTag) && currentNumber==needNumber){
			System.out.println(new String(ch, start, length));
		}
	}
	
}