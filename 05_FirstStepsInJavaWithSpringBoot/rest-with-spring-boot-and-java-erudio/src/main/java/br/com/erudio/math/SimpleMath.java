package br.com.erudio.math;

public class SimpleMath {
	
	public Double sum( Double numberOne, Double numberTwo ) {
		
		return numberOne + numberTwo;		
	}


	public Double sub(Double numberOne,	Double numberTwo)  {
		
		return numberOne - numberTwo;		
	}
		
	public Double mult(	Double numberOne, Double numberTwo)  {
		
		
		return numberOne * numberTwo;		
	}
	
	
	public Double div(Double numberOne, Double numberTwo)  {	
		
		return numberOne / numberTwo;		
	}
	
	
	public Double media(Double numberOne, Double numberTwo,	Double numberThree )  {		
		Double media = numberOne + numberTwo + numberThree;
		media = media / 3;
		return media; 		
	}
	
	public Double sqrt(	Double numberOne )  {		
		Double raiz = numberOne;
		raiz = Math.sqrt(raiz);
		return raiz; 		
	}

}
