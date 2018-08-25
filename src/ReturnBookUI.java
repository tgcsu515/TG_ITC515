import java.util.Scanner;


public class ReturnBookUI {

	public static enum UI_STATE { INITIALISED, READY, INSPECTING, COMPLETED };

	private ReturnBookControl currentReturnBookControl;							//Author: Kanchan Bala, Changed variable name "control" to "currentReturnBookControl"
	private Scanner input;
	private UI_STATE currentUIState;											//Author: Kanchan Bala, Changed variable name "state" to "currentUIState"

	
	public ReturnBookUI(ReturnBookControl currentReturnBookControl) {					//Author: Kanchan Bala, Update changed variable name "control" to "currentReturnBookControl"		
		this.currentReturnBookControl = currentReturnBookControl;						//Author: Kanchan Bala, Update changed variable name "control" to "currentReturnBookControl"
		input = new Scanner(System.in);
		currentUIState = UI_STATE.INITIALISED;											//Author: Kanchan Bala, Update changed variable name "state" to "currentUIState"
		currentReturnBookControl.setUI(this);											//Author: Kanchan Bala, Update changed variable name "control" to "currentReturnBookControl"
	}


	public void run() {		
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case INITIALISED:
				break;
				
			case READY:
				String bookStr = input("Scan Book (<enter> completes): ");
				if (bookStr.length() == 0) {
					currentReturnBookControl.scanningComplete();					//Author: Kanchan Bala, Update changed variable name "control" to "currentReturnBookControl"
				}
				else {
					try {
						int bookId = Integer.valueOf(bookStr).intValue();
						currentReturnBookControl.bookScanned(bookId);				//Author: Kanchan Bala, Update changed variable name "control" to "currentReturnBookControl"
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");
				boolean isDamaged = false;
				if (ans.toUpperCase().equals("Y")) {					
					isDamaged = true;
				}
				currentReturnBookControl.dischargeLoan(isDamaged);									
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);			
			}
		}
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void display(Object object) {
		output(object);
	}
	
	public void setState(UI_STATE state) {
		this.state = state;
	}

	
}
