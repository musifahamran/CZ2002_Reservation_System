import java.util.Scanner;
import java.util.ArrayList;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;




// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {
	
	/** The p. */
	static PropertyChangeListener p;
	
	/** The res list. */
	static ArrayList<Reservation> resList;
	
	/** The table list. */
	static TableList tableList;
	
	/** The Constant resFilePath. */
	public static final String resFilePath=System.getProperty("user.dir")+"/src/reservationsList";
	
	/** The Constant payFilePath. */
	public static final String payFilePath = System.getProperty("user.dir") + "/src/SaleRevenueList";// Please update the paths accordingly. 
	//public static final String resFilePath=System.getProperty("user.dir")+"\\src\\reservationsList";	// Please update the paths accordingly. 
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	//public static final String payFilePath = System.getProperty("user.dir") + "\\src\\SaleRevenueList";
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String staff_Name, phoneNo, title,message, input = "";
		int choice, num_people;
		Reservation temp_res;
		Order temp;
		
		Scanner scan = new Scanner(System.in);
		//ArrayList<Customer> cusList = new ArrayList<Customer>();// create arraylist for customer
		resList = new ArrayList<Reservation>();// create arraylist for reservations *Remember to clean up the expired reservations at shutdown time.
		tableList = new TableList(5, 5, 10, 10);
		ArrayList<SaleRevenue> revenueList = new ArrayList<SaleRevenue>();
		ArrayList<Order> orderList = new ArrayList<Order>();
		PromotionList promotionList = new PromotionList();
		MenuList menuList = new MenuList();
		int afternoon = 15, evening = 18;
		
		
		menuList.ReadAllItemsObjFromFile();
		resList = (ArrayList<Reservation>) ReadAllItemsObjFromFile(resFilePath);
		revenueList = (ArrayList<SaleRevenue>) ReadAllItemsObjFromFile(payFilePath);
		LocalDateTime today =  LocalDateTime.now();
		promotionList.readAllObjectsFromFile();
		p = new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				for (int i = 0; i<resList.size();i++) {
			    	   if (resList.get(i).isExpired()) {
			    		   if (tableList.searchByID(resList.get(i).getTableID()).getResID()!= 0) {
			    			   tableList.searchByID(resList.get(i).getTableID()).setReserved(false);
				    		   tableList.searchByID(resList.get(i).getTableID()).setResID(0);
				    		   System.out.println("The Table has been released successfully");

			    		   } 
			    	   }
			       }
			}
		};
		for (int i = 0; i< resList.size();i++) {
			temp_res = resList.get(i);
			System.out.println("\nReservations :");
			System.out.println("Id: "+ temp_res.getResID() + "phone Number : "+ temp_res.getCustomer().getPhoneNumber());
			int id = temp_res.getTableID();
			if (today.toLocalDate().equals(temp_res.getArrTime().toLocalDate())) {
				if ((today.toLocalTime().getHour()< afternoon && temp_res.getArrTime().toLocalTime().getHour()<afternoon) || (today.toLocalTime().getHour()> evening && temp_res.getArrTime().toLocalTime().getHour()>evening)) {
					
					if (id< 300) {
						tableList.getSeater2ByTableID(id).setReserved(true);
						tableList.getSeater2ByTableID(id).setResID(temp_res.getResID());
					}
					else if ((400< id) && (id < 500 )) {
						tableList.getseater4ByTableID(id).setReserved(true);
						tableList.getseater4ByTableID(id).setResID(temp_res.getResID());
					}
					else if ((800< id)&&(id<900)) {
						tableList.getSeater8ByTableID(id).setReserved(true);
						tableList.getSeater8ByTableID(id).setResID(temp_res.getResID());
					}
					else {
						tableList.getSeater10ByTableID(id).setReserved(true);
						tableList.getSeater10ByTableID(id).setResID(temp_res.getResID());
					}
					Reservation temP = resList.get(i);
					temP.addPropertyChangeListener(p);
					resList.get(i).timer_res();
				}

			}
			
			
		}
		System.out.println("\nPlease enter the name of the staff: ");
		staff_Name =scan.nextLine(); //s
		System.out.println("\nPlease enter phone number: ");
		phoneNo =scan.nextLine();//9
		System.out.println("\nEnter the staff job title: ");
		title = scan.nextLine(); //Chef
		Staff staff = new Staff(staff_Name, phoneNo, title);
		do {
		message = "\n1.  Create/Update/Remove menu item\r\n" + 
				"2.  Create/Update/Remove promotion\r\n" + 
				"3.  Create order\r\n" + 
				"4.  View order\r\n" + 
				"5.  Add/Remove order item(s) to/from order\r\n" + 
				"6.  Create reservation booking\r\n" + 
				"7.  Check/Remove reservation booking\r\n" + 
				"8.  Check table availability\r\n" + 
				"9.  Pay order & Print bill\r\n" + //change this to Pay order & print bill
				"10. Print Sale Revenue Report by Period(eg: day or month)\n"+
				"11. To shutdown the system\n";
	
		
		choice = validateInt(message); //checking for invalid inputs such as string characters
		
		switch (choice) {
		case 1:
			message = "1. Create Menu item\n"+ 
					  "2. Remove Menu Item\n" + 
					  "3. Update Menu Item\n"+ 
					  "4. Display menu list\n";
			choice = validateInt(message); //checking for invalid inputs such as string characters
			switch(choice) {
			case 1:
				Item item = new Item();
				System.out.println("Enter the name of the Item");
				//scan.nextLine();  // Consume newline left-over
				String setName = scan.nextLine();
				item.setMenuName(setName);//replaces nextLine to next
				//System.out.println("Enter the price of the Item");
				item.setPrice(validateFloatInput("Enter the price of the Item"));
				System.out.println("Enter the type of the Item");
				//scan.nextLine();  // Consume newline left-over
				item.setType(scan.nextLine());
				System.out.println("Enter the description of the Item");
				item.setDescription(scan.nextLine());
				menuList.addMenu(item);
				System.out.println("Item successfully created and added to the Menu");
				break;
			case 2:
				menuList.Display();
				menuList.deleteMenu(validateInt("Enter the ID the item that needs to be removed: "));
				break;
			case 3:
				menuList.Display();
				int id = validateInt("Enter the ID of the item that needs to be Updated: \n");
				if(itemIDRange(menuList,id)==true) {
				message = "Select the option to update the Item\n"+ 
								 "1. Name of Item\n"+ 
								 "2. Price of the Item\n"+ 
								 "3. Type of the Item\n"+ 
								 "4. Description of the Item\n";
				switch(validateInt(message)) {
				case 1:
					scan.nextLine();
					System.out.println("Enter the new name of the Item");
					setName = scan.nextLine();
					menuList.getItemByItemID(id).setMenuName(setName);
					System.out.println("Name updated successfully");
					break;
				case 2:
					scan.nextLine();
					//System.out.println("Enter the new price of the Item");
					menuList.getItemByItemID(id).setPrice(validateFloatInput("Enter the new price of the Item\n"));
					System.out.println("Price updated successfully");
					break;
				case 3:
					scan.nextLine();
					System.out.println("Enter the new Type of the Item");
					menuList.getItemByItemID(id).setType(scan.nextLine());
					System.out.println("Type updated successfully");
					break;
				case 4:
				//	scan.nextLine();
					System.out.println("Enter the new description of the Item");
					menuList.getItemByItemID(id).setDescription(scan.nextLine());
					System.out.println("Description updated successfully");
					break;
				
				}
				}
				break;
			case 4:
				menuList.Display();
				break;
			}
			break;
		case 2:
			message = "1. Create Promotion\n"+ 
					 "2. Remove Promotion\n" + 
					 "3. Update Promotion\n"+ 
					 "4. Display promotions\n";
			OrderItem promoPack;
			Promotion promotion;
			switch(validateInt(message)) {
			
			case 1:
				
				menuList.Display();
				promotion = new Promotion();
				int itemNum = validateInt("Enter the number of different Items in the promotion\n");
				for (int i = 0; i< itemNum;i++) {
					promoPack = new OrderItem();
					promoPack.setPromotionItem(true);
					System.out.println("Item No: " + (i+1) + " of new promo set.");
					int id = validateInt("Enter the ItemID to be added to promotion:\n");
					
					if(itemIDRange(menuList,id)==true) {
					promoPack.setMenuObj(menuList.getItemByItemID(id));
					promoPack.setId(promoPack.getMenuObj().getItemID());
					promoPack.setQuantity(validateInt("Enter the Quantity of the items:\n"));
					promotion.addMenuItem(promoPack);
					}
					else
						i = i-1; //go back to same index when id of item doesn't exist
				}
				promotion.setPrice(validateFloatInput("Enter the price of the promotion: \n"));
				promotionList.addPromotion(promotion);
				System.out.println("Promotion created and added successfully");
				break;
			case 2:
				promotionList.Display();
				promotionList.removePromoByID(validateInt("Enter the ID of the promotion to be deleted: \n"));
				break;
			case 3:
				
				promotionList.Display();
				int id = validateInt("Enter the ID of the promotion that needs to be updated: \n");
				if(itemIDPromo(promotionList,id)==true)//check for id of item
				{
				message = "1. Add items to the promotion \n"+ 
						  "2. Remove Items from the promotion \n" + 
						  "3. Update the Price of the promotion \n";
				switch(validateInt(message)) {
				case 1:
					
					itemNum = validateInt("Enter the number of items to be added to the promotion\n");
					for (int i = 0; i< itemNum;i++) {
						promoPack = new OrderItem();
						promoPack.setMenuObj(menuList.getItemByItemID(validateInt("Enter the ItemID to be added to promotion: \n")));
						promoPack.setId(promoPack.getMenuObj().getItemID());
						promoPack.setQuantity(validateInt("Enter the Quantity of the items: "));
						promoPack.setPromoID(promotionList.getPromoByID(id).getPromoID());
						promotionList.getPromoByID(id).addMenuItem(promoPack);
					}
					System.out.println("The Items are added to the promotion successfully.");
					break;
				case 2:
					promotionList.getPromoByID(id).printpromoPackage();
					promotionList.getPromoByID(id).removeMenuItembyID(validateInt("Enter the ID of the item to be removed\n"));
					break;
				case 3:
					promotionList.getPromoByID(id).setPrice(validateFloatInput("Enter the new price of the promotion: "));
					System.out.println("Price updated Successfully");
					break;
				
				}
				}
				break;
			case 4: 
				promotionList.Display();
				break;	
			}
		break;
		case 3://create Order
			System.out.println("Select one of the following options: ");
			message = "1) Walk in Order\n2) Reserved table Order\n";
			int walkInOrRes =validateInt(message);
			if(walkInOrRes==1)
			{
			//create order for walk ins
			tableList.DisplayAvaliable();
			int tableID = validateInt("Please Enter the table ID\n");
			if (tableRange(tableList, tableID)==true && tableList.searchByID(tableID).getOrderID() == 0)//check for invalid table id
			{
			LocalDateTime timestamp = LocalDateTime.now();
			Order newOrder = new Order(timestamp,tableID,staff.getEmployeeID());
			orderList.add(newOrder);
			tableList.searchByID(tableID).setResID(0);
			tableList.searchByID(tableID).setOrderID(newOrder.getOrderID());
			tableList.searchByID(tableID).setReserved(true);//sets the table to reserved - reserved & resID = 0 = occupied
			menuList.Display();//menu items display
			System.out.println("\n\n\n");
			promotionList.Display();
			message = "\n1.Add from Promotions \n2.Add from Menu\n";
			switch(validateInt(message)) {
			case 1:
				message = "Enter the ID of the promotion that needs to be added\n";
				int id = validateInt(message);
				Promotion temp1_promotion = promotionList.getPromoByID(id);
				ArrayList<OrderItem> oItem = temp1_promotion.getPromoPackage();
				ArrayList <OrderItem> temp2_order = newOrder.getOrderedItems();
				
				//temp2_order.addAll(oItem);
				
				
				//Create a item for this addition of promo won't add to menuList
				//so it wont write to file	
				Item promoPackagedToItem = new Item();
				promoPackagedToItem.setItemID(temp1_promotion.getPromoID());
				promoPackagedToItem.setMenuName("Promotion set : " + promoPackagedToItem.getItemID());
				promoPackagedToItem.setType("Promotion set");
				promoPackagedToItem.setPrice(temp1_promotion.getPrice());
				promoPackagedToItem.setDescription("Promotion for: " + promoPackagedToItem.getItemID());
				//temp newOrderItem
				OrderItem newOrderItem = new OrderItem(promoPackagedToItem,1,newOrder.getOrderID());
				newOrderItem.setPromotionItem(true);
				//
				newOrder.getOrderedItems().add(newOrderItem);

				
				//newOrder.setOrderedItems(temp2_order);
				newOrder.addPromoPrice(temp1_promotion.getPrice());
				
				System.out.println("The promotion item added successfully\n");
						
				newOrder.setTotalPrice();
				break;
			case 2:
				addingItems(menuList,newOrder);//add items to your order
			break;
			
			}
			System.out.println("Successfully created a Order| Order ID : " + newOrder.getOrderID() 
			+ " Table ID: " + newOrder.getTableID());
			}
			}
			
			//create order for reserved
			else if(walkInOrRes==2)
			{
				if(resList.size()==0)
					System.out.println("There are no reservations.");
				else {
					int reservedWithoutOrders = 0;
				for(int i = 0;i<resList.size();i++)
				{
					//display all reserved that has not ordered
					if(tableList.searchByID(resList.get(i).getTableID()).getOrderID()==0)
					{
						System.out.println("Index : "+ i +" Reservation : " + resList.get(i).getResID() 
								+ " TableID : " + resList.get(i).getTableID());
						
						reservedWithoutOrders++;
					}
				}
				if(reservedWithoutOrders  == 0)
					{System.out.println("There are no reservations without orders.");
					break;}
				//reservations without orders continue
				int indexOpt = validateInt("Select the index of the reservation to create Order\n");
				LocalDateTime timestamp = LocalDateTime.now();
				Order newOrder = new Order(timestamp,resList.get(indexOpt).getTableID(),staff.getEmployeeID());
				orderList.add(newOrder);
				tableList.searchByID(resList.get(indexOpt).getTableID()).setOrderID(newOrder.getOrderID());
				tableList.searchByID(resList.get(indexOpt).getTableID()).setResID(resList.get(indexOpt).getResID());
				tableList.searchByID(resList.get(indexOpt).getTableID()).setReserved(true);
				promotionList.Display();
				message = "\n1. Add from Promtions \n2.Add from Menu";
				switch(validateInt(message)) {
				case 1:
					message = "Enter the ID of the promotion that needs to be added";
					int id = validateInt(message);
					Promotion temp1_promotion = promotionList.getPromoByID(id);
					ArrayList<OrderItem> oItem = temp1_promotion.getPromoPackage();
					ArrayList <OrderItem> temp2_order = newOrder.getOrderedItems();
					temp2_order.addAll(oItem);
					newOrder.setOrderedItems(temp2_order);
					newOrder.addPromoPrice(temp1_promotion.getPrice());
					System.out.println("The promotion item added successfully\n");
					break;
				case 2:
					addingItems(menuList,newOrder);
				break;
				}
				System.out.println("Successfully created a Order for Reserved ID : "+tableList.searchByID(newOrder.getTableID()).getResID() 
									+ "Order ID : "+newOrder.getOrderID() 
									+ " Table ID: " + newOrder.getTableID());
				//when reservation makes an order halt expiry.
				
				}
			
			}
			//add orderItems into Order
			//move to case 5
			break;
		case 4://display all order options
			if(orderList.size() == 0)
				System.out.println("There are no orders");
			else
			{
			for(int i = 0;i<orderList.size();i++)
			{
				System.out.println("Index : " + i + ") order ID "+ orderList.get(i).getOrderID() + " table ID" + orderList.get(i).getTableID());
			}
			int viewByIndex =validateInt("Please select the index to view the ordered items\n");
			//print each orderedItem of the selected order
			if(orderRange(orderList,viewByIndex)==true) {
			temp = orderList.get(viewByIndex);
			if(temp.getOrderedItems().size()!=0) {
				for(int i =0;i<temp.getOrderedItems().size();i++)
				{
					System.out.println("Item Name : "+temp.getOrderedItems().get(i).getMenuObj().getMenuName() +" \tQty: "+temp.getOrderedItems().get(i).getQuantity());
				}
			}
			else
				System.out.println("There are no ordered items for this order");
			viewByIndex = 0;
			temp = null;
			}
			}
			break;
		case 5://add or remove ordered items we assume its add and remove not update qty.
			if (orderList.size() == 0) {
				System.out.println("There are no orders to add! Create an order first!\n\n");
				break;
			}
			for(int i = 0;i<orderList.size();i++)
			{
				System.out.println("index : " + i + ") order ID "+ orderList.get(i).getOrderID() + " table ID" + orderList.get(i).getTableID());
			}
			int viewByIndex = validateInt("Please select the index to view list of the ordered items for particular order\n");
			
			if(orderRange(orderList,viewByIndex)==true) {
			temp = orderList.get(viewByIndex);//order obj
			
			//ask if add or remove.
			message = "1) Add \n2) Remove\n";
			int AddOrRemove = validateInt(message);
			viewByIndex = 0;//reset viewByIndex to reuse
			//Add
			if(AddOrRemove == 1) {
			
			menuList.Display();//menu items display
			promotionList.Display();// promotions items display 
			System.out.println("How would you like to add?");
			message = "1) Add from promotions \n2) Add from the Menu\n";
			switch (validateInt(message)) {
			case 1:
				message = "Enter the ID of the promotion that needs to be added";
				int id = validateInt(message);
				Promotion temp1_promotion = promotionList.getPromoByID(id);
				ArrayList<OrderItem> oItem = temp1_promotion.getPromoPackage();
				ArrayList <OrderItem> temp2_order = temp.getOrderedItems();
				
				Item promoPackagedToItem = new Item();
				promoPackagedToItem.setItemID(temp1_promotion.getPromoID());
				promoPackagedToItem.setMenuName("Promotion set : " + promoPackagedToItem.getItemID());
				promoPackagedToItem.setType("Promotion set");
				promoPackagedToItem.setPrice(temp1_promotion.getPrice());
				promoPackagedToItem.setDescription("Promotion for: " + promoPackagedToItem.getItemID());
				//temp newOrderItem
				OrderItem newOrderItem = new OrderItem(promoPackagedToItem,1,temp.getOrderID());
				newOrderItem.setPromotionItem(true);
				//temp.setOrderedItems(temp2_order);
				temp.getOrderedItems().add(newOrderItem);
				temp.addPromoPrice(temp1_promotion.getPrice());
				System.out.println("The promotion item added successfully\n");
				break;
			case 2:
				addingItems(menuList,temp);
				break;
			}temp.setTotalPrice();
			
			}
			else if(AddOrRemove == 2)//Removes the entire indexed row
			{
				
				do {
				if(temp.getOrderedItems().size()!=0) {
				for(int i =0;i<temp.getOrderedItems().size();i++)
				{
					System.out.println("index : " + i 
							+ "Item Name : "+temp.getOrderedItems().get(i).getMenuObj().getMenuName() 
							+" \tQty: "+temp.getOrderedItems().get(i).getQuantity() + " \t Promotion Item: "+ temp.getOrderedItems().get(i).isPromotionItem());
				}
				System.out.println("Enter 'q' to stop removing items.\n");
					System.out.println("Enter index of order item to be removed:\n");
					input = scan.next();
					if(!input.equalsIgnoreCase("q") && input.matches("[0-9]+")) {
					viewByIndex = Integer.parseInt(input);//to get item out of menu list
					System.out.println("Item has been removed from order.\n");
					temp.getOrderedItems().remove(viewByIndex);
					}
					else
						System.out.println("There are no order items for the order index you selected : " + temp.getOrderID());
				break;
				}
				}while(!input.equalsIgnoreCase("q"));
				
			}
			temp.setTotalPrice();
			}
			
			break;
		case 6:
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			DateTimeFormatter formatter_time = DateTimeFormatter.ofPattern("hh:mm a");
			System.out.println("Please enter the name of the customer: ");
			String name = scan.next();//i changed nextLine to next because the it caused a skip of input for name due to nextLine
			System.out.println("Enter the customer phonenumber: ");
			String num = scan.next();
			Customer customer = new Customer(name, num);
			//System.out.println("Please enter the time of arrival: ");
			
			scan.nextLine();		
			String res_time = validDate("Please enter the date of arrival(DD/MM/YYYY): ", new Scanner(System.in));
			LocalDate localDate = LocalDate.parse(res_time, formatter);
			LocalDate date1 = LocalDate.now();
			boolean val = true;
			while(val) {
				if (localDate.compareTo(date1.plusDays(30))<0) {
					val = false;
				}
				else {
					System.out.println("Reservations are only avaliable upto 30 days. Please choose an appropriate date!\n");
					res_time = validDate("Please enter the date of arrival(DD/MM/YYYY): ", new Scanner(System.in));
					localDate = LocalDate.parse(res_time, formatter);
				}
			}
			res_time = validTime("Enter the time of arrival(eg: 02:05 PM) : ", new Scanner(System.in));
			LocalTime localTime = LocalTime.parse(res_time.toUpperCase(), formatter_time);
			LocalDateTime time = LocalDateTime.of(localDate, localTime);
			Reservation res = new Reservation(time);
			System.out.println("ID of res : "+ res.getResID());
			do {
			num_people = validateInt("Enter the number of people for reservation (Max 10 people): \n");
			if(num_people < 1)
				System.out.println("Invalid number!");
			if(num_people>10)
				System.out.println("Sorry! You have exceeded the number of people our tables can accomodate.");
			}while(num_people>10 || num_people <1);
			
			res.addPropertyChangeListener(p);
			
			if ( num_people <=2 ) {
				tableList.viewFree2seaterTables();
				setReservation(res,today,time, afternoon, evening, num_people);
			}
			else if (num_people <=4) {
				tableList.viewFree4seaterTables();
				setReservation(res,today,time, afternoon, evening, num_people);
			}
			
			else if (num_people<=8) {
				tableList.viewFree8seaterTables();
				setReservation(res,today,time, afternoon, evening, num_people);
			}
			else {
				tableList.viewFree10seaterTables();
				setReservation(res,today,time, afternoon, evening, num_people);
				
			}
			res.setArrTime(time);
			res.setCustomer(customer);
			resList.add(res);
			System.out.println("Reservation created successfully for "+res.getCustomer().getName()+ " "+res.getArrTime().toString());
			break;
		case 7:
			int flag = 0;
			int res_choice = validateInt("1. Check Reservation \n2. Remove Reservations\n");
			if ( res_choice == 1) {
				System.out.println("Please enter the phonenumber of the customer");
				
				String phone_num = scan.next();
				for ( int i = 0;i< resList.size();i++) {
					if (resList.get(i).getCustomer().getPhoneNumber().equals(phone_num)) {
						System.out.println("Reservation is under"+ resList.get(i).getCustomer().getName()+	" Arrival Time: " +resList.get(i).getArrTime().toString()+" Expired: "+ resList.get(i).isExpired());
						flag =1;
					}
				}
				
			}
			else if(res_choice == 2) {
				System.out.println("Please enter the phonenumber of the customer");
				String phone_num = scan.nextLine();
				for (int i = 0; i<resList.size();i++) {
					if(resList.get(i).getCustomer().getPhoneNumber().contentEquals(phone_num)) {
						
						flag = 1;
						System.out.println("Reservation removed Successfully.");
						tableList.searchByID(resList.get(i).getTableID()).setResID(0);
						tableList.searchByID(resList.get(i).getTableID()).setReserved(false);
						resList.remove(i);
					}
				}
			}
			else {
				System.out.println("Please enter a valid choice");
				break;
			}
			if (flag == 0) {
				System.out.println("No Reservation exists on given phone number");
			}
		break;
		case 8:
			tableList.Display();
			System.out.println(tableList.getSeater10().size());
			System.out.println(tableList.getSeaters10Size() + tableList.getSeaters8Size() + tableList.getSeaters4Size() + tableList.getSeaters2Size());
			break;
		case 9:// Print bill invoice for particular order
			double amtPaid;
			if(orderList.size() == 0)
				System.out.println("There are no orders");
			else
			{
			for(int i = 0;i<orderList.size();i++)
			{
				System.out.println("index : " + i + ") order ID "+ orderList.get(i).getOrderID() + " table ID" + orderList.get(i).getTableID());
			}
			Payment forDisplay = new Payment();
			viewByIndex = validateInt("Please select the order you would like to print invoice for: \n");
			//print each orderedItem of the selected order
			if(orderRange(orderList,viewByIndex)==true) {
			temp = orderList.get(viewByIndex);//order obj
			//temp.printBill(tableList.searchByID(temp.getTableID()), staff);//print the bill for order
			double amtToPay = temp.getTotalPrice()*(1+forDisplay.getGST());
			System.out.println("Total: " + String.format("%.2f",amtToPay));
			//haven't change displaying.
			message = "Pay by: \n1)Cash \n2)Card\n";
			
			int byCash = validateInt(message);
			boolean isCash = true;
			if(byCash == 1) {
				isCash = true;
			}
				else
				isCash = false;
			if(isCash ==true) {
			amtPaid = validateFloatInput("Enter amount paid:");
			while(amtPaid < amtToPay)
			{
				message = "Lacking payment of : " +String.format("%.2f", amtToPay-amtPaid) + "\nEnter Amount Paid : ";
				amtPaid = validateFloatInput(message);
			}
			}
			else
				amtPaid = amtToPay; //Since by credit card, amnt paid is fulfilled
			temp.printBill(tableList.searchByID(temp.getTableID()), staff);//print the bill for order
			LocalDateTime timestamp = LocalDateTime.now();
			Payment p = temp.checkout(timestamp, amtPaid, isCash, temp.getTotalPrice());
			System.out.println("\tChange given is : " + String.format("%.2f", p.getChange()));//
			SaleRevenue rev = new SaleRevenue(timestamp, temp.getOrderID(), temp.getOrderedItems(), p.getTotal());
			for (int i = 0;i<orderList.size();i++) {   // Handling of the order removal;
				if (orderList.get(i).getOrderID() == temp.getOrderID()) {
					tableList.searchByID(temp.getTableID()).setOrderID(0);
					tableList.searchByID(temp.getTableID()).setReserved(false);
					for(int j =0;j<resList.size();j++)
					{
						if(resList.get(j).getResID()==tableList.searchByID(temp.getTableID()).getResID());
						resList.remove(resList.get(j));
					}
					orderList.remove(i);
				}
			}
			revenueList.add(rev);
			viewByIndex = 0;
			temp = null;
			}
			else {
				System.out.println("Please enter a correct orderID.");
			}
			}
			break;
		case 10:
			
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			message = "1. for SaleRevenue by date\n"
					+ "2. for SaleRevenue by month\n";
			ArrayList<OrderItem> sale_temp;
			double total = 0;
			switch(validateInt(message)) {
			case 1:
				String date = validDate("Enter the date(dd/mm/yyyy): ", new Scanner(System.in));
				//if(validDate(date)==true) {
				LocalDate d = LocalDate.parse(date,formatter1);
				
				//revenueList = (ArrayList<SaleRevenue>)ReadAllItemsObjFromFile(payFilePath);
				for (int i = 0; i< revenueList.size();i++) {
					if (revenueList.get(i).getDate().toLocalDate().equals(d)) {
						System.out.println("Order ID : "+ revenueList.get(i).getOrderId());
						sale_temp = revenueList.get(i).getItems();
						for (int j = 0; j<sale_temp.size();j++) {
							System.out.println("Item: "+ sale_temp.get(j).getMenuObj().getMenuName()+" Quantity: " + sale_temp.get(j).getQuantity());
						}
						System.out.println("Total revenue made by this order :" +String.format("%.2f",revenueList.get(i).getTotalRevenue()));
						total+= revenueList.get(i).getTotalRevenue();
					}
				}
				System.out.println("The total Revenue made on "+ date+"is "+total);
				//}
				break;
			case 2:
				int month = validateInt("Enter the month(mm): \n");
				if(month >=1 && month<=12) {
				for (int i = 0; i< revenueList.size();i++) {
					if(revenueList.get(i).getDate().toLocalDate().getMonthValue() == month) {
						System.out.println("Order ID : "+ revenueList.get(i).getOrderId());
						sale_temp = revenueList.get(i).getItems();
						for (int j = 0; j<sale_temp.size();j++) {
							System.out.println("Item: "+ sale_temp.get(j).getMenuObj().getMenuName()+" Quantity: " + sale_temp.get(j).getQuantity());
						}
						System.out.println("Total revenue made by this order :" +revenueList.get(i).getTotalRevenue());
						total+= revenueList.get(i).getTotalRevenue();
						
					}
					
				}
				System.out.println("The total Revenue made on "+ month +" month is "+total);
				}
				break;
			}
		}
		}while(choice != 11);
		menuList.WriteAllItemsObjToFile();
		WriteAllItemsObjToFile(resList);
		WriteAllItemsObjToFile(revenueList);
		
		promotionList.WriteAllobjectsFromFile();
		System.out.println("Exiting Program...");
		System.out.println("Successfully exited Program");
		scan.close();
	
	}

	/**
	 * Method writes all items obj from parsed ArrayList. It will separate the type of ArrayList item objects to be written,
	 * based on the filePath set for the path of the serialized file
	 *
	 * @param List the list
	 * 
	 * The ArrayList makes use of reading Serialized files
	 * @see java.io.Serializable
	 */
	public static void WriteAllItemsObjToFile(ArrayList<?> List) {

		try {	if (List.size()==0) {
						return;
					}
				if (List.get(0) instanceof Reservation) {
					FileOutputStream fileOut = new FileOutputStream(resFilePath);
					ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
					objectOut.writeObject(List);
					objectOut.close();
					System.out.println("The Reservations were succesfully written to a file");
				}
				else {
					FileOutputStream fileOut = new FileOutputStream(payFilePath);
					ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
					objectOut.writeObject(List);
					objectOut.close();
					System.out.println("The SaleRevenue has been written successfully");
				}				
				
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

	/**
	 * Method Read all items obj from file. It will separate the type of ArrayList item objects to be read,
	 * based on the filePath set for the serialized lists given
	 *
	 * @param filePath the file path
	 * @return the array list
	 * 
	 * The ArrayList makes use of reading Serialized files
	 * @see java.io.Serializable
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<?> ReadAllItemsObjFromFile(String filePath) {
		try {
		
			ArrayList<?> resList;
			FileInputStream fileIn = new FileInputStream(filePath);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			if (filePath.contentEquals(resFilePath)) {
				resList = (ArrayList<Reservation>) objectIn.readObject();
				System.out.println("The Reservations have been read from the file");
				objectIn.close();
				return resList;
			}
			else {
				ArrayList<SaleRevenue> resList1 = new ArrayList<SaleRevenue>();
				resList1 = (ArrayList<SaleRevenue>) objectIn.readObject();
				System.out.println("The SaleRevenues have been read from the file");
				objectIn.close();
				return resList1;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method for controlling the actions and validation of adding menu items to a order.
	 * this method will loop until user keys in 'q' as the option when prompted to add.
	 * 
	 * @param menuList the menu list
	 * @param newOrder the new order
	 */
	private static void addingItems(MenuList menuList, Order newOrder) {
		String input;
		Scanner scan = new Scanner(System.in);
		do {
		System.out.println("\nEnter q to to stop adding items.");
		System.out.println("Please select the item ID to add");
	
		input = scan.next();//to get item out of menu list
		if(!input.equalsIgnoreCase("q")&& input.matches("[0-9]+")) {
			int viewByIndex = Integer.parseInt(input);
			if(itemIDRange(menuList,viewByIndex) == true) {
			System.out.println("Please enter quantity");
			input = scan.next();
			if(!input.equalsIgnoreCase("q") && input.matches("[0-9]+")) {
			int orderItemQty = Integer.parseInt(input);//qty to set
			OrderItem newOrderItem = new OrderItem(menuList.getItemByItemID(viewByIndex),orderItemQty,newOrder.getOrderID());
			newOrder.getOrderedItems().add(newOrderItem);//add new OrderItem obj into arrayList<OrderItem> of Order Obj temp.
			System.out.println("Successfully Added an Item " + newOrderItem.getMenuObj().getMenuName() 
					+ " Qty: " +newOrderItem.getQuantity());
		}
			}
		}
		else if(!input.equalsIgnoreCase("q"))
			System.out.println("Invalid Input!");
	}while(!input.equalsIgnoreCase("q"));
		newOrder.setTotalPrice();
	}
	
	/**
	 * Sets the a reservation id to the table object and sets the timing of the table reserved according to, Morning/Noon/evening
	 * 
	 * @param res the res
	 * @param today the today
	 * @param time the time
	 * @param afternoon the afternoon
	 * @param evening the evening
	 * @param numPpl the num ppl
	 */
	private static void setReservation(Reservation res,LocalDateTime today, LocalDateTime time, int afternoon, int evening, int numPpl)
	{
		//grouped similar code to this function
		int id_table;
		id_table = validateInt("Enter the ID of the Table to be Reserved\n");
		res.setTableID(id_table);
		if (today.toLocalDate().equals(time.toLocalDate())) {
			if ((today.toLocalTime().getHour()< afternoon && time.toLocalTime().getHour()<afternoon) || (today.toLocalTime().getHour()> evening && time.toLocalTime().getHour()>evening)) {
				
				if(numPpl <=2)
				{
					tableList.getSeater2ByTableID(id_table).setReserved(true);
					tableList.getSeater2ByTableID(id_table).setResID(res.getResID());
					tableList.getSeater2ByTableID(id_table).setOrderID(0);
				}
				else if(numPpl<=4)
				{
					tableList.getseater4ByTableID(id_table).setReserved(true);
					tableList.getseater4ByTableID(id_table).setResID(res.getResID());
					tableList.getseater4ByTableID(id_table).setOrderID(0);
				}
				else if(numPpl<=8)
				{
					tableList.getSeater8ByTableID(id_table).setReserved(true);
					tableList.getSeater8ByTableID(id_table).setResID(res.getResID());
					tableList.getSeater8ByTableID(id_table).setOrderID(0);
				}
				else
				{
					tableList.getSeater10ByTableID(id_table).setReserved(true);
					tableList.getSeater10ByTableID(id_table).setResID(res.getResID());
					tableList.getSeater10ByTableID(id_table).setOrderID(0);
				}
				
			}
		}
	}
	
	/**
	 * Validate float input.
	 * Method to validate if scanner input is of integer from conversion of String
	 * returns the float if is of float number.
	 * @param message the message
	 * @return the double
	 */
	private static double validateFloatInput(String message) {
		Scanner scan  = new Scanner(System.in);
		Double value = null;
			do
			{
				//Error handling for inputs
				System.out.println(message); //will print the prompted message to user
				String input = scan.nextLine();
				try {
	                value = Double.parseDouble(input);
	            } catch (NumberFormatException exp) {
	                System.out.println("Error! Invalid input.");
	            }
				
			}while(value == null);
		return value;
		}
	
	/**
	 * Validate int.
	 * Method to validate if scanner input is of integer from conversion of String
	 * returns the int if is of integer number.
	 * @param message the message
	 * @return the int
	 */
	private static int validateInt(String message) {
		Scanner scan = new Scanner(System.in);
	    Integer value = null;
	    //Error handling for inputs
	    do {
	        System.out.print(message);
	        String input = scan.nextLine();

	            try {
	                value = Integer.parseInt(input);
	            } catch (NumberFormatException exp) {
	                System.out.println("Error! Invalid input.");
	            }
	        
	    } while (value == null);
	    return value;
	}
	
	/**
	 * Item ID range.
	 * Method to check the if the parsed id is within the parsed menuList
	 * @param menuList the menuList
	 * @param id the id
	 * @return true, if successful
	 */
	private static boolean itemIDRange(MenuList menuList, int id)
	{	//check if the id is within the list
		for(int i=0;i<menuList.getSize();i++)
			if(menuList.getItem(i).getItemID() == id)
				return true;
		
		System.out.println("Invalid Item ID!");
		return false;
	}
	
	/**
	 * Item ID promo.
	 * Method to check the if the parsed id is within the parsed PromotionList
	 * @param pList the PromotionList
	 * @param id the id
	 * @return true, if successful
	 */
	private static boolean itemIDPromo(PromotionList pList, int id)
	{
		Promotion p = pList.getPromoByID(id);
		if(p == null)
			{System.out.println("Invalid Item ID!");
			return false;}
		else
		{
			return true;
		}
	}
	
	/**
	 * Table range.
	 * Method to check the if the parsed id is within the parsed tableList
	 * @param tableList the tableList
	 * @param id the id
	 * @return true, if successful
	 */
	private static boolean tableRange(TableList tableList, int id)
	{
		//check if the id is within the range of the list
		for(int i=0;i<tableList.getSeaters2Size();i++)
			if(tableList.getSeater2().get(i).getTableID() == id || tableList.getSeater4().get(i).getTableID() == id)
				return true;
		for(int i=0;i<tableList.getSeaters8Size();i++)
			if(tableList.getSeater8().get(i).getTableID() == id || tableList.getSeater10().get(i).getTableID() == id)
				return true;
		System.out.println("No such table exist!");
		return false;
	}
	
	/**
	 * Order range.
	 * Method to check the if the parsed index is within the parsed orderList
	 * @param orderList the orderList
	 * @param index the index
	 * @return true, if successful
	 */
	private static boolean orderRange(ArrayList<Order> orderList, int index)
	{	//check if the id is within the range of the list
		for(int i=0;i<orderList.size();i++)
			if(index == i)
				return true;
		
		return false;	
	}
	
	/**
	 * Valid date.
	 *
	 * Method that validate the input from scanner string value to the Date format {DD/MM/YYYY}
	 * @see java.text.SimpleDateFormat
	 * 
	 * @param message the message
	 * @param scan the scan
	 * @return the string
	 */
	private static String validDate(String message, Scanner scan)
	{
		//handle invalid inputs of date
		String date;
		boolean state = false;
		do {
			System.out.println(message);
			date = scan.next();
			
			String arrdate [] = date.split("/", 3);
			int day = Integer.parseInt(arrdate[0]);
			int month = Integer.parseInt(arrdate[1]);
			int year = Integer.parseInt(arrdate[2]);
			
		if(year>=2019) { 
			if(month>=1 && month<=12)
			{
				if(month==2 && year%4 == 0) { //checking for Feb in Leap year
					if(day>=1 && day<=29)
						state = true;
					}
				else if (month==2) { // checking for Feb in normal years
					if(day>=1 && day<=28)
						state =  true;
					else
						System.out.println("Invalid entered day for this month.");
				}
				else if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
					{if (day>=1 && day<=31) //check for the months with 31 days
						state =  true;
					else
						System.out.println("Invalid entered day for this month.");
					}
				else
				{
					if(day>=1 && day<=30)
						state =  true;
					else
						System.out.println("Invalid entered day for this month.");
				}
			
			}
			else
				System.out.println("Invalid month entered.");
		}
		else
			System.out.println("The year you have entered has passed! Try again.");
		}
		while(state == false);
		
		return date;
	}
	
	/**
	 * Valid time.
	 *
	 * @param message the message
	 * @param scan the scan
	 * @return the string
	 * 
	 * Method that validate the input from scanner string value to the time format {hh:mm:a}
	 * @see java.text.SimpleDateFormat
	 */
	private static String validTime(String message,Scanner scan)
	{
		String time;
		boolean state = false;
		do {
		System.out.println(message);
		time = scan.nextLine();
		String arrTime [] = time.split(":", 2); //split hour from min and AM/PM indicator
		String arrTime2 [] = arrTime[1].split(" ", 2);//split min from AM/PM indicator
		int hour = Integer.parseInt(arrTime[0]);
		int min = Integer.parseInt(arrTime2[0]);
		String zone = arrTime2[1];
		if(zone.equalsIgnoreCase("am")) {
		if(hour==11)
		{
			if(min>=0 && min <=59)
				state = true;
			else
				System.out.println("Invalid entry for minutes.");
		}
		else
			System.out.println("Invalid entry for hour. Restaurant operates from (11am to 3pm) and (6 to 10pm)");
		}
		else if(zone.equalsIgnoreCase("pm"))
		{
			if((hour>=6 && hour <=10)|| (hour>=1 && hour <= 3) || hour ==12) {
				if(min>=0 && min <=59)
					state = true;
				else
					System.out.println("Invalid entry for minutes.");
			}
			else
				System.out.println("Invalid entry for hour. Restaurant operates from (11am to 3pm) and (6 to 10pm)");
		}
		else
			System.out.println("Invalid indicator. Please enter AM or PM");
		}while(state==false);
		
		return time;
	}
}
	

