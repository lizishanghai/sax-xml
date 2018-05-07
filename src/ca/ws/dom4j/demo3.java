package ca.ws.dom4j;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
 * 查找xml文档中是否有和用户相匹配的用户名和密码
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
			System.out.println("用户名或者密码错误");
		}else{
			System.out.println("登陆成功");
		}
	}

}
