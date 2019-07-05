package com.gmadmin.bean;

public class RoleGoods
{
	private Integer roleid;

	private Integer goodsid;

	private Integer count;

	private String goodsname;

	public String getGoodsname()
	{
		return goodsname;
	}

	public void setGoodsname(String goodsname)
	{
		this.goodsname = goodsname;
	}

	public Integer getRoleid()
	{
		return roleid;
	}

	public void setRoleid(Integer roleid)
	{
		this.roleid = roleid;
	}

	public Integer getGoodsid()
	{
		return goodsid;
	}

	public void setGoodsid(Integer goodsid)
	{
		this.goodsid = goodsid;
	}

	public Integer getCount()
	{
		return count;
	}

	public void setCount(Integer count)
	{
		this.count = count;
	}
}