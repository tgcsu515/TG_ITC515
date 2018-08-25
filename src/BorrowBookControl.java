/* Author: Gurpreet Gill, Team Name: Team Genius, Student id: 11622931
 * Reviewer: Arashdeep Kaur, Mediator: Amandeep Kaur
 * @version 9.0.4(build 9.0.4+ 11)
 *
 * In this program I have made few changes with variable names and class name, the variable names, changed to give the meaningful names as per the "Guidelines
 * and class name, has been capatalized with first letter. Apart from these changes, I could not find any variable name, class name, indentation etcetra, which 
 * breach the guidelines.
 * This java program is control the record of Borrow Books.
 */
import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI borrowBookUi;  //Change the variable name from "UI" to "borrowBookUi" - BY GURPREET GILL
	
	private Library library; //Change the variable name from "L" to "library" - BY GURPREET GILL
	private Member member; //Change the variable name from "M", "member" to "member(object name)", "Member(class name)" - BY GURPREET GILL
	private enum BORROW_BOOK_CONTROL_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED }; //Change the variable name from "CONTROL_STATE" to "BORROW_BOOK_CONTROL_STATE" - BY GURPREET GILL
	private BORROW_BOOK_CONTROL_STATE borrowBookControlState; //Change the variable name from "CONTROL_STATE" to "BORROW_BOOK_CONTROL_STATE" - BY GURPREET GILL
	
	private List<Book> PENDING; //Change the class name from "book" to "Book" - BY GURPREET GILL
	private List<Loan> COMPLETED; //Change the class name from "loan" to "Loan" - BY GURPREET GILL
	private Book book; //Change the object name from "B" to "book" - BY GURPREET GILL
	
	
	public borrowBookControl() { //Change the object name from "BorrowBookControl" to "borrowBookControl" - BY GURPREET GILL
		this.library = library.INSTANCE(); //Change the object name from "L" to "library" - BY GURPREET GILL
		borrowBookControlState = BORROW_BOOK_CONTROL_STATE.INITIALISED; //Change the object name from "state" to "borrowBookControlState" - BY GURPREET GILL
	}
	

	public void setBorrowBookUI(BorrowBookUI borrowBookUi) {  //Change the variable name from "UI", "setUI" to "borrowBookUi", "setBorrowBookUI" - BY GURPREET GILL
		if (!borrowBookControlState.equals(BORROW_BOOK_CONTROL_STATE.INITIALISED))  //Change the variable name from "CONTROL_STATE" to "BORROW_BOOK_CONTROL_STATE" - BY GURPREET GILL
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.borrowBookUi = borrowBookUi;  //Change the variable name from "UI" to "borrowBookUi" - BY GURPREET GILL
		borrowBookUi.setBorrowBookControlState(BorrowBookUI.BORROW_BOOK_UI_STATE.READY);  //Change the variable name from "setState" to "setBorrowBookControlState" - BY GURPREET GILL
		borrowBookControlState = BORROW_BOOK_CONTROL_STATE.READY;  //Change the variable name from "CONTROL_STATE" to "BORROW_BOOK_CONTROL_STATE" - BY GURPREET GILL
	}

		
	public void swiped(int memberId) {  //Change the method name from "Swiped" to "swiped" - BY GURPREET GILL
		if (!borrowBookControlState.equals(BORROW_BOOK_CONTROL_STATE.READY))   //Change the variable name from "state" to "borrowBookControlState" - BY GURPREET GILL
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		member = library.getMember(memberId);  //Change the variable name from "M", "L" to "member", "library" - BY GURPREET GILL
		if (member == null) {  //Change the variable name from "M" to "member" - BY GURPREET GILL
			borrowBookUi.display("Invalid memberId");  //Change the variable name from "UI" to "borrowBookUi" - BY GURPREET GILL
			return;
		}
		if (library.memberCanBorrow(member)) {  //Change the variable name from "L" to "library" - BY GURPREET GILL
			PENDING = new ArrayList<>();
			borrowBookUi.setBorrowBookControlState(BorrowBookUI.BORROW_BOOK_UI_STATE.SCANNING);  //Change the variable name from "setState" to "setBorrowBookControlState" - BY GURPREET GILL
			borrowBookControlState = BORROW_BOOK_CONTROL_STATE.SCANNING; }  //Change the variable name from "state" to "borrowBookControlState" - BY GURPREET GILL
		else 
		{
			borrowBookUi.display("Member cannot borrow at this time");  //Change the variable name from "UI" to "borrowBookUi" - BY GURPREET GILL
			borrowBookUi.setBorrowBookControlState(BorrowBookUI.BORROW_BOOK_UI_STATE.RESTRICTED); }}  //Change the variable name from "setState" to "setBorrowBookControlState" - BY GURPREET GILL
	
	
	public void Scanned(int bookId) {
		book = null;  //Change the variable name from "B" to "book" - BY GURPREET GILL
		if (!borrowBookControlState.equals(BORROW_BOOK_CONTROL_STATE.SCANNING)) {  //Change the variable name from "state" to "borrowBookControlState" - BY GURPREET GILL
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		book = library.Book(bookId);  //Change the variable name from "L" to "library" - BY GURPREET GILL
		if (book == null) {  //Change the variable name from "B" to "book" - BY GURPREET GILL
			borrowBookUi.display("Invalid bookId");  //Change the variable name from "UI" to "borrowBookUi" - BY GURPREET GILL
			return;
		}
		if (!book.Available()) {  //Change the variable name from "B" to "book" - BY GURPREET GILL
			borrowBookUi.display("Book cannot be borrowed");  //Change the variable name from "UI" to "borrowBookUi" - BY GURPREET GILL
			return;
		}
		PENDING.add(book);  //Change the variable name from "B" to "book" - BY GURPREET GILL
		for (Book book : PENDING) {  //Change the variable name from "B" to "book" - BY GURPREET GILL
			borrowBookUi.display(book.toString());  //Change the variable name from "UI" to "borrowBookUi" - BY GURPREET GILL
		}
		if (library.loansRemainingForMember(member) - PENDING.size() == 0) {  //Change the variable name from "L" to "library" - BY GURPREET GILL
			borrowBookUi.display("Loan limit reached");  //Change the variable name from "UI" to "borrowBookUi" - BY GURPREET GILL
			Complete();
		}
	}
	
	
	public void complete() {  //Change the method name from "Complete" to "complete" - BY GURPREET GILL
		if (PENDING.size() == 0) {
			cancel();
		}
		else {
			borrowBookUi.display("\nFinal Borrowing List");  //Change the variable name from "UI" to "borrowBookUi" - BY GURPREET GILL
			for (Book book : PENDING) {  //Change the variable name from "book b" to "Book book" - BY GURPREET GILL
				borrowBookUi.display(book.toString());  //Change the variable name from "B" to "book" - BY GURPREET GILL
			}
			COMPLETED = new ArrayList<Loan>();  //Change the variable name from "loan" to "Loan" - BY GURPREET GILL
			borrowBookUi.setBorrowBookControlState(BorrowBookUI.BORROW_BOOK_UI_STATE.FINALISING);  //Change the variable name from "UI" to "borrowBookUi" - BY GURPREET GILL
			borrowBookControlState = BORROW_BOOK_CONTROL_STATE.FINALISING;  //Change the variable name from "UI" to "borrowBookUi" - BY GURPREET GILL
		}
	}


	public void commitLoans() {
		if (!borrowBookControlState.equals(BORROW_BOOK_CONTROL_STATE.FINALISING)) {  //Change the variable name from "CONTROL_STATE" to "BORROW_BOOK_CONTROL_STATE" - BY GURPREET GILL
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (Book book : PENDING) {  //Change the variable name from "book b" to "Book book" - BY GURPREET GILL
			Loan loan = loan.issueLoan(book, member);  //Change the variable name from "loan l" to "Loan loan" - BY GURPREET GILL
			COMPLETED.add(Loan);  //Change the variable name from "loan" to "Loan" - BY GURPREET GILL
		}
		borrowBookUi.display("Completed Loan Slip");  //Change the variable name from "UI" to "borrowBookUi" - BY GURPREET GILL
		for (Loan loan : COMPLETED) {  //Change the variable name from "loan loan" to "Loan loan" - BY GURPREET GILL
			borrowBookUi.display(Loan.toString());  //Change the variable name from "UI" to "borrowBookUi" - BY GURPREET GILL
		}
		borrowBookUi.setBorrowBookControlState(BorrowBookUI.BORROW_BOOK_UI_STATE.COMPLETED);  //Change the variable name from "UI" to "borrowBookUi" - BY GURPREET GILL
		borrowBookControlState = BORROW_BOOK_CONTROL_STATE.COMPLETED;  //Change the variable name from "UI" to "borrowBookUi" - BY GURPREET GILL
	}

	
	public void cancel() {
		borrowBookUi.setBorrowBookControlState(BorrowBookUI.BORROW_BOOK_UI_STATE.CANCELLED);  //Change the variable name from "UI" to "borrowBookUi" - BY GURPREET GILL
		borrowBookControlState = BORROW_BOOK_CONTROL_STATE.CANCELLED;  //Change the variable name from "state" to "borrowBookControlState" - BY GURPREET GILL
	}
	
	
}
