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
import tables.UDPRow;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Page2Controller implements Initializable {
    @FXML
    private ComboBox<String> URLCombo;

    @FXML
    private TableColumn<UDPRow, String> portNumberColumn;

    @FXML
    private TableView<UDPRow> tableView;

    @FXML
    private TableColumn<UDPRow, String> indexColumn;

    @FXML
    private TableColumn<UDPRow, String> IPAddressColumn;

    @FXML
    void onBackButtonClick(ActionEvent event) {
        StageHelper.showPage1(event);
    }
    public static String extractData(String row) {
        String[] arr = row.split(" ");
        return arr[1];
    }

    private void fillTable(String jsonString) {
        String []arr = jsonString.substring(1, jsonString.length()-1).split(",");
        int numberOfRows = arr.length/2;
        List<UDPRow> rows = new ArrayList<>();
        for(int i=0;i<numberOfRows;i++){
            String value1 = extractData(arr[i]);
            String value2 = extractData(arr[i+numberOfRows]);
            rows.add(new UDPRow(String.valueOf(i), value1.substring(0, value1.length()-1), value2.substring(0, value2.length()-1)));
        }

        ObservableList<UDPRow> data = FXCollections.observableArrayList(rows);
        indexColumn.setCellValueFactory(cellData -> cellData.getValue().indexProperty());
        IPAddressColumn.setCellValueFactory(cellData -> cellData.getValue().ipAddressProperty());
        portNumberColumn.setCellValueFactory(cellData -> cellData.getValue().portNumberProperty());

        tableView.setItems(data);
    }

    @FXML
    void onNextButtonClick(ActionEvent event) {
        StageHelper.showPage3(event);
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
        String []URLs = {"http://localhost:1218/server/getUDPTable", "http://localhost/Networks2-Asignment2/php/getters.php?function=getUDPTable"};
        ObservableList<String> list = FXCollections.observableArrayList(URLs);
        URLCombo.setItems(list);
    }
}
