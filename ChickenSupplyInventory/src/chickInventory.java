import java.util.ArrayList;
import java.io.*;
import java.text.DecimalFormat;

//class that creates a stack for chicken inventory with common stack methods
public class chickInventory{
	
    private ArrayList<chicken> list;
    
    private ArrayList<sale> totSales;
    
    public chickInventory() {
        this.list = new ArrayList<>();
        totSales = new ArrayList<sale>();
    }

    public void push(chicken chi) {
        list.add(chi);
    }

    public chicken pop() {
    	if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return list.remove(list.size()-1);
    }

    public chicken peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return list.get(list.size()-1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
    
    //processes a supply order and adds chickens to stack
    public void execSup(double p, int amt) {
    	for(int i = 0; i < amt; i++) {
    		this.push(new chicken(p, 0));
    	}
    }
    
    //processes an individual order and adds it as a sale to the sale arraylist
    public void execOrd(order order1) {
    	chicken c;
    	sale newSale = new sale(order1.name, order1.amount, 0, 0.00);
    	
    	//adds up total chicken days in warehouse and total chicken price to sale
    	for(int i = 0; i < order1.amount; i++) {
    		c = this.pop();
    		newSale.price += c.price;
    		newSale.days += c.days;
    	}
		totSales.add(newSale);
    }
    
    //used to increase the chicken price for a certain number of days corresponding
    //to an order that cannot be placed yet
    public void incDays(int days) {
    	for(int i = 0; i < days; i++) {
    		for(int j = 0; j < this.size(); j++) {
    			this.list.get(j).price += 0.1;
    			this.list.get(j).days += 1;
    		}
    	}
    }
    
    //this writes the sales totals from each company to the output file
    public void getSales() throws IOException {
    	double d;
    	sale pSale;
    	int totChicks = 0;
    	double totPrice = 0.00;
    	double ppChick;
    	String outputFile = "output.txt";
	    FileWriter wr = new FileWriter(outputFile);
	    PrintWriter pw = new PrintWriter(wr);
	    pw.println("Total Price			Company Name			# of Chickens		Price Per Chick");
	    
	    DecimalFormat df = new DecimalFormat();
    	df.setMinimumFractionDigits(2);
    	df.setMaximumFractionDigits(2);
	    for(int i = 0; i < totSales.size(); i++) {
	    	pSale = totSales.get(i);
	    	d = pSale.price;
	    	pw.printf("%-18s %-30s %-20s %s\n", df.format(d), pSale.name, pSale.amount, df.format(pSale.price/pSale.amount));
	    }
	    pw.println("--------------------------------------------------------------------------------------");
	    pw.println();
	    pw.println("Summary Statistics:");
	    
	    int totAmount = 0;
	    int cDays = 0;
	    double avgDays = 0;
	    
	    for(int i = 0; i < totSales.size(); i++) {
	    	cDays += totSales.get(i).days;
	    	totAmount += totSales.get(i).amount;
	    }
	    for(int i = 0; i < totSales.size(); i++) {
	    	totChicks += totSales.get(i).amount;
	    }
	    for(int i = 0; i < totSales.size(); i++) {
	    	totPrice += totSales.get(i).price;
	    }
	    ppChick = (double) totPrice/totAmount;
	    avgDays =  (double)cDays/totAmount;
	   
	    pw.printf("Average Number of Days a Sold Chick Stayed in the Warehouse: %s", df.format(avgDays));
	    pw.println("Total Number of Completed Sales: "+totSales.size());
	    pw.println("Total number of chicks sold: "+totChicks);
	    pw.printf("Average Price of Each Chick Sold: %s", df.format(ppChick));
	    pw.println();
	    pw.printf("Gross sales total: %s", df.format(totPrice));
	    
	    pw.close();
    }
}

