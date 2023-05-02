// Author: Evie Welch
// date: 05/02/23

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Menu;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class Exercise31_17 extends Application {
	private TextField tfInvestAmount;	
	private TextField tfNumberOfYears;
	private TextField tfAnnualInterestRate;
	private TextField tfFutureValue;
	
	private void calculate() {
		// get the values
		double investAmount = Double.parseDouble(tfInvestAmount.getText());		
		int numberOfYears = Integer.parseInt(tfNumberOfYears.getText());
		double annualInterest = Double.parseDouble(tfAnnualInterestRate.getText()) / 100;
		
		// calculate the Future Value
		double monthlyInterestRate = annualInterest / 12;
		double pwd = Math.pow(1 + monthlyInterestRate, numberOfYears * 12) ;
		double futureValue = investAmount * pwd;

		// set future value TextField text to the calculated amount
		tfFutureValue.setText(String.format("$%.2f", futureValue));
		
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// create menu items and add them to the menuBar
		MenuBar menuBar = new MenuBar();
		Menu menuOperation = new Menu("Operation");
		menuBar.getMenus().addAll(menuOperation);
		MenuItem menuItemCalculate = new MenuItem("Calculate");
		MenuItem menuItemExit = new MenuItem("Exit");
		menuOperation.getItems().addAll(menuItemCalculate, menuItemExit);

		// Create the text fields and labels
		Label investmentAmountLabel = new Label("Investement Amount: ");
		tfInvestAmount = new TextField();
		tfInvestAmount.setAlignment(Pos.BASELINE_RIGHT);

		Label numberOfYearsLabel = new Label("Number of Years: ");
		tfNumberOfYears = new TextField();
		tfNumberOfYears.setAlignment(Pos.BASELINE_RIGHT);

		Label annualInterestLabel = new Label("Annual Interest Rate: ");
		tfAnnualInterestRate = new TextField();
		tfAnnualInterestRate.setAlignment(Pos.BASELINE_RIGHT);	
		
		Label futureValueLabel = new Label("Future Value: ");
		tfFutureValue = new TextField();
		tfFutureValue.setAlignment(Pos.BASELINE_RIGHT);
		
		HBox btBox = new HBox(5);
		Button btCalculate = new Button("Calculate");
		btBox.setAlignment(Pos.BASELINE_RIGHT);
		btBox.getChildren().addAll(btCalculate);

		// Add button and menu actions
		btCalculate.setOnAction( e -> calculate());
		menuItemCalculate.setOnAction( e -> calculate());
		menuItemExit.setOnAction( e -> System.exit(0));
		
		// create the primary pane and add everything to it.
		VBox vBox = new VBox(10);
		
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		
		grid.add(investmentAmountLabel, 0, 0);
		grid.add(tfInvestAmount, 1, 0);
		
		grid.add(numberOfYearsLabel, 0, 1);
		grid.add(tfNumberOfYears, 1, 1);
		
		grid.add(annualInterestLabel, 0, 2);
		grid.add(tfAnnualInterestRate, 1, 2);
		
		grid.add(futureValueLabel, 0, 3);
		grid.add(tfFutureValue, 1, 3);
	
		grid.add(btBox, 1, 4);
		
		
		vBox.getChildren().addAll(menuBar, grid); 
		
		Scene scene = new Scene(vBox, 300, 225);
		primaryStage.setTitle("MenuDemo"); // Set the window title
		primaryStage.setScene(scene); // Place the scene in the window
		primaryStage.show(); // Display the window

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
