package ogdabou.antracker.leboncoin;

import ogdabou.antracker.common.Item;

public class LbcItem implements Item
{
	private String city;
	
	private String postalCode;
	
	private String name;
	
	private String description;
	
	private String price;
	
	private String url;
	
	public void setCity(final String text)
	{
		this.city = text;
	}
	
	public void setPostalCode(final String text)
	{
		this.postalCode = text;
	}
	
	public void setName(final String text)
	{
		this.name = text;
		
	}
	
	public void setPrice(final String text)
	{
		this.price = text;
	}
	
	public void setDescription(final String text)
	{
		this.description = text;
	}
	
	@Override
	public String toString()
	{
		return "Title: " + name + "\nPrice: " + price + "\nDescription: " + description + "\nPostalCode: " + postalCode
				+ "\nCity: " + city + "\nUrl: " + url;
	}
	
	public void setUrl(final String url)
	{
		this.url = url;
	}
}
