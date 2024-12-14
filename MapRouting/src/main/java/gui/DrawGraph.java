package gui;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import utils.Dijkstra;
import demo.EuclideanGraph;
import demo.Vertex;

public class DrawGraph {
    EuclideanGraph graph;
    Dijkstra dijkstra;
    DrawGraph() {
    }

    Pane pane = new Pane();

    private void drawCircles(Pane pane, Dijkstra dijkstra) {
        for(int i = 0; i < graph.getVerticesCount(); i++)
        {
            Text text = new Text();
            if (graph.getVerticesCount() < 100) {
                text.setText("" + i);
                text.setX(graph.getVertices()[i].getX());
                text.setY(-graph.getVertices()[i].getY());
                text.setTranslateX(-15);
                text.setTranslateY(515);
                text.setFont(Font.font("Verdana",50));
                text.setFill(Color.SNOW);
            }
            Circle circle = new Circle();

            circle.setFill(dijkstra.getShortestPath(graph.getEnd()).contains(i) ? Color.LIMEGREEN:Color.DARKRED);
            circle.setCenterX(graph.getVertices()[i].getX());
            circle.setCenterY(-graph.getVertices()[i].getY());
            circle.setTranslateY(graph.getVerticesCount()>30 ? 1300 : 500);
            circle.setRadius(graph.getVerticesCount()>30 ? 8 : 50);

            pane.getChildren().addAll(circle,text);
        }

        Text start = new Text("Start");
        Text end = new Text("End");
        start.setFont(Font.font("Verdana",70));
        start.setFill(Color.YELLOWGREEN);
        end.setFont(Font.font("Verdana",70));
        end.setFill(Color.YELLOWGREEN);

        start.setX(graph.getVertices()[graph.getStart()].getX());
        start.setY(-graph.getVertices()[graph.getStart()].getY());
        start.setTranslateX(graph.getVerticesCount()>30 ? -30 : -60);
        start.setTranslateY(graph.getVerticesCount()>30 ? 1250 : 420);

        end.setX(graph.getVertices()[graph.getEnd()].getX());
        end.setY(-graph.getVertices()[graph.getEnd()].getY());
        end.setTranslateX(graph.getVerticesCount()>30 ? -30 : -50);
        end.setTranslateY(graph.getVerticesCount()>30 ? 1370 : 620);

        pane.getChildren().addAll(start, end);
    }

    private void drawEdges(Pane pane, Dijkstra dijkstra) {
        for(int i = 0; i < graph.getVerticesCount()-1; i++)
        {
            int previous = 0;
            while (previous <= graph.getEdges(i).size()-1) {
                Vertex vertex = graph.getVertices()[graph.getEdges(i).get(previous)];
                Line line = new Line();
                line.setStartX(graph.getVertices()[i].getX());
                line.setStartY(-graph.getVertices()[i].getY());
                line.setEndX(vertex.getX());
                line.setEndY(-vertex.getY());
                line.setTranslateY(graph.getVerticesCount()>30 ? 1300 : 500);
                if (dijkstra.getShortestPath(graph.getEnd()).contains(i) &&
                        dijkstra.getShortestPath(graph.getEnd()).contains(graph.getEdges(i).get(previous))) {
                    line.setStroke(Color.LIMEGREEN);
                    line.setStrokeWidth(graph.getVerticesCount()>30 ? 3 : 8);
                }
                line.setStrokeWidth(graph.getVerticesCount()>30 ? 1 : 6);
                pane.getChildren().add(line);
                previous++;
            }
        }
    }

    public Pane getGraphPane(EuclideanGraph graph) {
        this.graph = graph;
        dijkstra = new Dijkstra(graph);
        drawEdges(pane, dijkstra);
        drawCircles(pane, dijkstra);
        return pane;
    }

    public double getDistance() {
        return dijkstra.distance(graph.getEnd());
    }

    public String getPath() {
        return dijkstra.showPath(graph.getStart(), graph.getEnd());
    }

    public boolean getReached() {
        return dijkstra.getReached(graph.getEnd());
    }
}