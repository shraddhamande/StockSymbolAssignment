/*
 * We have a set of 3 csv files. These files represent daily stock data values for a symbol - which is the stock symbol. e.g. Infosys has a symbol of INFY. Check the given csv files. We need to calculate the daily range = high - low for each stock
 
Variation:
We need to calculate a running total of the range for each day for each stock.Ordered by timestamp field in the csv
 
Then we need to create a single csv file in which we add the above 2 calculated fields for each row.
 
We will run the program as java command line program.
We must get the instructions to run it. E.g. any args to be sent.
Where to keep input csv files and where to find output csv files.
 
 */

package com.sourceCode;
import java.io.*;
import java.util.*;

public class Solution
{

	public static List<StockSymbol> csvReader(String path, String file) 
	{
		List<StockSymbol> stockList =null;
		BufferedReader reader=null;
		Scanner scanner = null;
		try
		{
			reader = new BufferedReader(new FileReader(path+file));

			Map<String, Integer> header = new HashMap<String, Integer>();
            
            String line = null;
            int index = 0;
            line = reader.readLine();
            
            if (line != null) 
            {
                StringTokenizer str = new StringTokenizer(line, ",");
                int headerCount = str.countTokens();
            
                for (int i = 0; i < headerCount; i++) 
                {
                    String headerKey = str.nextToken();
                    header.put(headerKey.toUpperCase(), new Integer(i));

                }
            }
			stockList = new ArrayList<StockSymbol>();

			while ((line = reader.readLine()) != null) 
			{
				StockSymbol stock = new StockSymbol();
				scanner = new Scanner(line);
				scanner.useDelimiter(",");
				
				while (scanner.hasNext()) 
				{
					String data = scanner.next();
					if (index == header.get("SYMBOL"))
						stock.setSymbol(data);
					else if (index == header.get("HIGH"))
						stock.setHigh(Float.parseFloat(data));
					else if (index == header.get("LOW"))
						stock.setLow(Float.parseFloat(data));
					else if (index == header.get("TIMESTAMP"))
						stock.setTimeStamp(data);
					else
						stock.setRange(stock.getHigh()-stock.getLow());
					
					index++;
				}
				index = 0;
				stockList.add(stock);
			}
		
			reader.close();
			System.out.println("File "+file+" read successfully");
		}
		catch(Exception e)
		{
			System.out.println(e+"\n Enter the correct file name.");
		}
		finally 
		{
            scanner.close();
        }
		
		return stockList;
	}
	

	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter number of files you want to upload : ");
		int count = sc.nextInt();
		
		sc.nextLine();
		
		System.out.println("Enter full path of your files :  ");
		String path = sc.nextLine();
		
		String file[]=new String[count];
		
		System.out.println("Enter file "+count+" names : ");
		for(int i=0;i<count;i++)
		{
			file[i]=sc.nextLine();
		}
		
		System.out.println("Enter output file name : ");
		String output = sc.nextLine();
		
		List<StockSymbol> allDetails = new ArrayList<>();
		
		for(int i=0;i<count;i++)
		{
			List<StockSymbol> details = new ArrayList<>();
			details = csvReader(path,file[i]);
			allDetails.addAll(details);
		}
		
		Collections.sort(allDetails, StockSymbol.TimeStampComparator);
	
		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter(new FileWriter(path+output));
			
			writer.append("SYMBOL");
			writer.append(",");
			writer.append("RANGE");
			writer.append("\n");
						
			Iterator itr = allDetails.iterator();
			while(itr.hasNext())
			{
				StockSymbol stock = (StockSymbol)itr.next();
				String rowData = stock.symbol+","+stock.range;
				writer.append(String.join(",", rowData));
			    writer.append("\n");
			}
						
			System.out.println(" \nFile write successfully");
			System.out.println("See output file "+output+" at given location "+path);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}


}
