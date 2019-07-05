package com.gmadmin.web.monster;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gmadmin.bean.Monster;
import com.gmadmin.bean.MonsterAttribute;
import com.gmadmin.bean.MonsterExample;
import com.gmadmin.bean.MonsterExample.Criteria;
import com.gmadmin.bean.MonsterType;
import com.gmadmin.mapper.MonsterAttributeMapper;
import com.gmadmin.mapper.MonsterMapper;
import com.gmadmin.mapper.MonsterTypeMapper;
import com.gmadmin.util.MapperUtil;
import com.gmadmin.util.PageHelper;

/*
 * 得到全部怪物信息
 */

@WebServlet("/api/GetAllMonsters")
public class GetAllMonstersServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		int page = -1;
		int limit = -1;
		// 尝试从前台得到分页参数
		if (req.getParameter("page") != null && req.getParameter("limit") != null)
		{
			page = Integer.parseInt(req.getParameter("page"));
			limit = Integer.parseInt(req.getParameter("limit"));
		}
		// 获取实现类对象
		MonsterMapper daoMonster = MapperUtil.getMapper(MonsterMapper.class);
		// 添加条件
		MonsterExample monsterExample = new MonsterExample();
		Criteria where = monsterExample.createCriteria();
		// 查询
		List<Monster> allMonster = daoMonster.selectByExample(monsterExample);
		// 如果有分页参数则从所有怪物中筛选一部分
		if (page != -1 && limit != -1)
			allMonster = PageHelper.SelectList(allMonster, page, limit);
		// 查询怪物的属性
		MonsterAttributeMapper daoAttr = MapperUtil.getMapper(MonsterAttributeMapper.class);
		MonsterTypeMapper daoType = MapperUtil.getMapper(MonsterTypeMapper.class);
		for (Monster monster : allMonster)
		{
			MonsterAttribute monsterAttribute = daoAttr.selectByPrimaryKey(monster.getAttribute());
			MonsterType monsterType = daoType.selectByPrimaryKey(monster.getType());
			monster.setAttributename(monsterAttribute.getName());
			monster.setTypename(monsterType.getName());
		}

		// 查询共有多少怪物
		int MonsterCount = daoMonster.countByExample(monsterExample);
		// 构造json对象，准备响应
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "OK");
		jsonObject.put("count", MonsterCount);
		jsonObject.put("data", JSONObject.toJSON(allMonster));
		// 响应
		// 设置响应内容类型
		resp.setContentType("application/json; charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.getWriter().write(jsonObject.toJSONString());
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
