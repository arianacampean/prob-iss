package controller;

import Service.Service;
import domain.Comanda;
import domain.Medicament;
import domain.Stare;
import domain.Utilizator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComandaNouaController {
    ObservableList<Medicament> model= FXCollections.observableArrayList();
    List<Comanda>comezi=new ArrayList<>();
    private Service serv;
    private Utilizator user;
    private String nr;

    @FXML
    TableView<Medicament> tabel;
    @FXML
    TableColumn<Medicament,String> id;
    @FXML
    TableColumn<Medicament,String>nume2;
    @FXML
    TextField cantitate;
    @FXML
    CheckBox normala;
    @FXML
    CheckBox urgenta;
    @FXML
    Button adauga;
    @FXML
    Button trimite;
    @FXML
    Button inapoi;
    Stage stage;
    @FXML
    Label med;



    public void setService(Service serv,String nr,Stage stage)
    {
        this.serv=serv;
        this.nr=nr;
        this.stage=stage;
        lista();

    }
    public void setUtil(Utilizator u)
    {
        this.user=u;
    }

    @FXML
    public void initialize()
    {
        tabel.setItems(model);
        id.setCellValueFactory(new PropertyValueFactory<Medicament, String>("id"));
        nume2.setCellValueFactory(new PropertyValueFactory<Medicament, String>("nume"));

    }
    public void lista()
    {
        List<Medicament> all= (List<Medicament>) serv.get_allMed();
        model.setAll(all);

    }
    @FXML
    public void adauga_med()
    {
        Alert a=new Alert(Alert.AlertType.INFORMATION,"Ambele stari sunt bifate");
        Alert b=new Alert(Alert.AlertType.INFORMATION,"Comanda necompletata");
        Comanda com=new Comanda();
        Medicament selected=tabel.getSelectionModel().getSelectedItem();
        com.setId_medicament(selected.getId());
        String ceva=cantitate.getText();
        if(ceva.equals(""))
           b.show();
        else{
        if (!(urgenta.isSelected() || normala.isSelected()))
            b.show();
        else{
            int canti = Integer.parseInt(cantitate.getText());
            com.setCantitate(canti);
            com.setSectie(Integer.parseInt(nr));
            if (urgenta.isSelected() && normala.isSelected())
                a.show();
            else {
                if (normala.isSelected())
                    com.setStare(Stare.NORMALA);
                else if (urgenta.isSelected())
                    com.setStare(Stare.URGENTA);
                med.setText("Medicament adaugat");
                comezi.add(com);
                cantitate.clear();
                urgenta.setSelected(false);
                normala.setSelected(false);
            }
        }
    }

    }
    @FXML
    public void trimite()
    {
        List<Comanda>com= (List<Comanda>) serv.get_allCom();
        int nrr=0;
        for(Comanda c :com)
        {
            if(nrr<c.getNr_comanda())
                nrr=c.getNr_comanda();
        }
        nrr++;
        for (Comanda cc :comezi)
        {
            cc.setNr_comanda(nrr);
            serv.add_com(cc);
        }
        comezi.clear();
        Alert a=new Alert(Alert.AlertType.INFORMATION, "Comanda va fi trimisa la farmacie.O zi buna!");
        a.show();
    }
    @FXML
    public void showInapoi() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/StartPageMedic.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Comanda Noua");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(root, 600, 450);
            dialogStage.setScene(scene);
            SPMedicController utilCont = loader.getController();
            utilCont.setUtil(user);
            utilCont.setService(serv,dialogStage,nr,comezi);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.close();



    }

}
