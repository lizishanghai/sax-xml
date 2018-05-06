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
 * sax����xml�ĵ�
 * @author li
 *
 */
public class demo2 {
	
	@Test
	public void test() throws ParserConfigurationException, SAXException, IOException{
		//1.������������
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		//2.��ý�����
		SAXParser sp = factory.newSAXParser();
		
		//3.�õ���ȡ��
		XMLReader reader = sp.getXMLReader();
		
		//4.���� ���ݴ�����
		reader.setContentHandler(new TagValueHandler());
		
		//5.��ȡxml�ĵ�
		reader.parse("src/book.xml");
		
	}

}

//��ȡָ����ǩ��ֵ
class TagValueHandler extends DefaultHandler{

	private String currentTag;	//��ס��ǰ����������ʲô��ǩ
	private int needNumber = 2;	//��ס���ȡ�ڼ������߱�ǩ��ֵ
	private int currentNumber = 0;	//��ǰ���������ǵڼ���	
	
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