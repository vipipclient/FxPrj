package sample;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.PickResult;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import reader.CheckWithLinkedHashMap;
import reader.HttpTranlateRequest;
import reader.TransletedHarryPotterWord;
public class Controller2 {


    @FXML
    private TextArea txt2;

    @FXML
    private TextArea txt1;

    @FXML
    private StackPane pl;

    @FXML
    private TableColumn<Person, String> Word;

    @FXML
    private TableColumn<Person, String> cnt;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView img1;

    @FXML
    private Button b4;

    private final ObservableList<Person> data
            = FXCollections.observableArrayList(
            new Person("Jacob", "Smith", "jacob.smith@example.com")
    );
    private final ObservableList<TransletedHarryPotterWord> dataH
            = FXCollections.observableArrayList();
    private ArrayList<TransletedHarryPotterWord> Hwords;


    @FXML
    void initialize() {
        System.out.println("void initialize() {");
        b4.setOnAction(actionEvent -> {


        });
        CheckWithLinkedHashMap checkWithLinkedHashMap =new CheckWithLinkedHashMap();
        Hwords = checkWithLinkedHashMap.GetTransletedHarryPotterWords();
        dataH.addAll(Hwords);
  //********************************************************
//        TableColumn<Person,String> nameColumn = new TableColumn("firstName");
//        nameColumn.setMinWidth(200);
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
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

//        Word.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
//        cnt.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
////        MainTable.setItems(data);

        pl.getChildren().add(table2);

        table2.setOnMouseClicked(event->{
            TransletedHarryPotterWord selectedCell = table2.getSelectionModel().getSelectedItem();
            System.out.println("Mouse pressed " + selectedCell.toString());

        });
//        table2.setOnKeyPressed(keyEvent -> {
//            System.out.println("key pressed " + keyEvent.toString());
//
//        });
        table2.setOnMouseEntered(mouseEvent -> {

            PickResult df = mouseEvent.getPickResult();

            System.out.println( df.toString());
        });
//        txt1.setOnTouchStationary(touchEvent -> {
//            System.out.println("txt1.setOnTouchStationary");
//        });
//        txt1.setOnMousePressed(mouseEvent1 -> {
//            System.out.println("txt1.setOnMousePressed");
//        });
//        txt1.setOnMouseEntered(mouseEvent -> System.out.println("setOnMouseEntered"));
//        txt1.setOnMouseReleased(mouseEvent -> System.out.println("@setOnMouseReleased"));
//
//        txt1.setOnMouseClicked(mouseEvent -> System.out.println("setOnMouseClicked"));
//        txt1.setOnMousePressed(mouseEvent -> System.out.println("setOnMousePressed"));
//        txt1.setOnMouseDragExited(mouseEvent -> System.out.println("setOnMouseDragExited"));

        txt2.setOnMouseClicked(mouseEvent -> {

        });
        txt1.setOnMouseClicked(mouseEvent -> {
//            Cursor fgh = txt1.getCursor();
//            if (mouseEvent.isControlDown() == true)
//                System.out.println(txt1.getCaretPosition());
//            System.out.println(txt1.getSelectedText());

//            txt1.selectNextWord();
//            txt1.selectPreviousWord();
//            txt1.selectForward();
//            txt1.selectEnd();
            System.out.println(txt1.getSelectedText());
            String Tranlate = "";
            try {

                Tranlate = HttpTranlateRequest.makeTranlation( txt1.getSelectedText());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            txt2.setText(Tranlate );



        });
    }
}