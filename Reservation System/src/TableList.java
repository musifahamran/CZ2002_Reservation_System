import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class TableList.
 */
public class TableList implements DisplayInterface {
	
	/** Holds the number of tables for 10 seats */
	private int seaters10;
	/** Holds the number of tables for 8 seats */
	private int seaters8;
	/** Holds the number of tables for 4 seats */
	private int seaters4;
	/** Holds the number of tables for 2 seats*/
	private int seaters2;//this is just to hold size actually.
	//public static final String tableFilePath = System.getProperty("user.dir") + "/src/tablesList";  // Please update the paths accordingly.
	//it can limit the amount of seats objects in each arraylist.
	//for example i have set that the number of tables for 10 seaters is only 3.
	//so when we pass the arraylist in (it must have the table objects in alrdy), which in order to get the table objects in
	/** The seater 10. */
	//we have generate table objects with no values and pass it into this each arraylist
	private ArrayList<Table> seater10 = new ArrayList<Table>();
	
	/** The seater 8. */
	private ArrayList<Table> seater8= new ArrayList<Table>(); 
	
	/** The seater 4. */
	private ArrayList<Table> seater4 = new ArrayList<Table>(); 
	
	/** The seater 2. */
	private ArrayList<Table> seater2 = new ArrayList<Table>();
	
	//method 1, create all table objects then let a controller read reservation objects and update it
	//but will have to change tableID to be string it becomes easier to declare
	/**
	 * Instantiates a new table list.
	 *
	 * @param seaters10 the seaters 10
	 * @param seaters8 the seaters 8
	 * @param seaters4 the seaters 4
	 * @param seaters2 the seaters 2
	 */
	//then if u want sort the id by ascending we just do java convert string to int is much easier
	public TableList(int seaters10,int seaters8,int seaters4,int seaters2)
	{
		
		this.seaters10 = seaters10;
		this.seaters8 = seaters8;
		this.seaters4 = seaters4;
		this.seaters2 = seaters2;
	
		//generate the number of objects for each seating table arraylist
		//eg. arraylist for 10 seaters has seater10 variable which will then be updated by a control module 
		//that reads in all the reservations for today and then updates accordingly.
		//lets say we have a id convention for determining tableCapacity
		//so for a 10 seater it starts with 10{index}= e.g 10_1, 10_2,10_3,...,10_21
		//4 seater is 4{index} = e.g 4_1,4_2,4_3
		//lets say there is a 10 seater table whose tableID is 1002 that is reserved today, 
		//then assume the arraylists have all the declare tables objects that	
		//so controller detects the Reservation object it hold has table ID 1003, determines first 2 characters is 10
		//meaning 10 seater, then controller just loops through tableList's seater10 arrayList for the particular empty table
		//then updates it's reservation and details etc.
			
		  for(int i=0;i<seaters10;i++)//for 10 seaters
		  {
		  	String seatType = "10";
		   if(i < 10)
		   	      //table --> 01,02,03,04,05,06,07,08,09,10
		   		  //i     -->  0, 1, 2, 3, 4, 5, 6, 7, 8, 9
		   seatType = "100";
		   String tableIDString = seatType + Integer.toString((i+1)); //"100" + "1" --> "1001"
		 						       //else for items of (i+1) >=10-->"10"  + "10"--> "1010" 
		   int tableID = Integer.parseInt(tableIDString);
		   Table newTable = new Table(tableID,false,0,10,0);
		   seater10.add(newTable);
		  }
		  for(int i=0;i<seaters8;i++)//for 8 seaters
		  {
		  	String seatType = "08";
		   if(i < 10)
		   	      //table --> 01,02,03,04,05,06,07,08,09,10
		   		  //i     -->  0, 1, 2, 3, 4, 5, 6, 7, 8, 9
		   seatType = "080";
		   String tableIDString = seatType + Integer.toString((i+1)); //"080" + "1" --> "0801"
		 						       //else for items of (i+1) >=10-->"10"  + "10"--> "1010" 
		   int tableID = Integer.parseInt(tableIDString);
		   Table newTable = new Table(tableID,false,0,8,0);
		   seater8.add(newTable);
		  }
		  for(int i=0;i<seaters4;i++)//for 4 seaters
		  {
		  	String seatType = "04";
		   if(i < 10)
		   	      //table --> 01,02,03,04,05,06,07,08,09,10
		   		  //i     -->  0, 1, 2, 3, 4, 5, 6, 7, 8, 9
		   seatType = "040";
		   String tableIDString = seatType + Integer.toString((i+1)); //"040" + "1" --> "0401"
		 						       //else for items of (i+1) >=10-->"10"  + "10"--> "1010" 
		   int tableID = Integer.parseInt(tableIDString);
		   Table newTable = new Table(tableID,false,0,4,0);
		   seater4.add(newTable);
		  }
		  for(int i=0;i<seaters2;i++)//for 02 seaters
		  {
		  	String seatType = "02";
		   if(i < 10)
		   	      //table --> 01,02,03,04,05,06,07,08,09,10
		   		  //i     -->  0, 1, 2, 3, 4, 5, 6, 7, 8, 9
		   seatType = "020";
		   String tableIDString = seatType + Integer.toString((i+1)); //"020" + "1" --> "0201"
		 						       //else for items of (i+1) >=10-->"02"  + "10"--> "0210" 
		   int tableID = Integer.parseInt(tableIDString);
		   Table newTable = new Table(tableID,false,0,2,0);
		   seater2.add(newTable);
		  }
		  
		 
		//if all IDs are string we can always just set example String baseDeterminate = '1000';for 10 seater value.
		//then Integer.parseInt(seaterDeterminant)++ -->> 1000++ -->> 1001 :)
	}

	/**
	 * Gets the seaters10 number of tables.
	 *
	 * @return the seaters10 number of tables
	 */
	public int getSeaters10Size() {
		return this.seaters10;
	}

	/**
	 * Sets the seaters10 size.
	 *
	 * @param seaters10 the new seaters 10 size
	 */
	public void setSeaters10Size(int seaters10) {
		this.seaters10 = seaters10;
	}

	/**
	 * Gets the seaters8 number of tables.
	 *
	 * @return the seaters8 number of tables
	 */
	public int getSeaters8Size() {
		return this.seaters8;
	}

	/**
	 * Sets the seaters8 number of tables.
	 *
	 * @param seaters8 the new seaters8 number of tables
	 */
	public void setSeaters8Size(int seaters8) {
		this.seaters8 = seaters8;
	}

	/**
	 * Gets the seaters4 number of tables.
	 *
	 * @return the seaters4 number of tables
	 */
	public int getSeaters4Size() {
		return this.seaters4;
	}

	/**
	 * Sets the seaters4 number of tables.
	 *
	 * @param seaters4 the new seaters4 number of tables
	 */
	public void setSeaters4Size(int seaters4) {
		this.seaters4 = seaters4;
	}

	/**
	 * Gets the seaters2 number of tables.
	 *
	 * @return the seaters2 number of tables
	 */
	public int getSeaters2Size() {
		return this.seaters2;
	}

	/**
	 * Sets the seaters2 number of tables.
	 *
	 * @param seaters2 the new seaters2 number of tables
	 */
	public void setSeaters2Size(int seaters2) {
		this.seaters2 = seaters2;
	}

	/**
	 * Gets the seater10 ArrayList.
	 *
	 * @return the seater10
	 */
	public ArrayList<Table> getSeater10() {
		return this.seater10;
	}

	/**
	 * Sets the seater10 ArrayList.
	 *
	 * @param seater10 the new seater10
	 */
	public void setSeater10TableByID(ArrayList<Table> seater10) {
		this.seater10 = seater10;
	}

	/**
	 * Gets the seater8 ArrayList.
	 *
	 * @return the seater8 the new seater8
	 */
	public ArrayList<Table> getSeater8() {
		return this.seater8;
	}

	/**
	 * Sets the seater8  ArrayList.
	 *
	 * @param seater8 the new seater8
	 */
	public void setSeater8TableByID(ArrayList<Table> seater8) {
		this.seater8 = seater8;
	}

	/**
	 * Gets the seater4 ArrayList
	 *
	 * @return the seater4
	 */
	public ArrayList<Table> getSeater4() {
		return this.seater4;
	}

	/**
	 * Sets the seater4.
	 *
	 * @param seater4 the new seater4
	 */
	public void setSeater4(ArrayList<Table> seater4) {
		this.seater4 = seater4;
	}

	/**
	 * Gets the seater2 ArrayList
	 *
	 * @return the seater2
	 */
	public ArrayList<Table> getSeater2() {
		return this.seater2;
	}

	/**
	 * Sets the seater2.
	 *
	 * @param seater2 sets to the new seater2
	 */
	public void setSeater2(ArrayList<Table> seater2) {
		this.seater2 = seater2;
	}
	
	/**
	 * Gets the seater2 by table ID.
	 *
	 * @param tableID the table ID
	 * @return the seater2 by table ID
	 */
	public Table getSeater2ByTableID(int tableID)
	{
		for(int i =0;i<this.seater2.size();i++)
		{
			if(this.seater2.get(i).getTableID()==tableID)
			{
				return this.seater2.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Gets the seater 4 by table ID.
	 *
	 * @param tableID the table ID
	 * @return the seater 4 by table ID
	 */
	public Table getseater4ByTableID(int tableID)
	{
		for(int i =0;i<this.seater4.size();i++)
		{
			if(this.seater4.get(i).getTableID()==tableID)
			{
				return this.seater4.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Gets the seater 8 by table ID.
	 *
	 * @param tableID the table ID
	 * @return the seater 8 by table ID
	 */
	public Table getSeater8ByTableID(int tableID)
	{
		for(int i =0;i<this.seater8.size();i++)
		{
			if(this.seater8.get(i).getTableID()==tableID)
			{
				return this.seater8.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Gets the seater10 by table ID.
	 *
	 * @param tableID the table ID
	 * @return the seater10 by table ID
	 */
	public Table getSeater10ByTableID(int tableID)
	{
		for(int i =0;i<this.seater10.size();i++)
		{
			if(this.seater10.get(i).getTableID()==tableID)
			{
				return this.seater10.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Overriden method of Display Interface
	 * 
	 * 
	 * Iterates through all the tables to display as follows
	 * reserved s a variable which holds string value of "Avaliable" or "Reserved" based on isReserved of table Object
	 * System.out.println(allTables.get(i).getTableID()+" \t " + reserved+" \t " 
		+ allTables.get(i).getSeatingCapacity() + " \t " + allTables.get(i).getOrderID() + " \t " + allTables.get(i).getResID());
	 *
	 *@see DisplayInterface#Display()
	 */
	@Override
	public void Display()
	{
		boolean status = false;
		ArrayList<Table>allTables = new ArrayList<Table>();
		allTables.addAll(seater10);
		allTables.addAll(seater8);
		allTables.addAll(seater4);
		allTables.addAll(seater2);
		System.out.println("TableID:  Status: \tSeats: \tOrder:\tResID:");
		//System.out.println("ID :\t Reserved: \t Seating Capacity : \t OrderID : \tResID : ");
		for(int i =0;i<allTables.size();i++)
		{
			String reserved = "Reserved";
			if(allTables.get(i).isReserved()==false)
				reserved = "Avaliable";
			else {
				reserved = "Reserved";
				System.out.println(allTables.get(i).getTableID()+" \t " + reserved+" \t " 
						+ allTables.get(i).getSeatingCapacity() + " \t " + allTables.get(i).getOrderID() + "  " + allTables.get(i).getResID());
				status = true;
			}
			if(status == true && !reserved.equalsIgnoreCase("reserved"))//When displayed, the columns would be inline with each other
				System.out.println(allTables.get(i).getTableID()+" \t " + reserved+" \t " 
						+ allTables.get(i).getSeatingCapacity() + " \t " + allTables.get(i).getOrderID() + "\t " + allTables.get(i).getResID());
			
			if(status == false)	
				System.out.println(allTables.get(i).getTableID()+" \t " + reserved+" \t " 
						+ allTables.get(i).getSeatingCapacity() + " \t " + allTables.get(i).getOrderID() + " \t " + allTables.get(i).getResID());
		}
		allTables = null;
	}
	
	/**
	 * Display available tables that has no reservation made for it
	 * If table is reserved and has reservation Id set as 0 for object the Table is considered as occupied for walk-ins
	 * <pre>
	 * {@code
	 * System.out.println("ID :"+allTables.get(i).getTableID()+" \t " + allTables.get(i).isReserved()+" Seats : " 
	 * + allTables.get(i).getSeatingCapacity() + " order ID : " + allTables.get(i).getOrderID() + " Res ID : " + allTables.get(i).getResID());
	 * }
	 * </pre>
	 * 
	 */
	public void DisplayAvaliable()//this means if table is reserved and has a resId of !=0 its unavaliable
	{							  //if table is reserved and has resID of = 0 its unavaliable as its occupied
		ArrayList<Table>allTables = new ArrayList<Table>();
		allTables.addAll(seater10);
		allTables.addAll(seater8);
		allTables.addAll(seater4);
		allTables.addAll(seater2);
		System.out.println("List of Table Avaliability");
		for(int i =0;i<allTables.size();i++)
		{
			if(allTables.get(i).isReserved()==false)
			System.out.println("ID :"+allTables.get(i).getTableID()+" \t " + allTables.get(i).isReserved()+" Seats : " 
		+ allTables.get(i).getSeatingCapacity() + " order ID : " + allTables.get(i).getOrderID() + " Res ID : " + allTables.get(i).getResID());
		}
	}
	
	/**
	 * Search by table by the tableID.
	 * This method returns a table object based on the table ID which is parsed in.
	 *
	 * @param tableID the table ID
	 * @return the table
	 */
	public Table searchByID(int tableID)
	{
		ArrayList<Table>allTables = new ArrayList<Table>();
		allTables.addAll(seater10);
		allTables.addAll(seater8);
		allTables.addAll(seater4);
		allTables.addAll(seater2);
		for(int i =0;i<allTables.size();i++)
		{
			if(allTables.get(i).getTableID() == tableID)
				return allTables.get(i);
		}
		return null;
	}
	
	/**
	 * View available 2 seater tables.
	 */
	public void viewFree2seaterTables() {
		for (int i = 0; i<seater2.size();i++) {
			if (!seater2.get(i).isReserved()) {
				System.out.println("TableID: "+seater2.get(i).getTableID());
			}
		
		}
	}
	
	/**
	 * View available 4 seater tables.
	 */
	public void viewFree4seaterTables() {
			for (int i = 0; i<seater4.size();i++) {
				if (!seater4.get(i).isReserved()) {
					System.out.println("TableID: "+seater4.get(i).getTableID());
				}
			
			}
	
	}
	
	/**
	 * View available 8 seater tables.
	 */
	public void viewFree8seaterTables() {
		for (int i = 0; i<seater8.size();i++) {
			if (!seater8.get(i).isReserved()) {
				System.out.println("TableID: "+seater8.get(i).getTableID());
			}
		
		}

	}
	
	/**
	 *  View available 10 seater tables.
	 */
	public void viewFree10seaterTables() {
		for (int i = 0; i<seater10.size();i++) {
			if (!seater10.get(i).isReserved()) {
				System.out.println("TableID: "+seater10.get(i).getTableID());
			}
		
		}

	}
}
