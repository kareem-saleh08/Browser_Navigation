import java.util.Iterator;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class BrowserLinkedList implements Iterable<String> //LL class implementing Iterable<String> interface
{
    public Node first; //first node to first element of LL
    public Node last; //last node to last element of LL
    private int modCount = 0;//incrementing number of modifications to the LL

    public BrowserLinkedList() //default constructor setting the nodes to null (empty LL)
    {
        first = null;
        last = null;
    }

    public boolean isEmpty() //checking if empty list by examining nullity of first 
    {
        return first == null;
    }
    
    // insert is O(1) since we have last node and we are adding to the end by just incrementing last and placing element

    public void insert(String a) // method to insert a string in LL
    {
        Node newNode = new Node(a); //Declaring and initializing a new node with parameterized constructor
        if (this.first == null) //if empty list
        {
            //setting first and last to the newNode
            this.first = newNode; 
            this.last = newNode;
        } 
        else 
        {
            this.last.next = newNode; //making last node's next pointer point to the newNode
            newNode.prev = this.last; // making the newNode prev pointer point to the last node
            this.last = newNode; //making newNode the last node  in LL
        }
        modCount++; // Accounting for modification in LL
    }
    
    // remove is O(1) since we have last node and we are removing from the end by just decrementing last, removing element after storing it to return
    public String remove() 
    {
        String rem=""; //empty string
        if (this.first == null) //if empty list
        {
            throw new EmptyStackException(); //No elements to remove from empty list
        } 
        else if (this.first == this.last)  //else if only one node in list
        {
            rem = this.last.data;
            this.first = null;
            this.last = null;
        } 
        else 
        {
            rem = this.last.data; //storing the removed string
            this.last = this.last.prev; //making the previous node in LL the last node 
            this.last.next = null; //making next pointer point to null since it is the last element in the list
        }
        modCount++; // Increment modification count
        return rem; //returning the removed string
    }

    public Iterator<String> iterator() // method for iterator that returns an object of type Iterator<String> 
    {
        return new LLiterator(); // returning the LL constructor
    }

    public class LLiterator implements Iterator<String> //LLiterator class that implements the Iterator<String> interface
    {
        private Node current = first; //making iterator start at the first node
        private final int expectedModCount = modCount;//assigning the current modCount to a final variable that cannot be changed

        public boolean hasNext()  // checking if there are further elements in list
        {
            return current != null;//checking if current node is null
        }

        public String next() //advances the element and stands before the next element but stores the element it just passed
        {
            if (modCount != expectedModCount) //making sure that there were no further modifications done 
            {
                throw new ConcurrentModificationException(); // throwing exception and halting program
            }
            if (!hasNext()) // if no further elements
            {
                throw new NoSuchElementException();
            }
            String nextItem = current.data; // stroing the current data in String var
            current = current.next; //advanicing forward
            return nextItem;//returning the item that the iterator just passed
        }

        public void remove() //we already have a remove method so I dont need this one
        {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }
    
    
    public void LLclear() //public clear method
    {
        LLdoClear();//calls another clear
    }
    
    // LLdoClear() is O(1) since we jsut make first and last nodes point to null
    
    private void LLdoClear() //private in order not to allow external tampering with the LL
    {
        first = null;
        last = null;
        //setting both first and last to null indicating empty LL
        modCount++; // adding 1 to modCount as clear is a modification of the LL
    }
}

class Node 
{
    String data; //string member var storing data
    Node prev;//node pointing to previous element
    Node next;//node pointing to next element
 
    Node(String d) //parameterized constructor with data 
    {
        data = d;//assigning some value to data member var
        prev = null;
        next = null;
        
        //setting nodes to null
    }
}
