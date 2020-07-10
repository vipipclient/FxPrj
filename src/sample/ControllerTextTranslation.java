package sample;


import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import reader.HttpTranlateRequest;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTextTranslation {
    @FXML
    private Scene scene;
    @FXML
    private AnchorPane anchRoot;

    @FXML // fx:id="h1"
    private HBox h1; // Value injected by FXMLLoader
    @FXML
    private TextField txtfield;
    @FXML
    private Button link;
    @FXML
    private TextArea txt2;

    @FXML
    private TextArea txt1;

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
    private void adjustOrientation(Number newVal) {
        txt1.setPrefHeight((double) newVal/2-h1.getPrefHeight()/2);
        txt2.setPrefHeight((double) newVal/2-h1.getPrefHeight()/2);
        h1.setLayoutY((double) newVal/2-12);
    }

    @FXML
    private Scene getCurrentScene() {
        System.out.println("anchRoot.getWidth()"+anchRoot.getWidth() + "anchRoot.getHeight()" +anchRoot.getHeight());
        return anchRoot.getScene();

    }

    @FXML
    void initialize() {
        txtfield.setText((String) AppSettings.get("link"));
        System.out.println("txtfield.getText(): " + txtfield.getText());
        link.setOnMouseClicked(mouseEvent -> {

            AppSettings.put("link",txtfield.getText());
            try {
                System.out.println("txtfield.getText(): " + txtfield.getText());
                AppSettings.save(new File("testing.xml"));
            } catch (Exception e) {
                System.out.println("ERROR happened");
                e.printStackTrace();
            }
        });
        txt1.setOnMouseMoved(mouse -> {
          //  System.out.println(txt1.getAnchor());
        });
        Button btnKz = new Button("Қазақ");
        Button btnEng = new Button("English");
        h1.getChildren().addAll(btnKz,btnEng);
        adjustOrientation(anchRoot.heightProperty().getValue());
        anchRoot.heightProperty().get();
        anchRoot.heightProperty().addListener((obs, oldVal, newVal)->{
            adjustOrientation(newVal);
        });
        txt1.setOnDragDetected(mouseEvent -> {
            System.out.println("TXT DRAG" + mouseEvent.toString());

        });
        //Create Http templete for requests
        HttpTranlateRequest transSession = new HttpTranlateRequest();

        System.out.println("void initialize() {");
        btnEng.setOnMouseClicked(actionEvent->{transSession.setLenguage("en-ru");});
        btnKz.setOnMouseClicked(actionEvent->{transSession.setLenguage("kk-ru");});

        txt2.setOnMouseClicked(mouseEvent -> {
            System.out.println("anchRoot.getScene().getHeight()"+anchRoot.getScene().getHeight());
            System.out.println(getCurrentScene());

        });
        txt1.setOnMouseClicked(mouseEvent -> {

            System.out.println(txt1.getSelectedText());
            String Tranlate = "";
            try {

                Tranlate = transSession.makeTranlation( txt1.getSelectedText());
                transSession.getXmlString();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            txt2.setText(Tranlate );

        });

    }


}