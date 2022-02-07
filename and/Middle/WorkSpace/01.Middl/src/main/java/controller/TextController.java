package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("*.te")
public class TextController extends HttpServlet {
	String resource = "mybatis/config.xml";
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	SqlSession session;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		//oracle.jdbc.driver.OracleDriver
		//SqlSession
		
		System.out.println("�ȵ���̵� �̵���� ������");
		System.out.println(req.getServletPath());
		initMybatis();
		PrintWriter writer = resp.getWriter();
		writer.print("servlet => g");
	
		//�����ϴ� ��� üũ�Ϸ���
		//�� �����ص� ������ �ǳ� ũ�� ���(������ URL�� �ؾߵ�)
		//http://localhost/01.middl/1135432432,jdsnfkfdsj
	}
	
	
		
		//if(req.getServletPath().equals("/sd45fsdf.te")) {
//			RequestDispatcher rd = req.getRequestDispatcher("inedx.jsp");
//			rd.forward(req, resp);
			//MVC ���� Model , View, Controller
			//Android���� �ʿ��Ѱ� View�� �ƴ� ���� = �ʿ��� �����͸� �ִ°��� �ʿ�
			
		
	//}
       
	public void initMybatis() {
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
			int testInt = session.selectOne("mybatis.test.select");
			System.out.println(testInt);
			session.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
