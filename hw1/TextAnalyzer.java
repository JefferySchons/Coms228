package edu.iastate.cs228.hw1;
/**
 * 
 * @author Jeffery Schons
 * 
 * This class is a parent class of all types of analysis code that takes text as input.
 * 
 * In practice, there are many analysis tasks that process text, such as simply counting 
 * the number of words, recognizing whether a word represents a name or a place or a political party.
 *  
 * In this assignment, we only have NaiveTextAnalyzer class as the subclass of this class. 
 * 
 * Note that we do not have a setter for the type of input because we want to always keep it as "Text".
 *
 */
public class TextAnalyzer {
	
	/**
	 * Define two private variables.
	 * 
	 *  - A String variable to keep the type of input acceptable for this Analyzer.
	 *  - A String variable to store the name of this Analyzer object.
	 * 
	 */
	String inputAcceptable;
	String analyzerName;
	String nameOfObject;
	
	/**
	 * Set the default value of the member variable keeping the type of the input to "Text".
	 * Set the default vaule of the member variable keeping the name of this object to "Text Analyzer".
	 */
	public TextAnalyzer() {
		inputAcceptable = "Text";
		analyzerName = "Text Analyzer";
	}
	
	/**
	 * 
	 * @return Value of the member variable that stores the input type of this class.
	 */
	public String getInputType() 
	{
		return inputAcceptable;
	}
	
	/**
	 * 
	 * @return Value of the member variable that stores the name of this Analyzer object.
	 */
	public String getName() {
		return analyzerName;
	}
	
	/**
	 * Set the value of the member variable that stores the name of the object to the newname.
	 * 
	 * @param newname New name of this object
	 * @throws NullPointerException is newname is null
	 */
	public void setName(String newname) {
		if(newname==null)
		{
			throw new NullPointerException("no imput");
		}
		else
		{
			//set name of object to be new name
			nameOfObject = newname;
		}
	}
	
	/**
	 * 
	 * Override this method using the guidelines discussed in class.
	 * Use all the member variables in the comparison.
	 * 
	 */
	@Override
	public boolean equals(Object o) {
		//can only check if classes are the same.
		if (o == null || o.getClass() != getClass())
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}
