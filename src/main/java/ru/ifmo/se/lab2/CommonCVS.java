package ru.ifmo.se.lab2;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class CommonCVS {
    public static CSVWriter createCSV(String name) throws IOException {
        return new CSVWriter(new FileWriter(name));
    }

    public static void addData(CSVWriter writer, String data){
        String[] record = data.split(",");
        writer.writeNext(record);
    }

    public static void fillCVS(CSVWriter writer, Function func, double from, double to, double step, double p){
        String data;

        while (from <= to) {
            data = Double.toString(from) + "," + Double.toString(func.calculate(from, p));
            addData(writer, data);
            from += step;
        }
    }
}
