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
		
		System.out.println("안드로이드 미들웨어 접근함");
		System.out.println(req.getServletPath());
		initMybatis();
		PrintWriter writer = resp.getWriter();
		writer.print("servlet => g");
	
		//접근하는 방법 체크하려면
		//뭘 접근해도 연결이 되나 크롬 열어서(무조건 URL로 해야됨)
		//http://localhost/01.middl/1135432432,jdsnfkfdsj
	}
	
	
		
		//if(req.getServletPath().equals("/sd45fsdf.te")) {
//			RequestDispatcher rd = req.getRequestDispatcher("inedx.jsp");
//			rd.forward(req, resp);
			//MVC 패턴 Model , View, Controller
			//Android에서 필요한건 View가 아님 응답 = 필요한 데이터를 주는것이 필요
			
		
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
