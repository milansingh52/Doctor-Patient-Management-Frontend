package com.ms.dpms;


import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainController 
{
    @FXML
    private VBox mainBox;

    @FXML
    public void initialize() 
    {
    	showContent1(null);
    }
    @FXML
    void showContent1(ActionEvent event) 
    {
        URL url = getClass().getResource("view/doctor.fxml");

        try 
        {
            AnchorPane pane1 = FXMLLoader.load(url);
            
            if(mainBox.getChildren().size() > 1)
            	mainBox.getChildren().remove(1);
            
            mainBox.getChildren().add(pane1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showContent2(ActionEvent event) 
    {
    	URL url = getClass().getResource("view/patient.fxml");

        try 
        {
            AnchorPane pane2 = FXMLLoader.load(url);
            
            if(mainBox.getChildren().size() > 1)
            	mainBox.getChildren().remove(1);
            
            mainBox.getChildren().add(pane2);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
