package com.gmadmin.web.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gmadmin.bean.UserExample;
import com.gmadmin.bean.UserExample.Criteria;
import com.gmadmin.mapper.UserMapper;
import com.gmadmin.util.MapperUtil;

/*
 * 删除用户
 */

@WebServlet("/api/DelUser")
public class DelUserServlet extends HttpServlet
{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// 获取实现类对象
		UserMapper daoUser = MapperUtil.getMapper(UserMapper.class);
		// 添加条件
		UserExample userExample = new UserExample();
		Criteria where = userExample.createCriteria();
		// 新建JSON对象
		JSONObject jsonObject = new JSONObject();
		// 删除用户
		if (req.getParameter("uid") != null)
		{
			if (!req.getParameter("uid").equals(""))
			{

				int int_uid = Integer.parseInt(req.getParameter("uid"));
				where.andUidEqualTo(int_uid);
				int delUser = daoUser.deleteByExample(userExample);
				if (delUser != 0)
				{
					jsonObject.put("msg", "Deleted");
					resp.setStatus(200);
				}
				else
				{
					jsonObject.put("msg", "Error");
					resp.setStatus(500); // 删除失败
				}
			}
		}
		else
		{
			jsonObject.put("msg", "Error");
			resp.setStatus(500); // 删除失败
		}
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.getWriter().write(jsonObject.toJSONString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
