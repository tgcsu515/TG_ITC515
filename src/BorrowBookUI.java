import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum BORROW_BOOK_UI_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };  //Change the variable name from "UI_STATE" to "BORROW_BOOK_UI_STATE" - BY GURPREET GILL

	private BorrowBookControl borrowBookControl;  //Change the variable name from "state" to "borrowBookControl" - BY GURPREET GILL
	private Scanner input;
	private BORROW_BOOK_UI_STATE borrowBookUiState;  //Change the variable name from "UI_STATE" to "BORROW_BOOK_UI_STATE" - BY GURPREET GILL

	
	public borrowBookUI(BorrowBookControl borrowBookControl) {  //Change the method name from "BorrowBookUI" to "borrowBookUI" - BY GURPREET GILL
		this.borrowBookControl = borrowBookControl;  //Change the variable name from "control" to "borrowBookControl" - BY GURPREET GILL
		input = new Scanner(System.in);
		borrowBookUiState = BORROW_BOOK_UI_STATE.INITIALISED;  //Change the variable name from "state" to "borrowBookUiState" - BY GURPREET GILL
		borrowBookControl.setBorrowBookUi(this);  //Change the variable name from "setUI" to "setBorrowBookUi" - BY GURPREET GILL
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void setBorrowBookState(BORROW_BOOK_UI_STATE borrowBookUiState) {  //Change the variable name from "setState" to "setBorrowBookState" - BY GURPREET GILL
		this.borrowBookUiState = borrowBookUiState;  //Change the variable name from "state" to "borrowBookUiState" - BY GURPREET GILL
	}

	
	public void run() {
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {			
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				
			case READY:
				String memStr = input("Swipe member card (press <enter> to cancel): ");
				if (memStr.length() == 0) {
					control.cancel();
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue();
					control.Swiped(memberId);
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				control.cancel();
				break;
			
				
			case SCANNING:
				String bookStr = input("Scan Book (<enter> completes): ");
				if (bookStr.length() == 0) {
					control.Complete();
					break;
				}
				try {
					int bookId = Integer.valueOf(bookStr).intValue();
					control.Scanned(bookId);
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String ans = input("Commit loans? (Y/N): ");
				if (ans.toUpperCase().equals("N")) {
					control.cancel();
					
				} else {
					control.commitLoans();
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + state);			
			}
		}		
	}


	public void display(Object object) {
		output(object);		
	}


}
