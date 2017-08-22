package refactoring;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

	private String _name;
	private Vector _rentals = new Vector();

	public Customer(String name){
		_name = name;
	}

	public void addRental(Rental arg){
		_rentals.addElement(arg);
	}

	public String getName(){
		return _name;
	}

	public String statement(){

		int frequentRenterPoints = 0;
		Enumeration rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasMoreElements()){
			Rental each = (Rental) rentals.nextElement();

			// レンタルポイントを加算
			frequentRenterPoints += each.getFrequentRenterPoints();

			// この貸出に関する数値の表示
			result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(getTotalCharge()) + "\n";
		}

		// フッタ部分の追加
		result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
		result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
		return result;
	}

	public String htmlStatement(){
		Enumeration rentals = _rentals.elements();
		String result = "<H1>Rentals for <EM>" + getName() + "</EM></H1><P>\n";
		while(rentals.hasMoreElements()){
			Rental each = (Rental) rentals.nextElement();
			// この貸出に関する数値の表示
			result += each.getMovie().getTitle() + ": " + String.valueOf(each.getCharge()) + "<BR>\n";
		}
		// フッタ部分の追加
		result += "<P>You owe <EM>" + String.valueOf(getTotalCharge()) + "</EM><P>\n";
		result += "On this rental you earned <EM>" + String.valueOf(getTotalFrequentRenterPoints()) + "</EM> frequent renter points<p>";
		return result;
	}

	public double amountFor(Rental aRental){
		return aRental.getCharge();
	}

	private double getTotalCharge() {
		double result = 0;
		Enumeration rentals = _rentals.elements();
		while(rentals.hasMoreElements()){
			Rental each = (Rental) rentals.nextElement();
			result += each.getCharge();
		}
		return result;
	}

	private int getTotalFrequentRenterPoints(){
		int result = 0;
		Enumeration rentals = _rentals.elements();
		while (rentals.hasMoreElements()){
			Rental each = (Rental) rentals.nextElement();
			result += each.getFrequentRenterPoints();
		}
		return result;
	}

}
