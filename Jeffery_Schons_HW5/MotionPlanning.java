package edu.iastate.cs228.hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import edu.iastate.cs228.hw5.MotionPlanning.IntegerPair;


/**
 * 
 * @author Jeffery Schons
 *
 */

public class MotionPlanning {

    /**
     * Returns a floor plan graph built from an input file floorPlanFile, which
     * is in the following format:
     * 
     * Each line, which indicates exactly one edge of the graph should contain
     * two integers, Ui and Vi, that are the endpoints of the edge.
     * 
     * Each edge (u, v) must appear exactly once in the input file and must not
     * be repeated as (v, u).
     * 
     * Poorly formed lines of the floor plan (i.e. lines that do not have
     * exactly 2 integers) should simply be skipped
     * 
     * @param floorPlanString
     * - the file containing the floor plan
     * @return a Graph constructed from the floor plan, null if the given file
     * doesn't exist.
     * 
     */
    public static Graph<Integer> readFloorPlan(String floorPlanString) {
    	
    	int Ui;
    	int Vi;
    	String[] theLinetoGraph;
    	Graph<Integer> floorGraph= new Graph<Integer>();
    	ArrayList<String> edgePairs=new ArrayList<String>();
    	String edgePairString;
    	
    	try
    	{
    		File floorPlanFile = new File (floorPlanString);
    		Scanner fileNameScanner = new Scanner(floorPlanFile);
    		
    		while (fileNameScanner.hasNextLine())
    		{
    			Ui=-1;
    			Vi=-1;
    			//this is used
    			boolean skipLine=false;
    			
    			String thisLine=fileNameScanner.nextLine();
    			
    			//split line into arraylist
    			theLinetoGraph=thisLine.split("\\s+");
    			
    			
    			//if theLinetoGraph.length=2 do bellow
    			if(theLinetoGraph.length==2)
    			{
    				try{
    					Ui = Integer.parseInt(theLinetoGraph[0]);
    					Vi = Integer.parseInt(theLinetoGraph[1]);
  					  // both integers
  					} catch (NumberFormatException e) {
  					  //not an integer
  						skipLine=true;
  					}
    			}
    			else
    			{
    				skipLine=true;
    			}
    			
    			
    			if (skipLine=false)
    			{
    				boolean repetedLine=false;
    				
    				//put Ui & Vi into edgePairs with the lower of the two first
    				if(Ui<Vi)
    				{
    					edgePairString=Ui+""+Vi;
    					edgePairs.add(edgePairString);
    				}
    				else//	Ui>Vi
    				{
    					edgePairString=Vi+""+Ui;
    					edgePairs.add(edgePairString);
    				}
    				
    				for(int i = 0; i<edgePairs.size();)
    				{
    					if(edgePairString.equals(edgePairs.get(i)))
    					{
    						repetedLine=true;
    					}
    					else
    					{
    						i++;
    					}
    				}
    				
    				if(repetedLine==false)
    				{
    					//put Ui & Vi into graph
    					floorGraph.addVertex(Ui);
    					floorGraph.addVertex(Vi);
    					
    					floorGraph.addEdge(Ui,Vi);
    				}
    				else
    				{
    					//skip
    				}
    				
    			}
    			else
    			{
    				//skip
    			}
    		}
    		fileNameScanner.close();
    	}
    	catch (FileNotFoundException e)
    	{
    		//System.out.println("Warning: document not found");
    		return null;
    	}
        
		return floorGraph;
    }

    /**
     * Returns the square of the (floor plan) graph g.
     * 
     * @param g
     * - the Graph to create the square of
     * @return the square of g
     */
    public static Graph<IntegerPair> square(Graph<Integer> g) {
        
		Graph<IntegerPair> squareMapH = new Graph<IntegerPair>();
    	//new graph h;
    	
        Iterator<Integer> iteratiorForG = g.vertices().iterator();
		while (iteratiorForG.hasNext())
		{
			Integer thisNodeInG = iteratiorForG.next();
			
			IntegerPair pairXX = new IntegerPair(thisNodeInG,thisNodeInG);
			
			 Iterator<Integer> naborsOfNodeInG = g.adjacentTo(thisNodeInG).iterator();
			 
			 while(naborsOfNodeInG.hasNext())
			 {
				 Integer theNabor = naborsOfNodeInG.next();
				 IntegerPair pairXY=new IntegerPair(thisNodeInG,theNabor);
				 
				//put nodeInG and each nabor as a IntegerPair  (integer pair #2)
				 
				 squareMapH.addEdge(pairXX, pairXY);
				// add edge to h with verivies as ip1 and ip2
			 }
        
		}
		return null;
		
		//loop through g
        	//make integer pair of nodeg (integer pair #1)
        	//get g's nabors using public Iterable<V> adjacentTo(V v)
        		//put nodeInG and each nabor as a IntegerPair  (integer pair #2)
        		// add edge to h with verivies as ip1 and ip2
    }

    /**
     * The arguments are the square graph h for some floor plan graph, and an
     * integer r >= 0. The method deletes nodes and edges from h to obtain the
     * configuration-space graph. This method must be implemented as described
     * on page 6 of the spec.
     * 
     * @param g
     * - the floor plan graph
     * @param h
     * - the graph to reduce
     * @param r
     * - the minimum allowed distance between vertices in g
     * @throws IllegalArgumentException
     * - if r is negative
     */
    public static void configSpace(Graph<Integer> g, Graph<IntegerPair> h, int r) throws IllegalArgumentException 
    {
    
    	if(r<0)
    	{
    		throw new IllegalArgumentException();
    	}
    	else
    	{
    		//go through all nodes in g (this one is x) breadth-first search
    		
    		Iterator<Integer> iteratiorForG = g.vertices().iterator();
    		
    		while (iteratiorForG.hasNext())
    		{
    			Integer thisNodeInG = iteratiorForG.next();
    			
    			
    			Map<V, Integer> distanceMap;
    			
    			Graph<Integer> distanceGraph;
    		
    			//gets map with distances from x to every node(y)
    			distanceMap.getDistancesFrom(g,thisNodeInG);
    			// retruns  Map<vertex, distance>
    		
    			///itorator of the keys of map
    			Iterator<Integer> distances = distanceMap.vertices().iterator();
    			while (distances.hasNext()) 
    			{
    			  Integer XNode = distances.next();
    			  Integer YXDistance=distanceMap.get(XNode);
    			  
    			  
    			  
    			  //only distances that are bellow r 
    			  if(YXDistance<r)
    			  {
    				  IntegerPair pairXY = new IntegerPair(XNode, thisNodeInG);
    				  IntegerPair pairYX = new IntegerPair(thisNodeInG, XNode);
    				  //remove vertex from h
    				  h.removeVertex(pairXY);
    				  h.removeVertex(pairYX);
    			  }
    			}
    			
    			
    		
    		//For each node u in G, we run a breadth-first search from u and identify all nodes v at
    		//distance strictly less than r from u. We list all these pairs (u; v) and delete them from H.
    		//go through all nodes in h

    		}
    	}
    }
    
    

    /**
     * Returns a String representation of an interference-free schedule for two
     * robots on graph g, where robots 1 and 2 start at nodes s1 and s2,
     * respectively, and end at nodes f1 and f2, respectively, and at every step
     * of the schedule, robots 1 and 2 are at distance at least r - 1 from each
     * other. If there is no interference-free schedule, the String returned
     * should be "NoScheduleExists". The method must use the algorithm described
     * on page 6. That is, it must (1) build the square of g using square(), (2)
     * use configSpace() to build the configuration-space graph using the square
     * of g, and (3) use depth- or breadth-first search to determine if there is
     * a path from the starting configuration to the final configuration in the
     * configuration-space graph.
     * 
     * @param g
     * - the floor plan graph
     * @param r
     * - the minimum allowed distance between vertices in g
     * @param s1
     * - the start vertex of Robot 1
     * @param f1
     * - the final vertex of Robot 1
     * @param s2
     * - the start vertex of Robot 2
     * @param f2
     * - the final vertex of Robot 2
     * @return a String as formatted above if a path exists, "NoScheduleExists"
     * otherwise
     * @throws IllegalArgumentException
     * - if r is negative or s1, f1, s2, or f2 are not in g
     */
    public static String schedule(Graph<Integer> g, int r, int s1, int f1, int s2, int f2) throws IllegalArgumentException 
    {
    	boolean s1Exists=g.hasVertex(s1);
    	boolean f1Exists=g.hasVertex(f1);
    	boolean s2Exists=g.hasVertex(s2);
    	boolean f2Exists=g.hasVertex(f2);
    	
    	if(r<0| s1Exists==false | f1Exists == false | s2Exists == false | f2Exists == false)
    	{
    		throw new IllegalArgumentException();
    	}
        else
        {
    		//throw an IllegalArgumentException if r < 0, or if s1, s2, f1, or f2 are not nodes of g.
    	
    	
    		Graph<IntegerPair> squareGraphOfG;
    		squareGraphOfG=square(g);
    		//Graph<IntegerPair> configSpaceOfG = new Graph<IntegerPair>();
    		
    		configSpace( g, squareGraphOfG, r);
    		
    		IntegerPair startPosition = new IntegerPair(s1,s2);
    		IntegerPair endPosition = new IntegerPair(f1,f2);
    		
    		String stringToOutPut=startPosition.toString();
    		
    		//squareGraphOfG.get
    		Iterable<V> pathItorator;
    		
    		pathItorator.getPath(squareGraphOfG, startPosition, endPosition);
    		//null if no path
    		
    		//g.vertices().iterator();
    		
    		
    		while (pathItorator.hasNext())
    		{
    			IntegerPair thisNodeInG = pathItorator.next();
    			stringToOutPut=stringToOutPut+thisNodeInG.toString();
    		}
    		
    		if(pathItorator==null)
    		{
    			stringToOutPut="NoScheduleExists";
    			return stringToOutPut;
    		}
    		else
    		{
    			return stringToOutPut;
    		}
    		
    		//think of the interger pairs as where the 2 robots are. 
    		//IntegerPair(robot 1 start, robot 2 start)  IntegerPair2(robot 1 end, robot 2 end) 
    		//if getPath(Graph<V> g, V s, V t)== null return "NoScheduleExists"
    		//else iterate over it pringting it out
    		

        
    		//\\(1) build the square of g using square()
    		//\\(2)use configSpace() to build the configuration-space graph using the square of g,
    		//(3) use depth- or breadth-first search to determine if there is a path from the 
    		//		 starting configuration to the final configuration in the configuration-space graph
        
        }
    }

    /**
     * Helper class to store data for a floor plan's square
     */
    public static class IntegerPair {

        /*
         * Instance variable for the stored Integers
         */
        int first, second;
        
        /**
         * Stores the given ints
         */
        public IntegerPair(int first, int second) {
            this.first=first;
            this.second=second;
        }

        /**
         * Returns true if o is an IntegerPair and the ints this and o store are
         * the same, and in the same order.
         */
        @Override
        public boolean equals(Object o) {
        	if(this.getClass().getName()!="IntegerPair" || o.getClass().getName()!="IntegerPair")
        	{
        		return false;
        	}
        	else
        	{
        		if(this.toString().equals(o.toString()))
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
         * Returns the stored ints enclosed in parenthesis separated by a comma.
         */
        public String toString() {
        	String retruningToString="("+first+","+second+")";
            return retruningToString;
        }

        /**
         * This is an example hashCode() method. You are welcome to write your
         * own, but this method but always retain the characteristic:
         * 
         * if a.equals(b) then a.hashCode() == b.hashCode()
         * 
         * (Note, the converse of this is not always true)
         */
        @Override
        public int hashCode() {
            return 100003 * first + second;
        }
    }
}
