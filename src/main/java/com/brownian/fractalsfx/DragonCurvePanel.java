package com.brownian.fractalsfx;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

public class DragonCurvePanel extends SequenceDrawingPanel<DragonCurvePanel.DrawInstruction> {

    static final LSystem<DrawInstruction> L_SYSTEM;

    static {
        EnumMap<DrawInstruction, List<DrawInstruction>> rewriteRules = new EnumMap<>(DrawInstruction.class);
        rewriteRules.put(DrawInstruction.F, List.of(DrawInstruction.F, DrawInstruction.TURN_LEFT, DrawInstruction.G));
        rewriteRules.put(DrawInstruction.G, List.of(DrawInstruction.F, DrawInstruction.TURN_RIGHT, DrawInstruction.G));
        L_SYSTEM = new LSystem<>(rewriteRules, Collections.singletonList(DrawInstruction.F));
    }

    public DragonCurvePanel() {
        super(L_SYSTEM);
    }

    @Override
    public Turtle getNewTurtle() {
        return new Turtle(getWidth() / 4, getHeight() * 0.6, -45 * getFractalLevel());
    }

    @Override
    protected Turtle.TurtleAction computeTurtleAction(DrawInstruction symbol) {
        return switch (symbol) {
            case F, G -> new Turtle.TurtleAction.DrawForward(0.5 * getWidth() / Math.pow(2, getFractalLevel() / 2.0));
            case TURN_LEFT -> new Turtle.TurtleAction.Turn(90);
            case TURN_RIGHT -> new Turtle.TurtleAction.Turn(-90);
        };
    }

    public enum DrawInstruction {
        F, G, TURN_LEFT, TURN_RIGHT
    }
}
