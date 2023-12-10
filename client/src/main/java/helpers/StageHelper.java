package helpers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageHelper {
    private static void showPage(ActionEvent event, String path, int width, int height) throws IOException {
        if (event.getSource() instanceof Node source) {
            Parent root = FXMLLoader.load(StageHelper.class.getResource(path));
            Scene scene = source.getScene();
            Stage stage = (Stage) scene.getWindow();
            scene.setRoot(root);
            stage.setHeight(height);
            stage.setWidth(width);
            stage.centerOnScreen();
        }
    }

    public static void showMainPage(ActionEvent event){
        try{
            showPage(event, "/FXMLFiles/main.fxml", 600, 400);
        }catch (Exception e){
            System.out.println("Can't open main page");
        }
    }

    public static void showPage1(ActionEvent event){
        try{
            showPage(event, "/FXMLFiles/page1.fxml", 1200, 800);
        }catch (Exception e){
            System.out.println("Can't open page1");
        }
    }

    public static void showPage2(ActionEvent event){
        try{
            showPage(event, "/FXMLFiles/page2.fxml", 1200, 800);
        }catch (Exception e){
            System.out.println("Can't open page2");
        }
    }

    public static void showPage3(ActionEvent event){
        try{
            showPage(event, "/FXMLFiles/page3.fxml", 1200, 800);
        }catch (Exception e){
            System.out.println("Can't open page3");
        }
    }

    public static void showPage4(ActionEvent event){
        try{
            showPage(event, "/FXMLFiles/page4.fxml", 1200, 800);
        }catch (Exception e){
            System.out.println("Can't open page4");
        }
    }
    public static void showStep1Page(ActionEvent event){
        try{
            showPage(event, "/FXMLFiles/step1.fxml", 600, 400);
        }catch (Exception e){
            System.out.println("Can't open step1 page");
        }
    }
    public static void showStep2Page(ActionEvent event){
        try{
            showPage(event, "/FXMLFiles/step2.fxml", 600, 400);
        }catch (Exception e){
            System.out.println("Can't open step2 page");
        }
    }

}
