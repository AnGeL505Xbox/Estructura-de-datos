package sample;

import fish.Fish;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {

    final String[][] fishArray = new String[500][2];

    @FXML ComboBox<String> cbType;
    @FXML TextField txBirth;
    @FXML ListView<String> lvTotal;
    int tx; //Selector de fila

    public void combo(javafx.scene.input.MouseEvent mouseEvent) {
        cbType.getItems().removeAll(cbType.getItems());
        cbType.getItems().addAll("Carpa","Salmon","Arenque","Magikarp","Trucha","Piraña","Betta","Joker","Luna");
    }
    public void insert(ActionEvent event){
        int birth = Integer.parseInt(txBirth.getText());
        fishArray[tx][0] = String.valueOf(cbType.getSelectionModel().getSelectedItem());
        fishArray[tx][1] = String.valueOf(birth);
        tx ++;
    }
    public void process(ActionEvent event){
        lvTotal.getItems().removeAll(lvTotal.getItems());
        for (int x = 0;x< tx;x++){
            Fish fish = new Fish(fishArray[x][0],Integer.parseInt(fishArray[x][1]));
            lvTotal.getItems().addAll("El Pescado "+fishArray[x][0]+" nace "+fishArray[x][1]+" por cria y "+fish.totalFish()+" al año");
        }
    }
}