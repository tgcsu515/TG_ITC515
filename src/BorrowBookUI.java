/* Author: Gurpreet Gill, Team Name: Team Genius, Student id: 11622931
 * Reviewer: Arashdeep Kaur, Mediator: Amandeep Kaur
 *
 * In this program I have made few changes with variable names and class name, the variable names, changed to give the meaningful names as per the "Guidelines
 * and class name, has been capatalized with first letter. Apart from these changes, I could not find any variable name, class name, indentation etcetra, which 
 * breach the guidelines.
 * This java program display the Borrow Books user interface.
 */
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
			
			switch (borrowBookUiState) {  //Change the variable name from "state" to "borrowBookUiState" - BY GURPREET GILL
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				
			case READY:
				String memberCard = input("Swipe member card (press <enter> to cancel): ");  //Change the variable name from "memStr" to "memberCard" - BY GURPREET GILL
				if (memberCard.length() == 0) {  //Change the variable name from "memStr" to "memberCard" - BY GURPREET GILL
					borrowBookControl.cancel();  //Change the variable name from "control" to "borrowBookControl" - BY GURPREET GILL
					break;
				}
				try {
					int memberId = Integer.valueOf(memberCard).intValue();  //Change the variable name from "memStr" to "memberCard" - BY GURPREET GILL
					borrowBookControl.Swiped(memberId);  //Change the variable name from "control" to "borrowBookControl" - BY GURPREET GILL
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				borrowBookControl.cancel();  //Change the variable name from "control" to "borrowBookControl" - BY GURPREET GILL
				break;
			
				
			case SCANNING:
				String scanBook = input("Scan Book (<enter> completes): ");  //Change the variable name from "bookStr" to "scanBook" - BY GURPREET GILL
				if (scanBook.length() == 0) {  //Change the variable name from "bookStr" to "scanBook" - BY GURPREET GILL
					borrowBookControl.Complete();   //Change the variable name from "control" to "borrowBookControl" - BY GURPREET GILL
					break;
				}
				try {
					int bookId = Integer.valueOf(scanBook).intValue();  //Change the variable name from "bookStr" to "scanBook" - BY GURPREET GILL
					borrowBookControl.Scanned(bookId);  //Change the variable name from "control" to "borrowBookControl" - BY GURPREET GILL
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String userInput = input("Commit loans? (Y/N): ");  //Change the variable name from "ans" to "userInput" - BY GURPREET GILL
				if (userInput.toUpperCase().equals("N")) {  //Change the variable name from "ans" to "userInput" - BY GURPREET GILL
					borrowBookControl.cancel();  //Change the variable name from "control" to "borrowBookControl" - BY GURPREET GILL
					
				} else {
					borrowBookControl.commitLoans();  //Change the variable name from "control" to "borrowBookControl" - BY GURPREET GILL
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + borrowBookUiState);	  //Change the variable name from "state" to "borrowBookUiState" - BY GURPREET GILL		
			}
		}		
	}


	public void display(Object object) {
		output(object);		
	}


}
