package edu.iastate.cs228.hw2;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Checks for discrepancies between employer filings and individual filings.
 * 
 * @author
 */
public class ReturnCheck {
        /**
         * Define private ArrayList variables for employers, filers, and
         * discrepancies
         */
		
		private ArrayList<Employer> ReturnCheckEmployers;
		private ArrayList<Filer> ReturnCheckFilers;
		private ArrayList<Discrepancy> ReturnCheckDiscrepancies;
		//Employer minEmployer = new Employer(null, 0, null, 0, 0);
		
        /**
         * Simple constructor setting the employers and filers ArrayLists
         * 
         * @param employers
         * @param filers
         */
        public ReturnCheck(ArrayList<Employer> employers,
                           ArrayList<Filer> filers) 
        {
        	ReturnCheckEmployers=employers;
        	ReturnCheckFilers=filers;
        }

        /**
         * Gets the employers ArrayList
         * 
         * @return the list of employers filings.
         */
        public ArrayList<Employer> getEmployers() {
                return ReturnCheckEmployers;
        }

        /**
         * Gets the filers ArrayList
         * 
         * @return the list of individual filers filings.
         */
        public ArrayList<Filer> getFilers() {
                return ReturnCheckFilers;
        }

        /**
         * Gets the discrepancies ArrayList. Must have called
         * calculateDiscrepancies first.
         * 
         * @return the list of discrepancies between employers and filers
         */
        public ArrayList<Discrepancy> getDiscrepancies() 
        {
                return ReturnCheckDiscrepancies;
        }

        /**
         * Sets the discrepancies list
         * 
         * @param discrepancies
         *            the new discrepancies list.
         */
        public void setDiscrepancies(ArrayList<Discrepancy> discrepancies) 
        {
        	ReturnCheckDiscrepancies=discrepancies;
        }

        /**
         * Sorts the employers list by employeeSSN using insertion sort
         */
        public void sortEmployerRecordsByEmployeeSSN() {
        	int n=ReturnCheckEmployers.size(); // length of he array ArrayList<Employer> ReturnCheckEmployers
        	int i; //index for selected number
        	int j; //index for number to see for swap

        			
        	for (i=0; i< n-1; i++)
        	{
        		int tempSSN = ReturnCheckEmployers.get(i).getEmployeeSSN();
        		j=i-1;
        		while(j>-1 && ReturnCheckEmployers.get(j).getEmployeeSSN() >tempSSN)
        		{
        			ReturnCheckEmployers.set(j+1, ReturnCheckEmployers.get(j));
        			j--;
        			
        		}
        		ReturnCheckEmployers.set(j+1, ReturnCheckEmployers.get(i));
        	}
        }

        /**
         * Sorts the filers list by SSN using merge sort
         */
		public void sortReturnsBySSN() 
        {
        	ArrayList<Filer> A = ReturnCheckFilers;
        	MergeSort(A);
        	ReturnCheckFilers=A;
        	return;
        }
        
		/**
		 * the mergesort function
		 * @param a
		 * @return 
		 */
		private ArrayList<Filer> MergeSort(ArrayList<Filer> a) 
		{
        	int n=a.size(); // length of he array ArrayList<Employer> ReturnCheckEmployers
        	if(n<=1)
        	{
        		return a;
        	}
        	int m=n/2;
        	//Arraylist <> Aleft =(A[0], ......,A[m-1])
        	ArrayList<Filer> Aleft = new ArrayList<Filer>();
        	for(int i=0; i<=(m-1); i++)
        	{
        		Aleft.add(a.get(i));
        	}
        	//Aright = (A[m],.....,A[n-1])
        	ArrayList<Filer> ARight = new ArrayList<Filer>();
        	for(int j=m; j<=n-1; j++)
        	{
        		ARight.add(a.get(j));
        	}
        	MergeSort(Aleft);  //are recurcive calls
        	MergeSort(ARight);
        	//A=Merge(Aleft,Aright)
        	a=Merge(Aleft,ARight);
        	return a;
			
		}

		/**
		 * the merge part of mergesort
		 * @param aleft
		 * @param aRight
		 * @return
		 */
		private ArrayList<Filer> Merge(ArrayList<Filer> aleft,
				ArrayList<Filer> aRight) 
		{
			int p = aleft.size();
			int q=aRight.size();
			ArrayList<Filer> left = aleft;
			ArrayList<Filer> right = aRight;
			
			ArrayList<Filer> D = new ArrayList<Filer>(p+q); //array D of length p+q;
			int i=0;
			int j=0;
			while (i<p&&j<q)
			{
				if (left.get(i).getSSN() <= right.get(i).getSSN())
				{
					//apend left.get(i) to D;
					D.add(left.get(i));
					i++;
				}
				else
				{
					//apend right.get(j) to D;
					D.add(right.get(j));
					j++;
				}
			}
			if (i >= p)
			{
				//apend right.get(j) to right.get(q-1) to D;
				for (int m=j; m==q-1; m++)
				{
					D.add(right.get(m));
				}
			}
			else
			{
				//apend left.get(i) to left.get(p-1) to D;
				for (int n=i; n==p-1; n++)
				{
					D.add(left.get(n));
				}
			}
			return D;
		}


        /**
         * Sorts the discrepancies list using Java's built-in sorting
         * options.  Assumes calculatedDiscrepancies has been called.
         * 
         * The sort order for discrepancies is defined as follows: 
         * 1. All records with an alert should be displayed before
         * any record without.
         * 2. When alert statuses are equal, larger absolute value
         *  of wage discrepancy are listed ahead of smaller.
         * 3. When all of the above are equal, underpayments should
         * appear before over-payments.
         * 4. When all of the above are equal, filer's names as
         * reported in their return should be listed alphabetically
         * 5. When all of the above are equal, numerically smaller
         *  SSNs should appear before larger.
         */
        public void sortDiscrepancies() {
        	ArrayList<Discrepancy> sortingDiscrepancies;
        	sortingDiscrepancies=ReturnCheckDiscrepancies;
        	
        	Collections.sort(sortingDiscrepancies);
        	
        	
        	setDiscrepancies(sortingDiscrepancies); 

        	//the conditions are met in the Discrepency compaire to
        }

        /**
         * Calculates the discrepancies between employer filings and
         * employee filings.
         */
		public void calculateDiscrepancies() {
        	
        	
        	//loop through filers
        	//at filer i
        	//	cycle through Employer to see if match with SSN
        	// 	tag that compony ID put into araylist
        	//	add wages from that compony to a sumwages variable
        	// 	check if filer employee name == emplyer employee name
        	//		if not this is and alius and is alert
        	//	if later componey has same SSN and ID ignore(was printed in twice)
        	//	check if next compony has employee with same ID	
        	// 		check if filer employee name == emplyer employee name
        	//			if not this is and alius and is alert
        	//		if so, add wages to sumwages
        	//	after loop thsough all employers compaire sumwages to filer income
        	//	if filer income< sumwages alert
        	// (income-sumwages)=wageDiscrepancy
    		//send to discrepency
    		//ArrayList<Discrepancy> TempDiscrepancies;
    		//Discrepancy (boolean alert, int wageDiscrepancy, String filerName, int filerSSN,String filerAlias) 
        	
        	//call in getEmployers() and getFilers()
        	//sameEmployer () uses ID is to see if compony is same
        	//sameEmployee() usses SSN to see if employee is same
        	//one employee might work for multipul componies
			
			//ArrayList<Employer> tempEMP = ReturnCheckEmployers;
        	//sortEmployerRecordsByEmployeeSSN();
        	//sortReturnsBySSN();
    		
        	ArrayList<Discrepancy> TempDiscrepancies = new ArrayList<Discrepancy>();
        	for(int i=0; i<ReturnCheckFilers.size(); i++)//loops through all Filers to get array of Discrepencies
        	{
        		Filer returnCheckTempFiler = ReturnCheckFilers.get(i);
        		int sumOfEmployeesWages =0;
        		boolean tempAlert=false;
        		boolean tempAlertAlias=false;
        		String tempAlias="";
        		int tempWageDiscrepancy=0;
        		int tempID;
        		boolean sameID = false;
        		ArrayList<Integer> IDList = new ArrayList<>();
        		
        		for(int j=0; j<ReturnCheckEmployers.size(); j++)  //cycle through Employers to find match
        		{
        			Employer returnCheckTempEmployers=ReturnCheckEmployers.get(j);
        			if(returnCheckTempFiler.getSSN()!= returnCheckTempEmployers.getEmployeeSSN()) //compaires to see if same employee via SSN's
        			{
        				//do nothing
        			}
        			else //the SSN's are =
        			{
        				tempID = returnCheckTempEmployers.getID(); //tag that company ID
        				for(int k=0; k< IDList.size() &&IDList.size()>1; k++) //get company ID check it with other company ID's if not add it to ID list
        				{
        					if (tempID== IDList.get(k).intValue())
        					{
        						sameID=true;
        					}
        				}
        				IDList.add(tempID);
        				if (sameID==true) 
        				{
        					
        				}
        				else
        				{
        					sumOfEmployeesWages = sumOfEmployeesWages+returnCheckTempEmployers.getEmployeeWages();
        					//add wages to sumwages of employee
        					String A=returnCheckTempEmployers.getEmployeeName();
        					String B=returnCheckTempFiler.getName();
        					if(A.equals(B)) //check if filer employee name == emplyer employee name
        					{
        					}
        					else //name discrepency alert true posible alias
        					{
        						tempAlias=tempAlias+returnCheckTempEmployers.getEmployeeName()+", ";
        						tempAlertAlias=true;
        					}
        					
        				}
        				
        			}
        		}
        		
        		
        		tempWageDiscrepancy = returnCheckTempFiler.getIncome() - sumOfEmployeesWages; 
        		if(tempWageDiscrepancy<0 && tempAlertAlias==true) //compares wages and puts temp alert to true if alias or wage discreency
        		{
        			tempAlert=true;
        		}
        		//compaire sumwages to Filer income (income-sumwages)=wageDiscrepancy
        		// if filer (income< sumwages) => alert
        		
        		
        		//send to discrepency
        		//ArrayList<Discrepancy> TempDiscrepancies;
        		//Discrepancy (boolean alert, int wageDiscrepancy, String filerName, int filerSSN,String filerAlias) 
        		
        		
        		Discrepancy tempListDiscrepancy= new Discrepancy (tempAlert, tempWageDiscrepancy, returnCheckTempFiler.getName(), returnCheckTempFiler.getSSN(),tempAlias); 
        	
        		TempDiscrepancies.add(tempListDiscrepancy);
        	}

        	//is empty
        	//getDiscrepancies()
        	
        	
        	//Discrepancy (boolean alert, int wageDiscrepancy, String filerName, int filerSSN,String filerAlias) 
        		
        		//alert if
                //if filer is less then Employers then underpaid 
        		//if surname on filer != any surname with Employers 
        	
        	//set this to new discrepency list
        	setDiscrepancies(TempDiscrepancies);
        }

        /**
         * Creates the output string for each discrepancy, with a
         * newline after each discrepancy. The output string is
         * formatted as follows: 
         * <alert><wage discrepancy>,_<filer name>,_<filer SSN>(,_<filer alias>)\n 
         * Where: '\n' is a newline character ',' is a literal comma,
         * '_' is a single space character, <alert> is an asterisk 
         * ('*') if the filer is suspected of fraud, <wage discrepancy>
         * is the non-zero difference between the sum of the wages
         * reported by all employers for this filer's SSN and the income
         * reported by filer in his or her return, including a prefixed
         * sign, whether positive or negative <filer name> is the filer's
         * name as reported in the filer's return, <filer SSN> is the
         * filer's social security number, and (,_<filer alias>)* is a
         * comma-and-space delineated list of zero or more employer-
         * reported names associated with the filer's SSN that do not
         * exactly match the name reported in the filer's return, with
         * ',' and '_' defined as between the required fields.
         * 
         * @return The concatenated strings of each discrepancy, in order.
         */
        public String writeDiscrepanciesToString() {
        	String discrepanciesString = "";
        	for (int i=0; i<ReturnCheckDiscrepancies.size(); i++)
        	{
        		if (ReturnCheckDiscrepancies.get(i).getwageDiscrepancy()==0)
        		{
        			
        		}
        		else
        		{
            		discrepanciesString=discrepanciesString+ReturnCheckDiscrepancies.get(i).toString()+'\n';
        		}
        	}

        	return discrepanciesString;	
        }

        /**
         * Uses a binary search to find all employer records for the
         * SSN provided in the Filer argument.
         * 
         * @param filer
         *            Used to provide an SSN to search for in the
         *             employer records.
         * @return A list of employer filings that contain the same
                   SSN as the filer.
         */
		public ArrayList<Employer> findEmployerRecords(Filer filer) {
        	//should be sorted already
        	int n;
        	n=ReturnCheckEmployers.size();
        	ArrayList<Employer> matchingEmployers = new ArrayList<Employer>();

        	int left = 0;
        	int right = n-1;
        	while (left<=right)
        	{
        		int mid=(left+right)/2;
        		if (ReturnCheckEmployers.get(mid).getEmployeeSSN()==filer.getSSN())
        		{
        			matchingEmployers.add(ReturnCheckEmployers.get(mid));
        		}
        		else if (filer.getSSN()<ReturnCheckEmployers.get(mid).getEmployeeSSN())
        		{
        			right=mid-1;
        		}
        		else //(filer.getSSN() > ReturnCheckEmployers.get(mid))
        		{
        			left=mid+1;
        		}
        	}
			return matchingEmployers;
        }
}
