package ch.makery.address.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.util.UUID;

import base.RateDAL;
import ch.makery.address.MainApp;
import ch.makery.address.model.Rate;


public class MortgageController {
	private static double theIncome;
	
	private static double theExpense;
	
	private static int theCreditScore;
	
	private static int theTerms;
	
	private static double theHouseCost;
	
	private static double theInterestRate;
	
	private static double monthlyPayment;
	
	private static String finalMonthlyPayment;
	
	@FXML
	public Button calculateMortgage;
	
	@FXML
	public Label income;
	
	@FXML
	public Label expense;
	
	@FXML
	public Label creditScore;
	
	@FXML
	public Label houseCost;
	
	@FXML
	public Label term;
	
	@FXML
	public TextField txtIncome;
	
	@FXML
	public TextField txtExpenses;
	
	@FXML
	public TextField txtCreditScore;
	
	@FXML
	public TextField txtHouseCost;
	
	@FXML
	public ComboBox cmbLoanTerm;
		
	@FXML
	public Label lblMortgagePayment;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MortgageController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }
    
    public void SetComboBox() {
    	//To create a new combo box with 2 items 15 and 30.
    	ObservableList<String> terms = FXCollections.observableArrayList(
    			"15",
    			"30"
    	); 
    	//To set 2 terms into combo box
    	cmbLoanTerm.setItems(terms);
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
    	
        this.mainApp = mainApp;
    }
    
    @FXML
    //To handle Button calculateMortgage
    private void calculateMortgagePayment() {	
    	//To get entered income, and set to a label.
    	//After text field is entered, the income label will be set visible 
    	//and text field will be set invisible
    	income.setText(txtIncome.getText());
    	income.setVisible(true);
    	txtIncome.setVisible(false);
    	
    	//To get entered monthlyExpense, and set to a label
    	//After text field is entered, the expense label will be set visible 
    	//and text field will be set invisible
    	expense.setText(txtExpenses.getText());
    	expense.setVisible(true);
    	txtExpenses.setVisible(false);
    	
    	//To get entered credit score, and set to a label
    	//After text field is entered, the credit score label will be set visible 
    	//and text field will be set invisible
    	creditScore.setText(txtCreditScore.getText());
    	creditScore.setVisible(true);
    	txtCreditScore.setVisible(false);
    	
    	//To get entered house cost, and set to a label
    	//After text field is entered, the house cost label will be set visible 
    	//and text field will be set invisible
    	houseCost.setText(txtHouseCost.getText());
    	houseCost.setVisible(true);
    	txtHouseCost.setVisible(false);
    	
    	//To create an new instance of RateDAL class
    	RateDAL interestRate = new RateDAL();
    	
    	//To get the selected terms, and change it to integer. 
    	//Then assign it to variable theTerms
    	theTerms = Integer.parseInt(cmbLoanTerm.getValue().toString());
    	
    	//Change the entered house cost, credit score, expense and income to integers, 
    	//and assign them to variables
    	theHouseCost = Integer.parseInt(txtHouseCost.getText().toString());
    	theCreditScore = Integer.parseInt(txtCreditScore.getText().toString());
    	theExpense = Integer.parseInt(txtCreditScore.getText().toString());
    	theIncome = Integer.parseInt(txtIncome.getText().toString());
    	
    	//To get the specific interest rate from database by passing a credit score
    	theInterestRate = (interestRate.getRate(theCreditScore).getInterestRate() * 0.01);
    	
    	//To calculate monthly payment
    	monthlyPayment = theHouseCost * theInterestRate / (1 - 1/Math.pow(1 + theInterestRate, theTerms * 12));
    	
    	//To check whether or not the entered house cost is too high
    	if ((monthlyPayment <= (theIncome * 0.36)) && (monthlyPayment <= (theIncome + theExpense) * 0.28)) {
    		lblMortgagePayment.setText("House cost too high!");
    	}
    	else {
    		//Create a new decimal format, and make monthly payment in 2 decimal numbers
    		DecimalFormat df = new DecimalFormat("#.00");
        	
        	finalMonthlyPayment = df.format(monthlyPayment);
        	  	
    		lblMortgagePayment.setText(finalMonthlyPayment);
    	}
    }
}