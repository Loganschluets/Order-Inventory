
import java.util.*;
import java.io.*;



class Main {
	
	/*this is the main method for a function that takes an input file of customer and supply
	 * orders that then add order to queue or chickens to stack. Only the top order of the queue can be processed.
	 * The results of the completed orders as well as extra statistics are printed to an output file within the project 
	 */
	public static void main(String[] args) throws IOException {
		
		//all temporary varbiables to iterate through data, including a line parse string array
		order tempOrd = new order("l", 1);
		String line = "";
		String[] lineParse = new String[4];
		
		//queue used to store orders
		customerOrders queue= new customerOrders();
		
		//stack used to store chickens
		chickInventory stack = new chickInventory();
		
	    //creates scanner to read from input file
	    File input = new File("input file with 50 records.txt");
	    Scanner scan1 = new Scanner(input);
	    
	    //main loop that iterates through the lines in the input file
	    while(scan1.hasNextLine()) {
	    	
	    	//scans line and parses it into an array of strings separated by commas and spaces
	    	line = scan1.nextLine();
	    	lineParse = line.split(", ", 4);
	    	stack.incDays(Integer.parseInt(lineParse[3]));
	    	
	    	//if the current line is a supply, then add chickens to the stack
	    	if(lineParse[0].equals("S")) {
	    		double p = Double.parseDouble(lineParse[2]);
	    		int amt = Integer.parseInt(lineParse[1]);
	    		stack.execSup(p, amt);
	    	}
	    	
	    	//if the current line is an order then add it to the queue
	    	if(lineParse[0].equals("O")) {
	    		//this statement executes if the line read is a customer order
	    		tempOrd.name = lineParse[1];
	    		tempOrd.amount = Integer.parseInt(lineParse[2]);
	    		queue.enqueue(new order(tempOrd.name, tempOrd.amount));
	    	}
	    	
	    	//executes as many orders in the queue while the stack has enough chickens
	    	while(queue.size()!=0&&queue.look().amount<=stack.size()) {
	    		tempOrd = queue.dequeue();
	    		stack.execOrd(tempOrd);
	    	}
	    	
	    }
	    stack.getSales();
	}
}
