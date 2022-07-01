import java.time.LocalDateTime;

// TODO: Auto-generated Javadoc
/**
 * The Class Payment.
 */
public class Payment {
	
	/** The total. */
	private double GST = 0.07, amountPaid, change, total;
	
	/** The time stamp. */
	private LocalDateTime timeStamp;
	
	/** To determine if payment is by cash or card. 
	 *	True = Cash, False = Card. 
	 */
	private boolean isCash;
	

	/**
	 * Default constructor
	 * Instantiates a new payment.
	 */
	public Payment() 
	{
		
	}
	
	/**
	 * Parameterized Constructor
	 * Instantiates a new payment.
	 *
	 * @param timeStamp the time stamp
	 * @param total the total
	 * @param amountPaid the amount paid
	 * @param isCash the is cash
	 */
	//Constructor with variables
	public Payment(LocalDateTime timeStamp, double total, double amountPaid, boolean isCash) {
		this.setAmountPaid(amountPaid);
		this.setTimeStamp(timeStamp);
		this.setIsCash(isCash);
		this.setTotal(total); //+ (total*this.getGST()));
		if ( isCash) {
			if(amountPaid>total)
			this.setChange(amountPaid-(this.total*(1+this.getGST())));
		}else {
			this.setChange(0);
		}
	}


	/**
	 * Gets the gst.
	 *
	 * @return the gst
	 */
	public double getGST() {
		return GST;
	}


	/**
	 * Sets the gst.
	 *
	 * @param gST the new gst
	 */
	public void setGST(double gST) {
		GST = gST;
	}


	/**
	 * Gets the amount paid.
	 *
	 * @return the amount paid
	 */
	public double getAmountPaid() {
		return amountPaid;
	}


	/**
	 * Sets the amount paid.
	 *
	 * @param amountPaid the new amount paid
	 */
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}


	/**
	 * Gets the change.
	 *
	 * @return the change
	 */
	public double getChange() {
		return change;
	}


	/**
	 * Sets the change.
	 *
	 * @param change the new change
	 */
	public void setChange(double change) {
		this.change = change;
	}


	/**
	 * Gets the time stamp.
	 *
	 * @return the time stamp
	 */
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}


	/**
	 * Sets the time stamp.
	 *
	 * @param timeStamp the new time stamp
	 */
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}


	/**
	 * Gets the checks if is cash.
	 *
	 * @return the checks if is cash
	 */
	public boolean getIsCash() {
		return isCash;
	}


	/**
	 * Sets the checks if is cash.
	 *
	 * @param isCash the new checks if is cash
	 */
	public void setIsCash(boolean isCash) {
		this.isCash = isCash;
	}


	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}


	/**
	 * Sets the total.
	 *
	 * @param total the new total
	 */
	public void setTotal(double total) {
		this.total = total;
	}
}


