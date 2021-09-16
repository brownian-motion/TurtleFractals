package com.brownian.fractalsfx;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

public class KochCurvePanel extends SequenceDrawingPanel<KochCurvePanel.DrawInstruction> {

    static final LSystem<DrawInstruction> L_SYSTEM;

    static {
        L_SYSTEM = new LSystem<>(
                new EnumMap<>(Collections.singletonMap(
                        DrawInstruction.MOVE_FORWARD,
                        List.of(
                                DrawInstruction.MOVE_FORWARD,
                                DrawInstruction.ROTATE_LEFT,
                                DrawInstruction.MOVE_FORWARD,
                                DrawInstruction.ROTATE_RIGHT,
                                DrawInstruction.MOVE_FORWARD,
                                DrawInstruction.ROTATE_RIGHT,
                                DrawInstruction.MOVE_FORWARD,
                                DrawInstruction.ROTATE_LEFT,
                                DrawInstruction.MOVE_FORWARD
                        )
                )),
                Collections.singletonList(DrawInstruction.MOVE_FORWARD)
        );
    }

    private final double rotationAngle;

    public KochCurvePanel(double rotationAngle) {
        super(L_SYSTEM);
        this.rotationAngle = rotationAngle;

        setFractalLevel(2);
    }

    @Override
    protected Turtle.TurtleAction computeTurtleAction(DrawInstruction instruction) {
        return switch (instruction) {
            case MOVE_FORWARD -> new Turtle.TurtleAction.DrawForward(getWidth() / Math.pow(3 + 2 * Math.cos(rotationAngle * Math.PI / 180), getFractalLevel()));
            case ROTATE_LEFT -> new Turtle.TurtleAction.Turn(rotationAngle);
            case ROTATE_RIGHT -> new Turtle.TurtleAction.Turn(-rotationAngle);
        };
    }

    @Override
    public Turtle getNewTurtle() {
        return new Turtle(0, getHeight() / 5, 0 /* right */);
    }

    public enum DrawInstruction {
        MOVE_FORWARD, ROTATE_LEFT, ROTATE_RIGHT
    }
}
