package com.example.premena_jednotiek;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML private TextField inputField;
    @FXML private Label resultMg;
    @FXML private Label resultG;
    @FXML private Label resultKg;
    @FXML private Label resultT;

    protected String currentMetric = "";

   @FXML void handleMetric (ActionEvent event) {
       Button button = (Button) event.getSource();
       currentMetric = button.getText();

       try {
           String vstup = inputField.getText();
           Double hodnota = Double.parseDouble(vstup);

           double gramy = 0;
           switch (currentMetric){
               case "mg":
                   gramy = hodnota / 1000.0;
                   break;
               case "g":
                   gramy = hodnota;
                   break;
               case "kg":
                   gramy = hodnota * 1000.0;
                   break;
               case "t":
                   gramy = hodnota * 1_000_000.0;
                   break;
           }
           double mg = gramy * 1000.0;
           double g = gramy;
           double kg = gramy / 1000.0;
           double t = gramy / 1_000_000.0;

           resultMg.setText(String.format("%.2f", mg));
           resultG.setText(String.format("%.2f", g));
           resultKg.setText(String.format("%.4f", kg));
           resultT.setText(String.format("%.6f", t));
       }
       catch (NumberFormatException e) {
           resultMg.setText("chyba");
           resultG.setText("zadaj cislo");
           resultKg.setText("");
           resultT.setText("");
       }
   }
}
