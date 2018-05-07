package ca.ws.dom4j;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
 * ����xml�ĵ����Ƿ��к��û���ƥ����û���������
 * @author li
 *
 */
public class demo3 {
	
	String username = "aaa";
	String password = "123";
	
	@Test
	public void test() throws DocumentException{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/users.xml"));
		
		Node node = document.selectSingleNode("//user[@username='"+username+"' and @password='"+password+"']");
		if(node == null){
			System.out.println("�û��������������");
		}else{
			System.out.println("��½�ɹ�");
		}
	}

}
