package com.sourceCode;
import java.util.*;
public class StockSymbol
{
	String symbol,timeStamp;
	float high,low,range;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public float getHigh() {
		return high;
	}
	public void setHigh(float high) {
		this.high = high;
	}
	public float getLow() {
		return low;
	}
	public void setLow(float low) {
		this.low = low;
	}
	public float getRange() {
		return range;
	}
	public void setRange(float range) {
		this.range = range;
	}

	public static Comparator<StockSymbol> TimeStampComparator = new Comparator<StockSymbol>() 
	{

		public int compare(StockSymbol s1, StockSymbol s2)
		{
		   String timeStamp1 = s1.getTimeStamp().toUpperCase();
		   String timeStamp2 = s2.getTimeStamp().toUpperCase();
		   
		   return timeStamp1.compareTo(timeStamp2);//ascending

		   //return timeStamp2.compareTo(timeStamp1);//Descending
	    }
	};

	
	@Override
	public String toString() {
		return " [symbol = " + symbol + ", range = " + range + ", timeStamp = " + timeStamp +"]\n";
	}
	
}

