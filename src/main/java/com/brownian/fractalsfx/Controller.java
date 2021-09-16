package com.brownian.fractalsfx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Controller {
    public StackPane fractal;
    private Timeline activeAnimation = null;

    public void showFractalAnimation(FractalAnimation fractalAnimation) {
        SequenceDrawingPanel<?> panel = fractalAnimation.panelConstructor.get();
        if (activeAnimation != null) {
            activeAnimation.stop();
        }
        this.fractal.getChildren().clear();
        panel.setWidth(this.fractal.getWidth() - 1);
        panel.setHeight(this.fractal.getWidth() - 1);
        this.fractal.getChildren().add(panel);

        activeAnimation = new Timeline(IntStream.range(0, fractalAnimation.maxDepth)
                .mapToObj(frameNum -> new KeyFrame(Duration.seconds(frameNum), e -> panel.setFractalLevel(frameNum)))
                .toArray(KeyFrame[]::new));

        Platform.runLater(activeAnimation::play);
    }

    public void showKochCurve90(ActionEvent actionEvent) {
        showFractalAnimation(FractalAnimation.KOCH_90);
    }

    public void showKochCurve120(ActionEvent actionEvent) {
        showFractalAnimation(FractalAnimation.KOCH_120);
    }

    public void showKochCurve60(ActionEvent actionEvent) {
        showFractalAnimation(FractalAnimation.KOCH_60);
    }

    public void showDragonCurve(ActionEvent actionEvent) {
        showFractalAnimation(FractalAnimation.DRAGON_CURVE);
    }

    public void showSierpinskiTriangle(ActionEvent actionEvent) {
        showFractalAnimation(FractalAnimation.SIERPINSKI_TRIANGLE);
    }

    public void showHilbertCurve(ActionEvent actionEvent) {
        showFractalAnimation(FractalAnimation.HILBERT_CURVE);
    }

    private enum FractalAnimation {
        KOCH_60(30, () -> new KochCurvePanel(60)),
        KOCH_90(30, () -> new KochCurvePanel(90)),
        KOCH_120(30, () -> new KochCurvePanel(120)),
        DRAGON_CURVE(20, DragonCurvePanel::new),
        SIERPINSKI_TRIANGLE(20, SierpinskiTrianglePanel::new),
        HILBERT_CURVE(10, HilbertCurvePanel::new);

        private final int maxDepth;
        private final Supplier<SequenceDrawingPanel<?>> panelConstructor;

        FractalAnimation(int maxDepth, Supplier<SequenceDrawingPanel<?>> panelConstructor) {
            this.maxDepth = maxDepth;
            this.panelConstructor = panelConstructor;
        }
    }
}
