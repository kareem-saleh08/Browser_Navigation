
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;


public class StackIterator
{
   
    BrowserStack forward; //forward stack
    BrowserStack backward; //backward stack
    Iterator<String> fiterator; //front stack Iterator
    Iterator<String> biterator;//back stack Iterator
       
    StackIterator() //default StackIterator constructor
    {
       forward = new BrowserStack(); //stack default constructor
       backward = new BrowserStack(); //stack default constructor
       fiterator = forward.iterator(); //setting up forward iterator
       biterator = backward.iterator(); //setting up backward iterator
    }
    
    //forwardIterator() is of order O(n) since while loop goes over each of the elements in the forward stack
    String forwardIterator()
    {
        StringBuilder forwardData = new StringBuilder(); // string builder to append the popped stack elements to put in file later
        while(fiterator.hasNext()) // while loop to check with iterator for further element in forward stack
        {
            forwardData.append(fiterator.next()).append("\n"); // appending the next() element and new line to have lines each storing a website from the forward stack
            
        }
        
        return forwardData.toString(); //returns the converted String builder(converted to string)
    }
    
    //backwardIterator() is of order O(n) since while loop goes over each of the elements in the forward stack
    String backwardIterator()
    {
        StringBuilder backwardData = new StringBuilder(); // string builder to append the popped stack elements to put in file later
        while(biterator.hasNext()) // while loop to check with iterator for further element in backward stack
        {
            backwardData.append(biterator.next()).append("\n"); // appending the next() element and new line to have lines each storing a website from the backward stack
            
        }
        
        return backwardData.toString(); //returns the converted String builder(converted to string)
    }
    
    
}