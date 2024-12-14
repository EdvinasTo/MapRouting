package utils;

import demo.Edge;
import demo.EuclideanGraph;

import java.util.*;

public class Dijkstra {
    private final static double INFINITY = Double.POSITIVE_INFINITY;

    private EuclideanGraph graph;
    private double[] dist;
    private int[] prec;
    private boolean reached;

    public Dijkstra(EuclideanGraph graph) {
        this.graph = graph;
        runDijkstra(graph.getStart());
    }

    public double distance(int end) {
        double distance;
        if (getReached(end)) {
            distance = Math.round(dist[end] * 100);
            distance = distance/100;
        }
        else
            distance = INFINITY;
        return distance;
    }

    public String showPath(int start, int destination) {
        if (dist[destination] == INFINITY) {
            reached = false;
            return destination + " yra nepasiekiamas iš " + start;
        }

        reached = true;
        StringBuilder path = new StringBuilder();
        for (int v = destination; v != start; v = prec[v]) {
            path.insert(0, " -> " + v);
        }

        path.insert(0, start);
        return "Kelias: " + path.toString();
    }

    public void runDijkstra(int start) {
        int verticesCount = graph.getVerticesCount();

        dist = new double[verticesCount];
        prec = new int[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            dist[i] = INFINITY;
            prec[i] = -1;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        dist[start] = 0;
        pq.add(new Edge(start, heuristic(start, graph.getEnd())));

        boolean[] visited = new boolean[graph.getVerticesCount()];

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int currentVertex = edge.getVertex();

            if (visited[currentVertex]) {
                continue;
            }

            visited[currentVertex] = true;

            if (currentVertex == graph.getEnd()) {
                break;
            }

            for (int neighbor : graph.getEdges(currentVertex)){
                if (visited[neighbor]) {
                    continue;
                }

                double newDist = dist[currentVertex] + graph.distance(currentVertex, neighbor);
                double priority = newDist + heuristic(neighbor, graph.getEnd());
                if (newDist < dist[neighbor]) {
                    dist[neighbor] = newDist;
                    prec[neighbor] = currentVertex;
                    pq.add(new Edge(neighbor,priority));
                }
            }
        }
    }

    private double heuristic(int v, int destination) {
        return graph.getVertices()[v].distanceTo(graph.getVertices()[destination]);
    }

    public void runDijkstra1(int start) {
        int verticesCount = graph.getVerticesCount();

        dist = new double[verticesCount];
        prec = new int[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            dist[i] = INFINITY;
            prec[i] = -1;
        }

        Queue<Edge> pq = new LinkedList<>();

        dist[start] = 0;
        pq.add(new Edge(start, heuristic(start, graph.getEnd())));

        boolean[] visited = new boolean[graph.getVerticesCount()];

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int currentVertex = edge.getVertex();

            if (visited[currentVertex]) {
                continue;
            }

            visited[currentVertex] = true;

            if (currentVertex == graph.getEnd()) {
                break;
            }

            for (int neighbor : graph.getEdges(currentVertex)){
                if (visited[neighbor]) {
                    continue;
                }

                double newDist = dist[currentVertex] + graph.distance(currentVertex, neighbor);
                double priority = newDist + heuristic(neighbor, graph.getEnd());
                if (newDist < dist[neighbor]) {
                    dist[neighbor] = newDist;
                    prec[neighbor] = currentVertex;
                    pq.add(new Edge(neighbor,priority));
                }
            }
        }
    }

    public List<Integer> getShortestPath(int end) {
        List<Integer> path = new ArrayList<>();

        reached = dist[end] != INFINITY;
        if (!reached) {
            path.add(graph.getStart());
            path.add(graph.getEnd());
        }
        else {
            for (int i = end; i != -1; i = prec[i]) {
                path.add(i);
            }
        }
        return path;
    }

    public boolean getReached(int end) {
        reached = dist[end] != INFINITY;
        return reached;
    }
}