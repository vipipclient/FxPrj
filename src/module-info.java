module FXprj {
    requires  javafx.fxml;
    requires  javafx.controls;
    requires jackson.all;
    requires java.desktop;
    requires java.rmi;
    requires jdk.httpserver;
    requires java.sql;

    opens sample;
    opens reader;

}