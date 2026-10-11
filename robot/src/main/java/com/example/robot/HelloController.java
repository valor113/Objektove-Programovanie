package com.example.robot;

import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class HelloController {
    @FXML
    private Rectangle robotRect;
    @FXML
    private Circle eyeLeft;
    @FXML
    private Circle eyeRight;

    private robot robot;

    @FXML
    public void initialize() {
        if (robotRect != null) {
            robot = new robot(robotRect, eyeLeft, eyeRight);
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

    @FXML
    protected void handleColor() {
        if (robot != null) {
            robot.changeColor();
        }
    }

    @FXML
    protected void handleSize() {
        if (robot != null) {
            robot.changeSize();
        }
    }
}