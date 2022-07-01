import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Staff.
 */
public class Staff extends Person {
	
	/** The employee ID. */
	private int employeeID;
	
	/** The job title. */
	private String jobTitle;
	
	/**
	 * Parameterized Constructor
	 * Instantiates a new staff.
	 *
	 * @param name the name
	 * @param phoneNumber the phone number
	 * @param jobTitle the job title
	 */
	public Staff(String name, String phoneNumber, String jobTitle) {
		super(name, phoneNumber);
		this.setJobTitle(jobTitle);
		Random r = new Random();
		setEmployeeID(r.nextInt(100000));// staff ID is a 5 digit number.
	}
	
	/**
	 * Gets the job title.
	 *
	 * @return the job title
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	
	/**
	 * Sets the job title.
	 *
	 * @param jobTitle the new job title
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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

}
