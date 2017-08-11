package com.vitaltechlabs.findhostels.model;

/**
 * Created by Shiva on 8/2/2017.
 */

public class HostelsListData
{
	private String hostel_id;
	private String hostelname;
	private String address;
	private String area;
	private String city;
	private String gender;

	public HostelsListData(final String hostel_id, final String hostelname, final String address, final String area, final String city, final String gender)
	{
		this.hostel_id = hostel_id;
		this.hostelname = hostelname;
		this.address = address;
		this.area = area;
		this.city = city;
		this.gender = gender;
	}

	public String getHostel_id()
	{
		return hostel_id;
	}

	public void setHostel_id(final String hostel_id)
	{
		this.hostel_id = hostel_id;
	}

	public String getHostelname()
	{
		return hostelname;
	}

	public void setHostelname(final String hostelname)
	{
		this.hostelname = hostelname;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(final String address)
	{
		this.address = address;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(final String area)
	{
		this.area = area;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(final String city)
	{
		this.city = city;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(final String gender)
	{
		this.gender = gender;
	}
}
