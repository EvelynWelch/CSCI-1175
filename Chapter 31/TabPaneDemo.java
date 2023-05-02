// author: Evie Welch
// date: 05/02/23

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.ToggleGroup; 
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Side;

public class TabPaneDemo extends Application {   
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {   
    TabPane tabPane = new TabPane();
    Tab tab1 = new Tab("Line");
    StackPane pane1 = new StackPane();
    pane1.getChildren().add(new Line(10, 10, 80, 80));
    tab1.setContent(pane1);
    Tab tab2 = new Tab("Rectangle");
    tab2.setContent(new Rectangle(10, 10, 200, 200));
    Tab tab3 = new Tab("Circle");
    tab3.setContent(new Circle(50, 50, 20));    
    Tab tab4 = new Tab("Ellipse");
    tab4.setContent(new Ellipse(10, 10, 100, 80));
    tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);
    
  
    
    RadioButton topRB = new RadioButton("Top");
    topRB.setOnAction( e -> {
    	tabPane.setSide(Side.TOP);
    });
    RadioButton leftRB = new RadioButton("Left");
    leftRB.setOnAction(e -> {
    	tabPane.setSide(Side.LEFT);
    });
    RadioButton bottomRB = new RadioButton("Buttom");
    bottomRB.setOnAction(e -> {
    	tabPane.setSide(Side.BOTTOM);
    });
    RadioButton rightRB = new RadioButton("Right");
    rightRB.setOnAction(e ->{
    	tabPane.setSide(Side.RIGHT);
    });
    
    
    ToggleGroup rbGroup = new ToggleGroup();
    topRB.setToggleGroup(rbGroup);
    leftRB.setToggleGroup(rbGroup);
    bottomRB.setToggleGroup(rbGroup);
    rightRB.setToggleGroup(rbGroup);
    
    HBox rbBox = new HBox(10);
    rbBox.getChildren().addAll(topRB, leftRB, bottomRB,rightRB);
    
    VBox vBox = new VBox();
    vBox.getChildren().addAll(tabPane, rbBox);
    
//    tabPane.setSide(Side.RIGHT);
    
    
    Scene scene = new Scene(vBox, 300, 250);  
    primaryStage.setTitle("DisplayFigure"); // Set the window title
    primaryStage.setScene(scene); // Place the scene in the window
    primaryStage.show(); // Display the window
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   * line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}