
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

// TODO: Auto-generated Javadoc
/**
 * The Class Reservation.
 */
public class Reservation implements Serializable{
	
		/** The Constant serialVersionUID. 
		* This tags the object to a unique serial for writing to serialized file
		* @see java.io.Serializable
		*/
		//default serialVersion id
	    private static final long serialVersionUID = 4L;
		
		/** The reservation ID. */
		private int resID;
		
		/** The Customer object reference */
		private Customer customer;
		
		/** The arrival time. */
		private LocalDateTime arrTime;
		
		/** The table ID. */
		private int tableID;
		
		/** Handles if the object expired. */
		private boolean expired;
		
		/** The timer. */
		transient  Timer timer;
	    
    	/** The changes. */
    	private PropertyChangeSupport changes = new PropertyChangeSupport(this); 
	    
    	/** The date for today. */
    	private LocalDateTime today = LocalDateTime.now();
	
	/**
	 * Parameterized Constructor
	 * Instantiates a new reservation.
	 *
	 * resID is handled by a random number generator
	 * @param arrTime the arrival time for Reservation
	 */
	public Reservation(LocalDateTime arrTime) {
		this.setArrTime(arrTime);
		this.setExpired(false);
		this.setResID((int)(Math.random()*1000));
		if (today.toLocalDate().equals(arrTime.toLocalDate())) {
			if ((today.toLocalTime().getHour()< 15 && arrTime.toLocalTime().getHour()<15) || (today.toLocalTime().getHour()> 18 && arrTime.toLocalTime().getHour()>18)) {
				timer_res();
				
			}
		}
	}
		
	/**
	 * Instantiates a new reservation.
	 *
	 * @param customer the customer
	 * @param arrTime the arr time
	 * @param tableID the table ID
	 */
	public Reservation(Customer customer, LocalDateTime arrTime, int tableID) {
		this.setCustomer(customer);
		this.setArrTime(arrTime);
		this.setTableID(tableID);
		this.setResID((int)(Math.random()*1000));
		this.setResID(tableID);
		this.setExpired(false);
		if (today.toLocalDate().equals(arrTime.toLocalDate())) {
			if ((today.toLocalTime().getHour()< 16 && arrTime.toLocalTime().getHour()<16) || (today.toLocalTime().getHour()> 18 && arrTime.toLocalTime().getHour()>18)) {
				timer_res();
				
			}
		}
	}

	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets the customer.
	 *
	 * @param customer the new customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Gets the arr time.
	 *
	 * @return the arr time
	 */
	public LocalDateTime getArrTime() {
		return arrTime;
	}

	/**
	 * Sets the arr time.
	 *
	 * @param arrTime the new arr time
	 */
	public void setArrTime(LocalDateTime arrTime) {
		this.arrTime = arrTime;
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
	 * Gets the res ID.
	 *
	 * @return the res ID
	 */
	public int getResID() {
		return resID;
	}

	/**
	 * Sets the res ID.
	 *
	 * @param resID the new res ID
	 */
	public void setResID(int resID) {
		this.resID = resID;
	}
	
	/**
	 * Timer res.
	 * When the Reservation object's arrival time has passed, Timer object prints out a reminder
	 * @see java.util.Timer
	 * @see java.util.TimerTask
	 */
	public void timer_res() {
		LocalDateTime expTime = this.arrTime.plusMinutes(15); //change the grace period here
		timer = new Timer();
		Date date = null;
		Instant instant = expTime.atZone(ZoneId.systemDefault()).toInstant();
		date = Date.from(instant);
		timer.schedule(new RemindTask(), date);
		System.out.println(date.toString());
	}
	
	/**
	 * Checks if is expired.
	 *
	 * @return true, if is expired
	 */
	public boolean isExpired() {
		return expired;
	}

	/**
	 * Sets the expired.
	 *
	 * @param expired the new expired
	 */
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	
	/**
	 * The Class RemindTask.
	 */
	class RemindTask extends TimerTask {
        
        /* (non-Javadoc)
         * @see java.util.TimerTask#run()
         */
        public void run() {
            System.out.println("The reservation "+getResID() +" has been Expired!");
            setExpired(true);
            
            changes.firePropertyChange("expiry", false, true);;
            timer.cancel(); //Terminate the timer thread
        }
    }
	
	/**
	 * Adds the property change listener.
	 * 
	 * @param p the p
	 * 
	 * @see java.beans.PropertyChangeListener
	 * @see java.beans.PropertyChangeSupport
	 */
	public void addPropertyChangeListener(PropertyChangeListener p) {
		changes.addPropertyChangeListener(p);
	}
	
	/**
	 * Removes the property change listener.
	 *
	 * @param p the p
	 * 
	 * @see java.beans.PropertyChangeListener
	 * @see java.beans.PropertyChangeSupport
	 */
	public void removePropertyChangeListener(PropertyChangeListener p) {
		changes.removePropertyChangeListener(p);
	}

}
