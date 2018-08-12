public class FixBookControl {
	
	private FixBookUI currentFixBookUI;
	private enum CONTROL_STATE { INITIALISED, READY, FIXING };
	private CONTROL_STATE currentState; //Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe
	
	private Library library; //Capitalized the first letter of the Library class name - Author: Kasun Amarasinghe
	private Book currentBook; //Capitalized the first letter of the Book class - Author: Kasun Amarasinghe


	public FixBookControl() {
		this.library = Library.INSTANCE();//Capitalized the first letter of the Library class name - Author: Kasun Amarasinghe
		currentState = CONTROL_STATE.INITIALISED; //Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe
	}
	
	
	public void setUI(FixBookUI ui) {
		//Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe
		if (!currentState.equals(CONTROL_STATE.INITIALISED)) { 
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.currentFixBookUI = ui;
		ui.setState(FixBookUI.UI_STATE.READY);
		currentState = CONTROL_STATE.READY;	//Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe	
	}


	public void bookScanned(int bookId) {
		//Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe
		if (!currentState.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		currentBook = library.book(bookId); //Made the first letter to lowercase in the method name - Author: Kasun Amarasinghe 		
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
		currentState = CONTROL_STATE.FIXING; //Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe		
	}


	public void fixBook(boolean fix) {
		//Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe
		if (!currentState.equals(CONTROL_STATE.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (fix) {
			library.repairBook(currentBook);
		}
		currentBook = null;
		currentFixBookUI.setState(FixBookUI.UI_STATE.READY);
		currentState = CONTROL_STATE.READY;	 //Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe	
	}

	
	public void scanningComplete() {
		//Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe
		if (!currentState.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		currentFixBookUI.setState(FixBookUI.UI_STATE.COMPLETED);		
	}

}
