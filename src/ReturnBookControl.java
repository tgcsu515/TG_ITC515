public class ReturnBookControl {

	private ReturnBookUI currentReturnBookUI;					//Author: Kanchan Bala, Changed variable name from "ui" to "currentReturnBookUI"
	private enum CONTROL_STATE { INITIALISED, READY, INSPECTING };
	private CONTROL_STATE currentControlState;			//Author: Kanchan Bala, Changed variable name "state" to "currentControlState"
	
	private Library currentLibrary;							//Author:Kanchan Bala, update class name "library" to "Library" and variable name "library to "currentLibrary"
	private loan currentLoan;
	

	public ReturnBookControl() {
		this.currentLibrary = Library.INSTANCE();				//Author:Kanchan Bala, update class name "library" to "Library" and variable name "this.library" to "this.currentLibrary"
		currentControlState = CONTROL_STATE.INITIALISED;		//Author: Kanchan Bala, Updated variable name "state" to "currentControlState"
	}
	
	
	public void setCurrentReturnBookUI(ReturnBookUI currentReturnBookUI) {	//Author: Kanchan Bala, Updated variable name from "ui" to "currentReturnBookUI" and "setUI" to "setCurrentReturnBookUI"
		if (!currentControlState.equals(CONTROL_STATE.INITIALISED)) {		//Author: Kanchan Bala, Updated variable name "state" to "currentControlState"
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.currentReturnBookUI = currentReturnBookUI;
		currentReturnBookUI.setCurrentControlState(ReturnBookUI.UI_STATE.READY); 		//Author: Kanchan Bala, Updated variable name "ui" to "currentReturnBookUI" and "setState" to "setCurrentControlState"
		currentControlState = CONTROL_STATE.READY;						//Author: Kanchan Bala, Updated variable name "state" to "currentControlState"	
	}


	public void bookScanned(int bookId) {
		if (!currentControlState.equals(CONTROL_STATE.READY)) {			//Author: Kanchan Bala, Updated variable name "state" to "currentControlState"
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		book currentBook = currentLibrary.Book(bookId);					//Author: Kanchan Bala, Updated variable name "library" to "currentLibrary"
		
		if (currentBook == null) {
			currentReturnBookUI.display("Invalid Book Id");				//Author: Kanchan Bala, Updated variable name "ui" to "currentReturnBookUI"
			return;
		}
		if (!currentBook.On_loan()) {
			currentReturnBookUI.display("Book has not been borrowed");	//Author: Kanchan Bala, Updated variable name "ui" to "currentReturnBookUI"
			return;
		}		
		currentLoan = currentLibrary.getLoanByBookId(bookId);			//Author: Kanchan Bala, Updated variable name "library" to "currentLibrary"
		double overDueFine = 0.0;
		if (currentLoan.isOverDue()) {
			overDueFine = currentLibrary.calculateOverDueFine(currentLoan);		//Author: Kanchan Bala, Updated variable name "library" to "currentLibrary"
		}
		currentReturnBookUI.display("Inspecting");				//Author: Kanchan Bala, Updated variable name "ui" to "currentReturnBookUI"
		currentReturnBookUI.display(currentBook.toString());	//Author: Kanchan Bala, Updated variable name "ui" to "currentReturnBookUI"
		currentReturnBookUI.display(currentLoan.toString());	//Author: Kanchan Bala, Updated variable name "ui" to "currentReturnBookUI"
		
		if (currentLoan.isOverDue()) {
			currentReturnBookUI.display(String.format("\nOverdue fine : $%.2f", overDueFine));		//Author: Kanchan Bala, Updated variable name "ui" to "currentReturnBookUI"
		}
		currentReturnBookUI.setCurrentControlState(ReturnBookUI.UI_STATE.INSPECTING);		//Author: Kanchan Bala, Updated variable name "ui" to "currentReturnBookUI" and "setState" to "setCurrentControlState"
		currentControlState = CONTROL_STATE.INSPECTING;						//Author: Kanchan Bala, Updated variable name "state" to "currentControlState"	
	}


	public void scanningComplete() {
		if (!currentControlState.equals(CONTROL_STATE.READY)) {				//Author: Kanchan Bala, Updated variable name "state" to "currentControlState"
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		currentReturnBookUI.setCurrentControlState(ReturnBookUI.UI_STATE.COMPLETED);		//Author: Kanchan Bala, Updated variable name "ui" to "currentReturnBookUI" and "setState" to "setCurrentControlState"
	}


	public void dischargeLoan(boolean isDamaged) {
		if (!currentControlState.equals(CONTROL_STATE.INSPECTING)) {		//Author: Kanchan Bala, Updated variable name "state" to "currentControlState"
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		library.dischargeLoan(currentLoan, isDamaged);
		currentLoan = null;
		currentReturnBookUI.setCurrentControlState(ReturnBookUI.UI_STATE.READY);			//Author: Kanchan Bala, Updated variable name "ui" to "currentReturnBookUI"
		currentControlState = CONTROL_STATE.READY;							//Author: Kanchan Bala, Updated variable name "state" to "currentControlState"
	}


}
