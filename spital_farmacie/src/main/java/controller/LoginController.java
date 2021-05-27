package controller;

import Service.Service;
import domain.Comanda;
import domain.Lucreaza;
import domain.Utilizator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    private Service serv;
    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    TextField sectie;
    @FXML
    Button aut;
    Stage stage;
    List<Comanda> com=new ArrayList<>();

    public void setService(Service serv,Stage stage)
    {
        this.stage=stage;
        this.serv=serv;
    }
    @FXML
    public void login()
    {
     String user=username.getText();
     String pass=password.getText();
     String secti=sectie.getText();
     Alert a=new Alert(Alert.AlertType.INFORMATION,"Username sau parola incorecta");
     Utilizator util=serv.login(user,pass);
     if(util==null)
         a.show();
     else
         if (util.getLuc() == Lucreaza.FARMACIST)
            showStartPageFarmacist(util);
         else {
             stage.close();
             showStartPageSpital(util, secti);
         }

    }

    public void showStartPageFarmacist(Utilizator user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/farmacist.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Consultare comenzi");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root, 750, 450);
            dialogStage.setScene(scene);
            FarmacistController utilCont = loader.getController();
            utilCont.setService(serv,dialogStage);
            utilCont.setUtil(user);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void showStartPageSpital(Utilizator user,String nr) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/StartPageMedic.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Inregistrare comenzi");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root, 600, 450);
            dialogStage.setScene(scene);
            SPMedicController utilCont = loader.getController();
            utilCont.setUtil(user);
            utilCont.setService(serv,dialogStage,nr,com);

            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }






}
