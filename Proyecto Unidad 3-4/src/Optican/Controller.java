package Optican;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class Controller {

    public void userHome(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HomeUser/HomeUController.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void adminHome(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HomeAdmin/HomeAController.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
        } catch (IOException e) { e.printStackTrace(); }
    }
}