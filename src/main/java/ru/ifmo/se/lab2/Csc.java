package ru.ifmo.se.lab2;

public class Csc extends Function{
    private Sin sin;

    public Csc(Sin sin){
        this.sin = sin;
    }

    @Override
    double calculate(double x, double p) {
        double sinResult = sin.calculate(x, p);
        double result = 1 / sinResult;

        if (sinResult == 0){
            throw new IllegalArgumentException("Divide by zero!");
        }

        return result;
    }
}
