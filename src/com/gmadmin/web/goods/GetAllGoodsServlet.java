package com.gmadmin.web.goods;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gmadmin.bean.Goods;
import com.gmadmin.bean.GoodsExample;
import com.gmadmin.bean.GoodsExample.Criteria;
import com.gmadmin.mapper.GoodsMapper;
import com.gmadmin.util.MapperUtil;
import com.gmadmin.util.PageHelper;

/*
 * 得到全部物品信息
 */

@WebServlet("/api/GetAllGoods")
public class GetAllGoodsServlet extends HttpServlet
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
		GoodsMapper daoGoods = MapperUtil.getMapper(GoodsMapper.class);
		// 添加条件
		GoodsExample goodsExample = new GoodsExample();
		Criteria where = goodsExample.createCriteria();
		// 查询
		List<Goods> allGoods = daoGoods.selectByExample(goodsExample);
		// 如果有分页参数则从所有角色中筛选一部分
		if (page != -1 && limit != -1)
			allGoods = PageHelper.SelectList(allGoods, page, limit);
		// 查询共有多少角色
		int goodsCount = daoGoods.countByExample(goodsExample);
		// 构造json对象，准备响应
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "OK");
		jsonObject.put("count", goodsCount);
		jsonObject.put("data", JSONObject.toJSON(allGoods));
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
