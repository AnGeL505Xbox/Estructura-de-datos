import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Home extends Application {

    static Product[] p1 = new Product[500];
    int timeP = 0;
    static CustomProduct[] pC = new CustomProduct[500];
    int timePC = 0;

    @Override
    public void start(Stage stage) throws Exception {

        //Menu
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidth(500);
        menuBar.setLayoutX(0); menuBar.setLayoutY(0);
        Menu mFile = new Menu("Archivo");
        Menu mHelp = new Menu("Ayuda");
        MenuItem iOpen = new MenuItem("Abrir");
        MenuItem iExit = new MenuItem("Salir");
        MenuItem iHelp = new MenuItem("Ayuda");
        menuBar.getMenus().addAll(mFile,mHelp);
        mFile.getItems().addAll(iOpen,iExit);
        mHelp.getItems().add(iHelp);

        //Titulo
        Image img = new Image(getClass().getResourceAsStream("BimboLogo.png"));
        Label lb = new Label();
        ImageView img2 = new ImageView(img);
        img2.setFitHeight(50); img2.setFitWidth(100);
        lb.setGraphic(img2);
        lb.setPrefSize(100,50);
        lb.setLayoutX(350); lb.setLayoutY(10);

        //Left elements
        ListView<String> lMain= new ListView<>();
        lMain.setLayoutX(0); lMain.setLayoutY(70);
        lMain.setPrefSize(350,100);
        ObservableList<String> olMain = FXCollections.observableArrayList();

        File fMain = new File("src/product/list_products.txt");
        BufferedReader bR = new BufferedReader(new FileReader(fMain));
        String txt;
        while( (txt = bR.readLine())!=null){ olMain.add(txt); }
        lMain.setItems(olMain);
        lMain.isEditable();

        ListView<String> lCustom= new ListView<>();
        lCustom.setLayoutX(0); lCustom.setLayoutY(180);
        lCustom.setPrefSize(350,100);
        ObservableList<String> olCustom = FXCollections.observableArrayList();
        lCustom.setItems(olCustom);
        lCustom.isEditable();

        //Right elements
        Text titleTotal = new Text();
        titleTotal.setText("Total de productos");
        titleTotal.setLayoutX(400); titleTotal.setLayoutY(80);
        titleTotal.resize(300,20);

        TextField txfTotal = new TextField();
        txfTotal.setLayoutX(400); txfTotal.setLayoutY(90);
        txfTotal.setPrefSize(300,20);
        txfTotal.setPromptText("Total de productos");

        TextField txfCode = new TextField();
        txfCode.setLayoutX(400); txfCode.setLayoutY(120);
        txfCode.setPrefSize(300,20);
        txfCode.setPromptText("Codigo (min 8 caracteres)");

        Button btTotal = new Button("Agregar venta");
        btTotal.setLayoutX(400); btTotal.setLayoutY(150);
        btTotal.setPrefSize(300,20);

        TextField txfCustomPrice = new TextField();
        txfCustomPrice.setLayoutX(400); txfCustomPrice.setLayoutY(220);
        txfCustomPrice.setPrefSize(300,20);
        txfCustomPrice.setPromptText("Precio Perzonalizado");

        Button btCustomTotal = new Button("Agregar venta personalizada");
        btCustomTotal.setLayoutX(400); btCustomTotal.setLayoutY(250);
        btCustomTotal.setPrefSize(300,20);

        Button btMaster = new Button("Generar el ticket");
        btMaster.setLayoutX(200); btMaster.setLayoutY(500);
        btMaster.setPrefSize(300,20);

        ComboBox<String> cbProduct = new ComboBox<>();
        cbProduct.setPrefSize(350,20);
        cbProduct.setLayoutX(0); cbProduct.setLayoutY(300);
        cbProduct.getItems().addAll("Producto de la lista","Producto personalizado");
        cbProduct.isEditable();

        //Eventos
            //Botones
        btTotal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = new Product(lMain.getSelectionModel().getSelectedItem(),txfCode.getText(),Double.parseDouble(txfTotal.getText()));
                p1[timeP] = product;
                timeP += 1;
            }
        });
        btCustomTotal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CustomProduct productC = new CustomProduct(lCustom.getSelectionModel().getSelectedItem(),txfCode.getText(),Double.parseDouble(txfTotal.getText()),Double.parseDouble(txfCustomPrice.getText()));
                pC[timePC] = productC;
                timePC += 1;
            }
        });
        btMaster.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert aTicket = new Alert(Alert.AlertType.INFORMATION);
                String textP = "";
                String textPC = "";
                for (int t = 0;t<timeP;t++){ textP = textP + "\n" + p1[t].alert(); }
                for (int t = 0;t<timePC;t++){ textPC = textPC + "\n" + pC[t].ticketAlert(); }
                if (cbProduct.getSelectionModel().getSelectedIndex() == 0) {
                    aTicket.setTitle("Ticket");
                    aTicket.setHeaderText("Ticekt con el total de productos");
                    aTicket.setContentText(textP);
                    aTicket.show();
                }
                else if (cbProduct.getSelectionModel().getSelectedIndex() == 1) {
                    aTicket.setTitle("Ticket");
                    aTicket.setHeaderText("Ticket con en total de productos personalizados");
                    aTicket.setContentText(textPC);
                    aTicket.show();
                }
            }
        });
            //Menu
        iExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        iOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Abrir lista de productos");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text file","*.txt"));
                File file = fileChooser.showOpenDialog(stage);
                if (file!=null){
                    try {
                        BufferedReader bf = new BufferedReader(new FileReader(file));
                        String renglon2;
                        String all = "";
                        while( (renglon2 = bf.readLine())!=null){
                            olCustom.add(renglon2);
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        iHelp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert helpAlert = new Alert(Alert.AlertType.INFORMATION);
                helpAlert.setTitle("Ayuda");
                helpAlert.setHeaderText("Ejemplo de alerta de ayuda");
                helpAlert.setContentText("Esta alerta solo se esta usando como un ejemplo para un cuadro de ayuda. \nPara agregar un producto personalizado se debe de abrir un archivo de texto con los productos y se deben de llenar todos los cuadros de texto");
                helpAlert.show();
            }
        });

        //Otros
        AnchorPane mainPane = new AnchorPane();
        Scene mainScene = new Scene(mainPane);
        mainPane.getChildren().addAll(lb,menuBar,lMain,lCustom,titleTotal,txfTotal,txfCode,btTotal,txfCustomPrice,btCustomTotal,btMaster,cbProduct);
        stage.setScene(mainScene);
        stage.setTitle("Bimbo - Tickets & Presupuesto");
        stage.setHeight(700); stage.setWidth(720); stage.show();
    }
}
