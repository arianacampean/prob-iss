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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViuzalizareDetController {
    ObservableList<String> model= FXCollections.observableArrayList();
    private Service serv;
    private Utilizator user;
    List<Comanda> comenzi=new ArrayList<>();
    @FXML
    ListView<String>list;
    Stage stage;
    String nrr;
    @FXML
    Button mod;
    @FXML
    TextField cant;
    @FXML
    CheckBox n;
    @FXML
    CheckBox u;
    @FXML
    Button sterg;



    @FXML
    public void initialize() {
        list.setItems(model);
    }

    public void setService(Service serv,List<Comanda> com,Stage stage,String nrr)
    {
        this.comenzi=com;
        this.serv=serv;
        this.stage=stage;
        this.nrr=nrr;
        lista();

    }
    public void setUtil(Utilizator u)
    {
        this.user=u;
    }
    public void lista()
    {
        List<Medicament>med= (List<Medicament>) serv.get_allMed();
        List<String>lis=new ArrayList<>();
        String s=" ";
        for(Comanda c:comenzi)
        {
            for(Medicament m:med)
                if(c.getId_medicament()==m.getId())
                    s=m.getNume()+" ,";

            s=s+"cantitate: "+c.getCantitate()+" ,stare: "+c.getStare();
            lis.add(s);


        }
        model.setAll(lis);


    }
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
        for (Comanda cc :comenzi)
        {
            cc.setNr_comanda(nrr);
            serv.add_com(cc);
        }
        comenzi.clear();
        Alert a=new Alert(Alert.AlertType.INFORMATION, "Comanda va fi trimisa la farmacie.O zi buna!");
        a.show();
        model.clear();
    }

    public void modif()
    {
        Alert a=new Alert(Alert.AlertType.INFORMATION, "Trebuie selectat medicamentul");
        int cantt= Integer.parseInt(cant.getText());
        String selected= String.valueOf(list.getSelectionModel().getSelectedItems());
        if(selected=="[]")
        {
            a.show();
        }
        else {
            String cv = selected.split(",")[0];
            List<Medicament> meds = (List<Medicament>) serv.get_allMed();
            int id_med = 0;
            for (Medicament m : meds) {
                String s = "[" + m.getNume() + " ";
                if (s.equals(cv))
                    id_med = m.getId();

            }
            for (Comanda c : comenzi) {
                if (c.getId_medicament() == id_med) {
                    c.setCantitate(cantt);
                    if (u.isSelected())
                        c.setStare(Stare.URGENTA);
                    else c.setStare(Stare.NORMALA);
                }
            }
            lista();
            cant.clear();
            u.setSelected(false);
            n.setSelected(false);
        }

    }

    public void del()
    {
        Alert a=new Alert(Alert.AlertType.INFORMATION, "Trebuie selectat medicamentul");
        String selected= String.valueOf(list.getSelectionModel().getSelectedItems());
        if(selected=="[]")
        {
            a.show();
        }
        else {
            String cv = selected.split(",")[0];
            List<Medicament> meds = (List<Medicament>) serv.get_allMed();
            int id_med = 0;
            for (Medicament m : meds) {
                String s = "[" + m.getNume() + " ";
                if (s.equals(cv))
                    id_med = m.getId();

            }
            int ceva=0;
            int nr = 0;
            for (Comanda c : comenzi) {
                if (c.getId_medicament() == id_med) {
                    nr=ceva;
                }else ceva++;
            }
            comenzi.remove(nr);
            lista();
            cant.clear();
            u.setSelected(false);
            n.setSelected(false);
        }

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
            utilCont.setService(serv,dialogStage,nrr,comenzi);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.close();



    }

}
