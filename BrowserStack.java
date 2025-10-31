
import java.util.EmptyStackException; //importing to allow throwing new EmptyStackException() 

public class BrowserStack extends BrowserLinkedList //child class of LL to employ it in stack
{
    public String push(String url) // pushing the url and returning it to use it in "Now at [website name]" in the main
    {
        this.insert(url); // push is using LL insert which is O(1) since we have last node and we are adding to the top of stack by just incrementing last and placing element
        
        return url; //returning the url
    }
    
    public String pop()
    {
        return this.remove();//pop is using LL remove which is O(1) since we have last node and we are removing from the top of the stack by just decrementing last, removing element after storing it to return
        
    }
    
    public String peek() //looking at element at top of stack without popping it
    {
        
        if(this.first==null) //checking if stack is empty
        {
            throw new EmptyStackException();
        }
        
        else
        {
            return last.data; // returning data in the node pointed to by last node (top of the stack)
        }
    }
    
}