import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Main 
{
    public static BrowserStack Fstack = new BrowserStack(); // declaring and initializing Fstack using default BrowserStack constructor
    public static BrowserStack Bstack = new BrowserStack(); // declaring and initializing Bstack using default BrowserStack constructor
    public static BrowserQueue Hist = new BrowserQueue(); // declaring and initializing Hist using default BrowserQueue constructor
    public static BrowserNavigation Nav= new BrowserNavigation(Fstack, Bstack, Hist);// declaring and initializing Nav using parameterized BrowserNavigation constructor
    
    
    public static void main(String[] args)
    {
        int numchoice; // Declaring numchoice as an int

        do // do while loop exiting at numchoice == 8
        {
            System.out.println("Type the number that corresponds with your choice.");
            System.out.println("Menu: ");
            System.out.println("1- Visit a URL");
            System.out.println("2- Undo");
            System.out.println("3- Redo");
            System.out.println("4- View Search History");
            System.out.println("5- Clear Search History");
            System.out.println("6- Close Browser");
            System.out.println("7- Restore Last Session");
            System.out.println("8- Exit");

            Scanner choiceScanner = new Scanner(System.in);//scanner for user input
            numchoice = choiceScanner.nextInt(); // capturing user input into numchoice

            if(numchoice == 1)
            {
                System.out.println("Enter your url: ");
                Scanner websiteScanner = new Scanner(System.in);
                visitWebsite(websiteScanner.nextLine());
            }
            else if(numchoice == 2)
            {
                System.out.print(Nav.goBack());
                System.out.print("\n");
            }
            else if(numchoice == 3)
            {
                System.out.print(Nav.goForward());
                System.out.print("\n");
            }
            else if(numchoice == 4)
            {
                Nav.showHistory();
                System.out.print("\n");
                
            }
            else if(numchoice == 5)
            {
                Nav.clearHistory();
                System.out.println("Browsing history cleared.");
            }
            else if(numchoice == 6)
            {
                File saver = Nav.closeBrowser();
                System.out.println("Browsing session saved.");
                
            }
            else if(numchoice == 7)
            {
                Nav.restoreLastSession();
            }
            else
            {
                System.out.println("Exiting...");
                break;
            }
        } while(numchoice != 8); // exit case
        
        
    }
    
    //visitWebsite(String url) is of order O(n) since O(n) from enqueue dominates the O(1) from LLclear() and push

    public static void visitWebsite(String url)
    {
        Bstack.push(url);
        Fstack.LLclear();
        Hist.enqueue(url);
        
        System.out.print("Now at ");
        System.out.print(url);
        System.out.print("\n");
        System.out.print("\n");
    }
}

    
    
    
    
    
    
    
    