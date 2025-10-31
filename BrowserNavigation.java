import java.io.File; 
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class BrowserNavigation extends StackIterator 
{
    BrowserStack forward; //forward stack 
    BrowserStack backward; //backward stack
    BrowserQueue history; //history queue
    
    BrowserNavigation(BrowserStack F, BrowserStack B, BrowserQueue H) //parameterized constructor to input stacks and queues to work on
    {
        //setting up values of the stacks and queues
        forward = F;
        backward = B;
        history = H;
    }

    //goBack() is O(1) since no loops and just popping a single element from top of backward stack then pushing it in forward stack
    
    public String goBack()
    {
        if(!backward.isEmpty()) //checking the back stack is not empty
        {
           String x = backward.pop(); // popping element from top of back stack
           forward.push(x);//pushing the popped element from the backward stack to forward stack. It enables redo after undo
           return "Now at " + x; //returning the popped element
        }
        else
        {
            return "No previous page available"; // accounting for empty stack
        }
    }
    
    //goForward() is O(1) since no loops and just popping a single element from top of forward stack then pushing it in backward stack
    public String goForward()
    {
       if(!forward.isEmpty()) //checking the forward stack is not empty
       {
           String y = forward.pop(); // popping element from top of forward stack
           backward.push(y); //pushing the popped element from the forward stack to backward stack. It enables undo after redo
           return "Now at " + y; 
       }
       else
       {
           return "No forward page available";  // accounting for empty stack
       }
    }
    
    //showHistory() is O(n) since for loop over hsitory queue's elements and printing them 1 by 1
    public void showHistory()
    {
        if(history.front != -1) //checking that queue isn't empty
        {
            int index;
            for(int i = 0; i < history.getSize(); i++) //for loop over history queue
            {
                index = (history.front + i) % history.capacity; // incrementing front of queue by i then mod history.capacity to enable wrap arounds
                System.out.print(history.arr[index]); // printing the element at the index
                System.out.print("\n");
            }
        }
        else
        {
            System.out.println("No browsing history available"); //accounting for empty queue
        }
    }

    // clearing using Arrayclear and ArraydoClear() is O(1) since we jsut make front and back indices be -1 and creating(+assigning) new empty array
    public boolean clearHistory()
    {
        history.Arrayclear(); 
        return true;
    }

    //HistorySaver() is O(n) since for loop over history queue elements
    public String HistorySaver()
    {
        StringBuilder historyData = new StringBuilder(); //string builder to store multiple urls (the search history)
        int index;
        for(int i = 0; i < history.getSize(); i++)
        {
            index = (history.front + i) % history.capacity; // incrementing front of queue by i then mod history.capacity to enable wrap arounds
            historyData.append(history.arr[index]).append("\n"); // storing the urls in a string builder with newlines to have a list of urls to store in file later on
        }
        return historyData.toString(); //returning converted string 
    }
    
    // closeBrowser() is of order O(n) since it contains forwardIterator() and backwardIterator() with single while loops and HistorySaver() with for loop
    public File closeBrowser()
    {
        String fileName = "session_data.txt"; // naming file
        File file = new File(fileName); // new file object
        
        //try block to automatically close FileWriter
        try
        {
            FileWriter writer = new FileWriter(file);
            writer.write(forwardIterator()); // writing the contents of the forward stack in a file
            writer.write("End"+"\n"); // end signifies end of forward stack
            writer.write(backwardIterator());// writing the contents of the backward stack in a file
            writer.write("End"+"\n");// end signifies end of backward stack
            writer.write(HistorySaver());// writing the contents of the history queue in a file
            writer.write("End"+"\n"); // end signifies end of history queue
            writer.close();
            System.out.println("Message written to file successfully.");
        }
        catch(IOException e)
        {
            System.out.println("An error occurred while writing to the file.");
        }
        return file; //returning the file storing the stacks and queue
    }

    // restoreLastSession() is of order O(n) since it contains single while loops
    public void restoreLastSession() 
    {
        File sessionFile = closeBrowser();  // Store the file object first

        if (!sessionFile.exists()) //if file doesn't exist
        {
            System.out.println("No previous session found");
            return;
        }

        //try block to automatically close BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(sessionFile))) // BufferedReader to be able to read file line by line
        {
            String line; // string to store lines
            BrowserStack tempStack = new BrowserStack(); // initialising temporary stack to enable smooth transition from file to temp and temp to original
            BrowserQueue tempQueue = new BrowserQueue(); // initialising temporary queue to enable smooth transition from file to temp and temp to original

            // Reading the forward stack elements in the file line by line and pushing them in temp stack until we hit the first end 
            while ((line = br.readLine()) != null && !line.equals("End")) 
            {
                tempStack.push(line);
            }   

            // Pushing the popped tempStack elements in forward stack
            while (!tempStack.isEmpty()) 
            {
                String m = tempStack.pop();
                forward.push(m);
            }

            // Reading the backward stack elements line by line and pushing them in temp stack until we hit the second end
            while ((line = br.readLine()) != null && !line.equals("End")) 
            {
                tempStack.push(line);
            }

            // Pushing the popped tempStack elements in backward stack
            while (!tempStack.isEmpty()) 
            {
                String n = tempStack.pop();
                backward.push(n);
            }

            // Reading the history queue elements line by line and pushing them in history queue until we hit the third end
            while ((line = br.readLine()) != null && !line.equals("End")) 
            {
                history.enqueue(line);
            }
            
            System.out.println("Last session restored successfully.");
        } 
        catch (IOException e) 
        {
            System.out.println("Error reading session file.");
        }
    }

    
}
