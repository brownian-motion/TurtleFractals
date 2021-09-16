package com.brownian.fractalsfx;

import java.util.List;
import java.util.stream.Stream;

public class HilbertCurvePanel extends SequenceDrawingPanel<HilbertCurvePanel.DrawingMovement> {

    public HilbertCurvePanel() {
        super(new HilbertCurveSequence());
    }

    @Override
    public Turtle getNewTurtle() {
        return new Turtle(
                getWidth() * Math.pow(0.5, getFractalLevel() + 2),
                getHeight() * Math.pow(0.5, getFractalLevel() + 2),
                90 // always start facing up; the inductive sequence will take care of rotating properly
        );
    }

    @Override
    protected Turtle.TurtleAction computeTurtleAction(DrawingMovement symbol) {
        return switch (symbol) {
            case Forward -> new Turtle.TurtleAction.DrawForward(getWidth() * Math.pow(0.5, getFractalLevel() + 1));
            case TurnLeft -> new Turtle.TurtleAction.Turn(90);
            case TurnRight -> new Turtle.TurtleAction.Turn(-90);
        };
    }

    public enum DrawingMovement {
        Forward, TurnRight, TurnLeft
    }

    private static class HilbertCurveSequence extends InductiveSequence<DrawingMovement> {
        @Override
        protected Stream<DrawingMovement> getInitialSequence() {
            return Stream.of(
                    DrawingMovement.Forward,
                    DrawingMovement.TurnRight,
                    DrawingMovement.Forward,
                    DrawingMovement.TurnRight,
                    DrawingMovement.Forward
            );
        }

        @Override
        protected Stream<DrawingMovement> generateNextSequence(List<DrawingMovement> previousSequence) {
            List<Stream<DrawingMovement>> subsequences = List.of(
                    Stream.of(DrawingMovement.TurnRight), // gets us into the right position
                    flipLeftRight(previousSequence.stream()),
                    Stream.of(DrawingMovement.TurnRight, DrawingMovement.Forward),
                    previousSequence.stream(),
                    Stream.of(DrawingMovement.TurnLeft, DrawingMovement.Forward, DrawingMovement.TurnLeft),
                    previousSequence.stream(),
                    Stream.of(DrawingMovement.Forward, DrawingMovement.TurnRight),
                    flipLeftRight(previousSequence.stream()),
                    Stream.of(DrawingMovement.TurnRight) // gets us pointed down like we would expect, so they all end consistently
            );

            return subsequences.stream().flatMap(e -> e);
        }

        private Stream<DrawingMovement> flipLeftRight(Stream<DrawingMovement> sequence) {
            return sequence.map(symbol -> switch (symbol) {
                case TurnLeft -> DrawingMovement.TurnRight;
                case TurnRight -> DrawingMovement.TurnLeft;
                case Forward -> DrawingMovement.Forward;
            });
        }
    }

}
