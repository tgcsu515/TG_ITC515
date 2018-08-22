
/* 
The author for the Library.java class is Amandeep kaur
The other members of the team will review the code updates which were done by the author to this file
The mediator for the Library.java file is Kanchan Bala
This class file will be reviewed according to the code style guidelines and necessary updation to the code  will be done by the author Amandeep Kaur
   */ 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Library implements Serializable { //Author Amandeep kaur change the first letter of the class from lower to upper case  Reviewed By Kasun Amarsinghe
	
	private static final String LIBRARY_FILE = "library.obj";
	private static final int LOAN_LIMIT = 2;
	private static final int LOAN_PERIOD = 2;
	private static final double FINE_PER_DAY = 1.0;
	private static final double MAX_FINES_OWED = 5.0;
	private static final double DAMAGE_FEE = 2.0;
	
	private static Library self; // Author Amandeep Kaur change the first letter of the class from lower to upper case  Reviewed By Kasun Amarsinghe
	private int bookID;
	private int memberID;
	private int loanID;// // Author Amandeep Kaur update the variable name LID to loan ID
	private Date loadDate;
	
	private Map<Integer, Book> catalog;// Author Amandeep Kaur change the first letter of the class Book from lower to upper case Reviewed By Kasun Amarsinghe
	private Map<Integer, Member> members;// Author Amandeep Kaur change the first letter of the class Member from lower to upper case Reviewed By Kasun Amarsinghe
	private Map<Integer, Loan> loans;// Author Amandeep Kaur change the first letter of the class from Loan lower to upper case Reviewed By Kasun Amarsinghe
	private Map<Integer, Loan> currentLoans;//  Author Amandeep Kaur change the first letter of the class from  Loan lower to upper case Reviewed By Kasun Amarsinghe
	private Map<Integer, Book> damagedBooks;// Author Amandeep Kaur change the first letter of the class from  Book lower to upper case Reviewed By Kasun Amarsinghe
	

	private Library() { //  Author Amandeep Kaur change the constructor name from lower to upper case Reviewed By Kasun Amarsinghe
		catalog = new HashMap<>();
		members = new HashMap<>();
		loans = new HashMap<>();
		currentLoans = new HashMap<>();
		damagedBooks = new HashMap<>();
		bookID = 1; // author Amandeep Kaur update the variable name BID to bookID
		memberID = 1;	// author Amandeep Kaur update the variable name MID to memberID
		loanID = 1;		// author Amandeep Kaur update the variable name LID to loanID
	} 

	
	public static synchronized Library instance() {		// author Amandeep Kaur  change the class name from lower to upper case and method name from capital to lower Reviewed By Kasun Amarsinghe
		if (self == null) {
			Path path = Paths.get(LIBRARY_FILE);			
			if (Files.exists(path)) {	
				try (ObjectInputStream lenghthOfFile = new ObjectInputStream(new FileInputStream(LIBRARY_FILE));) { // Author Amandeep Kaur update the object name lof to lengthOfFile
			    
					self = (library) lenghthOfFile.readObject();
					Calendar.getInstance().setDate(self.loadDate);
					lenghthOfFile.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new Library();//author Amandeep kaur change the class name from lower to upper case Reviewed By Kasun Amarsinghe
		}
		return self;
	}

	
	public static synchronized void SAVE() {
		if (self != null) {
			self.loadDate = Calendar.getInstance().Date();
			try (ObjectOutputStream lenghthOfFile = new ObjectOutputStream(new FileOutputStream(LIBRARY_FILE));) {
				lenghthOfFile.writeObject(self); // Author Amandeep Kaur update the object name lof to lengthOfFile
				lenghthOfFile.flush();
				lenghthOfFile.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int bookID() // author Amandeep Kaur change the method  first letter capital to lower Reviewed By Kasun Amarsinghe
	{
		return bookID; // author Amandeep Kaur update the variable name BID to bookID
	}
	
	
	public int memberID() //author Amandeep Kaur change the method  first letter capital to lower Reviewed By Kasun Amarsinghe
	{ 
		return memberID; // author Amandeep Kaur update the variable name MID to memberID
	}
	
	
	private int nextBookID() // author Amandeep Kaur update the method name nextBID to nextBookID
	{
		return bookID++; //author Amandeep Kaur update the variable name BID to bookID
	}

	
	private int nextMemberID() { // author Amandeep Kaur update the method name nextMID to nextMemberID
		return memberID++; // author Amandeep Kaur update the variable name MID to memberID
	}

	
	private int nextLoanID() { // author Amandeep Kaur update the method name nextLID to nextLoanID
		return loanID++;// author Amandeep Kaur update the variable name LID to loanID
	}

	
	public List<Member> Members() {		// author Amandeep Kaur capatalize the member class to Member
		return new ArrayList<Member>(members.values()); 
	}


	public List<Book> Books() {		// author Amandeep Kaur capatalize the  book  class to Book
		return new ArrayList<Book>(catalog.values()); 
	}


	public List<Loan> CurrentLoans() { // author Amandeep Kaur capatalize  the loan to Loan
		return new ArrayList<Loan>(currentLoans.values());
	}


	public Member addMember(String lastName, String firstName, String email, int phoneNo) // author Amandeep kaur change the method name and class Member first letter lower to upper Reviewed By Kasun Amarsinghe
	{		
		Member member = new Member(lastName, firstName, email, phoneNo, nextMID());
		members.put(member.getId(), member);		
		return member;
	}

	
	public Book addBook(String author, String title, String callNo) // author Amandeep Kaur change the method name  and argument variables  according to the naming convention to camel case Reviewed By Kasun Amarsinghe
	{		
		Book currentBook = new Book(author, title, callNo, nextBID());// author Amandeep Kaur capatalize  the Book class  from lower as reviewed by the Kasun Amarsinghe
		catalog.put(currentBook.ID(), currentBook);		// update the object name according to the naming convention
		return currentBook;
	}

	
	public Member getMember(int memberId) // author Amandeep Kaur change the class name first letter from lower to upper Reviewed By Kasun Amarsinghe
	{
		if (members.containsKey(memberId)) 
			return members.get(memberId);
		return null;
	}

	
	public Book book(int bookId) // Author Amandeep Kaur change the first letter of the class Book from lower to upper case and method name from upper to lower Reviewed By Kasun Amarsinghe
	{
		if (catalog.containsKey(bookId)) 
			return catalog.get(bookId);		
		return null;
	}

	
	public int loanLimit() {
		return LOAN_LIMIT;
	}

	
	public boolean memberCanBorrow(Member member) // Author Amandeep Kaur change the first letter of the class Member from lower to upper case Reviewed By Kasun Amarsinghe
	{		
		if (member.getNumberOfCurrentLoans() == LOAN_LIMIT ) 
			return false;
				
		if (member.getFinesOwed() >= MAX_FINES_OWED) 
			return false;
				
		for (Loan loan : member.getLoans()) // Author Amandeep Kaur change the first letter of the class Loan from lower to upper case Reviewed By Kasun Amarsinghe
			if (loan.isOverDue()) 
				return false;
			
		return true;
	}

	
	public int loansRemainingForMember(Member member) // Author Amandeep Kaur change the first letter of the class Member from lower to upper case Reviewed By Kasun Amarsinghe
	{		
		return LOAN_LIMIT - member.getNumberOfCurrentLoans();
	}

	
	public Loan issueLoan(Book book, Member member) // Author Amandeep Kaur change the first letter of the class Loan , Book and Member from lower to upper case Reviewed By Kasun Amarsinghe
	{
		Date dueDate = Calendar.getInstance().getDueDate(LOAN_PERIOD);
		Loan loan = new loan(nextLID(), book, member, dueDate);
		member.takeOutLoan(loan);
		book.Borrow();
		loans.put(loan.getId(), loan);
		currentLoans.put(book.ID(), loan);
		return loan;
	}
	
	
	public Loan getLoanByBookId(int bookId) // Author Amandeep Kaur change the first letter of the class  Loan from lower to upper case Reviewed By Kasun Amarsinghe
	{
		if (currentLoans.containsKey(bookId)) {
			return currentLoans.get(bookId);
		}
		return null;
	}

	
	public double calculateOverDueFine(Loan loan) // Author Amandeep kaur Capatalize the class name Reviewed By Kasun Amarsinghe
	{
		if (loan.isOverDue()) {
			long daysOverDue = Calendar.getInstance().getDaysDifference(loan.getDueDate());
			double fine = daysOverDue * FINE_PER_DAY;
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(Loan currentLoan, boolean isDamaged) // Author Amandeep kaur Capatalize the class name  and update the loan to Loan Reviewed By Kasun Amarsinghe
	{
		Member member = currentLoan.Member();// Author Amandeep kaur Capatalize the class name  Reviewed By Kasun Amarsinghe
		Book book  = currentLoan.Book(); // Author Amandeep kaur Capatalize the class name Reviewed By Kasun Amarsinghe
		
		double overDueFine = calculateOverDueFine(currentLoan);
		member.addFine(overDueFine);	
		
		member.dischargeLoan(currentLoan);
		book.Return(isDamaged);
		if (isDamaged) {
			member.addFine(DAMAGE_FEE);
			damagedBooks.put(book.ID(), book);
		}
		currentLoan.Loan();
		currentLoans.remove(book.ID()); 
	}


	public void checkCurrentLoans() {
		for (Loan loan : currentLoans.values()) //Author Amandeep kaur Capatalize the class name Reviewed By Kasun Amarsinghe
		{
			loan.checkOverDue();
		}		
	}


	public void repairBook(Book currentBook) //Author Amandeep kaur Capatalize the class name Reviewed By Kasun Amarsinghe
	{
		if (damagedBooks.containsKey(currentBook.ID())) {
			currentBook.Repair();
			damagedBooks.remove(currentBook.ID());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
