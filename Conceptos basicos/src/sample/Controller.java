package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML TextField txtdat;
    @FXML Label lbdat;

    public void event(ActionEvent event){
        int age = Integer.parseInt(txtdat.getText())*365;
        lbdat.setText("Usted ha vivido: "+age+" dias");
    }
}