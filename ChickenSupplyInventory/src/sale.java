/*sale object with name of company who purchased, amount of chickens, total days of all chickens in sale
 * and total price of all chickens in sale
 */
public class sale{
	String name;
	int amount;
	int days;
	double price;
	sale(String name, int amount, int days, double price){
		this.name = name;
		this.amount = amount;
		this.days = days;
		this.price = price;
	}
}