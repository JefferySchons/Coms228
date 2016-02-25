package edu.iastate.cs228.hw2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The runner class for the return check program.
 * 
 * @author
 */
public class ReturnCheckRunner {

        /**
         * Runs the program defined in the spec.
         * 
         * Reads from employer file into a list of employer filings 
         * and reads from filer file into a list of individual filings.
         * 
         * After reading in all records, create a return check object
         * and use it to sort the employer and individual filings. 
         * Use the sorted filings to calculate and sort any discrepancies,
         * then display discrepancies and exit.
         * 
         * @param args
         *            Two expected arguments, the first being the employer
         *            file filename, and the second being the filer file
         *             filename
         */
		public static void main(String[] args) 
        {
        	
        
        try
        {
        	
       ArrayList<String> employerLinesOneWord = new ArrayList<String>();
       employerLinesOneWord.add("lines of the input file");
       
       
       ArrayList<String> filerLinesOneWord = new ArrayList<String>();
       filerLinesOneWord.add("lines of the input file");
       
		ArrayList<Employer> employersArrayList = new ArrayList<Employer>();
        ArrayList<Filer> filersArrayList = new ArrayList<Filer>();
       
    	if(args[0]==null)
    	{
    		System.out.println( "<employer filename> <filer filename>");

    	}
    	else if(args[1]==null)
    	{
    		System.out.println( "<employer filename> <filer filename>");
    	}
    	else
    	{
    		//args[0] is the first argument and it has the employer filings file name
    		//args[1] is the second argument and it has the individual filings file name
    	
    		//scanns in args[0] to employerLinesOneWord
    		String employerinputName = args[0];
    		
    		
    		
        	File employerInputFileName = new File(employerinputName);
			Scanner employerInputNameScanner = new Scanner(employerInputFileName);
			
			while (employerInputNameScanner.hasNextLine())
			{
				employerLinesOneWord.add(employerInputNameScanner.nextLine());

			}
			employerInputNameScanner.close();
			
			//scanns in args[1] to filerLinesOneWord
			
			String filerinputName = args[1];
			
			File filerInputFileName = new File(filerinputName);
			Scanner filerInputNameScanner = new Scanner(filerInputFileName);
			
			while (filerInputNameScanner.hasNextLine())
			{
				filerLinesOneWord.add(filerInputNameScanner.nextLine());

			}
			filerInputNameScanner.close();
        	
        	
			
			//seing Filer and an Employer and a discrepency and a ReturnCheck to use later
			Filer mainFiler = new Filer(null, 0, 0);
			Employer mainEmployer = new Employer(null, 0, null, 0, 0);
			//Discrepancy mainDiscrepency = new Discrepancy(false, 0, null, 0, null);
			//ReturnCheck mainReturnChecck = new ReturnCheck (null, null);

			//takes each line of employer input lines (variable: employerLinesOneWord) and converts them into employersArrayList
			// which is an arraylist of Employer objectics, each made from a line of employer input lines
			for(int i=1; i<employerLinesOneWord.size(); i++)
			{
				// ex: Google, 0, BOX CHARLES, 724113610, 50
				if (employerLinesOneWord.get(i) != "")
				{
					// ex: Google, 0, BOX CHARLES, 724113610, 50
					String[] employerString = employerLinesOneWord.get(i).split(", ");
					String tempName = null;
					int tempID = 0;
					String tempEmployeeName = null;
					int tempEmployeeSSN = 0;
					int tempEmployeeWages=0;
					
					for(int j=0; j<employerString.length; j++)
					{
						if(j==0)
						{
							tempName=employerString[j];
						}
						if(j==1)
						{
							tempID=Integer.parseInt(employerString[j]);
						}
						if(j==2)
						{
							tempEmployeeName=employerString[j];
						}
						if(j==3)
						{
							tempEmployeeSSN=Integer.parseInt(employerString[j]);
						}
						if(j==4)
						{
							tempEmployeeWages=Integer.parseInt(employerString[j]);
						}
							//Employer(String name, int ID, String employeeName,int employeeSSN, int employeeWages) 
					}
					mainEmployer = new Employer (tempName, tempID, tempEmployeeName,tempEmployeeSSN, tempEmployeeWages);
					employersArrayList.add(mainEmployer);
					
					//System.out.println("employer test");
					//System.out.print(mainEmployer.getName()+" ");
					//System.out.print(mainEmployer.getID()+" ");
					//System.out.print(mainEmployer.getEmployeeName()+" ");
					//System.out.print(mainEmployer.getEmployeeSSN()+" ");
					//System.out.println(mainEmployer.getEmployeeWages()+" ");
				}
				else
				{
					return;
				}
			}
			
			//takes each line of filer input lines (variable: filerLinesOneWord) and converts them into employersArrayList
			// which is an arraylist of Filer objectecs, each made from a line of filer input lines
			
			for(int i=1; i<filerLinesOneWord.size(); i++)
			{
				if (filerLinesOneWord.get(i) != "")
				{
					//ex: FOX CHARLES, 724113610, 50
					String[] filerString = filerLinesOneWord.get(i).split(", ");
					
					String tempName = null;
					int tempSSN = 0;
					int tempIncome = 0;
					
					for(int j=0; j<filerString.length; j++)
					{
						if(j==0)
						{
							tempName=filerString[j];
						}
						if (j==1)
						{
							tempSSN=Integer.parseInt(filerString[j]);
						}
						if (j==2)
						{
							tempIncome=Integer.parseInt(filerString[j]);
						}
					}
					mainFiler = new Filer(tempName, tempSSN, tempIncome);
					filersArrayList.add(mainFiler);
					
					//Filer(String name, int SSN, int income)
					
					//System.out.println("filer test");
					//System.out.print(mainFiler.getName()+" ");
					//System.out.print(mainFiler.getSSN()+" ");
					//System.out.println(mainFiler.getIncome()+" ");
					
				}
				else
				{
					return;
				}
			}
    	
		//System.out.println("check to see if employersArrayList and filersArrayList exist");
		//for( int i=0; i<employersArrayList.size(); i++)
		//{
		//	System.out.println(employersArrayList.get(i).getName());
		//}
		//for( int i=0; i<filersArrayList.size(); i++)
		//{
		//	System.out.println(filersArrayList.get(i).getName());
		//}
		//System.out.println("the employersArrayList and filersArrayList came through");
		//System.out.println(" ");
			
    	ReturnCheck mainReturnCheck = new ReturnCheck(employersArrayList, filersArrayList);
    	// public ReturnCheck(ArrayList<Employer> employers, ArrayList<Filer> filers
    	//create the descrepency object in ReturnCheck
    	
    	//sorts filers list
    	//mainReturnCheck.sortReturnsBySSN();  //doent matter
    	//mainReturnCheck.sortEmployerRecordsByEmployeeSSN();
    	
    	//mainReturnCheck.sort this    	 .sortReturnsBySSN()  filers
    	//									.sortEmployerRecordsByEmployeeSSN()
    	//								
    	mainReturnCheck.calculateDiscrepancies();
    	//.sortDiscrepancies()

    	//Discrepancy mainDiscrepency = new Discrepancy(boolean alert, int wageDiscrepancy,String filerName, int filerSSN, String filerAlias);
    	
    	//System.out.println("check the return check");
    	//System.out.println(mainReturnCheck.writeDiscrepanciesToString());
    	
    	//sort return checks
    	//mainReturnCheck.getDiscrepancies();
    	
    	
    	mainReturnCheck.sortDiscrepancies();
        
    	System.out.println("UNSORT OUTPUT OUTPUT OUTPUT OUTPUT OUTPUT OUTPUT OUTPUT OUTPUT OUTPUT OUTPUT OUTPUT UNSORT");
    	System.out.println(mainReturnCheck.writeDiscrepanciesToString());
    	
        //then display discrepancies and exit.
    	mainReturnCheck.writeDiscrepanciesToString(); //this prints each discrepency as a new line
    	//exit here
    	}
       } //try
       catch (NullPointerException | NoSuchElementException | IllegalArgumentException |IOException | ArrayIndexOutOfBoundsException errorHere)
       {
    	   System.out.println("Input files not found or there're problems with the files. Check your input files.");
    	   errorHere.printStackTrace();
       }
      }
    }

