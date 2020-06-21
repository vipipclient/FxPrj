package sample;
import sample.Controller2;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import reader.ReadText;

public class Controller {
    @FXML
    private TextField t2;

    @FXML
    private Button b3;

    @FXML
    private Button b2;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField t1;

    @FXML
    private Button b1;

    @FXML
    void initialize() {
        runTranslation();
        System.out.println("public class Controller ");
        b1.setOnAction(actionEvent -> {
            System.out.println(t1.getCharacters());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/helloWorld.fxml"));
            try {
                FileChooser filedir = new FileChooser();
                final String dir = System.getProperty("user.dir");
                filedir.setInitialDirectory(new File(dir));
                File TextFile = filedir.showOpenDialog(new Stage());
                if (TextFile == null)
                    return;
                t1.setText(TextFile.getAbsolutePath());
                t2.setText(dir);
                ReadText.main(new String[]{TextFile.getAbsolutePath(), dir});
                loader.load();
            } catch (IOException e) {
                System.out.println("dsfdsfdsfdsfdsf");
          //      e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.showAndWait();


            ;
        });
            b3.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/table.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                System.out.println("table.fxml load error");
                      e.printStackTrace();
            }
            Parent root = loader.getRoot();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
//                stage.titleProperty().bind(
//                        scene.widthProperty().asString().
//                                concat(" : ").
//                                concat(scene.heightProperty().asString()));

                stage.widthProperty().addListener((obs, oldVal, newVal) -> {
                    // Do whatever you want

                });

//                stage.heightProperty().addListener((obs, oldVal, newVal) -> {
//                    System.out.println("stage.getHeight()"+stage.getHeight());
//                });
            stage.setScene(scene);
            stage.showAndWait();
        });


    }
    public  void  runTranslation(){
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/TextTranslate.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                System.out.println("table.fxml load error");
                e.printStackTrace();
            }
            Parent root = loader.getRoot();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        }
    }

}
