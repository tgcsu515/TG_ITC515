import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable {// Author Amandeep Kaur update the class name first letter from lower to upper case
	
	public static enum LOAN_STATE { CURRENT, OVER_DUE, DISCHARGED };
	
	private int loanID;// author Amandeep Kaur change the variable name ID to loanID
	private Book currentBook; // author Amandeep Kaur change the class name book to Book and variable name b to currentBook
	private Member currentMember; // author Amandeep Kaur change the the class name member to Member and variable name m to currentMember
	private Date dueDate; // author Amandeep Kaur change the variable name date to dueDate
	private LOAN_STATE loanState; // author Amandeep Kaur change the variable name state to loanState

	 // author Amandeep Kaur change the variable name and captalize the class name first letter
	public loan(int loanId, Book currentBook, Member currentMember, Date dueDate) 
	{
		this.loanID = loanId; // author Amandeep Kaur change the variable name ID to loanID
		this.currentBook = currentBook;// author Amandeep Kaur change the variable name b to currentBook  
		this.currentMember = currentMember; // author Amandeep Kaur change the variable name M to currentMember
		this.dueDate = dueDate; // author Amandeep Kaur change the variable name d to dueDate
		this.loanState = LOAN_STATE.CURRENT; // author Amandeep Kaur change the variable name state to loanState
	}

	
	public void checkOverDue() {
		if (loanState == LOAN_STATE.CURRENT &&  // author Amandeep Kaur change the variable name state to loanState
			Calendar.getInstance().Date().after(dueDate)) // author Amandeep Kaur change the variable name d to dueDate
			{
			this.loanState = LOAN_STATE.OVER_DUE; // author Amandeep Kaur change the variable name state to loanState
		}
	}

	
	public boolean isOverDue() {
		return loanState == LOAN_STATE.OVER_DUE; // author Amandeep Kaur change the variable name state to loanState
	}

	
	public Integer getId() {
		return loanID; // author Amandeep Kaur change the variable name ID to loanID
	}


	public Date getDueDate() {
		return dueDate; // author Amandeep Kaur change the variable name d to dueDate
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(ID).append("\n")
		  .append("  Borrower ").append(M.getId()).append(" : ")
		  .append(M.getLastName()).append(", ").append(M.getFirstName()).append("\n")
		  .append("  Book ").append(B.ID()).append(" : " )
		  .append(B.Title()).append("\n")
		  .append("  DueDate: ").append(sdf.format(D)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public member Member() {
		return M;
	}


	public book Book() {
		return B;
	}


	public void Loan() {
		state = LOAN_STATE.DISCHARGED;		
	}

}
