package demo;

import java.util.ArrayList;
import java.util.List;

public class EuclideanGraph {
    private final int verticesCount;
    private final int edgesCount;
    private final Vertex[] vertices;
    private final ArrayList<Integer>[] edges;
    private int start;
    private int end;

    public EuclideanGraph(int verticesCount, int edgesCount, Vertex[] vertices, ArrayList<Integer>[] edges, int start, int end) {
        this.verticesCount = verticesCount;
        this.edgesCount = edgesCount;
        this.vertices = vertices;
        this.edges = edges;
        this.start = start;
        this.end = end;
    }

    public EuclideanGraph() {
        this.verticesCount = -1;
        this.edgesCount = -1;
        this.vertices = new Vertex[0];
        this.edges = new ArrayList[0];
        this.start = -1;
        this.end = -1;
    }

    public int getVerticesCount() {
        return verticesCount;
    }

    //public int getEdgesCount() {
    //    return edgesCount;
    //}

    public Vertex[] getVertices() {
        return vertices;
    }

    public List<Integer> getEdges(int vertex) {
        return edges[vertex];
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void setStart(int chosen) {
        start = chosen;
    }

    public void setEnd(int chosen) {
        end = chosen;
    }

    public double distance(int v, int w) {
        return vertices[v].distanceTo(vertices[w]);
    }
}
