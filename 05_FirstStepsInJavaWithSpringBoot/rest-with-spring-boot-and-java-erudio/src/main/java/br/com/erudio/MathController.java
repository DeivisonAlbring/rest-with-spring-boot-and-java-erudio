package br.com.erudio;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {
		
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public Double sum(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		return convertToDouble(numberOne) + convertToDouble(numberTwo);		
	}
	
	@RequestMapping(value = "/sub/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public Double sub(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		return convertToDouble(numberOne) - convertToDouble(numberTwo);		
	}
	
	@RequestMapping(value = "/mult/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public Double mult(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		return convertToDouble(numberOne) * convertToDouble(numberTwo);		
	}
	
	@RequestMapping(value = "/div/{numberOne}/{numberTwo}",
			method = RequestMethod.GET)
	public Double div(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) throws Exception {
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		
		return convertToDouble(numberOne) / convertToDouble(numberTwo);		
	}
	
	@RequestMapping(value = "/media/{numberOne}/{numberTwo}/{numberThree}",
			method = RequestMethod.GET)
	public Double media(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo,
			@PathVariable(value = "numberThree") String numberThree
			) throws Exception {
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo) || !isNumeric(numberThree)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		Double media = convertToDouble(numberOne) + convertToDouble(numberTwo) + convertToDouble(numberThree);
		media = media / 3;
		return media; 		
	}
	
	
	@RequestMapping(value = "/sqrt/{numberOne}/{numberTwo}/{numberThree}",
			method = RequestMethod.GET)
	public Double sqrt(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo,
			@PathVariable(value = "numberThree") String numberThree
			) throws Exception {
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo) || !isNumeric(numberThree)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		Double raiz = convertToDouble(numberOne) + convertToDouble(numberTwo) + convertToDouble(numberThree);
		raiz = raiz / 3;
		return raiz; 		
	}
	
	

	private Double convertToDouble(String strNumber) {
		if (strNumber == null) return 0D;
	
		String number = strNumber.replaceAll(",", ".");
		if (isNumeric(number)) return Double.parseDouble(number);
		
		return 0D;
	}

	private boolean isNumeric(String strNumber) {
		if (strNumber == null) return false;				
		String number = strNumber.replaceAll(",", ".");			
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
	
}
