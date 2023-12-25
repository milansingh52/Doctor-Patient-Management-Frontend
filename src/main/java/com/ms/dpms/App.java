package com.ms.dpms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application 
{
    private static Scene scene;
    private static VBox mainBox;

    @Override
    public void start(Stage stage) throws IOException 
    {
        try 
        {
            mainBox = (VBox)FXMLLoader.load(getClass().getResource("view/Main.fxml"));
            scene = new Scene(mainBox);
            stage.setScene(scene);
            stage.show();
            
            // Reference of the MainBox inside the CommonObjs object
            CommonObjs commonObjs = CommonObjs.getInstance();
            commonObjs.setMainBox(mainBox);
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    static void setRoot(String fxml) throws IOException 
    {
        mainBox.getChildren().clear(); 
        mainBox.getChildren().add(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) 
    {
        launch();
    }
}