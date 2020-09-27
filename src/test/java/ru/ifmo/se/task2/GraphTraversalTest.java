package ru.ifmo.se.task2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class GraphTraversalTest {
    Graph graph;
    int startWith;
    int vNum;
    boolean[][] matrix;
    String expected;

    public GraphTraversalTest(int startWith, int vNum, boolean[][] matrix, String expected){
        this.startWith = startWith;
        this.vNum = vNum;
        this.matrix = matrix;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection inputValues() {
        return Arrays.asList(new Object[][] {
                {
                    0, 2, new boolean[][]{{false, true},
                                          {true, false}}, "0|1|"
                },
                {
                    1, 2, new boolean[][]{{false, false},
                                           {true, false}}, "1|0|"
                },
                {
                    0, 1, new boolean[][]{{false}}, "0|"
                },
                {
                    4, 8, new boolean[][]{{false, false, false, false, false, false, false, false},
                                          {false, false, false, false, false, false, false, false},
                                          {true, true, false, false, false, false, true, false},
                                          {false, true, false, false, false, true, false, false},
                                          {false, false, false, false, false, false, true, false},
                                          {false, false, false, false, false, false, false, false},
                                          {false, false, false, false, false, true, false, false},
                                          {false, false, false, false, false, false, true, false},}, "4|6|5|"
                }

        });
    }


    @Before
    public void Initialize(){
        this.graph = new Graph();
    }

    @Test
    public void graphTest() {
        graph.graphTraversal(startWith, vNum, matrix);
        Assert.assertEquals(expected, graph.str.toString());
    }
}
