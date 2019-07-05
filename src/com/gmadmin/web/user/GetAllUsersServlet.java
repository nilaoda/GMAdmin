package com.gmadmin.web.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gmadmin.bean.User;
import com.gmadmin.bean.UserExample;
import com.gmadmin.bean.UserExample.Criteria;
import com.gmadmin.mapper.UserMapper;
import com.gmadmin.util.MapperUtil;
import com.gmadmin.util.PageHelper;

/*
 * 得到全部用户信息
 */

@WebServlet("/api/GetAllUsers")
public class GetAllUsersServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		int page = -1;
		int limit = -1;
		String uid = "";
		String username = "";
		String email = "";

		// 尝试从前台得到分页参数
		if (req.getParameter("page") != null && req.getParameter("limit") != null)
		{
			page = Integer.parseInt(req.getParameter("page"));
			limit = Integer.parseInt(req.getParameter("limit"));
		}
		// 获取实现类对象
		UserMapper daoUser = MapperUtil.getMapper(UserMapper.class);
		// 添加条件
		UserExample userExample = new UserExample();
		Criteria where = userExample.createCriteria();
		// 查询UID
		if (req.getParameter("uid") != null)
		{
			if (!req.getParameter("uid").equals(""))
			{

				int int_uid = Integer.parseInt(req.getParameter("uid").trim());
				where.andUidEqualTo(int_uid);
			}
		}

		// 查询用户名
		if (req.getParameter("username") != null)
		{
			if (!req.getParameter("username").equals(""))
			{
				username = req.getParameter("username").trim();
				username = "%" + username + "%";
				where.andUsernameLike(username);
			}
		}
		// 查询邮箱
		if (req.getParameter("email") != null)
		{
			if (!req.getParameter("email").equals(""))
			{
				email = req.getParameter("email").trim();
				email = "%" + email + "%";
				where.andEmailLike(email);
			}
		}

		// 查询所有
		List<User> allUsers = daoUser.selectByExample(userExample);
		// 如果有分页参数则从所有用户中筛选一部分
		if (page != -1 && limit != -1)
			allUsers = PageHelper.SelectList(allUsers, page, limit);
		// 查询共有多少用户
		int usersCount = daoUser.countByExample(userExample);
		// 构造json对象，准备响应
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 0);
		jsonObject.put("msg", "OK");
		jsonObject.put("count", usersCount);
		jsonObject.put("data", JSONObject.toJSON(allUsers));
		// 响应
		// 设置响应内容类型
		resp.setContentType("application/json; charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.getWriter().write(jsonObject.toJSONString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doGet(req, resp);
	}
}