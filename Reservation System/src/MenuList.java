
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuList.
 */
public class MenuList implements DisplayInterface{
	
	/** The menuList arrayList that holds objects from Item class*/
	private ArrayList<Item> menuList = new ArrayList<Item>();
	
	/** The Constant itemFilePath. */
	//private static final String itemFilePath=System.getProperty("user.dir")+"/src/itemObjs";
	private static final String itemFilePath=System.getProperty("user.dir")+"\\src\\itemObjs";
	
	/**
	 * Default Constructor
	 * Instantiates a new menu list.
	 * MenuList has no parameters as it holds ArrayList of object Item
	 * @see java.util.ArrayList
	 */
	public MenuList() {
		
	}
	
	/**
	 * Parmeterized Constructor
	 * Adds the item object to the ArrayList
	 *
	 * @param menu the Item object to hold
	 */
	public void addMenu(Item menu) {
		int size = menuList.size();
		menu.setItemID((menuList.get(size-1).getItemID())+1);
		menuList.add(menu);
	}
	
	/**
	 * Delete the item object based on the item object ID from the ArrayList
	 *
	 * @param menuID the menu ID
	 */
	public void deleteMenu(int menuID) {
		Item temp;
		int flag  = 0;
		if (menuList.size() == 0) {
			System.out.println("Menu list is empty. There is no item to delete");
			return;
		}
		else {
		for (int i = 0; i< menuList.size();i++) {
			temp = menuList.get(i);
			if (temp.getItemID() == menuID) {
				menuList.remove(i);
				flag = 1;
				System.out.println("Menu Item removed Successfully");
				break;
			}
		}
		}
		if (flag==0) {
			System.out.println("Invalid ID");
		}
	}
	
	/**
	 * Gets the size of the ArrayList contained in MenuList class
	 *
	 * @return the size
	 */
	public int getSize()//new
	{
		return this.menuList.size();
	}
	
	/**
	 * Gets the item by index contained in the ArrayList of MenuList class
	 *
	 * @param i the i
	 * @return the item
	 */
	public Item getItem(int i)
	{
		return menuList.get(i);//new
	}
	
	/**
	 * Sets the item object to a specified index within the ArrayList inside MenuList class
	 *
	 * @param i the i
	 * @param itemObj the item obj
	 */
	public void setItem(int i, Item itemObj)//new
	{
		 this.menuList.set(i, itemObj);
	}
	
	/**
	 * Gets the item by item ID.
	 *
	 * @param itemID the item ID
	 * @return the item by item ID
	 */
	public Item getItemByItemID(int itemID)//new this is to get item obj out by id 
	{
		for(int i =0;i<this.menuList.size();i++)
		{
			if(this.menuList.get(i).getItemID()==itemID)
			{
				return this.menuList.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Formatted printing for item object out from the ArrayList in MenuList class.
	 * <pre>
	 * {@code System.out.println("ID: "+ temp.getItemID() + " Item Name: "+ temp.getMenuName() +" Type: "
	 * +temp.getType() +" Price: " + temp.getPrice()
	 * +"\nDesc: " + temp.getDescription());}
	 * </pre>
	 * @see DisplayInterface#Display()
	 */
	public void Display() {
		Item temp;
		if ( menuList.size() == 0) {
			System.out.println("Menu List is empty");
			return;
		}
		for (int i = 0; i<menuList.size();i++) {
			temp = menuList.get(i);
			System.out.println("ID: "+ temp.getItemID() + " Item Name: "+ temp.getMenuName() +" Type: "+temp.getType() +" Price: " + temp.getPrice()
			+"\nDesc: " + temp.getDescription());
		}
		
	}
	 
 	/**
 	 * Write all items objects of Item to internal constant itemFilePath in class.
 	 */
 	public void WriteAllItemsObjToFile() {
		 
	        try {
	 
	            FileOutputStream fileOut = new FileOutputStream(itemFilePath);
	            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	            objectOut.writeObject(this.menuList);
	            objectOut.close();
	            System.out.println("The Object  was succesfully written to a file");
	 
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	 
 	/**
 	 * Read all items objects of Item to internal constant itemFilePath in class.
 	 */
 	@SuppressWarnings("unchecked")
	public void ReadAllItemsObjFromFile(){
		 try {
			 
	            FileInputStream fileIn = new FileInputStream(itemFilePath);
	            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
	 
	            this.menuList=(ArrayList<Item>) objectIn.readObject();
	            System.out.println("The Objects has been read from the file");
	            objectIn.close();
	       } 
		 catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
}
