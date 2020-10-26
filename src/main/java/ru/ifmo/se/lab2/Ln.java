package ru.ifmo.se.lab2;

public class Ln extends Function{

    @Override
    double calculate(double x, double p) {
        double sum  = 0;
        double n = 1;
        double operand = 1;

        if (x == 0){
            throw new IllegalArgumentException("The argument must not be zero!");
        } else if (x < 0){
            throw new IllegalArgumentException("The argument must not be negative!");
        }

        while (Math.abs(operand) >= p){
            operand = 2 * Math.pow((x - 1) / (x + 1), n) / n;
            sum += operand;
            n = n + 2;
        }
        return sum;
    }
}
