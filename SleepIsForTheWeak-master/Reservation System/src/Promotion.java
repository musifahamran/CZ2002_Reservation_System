import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Promotion.
 */
public class Promotion implements Serializable{
		
		/** The price. */
		private double price;
		
		/** The promo ID. */
		private int promoID;
		
		/** The promo package. */
		private ArrayList<OrderItem> promoPackage = new ArrayList<OrderItem>();
		
		/** The Constant serialVersionUID. 
		* This tags the object to a unique serial for writing to serialized file
		* @see java.io.Serializable
		*/
		//default serialVersion id
	    private static final long serialVersionUID = 2L;
	
	/**
	 * Default Constructor
	 * Instantiates a new promotion.
	 * promoID is handled by a random number generator
	 */
	public Promotion() {
		Random r = new Random();
		this.setPromoID((r.nextInt(100000)));
		
	}
	
	/**
	 * Adds the menu item.
	 *
	 * @param menuItem the menu item
	 */
	public void addMenuItem(OrderItem menuItem) {
		getPromoPackage().add(menuItem);
		
	}
	
	/**
	 * Printpromo package.
	 */
	public void printpromoPackage() {
		if (getPromoPackage().size()== 0) {
			System.out.println("The Package is empty.");
			return;
		}
		else {
			OrderItem temp;
			System.out.println("ID: "+ this.getPromoID());
			for(int i = 0; i< getPromoPackage().size();i++) {
				temp = getPromoPackage().get(i);
				//Changed to temp.getMenuObj().getItemID() since makes sence for item id to be the same as menu object
				System.out.println("Item ID:"+ temp.getId()+" Item Name: "+ temp.getMenuObj().getMenuName() + " Quantity :"+ temp.getQuantity());
			}
		}
		
	}
	
	/**
	 * Removes the menu itemby ID.
	 *
	 * @param id the id
	 */
	public void removeMenuItembyID(int id) {
		int flag = 0;
		for (int i = 0; i<getPromoPackage().size();i++) {
			if ( getPromoPackage().get(i).getId() == id) {
				getPromoPackage().remove(i);
				System.out.println("Item removed successfully");
				flag = 1;
				
			}
		}
		if ( flag  ==0) {
			System.out.print("Enter a valid ID\n");
		}
		return;
		}
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(double price) {
		this.price = price;
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
	
	/**
	 * Gets the promo package.
	 *
	 * @return the promo package
	 */
	public ArrayList<OrderItem> getPromoPackage() {
		return promoPackage;
	}
	
	/**
	 * Sets the promo package.
	 *
	 * @param promoPackage the new promo package
	 */
	public void setPromoPackage(ArrayList<OrderItem> promoPackage) {
		this.promoPackage = promoPackage;
	}
	
	

}
