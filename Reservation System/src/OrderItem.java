import java.io.Serializable;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderItem.
 */
public class OrderItem implements Serializable{
	
	/** The orderID reference to a Order object */
	private int orderID;
	
	/** The quantity ordered*/
	private int quantity;
	
	/** The id */
	private int id;
	
	/** The promoID reference to a Promotion object */
	private int promoID;
	
	/** The is promotion item. */
	//default serialVersion id
	private boolean isPromotionItem = false;
    
    /** The Constant serialVersionUID. 
     * This tags the object to a unique serial for writing to serialized file
     * @see java.io.Serializable
     */
    private static final long serialVersionUID = 3L;
	
	/** Holds the Item object in MenuList. */
	private Item menuObj;
	
	/**
	 * Default constructor
	 * Instantiates a new order item.
	 */
	public OrderItem() {
		
	}
	
	/**
	 * Parameterized constructor
	 * Instantiates a new order item.
	 *
	 * @param menuObj the Item object to hold a reference to
	 * @param quantity the quantity of a unique Item object
	 * @param orderID the reference to an Order obj based on its orderID
	 */
	public OrderItem(Item menuObj, int quantity, int orderID) {
		Random r = new Random();
		this.setId(r.nextInt(500));
		this.setMenuObj(menuObj);
		this.setOrderID(orderID);
		this.setQuantity(quantity);
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
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Gets the menu obj.
	 *
	 * @return the menu obj
	 */
	public Item getMenuObj() {
		return menuObj;
	}
	
	/**
	 * Sets the menu obj.
	 *
	 * @param menuObj the new menu obj
	 */
	public void setMenuObj(Item menuObj) {
		this.menuObj = menuObj;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Checks if the OrderItem object is a promotion item.
	 *
	 * @return true, if is promotion item
	 */
	public boolean isPromotionItem() {
		return isPromotionItem;
	}
	
	/**
	 * Sets the promotion item.
	 * When a Promotion is added, it will be wrapped into a orderItem,
	 * This method separates the calculation of a regular Item object from MenuList to the Promotion
	 * @param isPromotionItem the new promotion item
	 */
	public void setPromotionItem(boolean isPromotionItem) {
		this.isPromotionItem = isPromotionItem;
	}
	
	/**
	 * Gets the promo ID.
	 *
	 * @return the promo ID
	 */
	public int getPromoID() {
		return promoID;
	}
	
	/**
	 * Sets the promo ID.
	 *
	 * @param promoID the new promo ID
	 */
	public void setPromoID(int promoID) {
		this.promoID = promoID;
	}

}
