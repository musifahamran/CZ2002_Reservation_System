import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class PromotionList.
 */
public class PromotionList implements DisplayInterface {
	
	/** The Constant promotionsPath. */
	private static final String promotionsPath = System.getProperty("user.dir")+"/src/promotionObjs";
	
	/** The promolist ArrayList that hold Promotion object */
	//private static final String promotionsPath = System.getProperty("user.dir")+"\\src\\promotionObjs";
	private ArrayList<Promotion> promoList = new ArrayList<Promotion>();
	
	
	/**
	 * Default constructor
	 * Instantiates a new promotion list.
	 */
	public PromotionList() {
	}
	
	/**
	 * Adds the promotion.
	 *
	 * @param promo the promo
	 */
	public void addPromotion(Promotion promo) {
		promoList.add(promo);
		return;
	}
	
	/**
	 * Removes the promo by ID.
	 *
	 * @param promoID the promo ID
	 */
	public void removePromoByID(int promoID) {
		Promotion temp = getPromoByID(promoID);
		if(temp == null)
			System.out.println("No such promo set exist!");
		else
			{this.promoList.remove(temp);
			System.out.println("Promotion set has been successfully removed!");
			}
	}
	
	/**
	 * Gets the promo by ID.
	 *
	 * @param promoID the promo ID
	 * @return the promo by ID
	 */
	public Promotion getPromoByID(int promoID) {
		Promotion temp;
		if ( this.promoList.size() == 0) {
			System.out.println(" The Promo list is empty!");
			return null;
		}
		else {
			for (int i = 0; i<this.promoList.size();i++ ) {
				temp = this.promoList.get(i);
				if (temp.getPromoID() == promoID) {
					return temp;
				}
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see DisplayInterface#Display()
	 */
	@Override
	public void Display() {
		Promotion temp = null;
		if ( promoList.size() == 0) {
			System.out.println(" The Promo list is empty!");
			return;
		}
		else {
			System.out.println("Promotions:");
			for (int i = 0; i<promoList.size();i++ ) {
				temp = promoList.get(i);
				temp.printpromoPackage();
				System.out.println("The price of the Promotion is " + temp.getPrice() + "\n");
			}
		}
		return;
	}
	
	/**
	 * Read all objects from file.
	 */
	@SuppressWarnings("unchecked")
	public void readAllObjectsFromFile() {
		try {
			 
            FileInputStream fileIn = new FileInputStream(promotionsPath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            this.promoList=(ArrayList<Promotion>) objectIn.readObject();
            System.out.println("The Promotions has been read from the file");
            objectIn.close();
       } 
	 catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize()
	{
		return this.promoList.size();
	}
	
	/**
	 * Write all objects of Promotion within promoList to file.
	 * @see java.io.FileInputStream
	 */
	public void WriteAllobjectsFromFile() {
		
		 try {
			 
	            FileOutputStream fileOut = new FileOutputStream(promotionsPath);
	            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	            objectOut.writeObject(this.promoList);
	            objectOut.close();
	            System.out.println("The promotions were succesfully written to a file");
	 
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	}
	
	
}
