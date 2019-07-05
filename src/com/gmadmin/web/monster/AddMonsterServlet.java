package com.gmadmin.web.monster;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gmadmin.bean.Monster;
import com.gmadmin.bean.MonsterExample;
import com.gmadmin.mapper.MonsterMapper;
import com.gmadmin.util.MapperUtil;
import com.gmadmin.util.Util;

/**
 * 添加一个怪物
 */
@WebServlet("/api/AddMonster")
public class AddMonsterServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddMonsterServlet()
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
		// 定义变量
		String name = input.getString("name");
		int type = Integer.parseInt(input.getString("_type"));
		int attr = Integer.parseInt(input.getString("_attr"));
		int power = Integer.parseInt(input.getString("power"));
		int magic = Integer.parseInt(input.getString("magic"));
		int speed = Integer.parseInt(input.getString("speed"));
		// 获取实现类对象
		MonsterMapper daoGoods = MapperUtil.getMapper(MonsterMapper.class);
		Monster monster = new Monster();
		monster.setAttribute(attr);
		monster.setType(type);
		monster.setName(name);
		monster.setPower(power);
		monster.setMagic(magic);
		monster.setSpeed(speed);

		// 构造json对象，准备响应
		JSONObject jsonObject = new JSONObject();
		try
		{
			int rows = daoGoods.insertSelective(monster);
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
