package edu.iastate.cs228.hw2;


/**
 * Keeps track of one employer filing for the IRS tax records.
 * 
 * @author
 */

public class Employer {
        /**
         * Create protected variables for employer name, employer ID,
         * employee name, employee SSN, and employee wages as reported
         * by the employer.
         */
	
	private String name;
	private int ID;
	private String employeeName;
	private int employeeSSN;
	private int employeeWages;

        /**
         * Simple constructor for setting value of protected variables
         * 
         * @param name
         *            Employer name
         * @param ID
         *            Employer ID
         * @param employeeName
         *            name of employee
         * @param employeeSSN
         *            SSN of employee
         * @param employeeWages
         *            wages earned by employee through this employer
         */
        public Employer(String name, int ID, String employeeName,
                        int employeeSSN, int employeeWages) 
        {
        	this.name = name;
        	this.ID=ID;
        	this.employeeName=employeeName;
        	this.employeeSSN=employeeSSN;
        	this.employeeWages=employeeWages;
        }

        // all this sees is if they are both Employer
        @Override
        public boolean equals(Object obj) {
        	if (obj == null || obj.getClass() != this.getClass())
        	{
        		return false;
        	}
        	else
        	{
        		if (obj.toString() == "Employer" && this.toString()=="Employer")
        		{
        			return true;
        		}
        		else 
        		{
        		return false;
        		}
        			
        	}
        }
        
        

        /**
         * Compares this against another Employer object to see if
         * the employers are the same in each.
         * 
         * @param employer
         *            The employer to compare against.
         * @return true if and only if the ID fields of this and
         *         employer are equal.
         */
        public boolean sameEmployer(Employer employer) {
                if (employer.getID() == this.getID())
                {
                	return true;
                }
                else
                {
                	return false;
                }
               
        }

        /**
         * Compares this against an Employee object to see if the
         * employees are the same in each.
         * 
         * @param employee
         *            The employee to compare against.
         * @return true if and only if the SSN fields of this and
         *         employer are equal.
         */
        public boolean sameEmployee(Employer employee) {
            if (employee.getEmployeeSSN() == this.getEmployeeSSN())
            {
            	return true;
            }
            else
            {
            	return false;
            }
        }

        /**
         * Gets the employer name.
         * 
         * @return The employer name
         */
        public String getName() {
                return name;
        }

        /**
         * Gets the employer ID
         * 
         * @return The employer ID
         */
        public int getID() {
                return ID;
        }

        /**
         * Gets the employee name
         * 
         * @return The employee name
         */
        public String getEmployeeName() {
                return employeeName;
        }

        /**
         * Gets the employee SSN
         * 
         * @return the employee SSN
         */
        public int getEmployeeSSN() {
                return employeeSSN;
        }

        /**
         * Gets the employee wages claimed by the employer
         * 
         * @return the employee wages
         */
        public int getEmployeeWages() {
                return employeeWages;
        }
         
        /**
         * toString outputs Employer
         * 
         * @return "Employer"
         */
        @Override
        public String toString()
        {
        	return "Employer";
        }
}
