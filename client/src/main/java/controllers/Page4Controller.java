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
import tables.TCPRow;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Page4Controller implements Initializable {
    @FXML
    private ComboBox<String> URLCombo;
    @FXML
    private TableColumn<TCPRow, String> destIPColumn;

    @FXML
    private TableColumn<TCPRow, String> destPortColumn;

    @FXML
    private TableColumn<TCPRow, String> indexColumn;

    @FXML
    private TableColumn<TCPRow, String> srcIPColumn;

    @FXML
    private TableColumn<TCPRow, String> srcPortColumn;

    @FXML
    private TableColumn<TCPRow, String> stateColumn;

    @FXML
    private TableView<TCPRow> tableView;


    @FXML
    void onBackButtonClick(ActionEvent event) {
        StageHelper.showPage3(event);
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
        int numberOfRows = arr.length/5;
        List<TCPRow> rows = new ArrayList<>();
        for(int i=0;i<numberOfRows;i++){
            String value1 = extractData(arr[i]);
            String value2 = extractData(arr[i+numberOfRows]);
            String value3 = extractData(arr[i+2*numberOfRows]);
            String value4 = extractData(arr[i+3*numberOfRows]);
            String value5 = extractData(arr[i+4*numberOfRows]);
            rows.add(new TCPRow(String.valueOf(i), value1.substring(0, value1.length()-2), value2.substring(0, value2.length()-2), value3.substring(0, value3.length()-2),value4.substring(0, value4.length()-2), value5.substring(0, value5.length()-2)));
        }

        ObservableList<TCPRow> data = FXCollections.observableArrayList(rows);
        indexColumn.setCellValueFactory(cellData -> cellData.getValue().getIndex());
        stateColumn.setCellValueFactory(cellData -> cellData.getValue().getState());
        srcIPColumn.setCellValueFactory(cellData -> cellData.getValue().getSrcIPAddress());
        srcPortColumn.setCellValueFactory(cellData -> cellData.getValue().getSrcPort());
        destIPColumn.setCellValueFactory(cellData -> cellData.getValue().getDesIPAddress());
        destPortColumn.setCellValueFactory(cellData -> cellData.getValue().getDesPort());
        tableView.setItems(data);
    }

    @FXML
    void onNextButtonClick(ActionEvent event) {
        StageHelper.showMainPage(event);
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
        String []URLs = {"http://localhost:1218/server/getTCPTable", "http://localhost/Networks2-Asignment2/php/getters.php?function=getTCPTable"};
        ObservableList<String> list = FXCollections.observableArrayList(URLs);
        URLCombo.setItems(list);
    }
}
