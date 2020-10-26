package ru.ifmo.se.lab2;

public class Cot extends Function{
    private Sin sin;
    private Cos cos;

    public Cot(Sin sin, Cos cos){
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    double calculate(double x, double p) {
        double sinResult = sin.calculate(x, p);
        double cosResult = cos.calculate(x, p);
        double result = cosResult / sinResult;

        if (sinResult == 0){
            throw new IllegalArgumentException("Divide by zero!");
        }

        return result;
    }
}
