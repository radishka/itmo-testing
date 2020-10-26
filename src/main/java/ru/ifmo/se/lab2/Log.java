package ru.ifmo.se.lab2;

public class Log extends Function{
    private double base;
    private Ln ln;

    public Log(Ln ln, double base){
        this.ln = ln;
        this.base = base;
    }

    @Override
    double calculate(double x, double p) {
        if (base <= 0){
            throw new IllegalArgumentException("The base must be > 0!");
        } else if (base == 1){
            throw new IllegalArgumentException("The argument must not be 1!");
        }

        if (x <= 0){
            throw new IllegalArgumentException("The x must be > 0!");
        }

        return (ln.calculate(x, p) / ln.calculate(base, p));
    }
}
