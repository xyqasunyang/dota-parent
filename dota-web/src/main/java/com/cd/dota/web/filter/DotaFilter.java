package com.cd.dota.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cd.dota.common.DotaConstant;

@WebFilter(filterName = "dotaFilter", urlPatterns = "/*")
public class DotaFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if(httpRequest.getSession().getAttribute(DotaConstant.LOGINSESSION)==null){
			//这些页面不能访问
			if(httpRequest.getRequestURI().endsWith("/addArticle.html")||
					httpRequest.getRequestURI().endsWith("/articleManage.html")
					){
				httpResponse.sendRedirect("/admin/admin.html");
				return;
			}
		}
		
		if(httpRequest.getRequestURI().equals("/")){
			httpResponse.sendRedirect("/blogs/index.html");
			return;
		}
		String str = String.valueOf(httpRequest.getSession().getAttribute(httpRequest.getLocalAddr()));
		if (!str.equals("null") && httpRequest.getServletPath().endsWith("/robot.do")) {
			Long interval = Long.valueOf(str);
			if ((new Date().getTime() - interval) / 500 < 1) {
				return;
			}
		}
		httpRequest.getSession().setAttribute(httpRequest.getLocalAddr(), new Date().getTime());
		if (httpRequest.getLocalAddr().equals("125.119.96.148")) {
			httpResponse.sendRedirect("http://www.163.com");
			return;
		}

		chain.doFilter(request, response);
		return;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}
