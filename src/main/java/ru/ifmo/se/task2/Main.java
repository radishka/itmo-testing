package ru.ifmo.se.task2;

public class Main {

    public static void main(String[] args){

        boolean[][] matrix = {{false, true}, {true, false}};
        boolean[][] matrix1 = {{false, true}, {true, false}};
        Graph graph = new Graph();
        Graph graph1 = new Graph();

        graph.graphTraversal(0, 2, matrix);
        System.out.println(graph.str);

        graph1.graphTraversal(1, 2, matrix1);
        System.out.println(graph1.str);
    }

}
