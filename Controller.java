package application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller implements Initializable {
	
	private BigDecimal left;
	private String selectedOperator;
	private boolean numberInputting;
	
	@FXML
	private TextField textField;
	
	public Controller(){
		this.left = BigDecimal.ZERO;
		this.selectedOperator = "";
		this.numberInputting = false;
	}

	//button controls 
	@FXML
	public void buttonPressed(ActionEvent evt){
		Button button = (Button)evt.getSource();
		String bText = button.getText();
		
		//clears all
		if(bText.equals("AC")){
			if(bText.equals("AC")){
				left = BigDecimal.ZERO;
			}
			selectedOperator = "";
			numberInputting = true;
			textField.clear();
			return;
		}
		//clears operator
		if(bText.equals("C")){
			selectedOperator = "";
			numberInputting = false;
			return;
		}
		//entering numbers and . 
		if(bText.matches("[0-9\\,.]")){
			if(!numberInputting){
				numberInputting = true;
				textField.clear();
			}
			textField.appendText(bText);
			return;
		}
		//entering operators 
		if(bText.matches("\\+") || bText.matches("\\*") || bText.matches("\\/") || bText.matches("\\-")){
			left = new BigDecimal(textField.getText());
			selectedOperator = bText;
			numberInputting = false;
			return;
		}
		//when equal is pressed 
		if(bText.equals("=")){
			final BigDecimal right = numberInputting ? new BigDecimal(textField.getText()) : left;
			
			left = math(selectedOperator,left, right);
			textField.setText(left.toString());
			numberInputting = false;
			return;
		}
		//flips signs 
		if(bText.equals("+/-")){
			left = new BigDecimal(textField.getText());
			if(left ==  BigDecimal.ZERO)
					return;
			left = left.negate();
			textField.setText(left.toString());
			return;
		}
		
	}
	
	//math of the calculator 
	public static BigDecimal math(String operator, BigDecimal left, BigDecimal right){
		switch(operator){
			case "+":
				return left.add(right);
			case "-":
				return left.subtract(right);
			case "*":
				return left.multiply(right);
			case "/":
				//rounding to 4 decimal places 
				return left.divide(right, 4, RoundingMode.HALF_UP);
		}
		return right;
	}
	
	//
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	
}
