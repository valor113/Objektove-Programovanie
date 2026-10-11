package com.example.robot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class robot {

    private final Rectangle robot;
    private final Circle eyeLeft;
    private final Circle eyeRight;

    private double speedX = 3;
    private double speedY = 2;
    private boolean isAutomatic = false;
    private Timeline robotTimeline;

    private final double BOUND_WIDTH = 1000.0;
    private final double BOUND_HEIGHT = 800.0;

    private int sizeState = 0;
    private final double[] scaleSteps = {1.0, 1.25, 1.5, 1.75};

    private int colorState = 0;

    public robot(Rectangle robot, Circle eyeLeft, Circle eyeRight){
        this.robot = robot;
        this.eyeLeft = eyeLeft;
        this.eyeRight = eyeRight;

        if (eyeLeft != null && eyeRight != null) {
            eyeLeft.layoutXProperty().bind(robot.layoutXProperty().add(robot.widthProperty().multiply(0.2)));
            eyeLeft.layoutYProperty().bind(robot.layoutYProperty().add(robot.heightProperty().multiply(0.25)));

            eyeRight.layoutXProperty().bind(robot.layoutXProperty().add(robot.widthProperty().multiply(0.6)));
            eyeRight.layoutYProperty().bind(robot.layoutYProperty().add(robot.heightProperty().multiply(0.25)));
        }

        robot.setOnMouseClicked(e -> {
            isAutomatic = !isAutomatic;
            if (isAutomatic) { setAutomaticMode(); }
            else { setManualMode(); }
        });

        robot.setFocusTraversable(true);
        robot.setOnKeyPressed(e -> {
            if(!isAutomatic) {
                double step = 10;
                double newX = robot.getLayoutX();
                double newY = robot.getLayoutY();

                double nodeWidth = robot.getLayoutBounds().getWidth() * robot.getScaleX();
                double nodeHeight = robot.getLayoutBounds().getHeight() * robot.getScaleY();

                if(e.getCode() == KeyCode.W) {
                    newY = Math.max(0, newY - step);
                    updateEyes("UP");
                }
                if(e.getCode() == KeyCode.S) {
                    newY = Math.min(BOUND_HEIGHT - nodeHeight, newY + step);
                    updateEyes("DOWN");
                }
                if(e.getCode() == KeyCode.A) {
                    newX = Math.max(0, newX - step);
                    updateEyes("LEFT");
                }
                if(e.getCode() == KeyCode.D) {
                    newX = Math.min(BOUND_WIDTH - nodeWidth, newX + step);
                    updateEyes("RIGHT");
                }

                robot.setLayoutX(newX);
                robot.setLayoutY(newY);
            }
        });

        robotTimeline = new Timeline(new KeyFrame(Duration.millis(20), e -> {
            if (isAutomatic) {
                double newX = robot.getLayoutX() + speedX;
                double nodeWidth = robot.getLayoutBounds().getWidth() * robot.getScaleX();
                double nodeHeight = robot.getLayoutBounds().getHeight() * robot.getScaleY();

                if (newX + nodeWidth >= BOUND_WIDTH) {
                    newX = BOUND_WIDTH - nodeWidth;
                    speedX = -speedX;
                    updateEyes("LEFT");
                } else if (newX <= 0) {
                    newX = 0;
                    speedX = -speedX;
                    updateEyes("RIGHT");
                } else {
                    if (speedX > 0) {
                        updateEyes("RIGHT");
                    } else {
                        updateEyes("LEFT");
                    }
                }

                double currentY = robot.getLayoutY();
                if (currentY < 0) {
                    robot.setLayoutY(0);
                } else if (currentY + nodeHeight > BOUND_HEIGHT) {
                    robot.setLayoutY(BOUND_HEIGHT - nodeHeight);
                }

                robot.setLayoutX(newX);
            }
        }));
        robotTimeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void updateEyes(String direction) {
        if (eyeLeft == null || eyeRight == null) return;

        switch (direction) {
            case "LEFT":
                eyeLeft.setVisible(true);
                eyeRight.setVisible(false);
                break;
            case "RIGHT":
                eyeLeft.setVisible(false);
                eyeRight.setVisible(true);
                break;
            case "DOWN":
                eyeLeft.setVisible(true);
                eyeRight.setVisible(true);
                break;
            case "UP":
                eyeLeft.setVisible(false);
                eyeRight.setVisible(false);
                break;
        }
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

    public void changeSize() {
        sizeState = (sizeState + 1) % scaleSteps.length;
        double scale = scaleSteps[sizeState];
        robot.setScaleX(scale);
        robot.setScaleY(scale);
        if (eyeLeft != null && eyeRight != null) {
            eyeLeft.setScaleX(scale);
            eyeLeft.setScaleY(scale);
            eyeRight.setScaleX(scale);
            eyeRight.setScaleY(scale);
        }

        double nodeWidth = robot.getLayoutBounds().getWidth() * scale;
        double nodeHeight = robot.getLayoutBounds().getHeight() * scale;

        if (robot.getLayoutX() + nodeWidth > BOUND_WIDTH) {
            robot.setLayoutX(BOUND_WIDTH - nodeWidth);
        }
        if (robot.getLayoutY() + nodeHeight > BOUND_HEIGHT) {
            robot.setLayoutY(BOUND_HEIGHT - nodeHeight);
        }
    }

    public void changeColor() {
        switch(colorState) {
            case 0: robot.setFill(Color.RED); break;
            case 1: robot.setFill(Color.ORANGE); break;
            case 2: robot.setFill(Color.GREEN); break;
            case 3: robot.setFill(Color.BLUE); break;
        }

        if (colorState == 3) {
            colorState = 0;
        } else {
            colorState++;
        }
    }
}