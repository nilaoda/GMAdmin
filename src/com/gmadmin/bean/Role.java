package com.gmadmin.bean;

public class Role
{
	private Integer id;

	private String rolename;

	private Integer career;

	private Integer money;

	private Integer gamecoin;

	private Integer daycontinuous;

	private Integer daytotal;

	private Integer userid;

	private String careername;

	public String getCareername()
	{
		return careername;
	}

	public void setCareername(String careername)
	{
		this.careername = careername;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getRolename()
	{
		return rolename;
	}

	public void setRolename(String rolename)
	{
		this.rolename = rolename == null ? null : rolename.trim();
	}

	public Integer getCareer()
	{
		return career;
	}

	public void setCareer(Integer career)
	{
		this.career = career;
	}

	public Integer getMoney()
	{
		return money;
	}

	public void setMoney(Integer money)
	{
		this.money = money;
	}

	public Integer getGamecoin()
	{
		return gamecoin;
	}

	public void setGamecoin(Integer gamecoin)
	{
		this.gamecoin = gamecoin;
	}

	public Integer getDaycontinuous()
	{
		return daycontinuous;
	}

	public void setDaycontinuous(Integer daycontinuous)
	{
		this.daycontinuous = daycontinuous;
	}

	public Integer getDaytotal()
	{
		return daytotal;
	}

	public void setDaytotal(Integer daytotal)
	{
		this.daytotal = daytotal;
	}

	public Integer getUserid()
	{
		return userid;
	}

	public void setUserid(Integer userid)
	{
		this.userid = userid;
	}
}