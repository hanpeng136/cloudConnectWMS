package com.hanpeng.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "MyLoginFilter", urlPatterns = "*.do")
public class MyLoginFilter implements Filter {

	/**
	 * 使用注解定义编码过滤器
	 * 
	 * @author hp
	 */

	/**
	 * @WebFilter将一个实现了javax.servlet.Filte接口的类定义为过滤器组件 属性filterName声明过滤器的名称,可选
	 *                                                 属性urlPatterns指定要过滤
	 *                                                 的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("执行了销毁函数");

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// System.out.println("执行了拦截器"+request.getAttribute("infoList"));
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		ServletContext application = session.getServletContext();
		// System.out.println(request.getRequestURI());
		System.out.println("==========" + session.getAttribute("userInfo"));
		String url = request.getRequestURI();
		if (url.endsWith("login.jsp") || url.endsWith("index.jsp") || url.endsWith("login.do")||url.endsWith("faceRecognition.do")
				||url.endsWith("validatePhone.do")||url.endsWith("validateCode.do")||url.endsWith("sentCode.do")||url.endsWith("saveUser.do")) {
			chain.doFilter(request, response);
			return;
		}
		if (null == session.getAttribute("username")) {
			response.sendRedirect("login.jsp");
		} else {
			chain.doFilter(request, response);
		}
		// if(session.getAttribute("userInfo")==null
		// && !request.getRequestURI().equals("/login/login.jsp")
		// && !request.getRequestURI().equals("/login/logining.jsp")){
		// System.out.println("userInfo是空的");
		// //response.sendRedirect("login.we");
		// PrintWriter out = response.getWriter();
		// out.write("<script>window.parent.location.href='login.hp'</script>");
		// return;
		// }else{
		// System.out.println(session.getAttribute("userInfo"));
		// chain.doFilter(request, response);

		// }
		// chain.doFilter(request, response);

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("执行了拦截器初始化");
	}

}
