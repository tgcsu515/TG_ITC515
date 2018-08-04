public class FixBookControl {
	
	private FixBookUI ui;
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
		this.ui = ui;
		ui.setState(FixBookUI.UI_STATE.READY);
		currentState = CONTROL_STATE.READY;		
	}


	public void bookScanned(int bookId) {
		if (!currentState.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		currentBook = library.Book(bookId);
		
		if (currentBook == null) {
			ui.display("Invalid bookId");
			return;
		}
		if (!currentBook.Damaged()) {
			ui.display("\"Book has not been damaged");
			return;
		}
		ui.display(currentBook.toString());
		ui.setState(FixBookUI.UI_STATE.FIXING);
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
		ui.setState(FixBookUI.UI_STATE.READY);
		currentState = CONTROL_STATE.READY;		
	}

	
	public void scanningComplete() {
		if (!currentState.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		ui.setState(FixBookUI.UI_STATE.COMPLETED);		
	}

}
