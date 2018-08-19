import java.util.Scanner;


public class PayFineUI {


	public static enum PAY_FINE_UI_STATE { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; //Change the variable name from "UI_STATE" to "PAY_FINE_UI_STATE" as Admin - Arashdeep kaur

	private PayFineControl payFineControl; //change the variable name "control" to "payFineControl" as Admin - Arashdeep kaur
	private Scanner input; 
	private PAY_FINE_UI_STATE state; //Change the variable name from "UI_STATE" to "PAY_FINE_UI_STATE" as Admin - Arashdeep kaur

	
	public PayFineUI(PayFineControl payFineControl) 
		//change the variable name control to "payFineControl" as Admin - Arashdeep kaur
	{
		this.payFineControl = payFineControl;//change the variable name control to "payFineControl" as Admin - Arashdeep kaur
		input = new Scanner(System.in);
		state = PAY_FINE_UI_STATE.INITIALISED; //Change the variable name from "UI_STATE" to "PAY_FINE_UI_STATE" as Admin - Arashdeep kaur
		payFineControl.setUI(this); //change the variable name control to "payFineControl" as Admin - Arashdeep kaur
	}
	
	
	public void setState(PAY_FINE_UI_STATE payFineUiState) //Change the variable name from "UI_STATE" to "PAY_FINE_UI_STATE" and "state" to "payFineUiState" as Admin - Arashdeep kaur
	{
		this.payFineUiState = payFineUiState; //Change variable name "state" to "payFineUiState" as Admin Arashdeep Kaur
	}


	public void run() {
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (payFineUiState) //Change variable name "state" to "payFineUiState" as Admin Arashdeep Kaur
			{
			
			case READY:
				String memberCard = input("Swipe member card (press <enter> to cancel): "); //change the variable name "memstr" to "memberCard" as Admin - Arashdeep Kaur
				if (memberCard.length() == 0) //change the variable name "memstr" to "memberCard" as Admin - Arashdeep Kaur
				{
					payfineControl.cancel(); //change the variable name control to "payFineControl" as Admin - Arashdeep kaur
					break;
				}
				try {
					int memberId = Integer.valueOf(memberCard).intValue(); //change the variable name "memstr" to "memberCard" as Admin - Arashdeep Kaur
					payFineControl.cardSwiped(memberId); //change the variable name control to "payFineControl" as Admin - Arashdeep kaur
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amount = 0;
				String amtStr = input("Enter amount (<Enter> cancels) : ");
				if (amtStr.length() == 0) {
					payFineControl.cancel(); //change the variable name control to "payFineControl" as Admin - Arashdeep kaur
					break;
				}
				try {
					amount = Double.valueOf(amtStr).doubleValue();
				}
				catch (NumberFormatException e) {}
				if (amount <= 0) {
					output("Amount must be positive");
					break;
				}
				payFineControl.payFine(amount); //change the variable name control to "payFineControl" as Admin - Arashdeep kaur
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
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
