package ru.ifmo.se.lab2;

public class Sin extends Function{
    @Override
    double calculate(double x, double p) {
        double sum = 0;
        int n = 0;
        double operand = 1;

        while (Math.abs(operand) > p){
            operand = (Math.pow(-1, n) * Math.pow(x, 2 * n + 1)) / getFactorial(2 * n + 1);
            sum += operand;
            n++;
        }
        return sum;
    }
}
