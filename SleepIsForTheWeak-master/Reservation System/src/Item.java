//import java.util.Random;
import java.io.Serializable;
import java.util.Random;



// TODO: Auto-generated Javadoc
/**
 * The Class Item.
 */
public class Item implements Serializable{
	
	/** The Constant serialVersionUID. 
	 *This tags the object to a unique serial for writing to serialized file
	 *
	 * @see java.io.Serializable 
	 */
	//default serialVersion id
    private static final long serialVersionUID = 1L;
    //fileName
    //private static final String itemFilePath=System.getProperty("user.dir")+"\\src\\itemObjs";
    
	/** The item ID. */
    private int itemID;
	
	/** The price. */
	private double price;
	
	/** The description. */
	private String type, menuName, description;
	
	/**
	 * Parameterized Constructor
	 * Instantiates a new item.
	 * Instantiates a new item, without Parameters
	 * itemID is handled by a random number generator
	 */
	public Item(){
		Random r = new Random();
		this.setItemID(r.nextInt(500));
	}//default empty constructor
	
	/**
	 * Instantiates a new item.
	 *
	 * @param itemID the item ID
	 * @param menuName the menu name
	 * @param price the price
	 * @param Type the type
	 * @param desc the desc
	 */
	public Item(int itemID,String menuName, double price,String Type,String desc) {
		this.setItemID(itemID);
		//this.setItemIDbyType(Type);
		this.setPrice(price);
		this.setMenuName(menuName);
		this.setType(Type);
		this.setDescription(desc);
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
	 * Gets the menu name.
	 *
	 * @return the menu name
	 */
	public String getMenuName() {
		return menuName;
	}
	
	/**
	 * Sets the menu name.
	 *
	 * @param menuName the new menu name
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the item ID.
	 *
	 * @return the item ID
	 */
	public int getItemID() {
		return itemID;
	}
	
	/**
	 * Sets the item ID.
	 *
	 * @param itemID the new item ID
	 */
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	/** Override of toString method from java
	 * 
	 * 
	 * In this implementation toString() displays the attributes of Item class
	 * itemID : this.itemID menuName : this.menuName price : this.type description : this.description
	 *  <pre>
	 * {@code         return new StringBuffer(" itemID: ").append(this.itemID)
     *           .append(" menuName : ").append(this.menuName)
     *           .append(" price: ").append(this.price)
     *          .append(" type: ").append(this.type)
     *           .append(" description : ").append(this.description).toString();
     * }           
     * </pre>
     * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
        return new StringBuffer(" itemID: ").append(this.itemID)
                .append(" menuName : ").append(this.menuName)
                .append(" price: ").append(this.price)
                .append(" type: ").append(this.type)
                .append(" description : ").append(this.description).toString();
    }

}
