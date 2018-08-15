import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Member implements Serializable {
//change class name member to 'Member'
	private String lastName; //Change variable name LN to 'lastName' - Author: arashdeep kaur
	private String firstName; //change variable name FN to 'Firstname' - Author: arashdeep kaur
	private String email; //change variable name EM to 'email' - Author: arashdeep kaur
	private int phoneNo; //change variable name PN to 'phoneNo' - Author: arashdeep kaur
	private int id;  //change object name ID to 'id' - Author: arashdeep kaur
	private double fines; //change variable name FINES to 'fines' - Author: arashdeep kaur
	
	private Map<Integer, loan> LNS;

	
	public member(String lastName, String firstName, String email, int phoneNo, int id) {
		this.lastName = lastName; //change object name LN to 'lastName' - Author: arashdeep kaur
		this.firstName = firstName; //change object name FN to 'firstName' - Author: arashdeep kaur
		this.email = email;  //change object name EM to 'email' - Author: arashdeep kaur
		this.phoneNo = phoneNo;  //change object name PN to 'phoneNo' - Author: arashdeep kaur
		this.id = id;  //change object name ID to 'id' - Author: arashdeep kaur
		
		this.LNS = new HashMap<>();
	}

	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder(); //change the object name sb to 'stringzbuilder' - Author : Arashdeep kaur
		stringBuilder.append("Member:  ").append(id).append("\n") //change the object name ID to 'id' - Author : Arashdeep kaur
		  .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n") //change the object name LN to 'lastName' and FN to 'firstName'- Author : Arashdeep kaur
		  .append("  Email: ").append(email).append("\n") //change the object name EM to 'email' - Author : Arashdeep kaur
		  .append("  Phone: ").append(phoneNo)  //change the object name PN to 'phoneNo' - Author : Arashdeep kaur
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", fines)) //change the object name FINES to 'fines' - Author : Arashdeep kaur
		  .append("\n");   //Change the object name sb to 'stringBuilder' - Author : Arashdeep Kaur
		
		for (Loan loan : LNS.values())   //change the name loan to 'Loan' - Author : arashdeep kaur
		{
			stringBuilder.append(loan).append("\n"); //Change the object name sb to 'stringBuilder' - Author : Arashdeep Kaur
		}		  
		return stringBuilder.toString();  //Change the object name sb to 'stringBuilder' - Author : Arashdeep Kaur
	}

	
	public int getId() {
		return id; // change the object name ID to id- Author Arashdeep kaur
	}

	
	public List<loan> getLoans() {
		return new ArrayList<loan>(LNS.values());
	}

	
	public int getNumberOfCurrentLoans() {
		return LNS.size();
	}

	
	public double getFinesOwed() {
		return fines; //change name FINES to fines - Author: arashdeep kaur
	}

	
	public void takeOutLoan(Loan loan)  //change the name loan to 'Loan' - Author : arashdeep kaur
	{
		if (!LNS.containsKey(loan.getId())) {
			LNS.put(loan.getId(), loan);
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String getLastName() {
		return lastName; //change variable name LN to 'lastName' - Author: arashdeep kaur
	}

	
	public String getFirstName() {
		return firstName; //change variable name FN to 'firstName' -Auther: arashdeep kaur
	}


	public void addFine(double fine) {
		fines += fine;  //change variable name FINES to 'fines' - Author: arashdeep kaur
	}
	
	public double payFine(double amount) {
		if (amount < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (amount > fines) {
			change = amount - fines;
			fines = 0;
			//change variable name FINES to fines - Author: arashdeep kaur
		}
		else {
			fines -= amount;  //change variable name FINES to fines - Author: arashdeep kaur
		}
		return change;
	}


	public void dischargeLoan(Loan loan) //change the name loan to 'Loan' - Author : arashdeep kaur
	{
		if (LNS.containsKey(loan.getId())) {
			LNS.remove(loan.getId());
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
