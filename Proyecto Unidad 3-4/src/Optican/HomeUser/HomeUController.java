package Optican.HomeUser;

import Optican.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import Optican.Others.User;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.LinkedList;

public class HomeUController {
    @FXML ListView listIMC, listPatient;
    @FXML TextField txName, txLName, txAge, txKG, txCM, txSS;
    @FXML ComboBox cbPerson;

    LinkedList<User> listPatients= new LinkedList<>();

    public void addData(ActionEvent event){

        User patient = new User(txName.getText(),txLName.getText(),Integer.parseInt(txAge.getText()),Integer.parseInt(txSS.getText()),Double.parseDouble(txKG.getText()),Double.parseDouble(txCM.getText()));
        this.listPatients.add(patient);

        listPatient.getItems().add(patient.getName()+" "+patient.getlName()+" "+patient.getSs());
        cbPerson.getItems().add(patient.getName()+" "+patient.getlName());
    }

    public void calculateIMC(ActionEvent event){

        /*Calculo*/
        for (int t = 0;t<listPatients.size();t++) {
            if(t==cbPerson.getSelectionModel().getSelectedIndex()){
                String range = "";

                listPatients.get(t).setImc(listPatients.get(t).getKg()/Math.pow(listPatients.get(t).getCm()/100,2));

                if (listPatients.get(t).getImc()<18.5){ range = "Bajo peso"; }
                else if (listPatients.get(t).getImc()>=18.5 && listPatients.get(t).getImc()<25 ){ range = "Peso normal"; }
                else if (listPatients.get(t).getImc()>=25 && listPatients.get(t).getImc()<30 ){ range = "Sobrepeso"; }
                else if(listPatients.get(t).getImc()>=30){ range = "Obesidad"; }

                listIMC.getItems().add("Su indice de masa corporal es: "+listPatients.get(t).getImc());
                listIMC.getItems().add("Su indice esta en el rango de: "+range);
            }
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