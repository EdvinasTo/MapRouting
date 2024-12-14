package utils;

import demo.EuclideanGraph;
import demo.Vertex;
import java.util.ArrayList;
import java.util.Random;

public class GraphGenerator {
    private int verticesCount;
    private int edgesCount;
    private Vertex[] vertices;
    private int start;
    private int end;
    private ArrayList<Integer>[] edges;

    Random random = new Random();

    private void generateVerticesCount() {
        int max = 50;
        int min = 2;
        verticesCount = random.nextInt(max - min + 1) + min;

        int maxEdges = verticesCount*(verticesCount-1)/8;
        edgesCount = random.nextInt(maxEdges + 1);
    }

    private void generateEdgesCount() {
        int maxEdges = verticesCount*(verticesCount-1)/8;
        edgesCount = random.nextInt(maxEdges + 1);
    }

    private void generateVertices() {
        vertices = new Vertex[verticesCount];
        int maxCoordinates = 6000;
        for (int i = 0; i < verticesCount; i++) {
            int X = random.nextInt(maxCoordinates + 1);
            int Y = random.nextInt(maxCoordinates + 1);
            vertices[i] = new Vertex(X,Y);
        }
    }

    private void generateEdges() {
        edges = new ArrayList[verticesCount];

        for (int i = 0; i < verticesCount; i++) {
            edges[i] = new ArrayList<>();
        }

        int generatedEdges = 0;
        while (generatedEdges < edgesCount) {
            int v = random.nextInt(verticesCount);
            int w = random.nextInt(verticesCount);

            if (v != w && !edges[v].contains(w)) {
                edges[v].add(w);
                edges[w].add(v);
                generatedEdges++;
            }
        }
    }

    private void generateStartAndFinish() {
        start = random.nextInt(verticesCount);
        end = random.nextInt(verticesCount);

        while (end == start)
            end = random.nextInt(verticesCount);
    }

    public EuclideanGraph getGeneratedGraph() {
        generateVerticesCount();
        generateEdgesCount();
        generateVertices();
        generateEdges();
        generateStartAndFinish();
        return new EuclideanGraph(verticesCount, edgesCount, vertices, edges, start, end);
    }

    public EuclideanGraph getGeneratedGraph(int verticeCount, int edgeCount) {
        verticesCount = verticeCount;
        edgesCount = edgeCount;
        generateVertices();
        generateEdges();
        generateStartAndFinish();
        return new EuclideanGraph(verticesCount, edgesCount, vertices, edges, start, end);
    }
}