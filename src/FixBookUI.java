import java.util.Scanner;

/*The author for the following FixBookUI class is Kasun Amarasinghe.
  Kanchan Bala will review the code updates which were done to this file.
  Arashdeep Kuar is the mediator for this class file.
  This class file will be reviewed using the given Code Style Guidelines and necessary code updates will be done by the Author.
*/

public class FixBookUI {

	public static enum UI_STATE { INITIALISED, READY, FIXING, COMPLETED };

	private FixBookControl control;
	private Scanner input;
	private UI_STATE state;

	
	public FixBookUI(FixBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UI_STATE.INITIALISED;
		control.setUI(this);
	}


	public void setState(UI_STATE state) {
		this.state = state;
	}

	
	public void run() {
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case READY:
				String bookStr = input("Scan Book (<enter> completes): ");
				if (bookStr.length() == 0) {
					control.scanningComplete();
				}
				else {
					try {
						int bookId = Integer.valueOf(bookStr).intValue();
						control.bookScanned(bookId);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String userSelectedAnswer = input("Fix Book? (Y/N) : "); //Changed the varible name "ans" to "userSelectedAnswer" to be meaningfull-Author Kasun Amarasinghe 
				boolean fix = false;
				if (userSelectedAnswer.toUpperCase().equals("Y")) //Changed the varible name "ans" to "userSelectedAnswer" to be meaningfull-Author Kasun Amarasinghe 
				{
					fix = true;
				}
				control.fixBook(fix);
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);						
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
}
