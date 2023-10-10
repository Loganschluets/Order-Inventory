import java.util.ArrayList;

//class that creates a customer order queue with normal queue methods
public class customerOrders{
	
    private ArrayList<order> list;
    
    public customerOrders() {
        this.list = new ArrayList<>();
    }

    //adds an object to the start of the queue
    public void enqueue(order ord) {
    	order ord1 = new order("l", 1);
    	ord1 = ord;
        list.add(ord1);
    }
    
    //removes an object from the end of the queue
    public order dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        order ord = list.get(0);
        list.remove(0);
        return ord;
    }
    
    //same as a "peek" method
    public order look() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}