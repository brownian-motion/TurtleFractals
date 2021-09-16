package com.brownian.fractalsfx;

import javafx.scene.canvas.GraphicsContext;

import java.util.stream.Stream;

public class Turtle {
    private double x, y;
    private double angle;
    private boolean isPenDown = false;

    public Turtle(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public Turtle(double x, double y) {
        this(x, y, 0);
    }

    public void processAction(GraphicsContext graphicsContext, TurtleAction action) {
        if (action instanceof TurtleAction.DrawForward step) {
            moveForward(graphicsContext, step);
        } else if (action instanceof TurtleAction.Turn turn) {
            rotateBy(turn);
        } else {
            throw new IllegalArgumentException("Invalid action " + action);
        }
    }

    private void rotateBy(TurtleAction.Turn turn) {
        angle += turn.degrees;
    }

    private void moveForward(GraphicsContext graphicsContext, TurtleAction.DrawForward step) {
        double x_ = x + step.distance * Math.cos(angle * Math.PI / 180), y_ = y + step.distance * Math.sin(angle * Math.PI / 180);
        if (isPenDown) {
            double height = graphicsContext.getCanvas().getHeight();
            graphicsContext.strokeLine(x, height - y, x_, height - y_);
        }
        this.x = x_;
        this.y = y_;
    }

    public void processActions(GraphicsContext context, Stream<TurtleAction> actions) {
        actions.forEachOrdered(action -> processAction(context, action));
    }

    public void putPenDown() {
        this.isPenDown = true;
    }

    public void liftPenUp() {
        this.isPenDown = false;
    }

    public void moveTo(double x, double y) {
        if (isPenDown) {
            liftPenUp();
            this.x = x;
            this.y = y;
            putPenDown();
        } else {
            this.x = x;
            this.y = y;
        }

    }

    public sealed interface TurtleAction permits TurtleAction.DrawForward, TurtleAction.Turn {
        record DrawForward(double distance) implements TurtleAction {
        }

        record Turn(double degrees) implements TurtleAction {
        }
    }
}
