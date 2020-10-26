package ru.ifmo.se.lab2;

public abstract class Function {

    abstract double calculate(double x, double p);

    protected double getFactorial(int f) {
        double result = 1;
        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }
}
