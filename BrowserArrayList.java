
public class BrowserArrayList //Arraylist class
{
    int capacity = 10;//capacity variable indicating the max number of element that can be stored
    
    String [] arr = new String[capacity]; // declaring an array member with capacity
    
    private int size = 0; // number of elements currently in the array but private to avoud tampering
    
    public int front = -1; //declaration of the element at front of the queue
    
    public int back = -1; //declaration of the element at back of the queue
    
    public BrowserArrayList() // Default constructor
    {
        capacity = 10;
    
        arr = new String[capacity];
    
        size = 0;
        
        front = -1;
    
        back = -1;
    }
    
    public int getSize() //returns the size of array and aims to access the private size member
    {
        return size;
    }
    
    boolean isEmpty() // checking whether the array is empty
    {
        if(front==-1) //if front hasnt been changed, indicating that no element was added
        {
            return true;
        }
        
        else
        {
          return false;  
        }
        
    }
    
    boolean isFullNorm() //making sure if the array is full without wrapping around
    {
        if(front==0 && back == capacity-1) // if front of queue at 0 and back at the capacity-1
        {
            return true;
        }
        
        
        return false;
    }
    
    boolean isFullLoop() //making sure if the array is full by wrapping around
    {
       if(((back+1) % capacity) == front) // if back is at the elemnt right before front. Using mod to avoid array out of bounds and allow circular arraylist
       {
         return true;
       }
       
       else
       {
          return false; 
       }
        
        
    }
    
    //add is O(n) in the case of a full array since we have multiple single for loops allowing us to resize and copy elements and multiplication constant is ignored
    //add is O(1) in the case of a none-full array since we just put the element at the desired index
    //Therefore, add is of order O(n) since O(n) dominates the O(1)
    
    public void add (String a) // adding a string to arraylsit
    {
        
        if(isFullNorm() || isFullLoop()) //checking if arraylist is full (whether wrapped around or just full for beginning to end front at 0 and back at capacity-1)
        {
            this.capacity = capacity*2 +1; //rezising the array
            String [] newarr = new String [capacity]; // creating new array with new capacity
            
            if(front<= back) // checking the front is before back
            {
                for(int i=0; i<size;i++) //loop around original array
                {
                    newarr[i] = this.arr[i];//copying array elements to the new array
                }
                
                front=0; //resetting front
                
                back = size-1; //resetting size based on number of element
                
                this.arr = newarr; // making original array become the resized array
                
                this.arr[++back] = a; // incrementing back and then adding an element at the new back index
            
                
                
                this.size++; // incrementing due to new element being added
            }
            
            else // case where array has wrapped around and back is before front
            {
                for (int i = 0, j = front; j < capacity; i++, j++) // for loop with 2 indices. i looping over new resized array and j looping arround the original array
                {
                    //this for loop copies elements from front to the end into the new resized array
                    newarr[i] = this.arr[j];
                }


                for (int i = size - (capacity - front), j = 0; j <= back; i++, j++) // for loop with 2 indices. i looping over new resized array and j looping arround the original array
                {
                    //this for loop copies elements from beginning of the array to the back index into the new resized array
                    newarr[i] = this.arr[j];
                }
                
                front=0;//resetting front
                back = size-1;//resetting size based on number of element
                this.arr = newarr;// making original array become the resized array
                this.arr[++back] = a;// incrementing back and then adding an element at the new back index
            
                
                
                
              
                this.size++; //increment size after incrementing elements
                
                
            }
            
            
        }
        
        else
        {
            if(front == -1) //checking if arraylist is empty
            {
                front = 0;
            }
            
            back = (back + 1) % capacity; // back is incremented then mod capacity aims to avoid going out of bounds and enables wrapping arround
            
            this.arr[back] = a; //adding element at the back index
            
            this.size++; //incrementing size
            
        }
        
        
        
            
    }
    
    //remove is O(1) since removing at end requires no for loops 
    
    public String delete ()
    {
       if(front==-1) //empty arraylist
       {
           return "No browsing history available"; //error message since we couldnt remove element from empty array
       }
       
       else if(front==back) //if 1 element arraylist
       {
          String del = arr[front]; //storing the element going to be deleted
          front=-1;
          back=-1;
          //front and back set to default to mean empty array
          this.size=0; //size to 0
          
          return del + "\n"; //returning the deleted element
       }
       
       else
       {
           String del = arr[front]; //storing the element going to be deleted
           front = (front + 1) % capacity; // front is incremented then mod capacity aims to avoid going out of bounds and enables wrapping arround
            
           this.size--;//decrementing size
           return del + "\n";//returning the deleted element
       }
    }
    
    public void Arrayclear() //public Arrayclear method to access private doClear method
    {
        ArraydoClear();
    }
    
    // ArraydoClear() is O(1) since we jsut make front and back indices be -1 and creating(+assigning) new empty array
    
    private void ArraydoClear() //avoids tampeing with arraylist
    {
        arr = new String [capacity]; //creating an empty array and assigning it to our original variable that stores array
        front=-1;
        back=-1;
        //front and back set to default to mean empty array
        size=0;//size to 0
    }
    
   
    
}

