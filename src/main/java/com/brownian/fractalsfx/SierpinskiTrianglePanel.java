package com.brownian.fractalsfx;

import java.util.EnumMap;
import java.util.List;

public class SierpinskiTrianglePanel extends SequenceDrawingPanel<SierpinskiTrianglePanel.DrawInstruction> {

    static final LSystem<DrawInstruction> L_SYSTEM;

    static {
        EnumMap<DrawInstruction, List<DrawInstruction>> rewriteRules = new EnumMap<>(DrawInstruction.class);
        rewriteRules.put(DrawInstruction.F, List.of(
                DrawInstruction.F,
                DrawInstruction.TURN_RIGHT,
                DrawInstruction.G,
                DrawInstruction.TURN_LEFT,
                DrawInstruction.F,
                DrawInstruction.TURN_LEFT,
                DrawInstruction.G,
                DrawInstruction.TURN_RIGHT,
                DrawInstruction.F
        ));
        rewriteRules.put(DrawInstruction.G, List.of(DrawInstruction.G, DrawInstruction.G));
        L_SYSTEM = new LSystem<>(rewriteRules, List.of(
                DrawInstruction.F,
                DrawInstruction.TURN_RIGHT,
                DrawInstruction.G,
                DrawInstruction.TURN_RIGHT,
                DrawInstruction.G
        ));
    }

    public SierpinskiTrianglePanel() {
        super(L_SYSTEM);
    }

    @Override
    public Turtle getNewTurtle() {
        return new Turtle(0, getHeight() * (1 - Math.sqrt(3) / 2), 60);
    }

    @Override
    protected Turtle.TurtleAction computeTurtleAction(DrawInstruction symbol) {
        return switch (symbol) {
            case F, G -> new Turtle.TurtleAction.DrawForward(getWidth() / Math.pow(2, getFractalLevel()));
            case TURN_LEFT -> new Turtle.TurtleAction.Turn(120);
            case TURN_RIGHT -> new Turtle.TurtleAction.Turn(-120);
        };
    }

    public enum DrawInstruction {
        F, G, TURN_LEFT, TURN_RIGHT
    }
}
