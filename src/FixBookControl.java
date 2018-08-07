public class FixBookControl {
	
	private FixBookUI currentFixBookUI;
	private enum CONTROL_STATE { INITIALISED, READY, FIXING };
	private CONTROL_STATE currentState;
	
	private Library library;
	private Book currentBook;


	public FixBookControl() {
		this.library = Library.INSTANCE();
		currentState = CONTROL_STATE.INITIALISED;
	}
	
	
	public void setUI(FixBookUI ui) {
		if (!currentState.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.currentFixBookUI = ui;
		ui.setState(FixBookUI.UI_STATE.READY);
		currentState = CONTROL_STATE.READY;		
	}


	public void bookScanned(int bookId) {
		if (!currentState.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		currentBook = library.book(bookId); //Made the first letter to lowercase in the method name. Author: Kasun Amarasinghe & Reviewer: All other members
		
		if (currentBook == null) {
			currentFixBookUI.display("Invalid bookId");
			return;
		}
		if (!currentBook.damaged()) { //Made the first letter to lowercase in the method name. Author: Kasun Amarasinghe & Reviewer: All other members
			currentFixBookUI.display("\"Book has not been damaged");
			return;
		}
		currentFixBookUI.display(currentBook.toString());
		currentFixBookUI.setState(FixBookUI.UI_STATE.FIXING);
		currentState = CONTROL_STATE.FIXING;		
	}


	public void fixBook(boolean fix) {
		if (!currentState.equals(CONTROL_STATE.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (fix) {
			library.repairBook(currentBook);
		}
		currentBook = null;
		currentFixBookUI.setState(FixBookUI.UI_STATE.READY);
		currentState = CONTROL_STATE.READY;		
	}

	
	public void scanningComplete() {
		if (!currentState.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		currentFixBookUI.setState(FixBookUI.UI_STATE.COMPLETED);		
	}

}
