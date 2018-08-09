import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable {   //Change the class name from "book" to "Book" - BY GURPREET GILL
	
	private String title; //Change the variable name from "T" to "title" - BY GURPREET GILL
	private String author;  //Change the variable name from "A" to "author" - BY GURPREET GILL
	private String callNo;  //Change the variable name from "C" to "callNo" - BY GURPREET GILL
	private int id;  //Change the variable name from "ID" to "id" - BY GURPREET GILL
	
	private enum STATE { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private STATE state;
	
	
	public book(String author, String title, String callNo, int id) {
		this.author = author;  //Change the variable name from "this.A" to "this.author" - BY GURPREET GILL
		this.title = title;  //Change the variable name from "this.T" to "this.title" - BY GURPREET GILL
		this.callNo = callNo;  //Change the variable name from "this.C" to "this.callNo" - BY GURPREET GILL
		this.id = id;  //Change the variable name from "this.ID" to "this.id" - BY GURPREET GILL
		this.state = STATE.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();  //Change the object name from "sb" to "stringBuilder" - BY GURPREET GILL
		stringBuilder.append("Book: ").append(id).append("\n")  //Change the variable name from "ID" to "id" - BY GURPREET GILL
		  .append("  Title:  ").append(title).append("\n") //Change the variable name from "T" to "title" - BY GURPREET GILL
		  .append("  Author: ").append(author).append("\n")  //Change the variable name from "A" to "author" - BY GURPREET GILL
		  .append("  CallNo: ").append(callNo).append("\n")  //Change the variable name from "C" to "callNo" - BY GURPREET GILL
		  .append("  State:  ").append(state);
		
		return stringBuilder.toString();  //Change the object name from "sb" to "stringBuilder" - BY GURPREET GILL
	}

	public Integer id() {  //Change the method name from "ID" to "id" - BY GURPREET GILL
		return id;   //Change the name from "ID" to "id" - BY GURPREET GILL
	}

	public String title() {  //Change the method name from "Title" to "title" - BY GURPREET GILL
		return title;  //Change the variable name from "T" to "title" - BY GURPREET GILL
	}


	
	public boolean available() {  //Change the method name from "Available" to "available" - BY GURPREET GILL
		return state == STATE.AVAILABLE;
	}

	
	public boolean on_loan() {  //Change the method name from "On_loan" to "on_loan" - BY GURPREET GILL
		return state == STATE.ON_LOAN;
	}

	
	public boolean damaged() {  //Change the method name from "Damaged" to "damaged" - BY GURPREET GILL
		return state == STATE.DAMAGED;
	}

	
	public void Borrow() {
		if (state.equals(STATE.AVAILABLE)) {
			state = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
		}
		
	}


	public void Return(boolean DAMAGED) {
		if (state.equals(STATE.ON_LOAN)) {
			if (DAMAGED) {
				state = STATE.DAMAGED;
			}
			else {
				state = STATE.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));
		}		
	}

	
	public void Repair() {
		if (state.equals(STATE.DAMAGED)) {
			state = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
		}
	}


}
