package ru.ifmo.se.lab2;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = ".\\src\\main\\java\\ru\\ifmo\\se\\lab2\\CSV\\";
        Sin sin = new Sin();
        Cos cos = new Cos(sin);
        Tg tg = new Tg(sin, cos);
        Cot cot = new Cot(sin, cos);
        Csc csc = new Csc(sin);
        Ln ln = new Ln();
        Log log2 = new Log(ln, 2.0);
        Log log10 = new Log(ln, 10.0);
        MyFunction myFunction = new MyFunction();
        double p = 0.0001;

        CSVWriter writerSin = CommonCVS.createCSV(path + "sin.csv");
        CommonCVS.fillCVS(writerSin, sin, 0.0, 2*Math.PI, Math.PI/3, p);
        writerSin.close();

        CSVWriter writerCos = CommonCVS.createCSV(path + "cos.csv");
        CommonCVS.fillCVS(writerCos, cos, 0.0, 2*Math.PI, Math.PI/3, p);
        writerCos.close();

        CSVWriter writerTg = CommonCVS.createCSV(path + "tg.csv");
        CommonCVS.fillCVS(writerTg, tg, -Math.PI/2, Math.PI/2, Math.PI/6, p);
        writerTg.close();

        CSVWriter writerCot = CommonCVS.createCSV(path + "cot.csv");
        CommonCVS.fillCVS(writerCot, cot, 0.1, Math.PI, Math.PI/6, p);
        writerCot.close();

        CSVWriter writerCsc = CommonCVS.createCSV(path + "csc.csv");
        CommonCVS.fillCVS(writerCsc, csc, 0.01, Math.PI, Math.PI/6, p);
        writerCsc.close();

        CSVWriter writerLn = CommonCVS.createCSV(path + "ln.csv");
        CommonCVS.fillCVS(writerLn, ln, 0.5, Math.pow(Math.E, 5), Math.E, p);
        writerLn.close();

        CSVWriter writerLog2 = CommonCVS.createCSV(path + "log2.csv");
        CommonCVS.fillCVS(writerLog2, log2, 0.5, Math.pow(2, 8), 10, p);
        writerLog2.close();

        CSVWriter writerLog10 = CommonCVS.createCSV(path + "log10.csv");
        CommonCVS.fillCVS(writerLog10, log10, 0.5, Math.pow(10, 5), 525, p);
        writerLog10.close();

        CSVWriter writerMyFunc = CommonCVS.createCSV(path + "myFunc.csv");
        CommonCVS.fillCVS(writerMyFunc, myFunction, -50, 50, 13, p);
        writerMyFunc.close();
    }
}
