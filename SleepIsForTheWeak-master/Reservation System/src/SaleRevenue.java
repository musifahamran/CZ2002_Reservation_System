
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class SaleRevenue.
 */
public class SaleRevenue implements Serializable {
	

	
	/** The Constant serialVersionUID. 
	 *
	 * This tags the object to a unique serial for writing to serialized file
	 * @see java.io.Serializable
	 */
	private static final long serialVersionUID = 1L;
	
	/** The Constant payFilePath. */
	public static final String payFilePath = System.getProperty("user.dir") + "/src/SaleRevenueList";
	
	/** The total revenue. */
	//public static final String payFilePath = System.getProperty("user.dir") + "\\src\\SaleRevenueList";
	private double totalRevenue = 0.0;
	
	/** The items. */
	private ArrayList<OrderItem> items = new ArrayList<OrderItem>();
	
	/** The order id. */
	private int orderId;
	
	/** The date. */
	private LocalDateTime date;
 	
	 /**
	  * Instantiates a new sale revenue.
	  *
	  * @param date the date
	  * @param orderId the order id
	  * @param items the items
	  * @param totalRevenue the total revenue
	  */
	 public SaleRevenue(LocalDateTime date, int orderId, ArrayList<OrderItem> items, double totalRevenue) {
		this.setTotalRevenue(totalRevenue);
		this.setItems(items);
		this.setOrderId(orderId);
		this.setDate(date);
	}
	
	/**
	 * Gets the total revenue.
	 *
	 * @return the total revenue
	 */
	public double getTotalRevenue() {
		return totalRevenue;
	}
	
	/**
	 * Sets the total revenue.
	 *
	 * @param totalRevenue the new total revenue
	 */
	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	
	/**
	 * Adds the revenue.
	 *
	 * @param revenue the revenue
	 */
	public void addRevenue(double revenue) {
		this.setTotalRevenue(getTotalRevenue()+revenue);
	}
	
	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	public ArrayList<OrderItem> getItems() {
		return items;
	}
	
	/**
	 * Sets the items.
	 *
	 * @param items the new items
	 */
	public void setItems(ArrayList<OrderItem> items) {
		this.items = items;
	}
	
	/**
	 * Gets the order id.
	 *
	 * @return the order id
	 */
	public int getOrderId() {
		return orderId;
	}
	
	/**
	 * Sets the order id.
	 *
	 * @param orderId the new order id
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public LocalDateTime getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
