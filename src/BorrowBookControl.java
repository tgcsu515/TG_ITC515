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
		B = null;
		if (!state.equals(CONTROL_STATE.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		B = L.Book(bookId);
		if (B == null) {
			ui.display("Invalid bookId");
			return;
		}
		if (!B.Available()) {
			ui.display("Book cannot be borrowed");
			return;
		}
		PENDING.add(B);
		for (book B : PENDING) {
			ui.display(B.toString());
		}
		if (L.loansRemainingForMember(M) - PENDING.size() == 0) {
			ui.display("Loan limit reached");
			Complete();
		}
	}
	
	
	public void Complete() {
		if (PENDING.size() == 0) {
			cancel();
		}
		else {
			ui.display("\nFinal Borrowing List");
			for (book b : PENDING) {
				ui.display(b.toString());
			}
			COMPLETED = new ArrayList<loan>();
			ui.setState(BorrowBookUI.UI_STATE.FINALISING);
			state = CONTROL_STATE.FINALISING;
		}
	}


	public void commitLoans() {
		if (!state.equals(CONTROL_STATE.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book b : PENDING) {
			loan loan = L.issueLoan(b, M);
			COMPLETED.add(loan);			
		}
		ui.display("Completed Loan Slip");
		for (loan loan : COMPLETED) {
			ui.display(loan.toString());
		}
		ui.setState(BorrowBookUI.UI_STATE.COMPLETED);
		state = CONTROL_STATE.COMPLETED;
	}

	
	public void cancel() {
		ui.setState(BorrowBookUI.UI_STATE.CANCELLED);
		state = CONTROL_STATE.CANCELLED;
	}
	
	
}
