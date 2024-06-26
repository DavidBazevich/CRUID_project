package com.example.lab_9.Animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition translateTransition;

    public Shake(Node node) {
        translateTransition = new TranslateTransition(Duration.millis(90), node);
        translateTransition.setFromX(0f);
        translateTransition.setByX(8f);
        translateTransition.setCycleCount(3);
        translateTransition.setAutoReverse(true);
    }

    public void playAnimation(){
        translateTransition.playFromStart();
    }

}