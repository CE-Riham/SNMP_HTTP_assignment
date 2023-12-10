package controllers;

import helpers.Alerts;
import helpers.Request;
import helpers.StageHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import tables.SystemGroupRow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ResourceBundle;

public class Page1Controller implements Initializable {
    @FXML
    private ComboBox<String> URLCombo;

    @FXML
    private TextField sysContactTextField;

    @FXML
    private TextField sysLocationTextField;

    @FXML
    private TextField sysNameTextField;

    @FXML
    private TableColumn<SystemGroupRow, String> valueColumn;

    @FXML
    private TableView<SystemGroupRow> tableView;

    @FXML
    private TableColumn<SystemGroupRow, String> descriptionColumn;

    @FXML
    void onBackButtonClick(ActionEvent event) {
        StageHelper.showMainPage(event);
    }
    public static String extractData(String row) {
        String[] arr = row.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < arr.length; i++)
            result.append(arr[i]).append(" ");
        if (arr[0].equals("STRING:")) {
            result = new StringBuilder(result.substring(1, result.length()-2));
        }
        return result.toString();
    }
    private void fillTable(String jsonString) {
        String []descriptions = {"sysDescr", "sysObjectID", "sysUpTime", "sysContact", "sysName", "sysLocation"};
        JSONObject jsonObject = new JSONObject(jsonString);
        ObservableList<SystemGroupRow> data = FXCollections.observableArrayList();
        int i=0;
        for (String key : jsonObject.keySet()) {
            if(i==6)break;
            String value = extractData(jsonObject.getString(key));
            SystemGroupRow row = new SystemGroupRow(descriptions[i++], value);
            data.add(row);
        }

        valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        tableView.setItems(data);
    }

    @FXML
    void onNextButtonClick(ActionEvent event) {
        StageHelper.showPage2(event);
    }

    @FXML
    void onUpdateButtonClick(ActionEvent event) throws IOException {
        String newSysContact = sysContactTextField.getText();
        String newSysName = sysNameTextField.getText();
        String newSysLocation = sysLocationTextField.getText();
        String urlString = "http://localhost/Networks2-Asignment2/php/editor.php?newSysContact=" + URLEncoder.encode(newSysContact, "UTF-8") +
                "&newSysName="+URLEncoder.encode(newSysName, "UTF-8") + "&newSysLocation="+URLEncoder.encode(newSysLocation, "UTF-8");
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        String response = connection.getResponseMessage();
        if(response.equals("OK"))
            Alerts.informationAlert("Update fields", "Done", "Fields were updated successfully! Request again to see the result.");
    }

    @FXML
    void onRequestButtonClick(ActionEvent event) throws IOException {
        String url = URLCombo.getValue();
        String result = Request.sendGetRequest(url);
        System.out.println(result);
        System.out.println();
        fillTable(result);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String []URLs = {"http://localhost:1218/server/getSystemGroup", "http://localhost/Networks2-Asignment2/php/getters.php?function=getSystemGroup"};
        ObservableList<String> list = FXCollections.observableArrayList(URLs);
        URLCombo.setItems(list);
    }
}
