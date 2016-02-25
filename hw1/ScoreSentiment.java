package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 
 * @author Jeffery Schons
 * 
 * This is the class that accepts String command line arguments which are in args argument given to the main method.
 *
 * The version number of this software is 1.0.0.
 * 
 * The only method required in this class is the main method.
 * 
 * If the two arguments are not given, output the following on the console and return from this method.
 * Usage: SentimentAnalysis-v1.0.0 <input filename> <config filename>
 * 
 * When both arguments are given, assume that the user gives them in the correct input order. 
 * 
 *  args[0] is the first argument and it has the input filename.
 *  args[1] is the second argument and it has the configuration filename.
 *  
 * The method creates a Property object with the name of the configuration file.
 * It creates a NaiveAnalyzer object. Then calls the init method of this object with the Property object.
 * 
 * In the try-catch statement, it reads one line at a time from the input file.
 * Each line including an empty line has a unique ID, starting from 1.
 * 
 * For each non-empty line, the program computes the sentiment score and text using the NaiveAnalyzer object 
 * and outputs the following line on the console.
 * 
 *  	Line <lineid>: score=<sentimentscore> Sentiment=<sentiment text>
 *  
 *  	<lineid> is replaced by line numbering starting from one, incrementing by one.
 *   
 *  	<sentimentscore> is replaced by the sentiment score of the line.
 * 		<sentiment text> is either "Positive", "Negative", or "Unknown".
 *  
 *      "Positive" is output on screen when the line has more positive words than negative words 
 *      by a significant margin defined using the scoring method and the minimum distance in 
 *      the configuration file.
 *  
 *      "Negative" is output on screen when the line has more negative words than positive words 
 *      by a significant margin defined using the scoring method and the minimum distance in 
 *      the configuration file.
 *      
 *      "Unknown" is printed out when we cannot classify it as "Positive" or "Negative".
 *      
 *      See the examples given in the description of the assignment.
 *      
 *  Once all the lines in this file are processed, show the following on the console.
 *  
 *  Total score with Naive Text Sentiment Analyzer=<sum sentiment score>
 *  
 *  <sum sentiment score> is to be replaced by the sum of all the sentiment scores for each line in the file.
 *  "Naive Text Sentiment Analyzer" is obtaind using the inheritted getName() method from TextAnalyzer.
 *  
 *  See the examples given in the description of the assignment.
 *  
 *  The catch statement handles the cases when the input file is not found or problems 
 *  with openning the input file (e.g., IOException). It outputs the following on the console and returns 
 *  (exit the program).
 *  
 *  Input file <input filename> not found or there're problems with the file. Check your input file.
 *  
 *  <input filename> is replaced by the actual input filename.
 *  
 *  When testing the program, it may be easier to use an absolute path to your input file and configuration file. 
 *  If you do not use the absolute path, the program looks for the files in a default path, which depends on 
 *  your setting and how you run the program. 
 *  
 *  If you run the program from Eclipse, the default directory is the directory of the project 
 *  under your workspace directory.
 *  
 *  See how to run a Java program with arguments in IDE in the text description of the assignment.
 */

public class ScoreSentiment {
	
	// Define private member variables you need to use.
	//scan in input
	//scan in config in Property
	//check if both a input and config
	//	if not SOP (Usage: SentimentAnalysis-v1.0.0 <input filename> <config filename>)
	//args[0] is the first argument and it has the input filename.
	//args[1] is the second argument and it has the configuration filename.
	//crate property object with name same as the configuration file
	//creates a NaiveAnalyzer object. 
	//	Then calls the init method of this object with the Property object.
	//In the try-catch statement, it reads one line at a time from the input file.
	//	Each line including an empty line has a unique ID, starting from 1.
	//For each non-empty line uses the NaiveAnalyzer object
	//	program computes the sentiment score and text and outputs a output line (needs varable name)
	//Total score with Naive Text Sentiment Analyzer
	//	is obtaind using the inheritted getName() method from TextAnalyzer
	//catch statement
	//	handles when input file not found or problems opening input file (e.g., IOException)
	//	SOP (Input file <input filename> not found or there're problems with the file. Check your input file.)
	
	public static void main(String[] args) 
	{
		try
		{
		
		//the input line is from consule
		//input file is put as an array with each line as new index
		//SentimentAnalysis-v1.0.0 <input filename> <config filename>
		
		
		//List<String> inputLines new ArrayList<String>();
		
		//String [] inputLines = {"",""};
		//inputLines [0]= "lines of the input file";
		double totalScore = 0;
		
		//inputLines as arraylist
		ArrayList<String> inputLines = new ArrayList<String>();
		inputLines.add("lines of the input file");
		
		
		//args[0] is the first argument and it has the input filename.
		//args[1] is the second argument and it has the configuration filename.
	
		String inputName = args[0];
		String configName = args[1];

		
		
		//System.out.println(inputName);
		//System.out.println(configName);

		
		//check if both input and config
		//	if not SOP (Usage: SentimentAnalysis-v1.0.0 <input filename> <config filename>)
		if(args[0]==null)
		{
			System.out.println("Usage: SentimentAnalysis-v1.0.0 <input filename> <config filename>");

		}
		else if(args[1]==null)
		{
			System.out.println("Usage: SentimentAnalysis-v1.0.0 <input filename> <config filename>");
		}
		else
		{
			//i know this is not used locally but it is here so other classes can use it
			//System.out.println(configName+ " scoreset 12");
			Property mainProperty = new Property(configName);
			//bring in name of config file and send it to property
			//create Property object
			NaiveTextAnalyzer thisLine = new NaiveTextAnalyzer ("thisLineTextAnalizer");
			
			//System.out.println(mainProperty.getPositiveTerms()+" mainpropertyposwords");
			thisLine.init(mainProperty);
			
			
			//creates a NaiveAnalyzer object. 
			//	Then calls the init method of this object with the Property object.
		
			//bring in input
			File inputFileName = new File(inputName);
			Scanner inputNameScanner;
			inputNameScanner = new Scanner(inputFileName);
				
			//	Each line including an empty line has a unique ID, starting from 1.
			//index for the line of input, this is one to make input lines go in
			// at correct places
			//int j = 1;
			
			while (inputNameScanner.hasNextLine()) // != null)
			{
				//configFile.hasNextLine()
				inputLines.add(inputNameScanner.nextLine());

					
					// give each line of inputfile into new index of arraylist
					//starting at 1
					//send to String [] inputLines;
					//////inputLines.add(inputNameScanner.nextLine());
					//inputLines [j]= inputNameScanner.nextLine();
					//j++;

			}
			for(int i=1; i< inputLines.size();)
			{
				//System.out.println(inputLines.get(i));
				if (inputLines.get(i) != "")
				{
				//System.out.println(inputLines.get(i)+ " 1234configFileLine");
				double sentimentScoreThisLine = thisLine.findSentiment(inputLines.get(i));
				
				//For each non-empty line uses the NaiveAnalyzer object
				//	program computes the sentiment score and text 
				String setimentThisLine = thisLine.showSentiment(sentimentScoreThisLine); 
				//returns, neg.,post.,or stop
				//and outputs a output line (needs variable name)
				System.out.println("Line"+i+": score="+sentimentScoreThisLine+"Sentiment="+setimentThisLine);
				totalScore = totalScore + sentimentScoreThisLine;
				i++;
				}
				else
				{
					i++;
				}
			}
			//output last line
			System.out.println("Total score with Native Text Sentiment Analizer="+totalScore);
				
		}
		}
		catch (NullPointerException | NoSuchElementException | IllegalArgumentException |IOException | ArrayIndexOutOfBoundsException bfni)
		{
			System.out.println("Input file <input filename> not found or there're problems with the file. Check your input file.");
		}
	}
}
	    
	
