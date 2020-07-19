package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import reader.HttpIdReqest;

import java.io.File;
import java.util.HashMap;

interface FunctionTest{
    void func(String x);
}
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
 //       HttpIdReqest.getId();
        Person a = new Person("", "", "");

 //       System.out.println("current yandex Link is :" + AppSettings.load(new File("conf.xml")));
        AppSettings.load(new File("conf.xml"));
        HashMap asd = AppSettings.getSettingsHashMap();
//        AppSettings.put("link","https://translate.yandex.net/api/v1/tr.json/translate?id=a0840ed9.5f06c2ac.4842985f-0-0&srv=tr-text&lang=");
        AppSettings.save(new File("testing.xml"));


        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Ilyas Text Translator ");
       primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.setMaxWidth(710);
        primaryStage.setMaxHeight(410);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
