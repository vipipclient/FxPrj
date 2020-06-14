package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.scene.control.TableColumn;
import netscape.javascript.JSObject;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{




        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
       primaryStage.setScene(new Scene(root, 700, 400));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
