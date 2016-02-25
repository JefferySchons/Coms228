package edu.iastate.cs228.hw2;

/**
 * Keeps track of one line read from the Filer File
 * 
 * @author
 */
public class Filer //this is the information from the Employee
{
        /**
         * Create 3 protected variables, one for the filer name, one
         * for the filer SSN, and one for the reported filer income.
         */

		private String filerName;
		private int filerSSN;
		private int reportedFilerIncome;
		
        /**
         * Simple constructor for setting values of name, SSN, and income
         * 
         * @param name
         *            Name of individual filer
         * @param SSN
         *            SSN of individual filer
         * @param income
         *            Income reported by individual filer
         */
        public Filer(String name, int SSN, int income) {
        	filerName=name;
        	filerSSN = SSN;
        	reportedFilerIncome=income;
        }

        /**
         * Gets the filer's name
         * 
         * @return the name
         */
        public String getName() {
                return filerName;
        }

        /**
         * Gets the filer's SSN
         * 
         * @return the SSN
         */
        public int getSSN() {
                return filerSSN;
        }

        /**
         * Gets the filer's claimed income
         * 
         * @return the income
         */
        public int getIncome() {
                return reportedFilerIncome;
        }
}
