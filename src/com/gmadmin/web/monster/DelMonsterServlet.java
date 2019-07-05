package com.gmadmin.web.monster;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gmadmin.mapper.MonsterMapper;
import com.gmadmin.util.MapperUtil;

/**
 * 删除怪物
 */
@WebServlet("/api/DelMonster")
public class DelMonsterServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DelMonsterServlet()
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
		int id = -1;
		// 新建JSON对象
		JSONObject jsonObject = new JSONObject();
		// 获取实现类对象
		MonsterMapper daoMonster = MapperUtil.getMapper(MonsterMapper.class);
		if (request.getParameter("id") != null && !"".equals(request.getParameter("id")))
		{
			id = Integer.parseInt(request.getParameter("id"));
		}
		try
		{
			int rows = daoMonster.deleteByPrimaryKey(id);
			if (rows != 0)
			{
				jsonObject.put("msg", "Deleted");
				response.setStatus(200);
			}
			else
			{
				jsonObject.put("msg", "Error");
				response.setStatus(500); // 删除失败
			}
		}
		catch (Exception e)
		{
			jsonObject.put("msg", "Error");
			response.setStatus(500); // 删除失败
		}
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
