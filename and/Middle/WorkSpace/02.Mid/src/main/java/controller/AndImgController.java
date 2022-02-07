package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;

import dto.AndImgDTO;


@WebServlet("*.amg")
public class AndImgController extends HttpServlet {
	String resource = "mybatis/config.xml"; //�����κ�
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	SqlSession session;
  @Override
protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		initMybatis();
		
		
		Gson gson = new Gson();
		String rtnData = gson.toJson(initMybatis());
		System.out.println(rtnData);
		//MultipartRequest multi = new MultipartRequest(req, req.getRealPath("/"), 300000);
		//�ȵ���̵�� ���� �Ķ���͸� �Է¹ް� �Ǹ� ó���ؾ��� �κ� ��
		PrintWriter writer = res.getWriter();
		writer.print(rtnData);
		
		
		
}
  
	public List<AndImgDTO> initMybatis() {
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
			//���� �����
			List<AndImgDTO> list = session.selectList("and.mapper.select");
//			for (AndImgDTO andImgDTO : list) {
//				System.out.println(andImgDTO.getImg_url());
//				System.out.println(andImgDTO.getImg_id());
//			}
			session.close();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}