package edu.ilstu;

import java.util.Iterator;

public class PolyDriver {

	public static void main(String[] args) { // Initializes several polynomials for testing
		
		SingleLinkedList<Term> poly1 = new SingleLinkedList<>();
		poly1.add(new Term(3, 4));
		poly1.add(new Term(2, 2));
		poly1.add(new Term(3, 1));
		poly1.add(new Term(7, 0));
		
		SingleLinkedList<Term> poly2 = new SingleLinkedList<>();
		poly2.add(new Term(2, 3));
		poly2.add(new Term(-5, 1));
		poly2.add(new Term(5, 0));
		
		SingleLinkedList<Term> poly3 = new SingleLinkedList<>();
		poly3.add(new Term(1, 2));
		poly3.add(new Term(3, 3));
		
		SingleLinkedList<Term> poly4 = new SingleLinkedList<>();
		poly4.add(new Term(5, 0));
		
		SingleLinkedList<Term> poly5 = new SingleLinkedList<>();
		poly5.add(new Term(2, 2));
		poly5.add(new Term(3, 1));
		poly5.add(new Term(7, 0));
		
		SingleLinkedList<Term> poly6 = new SingleLinkedList<>();
		poly6.add(new Term(2, 9));
		poly6.add(new Term(3, 0));
		
//		First polynomial = 3x^4+2x^3-x-8x^-1+5x^-3
		SingleLinkedList<Term> poly7 = new SingleLinkedList<>();
		poly7.add(new Term(3, 4));
		poly7.add(new Term(2, 3));
		poly7.add(new Term(-1, 1));
		poly7.add(new Term(-8, -1));
		poly7.add(new Term(5, -3));
		
//		Second polynomial = 2x^5-3x^4+6+8x^-1-4x^-3
		SingleLinkedList<Term> poly8 = new SingleLinkedList<>();
		poly8.add(new Term(2, 5));
		poly8.add(new Term(-3, 4));
		poly8.add(new Term(6, 0));
		poly8.add(new Term(8, -1));
		poly8.add(new Term(-4, -3));
		
		add(poly1, poly2);
//		add(poly3, poly4);
//		add(poly5, poly6);	
//		add(poly7, poly8);
		multiply(poly7, poly8);
		
	}
	
	private static void add(SingleLinkedList<Term> first, SingleLinkedList<Term> second) { // Adds two polynomials together
		first = simplify(first);   // Combines like terms in the first polynomial before beginning the addition process
		second = simplify(second); // Combines like terms in the second polynomial
		SingleLinkedList<Term> result = new SingleLinkedList<Term>(); // Initialize empty list that will store the sum
		Iterator<Term> iter1 = first.iterator();
		Iterator<Term> iter2 = second.iterator();
		
		while(iter1.hasNext()) {   // Iterates through first polynomial and adds all terms to the result list
			result.add(iter1.next());
		}
		while(iter2.hasNext()) {  // Iterates through second polynomial and adds all terms to the result list
			result.add(iter2.next());
		}
		
		System.out.println("1st Polynomial: " + toString(first)); // Displays first polynomial for reference
		System.out.println("2nd Polynomial: " + toString(second)); // Displays second polynomial
		System.out.println("Sum: " + toString(simplify(result)) + "\n"); // Displays simplified result

	}
	
	private static void multiply(SingleLinkedList<Term> first, SingleLinkedList<Term> second) {
		first = simplify(first);
		second = simplify(second);
		SingleLinkedList<Term> result = new SingleLinkedList<>();
		Iterator<Term> iter1 = first.iterator();
		
		while(iter1.hasNext()) {
			Iterator<Term> iter2 = second.iterator();
			Term term1 = iter1.next();
			
			while(iter2.hasNext()) {
				Term term2 = iter2.next();
				int newCoe = term1.getCoe() * term2.getCoe();
				int newExp = term1.getExp() + term2.getExp();
				result.add(new Term(newCoe, newExp));
			}
		}

		System.out.println("1st Polynomial: " + toString(first));
		System.out.println("2nd Polynomial: " + toString(second));
		System.out.println("Multiplication: " + toString(result));
		System.out.println("Simplified: " + toString(simplify(result)) + "\n");
	}
	
	private static String toString(SingleLinkedList<Term> poly) { // Returns polynomial with + / - signs
		Iterator<Term> iter = poly.iterator();
		String result = "";
		for(int i = 0; i<poly.size(); ++i) {
			Term current = iter.next();
			
			if (current.getExp() == 0) { // If exponent is 0
				if(i == 0)
					result += current.getCoe();
				else if(current.getCoe() > 0)
					result += "+" + current.getCoe();
				else
					result += current.getCoe();
			}
			
			else if(current.getExp() == 1) { // If exponent is 1
				if( i == 0 && current.getCoe() == 1)
					result += "x";
				else if(i == 0)
					result += current.getCoe() + "x";
				else if(current.getCoe() > 0)
					result += "+" + current.getCoe() + "x";
				else
					result += current.getCoe() + "x";
			}
			
			else if(current.getCoe() > 1) { // If coefficient is greater than 1 (If it is zero nothing will be added to result)
				if(i == 0) 
					result += current.getCoe() + "x^" + current.getExp();
				else
					result += "+" + current.getCoe() + "x^" + current.getExp();
			}
			
			else if(current.getCoe() == 1) { // If coefficient is 1
				if(i == 0)
					result += "x^" + current.getExp();
				else
					result += "+x^" + current.getExp();
			}
			
			else if(current.getCoe() < 0) { // If coefficient is negative
				result += current.getCoe() + "x^" + current.getExp();
			}
			
		}
		return result;
	}
	
	private static SingleLinkedList<Term> simplify(SingleLinkedList<Term> poly) { // Returns the given polynomial with combined like terms
		SingleLinkedList<Term> simplifiedPoly = new SingleLinkedList<Term>();
		Iterator<Term> iter1 = poly.iterator();
		int iter1Index = -1;
		
		while(iter1.hasNext()) {
			++iter1Index;
			Term term1 = iter1.next();
			Iterator<Term> iter2 = poly.iterator();
			int iter2Index = -1;
			
			while(iter2.hasNext()) {       
				++iter2Index;
				Term term2 = iter2.next();
				
				if(term1.getExp() == term2.getExp() && iter1Index != iter2Index) { // If exponents are equal and not at same index
					int newCoe = term1.getCoe() + term2.getCoe();
					term1.setCoe(newCoe);                         // Update first iter's term with sum of coefficients
					poly.remove(iter2Index);            // Remove the second iter's term once it has been combined with first
					--iter2Index;
				}
				
			}
		}
		return poly;
	}

}
