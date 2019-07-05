package com.gmadmin.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmadmin.bean.Admin;
import com.gmadmin.bean.AdminExample;
import com.gmadmin.bean.AdminExample.Criteria;
import com.gmadmin.mapper.AdminMapper;
import com.gmadmin.util.AES;
import com.gmadmin.util.MapperUtil;

/**
 * 过滤器=>在用户未登录的情况下，跳转会login.html页面
 *         在用户已登录的情况下，如果用户访问login.html则跳转回主页面
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter
{

	/**
	 * Default constructor.
	 */
	public LoginFilter()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		// 放行登录请求
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		if (uri.contains("/GMAdmin/layui/") || uri.contains("/GMAdmin/js/") || uri.equals("/GMAdmin/login.html")
				|| uri.equals("/GMAdmin/DoLogin"))
		{
			// 如果用户已登录，跳转回主页面
			if (uri.equals("/GMAdmin/login.html"))
			{
				Cookie[] cookies = req.getCookies();
				if (cookies != null && cookies.length > 0)
				{
					for (Cookie cookie : cookies)
					{
						if ("userName".equals(cookie.getName()))
						{
							if (!"".equals(cookie.getValue()))
								resp.sendRedirect("/GMAdmin/main.html");
						}
					}
				}
			}
			chain.doFilter(request, response);
			return;
		}

		// 不是登陆请求的话，判断是否有cookie
		Cookie[] cookies = req.getCookies();
		if (cookies != null && cookies.length > 0)
		{
			String userName = null;
			String password = null;
			// 判断cookie中的用户名和密码是否和数据库中的一致，如果一致则放行，否则转发请求到登录页面
			for (Cookie cookie : cookies)
			{
				if ("userName".equals(cookie.getName()))
				{
					if (!"".equals(cookie.getValue()))
						userName = AES.decrypt(cookie.getValue(), "testkey");
				}
				if ("password".equals(cookie.getName()))
				{
					if (!"".equals(cookie.getValue()))
						password = AES.decrypt(cookie.getValue(), "testkey");
				}
			}

			if (userName != null && password != null)
			{
				// 查找数据库中信息

				// 获取实现类对象
				AdminMapper daoAdmin = MapperUtil.getMapper(AdminMapper.class);
				AdminExample adminExample = new AdminExample();
				Criteria where = adminExample.createCriteria();
				where.andUsernameEqualTo(userName);
				where.andPasswordEqualTo(password);
				// 查询
				List<Admin> result = daoAdmin.selectByExample(adminExample);
				if (result != null && result.size() > 0)
				{
					// 验证成功
					chain.doFilter(request, response);
					return;
				}
				else
				{
					// 重定向到登陆界面
					resp.sendRedirect("/GMAdmin/login.html");
					return;
				}
			}
			else
			{
				resp.sendRedirect("/GMAdmin/login.html");
				return;
			}
		}
		else
		{
			resp.sendRedirect("/GMAdmin/login.html");
			return;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
		// TODO Auto-generated method stub
	}

}
