package controllers;

import helpers.StageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    void onPage1Click(ActionEvent event) {
        StageHelper.showPage1(event);
    }

    @FXML
    void onPage2Click(ActionEvent event) {
        StageHelper.showPage2(event);
    }

    @FXML
    void onPage3Click(ActionEvent event) {
        StageHelper.showPage3(event);
    }

    @FXML
    void onPage4Click(ActionEvent event) {
        StageHelper.showPage4(event);
    }
}