package ru.ifmo.se.task2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GraphExceptionTest {

    Graph graph;

    @Before
    public void Initialize(){
        this.graph = new Graph();
    }

    @Test
    public void graphTraversalIncorrectMatrixTest() {
        try {
            graph.graphTraversal(0, 4, new boolean[][]{{false, true}, {true, false}});
        } catch (IndexOutOfBoundsException e){
            Assert.assertEquals(e.getMessage(), "Matrix size must be equals vNum.");
        }
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void graphTraversalIncorrectStartIndexTest() {
        graph.graphTraversal(3, 2, new boolean[][]{{false, true}, {true, false}});
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void graphTraversalNegativeStartIndexTest() {
        graph.graphTraversal(-1, 2, new boolean[][]{{false, true}, {true, false}});
    }

    @Test (expected = NegativeArraySizeException.class)
    public void graphTraversalIncorrectVNumTest() {
        graph.graphTraversal(0, -1, new boolean[][]{{false, true}, {true, false}});
    }
}
