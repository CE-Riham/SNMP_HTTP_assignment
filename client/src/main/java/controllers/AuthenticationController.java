package controllers;

import helpers.Alerts;
import helpers.StageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class AuthenticationController {

    @FXML
    private TextField IDTextField;

    @FXML
    private PasswordField password1Field;

    @FXML
    private PasswordField password2Field;

    @FXML
    private TextField usernameTextField;

    @FXML
    void onVerify2Click(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = password2Field.getText();
        String urlString = "http://localhost:1218/server/Step2.jsp?username=" + URLEncoder.encode(username, "UTF-8") + "&password="+URLEncoder.encode(password, "UTF-8");
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line, response = "";
        while ((line = bufferedReader.readLine()) != null) {
            response += line;
        }
        bufferedReader.close();
        System.out.println(response);

        String []values = response.split("\"");
        response = values[3];

        if(response.equals("Permit"))
            StageHelper.showMainPage(event);
        else
            Alerts.errorAlert("Verify2", "Deny", "Invalid ID or password!");
    }
    @FXML
    void onVerify1Click(ActionEvent event) throws IOException {
        String id = IDTextField.getText();
        String password = password1Field.getText();
        String urlString = "http://localhost:1218/server/Step1?ID=" + URLEncoder.encode(id, "UTF-8") + "&password="+URLEncoder.encode(password, "UTF-8");
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line, response = "Deny";
        while ((line = bufferedReader.readLine()) != null) {
            response = line;
        }
        bufferedReader.close();
        System.out.println(response);
        String []values = response.split("\"");
        response = values[3];

        if(response.equals("Permit"))
            StageHelper.showStep2Page(event);
        else
            Alerts.errorAlert("Verify1", "Deny", "Invalid ID or password!");
    }

}
