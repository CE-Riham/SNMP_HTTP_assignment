module com.example.assignment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens classes to javafx.fxml;
    exports classes;
    exports controllers;
    opens controllers to javafx.fxml;
}