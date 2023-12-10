package controllers;

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
import tables.ARPTable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Page3Controller implements Initializable {
    @FXML
    private ComboBox<String> URLCombo;

    @FXML
    private TableColumn<ARPTable, String> MACColumn;

    @FXML
    private TableColumn<ARPTable, String> typeColumn;

    @FXML
    private TableView<ARPTable> tableView;

    @FXML
    private TableColumn<ARPTable, String> indexColumn;

    @FXML
    private TableColumn<ARPTable, String> IPAddressColumn;

    @FXML
    void onBackButtonClick(ActionEvent event) {
        StageHelper.showPage2(event);
    }
    public String extractData(String row) {
        String[] arr = row.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < arr.length; i++)
            result.append(arr[i]).append(" ");
        if (arr.length == 1)
            result = new StringBuilder("--");
        return result.toString();
    }

    private void fillTable(String jsonString) {
        String []arr = jsonString.substring(1, jsonString.length()-1).split(",");
        int numberOfRows = arr.length/4;
        List<ARPTable> rows = new ArrayList<>();
        for(int i=0;i<numberOfRows;i++){
            String value1 = extractData(arr[i+numberOfRows]);
            String value2 = extractData(arr[i+2*numberOfRows]);
            String value3 = extractData(arr[i+3*numberOfRows]);
            rows.add(new ARPTable(String.valueOf(i), value1.substring(0, value1.length()-2), value2.substring(0, value2.length()-2), value3.substring(0, value3.length()-2)));
        }

        ObservableList<ARPTable> data = FXCollections.observableArrayList(rows);
        indexColumn.setCellValueFactory(cellData -> cellData.getValue().indexProperty());
        IPAddressColumn.setCellValueFactory(cellData -> cellData.getValue().ipAddressProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        MACColumn.setCellValueFactory(cellData -> cellData.getValue().macAddressProperty());

        tableView.setItems(data);
    }

    @FXML
    void onNextButtonClick(ActionEvent event) {
        StageHelper.showPage4(event);
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
        String []URLs = {"http://localhost:1218/server/getARPTable", "http://localhost/Networks2-Asignment2/php/getters.php?function=getARPTable"};
        ObservableList<String> list = FXCollections.observableArrayList(URLs);
        URLCombo.setItems(list);
    }
}
