package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oreilly.servlet.MultipartRequest;

// Spring .. @controller <= ������ �����ӿ�ũ�� ��Ʈ�ѷ� ��°��� �ν���(Class)
//cos => Common.id (Apatch) MultipartResolver <= xml�� �ѹ������ϸ� ���� ���ʿ䰡 ����
@WebServlet("*.list")
public class ListController extends HttpServlet {
	Gson gson = new Gson();
	
       @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	   System.out.println(req.getParameter("list����"));
    	res.setCharacterEncoding("utf-8");
    	res.setContentType("text/html");
    	req.setCharacterEncoding("utf-8");
    	
    	MultipartRequest multi = new MultipartRequest(req, req.getRealPath("/"), 300000);
    	String listString = multi.getParameter("list");
    	System.out.println(listString);
    	
    	ArrayList<MemberDTO> list = gson.fromJson(listString, 
    			new TypeToken<List<MemberDTO>>() {}.getType());
    	
    	for(int i=0; i<list.size(); i++) {
    		System.out.println(multi.getParameter(list.get(i).getName()));
    	}
    	
    	//To String <-> String, From
    	list = new ArrayList<MemberDTO>();
    	for(int i=0; i<20; i++) {
    		list.add(new MemberDTO(i, "mid"+i,"midd"+i,"mid"+i));
    	}
    	PrintWriter writer = res.getWriter();
    	writer.print(gson.toJson(list));
    	
    }
   

}
