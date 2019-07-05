package com.gmadmin.util;

import java.util.ArrayList;
import java.util.List;

import com.gmadmin.bean.Goods;
import com.gmadmin.bean.Monster;
import com.gmadmin.bean.Role;
import com.gmadmin.bean.User;

/*
 * 分页功能类
 */

public class PageHelper
{
	public static <T> List<T> SelectList(List<T> list, int page, int limit)
	{
		List<T> tmp = new ArrayList<T>();
		for (int i = 1; i <= list.size(); i++)
		{
			if (i > (page - 1) * limit && i < (page * limit + 1))
			{
				tmp.add(list.get(i - 1));
			}
		}
		return tmp;
	}
}
