package com.gmadmin.web.goods;

import java.io.IOException;
import java.math.BigDecimal;

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
import com.gmadmin.util.Util;

/**
 * 更新物品
 */
@WebServlet("/api/UpdateGoods")
public class UpdateGoodsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateGoodsServlet()
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
		String name = input.getString("goodsname");
		int count = Integer.parseInt(input.getString("count"));
		BigDecimal rate = new BigDecimal(input.getString("rate"));
		double price = Double.parseDouble(input.getString("price"));
		int id = Integer.parseInt(input.getString("id"));

		// 获取实现类对象
		GoodsMapper daoGoods = MapperUtil.getMapper(GoodsMapper.class);
		GoodsExample goodsExample = new GoodsExample();
		Criteria where = goodsExample.createCriteria();
		where.andIdEqualTo(id);
		Goods goods = new Goods();
		goods.setCount(count);
		goods.setName(name);
		goods.setPrice(price);
		goods.setRate(rate);
		// 构造json对象，准备响应
		JSONObject jsonObject = new JSONObject();
		try
		{
			int rows = daoGoods.updateByExampleSelective(goods, goodsExample);
			if (rows != 0)
			{
				jsonObject.put("msg", "Updated");
				response.setStatus(200);
			}
			else
			{
				jsonObject.put("msg", "Error");
				response.setStatus(500);
			}
		}
		catch (Exception e)
		{
			// System.err.println(e);
			jsonObject.put("msg", "Error");
			response.setStatus(500);
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
