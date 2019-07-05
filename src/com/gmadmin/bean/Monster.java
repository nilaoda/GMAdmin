package com.gmadmin.bean;

public class Monster
{
	private Integer id;

	private String name;

	private Integer attribute;

	private Integer type;

	private String attributename;

	private String typename;

	private Integer power;

	private Integer speed;

	private Integer magic;

	public Integer getPower()
	{
		return power;
	}

	public void setPower(Integer power)
	{
		this.power = power;
	}

	public Integer getSpeed()
	{
		return speed;
	}

	public void setSpeed(Integer speed)
	{
		this.speed = speed;
	}

	public Integer getMagic()
	{
		return magic;
	}

	public void setMagic(Integer magic)
	{
		this.magic = magic;
	}

	public String getAttributename()
	{
		return attributename;
	}

	public void setAttributename(String attributename)
	{
		this.attributename = attributename;
	}

	public String getTypename()
	{
		return typename;
	}

	public void setTypename(String typename)
	{
		this.typename = typename;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name == null ? null : name.trim();
	}

	public Integer getAttribute()
	{
		return attribute;
	}

	public void setAttribute(Integer attribute)
	{
		this.attribute = attribute;
	}

	public Integer getType()
	{
		return type;
	}

	public void setType(Integer type)
	{
		this.type = type;
	}
}