import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class Order.
 */
public class Order {
	
	/** The time stamp. */
	private LocalDateTime timeStamp;
	
	/** The  orderID reference to a Order object*/
	private int orderID;
	
	/** The  employeeID reference to a staff object*/
	private int employeeID;
	
	/** The  tableID reference to a table object*/
	private int tableID;
	
	/** The total price of all items. */
	private double totalPrice=0.00;
	
	/** The added promo items price. */
	private double promoPrice = 0.0;
	
	/** The ordered items. */
	private ArrayList<OrderItem> orderedItems = new ArrayList<OrderItem>();
	
	/**
	 * Parameterized Constructor
	 * Instantiates a new order.
	 *
	 * OrderID is an internal variable set by a random generator up to 6 digits.
	 * @param timeStamp the time stamp
	 * @param tableID the table ID
	 * @param employeeID the employee ID
	 */
	public Order(LocalDateTime timeStamp,int tableID, int employeeID) {
		Random r = new Random();
		this.setOrderID(r.nextInt(1000000));// order ID is a 6-digit number.
		this.setTimeStamp(timeStamp);
		this.setTableID(tableID);
		this.setEmployeeID(employeeID);
	
	}
	

	/**
	 * Gets the time stamp.
	 *
	 * @return the time stamp
	 */
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Sets the time stamp.
	 *
	 * @param timeStamp the new time stamp
	 */
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Gets the employee ID.
	 *
	 * @return the employee ID
	 */
	public int getEmployeeID() {
		return employeeID;
	}

	/**
	 * Sets the employee ID.
	 *
	 * @param employeeID the new employee ID
	 */
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * Gets the order ID.
	 *
	 * @return the order ID
	 */
	public int getOrderID() {
		return orderID;
	}

	/**
	 * Sets the order ID.
	 *
	 * @param orderID the new order ID
	 */
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	/**
	 * Gets the total price.
	 *
	 * @return the total price
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Sets the total price.
	 */
	public void setTotalPrice() {
		double price = 0.00;
		for(int i=0;i<orderedItems.size();i++)
		{
			if ( orderedItems.get(i).isPromotionItem() == false) {
				price = (orderedItems.get(i).getQuantity() * orderedItems.get(i).getMenuObj().getPrice()) + price;
			}
			
		}
		this.totalPrice = price + promoPrice;
	}
	
	/**
	 * Adds the promo price.
	 * 
	 * @param price the price
	 */
	public void addPromoPrice(double price) {
		this.promoPrice +=price;
		return;
	}
	
	/**
	 * Removes the promo price.
	 *
	 * @param price the price
	 */
	public void removePromoPrice(double price) {
		this.promoPrice -= price;
		return;
	}

	/**
	 * Gets the orderedItems ArrayList
	 *
	 * @return the ordered items
	 */
	public ArrayList<OrderItem> getOrderedItems() {
		return orderedItems;
	}

	/**
	 * Sets the orderedItems ArrayList
	 *
	 * @param orderedItems the new ordered items
	 */
	public void setOrderedItems(ArrayList<OrderItem> orderedItems) {
		this.orderedItems = orderedItems;
	}

	/**
	 * Gets the table ID.
	 *
	 * @return the table ID
	 */
	public int getTableID() {
		return tableID;
	}

	/**
	 * Sets the table ID.
	 *
	 * @param tableID the new table ID
	 */
	public void setTableID(int tableID) {
		this.tableID = tableID;
	}
	
	/**
	 * Adds the ordered item.
	 *
	 * @param orderItem the order item
	 */
	public void addOrderedItem(OrderItem orderItem) {
		orderedItems.add(orderItem);
		totalPrice += (orderItem.getQuantity())*(orderItem.getMenuObj().getPrice());
		
	}
	
	/**
	 * Method displays a list of the orderItem objects' variables in string format.
	 * When the orderedItems ArrayList has no orderItems it will display a "The order is empty" text
	 * <br>
	 * This is an example of a viewOrder<br>
	 * viewOrder for a order with all menu items<br>
	 * <br>
	 * Item Name : lamb shanks 	Qty: 1<br>
	 * Item Name : chicken dinner 	Qty: 1<br>
	 * <br>
	 * <br>
	 * viewOrder for a order with promotion items<br>
	 * <br>
	 * Item Name : Promotion set : 43898 	Qty: 1<br>
	 * Item Name : chicken dinner 	Qty: 1<br>
	 * 
	 * 
	 */
	public void viewOrder() {
		if (orderedItems.size()==0) {
			System.out.println("The order is empty");
		}
		else {
			OrderItem temp;
			for (int i = 0; i<orderedItems.size();i++) {
				temp = orderedItems.get(i);
				System.out.println("ID : "+temp.getMenuObj().getItemID()+ "   " +temp.getQuantity()+ "   " 
				+ temp.getMenuObj().getMenuName() + "   "+ temp.getQuantity()*temp.getMenuObj().getPrice());
			}
		}
	}
	
	/**
	 * Removes a ordered item from the orderedItems ArrayList
	 * removeOrderedItem for a order with promotion items
	 * 
	 * Order example :<br>
	 * Item Name : Promotion set : 43898 	Qty: 1<br>
	 * Item Name : chicken dinner 	Qty: 1<br>
	 * 
	 * Order example deleted chicken dinner :<br>
	 * Item Name : Promotion set : 43898 	Qty: 1<br>
	 */
	public void removeOrderedItem() {
		Scanner sc = new Scanner(System.in);
		int choice;
		OrderItem temp;
		viewOrder();
		
		if (orderedItems.size()==0) {
			sc.close();
			return;
		}
		else {
		System.out.println(" Enter the ID of the Item you want to remove:");
		choice = sc.nextInt();
		
			for (int i = 0; i<=orderedItems.size();i++) {
				temp = orderedItems.get(i);
				if (choice == temp.getMenuObj().getItemID()) {
					orderedItems.remove(i);
					System.out.println("Item has been removed from the order succesfully!");
				}
			}
		}
		sc.close();
	}
	
	/**
	 * Prints the detailed bill for an order.
	 * 
	 * Example of printBill for order:
	 * 
	 * This. Restaurant <br>
	 * Order no: 	437810 <br>
	 * Staff:shaun 	Date:18/04/2019 <br>
	 * Table:207 	Time:02:52:03 <br>
	 * Qty: 	Item: 		Price: <br>
	 * 1 	Promotion set : 43898 	25.0<br>
	 * <br>
	 * 1 	chicken dinner 	9.9 <br>
	 * <p> ------------------------------ <p>
	 *		Sub total: 	34.90<br>
	 *		Taxes at 7% :    2.44 <br>
	 *		Total: 		37.34<br>
	 *		Change given is : 0.00 <br>
	 * @param table the table
	 * @param staff the staff
	 */
	public void printBill(Table table, Staff staff) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat time = new SimpleDateFormat("HH:mm:ss");
		OrderItem temp;
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		System.out.println("\nThis. Restaurant");
		System.out.printf("Order no: \t%s%n",this.orderID );
		System.out.printf("Staff:%s \tDate:%s%n",staff.getName(),dateFormat.format(date));
		System.out.printf("Table:%s \tTime:%s%n",table.getTableID(),time.format(date));
		//System.out.println("Staff: "+staff.getName() + "\tDate: " + dateFormat.format(date));
		//System.out.println("Table: "+table.getTableID() + "\tTime: "+time.format(date));
		System.out.printf("Qty: \tItem: \t\tPrice:");
		for ( int i=0; i<orderedItems.size();i++) {
			temp = orderedItems.get(i);
			System.out.printf("\n%s \t%s \t%s%n",temp.getQuantity(),temp.getMenuObj().getMenuName(),(temp.getQuantity()*temp.getMenuObj().getPrice()));
			
		}
		System.out.println("------------------------------");//changed it so can see sub total along
		System.out.println("\tSub total: \t" + String.format("%.2f", this.getTotalPrice()));
		
	}
	
	/**
	 * Checkouts an order. Calculation of total based on GST and total amount.
	 * creates a payment object based off the prices of each item in the orderedItem ArrayList in Order object,
	 * @param timeStamp the time stamp of checkout called
	 * @param amountPaid the amount paid by customer
	 * @param isCash the payment method by customer isCash == true (refers to paid by Cash) isCash==false (paid by card)
	 * @param subtotal the subtotal of the all the items within the Order object
	 * @return the payment
	 */
	public Payment checkout(LocalDateTime timeStamp, double amountPaid, boolean isCash, double subtotal) {
		Payment payment  = new Payment(timeStamp, subtotal, amountPaid, isCash);
		payment.setTotal(subtotal*(1+payment.getGST()));
	//	System.out.println("Sub total: \t" + String.format("%.2f",subtotal));
		System.out.println("\tTaxes at "+String.format("%.0f",(payment.getGST()*100))+"% :    "+ String.format("%.2f", (subtotal*0.07)));
		System.out.println("\tTotal: \t\t"+ String.format("%.2f",payment.getTotal()));
		return payment;
	}


	/**
	 * Gets the promo price.
	 *
	 * @return the promo price
	 */
	public double getPromoPrice() {
		return promoPrice;
	}


	/**
	 * Sets the promo price.
	 *
	 * @param promoPrice the new promo price
	 */
	public void setPromoPrice(double promoPrice) {
		this.promoPrice = promoPrice;
	}
}
