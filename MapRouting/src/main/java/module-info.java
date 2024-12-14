module com.example.maprouting {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires junit;
    requires org.hamcrest;
    requires jmh.core;
    requires jmh.generator.annprocess;
    requires commons.math3;
    requires jopt.simple;

    opens com.example.maprouting to javafx.fxml;
    exports gui;
    exports demo.jmh_generated to jmh.core;
}