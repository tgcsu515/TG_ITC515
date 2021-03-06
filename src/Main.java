import java.text.SimpleDateFormat;
import java.util.Scanner;

/*The author for the following Main class is Kasun Amarasinghe.
  Kanchan Bala will review the code updates which were done to this file.
  Arashdeep Kuar is the mediator for this class file.
  This class file will be reviewed using the given Code Style Guidelines and necessary code updates will be done by the Author.
*/

public class Main {

    private static Scanner input; //Changed the varible name "IN" to "input" to be meaningful-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala 
    private static Library libraryObj; //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
    private static String mainMenu; //Updated the varible name "MENU" to "mainMenu"-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
    private static Calendar calendarObj; //Renamed the varible name "CAL" to "calendarObj" to be meaningful-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
    private static SimpleDateFormat dateFormat; //Changed the varible name "SDF" to "dateFormat"-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala 

    private static String get_menu() {// Chaged the first letter to lowercase in the method name-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
        StringBuilder stringBuilderObj = new StringBuilder();

        stringBuilderObj.append("\nLibrary Main Menu\n\n")
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

        return stringBuilderObj.toString();
    }

    public static void main(String[] args) {
        try {
            input = new Scanner(System.in); //Changed the varible name "IN" to "input" to be meaningful-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
            libraryObj = Library.INSTANCE(); //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
            calendarObj = Calendar.getInstance(); //Renamed the varible name "CAL" to "calendarObj" to be meaningful-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
            dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //Changed the varible name "SDF" to "dateFormat"-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala 

            //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
            for (Member currentMember : libraryObj.Members()) {// Chaged the first letter to uppercase in Member class name-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
                output(m);
            }
            output(" ");
            //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
            for (Book currentBook : libraryObj.Books()) { // Chaged the first letter to uppercase in Book class name-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
                output(b);
            }

			//Updated the varible name "MENU" to "mainMenu"-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
            mainMenu = get_menu();// Chaged the first letter to lowercase in the method name-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala

            boolean e = false;

            while (!e) {
                //Changed the varible name "SDF" to "dateFormat"-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala 
                output("\n" + dateFormat.format(calendarObj.Date())); //Renamed the varible name "CAL" to "calendarObj" to be meaningful-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
                String c = input(mainMenu); //Updated the varible name "MENU" to "mainMenu"-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala

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
		// Chaged the first letter to uppercase in Loan class name-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
        for (Loan loan : libraryObj.CurrentLoans()) { //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
            output(loan + "\n");
        }
    }

    private static void listBooks() {
        output("");
		// Chaged the first letter to uppercase in Book class name-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
        for (Book book : libraryObj.Books()) { //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
            output(book + "\n");
        }
    }

    private static void listMembers() {
        output("");
		// Chaged the first letter to uppercase in Member class name-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
        for (Member member : libraryObj.Members()) { //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
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
            calendarObj.incrementDate(days); //Renamed the varible name "CAL" to "calendarObj" to be meaningful-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
            libraryObj.checkCurrentLoans(); //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
            //Changed the varible name "SDF" to "dateFormat"-Author Kasun Amarasinghe 
            output(dateFormat.format(calendarObj.Date())); //Renamed the varible name "CAL" to "calendarObj" to be meaningful-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala

        } catch (NumberFormatException e) {
            output("\nInvalid number of days\n");
        }
    }

    private static void addBook() {

        String author = input("Enter author: ");
        String title = input("Enter title: ");
        String callNo = input("Enter call number: ");
		// Chaged the first letter to uppercase in Book class name-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
        Book book = libraryObj.Add_book(author, title, callNo); //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
        output("\n" + book + "\n");

    }

    private static void addMember() {
        try {
            String lastName = input("Enter last name: ");
            String firstName = input("Enter first name: ");
            String email = input("Enter email: ");
            int phoneNo = Integer.valueOf(input("Enter phone number: ")).intValue();
			// Chaged the first letter to uppercase in Member class name-Author Kasun Amarasinghe
            Member member = libraryObj.Add_mem(lastName, firstName, email, phoneNo); //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
            output("\n" + member + "\n");

        } catch (NumberFormatException e) {
            output("\nInvalid phone number\n");
        }
    }

    private static String input(String prompt) {
        System.out.print(prompt);
        return input.nextLine(); //Changed the varible name "IN" to "input" to be meaningful-Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
    }

    private static void output(Object object) {
        System.out.println(object);
    }

}