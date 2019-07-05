package com.gmadmin.web.role;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gmadmin.bean.Role;
import com.gmadmin.bean.RoleCareer;
import com.gmadmin.bean.RoleCareerExample;
import com.gmadmin.bean.RoleExample;
import com.gmadmin.bean.RoleExample.Criteria;
import com.gmadmin.mapper.RoleCareerMapper;
import com.gmadmin.mapper.RoleMapper;
import com.gmadmin.util.MapperUtil;
import com.gmadmin.util.PageHelper;

/*
 * 得到全部角色信息
 */

@WebServlet("/api/GetAllRoles")
public class GetAllRolesServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		int page = -1;
		int limit = -1;
		int uid = 0;
		// 尝试从前台得到分页参数
		if (req.getParameter("page") != null && req.getParameter("limit") != null)
		{
			page = Integer.parseInt(req.getParameter("page"));
			limit = Integer.parseInt(req.getParameter("limit"));
		}
		// 获取实现类对象
		RoleMapper daoRoles = MapperUtil.getMapper(RoleMapper.class);
		// 添加条件
		RoleExample rolesExample = new RoleExample();
		Criteria where = rolesExample.createCriteria();
		// 根据UID返回角色
		if (req.getParameter("uid") != null && !"".equals(req.getParameter("uid").trim()))
		{
			uid = Integer.parseInt(req.getParameter("uid").trim());
			where.andUseridEqualTo(uid);
		}
		if (req.getParameter("rolename") != null && !"".equals(req.getParameter("rolename").trim()))
		{
			where.andRolenameLike("%" + req.getParameter("rolename").trim() + "%");
		}
		if (req.getParameter("career") != null && !"".equals(req.getParameter("career").trim()))
		{
			String careerName = req.getParameter("career").trim();
			RoleCareerMapper dao = MapperUtil.getMapper(RoleCareerMapper.class);
			RoleCareerExample tExample = new RoleCareerExample();
			com.gmadmin.bean.RoleCareerExample.Criteria tWhere = tExample.createCriteria();
			tWhere.andCareerEqualTo(careerName);
			if (dao.selectByExample(tExample).size() > 0)
				where.andCareerEqualTo(dao.selectByExample(tExample).get(0).getId());
		}
		if (req.getParameter("min_money") != null && !"".equals(req.getParameter("min_money").trim()))
		{
			int min_money = Integer.parseInt(req.getParameter("min_money").trim());
			where.andMoneyGreaterThanOrEqualTo(min_money);
		}
		if (req.getParameter("max_money") != null && !"".equals(req.getParameter("max_money").trim()))
		{
			int max_money = Integer.parseInt(req.getParameter("max_money").trim());
			where.andMoneyLessThanOrEqualTo(max_money);
		}
		if (req.getParameter("min_gamecoin") != null && !"".equals(req.getParameter("min_gamecoin").trim()))
		{
			int min_gamecoin = Integer.parseInt(req.getParameter("min_gamecoin").trim());
			where.andGamecoinGreaterThanOrEqualTo(min_gamecoin);
		}
		if (req.getParameter("max_gamecoin") != null && !"".equals(req.getParameter("max_gamecoin").trim()))
		{
			int max_gamecoin = Integer.parseInt(req.getParameter("max_gamecoin").trim());
			where.andGamecoinLessThanOrEqualTo(max_gamecoin);
		}
		if (req.getParameter("min_daycontinuous") != null && !"".equals(req.getParameter("min_daycontinuous").trim()))
		{
			int min_daycontinuous = Integer.parseInt(req.getParameter("min_daycontinuous").trim());
			where.andDaycontinuousGreaterThanOrEqualTo(min_daycontinuous);
		}
		if (req.getParameter("max_daycontinuous") != null && !"".equals(req.getParameter("max_daycontinuous").trim()))
		{
			int max_daycontinuous = Integer.parseInt(req.getParameter("max_daycontinuous").trim());
			where.andDaycontinuousLessThanOrEqualTo(max_daycontinuous);
		}
		if (req.getParameter("min_daytotal") != null && !"".equals(req.getParameter("min_daytotal").trim()))
		{
			int min_daytotal = Integer.parseInt(req.getParameter("min_daytotal").trim());
			where.andDaytotalGreaterThanOrEqualTo(min_daytotal);
		}
		if (req.getParameter("max_daytotal") != null && !"".equals(req.getParameter("max_daytotal").trim()))
		{
			int max_daytotal = Integer.parseInt(req.getParameter("max_daytotal").trim());
			where.andDaytotalLessThanOrEqualTo(max_daytotal);
		}
		// 查询
		List<Role> allRoles = daoRoles.selectByExample(rolesExample);
		// 如果有分页参数则从所有角色中筛选一部分
		if (page != -1 && limit != -1)
			allRoles = PageHelper.SelectList(allRoles, page, limit);
		// 查找角色职业名
		RoleCareerMapper daoRoleCareer = MapperUtil.getMapper(RoleCareerMapper.class);
		for (Role role : allRoles)
		{
			RoleCareer selectByExample = daoRoleCareer.selectByPrimaryKey(role.getCareer());
			role.setCareername(selectByExample.getCareer());
		}

		// 查询共有多少角色
		int RolesCount = daoRoles.countByExample(rolesExample);
		// 构造json对象，准备响应
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "OK");
		jsonObject.put("count", RolesCount);
		jsonObject.put("data", JSONObject.toJSON(allRoles));
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
