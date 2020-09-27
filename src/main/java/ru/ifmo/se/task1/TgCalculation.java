package ru.ifmo.se.task1;

public class TgCalculation {

    public static double getFactorial(int f) {
        double result = 1;
        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }

    private static double getBernoulli(int n) {
        double result = 0;
        if (n == 0) {
            result = 1;
        } else if (n == 1){
            result = -0.5;
        } else if (n % 2 == 0){

            double sum = 0;

            for (int k = 1; k < n+1; k ++){
                sum += getFactorial(n + 1) * getBernoulli(n-k) / (getFactorial(k + 1) * getFactorial(n-k));
            }

            result = (-1) * sum / (n+1);
        }
        return result ;
    }

    public static double tg(double x, int p){
        double sum = 0;
        double operand = 1;
        int n = 1;

        if (Math.abs(x) > Math.PI / 2 ){
            x = x % Math.PI;
        }

        while (n <= p){
            operand = Math.pow(-4, n) * (1 - Math.pow(4, n)) * (Math.pow(x, 2 * n - 1)) * getBernoulli(2 * n) / (getFactorial(2 * n)) ;
            sum += operand;
            n++;
        }
        return sum;
    }
}
