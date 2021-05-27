package controller;

import Service.Service;
import domain.Comanda;
import domain.Medicament;
import domain.Utilizator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SPMedicController {
    ObservableList<Medicament>model= FXCollections.observableArrayList();
    private Service serv;
    private Utilizator user;
    @FXML
    TextField nume;
    @FXML
    TextField sectie;
    @FXML
    Button comanda;
    @FXML
    Button vizualiz;
    @FXML
    TableView<Medicament>tabel;
    @FXML
    TableColumn<Medicament,String>id;
    @FXML
    TableColumn<Medicament,String>nume2;
    Stage stage;
    String nr;
    List<Comanda> com=new ArrayList<>();



    public void setService(Service serv, Stage stage, String nr, List<Comanda> com)
    {
        this.serv=serv;
        this.stage=stage;
        this.nr=nr;
        this.com=com;

        setNum();
    }
    public void setUtil(Utilizator u)
    {
        this.user=u;
    }
    public void setNum(){
        nume.setText(user.getNume()+" "+user.getPrenume());
        sectie.setText(nr);

    }
    @FXML
    public void showComandaNoua() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/comandaNoua.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Comanda Noua");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root, 750, 450);
            dialogStage.setScene(scene);
            ComandaNouaController utilCont = loader.getController();
            utilCont.setUtil(user);
            utilCont.setService(serv,nr,dialogStage);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.close();


    }
    @FXML
    public void showVizualizare() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/VizualizareDetalii.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Comanda Noua");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root, 600, 450);
            dialogStage.setScene(scene);
            ViuzalizareDetController utilCont = loader.getController();
            utilCont.setUtil(user);
            utilCont.setService(serv,com,dialogStage,nr);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.close();


    }
    @FXML
    public void signOut()
    {
        stage.close();
    }


}
