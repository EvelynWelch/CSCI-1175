// author: Evie Welch
// date: 04/26/23

// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise33_01Server extends Application {
	// Text area for displaying contents
	private TextArea ta = new TextArea();
	private DataInputStream input;

	// Creates a thread that creates a Loan and writes the loan data back to the client
	class HandleLoan implements Runnable {

		private Socket socket;

		public HandleLoan(Socket socket) {
			this.socket = socket;
		}
		// creates a string with the loan data.
		public String log(Loan loan) {
			StringBuilder s = new StringBuilder();
			s.append("Annual Interest Rate: " + loan.getAnnualInterestRate() + "\n");
			s.append("Number Of Years: " + loan.getNumberOfYears() + "\n");
			s.append("Loan Amount: " + loan.getLoanAmount() + "\n");
			s.append("monthlyPayment: " + loan.getMonthlyPayment() + "\n");
			s.append("totalPayment: " + loan.getTotalPayment() + "\n");
			s.append("\n");
			return s.toString();
		}

		@Override
		public void run() {
			try {
				DataInputStream input = new DataInputStream(socket.getInputStream());
				DataOutputStream output = new DataOutputStream(socket.getOutputStream());
				while (true) {
					double interestRate = input.readDouble();
					int years = input.readInt();
					double loanAmount = input.readDouble();
					Loan loan = new Loan(interestRate, years, loanAmount);
					String l = log(loan);
					ta.appendText(l);
					output.writeUTF(l);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		ta.setWrapText(true);
		new Thread(() -> {
			try {
				ServerSocket serverSocket = new ServerSocket(8001);
				ta.appendText("Exercise33_01Sever started at " + new Date() + '\n');

				while (true) {
					Socket socket = serverSocket.accept();
					ta.appendText("Connected to a client at " + new Date() + '\n');
					input = new DataInputStream(socket.getInputStream());
					new Thread(new HandleLoan(socket)).start();
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					input.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}).start();

		// Create a scene and place it in the stage
		Scene scene = new Scene(new ScrollPane(ta), 400, 200);
		primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
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
