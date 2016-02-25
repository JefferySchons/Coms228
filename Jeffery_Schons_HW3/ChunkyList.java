package edu.iastate.cs228.hw3;

import java.rmi.NoSuchObjectException;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the list interface based on linked nodes
 * that store multiple items per node.  Rules for adding and removing
 * elements ensure that each node (except possibly the last one)
 * is at least half full.
 */
public class ChunkyList<E extends Comparable<? super E>> extends AbstractSequentialList<E>
{
  /**
   * Default number of elements that may be stored in each node.
   */
  private static final int DEFAULT_NODESIZE = 4;
  
  /**
   * Number of elements that can be stored in each node.
   */
  private final int nodeSize;
  
  /**
   * Dummy node for head.  It should be private but set to public here only  
   * for grading purpose.  In practice, you should always make the head of a 
   * linked list a private instance variable.  
   */
  public Node head = null;
  
  /**
   * Dummy node for tail.
   */
  private Node tail = null;
  
  /**
   * Number of elements in the list.
   */
  private int size = 0; //preset to 0
  
  /**
   * Constructs an empty list with the default node size.
   */
  public ChunkyList()
  {
    this(DEFAULT_NODESIZE);
  }

  /**
   * Constructs an empty list with the given node size.
   * @param nodeSize number of elements that may be stored in each node, must be 
   *   an even number
   */
  public ChunkyList(int nodeSize)
  {
    if (nodeSize <= 0 || nodeSize % 2 != 0) throw new IllegalArgumentException();
    
    // dummy nodes
    head = new Node();
    tail = new Node();
    head.next = tail;
    tail.previous = head;
    this.nodeSize = nodeSize;
  }
  
  /**
   * Constructor for grading only.  Fully implemented. 
   * @param head
   * @param tail
   * @param nodeSize
   * @param size
   */
  public ChunkyList(Node head, Node tail, int nodeSize, int size)
  {
	  this.head = head; 
	  this.tail = tail; 
	  this.nodeSize = nodeSize; 
	  this.size = size; 
  }

  @Override
  public int size()
  {
    return size;
  }
  
  @Override
  public boolean add(E item)
  {
	  //first see there is a node
	  //		if not make node place E at place one
	  //if their exists a node
	  //	check if last node is full
	  //		if no add E to next open place in last node
	  //	else: make node and place E in first place of that node
	  //else: make node and place E in first place of that node
    //add to end of list
	  if (item==null)
	  {
		  throw new NullPointerException();
	  }
	  else
	  {
		  if (head.next ==tail)
		  {
			  //there is no nodes
			  Node temp = new Node();
			  temp.addItem(item);
			  tail.previous =temp;
			  temp.next=tail;
			  head.next= temp;
			  temp.previous=head;
			  size++;
		  }
		  else if(tail.previous.count<nodeSize) // see if last space in node is not full
		  {
			  // there are nodes and the last node is full, does makes node
			  //Node temp = new Node (null, null, tail);
			  tail.previous.addItem(item);
			  size++;
		  }
		  else 
		  {
			//place this data in the last node, makes a node
			  Node temp = new Node();
			  temp.addItem(item);
			  tail.previous.next =temp;
			  tail.previous= temp;
			  temp.addItem(item);
			  size++;
		  }
		  return true;
	  }
	  
  }

  @Override
  public void add(int pos, E item)
  {
	//find node 
  	if (head.next==tail)//the collection is empty, cant add at that index
  	{
  		throw new NoSuchElementException();
  	}
  	else if(item==null | pos<0)
  	{
  		throw new NullPointerException();
  	}
  	else
  	{
  	    Node temp;
  	    temp=head;
  	    int index=temp.count;
  	    while (index<pos  && temp!=tail)
  	    {
  	    	index=index+temp.count;
  	    	temp=temp.next;
  	    }
  	    if (pos > index && temp==tail) //added after last node, 
  	    { //nodeSize
  	    	if (pos - index > 1) 
  	    		// is trying to be added beyond where there are elements, skiping elements
  	    	{
  	    		throw new NoSuchElementException();
  	    	}
  	    	else
  	    	{
  	    		Node newNode=null;
  	    		newNode.addItem(item);
  	    		temp.previous.next=newNode;
  	    		newNode.previous=temp.previous;
  	    		newNode.next=tail;
  	    		size++;
  	    	}
  	    }
  	    else if(pos == index && temp==tail) //error
  	    {
  	    	//add this to last open space in temp, last space in temp is open, temp is end node
  	    	temp.previous.addItem(item);
  	    	size++;
  	    }
  	    else if(pos != index && temp==tail) //not at last open space in temp and last node
  	    {
  	    		
	    	Node newNode=null; //new end node
	    	newNode.addItem(temp.previous.data[temp.count]);
	    	temp.previous.next=newNode;
	    	newNode.previous=temp.previous;
	    	newNode.next=tail;
	    	size++;
  	    }
  	    else if(pos == index && temp!=tail) // last open space in temp , not last node
  	    {
  	    	temp.previous.addItem(item);
  	    	size++;
  	    }
  	    else 									//not last element in temp, not last node
  	    {
  	    	int offset=temp.count -(index-pos);
  	    	E tempData =temp.previous.data[offset];
  	    	temp.previous.data[offset]=item;
  	    	offset=offset+1;
  	    	E otherTempData;
  	    	for(;offset==temp.count; offset++)
  	    	{
  	    		otherTempData=temp.previous.data[offset];
  	    		temp.previous.data[offset]=tempData;
  	    		tempData=otherTempData;
  	    	}
  	    	while(temp.next!=tail) //cycles through nodes
  	    	{
  	    		for(int i=0; i==temp.count; i++)
  	    		{
  	    			otherTempData=temp.data[i];
  	    			temp.data[i]=tempData;
  	    			tempData=otherTempData;
  	    		}
  	  	    	temp=temp.next;
  	    	}
  	    	if(temp.previous.count==nodeSize) //need to make a new node, based on if last node is full before shift
  	    	{
  		    	Node newNode=null; //new end node
  		    	newNode.addItem(tempData);
  		    	temp.previous.next=newNode;
  		    	newNode.previous=temp.previous;
  		    	newNode.next=tail;
  	    	}
  	    		
  	    	size++;
  	    }
  	}
  }

  //fi pos == size then node is tail node
  //The return value should be the actual node and offset at which the 
  //new element was placed. (In some cases this will
	//be the given node and offset, in some cases it will be the previous node, and in case of a split
	//there might be a completely new node.) You don’t need the return value for the add method,
	//but you will need it in the iterator.
  
  @Override
  public E remove(int pos)
  {
	  if (head.next==null)//the collection is empty, cant add at that index
	  {
		  throw new NoSuchElementException();
	  }
	  else if(pos<0 | pos>size)
	  {
		  throw new NoSuchElementException();
	  }
	  else
	  {
	  	    Node temp;
	  	    temp=head;
	  	    int index=temp.count;
	  	    while (index<pos  && temp!=tail)
	  	    {
	  	    	index=index+temp.count;
	  	    	temp=temp.next;
	  	    }
	  	    temp=temp.previous;//to put temp to beeing the node with the pos in it
	  	    int offset=temp.count-(index-pos);//pos is at place temp.count-(index-pos) in temp
	  	    //diferent conditions
	  	    
	  	    if (offset==1) //only item in that node
	  	    {
	  	    	temp.previous=temp.next;
	  	    	temp.next=temp.previous;
	  	    	size--;
	  	    }
	  	    else if(offset==index) //last element in node
	  	    {
	  	    	
	  	    }
	  	    else //
	  	    {
	  	    	size--;
	  	    	E tempData;
	  	    	for(;offset==temp.count-1; offset++)
	  	    	{
	  	    		tempData=temp.data[offset+1];
	  	    		temp.data[offset]=tempData;
	  	    	} 
	  	    	temp.data[temp.count]=temp.next.data[0]; // for the last element in the node with pos
	  	    	
	  	    	while(temp!=tail) //cycles through nodes 
	  	      	{
	  	      		for(int i=0; i==temp.count; i++)
	  	      		{ 
	  	      			if (i==temp.count && temp.next==tail)
	  	      			{
	  	      				//last element in the last node is removed becouse it has already been shifted
	  	      				temp.data[i]=null;
	  	      			}
	  	      			if (i==temp.count && temp.next!=tail) //last element in node, node not last
	  	      			{
	  	      				temp.data[i]=temp.next.data[0];
	  	      			}
	  	      			else
	  	      			{
	  	      				tempData=temp.data[i+1];
	  	      				temp.data[i]=tempData;
	  	      			}

	  	      		}
	  	      		temp=temp.next;
	  	      	}
	  	    	//first remove
	  	    	//	shift
	  	    	//		eddit nodes
	  	    	
	  	    	//eddit nodes
	  	    	Node secoundTemp;
	  	    	secoundTemp=head.next; //made a secound temp node to prevent problems with cycling through nodes
	  	    	while(secoundTemp.next!=tail)//loop through nodes
	  	    	{
	  	    		if(secoundTemp.count==0) //removes empty nodes
	  	    		{
	  	    			secoundTemp.previous=secoundTemp.next;
	  	    		}
	  	    		//merge operation
	  	    		if (secoundTemp.count<(nodeSize/2) && secoundTemp.next!=tail) //if node that is to small
	  	    		{
	  	    			if (secoundTemp.next.count<(nodeSize/2))
	  	    			{
	  	    				//move all elements from temp.next to temp
	  	    				for(int i=0; i==secoundTemp.next.count;i++)
	  	    				{
	  	    					secoundTemp.addItem(secoundTemp.next.data[i]);
	  	    				}
	  	    				
	  	    				//remove node
	  	    				secoundTemp.next.next.previous =secoundTemp;
	  	    				secoundTemp.next=secoundTemp.next.next;
	  	    			}
	  	    			else //move first element of temp.next to temp
	  	    			{
	  	    				secoundTemp.addItem(secoundTemp.next.data[0]);
	  	    				
	  	    				for(int i=0; i==secoundTemp.next.count-1; i++)
	  	    				{
	  	    					secoundTemp.next.data[i]=secoundTemp.next.data[i+1];
	  	    				}
	  	    				
	  	    				secoundTemp.next.data[secoundTemp.next.count]=null;
	  	    			}
	  	    		}
	  	    		secoundTemp=secoundTemp.next;
	  	    		//if temp.count<(nodeSize/2)
	  	    		//		if temp.next.count<(nodeSize/2) combing the two nodes
	  	    		//		//else move fist elemenent of temp.next to last open space in temp
	  	    	}
	  	    }
	  }
	return null;
  }

  /**
   * Sort all elements in the chunky list in the NON-DECREASING order. You may do the following. 
   * Traverse the list and copy its elements into an array, deleting every visited node along 
   * the way.  Then, sort the array by calling the insertionSort() method.  (Note that sorting 
   * efficiency is not a concern for this project.)  Finally, copy all elements from the array 
   * back to the chunky list, creating new nodes for storage. After sorting, all nodes but 
   * (possibly) the last one must be full of elements.  
   *  
   * Comparator<E> must have been implemented for calling insertionSort().    
   */
  public void sort()
  {
	  // TODO 
  }
  
  /**
   * Sort all elements in the chunky list in the NON-INCREASING order. Call the bubbleSort()
   * method.  After sorting, all but (possibly) the last nodes must be filled with elements.  
   *  
   * Comparable<? super E> must be implemented for calling bubbleSort(). 
   */
  public void sortReverse() 
  {
	  // TODO 
  }
  
  @Override
  public Iterator<E> iterator()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ListIterator<E> listIterator()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ListIterator<E> listIterator(int index)
  {
    // TODO Auto-generated method stub
    return null;
  }
  
  /**
   * Returns a string representation of this list showing
   * the internal structure of the nodes.
   */
  public String toStringInternal()
  {
    return toStringInternal(null);
  }

  /**
   * Returns a string representation of this list showing the internal
   * structure of the nodes and the position of the iterator.
   *
   * @param iter
   *            an iterator for this list
   */
  public String toStringInternal(ListIterator<E> iter) 
  {
      int count = 0;
      int position = -1;
      if (iter != null) {
          position = iter.nextIndex();
      }

      StringBuilder sb = new StringBuilder();
      sb.append('[');
      Node current = head.next;
      while (current != tail) {
          sb.append('(');
          E data = current.data[0];
          if (data == null) {
              sb.append("-");
          } else {
              if (position == count) {
                  sb.append("| ");
                  position = -1;
              }
              sb.append(data.toString());
              ++count;
          }

          for (int i = 1; i < nodeSize; ++i) {
             sb.append(", ");
              data = current.data[i];
              if (data == null) {
                  sb.append("-");
              } else {
                  if (position == count) {
                      sb.append("| ");
                      position = -1;
                  }
                  sb.append(data.toString());
                  ++count;

                  // iterator at end
                  if (position == size && count == size) {
                      sb.append(" |");
                      position = -1;
                  }
             }
          }
          sb.append(')');
          current = current.next;
          if (current != tail)
              sb.append(", ");
      }
      sb.append("]");
      return sb.toString();
  }


  /**
   * Node type for this list.  Each node holds a maximum
   * of nodeSize elements in an array.  Empty slots
   * are null.
   */
  private class Node
  {
    /**
     * Array of actual data elements.
     */
    // Unchecked warning unavoidable.
    public E[] data = (E[]) new Comparable[nodeSize];
    
    /**
     * Link to next node.
     */
    public Node next;
    
    /**
     * Link to previous node;
     */
    public Node previous;
    
    /**
     * Index of the next available offset in this node, also 
     * equal to the number of elements in this node.
     */
    public int count;

	/**
     * Adds an item to this node at the first available offset.
     * Precondition: count < nodeSize
     * @param item element to be added
     */
    void addItem(E item) 
    {
      if (count >= nodeSize)
      {
        return;
      }
      data[count++] = item;
      //useful for debugging
      //      System.out.println("Added " + item.toString() + " at index " + count + " to node "  + Arrays.toString(data));
    }
  
    /**
     * Adds an item to this node at the indicated offset, shifting
     * elements to the right as necessary.
     * 
     * Precondition: count < nodeSize
     * @param offset array index at which to put the new element
     * @param item element to be added
     */
    void addItem(int offset, E item)
    {
      if (count >= nodeSize)
      {
    	  return;
      }
      for (int i = count - 1; i >= offset; --i)
      {
        data[i + 1] = data[i];
      }
      ++count;
      data[offset] = item;
      //useful for debugging 
//      System.out.println("Added " + item.toString() + " at index " + offset + " to node: "  + Arrays.toString(data));
    }

    /**
     * Deletes an element from this node at the indicated offset, 
     * shifting elements left as necessary.
     * Precondition: 0 <= offset < count
     * @param offset
     */
    void removeItem(int offset)
    {
      E item = data[offset];
      for (int i = offset + 1; i < nodeSize; ++i)
      {
        data[i - 1] = data[i];
      }
      data[count - 1] = null;
      --count;
    }    
  }
 
  private class ChunkyListIterator implements ListIterator<E>
  {
	  //implements ListIterator methods nextIndex(), previousIndex(),hasPrevious(), and previous()
	  
	  //Implement the listIterator() method. 
	  //You should then be able to iterate forward and backward, 
	  //and you can check your iterator position using toStringInternal(iter). 
	  //The indexOf(Object obj) method of List should work now
	  
	  
	// constants you possibly use ...   
	// instance variables ...
	  
	  //node has count which is the # of elements in node '
	  
	  //private Node cursor = head; // this is incorrect, needs to be not a node but a int for 
								// for what element it is, use int count to find number
	  								// of elements in node
	  //private Node pending = null;
	  private int cursor=0;
	  private int pending;
	  private boolean canRemoveSet = false;
	// instance variables ... 
	  
    /**
     * Default constructor 
     */
    public ChunkyListIterator()
    {
    	// TODO 
    }

    /**
     * Constructor finds node at a given position.
     * @param pos
     */
    public ChunkyListIterator(int pos)
    {
    	// TODO 
    }

    @Override
    public boolean hasNext()
    {
    	if (head.next==null)//the collection if empty
    	{
    		return false;
    	}
    	else if(cursor<size)//
    	{
    		//compare curse to chunky list size variable
    		// this variable is the number of elements in chunky list
    		// if cursor is then size of chunky list, and pos, then its in it
    		
    		
    		return true;
    	}
    	else //it is the end element in chunkylist
    	{
    		return false;
    	}
    }

    @Override
    public E next()
    {
    	if (hasNext()==false)
    	{
    		throw new NoSuchElementException();
    	}
    	
    	Node temp;
    	temp=head;
    	int index=temp.count;
    	//look at node and see # of elements in them add those up, 
    	// if next node has count> #needed to get to count,
    	//	then its in that node, else(count==#needed) its in next node
    	// if count <#needed, keep tracck and move to next node
    	while (index<cursor)
    	{
    		index=index+temp.count;
    		temp=temp.next;
    	}
    	if (index==cursor)
    	{
    		pending =cursor;
        	cursor=cursor+1;
        	canRemoveSet=true;
    		return temp.data[nodeSize]; //last element in temp
    	}
    	else //index>cursor, element is in temp
    	{
    		int placementInNode = temp.count-(index-cursor);
    		pending =cursor;
        	cursor=cursor+1;
        	canRemoveSet=true;
    		return temp.data[placementInNode];
    	}
    }

    @Override
    public int nextIndex()
    {
    	if (hasNext()==false)
    	{
    		throw new NoSuchElementException();
    	}
    	pending =cursor;
    	cursor=cursor +1;
    	return cursor;
    }
    
    @Override
    public int previousIndex()
    {
    	if (hasPrevious()==false)
    	{
    		throw new NoSuchElementException();
    	}
    	pending =cursor;
    	cursor = cursor-1;
    	return cursor;
    }
    
    @Override
    public boolean hasPrevious()
    {
    	if (head.next==null)//the collection is empty
    	{
    		return false;
    	}
    	else if(cursor>0)
    	{
    		// cursor at 0 means that it is before first node
    		// cursor greater then 0 means their is always a previous element
    		return true;
    	}
    	else //it is the end element in chunkylist
    	{
    		return false;
    	}
    }
    
    @Override
    public E previous()
    {
    	if (hasPrevious()==false)
    	{
    		throw new NoSuchElementException();
    	}
    	
    	Node temp;
    	temp=head;
    	int index1=0;
    	int index2=temp.count;
    	//look at node and see # of elements in them add those up, 
    	// if next node has count> #needed to get to count,
    	//	then its in that node, else(count==#needed) its in next node
    	// if count <#needed, keep tracck and move to next node
    	// index 1 is to mark the end of the previous node
    	while (index1<cursor)
    	{
    		index1=index2;
    		index2=index1+temp.count;
    		temp=temp.next;
    	}
    	if(cursor == index1+1)//is at start of a node
    	{
    		pending =cursor;
        	cursor=cursor-1;
        	canRemoveSet=true;
    		return temp.data[0]; //first element in temp
    	}
    	else //cursor is between index 1 and 2 so in temp node
    	{
    		int placementInNode = temp.count-(index2-cursor);
    		pending =cursor;
        	cursor=cursor-1;
        	canRemoveSet=true;
    		return temp.data[placementInNode];
    	}
    }
    
    @Override
    public void remove()
    {
      	if(canRemoveSet==false)
    	{
    		throw new IllegalStateException();
    	}
    	else
    	{
    		ChunkyList.this.remove(cursor);
    		Node temp = new Node();
    		return; 
    	}
    }
    
   // public E remove(int pos)
    public void add() 
    {
    	ChunkyList.this.add(cursor, null);
    }
    // Other methods you may want to add or override that could possibly facilitate 
    // other operations, for instance, addition, access to the previous element, etc.
    // 
    // ...
    @SuppressWarnings("unchecked")
	public void set()
    {
    	
    	//keep track of whether to act on the 
    	//element before or after the cursor (and possibly throw an IllegalStateException),
    	//but the method is not complicated since it doesnt have to 
    	//change the structure of the list or reposition the cursor.
    	if(canRemoveSet==false)
    	{
    		throw new IllegalStateException();
    	}
    	else
    	{
    		Node temp;
    		temp=head;
    		int index=temp.count;
    		//look at node and see # of elements in them add those up, 
    		// if next node has count> #needed to get to count,
    		//	then its in that node, else(count==#needed) its in next node
    		// if count <#needed, keep tracck and move to next node
    		if (canRemoveSet==true)
    		{
    			while (index<pending)
    			{
    				index=index+temp.count;
    				temp=temp.next;
    			}
    		
    			if (index==pending)//set the last element in temp
    			{
    				temp.data[nodeSize]=(E) this;
    				return; 
    			}
    			else //index>pending, element is in temp
    			{
    			
    				int placementInNode = temp.count-(index-pending);
    				temp.data[placementInNode]=(E) this;
    				return;
    			}
    		}
    	}
    }

    // returns the node and offset for the given logical index
    private NodeInfo find ( int pos )
    {
    	Node temp;
    	temp=head;
    	int index=temp.count;
    	while (index<pos)
    	{
    		index=index+temp.count;
    		temp=temp.next;
    	}
    	if(pos == index)//last element in temp
    	{
    		return new NodeInfo(temp, temp.count);
    	}
    	else //pos is within the node
    	{
    		int offset=temp.count -(index-pos);
    		return new NodeInfo(temp, offset);
    	}
    }
    // 
    private class NodeInfo
    {
    	public Node node ;
    	public int count ;
    	public NodeInfo ( Node node , int offset )
    	{
    		this.node = node ;
    		this.count =offset ;
    	}
    
  }
  

  /**
   * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING order. 
   * @param arr   array storing elements from the list 
   * @param comp  comparator used in sorting 
   */
  private void insertionSort(E[] arr, Comparator<? super E> comp)
  {
	  int n=arr.length;
	  for(int i=0; i==n; i++)
	  {
		  E temp=arr[i];
		  int j=i-1;
		  while(j>-1 && arr[j] .compareTo(temp)<0)
		  {
			  arr[j+1]=arr[j];
			  j--;
		  }
		  arr[j+1]=temp;
	  }
  }
  
  /**
   * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a 
   * description of bubble sort please refer to Section 6.1 in the project description. 
   * You must use the compareTo() method from an implementation of the Comparable 
   * interface by the class E or ? super E. 
   * @param arr  array holding elements from the list
   */
  private void bubbleSort(E[] arr)
  {
	  //x.compareTo(y) < 0 = x < y
	  int n=arr.length;
	  E temp;
	  boolean notSorted=true;
	  while(notSorted ==true)
	  {
		notSorted=false;
		for(int i=0; i==n-1; i++)
	  	{
		  	if(arr[i].compareTo(arr[i+1])>0)
		  	{
			  //swap 
		  		temp=arr[i];
		  		arr[i]=arr[i+1];
		  		arr[i+1]=temp;
		  		notSorted=true;
		  	}
	  	}
	  }
	  
  }

@Override
public void add(E arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void set(E arg0) {
	// TODO Auto-generated method stub
	
}
}
}
