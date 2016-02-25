package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;





/**
 * 
 * @author <Jeffery Schons>
 * 
 * This class maintains properties obtained from reading a configuration file.
 * 
 * For methods that require copying values of a String array, you may   
 * write your own private method to do the copy or use the generic method
 * 
 * public static <T> T[] Arrays.copyOf(T[] original, int newLength)
 * 
 * E.g., String[] oldArray={"1","2","3"};
 * 		 String[] newArray=null; 
 * 
 * newArray = Arrays.copyOf(oldArray, oldArray.length);
 * 
 *
 */
public class Property  {
	
	
	/**
	 * Define three private member variables of type String[] for positive terms, negative terms, stop terms.
	 * Define one private variable of type int to keep the method for determining the sentiment scoring.
	 * Define one private variable of type double to store the minimum distance used to judge the sentiment 
	 * of a given string.
	 * 
	 */
	
	private String[] positiveTerms;
	private String[] negativeTerms;
	private String[] stopTerms;
	private int scoringMethod;
	private double minDistance;
	
	/**
	 * Default constructor
	 * 
	 * Set the default values of all member variables.
	 * 
	 * By default, the array of positive terms has "fun", "happy", "positive".
	 * By default, the array of negative terms has "sad", "bad", "angry". 
	 * By default, the array of stop terms keeps "a", "an", "the".
	 * The default value of the scoring method is 0.
	 * The default value of the minimum distance is 0.5.
	 * 
	 */
	public Property() 
	{
		positiveTerms = new String[] {"fun","happy","positive"};
		negativeTerms = new String[] {"sad","bad","angry"};
		stopTerms = new String[] {"a", "an", "the"};
		scoringMethod = 0;
		minDistance = 0.5;
	}
	
	/**
	 * Copy constructor; deep copy
	 * 
	 * Copy all the values of the member variables of the input Property object to this object.
	 * 
	 * @param p Property object as input
	 */
	
	public Property (Property p) {

		Property n = new Property();
		positiveTerms=p.positiveTerms;
		negativeTerms=p.negativeTerms;
		stopTerms=p.stopTerms;
		scoringMethod=p.scoringMethod;
		minDistance=p.minDistance;
		
		n.setPositiveTerms(p.positiveTerms);
		n.setNegativeTerms(p.negativeTerms);
		n.setStopTerms(p.stopTerms);
		n.setScoringMethod(p.scoringMethod);
		n.setMinDistance(p.minDistance);
		return;
	}
	
	
	/**
	 * 
	 * 	Initialize member variables with values read from the given configuration file.
	 * 
	 *    The content of the file is case sensitive. 
	 *    
	 *    Set all the properties to the default values first. Read the configuration file 
	 *    and overrides the default properties if the values in the configuration file are valid.
	 *   
	 *    See the example of the configuration file in the assignment description.
	 *    
	 *    We assume that users do not provide duplicated terms for positive terms, negative terms, 
	 *    or stop terms.
	 *    
	 *    You do not need to check whether positive terms, negative terms, or stop terms overlap.
	 *    You do not need to check duplicates within each array of terms. 
	 *    
	 *    Ignore any properties that do not match the above predefined properties.
	 *       
	 *    Use try catch to handle IOException or NumberFormatException. If any of them occurs, print on the screen 
	 *    to indicate such an error and use the default values for all the properties like those set 
	 *    in the default constructor.
	 *    
	 *    
	 *    You do not need to handle other exceptions.
	 *    
	 *    Properly close the file when done.
	 *    
	 *    You may introduce your own private method to keep the code concise.
	 *    No JUnit is needed for these private methods.
	 *   
	 *  @param configFileName Name of the configuration file 
	 *  @throws NullPointerException if configFileName is null
	 *          
	 */
	public Property(String configFileName) {
		
		//System.out.println(configFileName);
		
		// assigning positive, negative, stop, scoring method, and min distance
		// to default terms first
		positiveTerms = new String[] {"fun","happy","positive"};
		negativeTerms = new String[] {"sad","bad","angry"};
		stopTerms = new String[] {"a", "an", "the"};
		scoringMethod = 0;
		minDistance = 0.5;
		
		//if (configFileName != null)
		try
		{
			//put each line of the config file into own string
			
			File fileConfigFile = new File(configFileName);
			//problem is not real i have already cought the problem 
			// if file not found for file is already cought
			Scanner configFile = new Scanner(fileConfigFile);
			
			String [] newlineConfig = new String[5];
			//preset newlineConfig to be a array of "";
			for(int d = 0; d<newlineConfig.length; d++)
			{
				newlineConfig[d]="1";
				//System.out.println(newlineConfig[d]+" 00property");
			}
			int index = 0;
			String configFileLine;
			
			
			//System.out.println(configFileName+" 01property");
			//System.out.println(newlineConfig+" 02property");
			//System.out.println(configFileName+" 1");
			//System.out.println(configFile.nextLine()+" 2");
			//try
			//
			while (configFile.hasNextLine()) //() != null)
			{
				//puts next line of config file into temporary string
				configFileLine=configFile.nextLine(); 
				//System.out.println(configFileLine+ " 1234configFileLine");
				//System.out.println(configFile.nextLine()+" 3");
				//System.out.println(newlineConfig[index]+" 1");
			//this if is incase someone has extra lines at end of the config to cut them off
			//also if there is a blank line in config
				//error newlineConfig[index] is null becouse nothing is their;
				if(configFileLine != "" && index < newlineConfig.length)
				{
					//(newlineConfig[index] != "" && position < newlineConfig.length)
					
					newlineConfig[index]=configFileLine;
					//System.out.println(newlineConfig[index]+" "+index+ " 1234");
					//System.out.println(newlineConfig.length+ " 1234");
					//System.out.println(index+ " index 1");
					index++;
					//System.out.println(index+ " index 2");
				}
				else if (configFileLine == "")
				{
					//System.out.println(index+ " index 3");
					//index++;
					//System.out.println(index+ " index 4");
					//System.out.println(newlineConfig.length+ " 1234");
				}
				else if (index >= newlineConfig.length)
				{
					//System.out.println(configFileLine+ " this shouldnt appear");
				}
				
				//System.out.println("number line");
			}

			configFile.close();
			
			//to set Configs for first 3 lines(positive, negatinve, and stop
			for(int i=0; i<3; i++)
			{
				String[] newLineSplitOffNeededBits = newlineConfig[i].split("=");
				String newLineFirstWord = newLineSplitOffNeededBits[0]; 
				String newLineString = newLineSplitOffNeededBits[1];
				String [] newTerms = newLineString.split(",");
				
				if(i==0 && newLineFirstWord.equals("positive"))
				{
					setPositiveTerms(newTerms);
				}
				else if (i==1 && newLineFirstWord.equals("negative"))
				{
					setNegativeTerms(newTerms);
				}
				else if (i==2 && newLineFirstWord.equals("stop"))
				{
					setStopTerms(newTerms);
				}
				else
				{
					//use default
					return;
				}
			}
			
			//for the last two lines in config file (scoremethod and MinDistance)
			for(int i=3; i<5; i++)
			{
				String [] splitString;
				try
				{
					if (i==3)
					{
						// change scoremethodLineScanner as a string into an int
						//String scoremethodLineScanner = configFile.nextLine();
						// if error acorers due to IllegalArgumentException the 
						// scoring method is already set to defult at start of this method
						splitString=newlineConfig[3].split("=");
						//numbers of scoremethod as string
						String partOne = splitString[1];
						//numbers of scoremethod as int
						int partTwo =Integer.parseInt(partOne);
						setScoringMethod(partTwo);
						//setScoringMethod(Integer.parseInt(newlineConfig[3]));
						//System.out.println(getScoringMethod()+ " scoremethod 3");
					}
					if (i==4)
					{
						//change mindistanceLineScanner from string to a double
						// then send it to setMinDistance method to get min distance.
						// if error acorers due to IllegalArgumentException the 
						// min distance is already set to defult at start of this method
						//System.out.println(newlineConfig[4]+ " scoremethod 2");

						splitString=newlineConfig[4].split("=");
						//numbers of scoremethod as string
						String partOne = splitString[1];
						//numbers of scoremethod as int
						Double partTwo =Double.parseDouble(partOne);
						setMinDistance(partTwo);
						//setScoringMethod(Integer.parseInt(newlineConfig[3]));
						//System.out.println(getMinDistance()+ " scoremethod 3");
					}
				}
				catch (NumberFormatException nfp)
				{
					throw new NumberFormatException("problem with configuration; using defult configuration");
				}
			}	
		}
		//else
		catch (NullPointerException | FileNotFoundException | NoSuchElementException e)
		{
			//throw new NullPointerException("no config File Name; using defult configuratins");
			System.out.println("no config File Name, property catch error; using defult configuratins");
			setPositiveTerms(positiveTerms);
			setNegativeTerms(negativeTerms);
			setStopTerms(stopTerms);
			setScoringMethod(scoringMethod);
			setMinDistance(minDistance);
		}
	}
	
	/**
	 * 
	 * @return A copy of the array of positive terms.
	 *         May return an empty String array if the original array is empty.
	 *         
	 */
	
	public String[] getPositiveTerms()
	{
		//System.out.println(positiveTerms+ " main positive terms");
		return positiveTerms;
	}
	
	/**
	 * 
	 * Set the content of the positiveTerms array to the content of the newTerms array.
	 * Both arrays must have the same length and the same content once this method returns.
	 * 
	 * Make sure that positiveTerms and newTerms do not reference the same array object 
	 * since we do not want the positiveTerm Property to change unknowingly because the 
	 * content of newTerms are changed.
	 * 
	 * @param newTerms Array of String of new words to use as positive terms
	 * @throws NullPointerException when newTerms is null or the array is empty
	 * 
	 */
	public void setPositiveTerms(String[] newTerms) {	
		//System.out.println(" start set positive");
		if (newTerms != null && newTerms.length != 0)
		{
			//String[] positiveTermsSetUp = new String[newTerms.length];
			//for (int i = 0; i<newTerms.length; i++)
			//{
			//	positiveTermsSetUp[i] = newTerms[i];
			//}
			positiveTerms=newTerms;
		}
		else
		{
			//System.out.println(" this is from set positive");
			throw new NullPointerException("new terms is empty; using defult configuratins");
		}
	}
	
	/**
	 * 
	 * @return A copy of the array of negative terms.
	 *         May return an empty String array if the original array is empty.
	 */
	
	public String[] getNegativeTerms() 
	{
		return negativeTerms;
	}
	
	
	/**
	 * 
	 * Set the content of the negativeTerms array to the content of the newTerms array.
	 * Both arrays must have the same length and the same content once this method returns.
	 * 
	 * Make sure that negativeTerms and newTerms do not reference the same array object 
	 * since we do not want the negative terms of this Property object to change unknowingly 
	 * because newTerms are changed.
	 * 
	 * @param newTerms Array of String of new words to use as negative terms
	 * @throws NullPointerException when newTerms is null or the array is empty
	 * 
	 */
	public void setNegativeTerms(String[] newTerms) 
	{
		if (newTerms != null && newTerms.length != 0)
		{
			String[] negativeTermsSetUp = new String[newTerms.length];
			for (int i = 0; i<newTerms.length; i++)
			{
				negativeTermsSetUp[i] = newTerms[i];
			}
		}
		else
		{
			throw new NullPointerException("new terms is empty; using defult configuratins");
		}
	
	}
	
	/**
	 * 
	 * @return A copy of the array of stop terms
	 * 		   May return an empty array if the orginal array is empty.
	 */
	
	public String[] getStopTerms() {
		
		return stopTerms;
	}
	
	/**
	 * 
	 * Set the content of the stopTerms array to the content of the newTerms array.
	 * Both arrays must have the same length and the same content once this method returns.
	 * 
	 * Make sure that stopTerms and newTerms do not reference the same array object 
	 * since we do not want the stop terms of this Property object to change unknowingly 
	 * because newTerms are changed.
	 * 
	 * @param newTerms Array of String of new words to use as stop terms
	 * @throws NullPointerException when newTerms is null or the array is empty
	 * 
	 */
	public void setStopTerms(String[] newTerms) {
		if (newTerms != null && newTerms.length != 0)
		{
			String[] stopTermsSetUp = new String[newTerms.length];
			for (int i = 0; i<newTerms.length; i++)
			{
				stopTermsSetUp[i] = newTerms[i];
			}
		}
		else
		{
			throw new NullPointerException("new terms is empty; using defult configuratins");
		}
	}
	
	
	 // /**
	  // * Constructs a deep copy of the given vector.
	 //  * @param existing an existing IntVector to be copied
	 //  */
	 // public IntVector(IntVector existing)
	//  {
	//    dim = existing.dim;
	//    coords = new int[dim];
	 //   
	//    // Alternative: use System.arraycopy(...)
	 //   for (int i = 0; i < dim; ++i)
	//    {
	 //     coords[i] = existing.coords[i];
	//  }
		//}
	
	/**
	 * 
	 * @return Sentiment analysis scoring method
	 */
	public int getScoringMethod() {
		return scoringMethod;
	}
	
	/**
	 * 
	 * @param method Positive integer indicating which method is used for computing the sentimental score
	 * @throws IllegalArgumentException if the method value is not zero and is not 1.
	 */
	public void setScoringMethod(int method) {
		if (method != 0 && method != 1)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			scoringMethod = method;
		}
	}
	
	/**
	 * 
	 * @return Value of the member variable keeping the minimum distance for sentiment classification
	 */
	public double getMinDistance() {
		return minDistance;
	}
	
	/**
	 * Set the value of the member variable that keeps the minimum distance to the value of newdistance.
	 * 
	 * @param newdistance New minimum distance
	 * @throws IllegalArgumentException when the newdistance is not positive.
	 */
	public void setMinDistance(double newdistance) {
		
		if (minDistance <=0)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			minDistance = newdistance;
		}
		
	}
	
	/**
	 * Two objects are equals if they have same values for all their private member variables: 
	 * positiveTerms, negativeTerms, stopTerms, scoringMethod, and minDistance.
	 * 
	 * Implement the method using the guidelines given in class.
	 * 
	 * For comparing two arrays, make sure that the content of the two arrays at the same index have 
	 * the same value and the two arrays have the same length. You may write your own private method 
	 * to compare or you may use Arrays.equals() or Arrays.deepEquals() for this purpose.
	 * These two methods return different results for nested arrays. 
	 * 
	 * For comparing doubles, two doubles are equal if they differ less than a small error range, 
	 * defined as 0.000001 here.
	 * 
	 * 
	 */
	public boolean equals(Object o) 
	{
		// i could do this as one long if but i chose a nested if for readability.
		if(this.getPositiveTerms().equals(((Property) o).getPositiveTerms()))
		{
			if (this.getNegativeTerms().equals(((Property) o).getNegativeTerms()))
			{
				if (this.getStopTerms().equals(((Property) o).getStopTerms()))
				{
					if (this.getScoringMethod()==((Property) o).getScoringMethod())
					{
						if (this.getMinDistance()==((Property) o).getMinDistance())
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
						return false;
					}
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * @return String "Property"
	 */
	public String toString() 
	{
		return "Property";
	}
}
