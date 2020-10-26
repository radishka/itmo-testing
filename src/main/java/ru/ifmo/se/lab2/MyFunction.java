package ru.ifmo.se.lab2;

public class MyFunction extends Function{
    @Override
    double calculate(double x, double p) {
        double result;
        Sin sin = new Sin();
        Cos cos = new Cos(sin);
        Tg tg = new Tg(sin, cos);
        Cot cot = new Cot(sin, cos);
        Csc csc = new Csc(sin);
        Ln ln = new Ln();
        Log log2 = new Log(ln, 2.0);
        Log log10 = new Log(ln, 10.0);

        if (x <= 0){
            result =  ((((tg.calculate(x, p) - sin.calculate(x, p)) + cot.calculate(x, p)) / cos.calculate(x, p)) -
                    sin.calculate(x, p)) - ((csc.calculate(x, p) / (tg.calculate(x, p) * cot.calculate(x, p))) - csc.calculate(x, p));
        } else {
            result = (Math.pow((Math.pow(ln.calculate(x, p) * log10.calculate(x, p), 2) -
                    log2.calculate(x, p)), 3) + log2.calculate(x, p));
        }                                                       

        return result;
    }
}
