package ogdabou.antracker.common;

/**
 * All items should have those parameters.
 * 
 * @author Axel COUTY (axel.couty@thalesgroup.com)
 * 
 */
public interface Item
{
	public void setName(final String text);
	
	public void setPrice(final String text);
	
	public void setDescription(final String text);
	
	public String toString();
	
	public void setUrl(String url);
}
