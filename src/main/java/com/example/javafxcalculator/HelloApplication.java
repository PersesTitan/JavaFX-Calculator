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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HelloApplication extends Application implements EventHandler<ActionEvent> {

    Button button0, button1, button2, button3, button4, button5, button6, button7;
    Button button8, button9, button10, button11, button12, button13, button14, button15, button16;
    Label label;
    Button[] buttons;
    String TotalText = "";
    Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        VBox vBox = new VBox();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        HBox hBox4 = new HBox();
        HBox hBox5 = new HBox();
        final String[] texts = {
                "OFF", "AC", "/", "*",
                "1", "2", "3", "-",
                "4", "5", "6", "+",
                "7", "8", "9", "=",
                "0"};
        buttons = new Button[]{
                button0, button1, button2, button3,
                button4, button5, button6, button7,
                button8, button9, button10, button11,
                button12, button13, button14, button15,
                button16};

        HBox[] hBoxS = new HBox[]{hBox1, hBox2, hBox3, hBox4, hBox5};

        stage.setTitle("Calculator");
        HBox hBox = new HBox();
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
            case "=" -> cal();
            default -> TotalText += buttonText;
        }
        label.setText(TotalText);
    }

    private void cal() {
        String[] numbers = TotalText.split("[*|/|\\-|+]");
        String[] texts = TotalText.split("[0-9]");

        List<String> numberList = new ArrayList<>(Arrays.asList(numbers));
        List<String> textList = new ArrayList<>(Arrays.asList(texts));
        textList.removeAll(Collections.singletonList(null));
        textList.removeAll(Collections.singletonList(""));
        assert numberList.size() == textList.size()+1;
        double total = Integer.parseInt(numberList.get(0));
        for (int i = 0; i<textList.size(); i++) {
            switch (textList.get(i)) {
                case "+" -> total += Integer.parseInt(numberList.get(i + 1));
                case "-" -> total -= Integer.parseInt(numberList.get(i + 1));
                case "*" -> total *= Integer.parseInt(numberList.get(i + 1));
                case "/" -> total /= Integer.parseInt(numberList.get(i + 1));
            }
        }

        TotalText = String.valueOf(total);
    }
}