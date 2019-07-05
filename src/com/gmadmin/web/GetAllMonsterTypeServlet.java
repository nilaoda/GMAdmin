package com.gmadmin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gmadmin.bean.MonsterType;
import com.gmadmin.mapper.MonsterTypeMapper;
import com.gmadmin.util.MapperUtil;

/**
 * 得到全部怪物类型
 */
@WebServlet("/api/GetAllMonsterType")
public class GetAllMonsterTypeServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllMonsterTypeServlet()
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
		MonsterTypeMapper dao = MapperUtil.getMapper(MonsterTypeMapper.class);
		List<MonsterType> allType = dao.selectByExample(null);
		// 构造json对象，准备响应
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", JSONObject.toJSON(allType));
		// 响应
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
