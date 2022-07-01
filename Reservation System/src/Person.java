import java.io.Serializable;
// TODO: Auto-generated Javadoc

/**
 * The Class Person.
 */
public class Person implements Serializable{
	
	/** The Constant serialVersionUID. 
	 * This tags the object to a unique serial for writing to serialized file
	 * @see java.io.Serializable
	 */
	//default serialVersion id
    private static final long serialVersionUID = 5L;
	
	/** The name of person. */
	private String name;
	
	/** The name of person. */
	private String phoneNumber;
	
	/**
	 * Parameterized Constructor
	 * Instantiates a new person.
	 *
	 * @param name the name
	 * @param phoneNumber the phone number
	 */
	public Person(String name, String phoneNumber) {
		this.setName(name);
		this.setPhoneNumber(phoneNumber);
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
