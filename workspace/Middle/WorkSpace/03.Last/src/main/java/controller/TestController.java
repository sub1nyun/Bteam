package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

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
public class TestController extends HttpServlet {
	
	String resource = "mybatis/config.xml";
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	SqlSession session;
 @Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		
		System.out.println("안드로이드 미들웨어 접근함");
		System.out.println(req.getServletPath());
		initMybatis();
		PrintWriter writer = resp.getWriter();
		writer.print("servlet => g");
		
	
	
	
}
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
