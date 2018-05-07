package ca.ws.dom4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class demo1 {
	
	//读取xml文档第二本书的	<title lang="en">Harry Potter</title>
	@Test
	public void read() throws DocumentException{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/book.xml"));
		
		Element root = document.getRootElement();
		Element book = root.elements("book").get(1);
		String title = book.element("title").getText();
		
		System.out.println(title);
	}


	//读取xml文档第二本书的title属性	<title lang="en">Harry Potter</title>
	@Test
	public void readAttr() throws DocumentException{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/book.xml"));
		
		Element root = document.getRootElement();
		Element book = root.elements("book").get(1);
		String value = book.element("title").attributeValue("lang");
		
		System.out.println(value);
	}

	//在第一本书上添加一个新的售价: <price>40.00</price>
	@Test
	public void add1() throws DocumentException, IOException{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/book.xml"));
		
		Element root = document.getRootElement();
		Element book = root.elements("book").get(0);
		book.addElement("price").setText("40.00");
		
		XMLWriter writer = new XMLWriter(new FileWriter("src/book.xml"));
		writer.write(document);
		writer.close();
		
	}
	

	//在指定的位置添加一个新的售价: <price>50.00</price>
	@Test
	public void add2() throws DocumentException, IOException{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/book.xml"));

		Element book = document.getRootElement().elements("book").get(0);
		List list = book.elements();
		
		Element price = DocumentHelper.createElement("price");
		price.setText("50.00");
		
		list.add(3, price);

		XMLWriter writer = new XMLWriter(new FileWriter("src/book.xml"));
		writer.write(document);
		writer.close();
		
	}	
	
	//删除上面添加的price节点: <price>50.00</price>
	@Test
	public void delete() throws DocumentException, IOException{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/book.xml"));

		Element price = document.getRootElement().elements("book").get(0).element("price");
		price.getParent().remove(price);

		XMLWriter writer = new XMLWriter(new FileWriter("src/book.xml"));
		writer.write(document);
		writer.close();
		
	}	
	
	//更新上面price节点: <price>33.00</price>
	@Test
	public void update() throws DocumentException, IOException{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/book.xml"));

		Element price = document.getRootElement().elements("book").get(0).element("price");
		price.setText("33.00");

		XMLWriter writer = new XMLWriter(new FileWriter("src/book.xml"));
		writer.write(document);
		writer.close();
		
	}	


}
