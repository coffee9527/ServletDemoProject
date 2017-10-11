package com.coffee.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用request.getParameterNames()方式读取所有可用的表单参数。该方法返回一个枚举，
 * 其中包含未指定顺序的参数名。
 * 一旦我们有一个枚举，我们可以以标准方式循环枚举，使用 hasMoreElements() 方法来确定何时停止，
 * 使用 nextElement() 方法来获取每个参数的名称。
 * @author Administrator
 *
 */
@WebServlet("/readParamsServlet")
public class DReadParamsServlet extends HttpServlet {
	
	public DReadParamsServlet() {
		super();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String title = "读取所有的表单数据";
		String docType =  "<!DOCTYPE html>\n";
		PrintWriter out = resp.getWriter();
		out.println(docType +
	            "<html>\n" +
	            "<head><meta charset=\"utf-8\"><title>" + title + "</title></head>\n" +
	            "<body bgcolor=\"#f0f0f0\">\n" +
	            "<h1 align=\"center\">" + title + "</h1>\n" +
	            "<table width=\"100%\" border=\"1\" align=\"center\">\n" +
	            "<tr bgcolor=\"#949494\">\n" +
	            "<th>参数名称</th><th>参数值</th>\n"+
	            "</tr>\n");
		
		Enumeration<String> paramsEnum = req.getParameterNames();
		while(paramsEnum.hasMoreElements()) {
			String paramName = paramsEnum.nextElement();
			out.print("<tr><td>" + paramName + "</td>\n");
			
			//通过参数名得到参数值
			String[] paramValues =
		            req.getParameterValues(paramName);
			
			// 读取单个值的数据
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() == 0)
                    out.println("<td><i>没有值</i></td>");
                else
                    out.println("<td>" + paramValue + "</td>");
            }  else {
                // 读取多个值的数据
                out.println("<td><ul>");
                for(int i=0; i < paramValues.length; i++) {
                out.println("<li>" + paramValues[i]);
                }
                out.println("</ul></td>");
            }
            out.print("</tr>");
		}
		 out.println("\n</table>\n</body></html>");
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
