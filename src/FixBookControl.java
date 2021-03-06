/*The author for the following FixBookUI class is Kasun Amarasinghe.
  kanchan Bala will review the code updates which were done to this file.
  Arashdeep Kuar is the mediator for this class file.
  This class file will be reviewed using the given Code Style Guidelines and necessary code updates will be done by the Author.
*/

public class FixBookControl {
	
	private FixBookUI currentFixBookUI;
	private enum CONTROL_STATE { INITIALISED, READY, FIXING };
	private CONTROL_STATE currentState; //Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
	
	private Library library; //Capitalized the first letter of the Library class name - Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
	private Book currentBook; //Capitalized the first letter of the Book class - Author: Kasun Amarasinghe/Reviewed By Kanchan Bala


	public FixBookControl() {
		this.library = Library.INSTANCE();//Capitalized the first letter of the Library class name - Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
		currentState = CONTROL_STATE.INITIALISED; //Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
	}
	
	
	public void setUI(FixBookUI ui) {
		//Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
		if (!currentState.equals(CONTROL_STATE.INITIALISED)) { 
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.currentFixBookUI = ui;
		ui.setState(FixBookUI.UI_STATE.READY);
		currentState = CONTROL_STATE.READY;	//Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe/Reviewed By Kanchan Bala	
	}


	public void bookScanned(int bookId) {
		//Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
		if (!currentState.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		currentBook = library.book(bookId); //Made the first letter to lowercase in the method name - Author: Kasun Amarasinghe/Reviewed By Kanchan Bala 		
		if (currentBook == null) {
			currentFixBookUI.display("Invalid bookId");
			return;
		}
		if (!currentBook.damaged()) { //Made the first letter to lowercase in the method name. Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
			currentFixBookUI.display("\"Book has not been damaged");
			return;
		}
		currentFixBookUI.display(currentBook.toString());
		currentFixBookUI.setState(FixBookUI.UI_STATE.FIXING);
		currentState = CONTROL_STATE.FIXING; //Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe/Reviewed By Kanchan Bala		
	}


	public void fixBook(boolean fix) {
		//Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
		if (!currentState.equals(CONTROL_STATE.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (fix) {
			library.repairBook(currentBook);
		}
		currentBook = null;
		currentFixBookUI.setState(FixBookUI.UI_STATE.READY);
		currentState = CONTROL_STATE.READY;	 //Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe/Reviewed By Kanchan Bala	
	}

	
	public void scanningComplete() {
		//Updated the variable name "state" to "currentState" - Author: Kasun Amarasinghe/Reviewed By Kanchan Bala
		if (!currentState.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		currentFixBookUI.setState(FixBookUI.UI_STATE.COMPLETED);		
	}

}
