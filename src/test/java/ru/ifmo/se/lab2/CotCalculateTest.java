package ru.ifmo.se.lab2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class CotCalculateTest {
    Sin sin;
    Cos cos;
    Cot cot;
    double x;
    double p = 0.001;
    double expected;

    public void createSinMock(){
        sin = mock(Sin.class);

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

    public void createCosMock(){
        cos = mock(Cos.class);

        when(cos.calculate(0.0, p)).thenReturn(1.0);
        when(cos.calculate(Math.PI/6, p)).thenReturn(0.866);
        when(cos.calculate(Math.PI/3, p)).thenReturn(0.5);
        when(cos.calculate(Math.PI/4, p)).thenReturn(0.707);
        when(cos.calculate(Math.PI/2, p)).thenReturn(0.0);
        when(cos.calculate(-Math.PI/6, p)).thenReturn(0.866);
        when(cos.calculate(-Math.PI/3, p)).thenReturn(0.5);
        when(cos.calculate(-Math.PI/4, p)).thenReturn(0.707);
        when(cos.calculate(Math.PI/2, p)).thenReturn(0.0);
        when(cos.calculate(Math.PI/6 - 4 * Math.PI, p)).thenReturn(0.866);
        when(cos.calculate(Math.PI/3 + 6 * Math.PI, p)).thenReturn(0.5);
        when(cos.calculate(Math.PI/4 - 2 * Math.PI, p)).thenReturn(0.707);
        when(cos.calculate(Math.PI/2 + 8 * Math.PI, p)).thenReturn(0.0);
    }

    public CotCalculateTest(double x, double expected){
        this.x = x;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection inputNumbers(){
        return Arrays.asList(new Double[][] {
                {Math.PI/2, 0.0},
                {-Math.PI/2, 0.0},
               // {Math.PI/2 + 8 * Math.PI, 0.0},
                {Math.PI/6, 1.732},
                {Math.PI/3, 0.577},
                {Math.PI/4, 1.0},
                {-Math.PI/6, -1.732},
                {-Math.PI/3, -0.577},
                {-Math.PI/4, -1.0},
        });
    }

    @Test
    public void tableValuesSinMockTest(){
        createSinMock();
        cos = new Cos(sin);
        cot = new Cot(sin, cos);
        Assert.assertEquals(expected, cot.calculate(x, p), p);
    }

    @Test
    public void tableValuesCosMockTest(){
        createCosMock();
        sin = new Sin();
        cot = new Cot(sin, cos);
        Assert.assertEquals(expected, cot.calculate(x, p), p);
    }

    @Test
    public void tableValuesMockTest(){
        createSinMock();;
        createCosMock();
        cot = new Cot(sin, cos);
        Assert.assertEquals(expected, cot.calculate(x, p), p);
    }

    @Test
    public void tableValuesTest(){
        sin = new Sin();
        cos = new Cos(sin);
        cot = new Cot(sin, cos);
        Assert.assertEquals(expected, cot.calculate(x, p), p);
    }

    @Test (expected = IllegalArgumentException.class)
    public void exceptionValueTest1(){
        sin = new Sin();
        cos = new Cos(sin);
        cot = new Cot(sin, cos);
        cot.calculate(0.0, p);
    }

}

