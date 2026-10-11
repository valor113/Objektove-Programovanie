package com.example.robot;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public class HelloController {
    @FXML
    private Rectangle robotRect;
    private robot robot;

    @FXML
    public void initialize() {
        if (robotRect != null) {
            robot = new robot(robotRect);
        }
    }

    @FXML
    protected void handleManual() {
        if (robot != null) {
            robot.setManualMode();
        }
    }

    @FXML
    protected void handleAutomatic() {
        if (robot != null) {
            robot.setAutomaticMode();
        }
    }
}