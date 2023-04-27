// author: Evie Welch
// date: 04/26/23

// Exercise31_01Client.java: The client sends the input to the server and receives
// result back from the server
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise33_01Client extends Application {
	// Text field for receiving radius
	private TextField tfAnnualInterestRate = new TextField();
	private TextField tfNumOfYears = new TextField();
	private TextField tfLoanAmount = new TextField();
	private Button btSubmit = new Button("Submit");
	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream output = null;

	// instantiate the socket, and its input and output.
	private void connect() {
		try {
			socket = new Socket("localhost", 8001);
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Text area to display contents
	private TextArea ta = new TextArea();

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {

		ta.setWrapText(true);

		GridPane gridPane = new GridPane();
		gridPane.add(new Label("Annual Interest Rate"), 0, 0);
		gridPane.add(new Label("Number Of Years"), 0, 1);
		gridPane.add(new Label("Loan Amount"), 0, 2);
		gridPane.add(tfAnnualInterestRate, 1, 0);
		gridPane.add(tfNumOfYears, 1, 1);
		gridPane.add(tfLoanAmount, 1, 2);
		gridPane.add(btSubmit, 2, 1);

		// make the submit button write loan info to the socket
		btSubmit.setOnAction(e -> {
			// If socket hasn't been instantiated do so.
			if(socket == null) {
				connect();
			}
			// get the data to send
			String annualInterestRate = tfAnnualInterestRate.getText().trim();
			String numberOfYears = tfNumOfYears.getText().trim();
			String loanAmount = tfLoanAmount.getText().trim();
			try {
				// write the data to the client
				output.writeDouble(Double.valueOf(annualInterestRate));
				output.writeInt(Integer.valueOf(numberOfYears));
				output.writeDouble(Double.valueOf(loanAmount));
				output.flush();
				// get the response from the server
				ta.appendText(input.readUTF());
			} catch (Exception buttonEx) {
				buttonEx.printStackTrace();
			}
		});

		tfAnnualInterestRate.setAlignment(Pos.BASELINE_RIGHT);
		tfNumOfYears.setAlignment(Pos.BASELINE_RIGHT);
		tfLoanAmount.setAlignment(Pos.BASELINE_RIGHT);

		tfLoanAmount.setPrefColumnCount(5);
		tfNumOfYears.setPrefColumnCount(5);
		tfLoanAmount.setPrefColumnCount(5);

		BorderPane pane = new BorderPane();
		pane.setCenter(new ScrollPane(ta));
		pane.setTop(gridPane);

		// Create a scene and place it in the stage
		Scene scene = new Scene(pane, 400, 250);
		primaryStage.setTitle("Exercise31_01Client"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support. Not
	 * needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
