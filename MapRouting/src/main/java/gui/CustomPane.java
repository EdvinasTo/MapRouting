package gui;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import demo.EuclideanGraph;
import utils.GraphGenerator;
import utils.InOutUtils;

import java.io.File;

public class CustomPane extends Pane {
    File file = new File("data/data1.txt");
    String path = file.getAbsolutePath();
    EuclideanGraph graph = InOutUtils.load(path);
    String text = "";
    TextArea textArea = new TextArea();
    DrawGraph drawGraph = new DrawGraph();
    Button button = new Button("Paleisti");
    Pane visualPane = new Pane();
    Stage stage;

    public CustomPane(Stage stage) {
        this.stage = stage;
    }

    private Pane customPane() {
        Pane pane = new Pane();
        pane.setPrefWidth(200);

        Label label1 = new Label("Pradžia:");
        int offsetX = 30;
        label1.setTranslateX(offsetX);
        label1.setTranslateY(20);
        TextField start = new TextField();
        start.setTranslateX(offsetX);
        start.setTranslateY(40);

        Label label2 = new Label("Pabaiga:");
        label2.setTranslateX(offsetX);
        label2.setTranslateY(75);
        TextField end = new TextField();

        textArea.setWrapText(true);
        textArea.setPrefWidth(170);
        textArea.setPrefHeight(160);
        textArea.setTranslateX(15);
        textArea.setTranslateY(300);

        button.setTranslateY(135);
        button.setTranslateX(75);
        end.setTranslateX(offsetX);
        end.setTranslateY(95);
        button.setOnAction(e -> startButton(e, start, end));

        Label label3 = new Label("Įkelkite failą:");
        label3.setTranslateX(67);
        label3.setTranslateY(200);
        FileChooser fileChooser = new FileChooser();

        Button button2 = new Button("Įkelti failą");
        button2.setTranslateX(67);
        button2.setTranslateY(230);

        button2.setOnAction(event -> fileImportButton(event,fileChooser,label3));

        Button button3 = new Button("Generuoti grafą");
        button3.setTranslateX(55);
        button3.setTranslateY(500);
        button3.setOnAction(e -> generateGraphButton(e));

        pane.getChildren().addAll(label1,label2,button, start,end,label3,button2,textArea, button3);

        pane.setBackground(new Background(new BackgroundFill(Color.valueOf("FAF3DD"), null, null)));
        return pane;
    }

    private Pane centerPane() {
        visualPane = drawGraph.getGraphPane(graph);
        getText();

        ZoomableScrollPane zoomableScrollPane = new ZoomableScrollPane(visualPane);
        zoomableScrollPane.setFitToHeight(true);
        zoomableScrollPane.setFitToWidth(true);

        VBox vBox = new VBox(zoomableScrollPane);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefWidth(600);
        vBox.setPrefHeight(600);
        return vBox;
    }

    public Pane getPane() {
        return customPane();
    }

    public Pane getCenterPane() {
        return centerPane();
    }

    private void fileImportButton(ActionEvent e, FileChooser fileChooser, Label label3)
    {
        File file = fileChooser.showOpenDialog(stage);

        if (file != null && file.toString().endsWith("txt")) {
            path = file.getAbsolutePath();
            try {
                graph = InOutUtils.load(path);
            } catch (Exception ex) {
                text += ex.getMessage() +"\n\n";
                textArea.setText(text);
            }
        }
        else
            label3.setText("netinkamas failas");
    }

    private void startButton(ActionEvent e,TextField start, TextField end) {
        visualPane.getChildren().clear();

        if (!start.getText().isEmpty()) {
            try {
                validateInput(start, "Neteisingas duomenų formatas pradžios lange", "Negalima pradžios viršūnės reikšmė");
                graph.setStart(Integer.parseInt(start.getText()));
            } catch (Exception ex) {
                text += ex.getMessage() + "\n\n";
                textArea.setText(text);
                return;
            }
        }

        if (!end.getText().isEmpty()) {
            try {
                validateInput(end, "Neteisingas duomenų formatas pabaigos lange", "Negalima pabaigos viršūnės reikšmė");
                graph.setEnd(Integer.parseInt(end.getText()));
            } catch (Exception ex) {
                text += ex.getMessage() + "\n\n";
                textArea.setText(text);
                return;
            }
        }

        visualPane = drawGraph.getGraphPane(graph);
        getText();
    }

    private void validateInput (TextField textField, String error1, String error2) {
        if (!textField.getText().matches("-?\\d+"))
            throw new NumberFormatException(error1);
        if (Integer.parseInt(textField.getText()) >= graph.getVerticesCount() || Integer.parseInt(textField.getText()) < 0) {
            throw new IllegalArgumentException(error2);
        }
    }

    private void generateGraphButton(ActionEvent e) {
        visualPane.getChildren().clear();
        GraphGenerator generator = new GraphGenerator();
        graph = generator.getGeneratedGraph();
        visualPane = drawGraph.getGraphPane(graph);
        getText();
    }

    private void getText() {
        if (drawGraph.getReached())
            text += drawGraph.getPath() + "\n Atstumas: " + drawGraph.getDistance() + "\n\n";
        else
            text += drawGraph.getPath() + "\n\n";
        textArea.setText(text);
    }
}