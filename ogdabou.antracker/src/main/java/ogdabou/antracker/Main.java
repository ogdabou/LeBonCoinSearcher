package ogdabou.antracker;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

import ogdabou.antracker.leboncoin.LeBonCoinCrawler;

/**
 * Hello world!
 * 
 */
public class Main
{
	public static void main(final String[] args) throws XPathExpressionException, IOException
	{
		final LeBonCoinCrawler lbcCrawler = new LeBonCoinCrawler();
		lbcCrawler.process("guitare", "ile_de_france", "instruments_de_musique");
	}
}
