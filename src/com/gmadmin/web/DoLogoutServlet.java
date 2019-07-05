package com.gmadmin.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登出逻辑，清除cookie
 */
@WebServlet("/DoLogout")
public class DoLogoutServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoLogoutServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies)
		{
			if (cookie.getName().equals("userName") || cookie.getName().equals("password"))
			{
				cookie.setValue(null);
				cookie.setMaxAge(0);// 立即销毁cookie
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		response.sendRedirect("/GMAdmin/login.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
