package com.gmadmin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gmadmin.bean.Goods;
import com.gmadmin.bean.RoleGoods;
import com.gmadmin.bean.RoleGoodsExample;
import com.gmadmin.bean.RoleGoodsExample.Criteria;
import com.gmadmin.mapper.GoodsMapper;
import com.gmadmin.mapper.RoleGoodsMapper;
import com.gmadmin.util.MapperUtil;

/**
 * 得到全部角色物品表信息
 */
@WebServlet("/api/GetRoleGoods")
public class GetRoleGoodsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetRoleGoodsServlet()
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
		RoleGoodsMapper dao = MapperUtil.getMapper(RoleGoodsMapper.class);
		RoleGoodsExample roleGoodsExample = new RoleGoodsExample();
		Criteria where = roleGoodsExample.createCriteria();
		if (request.getParameter("roleid") != null && !"".equals(request.getParameter("roleid")))
		{
			int roleId = Integer.parseInt(request.getParameter("roleid"));
			where.andRoleidEqualTo(roleId);
		}
		List<RoleGoods> result = dao.selectByExample(roleGoodsExample);
		for (RoleGoods roleGoods : result)
		{
			GoodsMapper daoGoods = MapperUtil.getMapper(GoodsMapper.class);
			Goods tmp = daoGoods.selectByPrimaryKey(roleGoods.getGoodsid());
			roleGoods.setGoodsname(tmp.getName());
		}
		// 构造json对象，准备响应
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "OK");
		jsonObject.put("data", JSONObject.toJSON(result));
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
