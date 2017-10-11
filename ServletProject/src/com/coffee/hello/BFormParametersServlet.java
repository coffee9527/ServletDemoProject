package com.coffee.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 通过Servlet获取表单中GET方式提交过来参数名和参数值
 * 
 * Servlet 处理表单数据，这些数据会根据不同的情况使用不同的方法自动解析：
 * getParameter(String args)：您可以调用 request.getParameter() 方法来获取表单参数的值。
 * getParameterValues()：如果参数出现一次以上，则调用该方法，并返回多个值，例如复选框。
 * getParameterNames(String args)：如果您想要得到当前请求中的所有参数的完整列表，则调用该方法。
 * 
 * @author Administrator
 *
 */
@WebServlet("/formParametersServlet")
public class BFormParametersServlet extends HttpServlet {
	private static final long serialVersionUID = 1l;
	
	public BFormParametersServlet() {
		super();
	}
	
	/**
	 * 中文乱码问题
	 * 	出现中文乱码的问题有很多，以前在学习php的时候乱码的问题近乎没有多少，php在这方面还是处理的比较好的，但在javaee的servlet中中文乱码的问题比较好，而出现的原因也比较多，下面做一下简单的总结：
	 *	按照数据传送的方式我分为了两类：POST和GET传送数据，当然了还有其它的传输方式，但是这两种是我们最常见的。
		1：以POST传送数据的方式：
		这种方式相对于get传送的处理要简单一些，首先在浏览器端如用户填写的表单以post的方式传送到servlet，这是的数据只要经过utf-8转码就可以时间正确的编码：
		request.setCharacterEncoding("utf-8");就可以在服务器端获取到正确的码。
		2：这种方式较为麻烦一点，以get传送数据的话会把数据编码会转换为iso-8859-1编码，所以在servlet接收到数据之后，需要转换成正确的编码，可以使用类似：
		String newusername=new String(username.getBytes("iso-8859-1"),"utf-8");的方式转换编码。
		3：上面的两种方式的编码确实是正确了，但也只是在服务器端是正确的，因为服务器的数据如果返回给浏览器，还是需要再次进行转码的。这个相对简单一一些：
		response.setContentType("text/html;charset=utf-8"); 但是需要注意的是：get和post方法都要去设置返回数据的编码方式，否则单方面还是不可行的。
		
		建议一下：最好把复杂的整理乱码方案封装成为工具类，减少代码量。
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置响应内容类型,get请求要设置相应类型和编码
		resp.setContentType("text/html;charset=utf-8");
		//req.setCharacterEncoding("utf-8"); 
		//resp.setContentType("text/html;charset=utf-8"); 
		//resp.setCharacterEncoding("utf-8"); 
		
		PrintWriter out = resp.getWriter();
		String title = "使用GET方法读取表单数据";
		//处理中文
		//System.out.println(req.getParameter("name"));
		//String name = new String(req.getParameter("name").getBytes("iso-8859-1"),"utf-8");
		String name = req.getParameter("name");
		String docType = "<!DOCTYPE html> \n";
		 out.println(docType +
		            "<html>\n" +
		            "<head><title>" + title + "</title></head>\n" +
		            "<body bgcolor=\"#f0f0f0\">\n" +
		            "<h1 align=\"center\">" + title + "</h1>\n" +
		            "<ul>\n" +
		            "  <li><b>站点名</b>："
		            + name + "\n" +
		            "  <li><b>网址</b>："
		            + req.getParameter("url") + "\n" +
		            "</ul>\n" +
		            "</body></html>");
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
