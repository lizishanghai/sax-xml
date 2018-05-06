package ca.ws.sax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class demo3 {
	
	@Test
	public void test() throws ParserConfigurationException, SAXException, IOException{
		//1.������������
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		//2.��ý�����
		SAXParser sp = factory.newSAXParser();
		
		//3.�õ���ȡ��
		XMLReader reader = sp.getXMLReader();
		
		//4.���� ���ݴ�����
		BeanListHandler handler = new BeanListHandler();
		reader.setContentHandler(handler);
		
		//5.��ȡxml�ĵ�
		reader.parse("src/book.xml");
		
		List<Book> list =handler.getBooks();
		System.out.println(list);
	}

}

//��xml�ĵ��е�ÿһ�����װ��һ��book���󣬲��Ѷ���������һ��list�����з���
class BeanListHandler extends DefaultHandler{

	private List list = new ArrayList();
	private String currentTag;
	private Book book;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		
		currentTag = qName;
		if("book".equals(currentTag)){
			book = new Book();
			
		}
	}

	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);

		if("title".equals(currentTag)){
			String title = new String(ch, start, length); 
			book.setTitle(title);
		}
		if("author".equals(currentTag)){
			String author = new String(ch, start, length); 
			book.setAuthor(author);
		}
		if("price".equals(currentTag)){
			String price = new String(ch, start, length); 
			book.setPrice(price);;
		}
		if("year".equals(currentTag)){
			String year = new String(ch, start, length); 
			book.setYear(year);;
		}
	}
	
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		
		if("book".equals(qName)){
			list.add(book);
			book = null;
		}
		currentTag = null;		
		
	}
	
	public List getBooks(){
		return list;
	}
}