package com.yc.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RightFilter implements Filter{

	

	@Override
	public void doFilter(ServletRequest request, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		if(req.getSession().getAttribute("uname")!=null){
			arg2.doFilter(request, arg1);
		}else{
			req.getSession().setAttribute("errmsg", "请先登录");
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	@Override
	public void destroy() {
		
	}

}
