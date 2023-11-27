package main;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculator {
	private Double num1;
	private Double num2;
	private Double result;
	
    private Stage primaryStage;
    private VBox mainLayout;
    private GridPane inputGP, buttonGP, resultGP;
    private Label num1LB, num2LB, resultLB;
    private TextField num1TF, num2TF, resultTF;
    private Button plus, minus, times, div, AC;

    public Calculator(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {

        // gridpane for input
        inputGP = new GridPane();
        num1LB = new Label("1st Number (Decimal) : ");
        num2LB = new Label("2nd Number (Decimal) : ");
        num1TF = new TextField();
        num2TF = new TextField();
        inputGP.add(num1LB, 0, 0);
        inputGP.add(num1TF, 1, 0);
        inputGP.add(num2LB, 0, 1);
        inputGP.add(num2TF, 1, 1);
        setGP(inputGP);
        inputGP.setHalignment(num1LB, HPos.RIGHT);
        inputGP.setHalignment(num2LB, HPos.RIGHT);

        // gridpane for button
        buttonGP = new GridPane();
        plus = createButton("+", 70, 50); 
        minus = createButton("-", 70, 50);
        times = createButton("×", 70, 50);
        div = createButton("÷", 70, 50);

        buttonGP.add(plus, 0, 0);
        buttonGP.add(minus, 0, 1);
        buttonGP.add(times, 1, 0);
        buttonGP.add(div, 1, 1);
        buttonGP.setAlignment(Pos.CENTER);
        buttonGP.setVgap(10);
        buttonGP.setHgap(10);
        
        
        // gridpane for output
        AC = createButton("Clear", 50, 20);
        resultGP = new GridPane();
        resultLB = new Label("Result : ");
        resultTF = new TextField("0");
        resultGP.add(resultLB, 0, 0);
        resultGP.add(resultTF, 1, 0);
        resultGP.add(AC, 1,2);
        setGP(resultGP);
        inputGP.setHalignment(resultLB, HPos.RIGHT);
        

        // main layout
        mainLayout = new VBox(inputGP, buttonGP, resultGP);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setMaxSize(200, 200);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setSpacing(20);

        Scene scene = new Scene(mainLayout, 370, 300);
        primaryStage.setTitle("Jonathan Samuel Tanadi - 2602051094");
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeyPress);
    }

    public void setGP(GridPane gp) {
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setMinWidth(175);
        gp.getColumnConstraints().add(column1);
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(10);
    }
    
    private Button createButton(String label, double minWidth, double minHeight) {
        Button button = new Button(label);
        button.setMinSize(minWidth, minHeight);
        button.setMnemonicParsing(false);
		button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: orange;"));
		button.setOnMouseExited(event -> button.setStyle(""));
		button.setOnAction(event -> buttonHandler(label));
        return button;
    }
    
    public void buttonHandler(String label) {
    	if(validateInput()) {
    		if (label.equals("+")) {
    		    plus();
    		} else if (label.equals("-")) {
    		    minus();
    		} else if (label.equals("×") || label.equals("*") ) {
    		    times();
    		} else if (label.equals("÷") || label.equals("/") ) {
    		    div();
    		} else if (label.equals("Clear")) {
    		    clear();
    		} else {
    		    Alert alert = new Alert(AlertType.ERROR);
    		    alert.setTitle("Invalid Button");
    		    alert.setContentText("Check the code");
    		    alert.showAndWait();
    		}

    	}
    }
    
    public boolean validateInput() {
    	num1 = 0.0;
    	num2 = 0.0;
    	result = 0.0;
    	try {
    		num1 = Double.parseDouble(num1TF.getText());
    		num2 = Double.parseDouble(num2TF.getText());
    		return true;
    	}catch(Exception e) {
    		showAlert(Alert.AlertType.ERROR,"Error","Invalid Input","Please fill the input with valid number");
    		return false;
    	}
    }
    
    public void plus() {
    	result = num1 + num2;
    	resultTF.setText(Double.toString(result));
    }
    
    public void minus() {
    	result = num1 - num2;
    	resultTF.setText(Double.toString(result));
    }
    
    public void times() {
    	result = num1 * num2;
    	resultTF.setText(Double.toString(result));
    }
    
    public void div() {
    	if(num2 != 0) { 
	    	result = num1 / num2;
	    	resultTF.setText(Double.toString(result));
    	}else {
    		resultTF.setText("Infinity");
    	}
    }
    
    public void clear() {
    	num1 = 0.0;
    	num2 = 0.0;
    	result = 0.0;
    	num1TF.setText(null);
    	num2TF.setText(null);
    	resultTF.setText("0");
    }
    
    private void handleKeyPress(KeyEvent event) {
        String keyText = event.getText();
        if (!keyText.isEmpty()) {
            if(keyText.matches("[+\\-*/]")) {
                buttonHandler(keyText);
            }
        }
    }
    
    public static void showAlert(Alert.AlertType alertType, String title,String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
