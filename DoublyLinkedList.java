/*  COS 161 USM, FALL 2018
 * Professor: Phoulady
 * Project:   Project 4, Doubly Linked Lisk
 * Date:	  11/29/2018
 * Author:	  Omar Gonzaga 
 */

public class DoublyLinkedList {
	
    private Node front;  // first element in the list
    private Node last;  // last element in the list
    private int size;  // number of elements in the list

   
    //Method to return size
    //Added by Omar - Method is called in any method that works with a list. Complete and tested.
    public int size() {
    	return size;
    }
    
    // post: constructs an empty list
    public DoublyLinkedList() {
        front = last = null;
      //Added by Omar
        size = 0;
    }

    // post: constructs a list with data in first element and "list" after that
    public DoublyLinkedList(int data, DoublyLinkedList list) {
    	front = new Node(data);
    	front.next = list.front;
        last = list.last;
        
        //Added by Omar
        size = list.size() + 1;
    }
    
    // post: returns the integer at the given index in the list
    public int get(int index) {
		checkIndex(index);
        return nodeAt(index).data;
    }

    // post: returns the position of the first occurrence of the given value (-1 if not found)
    public int indexOf(int value) {
        int index = 0;
        Node current = front;
        while (current !=  null) {
            if (current.data == value) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    // post: appends the given value to the end of the list
    public void add(int value) {
    	size = this.size();
    	if (last != null) {
	        last.next = new Node(value);
	        last = last.next;
	        size++;
    	}
    	else {
    		front = last = new Node(value);
    		//Added by Omar
    		size = 1;
    	}
    	
    }
    
    //Insert at start 
    //Added by Omar - Not required in the tasks but necessary for other methods to work. Complete and tested.
    public void insertAtStart(int value) {
    	if (front == null) {
    		front = new Node(value);
    		size = 1;
    	}
    	
    	else {
    		size = this.size();
    		Node current = front;
    		Node newFront = new Node(value); 
    		newFront.next = current;
    		current.previous = newFront;
    		front = newFront;
    		size++;
     	}
    }
    
    //Overloaded Add 
    //Added by Omar - Complete and tested
    public void add(int value , int index){
    	checkIndex(index);
        if (index == 1){
            insertAtStart(value);
            size = 1;
        }            
        else {
                size = this.size();
        		Node nextNew = nodeAt(index -1 );
            	Node prevNew = nodeAt(index - 2);
            	Node newMiddle = new Node(prevNew, value, nextNew); 
            	size++ ;
            	
            }
        }
    // AddALL method - started by Omar   
    //INCOMPLETE**************************************************************************************************
    public void addAll(int index, int [] values) {
    	checkIndex(index);
    	size = this.size();
    	
    	for (int i = 0; i < values.length; i++) {
    		
    		this.add(index, values[i] );
    		size++;
    			
    	}
    	
    }
    
    // post: removes value at the given index
    public void remove(int index) {
    	checkIndex(index);
        size = this.size();
    	if (index == 0) {
            front = front.next;
            
        } 
        else {
            Node current = nodeAt(index - 1);
            current.next = current.next.next;
        }
      //Added by Omar
        size--;
    }

    // post: returns a reference to the node at the given index
    private Node nodeAt(int index) {
    	checkIndex(index);
        Node current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
    
    // checks if index is out of bound and if it is throws IndexOutOfBoundsException
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    // post: creates a comma-separated, bracketed version of the list
    @Override
    public String toString() {
        if (front == null) {
            return "[]";
        } else {
            String result = "[" + front.data;
            Node current = front.next;
            while (current != null) {
                result += ", " + current.data;
                current = current.next;
            }
            result += "]";
            return result;
        }
    }
    
    //ADD Alternative
    //Added by Omar - Complete and tested
    public void addAlternative(int value) {
    	
    	if (front == null) {
    		insertAtStart(value);
    		size = 1;
       	}
    	
    	else {
    		int q = 0;
    		int i = 2;
    		Node current = front;
    		
    		while (current.next != null) {
    			add(value, i);
    			 i = i +2;
    			 current = current.next.next;
    			 size++;
    			 q++;
    			 
    		}
    			add(value);
    			q++;
    			size = q * 2;
    			
    	}
    }
    	
    //Added by Omar - Complete and tested
    //Reversed toString method
    public String toStringReverse() {
        //Doubly link list to reverse it
    	if (last == null) {
            return "[]";
        } 
    	else {
            int mySize = this.size();
            //System.out.println(size);
            nodeAt(1).previous = null;
            Node thisOne = nodeAt(2);
            int i = 2;
    		while (mySize >= 3) {
    			thisOne.previous = nodeAt(i - 1);
    			thisOne = thisOne.next;
    			mySize--;
    			i++;
    		}
                        	
            
    		String result = "[" + last.data;
            Node current = last.previous;
            while (current != null) {
                result += ", " + current.data;
                current = current.previous;
            }
            result += ", " + front.data;
            result += "]";
            return result;
        }
    }
    //Added by Omar - Complete and tested 
    public static boolean equals(DoublyLinkedList A, DoublyLinkedList B) {
        boolean result = false;
        
        Node currentA = A.front;
        Node currentB = B.front;
        
        while (currentA != null && currentB != null) {
                if (currentA.data != currentB.data) {
                	result = false;
                } 
                else {
                    currentA = currentA.next;
                    currentB = currentB.next;
                }
            }
            if (currentA == currentB)
                result = true;
        
        
        return result;
        
    }  
    
    // Node inner class containing the data, and next and previous references
    private static class Node {
        Node previous;
        int data;
        Node next;
        
        // constructor to create a Node with data and null previous and next references
        public Node(int data) {
            this.previous = this.next = null;
            this.data = data;
        }

        // constructor to add a new Node with data before another "next" node
        public Node(int data, Node next) {
            this.previous = null;
            this.data = data;
            this.next = next;
            if (next != null)
            	next.previous = this;
        }

        // constructor to initialize a new Node with data between its "previous" and "next" node
        //USED THIS FOR THE ADD OVERLOAD - Omar
        
        public Node(Node previous, int data, Node next) {
            this.previous = previous;
            this.data = data;
            this.next = next;
            if (previous != null)
            	previous.next = this;
            if (next != null)
            	next.previous = this;
        }
    }
}



