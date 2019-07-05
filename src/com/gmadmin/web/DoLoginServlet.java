package com.gmadmin.web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gmadmin.bean.Admin;
import com.gmadmin.bean.AdminExample;
import com.gmadmin.bean.AdminExample.Criteria;
import com.gmadmin.mapper.AdminMapper;
import com.gmadmin.util.AES;
import com.gmadmin.util.MapperUtil;
import com.gmadmin.util.Util;

/**
 * 登录逻辑，通过设置cookie实现
 */
@WebServlet("/DoLogin")
public class DoLoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoLoginServlet()
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
		// 得到前台json对象
		JSONObject input = Util.readJSONObject(request);
		String username = input.getString("username");
		String password = input.getString("password");
		String remember = "";
		if (input.containsKey("remember"))
			remember = input.getString("remember");

		// 获取实现类对象
		AdminMapper daoAdmin = MapperUtil.getMapper(AdminMapper.class);
		AdminExample adminExample = new AdminExample();
		Criteria where = adminExample.createCriteria();
		where.andUsernameEqualTo(username);

		// 构造json对象，准备响应
		JSONObject jsonObject = new JSONObject();
		// 查询
		List<Admin> result = daoAdmin.selectByExample(adminExample);
		// 用户存在
		if (result != null && result.size() > 0)
		{
			Admin admin = result.get(0);
			String cPassword = admin.getPassword(); // 数据库中的密码
			// 密码正确
			if (cPassword.equals(password))
			{
				jsonObject.put("msg", "登录成功");

				Cookie cookie = new Cookie("userName", AES.encrypt(username, "testkey"));
				Cookie cookie2 = new Cookie("password", AES.encrypt(password, "testkey"));
				// 记住密码
				if (remember.equals("on"))
				{
					// 设置cookie的存储时长
					cookie.setMaxAge(30 * 60);
					cookie2.setMaxAge(30 * 60);
				}
				else
				{
					// 浏览器关闭时清除cookie
					cookie.setMaxAge(-1);
					cookie2.setMaxAge(-1);
				}
				cookie.setPath("/");
				cookie2.setPath("/");
				// 把cookie发送给浏览器
				response.addCookie(cookie);
				response.addCookie(cookie2);
			}
			else
			{
				jsonObject.put("msg", "密码错误");
			}
		}
		else
		{
			jsonObject.put("msg", "用户不存在");
		}
		// 设置响应内容类型
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().write(jsonObject.toJSONString());
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
