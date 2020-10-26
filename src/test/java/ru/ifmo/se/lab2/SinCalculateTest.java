package ru.ifmo.se.lab2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class SinCalculateTest {
    Sin sin = new Sin();
    double x;
    double p = 0.001;
    double expected;


    public SinCalculateTest(double x, double expected){
        this.x = x;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection inputNumbers(){
        return Arrays.asList(new Double[][] {
                {0.0, 0.0},
                {Math.PI/6, 0.5},
                {Math.PI/3, 0.866},
                {Math.PI/4, 0.707},
                {Math.PI/2, 1.0},
                {-Math.PI/6, -0.5},
                {-Math.PI/3, -0.866},
                {-Math.PI/4, -0.707},
                {-Math.PI/2, -1.0},
                {2 * Math.PI, 0.0},
                {Math.PI/6 - 4 * Math.PI, 0.5},
                {Math.PI/3 + 6 * Math.PI, 0.866},
                {Math.PI/4 - 2 * Math.PI, 0.707},
                {Math.PI/2 + 8 * Math.PI, 1.0},
        });
    }

    @Test
    public void tableValuesTest(){
       Assert.assertEquals(expected, sin.calculate(x, p), p);
    }
}
