package Optican.HomeUser;

import Optican.Main;
import Optican.Others.User;

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

public class HomeUController {
    @FXML ListView listIMC, listPatient, listOthers;
    @FXML TextField txName, txLName, txAge, txKG, txCM, txSS, txSpO2, txPpm, txMgDl;
    @FXML ComboBox cbPerson;
    @FXML Tab tabInfo;

    LinkedList<User> listPatients = new LinkedList<>();
    Stack<Double> stackPatient = new Stack<>();

    public void addData(ActionEvent event){

        if(txName.getText().equals("") || txLName.getText().equals("") || txAge.getText().equals("") || txKG.getText().equals("") || txCM.getText().equals("") || txSS.getText().equals("") || txSpO2.getText().equals("") || txMgDl.getText().equals("") || txPpm.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sin datos");
            alert.setContentText("Necesita ingresar todos los datos");
            alert.show();
        }else {
            User user = new User(txName.getText(), txLName.getText(), Integer.parseInt(txAge.getText()), Integer.parseInt(txSS.getText()), Double.parseDouble(txKG.getText()), Double.parseDouble(txCM.getText()), Double.parseDouble(txPpm.getText()), Double.parseDouble(txSpO2.getText()), Double.parseDouble(txMgDl.getText()));
            this.listPatients.add(user);

            listPatient.getItems().add(user.getName() + " " + user.getlName() + " " + user.getSs());
            cbPerson.getItems().add(user.getName() + " " + user.getlName());
        }
    }

    public void calculateIMC(ActionEvent event){

        if (listPatients.size()!=0) {

            /*Calculo*/
            for (int t = 0; t < listPatients.size(); t++) {
                if (t == cbPerson.getSelectionModel().getSelectedIndex()) {

                    String range = "";

                    listPatients.get(t).setImc(listPatients.get(t).getKg() / Math.pow(listPatients.get(t).getCm() / 100, 2));

                    if (listPatients.get(t).getImc() < 18.5) {
                        range = "Bajo peso";
                    } else if (listPatients.get(t).getImc() >= 18.5 && listPatients.get(t).getImc() < 25) {
                        range = "Peso normal";
                    } else if (listPatients.get(t).getImc() >= 25 && listPatients.get(t).getImc() < 30) {
                        range = "Sobrepeso";
                    } else if (listPatients.get(t).getImc() >= 30) {
                        range = "Obesidad";
                    }

                    listIMC.getItems().add("Su indice de masa corporal es: " + listPatients.get(t).getImc());
                    listIMC.getItems().add("Su indice esta en el rango de: " + range);
                }
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sin paciente");
            alert.setContentText("Necesita ingresar todos los datos en la pestaña de Informacion");
            alert.show();
        }
    }

    public void calculateOthers(){
        if (tabInfo.isSelected()) {
            listOthers.getItems().clear();
            if (listPatients.size() > 0) {
                for (int x = 0; x < listPatients.size(); x++) {

                    listOthers.getItems().add(listPatients.get(x).getName() + " " + listPatients.get(x).getlName());

                    stackPatient.add(listPatients.get(x).getPpm());
                    stackPatient.add(listPatients.get(x).getMgDl());
                    stackPatient.add(listPatients.get(x).getSpo2());

                    if (stackPatient.peek() < 90) {
                        listOthers.getItems().add("Su saturacion de oxigeno es: " + stackPatient.peek() + " - Nivel bajo");
                    } else {
                        listOthers.getItems().add("Su saturacion de oxigeno es: " + stackPatient.peek() + " - Nivel normal");
                    }
                    stackPatient.pop();

                    if (stackPatient.peek() < 180) {
                        listOthers.getItems().add("Su glucosa en la sangre es: " + stackPatient.peek() + " - Normal");
                    } else {
                        listOthers.getItems().add("Su glucosa en la sangre es: " + stackPatient.peek() + " - Alto");
                    }
                    stackPatient.pop();

                    if (listPatients.get(x).getAge() >= 50) {
                        if (stackPatient.peek() > 90) {
                            listOthers.getItems().add("Su frecuencia cardiaca es: " + stackPatient.peek() + "ppm - Alta");
                        } else if (stackPatient.peek() < 90 && stackPatient.peek() >= 76) {
                            listOthers.getItems().add("Su frecuencia cardiaca es: " + stackPatient.peek() + "ppm - Normal");
                        } else if (stackPatient.peek() < 76 && stackPatient.peek() >= 68) {
                            listOthers.getItems().add("Su frecuencia cardiaca es: " + stackPatient.peek() + "ppm - Bien");
                        } else {
                            listOthers.getItems().add("Su frecuencia cardiaca es: " + stackPatient.peek() + "ppm - Excelente");
                        }
                    } else if (listPatients.get(x).getAge() >= 40 && listPatients.get(x).getAge() < 50) {
                        if (stackPatient.peek() > 90) {
                            listOthers.getItems().add("Su frecuencia cardiaca es: " + stackPatient.peek() + "ppm - Alta");
                        } else if (stackPatient.peek() < 90 && stackPatient.peek() >= 74) {
                            listOthers.getItems().add("Su frecuencia cardiaca es: " + stackPatient.peek() + "ppm - Normal");
                        } else if (stackPatient.peek() < 74 && stackPatient.peek() >= 66) {
                            listOthers.getItems().add("Su frecuencia cardiaca es: " + stackPatient.peek() + "ppm - Bien");
                        } else {
                            listOthers.getItems().add("Su frecuencia cardiaca es: " + stackPatient.peek() + "ppm - Excelente");
                        }
                    } else if (listPatients.get(x).getAge() >= 30 && listPatients.get(x).getAge() < 40) {
                        if (stackPatient.peek() > 86) {
                            listOthers.getItems().add("Su frecuencia cardiaca es: " + stackPatient.peek() + "ppm - Alta");
                        } else if (stackPatient.peek() < 86 && stackPatient.peek() >= 72) {
                            listOthers.getItems().add("Su frecuencia cardiaca es: " + stackPatient.peek() + "ppm - Normal");
                        } else if (stackPatient.peek() < 72 && stackPatient.peek() >= 64) {
                            listOthers.getItems().add("Su frecuencia cardiaca es: " + stackPatient.peek() + "ppm - Bien");
                        } else {
                            listOthers.getItems().add("Su frecuencia cardiaca es: " + stackPatient.peek() + "ppm - Excelente");
                        }
                    } else {
                        if (stackPatient.peek() > 86) {
                            listOthers.getItems().add("Su frecuencia cardiaca es: " + stackPatient.peek() + "ppm - Alta");
                        } else if (stackPatient.peek() < 86 && stackPatient.peek() >= 70) {
                            listOthers.getItems().add("Su frecuencia cardiaca es: " + stackPatient.peek() + "ppm - Normal");
                        } else if (stackPatient.peek() < 70 && stackPatient.peek() >= 62) {
                            listOthers.getItems().add("Su frecuencia cardiaca es: " + stackPatient.peek() + "ppm - Bien");
                        } else {
                            listOthers.getItems().add("Su frecuencia cardiaca es: " + stackPatient.peek() + "ppm - Excelente");
                        }
                    }
                    stackPatient.pop();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Usuario faltante");
                alert.setContentText("Agrega un usuario o paciente en la pestaña de informacion");
                alert.show();
            }
        }else {}
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