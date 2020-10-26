package ru.ifmo.se.lab2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class Log10CalculateTest {
    Ln ln;
    Log log10;
    double x;
    double expected;
    double p = 0.0001;
    double base = 10.0;

    public void createMock(){
        ln = mock(Ln.class);

        when(ln.calculate(10.0, p)).thenReturn(2.303);
        when(ln.calculate(100.0, p)).thenReturn(4.605);
        when(ln.calculate(1000.0, p)).thenReturn(6.908);

    }

    public Log10CalculateTest(double x, double expected) {
        this.x = x;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection inputNumbers() {
        return Arrays.asList(new Double[][]{
                {10.0, 1.0},
                {100.0, 2.0},
                {1000.0, 3.0},
        });
    }

    @Test
    public void tableValuesMockTest(){
        createMock();
        log10 = new Log(ln, base);
        assertEquals(expected, log10.calculate(x, p), 0.01);
    }

    @Test
    public void tableValuesTest(){
        ln = new Ln();
        log10 = new Log(ln, base);
        assertEquals(expected, log10.calculate(x, p), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionValueTest() {
        ln = new Ln();
        log10 = new Log(ln, base);
        assertEquals(expected, log10.calculate(-25.0, p), p);
    }
}
