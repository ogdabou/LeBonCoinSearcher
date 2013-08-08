package ogdabou.antracker.leboncoin;

import java.util.ArrayList;

/**
 * Constants used in our LBC crawler.
 * 
 * @author Axel COUTY (axel.couty@thalesgroup.com)
 * 
 */
public class LeBonCoinConstants
{
	/** LBC url */
	public static String URL = "http://www.leboncoin.fr/";
	
	/** Used when we don't precise the category */
	public static String ANNOUNCE_URL = "/annonces/offres/";
	
	/** If a category is precised, we use this */
	public static String OFFERS_URL = "/offres/";
}
