// author: Evie Welch
// date: 04/27/23

import javafx.application.Application;
import javafx.application.Platform;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise33_09Client extends Application {
	private TextArea taServer = new TextArea();
	private TextArea taClient = new TextArea();

	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {

		taServer.setWrapText(true);
		taClient.setWrapText(true);
		taServer.setDisable(true);

		BorderPane pane1 = new BorderPane();
		pane1.setTop(new Label("History"));
		pane1.setCenter(new ScrollPane(taServer));
		BorderPane pane2 = new BorderPane();
		pane2.setTop(new Label("New Message"));
		pane2.setCenter(new ScrollPane(taClient));

		// connect to localhost and get i/o's
		try {
			socket = new Socket("localhost", 8001);
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// create thread that monitors input and updates taServer when data is available.
		new Thread(() -> {
			while (true) {
				try {
					if (input.available() > 0) {
						Platform.runLater(() -> {
							try {
								taServer.appendText("S: ");
								taServer.appendText(input.readUTF());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						});
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}).start();

		taClient.setOnKeyReleased(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				try {

					String toSend = taClient.getText();
					output.writeUTF(toSend);
					taServer.appendText("C: " + toSend);
					taClient.setText("");
				} catch (Exception outputE) {
					outputE.printStackTrace();
				}
			}
		});


		VBox vBox = new VBox(5);
		vBox.getChildren().addAll(pane1, pane2);

		// Create a scene and place it in the stage
		Scene scene = new Scene(vBox, 200, 200);
		primaryStage.setTitle("Exercise31_09Client"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		// To complete later

	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support. Not
	 * needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
