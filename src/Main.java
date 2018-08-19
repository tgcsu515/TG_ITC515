import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

    private static Scanner input; //Changed the varible name "IN" to "input" to be meaningful-Author Kasun Amarasinghe 
    private static Library libraryObj; //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
    private static String mainMenu; //Updated the varible name "MENU" to "mainMenu"-Author Kasun Amarasinghe
    private static Calendar calendarObj; //Renamed the varible name "CAL" to "calendarObj" to be meaningful-Author Kasun Amarasinghe
    private static SimpleDateFormat dateFormat; //Changed the varible name "SDF" to "dateFormat"-Author Kasun Amarasinghe 

    private static String Get_menu() {
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
            input = new Scanner(System.in); //Changed the varible name "IN" to "input" to be meaningful-Author Kasun Amarasinghe
            libraryObj = Library.INSTANCE(); //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
            calendarObj = Calendar.getInstance(); //Renamed the varible name "CAL" to "calendarObj" to be meaningful-Author Kasun Amarasinghe
            dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //Changed the varible name "SDF" to "dateFormat"-Author Kasun Amarasinghe 

            //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
            for (Member currentMember : libraryObj.Members()) {// Chaged the first letter to uppercase in Member class name-Author Kasun Amarasinghe
                output(m);
            }
            output(" ");
            //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
            for (Book currentBook : libraryObj.Books()) { // Chaged the first letter to uppercase in Book class name-Author Kasun Amarasinghe
                output(b);
            }

            mainMenu = Get_menu(); //Updated the varible name "MENU" to "mainMenu"-Author Kasun Amarasinghe

            boolean e = false;

            while (!e) {
                //Changed the varible name "SDF" to "dateFormat"-Author Kasun Amarasinghe 
                output("\n" + dateFormat.format(calendarObj.Date())); //Renamed the varible name "CAL" to "calendarObj" to be meaningful-Author Kasun Amarasinghe
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
		// Chaged the first letter to uppercase in Loan class name-Author Kasun Amarasinghe
        for (Loan loan : libraryObj.CurrentLoans()) { //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
            output(loan + "\n");
        }
    }

    private static void listBooks() {
        output("");
		// Chaged the first letter to uppercase in Book class name-Author Kasun Amarasinghe
        for (Book book : libraryObj.Books()) { //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
            output(book + "\n");
        }
    }

    private static void listMembers() {
        output("");
		// Chaged the first letter to uppercase in Member class name-Author Kasun Amarasinghe
        for (Member member : libraryObj.Members()) { //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
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
            //Changed the varible name "SDF" to "dateFormat"-Author Kasun Amarasinghe 
            output(dateFormat.format(calendarObj.Date())); //Renamed the varible name "CAL" to "calendarObj" to be meaningful-Author Kasun Amarasinghe

        } catch (NumberFormatException e) {
            output("\nInvalid number of days\n");
        }
    }

    private static void addBook() {

        String author = input("Enter author: ");
        String title = input("Enter title: ");
        String callNo = input("Enter call number: ");
		// Chaged the first letter to uppercase in Book class name-Author Kasun Amarasinghe
        Book book = libraryObj.Add_book(author, title, callNo); //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
        output("\n" + book + "\n");

    }

    private static void addMember() {
        try {
            String lastName = input("Enter last name: ");
            String firstName = input("Enter first name: ");
            String email = input("Enter email: ");
            int phoneNo = Integer.valueOf(input("Enter phone number: ")).intValue();
			// Chaged the first letter to uppercase in Member class name-Author Kasun Amarasinghe
            Member member = libraryObj.Add_mem(lastName, firstName, email, phoneNo); //Renamed the varible name "LIB" to "libraryObj" to be meaningful-Author Kasun Amarasinghe
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