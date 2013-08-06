package ogdabou.antracker.leboncoin;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static ogdabou.antracker.leboncoin.LeBonCoinConstants.OFFERS_URL;

import static ogdabou.antracker.leboncoin.LeBonCoinConstants.ANNOUNCE_URL;

import static ogdabou.antracker.leboncoin.LeBonCoinConstants.URL;

/**
 * The crawler for LBC.
 * 
 * @author Axel COUTY (axel.couty@thalesgroup.com)
 * 
 */
public class LeBonCoinCrawler
{
	private Document lbcPage;
	private final List<LbcItem> itemList = new ArrayList<LbcItem>();
	private String pageToVisit;
	
	public LeBonCoinCrawler()
	{
		
	}
	
	/**
	 * Process the crawl of a search, passing through all the pages.
	 * 
	 * @throws IOException
	 */
	public List<LbcItem> process(final String searchRequest, final String region)
			throws IOException
	{
		if (pageToVisit == null)
		{
			pageToVisit = URL + ANNOUNCE_URL + region + "/?f=a&th=1&q=" + searchRequest;
		}
		try
		{
			do
			{
				lbcPage = Jsoup.connect(pageToVisit).timeout(0).get();
				System.out.println("Processing " + pageToVisit);
				
				processItemPage(lbcPage.getElementsByClass("list-lbc").get(0).getElementsByTag("a"));
				
				pageToVisit = getNextPage(lbcPage.getElementsByClass("page"));
			}
			while (pageToVisit != null);
		}
		catch (final MalformedURLException e)
		{
			e.printStackTrace();
		}
		System.out.println("A total of " + itemList.size() + " items have been extracted.");
		pageToVisit = null;
		return itemList;
	}
	
	public List<LbcItem> process(final String searchRequest, final String region, final String category)
			throws IOException
	{
		pageToVisit = URL + category + OFFERS_URL + region + "/?f=a&th=1&q=" + searchRequest;
		return process(searchRequest, region);
	}
	
	/**
	 * Process for each item page.
	 * 
	 * @param pageList
	 * @throws IOException
	 */
	private void processItemPage(final Elements pageList) throws IOException
	{
		for (int i = 0; i < pageList.size(); i++)
		{
			final Element pageEl = pageList.get(i);
			System.out.println("Processing item at: " + pageEl.attr("href"));
			final Document itemPage = Jsoup.connect(pageEl.attr("href")).timeout(0).get();
			
			final LbcItem newItem = new LbcItem();
			newItem.setUrl(pageEl.getElementsByTag("a").get(0).attr("href"));
			newItem.setName(itemPage.getElementsByTag("h2").get(0).text());
			if (itemPage.getElementsByClass("price").size() > 0)
			{
				newItem.setPrice(itemPage.getElementsByClass("price").get(0).text());
			}
			newItem.setDescription(itemPage.getElementsByClass("content").get(0).text());
			processParams(newItem, itemPage);
			itemList.add(newItem);
			System.out.println(newItem);
		}
	}
	
	/**
	 * Process the 'params' of the announce (get the City and the postal Code')
	 * 
	 * @param newItem
	 * @param itemPage
	 */
	private void processParams(final LbcItem newItem, final Document itemPage)
	{
		final Element params = itemPage.getElementsByClass("lbcParams").get(0);
		final Elements paramsField = params.getElementsByTag("tr");
		if (paramsField.size() >= 2)
		{
			final Element cityTag = paramsField.get(1).getElementsByTag("td").get(0);
			newItem.setCity(cityTag.text());
		}
		if (paramsField.size() >= 3)
		{
			final Element postalTag = paramsField.get(2).getElementsByTag("td").get(0);
			newItem.setPostalCode(postalTag.text());
		}
	}
	
	/**
	 * Get the next page url.
	 * 
	 * @param nextPageElements
	 * @return
	 */
	private String getNextPage(final Elements nextPageElements)
	{
		if (nextPageElements.get(1).children().size() > 0)
		{
			return nextPageElements.get(1).children().get(0).attr("href");
		}
		return null;
	}
	
}
