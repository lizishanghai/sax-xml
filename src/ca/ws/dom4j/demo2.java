package ca.ws.dom4j;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
 * 应用xpath提取xml文档的数据
 * @author li
 *
 */
public class demo2 {
	
	@Test
	public  void test() throws DocumentException{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/book.xml"));
		
		String value = document.selectSingleNode("//author").getText();
		System.out.println(value);
	}

}
