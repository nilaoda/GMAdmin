package com.gmadmin.web.role;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gmadmin.bean.Role;
import com.gmadmin.bean.RoleExample;
import com.gmadmin.bean.RoleExample.Criteria;
import com.gmadmin.mapper.RoleMapper;
import com.gmadmin.util.MapperUtil;
import com.gmadmin.util.Util;

/**
 * 添加角色
 */
@WebServlet("/api/AddRole")
public class AddRoleServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddRoleServlet()
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
		// 得到前台json
		JSONObject input = Util.readJSONObject(request);
		String roleName = input.getString("_name");
		int career = Integer.parseInt(input.getString("_career"));
		int uid = input.getIntValue("uid");
		RoleMapper daoRole = MapperUtil.getMapper(RoleMapper.class);
		Role role = new Role();
		role.setRolename(roleName);
		role.setCareer(career);
		role.setUserid(uid);

		// 构造json对象，准备响应
		JSONObject jsonObject = new JSONObject();
		try
		{
			int rows = daoRole.insertSelective(role);
			if (rows != 0)
			{
				jsonObject.put("msg", "Inserted");
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
