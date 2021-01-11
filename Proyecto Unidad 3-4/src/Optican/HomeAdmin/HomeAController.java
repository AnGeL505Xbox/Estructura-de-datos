package Optican.HomeAdmin;

import Optican.Main;
import Optican.Others.Patient;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import javafx.fxml.FXML;;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class HomeAController {
    @FXML ListView listPatient, listCita;
    @FXML TextField txName, txLName, txAge, txKG, txCM, txSS;

    Queue<Patient> cPacientes = new LinkedList<>();
    int t=0;

    public void addCita(ActionEvent event){

        if(txName.getText().equals("") || txLName.getText().equals("") || txAge.getText().equals("") || txKG.getText().equals("") || txCM.getText().equals("") || txSS.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sin datos");
            alert.setContentText("Necesita ingresar todos los datos");
            alert.show();
        }else {
            Patient patient = new Patient(txName.getText(), txLName.getText(), Integer.parseInt(txAge.getText()), Integer.parseInt(txSS.getText()), Double.parseDouble(txKG.getText()), Double.parseDouble(txCM.getText()), 0, 0, 0);
            cPacientes.add(patient);

            listPatient.getItems().add("#" + t + " " + patient.getName() + " " + patient.getlName());
            listCita.getItems().add("#" + t + " " + patient.getName() + " " + patient.getlName());
            this.t += 1;
        }
    }
    public void deleteCita(ActionEvent event){
        if (cPacientes.size() > 0){
            listCita.getItems().remove(0);
            cPacientes.poll();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sin citas");
            alert.setContentText("Usted ya no le quedan citas");
            alert.show();
        }
    }
    public void returnLogin(MouseEvent mouseEvent){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Optican/Login.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
