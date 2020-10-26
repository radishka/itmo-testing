package ru.ifmo.se.lab2;

public class Cos extends Function{
    private Sin sin;

    public Cos(Sin sin){
        this.sin = sin;
    }

    @Override
    double calculate(double x, double p) {
        x = x % (2 * Math.PI);

        double result;

        if (x == 0){
            result = 1.0;
        } else if (x == Math.PI || x == 3*Math.PI){
            result = -1;
        } else {
            result = sin.calculate(2*x, p) / (2 * sin.calculate(x, p));
        }

        return result;
    }
}
