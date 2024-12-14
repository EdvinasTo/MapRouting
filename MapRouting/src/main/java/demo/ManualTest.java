package demo;

import utils.Dijkstra;

import java.util.ArrayList;
import java.util.Locale;

public class ManualTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        executeTest();
    }

    private static void executeTest() {
        int size = 4;
        int start = 0;
        int end = 2;
        Vertex[] vertices = new Vertex[size];
        vertices[0] = new Vertex(0,2);
        vertices[1] = new Vertex(10,4);
        vertices[2] = new Vertex(5,10);
        vertices[3] = new Vertex(3,16);

        ArrayList<Integer>[] edges = new ArrayList[size];
        for (int i = 0; i < size; i++)
            edges[i] = new ArrayList<>();
        edges[0].add(1);
        edges[0].add(3);
        edges[1].add(0);
        edges[1].add(2);
        edges[2].add(1);
        edges[2].add(3);
        edges[3].add(0);
        edges[3].add(2);

        EuclideanGraph graph = new EuclideanGraph(size,8,vertices,edges,start,end);

        Dijkstra dijkstra = new Dijkstra(graph);

        System.out.println("Pirmas grafas");
        System.out.println("----------------------");
        System.out.println("Ar rastas kelias?");
        System.out.println(dijkstra.getReached(end));
        System.out.println(dijkstra.showPath(start,end));
        System.out.println("Atstumas:");
        System.out.println(dijkstra.distance(end) + "\n");

        edges = new ArrayList[size];
        for (int i = 0; i < size; i++)
            edges[i] = new ArrayList<>();
        edges[0].add(1);
        edges[0].add(3);
        edges[1].add(0);
        edges[1].add(3);
        edges[3].add(0);
        edges[3].add(1);
        graph = new EuclideanGraph(size,6,vertices,edges,start,end);
        dijkstra = new Dijkstra(graph);

        System.out.println("Antras grafas");
        System.out.println("----------------------");
        System.out.println("Ar rastas kelias?");
        System.out.println(dijkstra.getReached(end));
        System.out.println(dijkstra.showPath(start,end));
        System.out.println("Atstumas:");
        System.out.println(dijkstra.distance(end));

    }

}
