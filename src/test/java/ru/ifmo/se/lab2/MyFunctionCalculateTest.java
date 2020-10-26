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
public class MyFunctionCalculateTest {
    Sin sin;
    Cos cos;
    Tg tg;
    Cot cot;
    Csc csc;
    Ln ln;
    Log log2;
    Log log10;
    MyFunction myFunction;
    double x;
    double p = 0.001;
    double expected;

    public void createSinMock(){
        sin = mock(Sin.class);

        when(sin.calculate(-Math.PI/3, p)).thenReturn(-0.866);
        when(sin.calculate(-Math.PI/6, p)).thenReturn(-1.5);
    }

    public void createCosMock(){
        cos = mock(Cos.class);

        when(cos.calculate(-Math.PI/3, p)).thenReturn(0.5);
        when(cos.calculate(-Math.PI/6, p)).thenReturn(0.866);
    }

    public void createLnMock(){
        ln = mock(Ln.class);

        when(ln.calculate(2.0, p)).thenReturn(0.693);
        when(ln.calculate(10.0, p)).thenReturn(2.303);

    }

    public MyFunctionCalculateTest(double x, double expected){
        this.x = x;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection inputNumbers(){
        return Arrays.asList(new Double[][] {
                {-Math.PI/3, -2.021},
                {-Math.PI/6, -1.589},
                {2.0, 0.125},
                {10.0, 11.084},
        });
    }

    @Test
    public void tableValuesSinMockTest(){
        createSinMock();

        cos = new Cos(sin);
        tg = new Tg(sin, cos);
        cot = new Cot(sin, cos);
        csc = new Csc(sin);
        ln = new Ln();
        log2 = new Log(ln, 2);
        log10 = new Log(ln, 10);
        myFunction = new MyFunction();

        Assert.assertEquals(expected, myFunction.calculate(x, p), 0.1);
    }

    @Test
    public void tableValuesCosMockTest(){
        createCosMock();

        sin = new Sin();
        tg = new Tg(sin, cos);
        cot = new Cot(sin, cos);
        csc = new Csc(sin);
        ln = new Ln();
        log2 = new Log(ln, 2);
        log10 = new Log(ln, 10);
        myFunction = new MyFunction();

        Assert.assertEquals(expected, myFunction.calculate(x, p), 0.1);
    }

    @Test
    public void tableValuesLnMockTest(){
        createLnMock();

        sin = new Sin();
        cos = new Cos(sin);
        tg = new Tg(sin, cos);
        cot = new Cot(sin, cos);
        csc = new Csc(sin);
        log2 = new Log(ln, 2);
        log10 = new Log(ln, 10);
        myFunction = new MyFunction();

        Assert.assertEquals(expected, myFunction.calculate(x, p), 0.1);
    }

    @Test
    public void tableValuesMockTest(){
        createSinMock();
        createCosMock();
        createLnMock();

        tg = new Tg(sin, cos);
        cot = new Cot(sin, cos);
        csc = new Csc(sin);
        log2 = new Log(ln, 2);
        log10 = new Log(ln, 10);
        myFunction = new MyFunction();

        Assert.assertEquals(expected, myFunction.calculate(x, p), 0.1);
    }

    @Test
    public void tableValuesTest(){
        sin = new Sin();
        cos = new Cos(sin);
        ln = new Ln();
        tg = new Tg(sin, cos);
        cot = new Cot(sin, cos);
        csc = new Csc(sin);
        log2 = new Log(ln, 2);
        log10 = new Log(ln, 10);
        myFunction = new MyFunction();

        Assert.assertEquals(expected, myFunction.calculate(x, p), 0.1);
    }

}
