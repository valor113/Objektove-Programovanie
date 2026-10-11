package com.example.robot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class robot {

    private final Node robot;
    private double speedX = 3;
    private boolean isAutomatic = false;
    private Timeline robotTimeline;

    private final double BOUND_WIDTH = 1000.0;
    private final double BOUND_HEIGHT = 800.0;

    public robot(Node robot){
        this.robot = robot;

        robot.setOnMouseClicked(e ->{isAutomatic = !isAutomatic;
            if (isAutomatic) {setAutomaticMode();}
            else {setManualMode();}
        });

        robot.setFocusTraversable(true);
        robot.setOnKeyPressed(e -> {
            if(!isAutomatic) {
                double step = 10;
                double newX = robot.getLayoutX();
                double newY = robot.getLayoutY();

                double nodeWidth = robot.getLayoutBounds().getWidth();
                double nodeHeight = robot.getLayoutBounds().getHeight();

                if(e.getCode() == KeyCode.W) {
                    newY = Math.max(0, newY - step);
                }
                if(e.getCode() == KeyCode.S) {
                    newY = Math.max(0, newY + step);
                }
                if(e.getCode() == KeyCode.A) {
                    newX = Math.max(0, newX - step);
                }
                if(e.getCode() == KeyCode.D) {
                    newX = Math.max(0,newX + step);
                }

                robot.setLayoutX(newX);
                robot.setLayoutY(newY);
            }
        });

        robotTimeline = new Timeline(new KeyFrame(Duration.millis(20), e -> {
            if (isAutomatic) {
                double newX = robot.getLayoutX() + speedX;
                double nodeWidth = robot.getLayoutBounds().getWidth();

                if (newX + nodeWidth >= BOUND_WIDTH) {
                    newX= BOUND_WIDTH - nodeWidth;
                    speedX = -speedX;
                } else if (newX <= 0) {
                    newX = 0;
                    speedX = -speedX;
                }

                robot.setLayoutX(newX);
            }
        }));
        robotTimeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void setManualMode() {
        isAutomatic = false;
        robotTimeline.stop();
        robot.requestFocus();
    }

    public void setAutomaticMode() {
        isAutomatic = true;
        robotTimeline.play();
    }
}
