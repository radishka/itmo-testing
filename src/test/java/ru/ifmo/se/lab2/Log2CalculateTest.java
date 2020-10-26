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
public class Log2CalculateTest {
    Ln ln;
    Log log2;
    double x;
    double expected;
    double p = 0.00001;
    double base = 2;

    public void createMock(){
        ln = mock(Ln.class);

        when(ln.calculate(2.0, p)).thenReturn(0.693);
        when(ln.calculate(4.0, p)).thenReturn(1.386);
        when(ln.calculate(8.0, p)).thenReturn(2.079);
        when(ln.calculate(1024.0, p)).thenReturn(6.931);

    }

    public Log2CalculateTest(double x, double expected) {
        this.x = x;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection inputNumbers() {
        return Arrays.asList(new Double[][]{
                {2.0, 1.0},
                {4.0, 2.0},
                {8.0, 3.0},
                {1024.0, 10.0},
        });
    }

    @Test
    public void tableValuesMockTest(){
        createMock();
        log2 = new Log(ln, base);
        assertEquals(expected, log2.calculate(x, p), 0.01);
    }

    @Test
    public void tableValuesTest(){
        ln = new Ln();
        log2 = new Log(ln, base);
        assertEquals(expected, log2.calculate(x, p), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionValueTest() {
        ln = new Ln();
        log2 = new Log(ln, base);
        assertEquals(expected, log2.calculate(-25.0, p), p);
    }
}