package com.gmadmin.web.user;

import java.io.IOException;
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

/**
 * 更新角色
 */
@WebServlet("/api/UpdateUser")
public class UpdateUserServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserServlet()
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
		// 定义变量
		String username = input.getString("username");
		String password = input.getString("password");
		String email = input.getString("email");
		int uid = Integer.parseInt(input.getString("uid"));
		// 获取实现类对象
		UserMapper daoUser = MapperUtil.getMapper(UserMapper.class);
		// 添加条件
		UserExample userExample = new UserExample();
		Criteria where = userExample.createCriteria();
		where.andUidEqualTo(uid);
		// 实体类对象
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setUsername(username);

		// 构造json对象，准备响应
		JSONObject jsonObject = new JSONObject();
		try
		{
			int rows = daoUser.updateByExampleSelective(user, userExample);
			if (rows != 0)
			{
				jsonObject.put("msg", "Updated");
				response.setStatus(200);
			}
			else
			{
				jsonObject.put("msg", "Error");
				response.setStatus(500); // 添加失败
			}
		}
		catch (Exception e)
		{
			// System.err.println(e);
			jsonObject.put("msg", "Error");
			response.setStatus(500); // 添加失败
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
