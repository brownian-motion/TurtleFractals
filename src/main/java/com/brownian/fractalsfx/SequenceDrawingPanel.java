package com.brownian.fractalsfx;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.stream.Stream;

public abstract class SequenceDrawingPanel<E extends Enum<E>> extends Canvas {
    private final SymbolSequence<E> sequence;
    private int fractalLevel = 0;

    public SequenceDrawingPanel(SymbolSequence<E> sequence) {
        this.sequence = sequence;
    }

    public abstract Turtle getNewTurtle();

    protected abstract Turtle.TurtleAction computeTurtleAction(E symbol);

    public int getFractalLevel() {
        return this.fractalLevel;
    }

    public void setFractalLevel(int fractalLevel) {
        this.fractalLevel = fractalLevel;

        this.getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
        this.getGraphicsContext2D().setFill(Color.WHITE);
        this.getGraphicsContext2D().fillRect(0, 0, getWidth(), getHeight());

        var turtle = getNewTurtle();

        var symbolSequence = this.sequence.getSequence(fractalLevel);

        Stream<Turtle.TurtleAction> turtleActions = symbolSequence.map(this::computeTurtleAction);

        turtle.putPenDown();
        turtle.processActions(this.getGraphicsContext2D(), turtleActions);
        turtle.liftPenUp();
    }
}
