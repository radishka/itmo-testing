package ru.ifmo.se.lab1.task2;

public class Graph {

    public StringBuilder str = new StringBuilder();

    public void graphTraversal(int v, int vNum, boolean[][] matrix) {
        boolean[] used = new boolean[vNum]; // массив пометок
        int[] queue = new int[vNum]; // очередь
        int qH = 0; // голова очереди
        int qT = 0; // хвост

        if (matrix.length != vNum){
            throw new IndexOutOfBoundsException("Matrix size must be equals vNum.");
        }

        /* <обработка вершины v> */
        used[v] = true; // помечаем исходную вершину
        queue[qT++] = v; // помещаем ее в очередь
        str.append(v);
        str.append("|");

        while (qH < qT) { // пока очередь не пуста
            v = queue[qH++]; // извлекаем текущую вершину
            for (int nv = 0; nv < vNum; nv++) { // перебираем вершины
                if (!used[nv] && matrix[v][nv]) { // если nv не помечена и смежна с v
                    /* <обработка вершины nv> */
                    used[nv] = true; // помечаем ее
                    str.append(nv);
                    str.append("|");
                    queue[qT++] = nv; // и добавляем в очередь
                }
            }
        }
    }
}
