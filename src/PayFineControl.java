public class PayFineControl {
	
	private PayFineUI payFineUi; //change the name of the variable from 'ui' to 'payFineUi' as Admin - Arashdeep Kaur
	private enum CONTROL_STATE { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
	private CONTROL_STATE payFineState; //change the name of the variable from 'state' to 'payFineState' as Admin - Arashdeep Kaur
	
	private library library;
	private member member;;


	public PayFineControl() {
		this.library = library.INSTANCE();
		payFineState = CONTROL_STATE.INITIALISED; //change the name of the variable from 'state' to 'payFineState' as Admin - Arashdeep Kaur
	}
	
	
	public void setUI(PayFineUI payFineUi)  //change the name of the variable from 'ui' to 'payFineUi' as Admin - Arashdeep Kaur
	{
		if (!state.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.payFineUi = payFineUi;  //change the name of the variable from 'ui' to 'payFineUi' as Admin - Arashdeep Kaur
		payFineUi.setState(PayFineUI.UI_STATE.READY);  //change the name of the variable from 'ui' to 'payFineUi' as Admin - Arashdeep Kaur
		payFineState = CONTROL_STATE.READY;	//change the name of the variable from 'state' to 'payFineState' as Admin - Arashdeep Kaur	
	}


	public void cardSwiped(int memberId) {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		member = library.getMember(memberId);
		
		if (member == null) {
			payFineUi.display("Invalid Member Id");  //change the name of the variable from 'ui' to 'payFineUi' as Admin - Arashdeep Kaur
			return;
		}
		payFineUi.display(member.toString());  //change the name of the variable from 'ui' to 'payFineUi' as Admin - Arashdeep Kaur
		payFineUi.setState(PayFineUI.UI_STATE.PAYING);  //change the name of the variable from 'ui' to 'payFineUi' as Admin - Arashdeep Kaur
		payFineState = CONTROL_STATE.PAYING;  //change the name of the variable from 'state' to 'payFineState' as Admin - Arashdeep Kaur
	}
	
	
	public void cancel() {
		payFineUi.setState(PayFineUI.UI_STATE.CANCELLED);  //change the name of the variable from 'ui' to 'payFineUi' as Admin - Arashdeep Kaur
		PayFineState = CONTROL_STATE.CANCELLED; //change the name of the variable from 'state' to 'payFineState' as Admin - Arashdeep Kaur
	}


	public double payFine(double amount) {
		if (!state.equals(CONTROL_STATE.PAYING)) {
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double change = member.payFine(amount);
		if (change > 0) {
			payFineUi.display(String.format("Change: $%.2f", change));  //change the name of the variable from 'ui' to 'payFineUi' as Admin - Arashdeep Kaur
		}
		payFineUi.display(member.toString()); //change the name of the variable from 'ui' to 'payFineUi' as Admin - Arashdeep Kaur
		payFineUi.setState(PayFineUI.UI_STATE.COMPLETED); //change the name of the variable from 'ui' to 'payFineUi' as Admin - Arashdeep Kaur
		payFineState = CONTROL_STATE.COMPLETED; //change the name of the variable from 'state' to 'payFineState' as Admin - Arashdeep Kaur
		return change;
	}
	


}
