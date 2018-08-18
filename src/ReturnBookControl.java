public class ReturnBookControl {

	private ReturnBookUI currentReturnBookUI ui;					//Author: Kanchan Bala, Changed variable name from ui to currentReturnBookUI
	private enum CONTROL_STATE { INITIALISED, READY, INSPECTING };
	private CONTROL_STATE state;
	
	private library library;
	private loan currentLoan;
	

	public ReturnBookControl() {
		this.library = library.INSTANCE();
		state = CONTROL_STATE.INITIALISED;
	}
	
	
	public void setUI(ReturnBookUI currentReturnBookUI) {	//Author: Kanchan Bala, Updated variable name from ui to currentReturnBookUI
		if (!state.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.currentReturnBookUI = currentReturnBookUI;
		currentReturnBookUI.setState(ReturnBookUI.UI_STATE.READY); 		//Author: Kanchan Bala, Updated variable name ui to currentReturnBookUI
		state = CONTROL_STATE.READY;		
	}


	public void bookScanned(int bookId) {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		book currentBook = library.Book(bookId);
		
		if (currentBook == null) {
			currentReturnBookUI.display("Invalid Book Id");				//Author: Kanchan Bala, Updated variable name ui to currentReturnBookUI
			return;
		}
		if (!currentBook.On_loan()) {
			currentReturnBookUI.display("Book has not been borrowed");	//Author: Kanchan Bala, Updated variable name ui to currentReturnBookUI
			return;
		}		
		currentLoan = library.getLoanByBookId(bookId);	
		double overDueFine = 0.0;
		if (currentLoan.isOverDue()) {
			overDueFine = library.calculateOverDueFine(currentLoan);
		}
		currentReturnBookUI.display("Inspecting");				//Author: Kanchan Bala, Updated variable name ui to currentReturnBookUI
		currentReturnBookUI.display(currentBook.toString());	//Author: Kanchan Bala, Updated variable name ui to currentReturnBookUI
		currentReturnBookUI.display(currentLoan.toString());	//Author: Kanchan Bala, Updated variable name ui to currentReturnBookUI
		
		if (currentLoan.isOverDue()) {
			ui.display(String.format("\nOverdue fine : $%.2f", overDueFine));
		}
		ui.setState(ReturnBookUI.UI_STATE.INSPECTING);
		state = CONTROL_STATE.INSPECTING;		
	}


	public void scanningComplete() {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		ui.setState(ReturnBookUI.UI_STATE.COMPLETED);		
	}


	public void dischargeLoan(boolean isDamaged) {
		if (!state.equals(CONTROL_STATE.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		library.dischargeLoan(currentLoan, isDamaged);
		currentLoan = null;
		ui.setState(ReturnBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;				
	}


}
