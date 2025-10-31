


public class BrowserQueue extends BrowserArrayList //child class of ArrayList to employ it in Queue
{
    //enqueue uses the Arraylist add which is O(n) in the case of a full array since we have multiple single for loops allowing us to resize and copy elements and multiplication constant is ignored
    //enqueue uses the Arraylist add which is O(1) in the case of a none-full array since we just put the element at the desired index
    //Therefore, enqueue uses the Arraylist add which is of order O(n) since O(n) dominates the O(1)
    
    public String enqueue(String url) // enqueueing the url and returning it to use it in search history in the main
    {
        this.add(url);//enqueue uses the Arraylist add which is of order O(n) since O(n) dominates the O(1) to implement circular queue
        
        return url; //returning the url
    }
    
    public String dequeue()
    {
        if(getSize()!=0) // checking if queue is not empty
        {
            return this.delete(); //dequeue is using ArrayList's remove which is O(1) since we have front index and we are removing from the front of the queue by just incrementing front, removing element after storing it to return
        }
        
        return "No Browsing History Available"; // error if empty arraylist
    }
    
    public String get(int index) // returning ArrayList element at given index
    {
        return arr[index];
    }
    
    public void set(int index, String s) // setting ArrayList element at given index to a certain value
    {
        arr[index] = s;
    }
    
    
    
}