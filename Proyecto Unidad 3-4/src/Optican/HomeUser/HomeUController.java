package Optican.HomeUser;

import Optican.Controller;
import Optican.Main;
import Optican.User;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;

public class HomeUController{
    @FXML ListView<String> listIMC, listPatient, listOthers;
    @FXML TextField txName, txLName, txAge, txKG, txCM, txSS, txSpO2, txPpm, txMgDl;
    @FXML ComboBox<String> cbPerson;
    @FXML Tab tabInfo;

    LinkedList<User> lPatient = new LinkedList<>();
    Stack<Double> sPatient = new Stack<>();
    int id = 0;

    //#region Añadir datos

    public void addData(ActionEvent event){
        if(txName.getText().equals("") || txLName.getText().equals("") || txAge.getText().equals("") || txKG.getText().equals("") || txCM.getText().equals("") || txSS.getText().equals("") || txSpO2.getText().equals("") || txMgDl.getText().equals("") || txPpm.getText().equals("")){
            String aa="Sin datos";
            String bb="Necesita ingresar todos los datos";
            alert(aa,bb);
        }else {
            User user = new User(txName.getText(),txLName.getText(),Integer.parseInt(txAge.getText()),Integer.parseInt(txSS.getText()),Double.parseDouble(txKG.getText()),Double.parseDouble(txCM.getText()),Double.parseDouble(txPpm.getText()),Double.parseDouble(txMgDl.getText()),Double.parseDouble(txSpO2.getText()));
            user.setId(id);
            id++;

            this.lPatient.add(user);

            listPatient.getItems().add(user.getName() + " " + user.getlName() + " " + user.getSs());
        }
    }
    //#endregion
    //#region Indice de Masa Corporal

    public void calculateIMC(ActionEvent event){
        listIMC.getItems().clear();
        if (lPatient.size()!=0) {
            for (int t = 0; t < lPatient.size(); t++) {
                if (t == cbPerson.getSelectionModel().getSelectedIndex()) {

                    lPatient.get(t).setImc(lPatient.get(t).getKg() / Math.pow(lPatient.get(t).getCm() / 100, 2));

                    String range = "";
                    if (lPatient.get(t).getImc() < 18.5) range = "Bajo peso";
                    else if (lPatient.get(t).getImc() >= 18.5 && lPatient.get(t).getImc() < 25) range = "Peso normal";
                    else if (lPatient.get(t).getImc() >= 25 && lPatient.get(t).getImc() < 30) range = "Sobrepeso";
                    else if (lPatient.get(t).getImc() >= 30) range = "Obesidad";

                    listIMC.getItems().add("Su indice de masa corporal es: " + lPatient.get(t).getImc());
                    listIMC.getItems().add("Su indice esta en el rango de: " + range);
                }
            }
        }else {
            String aa="Sin pacientes";
            String bb="Necesita ingresar todos los datos en la pestaña de Informacion";
            alert(aa,bb);
        }
    }

    public void ordCb(){
        cbPerson.getItems().clear();
        if (cbPerson.getItems().size()>0) {
            int[] q = new int[lPatient.size()];
            for (int x = 0; x < lPatient.size(); x++) {
                q[x] = lPatient.get(x).getAge();
            }
            quickOrd(q, 0, q.length - 1);
            for (int x = 0; x < lPatient.size(); x++) {
                searchSeq(q[x]);
            }
        } else {
            String aa="Sin pacientes en la lista";
            String bb="Necesita ingresar todos los datos en la pestaña de Informacion";
            alert(aa,bb);
        }
    }

    public static void quickOrd(int[] array, int left, int right) {

        int y=array[left];
        int x=left;
        int z=right;
        int auxiliar;

        while(x < z){
            while(array[x] <= y && x < z) x++;
            while(array[z] > y) z--;
            if (x < z) {
                auxiliar=array[x];
                array[x]=array[z];
                array[z]=auxiliar;
            }
        }

        array[left]=array[z]; array[z]=y;

        if(left < z-1) quickOrd(array,left,z-1);
        if(z+1 < right) quickOrd(array,z+1,right);
    }

    public void searchSeq(int age){
        for(int y=0;y<lPatient.size();y++){
            if (age == lPatient.get(y).getAge()){
                cbPerson.getItems().add(lPatient.get(y).getName()+" "+lPatient.get(y).getlName());
                break;
            }
        }
    }
    //#endregion
    //#region Mas informacion

    public void calculateOthers(){
        if (tabInfo.isSelected()) {
            listOthers.getItems().clear();
            if (lPatient.size() > 0) {
                for (User patient : lPatient) {

                    listOthers.getItems().add(patient.getName() + " " + patient.getlName());

                    sPatient.add(patient.getPpm());
                    sPatient.add(patient.getMgDl());
                    sPatient.add(patient.getSpo2());

                    if (sPatient.peek() < 90) listOthers.getItems().add("Su saturacion de oxigeno es: " + sPatient.peek() + " - Nivel bajo");
                    else listOthers.getItems().add("Su saturacion de oxigeno es: " + sPatient.peek() + " - Nivel normal");
                    sPatient.pop();

                    if (sPatient.peek() < 180) listOthers.getItems().add("Su glucosa en la sangre es: " + sPatient.peek() + " - Normal");
                    else listOthers.getItems().add("Su glucosa en la sangre es: " + sPatient.peek() + " - Alto");
                    sPatient.pop();

                    if (patient.getAge() >= 50) {
                        if (sPatient.peek() > 90) ppHigh();
                        else if (sPatient.peek() < 90 && sPatient.peek() >= 76) ppNormal();
                        else if (sPatient.peek() < 76 && sPatient.peek() >= 68) ppGood();
                        else ppExcelent();
                    } else if (patient.getAge() >= 40 && patient.getAge() < 50) {
                        if (sPatient.peek() > 90) ppHigh();
                        else if (sPatient.peek() < 90 && sPatient.peek() >= 74) ppNormal();
                        else if (sPatient.peek() < 74 && sPatient.peek() >= 66) ppGood();
                        else ppExcelent();
                    } else if (patient.getAge() >= 30 && patient.getAge() < 40) {
                        if (sPatient.peek() > 86) ppHigh();
                        else if (sPatient.peek() < 86 && sPatient.peek() >= 72) ppNormal();
                        else if (sPatient.peek() < 72 && sPatient.peek() >= 64) ppGood();
                        else ppExcelent();
                    } else {
                        if (sPatient.peek() > 86) ppHigh();
                        else if (sPatient.peek() < 86 && sPatient.peek() >= 70) ppNormal();
                        else if (sPatient.peek() < 70 && sPatient.peek() >= 62) ppGood();
                        else ppExcelent();
                    }
                    sPatient.pop();
                }
            } else {
                String aa="Usuario faltante";
                String bb="Agrega un usuario o paciente en la pestaña de informacion";
                alert(aa,bb);
            }
        }
    }

    public void ppHigh(){ listOthers.getItems().add("Su frecuencia cardiaca es: " + sPatient.peek() + "ppm - Alta"); }
    public void ppNormal(){ listOthers.getItems().add("Su frecuencia cardiaca es: " + sPatient.peek() + "ppm - Normal"); }
    public void ppGood(){ listOthers.getItems().add("Su frecuencia cardiaca es: " + sPatient.peek() + "ppm - Bien"); }
    public void ppExcelent(){ listOthers.getItems().add("Su frecuencia cardiaca es: " + sPatient.peek() + "ppm - Excelente"); }

    //#endregion
    //#region Otras funciones

    public void returnLogin(MouseEvent mouseEvent){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Optican/Login.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void alert(String titulo, String contenido){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(contenido);
        alert.show();
    }
     //#endregion
}