package ru.ifmo.se.lab2;

public class Tg extends Function{
    private Sin sin;
    private Cos cos;

    public Tg(Sin sin, Cos cos){
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    double calculate(double x, double p) {
        x = x % Math.PI;
        if (x == Math.PI/2){
            throw new IllegalArgumentException("Divide by zero!");
        }

        double sinResult = sin.calculate(x, p);
        double cosResult = cos.calculate(x, p);
        double result = sinResult / cosResult;

        return result;
    }
}
