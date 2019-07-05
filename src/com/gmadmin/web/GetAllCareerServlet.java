package com.gmadmin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gmadmin.bean.RoleCareer;
import com.gmadmin.bean.RoleCareerExample;
import com.gmadmin.mapper.RoleCareerMapper;
import com.gmadmin.util.MapperUtil;

/**
 * 得到全部职业信息
 */
@WebServlet("/api/GetAllCareer")
public class GetAllCareerServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllCareerServlet()
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
		RoleCareerMapper daoCareer = MapperUtil.getMapper(RoleCareerMapper.class);
		List<RoleCareer> allCareer = daoCareer.selectByExample(null);
		// 构造json对象，准备响应
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", JSONObject.toJSON(allCareer));
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
