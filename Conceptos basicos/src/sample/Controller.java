package sample;

import conversiones.Conversiones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML TextField txtdat;
    @FXML Label lbdat;

    public void event(ActionEvent event){
        Conversiones td = new Conversiones();
        int num = Integer.parseInt(txtdat.getText());
        String res = td.binary(num);
        lbdat.setText(res);
    }
}