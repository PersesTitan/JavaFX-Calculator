package com.example.javafxcalculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static com.example.javafxcalculator.Formula.Formula.formula;

public class HelloApplication extends Application implements EventHandler<ActionEvent> {

    Label label;
    Button[] buttons;
    String TotalText = "";
    Stage stage;
    VBox vBox = new VBox();
    HBox hBox = new HBox();
    HBox hBox1 = new HBox();
    HBox hBox2 = new HBox();
    HBox hBox3 = new HBox();
    HBox hBox4 = new HBox();
    HBox hBox5 = new HBox();
    HBox[] hBoxS = {hBox1, hBox2, hBox3, hBox4, hBox5};

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        buttons = new Button[17];
        final String[] texts = {
                "OFF", "AC", "/", "*",
                "1", "2", "3", "-",
                "4", "5", "6", "+",
                "7", "8", "9", "=",
                "0"};

        stage.setTitle("Calculator");

        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);

        label = new Label();
        label.setFont(new Font("Arial", 30));
        vBox.getChildren().add(label);

        for (int i = 0; i<buttons.length; i++) {
            buttons[i] = new Button(texts[i]);
            buttons[i].setOnAction(this);
            buttons[i].setPrefSize(50, 50);
            hBoxS[i/4].getChildren().add(buttons[i]);
        }
        vBox.getChildren().addAll(hBoxS);
        stage.setScene(new Scene(vBox, 200, 280));
        stage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        for (Button button : buttons) {
            button.setOnAction(act -> clickButton(act.getSource()));
        }
    }

    public static void main(String[] args) {
        launch();
    }

    private void clickButton(Object button) {
        Button btn = (Button) button;
        String buttonText = btn.getText();

        switch (buttonText) {
            case "OFF" -> stage.close();
            case "AC" -> TotalText = "";
            case "=" -> TotalText = Double.toString(formula(TotalText, true));
            default -> TotalText += buttonText;
        }
        label.setText(TotalText);
    }
}