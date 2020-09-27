package ru.ifmo.se.task1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TgCalculationTest {

    private final double inputNumber;
    private final double expected;
    private final double delta = 0.0001;
    private final int exponent = 15;

    public TgCalculationTest(double inputNumber, double expected){
        this.inputNumber = inputNumber;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection inputNumbers(){
        return Arrays.asList(new Double[][] {
                { Math.PI/3, 1.732 },
                { -Math.PI/3, -1.732 },
                { -Math.PI/3 - 2*Math.PI, -1.732 },
                { Math.PI/3 + Math.PI, 1.732 },
                { 0.0, 0.0 },
                { Math.PI/4, 1.0 },
                { -Math.PI/4, -1.0 },
                { Math.PI/4 + 3*Math.PI, 1.0 },
                { -Math.PI/4 - 4*Math.PI, -1.0 }

        });
    }

    @Test
    public void tgTest() {
        Assert.assertEquals(expected,
                TgCalculation.tg(inputNumber, exponent), delta);
    }
}
