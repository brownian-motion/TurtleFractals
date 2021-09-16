package com.brownian.fractalsfx;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KochCurvePanelLSystemTest {
    @Test
    public void lSystem_level2() {
        assertEquals(
                List.of(
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_LEFT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_RIGHT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_RIGHT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_LEFT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_LEFT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_LEFT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_RIGHT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_RIGHT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_LEFT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_RIGHT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_LEFT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_RIGHT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_RIGHT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_LEFT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_RIGHT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_LEFT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_RIGHT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_RIGHT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_LEFT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_LEFT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_LEFT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_RIGHT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_RIGHT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD,
                        KochCurvePanel.DrawInstruction.ROTATE_LEFT,
                        KochCurvePanel.DrawInstruction.MOVE_FORWARD
                ),
                KochCurvePanel.L_SYSTEM.getSequence(2).toList()
        );
    }
}