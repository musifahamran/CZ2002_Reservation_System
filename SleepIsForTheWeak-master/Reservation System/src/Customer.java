
// TODO: Auto-generated Javadoc
/**
 * The Class Customer.
 */
public class Customer extends Person {
	
	/** The loyaltypoints. */
	private int loyaltypoints;
		
		/**
		 * Instantiates a new customer.
		 *
		 * @param name the name
		 * @param phonenumber the phonenumber
		 */
		public Customer(String name, String phonenumber) {
		super(name, phonenumber);
		setLoyaltypoints(0);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Gets the loyaltypoints.
	 *  The loyalty points is right now not used 
	 *  its for the future feasibility for the expansion
	 * @return the loyaltypoints
	 */
	public int getLoyaltypoints() {   	//The loyalty points is right now not used 
		return loyaltypoints;			// its for the future feasibility for the expansion
	}
	
	/**
	 * Sets the loyaltypoints.
	 *
	 * @param loyaltypoints the new loyaltypoints
	 */
	public void setLoyaltypoints(int loyaltypoints) {
		this.loyaltypoints = loyaltypoints;
	}
	
	/**
	 * Adds the loyaltypoints.
	 *
	 * @param points the points
	 */
	public void addLoyaltypoints(int points) {
		this.setLoyaltypoints(this.getLoyaltypoints() + points);
		return;
	}

}
