package com.ms.dpms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.net.*;
import java.net.http.*;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ms.dpms.model.DoctorModel;

public class DoctorController implements Initializable 
{
    @FXML
    private TableColumn<DoctorModel, String> colEmail;

    @FXML
    private TableColumn<DoctorModel, String> colGender;

    @FXML
    private TableColumn<DoctorModel, Long> colId;

    @FXML
    private TableColumn<DoctorModel, String> colName;

    @FXML
    private TableView<DoctorModel> tableView;

    @FXML
    private TextField txtEmail;

    @FXML
    private ComboBox<String> txtGender;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    private ObservableList<DoctorModel> doctorList;

    @FXML
    void addDoctor(ActionEvent event) 
    {
    	System.out.println("Add data");
    	
        if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtEmail.getText().isEmpty()
                || txtGender.getValue() == null) 
        {
            System.out.println("Please fill in all fields.");
            return;
        }

        // Get values from text fields
        Long id = Long.parseLong(txtId.getText());
        String name = txtName.getText();
        String email = txtEmail.getText();
        String gender = txtGender.getValue();

        // Create a new DoctorModel object
        DoctorModel newDoctor = new DoctorModel(id, name, email, gender);

        // Add the new doctor to the table view
        doctorList.add(newDoctor);
        
        // Add the new doctor to the API
        addDoctorToAPI(newDoctor);
        
        
        getAPIData();

        // Clear text fields
        clearFields();
    }

    private void clearFields() 
    {
        txtId.clear();
        txtName.clear();
        txtEmail.clear();
        txtGender.getSelectionModel().clearSelection();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        // Initialize the observable list
        doctorList = FXCollections.observableArrayList();

        // Set up the table columns
        colId.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colGender.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));

        // Bind the data to the table view
        tableView.setItems(doctorList);

        // Initialize gender ComboBox
        txtGender.getItems().addAll("Male", "Female", "Other");

        // Display data from API
        getAPIData();
    }
    
    void getAPIData() 
    {
        try 
        {
            var url = "http://localhost:9090/doctors";

            // http request
            var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
            var client = HttpClient.newBuilder().build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            populateTable(response.body());
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }

    private void populateTable(String jsonResponse) 
    {
        try 
        {
            // Try to parse the response as a JSON array
            JSONArray jsonArray = new JSONArray(jsonResponse);

            // If the response is not a JSON array, log an error and return
            if (jsonArray.length() == 0) 
            {
                System.err.println("Invalid JSON array. Empty or not starting with '['");
                return;
            }

            // Parse and add data to the observable list
            ObservableList<DoctorModel> doctorModels = FXCollections.observableArrayList();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                long id = jsonObject.getLong("id");
                String name = jsonObject.getString("name");
                String email = jsonObject.getString("email");
                String gender = jsonObject.getString("gender");

                DoctorModel doctorModel = new DoctorModel(id, name, email, gender);
                doctorModels.add(doctorModel);
            }

            // Set up the table columns
            colId.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));
            colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
            colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
            colGender.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));

            // Bind the data to the table view
            tableView.setItems(doctorModels);
        } 
        catch (Exception e) 
        {
            System.err.println("Error parsing JSON response: " + e.getMessage());
        }
    }
    
    private void addDoctorToAPI(DoctorModel doctor) 
    {
        try 
        {
            var url = "http://localhost:9090/doctors";

            // Create JSON payload
            JSONObject jsonPayload = new JSONObject();
            jsonPayload.put("id", doctor.getId());
            jsonPayload.put("name", doctor.getName());
            jsonPayload.put("email", doctor.getEmail());
            jsonPayload.put("gender", doctor.getGender());

            // http request
            var request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload.toString()))
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .build();

            var client = HttpClient.newBuilder().build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());
        } 
        catch (Exception e) 
        {
            System.out.println("Error adding doctor to API: " + e.getMessage());
        }
    }
}
