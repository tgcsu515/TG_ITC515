import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner input; //Changed the varible name "IN" to "input" to be meaningful-Author Kasun Amarasinghe 
	private static Library libraryObj; //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
	private static String mainMenu; //Updated the varible name "MENU" to "mainMenu"-Author Kasun Amarasinghe
	private static Calendar calendarObj; //Renamed the varible name "CAL" to "calendarObj" to be meaningful-Author Kasun Amarasinghe
	private static SimpleDateFormat SDF;
	
	
	private static String Get_menu() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {			
			input = new Scanner(System.in); //Changed the varible name "IN" to "input" to be meaningful-Author Kasun Amarasinghe
			libraryObj = Library.INSTANCE(); //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
			calendarObj = Calendar.getInstance(); //Renamed the varible name "CAL" to "calendarObj" to be meaningful-Author Kasun Amarasinghe
			SDF = new SimpleDateFormat("dd/MM/yyyy");
			
			//Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
			for (member m : libraryObj.Members()) {
				output(m);
			}
			output(" ");
			//Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
			for (book b : libraryObj.Books()) {
				output(b);
			}
						
			mainMenu = Get_menu(); //Updated the varible name "MENU" to "mainMenu"-Author Kasun Amarasinghe
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + SDF.format(calendarObj.Date())); //Renamed the varible name "CAL" to "calendarObj" to be meaningful-Author Kasun Amarasinghe
				String c = input(mainMenu); //Updated the varible name "MENU" to "mainMenu"-Author Kasun Amarasinghe
				
				switch (c.toUpperCase()) {
				
				case "M": 
					addMember();
					break;
					
				case "LM": 
					listMembers();
					break;
					
				case "B": 
					addBook();
					break;
					
				case "LB": 
					listBooks();
					break;
					
				case "FB": 
					fixBooks();
					break;
					
				case "L": 
					borrowBook();
					break;
					
				case "R": 
					returnBook();
					break;
					
				case "LL": 
					listCurrentLoans();
					break;
					
				case "P": 
					payFine();
					break;
					
				case "T": 
					incrementDate();
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				Library.SAVE();
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

		private static void payFine() {
		new PayFineUI(new PayFineControl()).run();		
	}


	private static void listCurrentLoans() {
		output("");
		for (loan loan : libraryObj.CurrentLoans()) { //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
			output(loan + "\n");
		}		
	}



	private static void listBooks() {
		output("");
		for (book book : libraryObj.Books()) { //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
			output(book + "\n");
		}		
	}



	private static void listMembers() {
		output("");
		for (member member : libraryObj.Members()) { //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
			output(member + "\n");
		}		
	}



	private static void borrowBook() {
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void returnBook() {
		new ReturnBookUI(new ReturnBookControl()).run();		
	}


	private static void fixBooks() {
		new FixBookUI(new FixBookControl()).run();		
	}


	private static void incrementDate() {
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			calendarObj.incrementDate(days); //Renamed the varible name "CAL" to "calendarObj" to be meaningful-Author Kasun Amarasinghe
			libraryObj.checkCurrentLoans(); //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
			output(SDF.format(calendarObj.Date())); //Renamed the varible name "CAL" to "calendarObj" to be meaningful-Author Kasun Amarasinghe
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() {
		
		String author = input("Enter author: ");
		String title  = input("Enter title: ");
		String callNo = input("Enter call number: ");
		book book = libraryObj.Add_book(author, title, callNo); //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
		output("\n" + book + "\n");
		
	}

	
	private static void addMember() {
		try {
			String lastName = input("Enter last name: ");
			String firstName  = input("Enter first name: ");
			String email = input("Enter email: ");
			int phoneNo = Integer.valueOf(input("Enter phone number: ")).intValue();
			member member = libraryObj.Add_mem(lastName, firstName, email, phoneNo); //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
			output("\n" + member + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine(); //Changed the varible name "IN" to "input" to be meaningful-Author Kasun Amarasinghe
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
