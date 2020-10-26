package ru.ifmo.se.lab2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class LnCalculateTest {
    Ln ln = new Ln();
    double x;
    double expected;
    double p = 0.01;


    public LnCalculateTest(double x, double expected) {
        this.x = x;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection inputNumbers() {
        return Arrays.asList(new Double[][]{
                {1.0, 0.0},
                {Math.E, 1.0},
                {Math.pow(Math.E, 2), 2.0}
        });
    }

    @Test
    public void tableValuesTest(){
        assertEquals(expected, ln.calculate(x, p), p);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionValueTest1(){
        assertEquals(expected, ln.calculate(0, p), p);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionValueTest2(){
        assertEquals(expected, ln.calculate(-5, p), p);
    }
}
