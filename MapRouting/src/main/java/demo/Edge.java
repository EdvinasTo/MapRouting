package demo;

public class Edge implements Comparable<Edge> {
    int vertex;
    double distance;

    public Edge(int vertex, double distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    public int getVertex() {
        return  this.vertex;
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.distance, other.distance);
    }
}
