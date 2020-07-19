package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.PickResult;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import reader.CheckWithLinkedHashMap;
import reader.TransletedHarryPotterWord;

import java.util.ArrayList;

public class Controller2 {
    @FXML
    private Scene scene;
    @FXML
    private AnchorPane anchRoot;

    @FXML
    private StackPane pl;

    @FXML
    private Scene getCurrentScene() {
        System.out.println("anchRoot.getWidth()"+anchRoot.getWidth() + "anchRoot.getHeight()" +anchRoot.getHeight());
        return anchRoot.getScene();

    }

    private final ObservableList<Person> data
            = FXCollections.observableArrayList(
            new Person("Jacob", "Smith", "jacob.smith@example.com")
    );
    private final ObservableList<TransletedHarryPotterWord> dataH
            = FXCollections.observableArrayList();
    private ArrayList<TransletedHarryPotterWord> Hwords;


    @FXML
    void initialize() {

        CheckWithLinkedHashMap checkWithLinkedHashMap =new CheckWithLinkedHashMap();
        Hwords = checkWithLinkedHashMap.GetTransletedHarryPotterWords();
        dataH.addAll(Hwords);
  //********************************************************

//********************************************************************************
        TableColumn<TransletedHarryPotterWord, String> Column1 = new TableColumn("word");
        Column1.setMinWidth(200);
        Column1.setCellValueFactory(new PropertyValueFactory<>("word"));

        TableColumn<TransletedHarryPotterWord, String> Column2 = new TableColumn("translation");
        Column2.setMinWidth(200);
        Column2.setCellValueFactory(new PropertyValueFactory<>("translation"));

        TableColumn<TransletedHarryPotterWord, Integer> Column3 = new TableColumn("count");
        Column3.setMinWidth(50);
        Column3.setCellValueFactory(new PropertyValueFactory<>("count"));


 //***************************************************************************************

        TableView<TransletedHarryPotterWord> table2 = new TableView<>();
        table2.setItems(dataH);
        table2.getColumns().addAll(Column1,Column2,Column3);


        pl.getChildren().add(table2);

        table2.setOnMouseClicked(event->{
            TransletedHarryPotterWord selectedCell = table2.getSelectionModel().getSelectedItem();
            System.out.println("Mouse pressed " + selectedCell.toString());

        });

        table2.setOnMouseEntered(mouseEvent -> {

            PickResult df = mouseEvent.getPickResult();

            System.out.println( df.toString());
        });



    }
}