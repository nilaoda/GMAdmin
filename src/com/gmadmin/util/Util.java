package com.gmadmin.util;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public class Util
{
	// 获取request流解析为字符串
	public static JSONObject readJSONObject(HttpServletRequest request)
	{
		StringBuffer json = new StringBuffer();
		String line = null;
		try
		{
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
			{
				json.append(line);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
		return JSONObject.parseObject(json.toString());
	}
}
