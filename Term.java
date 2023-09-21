package edu.ilstu;

public class Term {
	
	private int coefficient;
	private int exponent;
	
	public Term(int coe, int exp) {
		this.coefficient = coe;
		this.exponent = exp;
	}
	
	public int getCoe() { // Return term's coefficient
		return coefficient;
	}
	
	public int getExp() { // Return term's exponenct
		return exponent;
	}
	
	public String toString() { //Static PolyDriver toString makes this obsolete
		if(coefficient == 1 && exponent == 1) 
			return "x";
		else if(coefficient == 1)
			return "x^" + exponent;
		else if(exponent == 1)
			return coefficient + "x";
		return coefficient + "x^" + exponent;
	}
	
	public void setCoe(int coe) {
		this.coefficient = coe;
	}
	
	public void setExp(int exp) {
		this.exponent = exp;
	}
}
