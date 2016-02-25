package edu.iastate.cs228.hw1;

import java.util.Arrays;

import javax.print.attribute.standard.MediaSize.Other;

/**
 * 
 * @author Jeffery Schons
 * 
 * This class is a sub-class of TextAnalyzer and implements ISentiment.
 * 
 *
 */
public class NaiveTextAnalyzer extends TextAnalyzer implements ISentiment {
	private static final int h = 0;
	/**
	 * Define a private member variable of type Property to keep the reference to a Property object 
	 * storing various properties from the configuration file.
	 */
 
	private Property NaivemainProperty = new Property();
	/**
	 * Default constructor
	 * 
	 * Call the setName method of TextAnalyzer using "Naive Text Sentiment Analyzer" as the argument.
	 */
	public NaiveTextAnalyzer() {
		super.setName("Naive Text Sentiment Analyzer");
	}
	
	/**
	 * Call the setName method of Text Analyzer using the input string of this method as the argument.
	 * 
	 * @param newname
	 */
	public NaiveTextAnalyzer(String newname) {
		super.setName(newname);
	}
	
	/**
	 * Find sentiment of a given input string s. 
	 * 
	 * Follow this guideline.
	 * 
	 * - Eliminate all full stops(periods), commas, semi-colons from s. 
	 *   You do not need to check other punctuations.
	 *   
	 * - Separate the string into words using whitespace characters [ \t\n\x0B\f\r].
	 * 
	 *  /is done in another part
	 * - Count the number of negative words in s.
	 * - Count the number of positive words in s.
	 * - Count the number of stop words in s.
	 * - Count the total number of words in s.
	 * 
	 * - Compute the sentiment score using these counts.
	 * - See how to do so and the examples in the assignment description.
	 * 
	 * - Use getProperty() to get the reference to the Property object.
	 *  
	 *   Obtain the array of positive words, negative words, and stop words 
	 *   using the methods getPositiveTerms(), getNegativeTerms(), getStopWords() of 
	 *   the Property object.
	 * 
	 *   The scoring method is obtained by calling the method getScoringMethod() 
	 *   of the same Property object.
	 * 
	 * @param s Input string
	 * @return 0 if s is empty or 
	 *           the stop word count is equal to the number of words in the string 
	 *         Otherwise, return the sentiment score computed based on the choice of the scoring method.
	 * 
	 * @throws NullPointerException if s is null
	 *         IllegalStateException if the member variable keeping the reference to the Property object is null. 
	 *         	  
	 */

	@Override
	public double findSentiment(String s) {
		// this method finds the sentimentScore
		//start counting trough words 
		//System.out.println(s+ " start of find sentiment"); 
		 
		
		
		int wordIndex = 0;
		String noPeriodsCommasSemiColons ="";
		String [] arrayOfInputWords;
		int totalWordCount = 0;
		int numberOfPositiveWords = 0;
		int numberOfNegativeWords = 0;
		int numberOfStopWords = 0;
		int scorringMethod;
		double sentimentScore;
		
		//String sentimentPosition;
		
		String [] positiveWords;
		String [] negativeWords;
		String [] stopWords;
		
		//to eliminate the full stops(periods), commas, semi-colons from s.
		// dosent check other punctuation but can be modified to do so
		// puts the non punctuation charictors into new string
	    while (wordIndex < s.length())
	    {
	    	char compair = s.charAt(wordIndex);
	    	if (compair=='.')
	    	{
	    		wordIndex = wordIndex + 1;
	    	}
	    	else if (compair==',')
	      	{
	    	  	wordIndex = wordIndex + 1;
	      	}
	      	else if (compair==';')
	      	{
	    	  	wordIndex = wordIndex + 1;
	      	}
	      	else
	      	{
	      		// - Separate the string into words using whitespace characters [ \t\n\x0B\f\r].
	      		noPeriodsCommasSemiColons = noPeriodsCommasSemiColons+compair;
	      		wordIndex = wordIndex + 1;
	      	}
	    }

	    //seperate String noPeriodsCommasSemiColons into words
	    noPeriodsCommasSemiColons.replaceAll(".", noPeriodsCommasSemiColons);
	    //System.out.println(noPeriodsCommasSemiColons+" hellow");
	    String [] arrayOfInputWordsWithNull = noPeriodsCommasSemiColons.split(" ");
	    arrayOfInputWords = Arrays.copyOfRange(arrayOfInputWordsWithNull, 0, arrayOfInputWordsWithNull.length);
	    
	    //checker
	  // for (int t=0; t<arrayOfInputWords.length; t++)
	  // {
		   //arrayOfInputWords[t]; 
				   //=  noPeriodsCommasSemiColons.replaceAll(" ");
		//		   System.out.println(arrayOfInputWords[t]+ "  hdhualiuihfuyglyu");
	    //}
	    
	    //System.out.println(arrayOfInputWords(1)+ "hdhualiuihfuyglyu");
	    totalWordCount = arrayOfInputWords.length;

	    //need to declare property in main 
	    //Property p = new Property();
	    //Property word = new Property();
	    
	    //set mainPropert to be equil to outside, scoresetiment property
	    
	    
	    
	    positiveWords = NaivemainProperty.getPositiveTerms();
	    negativeWords = NaivemainProperty.getNegativeTerms();
	    stopWords = NaivemainProperty.getStopTerms();
	    //- Use getProperty() to get the reference to the Property object.
	    //Obtain the array of positive words, negative words, and stop words 
		 //   using the methods getPositiveTerms(), getNegativeTerms(), getStopTerms() of 
		 //  the Property object.
	   
	    
	    //System.out.println(arrayOfInputWords+ "hdhualiuihfuyglyu");
	    numberOfPositiveWords = findCount(arrayOfInputWords, positiveWords);
	    numberOfNegativeWords = findCount(arrayOfInputWords, negativeWords);
	    numberOfStopWords = findCount(arrayOfInputWords, stopWords);
	    
	    //System.out.println(numberOfPositiveWords+"number of positive words");
	    //System.out.println(numberOfNegativeWords+"number of negative words");
	    //System.out.println(numberOfStopWords+"number of stop words");
	   // System.out.println(totalWordCount+" totale word count");
		
		//getScoringMethod() 
	    //their is a method but i do not see a use for it.
	    scorringMethod=NaivemainProperty.getScoringMethod();
	   // System.out.println(scorringMethod+ "  scoring method");
		//of the same Property object.
		//check if line is positive or negative or not	
	    
		//-Compute the sentiment score
	    if (scorringMethod == 0)
	    {
	    	//System.out.println(" not this");
	    	sentimentScore = numberOfPositiveWords - numberOfNegativeWords;
	    }
	    else //scorringMethod == 1
	    {
	    	//System.out.println(" this one");
	    	int topScore = numberOfPositiveWords - numberOfNegativeWords;
	    	int bottomScore = totalWordCount -numberOfStopWords;
	    	//System.out.println(topScore+" topScore");
	    	//System.out.println(bottomScore+" bottomScore");
	    	
	    	//sentimentScore = (numberOfPositiveWords - numberOfNegativeWords)/(totalWordCount -numberOfStopWords);
	    	
	    	//done as top score, bottom score seperate to make the score come out as not 0.0
	    	sentimentScore=((double) topScore)/bottomScore;
	    	//System.out.println(sentimentScore+" sentimentScore");
	    }

	    //showSentiment(double score)
	    return sentimentScore;
	}
	

	/**
	 * Count the number of words in tokenArr that match the words in wordArr.
	 * 
	 * 
	 * @param wordArr Array of positive words, negative words, or stop words depending on what you want to count
	 * @param tokenArr Array of words 
	 * @return 0 if wordArr is empty or tokenArr is empty; 
	 * 			otherwise, return the count of words in tokenArr matching those in wordArr.
	 * @throws NullPointerException if wordArr or tokenArr is null
	 */
	public static int findCount(String[] wordArr, String[] tokenArr) 
	{
		//for (int t=0; t<wordArr.length; t++)
		//{System.out.println(wordArr[t]+ "  1st");
		//}
		
		//for (int t=0; t<tokenArr.length; t++)
		//{System.out.println(tokenArr[t]+ "  2nd");
		//}
		
		int count = 0;
		// wordArr == arrayOfInputWords; tokenArr == positiveWords, NegativeWords, stopWords
		if(wordArr == null && tokenArr == null)
		{
			throw new NullPointerException("String empty");
		}
		else
		{
			//System.out.println("count 01");
			//System.out.println("hi "+wordArr[0]);
		    for(int i=0; i<wordArr.length; i++)
		    {
		    	//System.out.println("hi "+wordArr[i]);
	    		//System.out.println("count 11");
	    		
		    	for (int j=0; j<tokenArr.length; j++)
		    	{
		    		//System.out.println("count 02");
		    		//System.out.println("hi "+wordArr[i]+"    "+tokenArr[j]+ "  hi");
		    		if (wordArr[i].equals(tokenArr[j]))
		    		{
		    			count = count+1;
		    			//System.out.println(count+ "should be here");
		    		}
		    	}
		    }
		}
		return count;
	}
	
	/**
	 * Override equals as discussed in class.
	 * Consider both name and property when comparing the objects.
	 * Your code must not break if the reference to the Property object is null. 
	 * 
	 */
	@Override
	public boolean equals(Object o) {
		
		//check if classes are the same.
		if (o == null || o.getClass() != this.getClass())
		{
			return false;
		}
		else //classes are the same check if classes are property
		{
			if (o.toString() == "Property")
			{
				//private Property o;
				if (((Property) o).getPositiveTerms().equals((NaivemainProperty).getPositiveTerms()) 
						&& ((Property) o).getNegativeTerms().equals((NaivemainProperty).getNegativeTerms())
						&& ((Property) o).getStopTerms().equals((NaivemainProperty).getStopTerms())
						&& ((Property) o).getScoringMethod()==((NaivemainProperty).getScoringMethod())
						&& ((Property) o).getMinDistance()==((NaivemainProperty).getMinDistance()))
				{
					return true;
				}
				else 
				{
					return false;
				}
				
			}
			else
			{
				return true;
			}
		}
	}
	
	/**
	 * sets the scoring method from the property object
	 * @return
	 */
	private Object getScoringMethod() {
		// I have not used this because one line above replaces this entire method. 
		//so i used the why with less lines of code ran.
		int neededScorringMethod;
		neededScorringMethod=NaivemainProperty.getScoringMethod();
		return neededScorringMethod;
		
	}

	/**
	 * Set the member variable of type Property to keep the given reference to the Property object.
	 * 
	 * @throws NullPointerException if o is null;
	 * 
	 */
	@Override
	public void init(Property o) {
		if (o == null)
		{
			//System.out.println("why this");
			throw new NullPointerException("no Property");
		}
		else 
		{
			
			NaivemainProperty.setPositiveTerms(o.getPositiveTerms());
			NaivemainProperty.setNegativeTerms(o.getNegativeTerms());
			NaivemainProperty.setStopTerms(o.getStopTerms());
			NaivemainProperty.setScoringMethod(o.getScoringMethod());
			NaivemainProperty.setMinDistance(o.getMinDistance());
			return;
		}
	}
	
	/**
	 * Show text sentiment for the given sentiment score.
	 * 
	 * @param score sentiment score
	 * @return 
	 *    "Positive" if score >= the predefined distance threshold obtained by calling getMinDistance() of the Property object
	 *    "Negative" if score <= -1 * the predefined distance threshold
	 *    "Unknown" otherwise
	 *  
	 *  @throws IllegalStateException if the member variable that keeps the reference to the Property object is null. 
	 */
	@Override
	public String showSentiment(double score) {
		//System.out.println(score+" scoure is this");
		//mainProperty is variable for referrence to propty object
		if (NaivemainProperty==null)
		{
			throw new NullPointerException("String empty");
		}
		else
		{
			double minimumDistance;
			minimumDistance=NaivemainProperty.getMinDistance();
			String sentimentPosition;
			
			//System.out.println(minimumDistance+" mindistance");
			//Double negativeMinDistance = -1*minimumDistance;
			//System.out.println(-minimumDistance+" negative mindistance");
			if (score <= -minimumDistance)
			{
				//System.out.println(" this is the first answer");
				sentimentPosition="negative";
			}
			else if (score >= minimumDistance)
			{
				//System.out.println(" secound answer");
				sentimentPosition="positive";
			}
			else // (-minimumDistance < score < minimumDistance)
			{
				//System.out.println(" 3rd answer");
				sentimentPosition="unknown";
			}
			return sentimentPosition;
		}
	}
	
	/**
	 * Use the copy constructor of the Property object here.
	 * 
	 * @return  Reference to a copy of the current property object. 
	 */
	@Override
	public Property getProperty() {
		if (toString().equals("Property"))
		{
			
			//Property neededProperty = new Property(NaivemainProperty);
			return NaivemainProperty;
		}
		else
		{
			System.out.println("cant do this if it isnt property");
		}
		return NaivemainProperty;
	}
	
}

