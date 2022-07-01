

// TODO: Auto-generated Javadoc
/**
 * The Class Table.
 */
public class Table {
	
	/** The seating capacity. */
	private int tableID, orderID, seatingCapacity;
	
	/** Handles if the Table is reserved. 
	 * True = indicates reserved, false = indicates available
	 */
	private boolean reserved;
	
	/** The reservationID which references to a Reservation Object. */
	private int resID;
	
	/**
	 * Paramterized Constructor
	 * Instantiates a new table.
	 *
	 * @param TableID the table ID
	 * @param reserved the reserved
	 * @param orderID the order ID
	 * @param seatingCapacity the seating capacity
	 * @param resID the res ID
	 */
	public Table(int TableID, boolean reserved, int orderID,int seatingCapacity, int resID) {
		this.tableID = TableID;
		this.reserved = reserved;
		this.seatingCapacity =seatingCapacity;
		this.orderID = orderID;
		this.resID = resID;
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
	 * Gets the seating capacity.
	 *
	 * @return the seating capacity
	 */
	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	/**
	 * Sets the seating capacity.
	 *
	 * @param seatingCapacity the new seating capacity
	 */
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	/**
	 * Checks if is reserved.
	 *
	 * @return true, if is reserved
	 */
	public boolean isReserved() {
		return reserved;
	}

	/**
	 * Sets the reserved.
	 *
	 * @param reserved the new reserved
	 */
	public void setReserved(boolean reserved) {
		this.reserved = reserved;
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
	

}
