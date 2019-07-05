package com.gmadmin.web.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gmadmin.bean.User;
import com.gmadmin.bean.UserExample;
import com.gmadmin.bean.UserExample.Criteria;
import com.gmadmin.mapper.UserMapper;
import com.gmadmin.util.MapperUtil;
import com.gmadmin.util.Util;
/*
 * 添加用户
 */

@WebServlet("/api/AddUser")
public class AddUserServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// 得到前台参数
		JSONObject input = Util.readJSONObject(req);

		// 定义变量
		String username = input.getString("username");
		String password = input.getString("password");
		String email = input.getString("email");

		// 获取实现类对象
		UserMapper daoUser = MapperUtil.getMapper(UserMapper.class);
		// 添加条件
		UserExample userExample = new UserExample();
		Criteria where = userExample.createCriteria();
		// 构造json对象，准备响应
		JSONObject jsonObject = new JSONObject();

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);

		try
		{
			Integer rows = daoUser.insertSelective(user);
			if (rows != 0)
			{
				jsonObject.put("msg", "Inserted");
				resp.setStatus(200);
			}
			else
			{
				jsonObject.put("msg", "Error");
				resp.setStatus(500); // 添加失败
			}
		}
		catch (Exception e)
		{
			jsonObject.put("msg", "Error");
			resp.setStatus(500); // 添加失败
		}

		// 设置响应内容类型
		resp.setContentType("application/json; charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.getWriter().write(jsonObject.toJSONString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		doGet(request, response);
	}

}
