package ru.ifmo.se.lab2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class CscCalculateTest {
    Sin sin;
    Csc csc;
    double x;
    double p = 0.001;
    double expected;

    public void createMock(){
        sin = mock(Sin.class);

        when(sin.calculate(-5.759586531581288, p)).thenReturn(0.5);
        when(sin.calculate(-11.519173063162576, p)).thenReturn(0.866);
        when(sin.calculate(0.0, p)).thenReturn(0.0);
        when(sin.calculate(Math.PI, p)).thenReturn(0.0);
        when(sin.calculate(Math.PI/6, p)).thenReturn(0.5);
        when(sin.calculate(Math.PI/3, p)).thenReturn(0.866);
        when(sin.calculate(2*Math.PI/3, p)).thenReturn(0.866);
        when(sin.calculate(Math.PI/4, p)).thenReturn(0.707);
        when(sin.calculate(Math.PI/2, p)).thenReturn(1.0);
        when(sin.calculate(1.570796, p)).thenReturn(1.0);
        when(sin.calculate(-Math.PI/6, p)).thenReturn(-0.5);
        when(sin.calculate(-Math.PI/3, p)).thenReturn(-0.866);
        when(sin.calculate(-2*Math.PI/3, p)).thenReturn(-0.866);
        when(sin.calculate(-Math.PI/4, p)).thenReturn(-0.707);
        when(sin.calculate(-Math.PI/2, p)).thenReturn(-1.0);
        when(sin.calculate(2 * Math.PI, p)).thenReturn(0.0);
        when(sin.calculate(3*Math.PI/2, p)).thenReturn(-1.0);
    }

    public CscCalculateTest(double x, double expected){
        this.x = x;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection inputNumbers(){
        return Arrays.asList(new Double[][] {
                {Math.PI/2, 1.0},
                {-Math.PI/2, -1.0},
                {Math.PI/6, 2.0},
                {Math.PI/3, 1.155},
                {Math.PI/4, 1.414},
                {-Math.PI/6, -2.0},
                {-Math.PI/3, -1.155},
                {-Math.PI/4, -1.414},

        });
    }

    @Test
    public void tableValueMockTest(){
        createMock();
        csc = new Csc(sin);
        Assert.assertEquals(expected, csc.calculate(x, p), p);
    }

    @Test
    public void tableValueTest(){
        sin = new Sin();
        csc = new Csc(sin);
        Assert.assertEquals(expected, csc.calculate(x, p), p);
    }

    @Test (expected = IllegalArgumentException.class)
    public void exceptionValueTest1(){
        sin = new Sin();
        csc = new Csc(sin);
        csc.calculate(0.0, p);
    }

}


